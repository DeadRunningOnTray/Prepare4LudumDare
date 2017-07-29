package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.MAX_HEALTH;

/**
 * Game character.
 */
public abstract class Character extends GameObject {
    private int health = MAX_HEALTH;
    private boolean isAlive = true;

    Character(final int x, final int y, int id, String spritePath) {
        super(x, y, id, spritePath);
    }

    private void changeHealth(int dh) {
        if (isAlive) {
            health += dh;
            isAlive = health > 0;
        }
    }
}
