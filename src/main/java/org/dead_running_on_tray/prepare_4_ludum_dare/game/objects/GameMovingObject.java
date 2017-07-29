package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.texture.Texture;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_HEIGHT;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_WIDTH;
import static org.lwjgl.opengl.GL11.*;

public class GameMovingObject extends GameObject {

    GameMovingObject(int x, int y, int id, int scale, String spritePath) {
        super(x, y, id, scale, spritePath);
    }

    public void move(float dx, float dy) {
        x += dx;
        y += dy;
        normX = x / SCREEN_WIDTH;
        normY = y / SCREEN_HEIGHT;

        System.out.println(x + " " + y);
    }
}
