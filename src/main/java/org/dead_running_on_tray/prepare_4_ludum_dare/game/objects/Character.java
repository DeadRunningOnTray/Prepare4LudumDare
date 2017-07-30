package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_HEIGHT;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.MAX_HEALTH;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.BULLET_SCALE;

/**
 * Game character.
 */
public abstract class Character extends GameMovingObject {
    private int health = MAX_HEALTH;
    private boolean alive = true;
    private int damage;

    public Character(int x, int y, int id, int scale, String spritePath) {
        super(x, y, id, scale, spritePath);
    }

    public Character(int x, int y, int id, int scale, int scaleWidth, String spritePath) {
        super(x, y, id, scale, scaleWidth, spritePath);
    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    protected void setDamage(int damage) {
        this.damage = damage;
    }

    public void decHealth(int damage) {
        health -= damage;

        if (health < 0) {
            alive = false;
        }

        System.out.println(health + " " + alive);
    }

    private void changeHealth(int dh) {
        if (alive) {
            health += dh;
            alive = health > 0;
        }
    }

    public void move(float dx, float dy) {
        if (inversedX && dx > 0 || dx < 0 && !inversedX) {
            inversedX = !inversedX;
        }

        if (getY() + dy > LOW_BORDER && getY() + dy < HIGH_BORDER) {
            coordinates.move(dx, dy);
        } else {
            coordinates.move(dx, 0);
        }

        normX = coordinates.getX() / SCREEN_WIDTH;
        normY = coordinates.getY() / SCREEN_HEIGHT;
    }

    public boolean isAlive() {
        return alive;
    }

    public Bullet shoot() {
        Bullet b = new Bullet(
            getX() + 200,
            getY() + 150,
            id,
            BULLET_SCALE,
            BULLET_PATH
        );

        if (inversedX) {
            b.inverseX();
        }

        return b;
    }
}
