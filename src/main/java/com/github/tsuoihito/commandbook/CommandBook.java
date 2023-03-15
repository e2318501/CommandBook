package com.github.tsuoihito.commandbook;

import org.bukkit.plugin.java.JavaPlugin;

public final class CommandBook extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BookListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
