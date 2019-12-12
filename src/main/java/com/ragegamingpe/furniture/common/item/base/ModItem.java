package com.ragegamingpe.furniture.common.item.base;

import com.ragegamingpe.furniture.client.model.ModelHelper;
import com.ragegamingpe.furniture.common.Furniture;
import com.ragegamingpe.furniture.common.lib.LibItems;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

public class ModItem<V extends Enum & IStringSerializable> extends Item implements IModItem
{
    protected Map<Integer, V> subtypes;

    public ModItem(String regName)
    {
        super();

        this.setUnlocalizedName(regName);
        this.setRegistryName(Furniture.MODID, regName);
        LibItems.ALL_ITEMS.add(this);
    }

    public Item setSubtypes(Class<V> types)
    {
        this.setHasSubtypes(true);

        this.subtypes = new HashMap<>();
        for (V variant : types.getEnumConstants()) {
            this.subtypes.put(variant.ordinal(), variant);
        }

        return this;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s:%s", Furniture.MODID, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (this.getHasSubtypes()) {
            return getUnlocalizedName() + "." + this.subtypes.get(stack.getMetadata()).getName();
        }

        return getUnlocalizedName();
    }

    @Override
    public ModItem setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (!this.getHasSubtypes()) {
            super.getSubItems(tab, items);
        } else {
            for (Map.Entry<Integer, V> entry : this.subtypes.entrySet()) {
                items.add(new ItemStack(this, 1, entry.getKey()));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModels()
    {
        ResourceLocation[] locations = new ResourceLocation[subtypes.size()];
        for (Map.Entry<Integer, V> entry : this.subtypes.entrySet()) {
            locations[entry.getKey()] = new ResourceLocation(
                    Furniture.MODID,
                    getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + "." + entry.getValue().getName()
            );
        }

        ModelBakery.registerItemVariants(this, locations);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerRender()
    {
        if (this.getHasSubtypes()) {
            for (Map.Entry<Integer, V> entry : this.subtypes.entrySet()) {
                ModelHelper.registerItemModel(this, entry.getKey(), getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + "." + entry.getValue().getName());
            }
        } else {
            ModelHelper.registerItemModel(this, 0, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
        }
    }
}
