package org.dead_running_on_tray.prepare_4_ludum_dare.game.logic;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.Player;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.players.players_properties_constants.PropertiesConstants.*;

public class WinOrLose {
    public static boolean isWinner(Player player, int time) {
        return (player.getHealth() > 0 && player.getProgress() >= MAX_PROGRESS && time <= TIME_IN_SECONDS);
    }

    public static boolean isLooser(Player player, int time) {
        return (player.getHealth() <= 0 || time > TIME_IN_SECONDS && player.getProgress() < MAX_PROGRESS);
    }
}
