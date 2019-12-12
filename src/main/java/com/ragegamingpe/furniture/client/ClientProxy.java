package com.ragegamingpe.furniture.client;

import com.ragegamingpe.furniture.common.CommonProxy;
import com.ragegamingpe.furniture.common.block.base.IModBlock;
import com.ragegamingpe.furniture.common.item.base.IModItem;
import com.ragegamingpe.furniture.common.lib.LibBlocks;
import com.ragegamingpe.furniture.common.lib.LibItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        LibBlocks.ALL_BLOCKS.forEach(IModBlock::registerModels);
        LibItems.ALL_ITEMS.forEach(IModItem::registerModels);

        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        LibBlocks.ALL_BLOCKS.forEach(IModBlock::registerRender);
        LibItems.ALL_ITEMS.forEach(IModItem::registerRender);
    }
}
