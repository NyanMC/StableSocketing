package com.chromanyan.stablesocketing.mixin;

import daripher.apothiccurios.ApothicCuriosMod;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import shadows.apotheosis.adventure.affix.socket.gem.Gem;
import shadows.apotheosis.adventure.affix.socket.gem.GemInstance;
import shadows.apotheosis.adventure.affix.socket.gem.bonus.GemBonus;
import shadows.apotheosis.adventure.loot.LootCategory;

import java.util.Optional;

@Mixin(value = ApothicCuriosMod.class, remap = false)
public class ApothicCuriosModMixin {

    @Redirect(method = "removeTooltip(Lnet/minecraftforge/event/entity/player/ItemTooltipEvent;Lshadows/apotheosis/adventure/affix/socket/gem/GemInstance;Lnet/minecraft/world/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lshadows/apotheosis/adventure/affix/socket/gem/Gem;getBonus(Lshadows/apotheosis/adventure/loot/LootCategory;)Ljava/util/Optional;"))
    private Optional<GemBonus> actuallyGetBonus(Gem instance, LootCategory lootCategory, ItemTooltipEvent event, GemInstance gem, ItemStack stack) {

        return instance.getBonus(lootCategory, gem.rarity());
    }

}
