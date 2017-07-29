package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route.Point;

import java.util.ArrayList;

public class Enemy extends NPC {
    private boolean aggressive = false;

    public Enemy(int x, int y, int id, String spritePath) {
        super(x, y, id, spritePath);
    }

    public Enemy(int x, int y, int id, String spritePath, ArrayList<Point> route) {
        super(x, y, id, spritePath);
    }

    public Enemy(int x, int y, int id, String spritePath, String routeFile) {
        super(x, y, id, spritePath, routeFile);
    }

    public void becomeAggressiveTo(Character c) {
        aggressive = true;
        route.clear();
        route.add(c.getCoordinates());
    }
}
