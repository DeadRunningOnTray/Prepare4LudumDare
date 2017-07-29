package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

public class EnemyProcessor {
    public static void process(Character c, Enemy e) {
        float dx = Math.abs(e.getX() - c.getX());
        float dy = Math.abs(e.getY() - c.getY());

        if (dx < 50 && dy < 50) {
            e.becomeAggressiveTo(c);
        }
    }
}
