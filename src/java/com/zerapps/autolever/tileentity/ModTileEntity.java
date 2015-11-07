package com.zerapps.autolever.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class ModTileEntity extends TileEntity
{
	private int leverDelay;
	
	/**
	 * Save all tile entity data.
	 */
	@Override
    public void writeToNBT(NBTTagCompound compound)
	{
        super.writeToNBT(compound);

        compound.setInteger("leverDelay", leverDelay);
    }
	
	/**
	 * Load all tile entity data.
	 */
	@Override
    public void readFromNBT(NBTTagCompound compound)
	{
        super.readFromNBT(compound);

        leverDelay = compound.getInteger("leverDelay");
	}
	
	/**
	 * Sync the tile entity data to the server and update.
	 */
	@Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        writeToNBT(syncData);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
    }
	
	/**
	 * Read the tile entity data from the server.
	 */
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.func_148857_g());
    }
	
	public void setLeverDelay(int delay)
	{
		leverDelay = delay;
		markDirty();
	}
	
	public int getLeverDelay()
	{
		return leverDelay;
	}
}
