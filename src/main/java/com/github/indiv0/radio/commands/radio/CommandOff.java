package com.github.indiv0.radio.commands.radio;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.amshulman.mbapi.commands.PlayerOnlyCommand;
import com.amshulman.typesafety.TypeSafeCollections;
import com.amshulman.typesafety.TypeSafeList;
import com.github.indiv0.radio.management.RadioInfoManager;
import com.github.indiv0.radio.serialization.Frequency;
import com.github.indiv0.radio.util.Commands;
import com.github.indiv0.radio.util.RadioConfigurationContext;

public class CommandOff extends PlayerOnlyCommand {
    private final RadioInfoManager infoManager;
    private final int pipboyId;

    public CommandOff(final RadioConfigurationContext configurationContext) {
        super(configurationContext, Commands.OFF, 0, 0);
        infoManager = configurationContext.infoManager;
        pipboyId = configurationContext.pipboyId;
    }

    @Override
    protected boolean executeForPlayer(Player player, TypeSafeList<String> args) {
        // Makes sure that the currently held item is the "Pipboy".
        if (player.getItemInHand().getTypeId() != pipboyId) {
            player.sendMessage("You must be holding a compass to work the radio.");
            return true;
        }

        infoManager.setFrequency(player, Frequency.OFF);
        player.sendMessage("Successfully turned off the radio.");
        return true;
    }

    @Override
    public TypeSafeList<String> onTabComplete(final CommandSender sender, final TypeSafeList<String> args) {
        return TypeSafeCollections.emptyList();
    }
}
