package wiiv.emporium.block.tile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.animation.Animation;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.animation.TimeValues.VariableValue;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import wiiv.emporium.Emporium;
import wiiv.emporium.Globals;

public class TileStorageCabinetWood1 extends TileEntity {
	
	/**
     *  Our Animation state controller. We can use this to, for example, query the current state, or transition
     *  to a different state.
     *
     *  This is assigned through the proxy and is null server side.
     */
    private final IAnimationStateMachine asm;

    /**
     * Here we define our variables we wish to act as parameters in the state machine. These get mapped to parameters
     * that we can access in the asm json files. Use these to trigger events or transition to a different state. The
     * values are set through code. See {@link #click()} for an example.
     */
    private final VariableValue clickTime = new VariableValue(Float.NEGATIVE_INFINITY);
	
	public TileStorageCabinetWood1() {

        /**
         * This is loaded through the proxy. Server side returns null. Here we also map {@link VariableValue} from above
         * that we wish to access from the asm json file. See {@link assets.anim.asms.block#lever.json}
         */
        asm = Emporium.proxy.load(new ResourceLocation(Globals.MOD_ID, "asms/block/cabinet_wood.json"), ImmutableMap.<String, ITimeValue>of("click_time", clickTime));
    }
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing side) {
        return capability == CapabilityAnimation.ANIMATION_CAPABILITY || super.hasCapability(capability, side);
    }

    @Nonnull
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing side) {
        if(capability == CapabilityAnimation.ANIMATION_CAPABILITY) {
            return CapabilityAnimation.ANIMATION_CAPABILITY.cast(asm);
        }
        return super.getCapability(capability, side);
    }

    public void handleEvents(float time, Iterable<Event> pastEvents) { }

    /**
     * Example method showing how to change states from code.
     */
    public void click() {
        if(asm != null) {
            if(asm.currentState().equals("open")) {
                float time = Animation.getWorldTime(getWorld(), Animation.getPartialTickTime());
                clickTime.setValue(time);
                asm.transition("closing");
            } else if(asm.currentState().equals("closed")) {
                float time = Animation.getWorldTime(getWorld(), Animation.getPartialTickTime());
                clickTime.setValue(time);
                asm.transition("opening");
            }
        }
    }

}