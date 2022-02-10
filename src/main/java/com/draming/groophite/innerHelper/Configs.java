package com.draming.groophite.innerHelper;

import net.minecraftforge.common.config.Config;

@Config(modid = "groophite")

public final class Configs {

    @Config.Comment("Configure the number of placeholder items.")
    @Config.Name("Placeholder Item Count")
    @Config.RangeInt(min = 0, max = 120)
    @Config.RequiresMcRestart
    public static int placeholder_item_count = 0;

}