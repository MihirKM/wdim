package com.mihir.wdim.world.dimension;

import java.util.function.Function;

import com.mihir.wdim.block.ModBlocks;
import com.mihir.wdim.block.custom.TeleporterBlock;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;


public class SimpleTeleporter implements ITeleporter {
	public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDimension = true;

    // Scaling of the dimension.
    // TODO: Don't hardcode it
    private int worldScale = 24;
    
    public SimpleTeleporter(BlockPos pos, boolean insideDim) {
        thisPos = pos;
        insideDimension = insideDim;
    }

    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destinationWorld,
                              float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        double y = 61;

        BlockPos destinationPos;
        
        // Account for scaling.
        if (!insideDimension) {
            y = thisPos.getY();
            // Multiply by world scale.
            destinationPos = new BlockPos(thisPos.getX() * worldScale, y, thisPos.getZ() * worldScale);
        }
        else {
        	// Divide by world scale.
        	destinationPos = new BlockPos(thisPos.getX() / worldScale, y, thisPos.getZ() / worldScale);
        }
        

        int tries = 0;
        while ((destinationWorld.getBlockState(destinationPos).getMaterial() != Material.AIR) &&
                !destinationWorld.getBlockState(destinationPos).isReplaceable(Fluids.WATER) &&
                destinationWorld.getBlockState(destinationPos.up()).getMaterial() != Material.AIR &&
                !destinationWorld.getBlockState(destinationPos.up()).isReplaceable(Fluids.WATER) && tries < 25) {
            destinationPos = destinationPos.up(2);
            tries++;
        }

        entity.setPositionAndUpdate(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());

        if (insideDimension) {
            boolean doSetBlock = true;
            for (BlockPos checkPos : BlockPos.getAllInBoxMutable(destinationPos.down(10).west(10), destinationPos.up(10).east(10))) {
                if (destinationWorld.getBlockState(checkPos).getBlock() instanceof TeleporterBlock) {
                    doSetBlock = false;
                    break;
                }
            }
            if (doSetBlock) {
                destinationWorld.setBlockState(destinationPos, ModBlocks.TELEPORTER.get().getDefaultState());
            }
        }

        return entity;
    }
}
