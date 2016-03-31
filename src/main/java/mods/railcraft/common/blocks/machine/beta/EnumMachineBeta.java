/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.beta;

import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.machine.IEnumMachine;
import mods.railcraft.common.blocks.machine.TileMachineBase;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.modules.ModuleManager;
import mods.railcraft.common.modules.ModuleManager.Module;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CovertJaguar
 */
public enum EnumMachineBeta implements IEnumMachine<EnumMachineBeta> {

    TANK_IRON_WALL(Module.TRANSPORT, "tank.iron.wall", TileTankIronWall.class, 2, 1, 0, 0, 1, 1, 1, 1),
    TANK_IRON_GAUGE(Module.TRANSPORT, "tank.iron.gauge", TileTankIronGauge.class, 1, 5, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4),
    TANK_IRON_VALVE(Module.TRANSPORT, "tank.iron.valve", TileTankIronValve.class, 4, 1, 0, 0, 1, 1, 1, 1, 2, 3),
    BOILER_TANK_LOW_PRESSURE(Module.STEAM, "boiler.tank.pressure.low", TileBoilerTankLow.class, 2, 1, 0, 0, 1, 1, 1, 1),
    BOILER_TANK_HIGH_PRESSURE(Module.STEAM, "boiler.tank.pressure.high", TileBoilerTankHigh.class, 2, 1, 0, 0, 1, 1, 1, 1),
    BOILER_FIREBOX_SOLID(Module.STEAM, "boiler.firebox.solid", TileBoilerFireboxSolid.class, 3, 1, 0, 0, 1, 1, 1, 1, 2),
    BOILER_FIREBOX_FLUID(Module.STEAM, "boiler.firebox.liquid", TileBoilerFireboxFluid.class, 3, 1, 0, 0, 1, 1, 1, 1, 2),
    ENGINE_STEAM_HOBBY(Module.STEAM, "engine.steam.hobby", TileEngineSteamHobby.class, 1, 1, 0),
    ENGINE_STEAM_LOW(Module.STEAM, "engine.steam.low", TileEngineSteamLow.class, 1, 1, 0),
    ENGINE_STEAM_HIGH(Module.STEAM, "engine.steam.high", TileEngineSteamHigh.class, 1, 1, 0),
    SENTINEL(Module.CHUNK_LOADING, "anchor.sentinel", TileSentinel.class, 2, 1, 0, 0, 1, 1, 1, 1),
    VOID_CHEST(Module.TRANSPORT, "chest.void", TileChestVoid.class, 1, 1, 0),
    METALS_CHEST(Module.TRANSPORT, "chest.metals", TileChestMetals.class, 1, 1, 0),
    TANK_STEEL_WALL(Module.TRANSPORT, "tank.steel.wall", TileTankSteelWall.class, 2, 1, 0, 0, 1, 1, 1, 1),
    TANK_STEEL_GAUGE(Module.TRANSPORT, "tank.steel.gauge", TileTankSteelGauge.class, 1, 5, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4),
    TANK_STEEL_VALVE(Module.TRANSPORT, "tank.steel.valve", TileTankSteelValve.class, 4, 1, 0, 0, 1, 1, 1, 1, 2, 3);
    private final Module module;
    private final String tag;
    private final Class<? extends TileMachineBase<EnumMachineBeta>> tile;
    private final int[] textureInfo;
    private static final List<EnumMachineBeta> creativeList = new ArrayList<EnumMachineBeta>();
    private static final EnumMachineBeta[] VALUES = values();
    private ToolTip tip;

    static {
        creativeList.add(TANK_IRON_WALL);
        creativeList.add(TANK_IRON_GAUGE);
        creativeList.add(TANK_IRON_VALVE);
        creativeList.add(TANK_STEEL_WALL);
        creativeList.add(TANK_STEEL_GAUGE);
        creativeList.add(TANK_STEEL_VALVE);
        creativeList.add(BOILER_FIREBOX_SOLID);
        creativeList.add(BOILER_FIREBOX_FLUID);
        creativeList.add(BOILER_TANK_LOW_PRESSURE);
        creativeList.add(BOILER_TANK_HIGH_PRESSURE);
        creativeList.add(ENGINE_STEAM_HOBBY);
        creativeList.add(ENGINE_STEAM_LOW);
        creativeList.add(ENGINE_STEAM_HIGH);
        creativeList.add(SENTINEL);
        creativeList.add(VOID_CHEST);
        creativeList.add(METALS_CHEST);
    }

    EnumMachineBeta(Module module, String tag, Class<? extends TileMachineBase<EnumMachineBeta>> tile, int... textureInfo) {
        this.module = module;
        this.tile = tile;
        this.tag = tag;
        this.textureInfo = textureInfo;
    }

    public boolean register() {
        if (RailcraftConfig.isSubBlockEnabled(getTag())) {
            RailcraftBlocks.registerBlockMachineBeta();
            return getBlock() != null;
        }
        return false;
    }

    @Override
    public boolean isDepreciated() {
        return module == null;
    }

    public static EnumMachineBeta fromId(int id) {
        if (id < 0 || id >= VALUES.length)
            id = 0;
        return VALUES[id];
    }

    public static List<EnumMachineBeta> getCreativeList() {
        return creativeList;
    }

    @Override
    public String getTag() {
        return "tile.railcraft.machine.beta." + tag;
    }

    @Override
    public Class<? extends TileMachineBase<EnumMachineBeta>> getTileClass() {
        return tile;
    }

    @Override
    public TileMachineBase<EnumMachineBeta> getTileEntity() {
        try {
            return tile.newInstance();
        } catch (Exception ex) {
        }
        return null;
    }

    @Override
    public ItemStack getItem() {
        return getItem(1);
    }

    @Override
    public ItemStack getItem(int qty) {
        Block block = getBlock();
        if (block == null)
            return null;
        return new ItemStack(block, qty, ordinal());
    }

    public Module getModule() {
        return module;
    }

    @Override
    public Block getBlock() {
        return RailcraftBlocks.getBlockMachineBeta();
    }

    @Override
    public IBlockState getState() {
        return getBlock().getDefaultState().withProperty(MachineProxyBeta.VARIANT, this);
    }

    public boolean isEnabled() {
        return ModuleManager.isModuleLoaded(getModule()) && RailcraftConfig.isSubBlockEnabled(getTag());
    }

    @Override
    public boolean isAvailable() {
        return getBlock() != null && isEnabled();
    }

    @Override
    public ToolTip getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
        if (tip != null)
            return tip;
        String tipTag = getTag() + ".tip";
        if (LocalizationPlugin.hasTag(tipTag))
            tip = ToolTip.buildToolTip(tipTag);
        return tip;
    }

    @Override
    public String getName() {
        return name();
    }
}
