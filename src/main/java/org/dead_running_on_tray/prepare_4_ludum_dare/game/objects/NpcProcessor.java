package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route.Point;

public class NpcProcessor {
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

    public static boolean isVisible(NPC npc, Character character, float radius, float radius1) {
        Point npcPoint = npc.getCoordinates();
        Point characterPoint = character.getCoordinates();

        float npcHeight = npc.getUnit_height();
        float npcWidth = npc.getUnit_width();
        float characterHeight = character.getUnit_height();
        float characterWidth = character.getUnit_width();

        float x1 = npcPoint.getX() - npcWidth / 2, x2 = characterPoint.getX() - characterWidth / 2;
        float y1 = npcPoint.getY() - npcHeight / 2, y2 = character.getY() - characterHeight / 2;

        float dx = x1 - x2;
        float dy = y1 - y2;
        return dx * dx + dy * dy < radius * radius/* && dx * dx + dy * dy > radius1 * radius1*/;
    }
}
