package com.mihir.wdim.block.custom;

import com.mihir.wdim.world.dimension.ModDimensions;
import com.mihir.wdim.world.dimension.SimpleTeleporter;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TeleporterBlock extends Block {
	public TeleporterBlock(Properties properties) {
		super(properties);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            if (!player.isCrouching()) {
                MinecraftServer server = worldIn.getServer();

                if (server != null) {
                    if (worldIn.getDimensionKey() == ModDimensions.WDim) {
                        ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                        if (overWorld != null) {
                            player.changeDimension(overWorld, new SimpleTeleporter(pos, false));
                        }
                    } else {
                        ServerWorld wDim = server.getWorld(ModDimensions.WDim);
                        if (wDim != null) {
                            player.changeDimension(wDim, new SimpleTeleporter(pos, true));
                        }
                    }
                    return ActionResultType.SUCCESS;
                }
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }


}
