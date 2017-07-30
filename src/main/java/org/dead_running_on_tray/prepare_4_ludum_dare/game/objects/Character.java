package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.Bullet;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.BULLET_PATH;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.MAX_HEALTH;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.BULLET_SCALE;

/**
 * Game character.
 */
public abstract class Character extends GameMovingObject {
    private int health = MAX_HEALTH;
    private boolean isAlive = true;
    private int damage;

    public Character(int x, int y, int id, int scale, String spritePath) {
        super(x, y, id, scale, spritePath);
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
    }

    private void changeHealth(int dh) {
        if (isAlive) {
            health += dh;
            isAlive = health > 0;
        }
    }

    public Bullet shoot() {
        Bullet b = new Bullet(
            getX() + getWidth(),
            getY() + getHeight(),
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
