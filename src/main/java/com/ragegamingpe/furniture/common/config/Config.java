package com.ragegamingpe.furniture.common.config;

import com.ragegamingpe.furniture.common.Furniture;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class Config extends Configuration
{
    public Config(File file)
    {
        super(file, null);
        this.sync();

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void sync()
    {

        if (this.hasChanged()) {
            this.save();
        }
    }

    @SubscribeEvent
    public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Furniture.MODID)) {
            this.sync();
        }
    }
}
