package com.zerapps.autolever.util.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class ChatHandler
{
	/**
	 * Send a chat message to a player.
	 * @param message The message to send.
	 * @param player The player to receive the message.
	 */
	public static void SendMessageToPlayer(String message, EntityPlayer player)
	{
		ChatComponentTranslation chat = new ChatComponentTranslation(message, new Object[0]);
		chat.getChatStyle().setColor(EnumChatFormatting.WHITE);
		
		player.addChatMessage(chat);
	}
}
