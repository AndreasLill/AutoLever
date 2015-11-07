package com.zerapps.autolever.blocks;

import java.util.Random;

import com.zerapps.autolever.tileentity.AutoTileEntity;
import com.zerapps.autolever.util.ModBlocks;
import com.zerapps.autolever.util.ModReferences;
import com.zerapps.autolever.util.handler.ChatHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Works like a regular lever with the ability to self-reset with a customizable timer.
 * Hit the lever with a "Redstone Torch" to change the time.
 */
public class AutoLeverBlock extends BlockLever implements ITileEntityProvider
{
	private int resetDelay = 0;
	
	public AutoLeverBlock()
	{
		this.setHardness(0.5f);
		this.setStepSound(soundTypeWood);
		this.setBlockName("autolever");
		this.setBlockTextureName(ModReferences.MOD_ID + ":" + "autolever");
		this.isBlockContainer = true;
	}
	
	/**
	 * Returns a new instance of a block's tile entity class.
	 * Called on placing the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new AutoTileEntity();
	}
	
	/**
	 * Remove the tile entity when destroyed for performance.
	 * Called on the block breaking.
	 */
	@Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
        super.breakBlock(world, x, y, z, block, meta);
        
        world.removeTileEntity(x, y, z);
    }
	
	/**
	 * Returns the tick rate of the lever.
	 * The tick rate is N times the reset delay in seconds.
	 */
	@Override
	public int tickRate(World world)
    {
        return ModBlocks.TICK_SECOND * resetDelay;
    }
	
	/**
	 * Ticks the block if it's been scheduled to update.
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (!world.isRemote)
        {
    		int metadata = world.getBlockMetadata(x, y, z);

            if ((metadata & 8) != 0)
            {
            	// Reset the lever to cut the power.
            	
                world.setBlockMetadataWithNotify(x, y, z, metadata & 7, 3);
                world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "random.click", 0.3F, 0.5F);
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
        }
    }
	
	/**
	 * Changes the delay of the lever, saves the tile entity and notifies the player of the new delay.
	 * @param tileEntity The block tile entity.
	 * @param player The player who used the lever.
	 */
	private void changeDelay(AutoTileEntity tileEntity, EntityPlayer player)
	{
		switch (resetDelay)
		{
			case 0:
				resetDelay = 1;
				break;
			case 1:
				resetDelay = 2;
				break;
			case 2:
				resetDelay = 3;
				break;
			case 3:
				resetDelay = 5;
				break;
			case 5:
				resetDelay = 8;
				break;
			case 8:
				resetDelay = 10;
				break;
			default:
				resetDelay = 0;
				break;
		}
		
		tileEntity.setLeverDelay(resetDelay);
		
		if (resetDelay > 0)
		{
			ChatHandler.SendMessageToPlayer("Self-Reset changed to " + resetDelay + " sec.", player);
		}
		else
		{
			ChatHandler.SendMessageToPlayer("Self-Reset disabled.", player);
		}
	}
	
	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float sideX, float sideY, float sideZ)
	{
	    if (!world.isRemote)
	    {
	    	AutoTileEntity tileEntity = (AutoTileEntity) world.getTileEntity(x, y, z);
	    	resetDelay = tileEntity.getLeverDelay();
	    	
	    	// Get the item in player's hand.
	    	ItemStack itemInHand = player.getCurrentEquippedItem();
	    	
	    	// Check if a "Redstone Torch" is held.
	    	if (itemInHand != null && itemInHand.getUnlocalizedName().equals(Blocks.redstone_torch.getUnlocalizedName()))
	    	{
	    		if (tileEntity != null)
	            {
	    			// If a "Redstone Torch" is held, change the reset delay of the lever.
	    			this.changeDelay(tileEntity, player);
	            }
	    	}
	    	else
	    	{
	    		// Use the lever and update position.
	    		int metadata = world.getBlockMetadata(x, y, z);
		        int j1 = metadata & 7;
		        int k1 = 8 - (metadata & 8);
		        world.setBlockMetadataWithNotify(x, y, z, j1 + k1, 3);
		        world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "random.click", 0.3F, k1 > 0 ? 0.6F : 0.5F);
		        world.notifyBlocksOfNeighborChange(x, y, z, this);
		        
		        // Schedule for self-reset if applicable.
		        if (resetDelay > 0)
		        {
		        	world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
		        }
		
		        if (j1 == 1)
		        {
		        	world.notifyBlocksOfNeighborChange(x - 1, y, z, this);
		        }
		        else if (j1 == 2)
		        {
		        	world.notifyBlocksOfNeighborChange(x + 1, y, z, this);
		        }
		        else if (j1 == 3)
		        {
		        	world.notifyBlocksOfNeighborChange(x, y, z - 1, this);
		        }
		        else if (j1 == 4)
		        {
		        	world.notifyBlocksOfNeighborChange(x, y, z + 1, this);
		        }
		        else if (j1 != 5 && j1 != 6)
		        {
		            if (j1 == 0 || j1 == 7)
		            {
		            	world.notifyBlocksOfNeighborChange(x, y + 1, z, this);
		            }
		        }
		        else
		        {
		        	world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
		        }
	    	}
	    }
	    
	    return true;
	}
}
