package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import java.util.ArrayList;
import java.util.Iterator;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_WIDTH;

public class BulletsProcessor {
    public static void process(ArrayList<Bullet> bullets) {
        deleteUnreachable(bullets);
        for (Bullet b : bullets) {
            b.move();
        }
    }

    private static void deleteUnreachable(ArrayList<Bullet> bullets) {
        for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext();) {
            Bullet b = iterator.next();
            if (b.getX() < -SCREEN_WIDTH || b.getX() > SCREEN_WIDTH) {
                iterator.remove();
            }
        }
    }
}
