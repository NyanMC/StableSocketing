package com.chromanyan.stablesocketing.mixin;

import daripher.apothiccurios.ApothicCuriosMod;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import dev.shadowsoffire.apotheosis.adventure.affix.socket.gem.Gem;
import dev.shadowsoffire.apotheosis.adventure.affix.socket.gem.GemInstance;
import dev.shadowsoffire.apotheosis.adventure.affix.socket.gem.bonus.GemBonus;
import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;

import java.util.Optional;

@Mixin(value = ApothicCuriosMod.class, remap = false)
public class ApothicCuriosModMixin {

    @Redirect(method = "removeTooltip(Lnet/minecraftforge/event/entity/player/ItemTooltipEvent;Ldev/shadowsoffire/apotheosis/adventure/affix/socket/gem/GemInstance;Lnet/minecraft/world/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Ldev/shadowsoffire/apotheosis/adventure/affix/socket/gem/Gem;getBonus(Ldev/shadowsoffire/apotheosis/adventure/loot/LootCategory;)Ljava/util/Optional;"))
    private Optional<GemBonus> actuallyGetBonus(Gem instance, LootCategory lootCategory, ItemTooltipEvent event, GemInstance gem, ItemStack stack) {

        return instance.getBonus(lootCategory, gem.rarity().get());
    }

}
