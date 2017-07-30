package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

public class NpcRouteProcessor {
    public static void process(NPC npc) {
        float dx = npc.getX() - npc.getCurrentDestination().getX();
        float dy = npc.getY() - npc.getCurrentDestination().getY();

        if (Math.abs(dx) < 1 && Math.abs(dy) < 1) {
            npc.getCurrentDestination();
            //npc.nextPoint();
        }

        if ((int) dx > 0) {
            npc.move(-1, 0);
        } else if ((int) dx < 0) {
            npc.move(1, 0);
        }

        if ((int) dy > 0) {
            npc.move(0, -1);
        } else if ((int) dy < 0) {
            npc.move(0, 1);
        }
    }
}
