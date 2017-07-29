package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;
/**
 * Game player class.
 */
public class Player extends Character{
    private int progress = 0;

    public Player(int x, int y, int id, int scale, String spritePath) {
        super(x, y, id, scale, spritePath);
        this.setDamage(PLAYER_DAMAGE);
        this.setHealth(PLAYER_HEALTH);
    }

    public int getProgress() {
        return progress;
    }

    protected void setProgress(int progress) {
        this.progress = progress;
    }

    public void incProgress(int work) {
        progress += work;
    }
}