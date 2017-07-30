package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.texture.Texture;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_HEIGHT;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_WIDTH;
import static org.lwjgl.opengl.GL11.*;

public abstract class GameMovingObject extends GameObject {


    public GameMovingObject(int x, int y, int id, int scale, String spritePath) {
        super(x, y, id, scale, spritePath);
    }

    public GameMovingObject(int x, int y, int id, int scale, int scaleWidth, String spritePath) {
        super(x, y, id, scale, scaleWidth, spritePath);
    }

    public void move(float dx, float dy) {
        if (inversedX && dx > 0 || dx < 0 && !inversedX) {
            inversedX = !inversedX;
        }

        coordinates.move(dx, dy);
        normX = coordinates.getX() / SCREEN_WIDTH;
        normY = coordinates.getY() / SCREEN_HEIGHT;
    }
}
