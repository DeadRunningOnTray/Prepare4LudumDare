package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;

/**
 * Non playable character.
 */
public class NPC extends Character {
    public NPC(int x, int y, int id, int scale, String spritePath) {
        super(x, y, id, scale, spritePath);
        this.setDamage(NPC_DAMAGE);
        this.setHealth(NPC_HEALTH);
    }
}
