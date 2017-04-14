package eyeq.shatterskeleton.entity.monster;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityShatterSkeleton extends EntitySkeleton {
    public EntityShatterSkeleton(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(world.isRemote) {
            return;
        }
        if(!world.getGameRules().getBoolean("mobGriefing")) {
            return;
        }
        BlockPos pos = this.getPosition();
        if(onGround && rand.nextInt(10) == 0) {
            pos = pos.add(rand.nextInt(3) - 1, 0, rand.nextInt(3) - 1);
        } else {
            pos = pos.down();
        }
        IBlockState state = world.getBlockState(pos);
        if(state.getBlock() instanceof IGrowable) {
            IGrowable block = (IGrowable) state.getBlock();
            if(block.canGrow(world, pos, state, false) && block.canUseBonemeal(world, rand, pos, state)) {
                block.grow(world, rand, pos, state);
            }
            world.playBroadcastSound(2005, pos, 0);
        }
    }
}
