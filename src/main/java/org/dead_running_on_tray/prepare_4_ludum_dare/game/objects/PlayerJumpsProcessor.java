package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.JUMP_DURATION_MILLIS;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.JUMP_Y_INCREMENT;

public class PlayerJumpsProcessor {
    private static long lastTimeJump = -1;
    private static long lastTimeFall = -1;

    public static void process(Player p) {
        if (lastTimeJump == -1 && p.isInJump()) {
            lastTimeJump = System.currentTimeMillis();
            p.move(0, JUMP_Y_INCREMENT);
        } else if (p.isInJump() && (System.currentTimeMillis() - lastTimeJump) <= JUMP_DURATION_MILLIS / 2) {
            p.move(0, JUMP_Y_INCREMENT);
        } else if (p.isInJump() && (System.currentTimeMillis() - lastTimeJump) > JUMP_DURATION_MILLIS / 2) {
            lastTimeJump = -1;
            p.fall();
        }

        if (lastTimeFall == -1 && p.isFalling()) {
            lastTimeFall = System.currentTimeMillis();
            p.move(0, -JUMP_Y_INCREMENT);
        } else if (p.isFalling() && (System.currentTimeMillis() - lastTimeFall) <= JUMP_DURATION_MILLIS / 2) {
            p.move(0, -JUMP_Y_INCREMENT);
        } else if (p.isFalling() && (System.currentTimeMillis() - lastTimeFall) > JUMP_DURATION_MILLIS / 2) {
            lastTimeFall = -1;
            p.land();
        }
    }
}
