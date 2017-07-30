package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.NPC_SPEED_X;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.NPC_SPEED_Y;

public class NpcProcessor {
    public static void process(NPC npc) {
        float dx = npc.getX() - npc.getCurrentDestination().getX();
        float dy = npc.getY() - npc.getCurrentDestination().getY();

        if (Math.abs(dx) < 1 && Math.abs(dy) < 1) {
            npc.nextPoint();
        }

        if ((int) dx > 0) {
            npc.move(-NPC_SPEED_X, 0);
        } else if ((int) dx < 0) {
            npc.move(NPC_SPEED_X, 0);
        }

        if ((int) dy > 0) {
            npc.move(0, -NPC_SPEED_Y);
        } else if ((int) dy < 0) {
            npc.move(0, NPC_SPEED_Y);
        }
    }
}
