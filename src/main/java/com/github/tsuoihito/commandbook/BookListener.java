package com.github.tsuoihito.commandbook;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;

public class BookListener implements Listener {
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        if (!event.isSneaking()) {
            BookHandler
                .getCommands(event.getPlayer().getInventory().getItemInMainHand())
                .ifPresent(cmds ->
                    cmds.forEach(cmd -> event.getPlayer().performCommand(cmd))
                );
        }
    }
}
