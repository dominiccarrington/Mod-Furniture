package com.ragegamingpe.furniture.common.block.base;

import com.ragegamingpe.furniture.client.model.ModelHelper;
import com.ragegamingpe.furniture.common.Furniture;
import com.ragegamingpe.furniture.common.lib.LibBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;

public class ModBlock extends Block implements IModBlock
{
    public ModBlock(Material material, MapColor color, String regName)
    {
        super(material, color);

        this.setUnlocalizedName(regName);
        this.setRegistryName(Furniture.MODID, regName);
        LibBlocks.ALL_BLOCKS.add(this);
    }

    public ModBlock(Material material, String regName)
    {
        this(material, material.getMaterialMapColor(), regName);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s:%s", Furniture.MODID, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public ModBlock setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public void registerRender()
    {
        ModelHelper.registerItemModel(this, 0, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected static AxisAlignedBB createAABB(int x1, int y1, int z1, int x2, int y2, int z2)
    {
        return new AxisAlignedBB(x1 / 16F, y1 / 16F, z1 / 16F, x2 / 16F, y2 / 16F, z2 / 16F);
    }

    public ItemBlock getItemBlock()
    {
        return (ItemBlock) new ItemBlock(this).setRegistryName(this.getRegistryName());
    }
}
