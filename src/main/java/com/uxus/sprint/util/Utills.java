package com.uxus.sprint.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.item.Items;

public class Utills {
    MinecraftClient mc = MinecraftClient.getInstance();

    /*public String totemcount(){
        int totems = mc.player.inventory.mainInventory.stream().filter(itemStack -> (itemStack.getItem() == Items.TOTEM_OF_UNDYING)).mapToInt(ItemStack::getCount).sum();
        if (mc.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING)
            totems += mc.player.getOffHandStack().getCount();
        return String.valueOf(totems);
    }
*/
    public static String totemcount(){
        MinecraftClient mc = MinecraftClient.getInstance();
        int totems = mc.player.getInventory().count(Items.TOTEM_OF_UNDYING);
        return String.valueOf(totems);
    }

    public static int getPing() {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayerEntity player = mc.player;
        PlayerListEntry entry = mc.getNetworkHandler().getPlayerListEntry(player.getUuid());
        if (entry != null) {
            return entry.getLatency();
        }
        return 0;
    }
}



