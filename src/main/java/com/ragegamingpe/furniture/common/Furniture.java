package com.ragegamingpe.furniture.common;

import com.ragegamingpe.furniture.common.config.Config;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Furniture.MODID, name = Furniture.NAME, version = Furniture.VERSION, dependencies = Furniture.DEPENDENCIES, guiFactory = Furniture.CONFIG_GUI)
public class Furniture
{
    public static final String MODID = "furniture";
    public static final String NAME = "WNA Furniture Mod";
    public static final String VERSION = "GRADLE:VERSION";
    public static final String DEPENDENCIES = "";
    public static final String CONFIG_GUI = "com.ragegamingpe.furniture.client.gui.config.GuiFactory";

    public static Logger logger;

    @Mod.Instance
    public static Furniture instance;

    @SidedProxy(
            clientSide = "com.ragegamingpe.furniture.client.ClientProxy",
            serverSide = "com.ragegamingpe.furniture.common.CommonProxy"
    )
    private static CommonProxy proxy;

    public static Config config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

    private String enabledOrDisabled(boolean b)
    {
        return b ? "Enabled" : "Disabled";
    }
}