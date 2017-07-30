package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameMovingObject;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.BULLET_SPEED;

public class Bullet extends GameMovingObject {
    public Bullet(int x, int y, int id, int scale, String spritePath) {
        super(x, y, id, scale, spritePath);
    }

    public Bullet(float x, float y, int id, int scale, String spritePath) {
        super(x, y, id, scale, spritePath);
    }

    public void move() {
        if (inversedX) {
            move(-BULLET_SPEED, 0);
        } else {
            move(BULLET_SPEED, 0);
        }
    }
}
