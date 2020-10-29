package com.ytg123.manhunt.config;

import com.google.common.collect.Lists;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

import java.util.ArrayList;
import java.util.List;

import static me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.Gui;

@Config(name = "manhunt")
public class ManhuntConfig implements ConfigData {
    @Gui.EnumHandler(option = Gui.EnumHandler.EnumDisplayOption.BUTTON)
    @Comment(
            "Sets the behaviour of the compass mechanic, can be either UPDATE or USE. UPDATE = Automatically update the compass every tick. USE = Use the compass to update it (more like Dream's manhunt).")
    @Gui.Tooltip(count = 3)
    public Behaviours.Compass compassBehaviour = Behaviours.Compass.USE;

    @Gui.EnumHandler(option = Gui.EnumHandler.EnumDisplayOption.BUTTON)
    @Comment(
            "Sets the behaviour of the damage mechanic. Can be either KILL or DAMAGE. KILL = The speedrunner loses when they are killed (like Dream's manhunt). DAMAGE = The speedrunner loses when they take damage (like dream's assassin).")
    @Gui.Tooltip(count = 3)
    public Behaviours.Damage damageBehaviour = Behaviours.Damage.KILL;

    @Comment("If true, gives players a compass when added to the hunters list.")
    @Gui.Tooltip
    public boolean giveCompassWhenSettingHunters = true;

    @Comment("Dimensions that the compass won't work in.")
    @Gui.Tooltip
    public List<String> disabledDimensions = Lists.newArrayList("minecraft:the_nether", "minecraft:the_end");

    /*
     * @Comment("Freeze the hunter when looked at, similar to Dream's assassin.")
     * @Gui.Tooltip(count = 0)
     * public boolean freeze = false; */
}
