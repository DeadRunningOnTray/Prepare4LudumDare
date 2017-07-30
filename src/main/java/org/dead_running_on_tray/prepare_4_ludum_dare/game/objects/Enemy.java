package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route.Point;

import java.util.ArrayList;

public class Enemy extends NPC {
    private boolean aggressive = false;

    public Enemy(int x, int y, int id, int scale, String spritePath) {
        super(x, y, id, scale, spritePath);
    }

    public Enemy(int x, int y, int id, int scale, String spritePath, ArrayList<Point> route) {
        super(x, y, id, scale, spritePath);
    }

    public Enemy(int x, int y, int id, int scale, String spritePath, String routeFile) {
        super(x, y, id, scale, spritePath, routeFile);
    }

    public void becomeAggressiveTo(Character c) {
        aggressive = true;
        route.clear();
        route.add(c.getCoordinates());
    }
}
