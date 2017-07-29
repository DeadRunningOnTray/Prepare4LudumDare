package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.texture.Texture;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_HEIGHT;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_WIDTH;
import static org.lwjgl.opengl.GL11.*;

public abstract class GameObject {
    protected int id;
    protected float x, y;
    protected Texture sprite;

    /**
     * For inner using.
     */
    protected float unit_width, unit_height, normX, normY;

    public GameObject(int x, int y, int id, int scale, String spritePath) {
        this.x = x;
        this.y = y;
        this.id = id;

        try {
            sprite = new Texture(spritePath);
        } catch (Exception e) {
            System.err.println("Error while reading sprite for game object!");
            sprite = null;
        }

        unit_width = scale * getWidth() / SCREEN_WIDTH;
        unit_height = scale * getHeight() / SCREEN_HEIGHT;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public Texture getSprite() {
        return sprite;
    }

    public void draw() {
        sprite.bind();

        glBegin(GL_POLYGON);
        glTexCoord2f(0.0F, 1.0F);
        glVertex2f(normX, normY);
        glTexCoord2f(1.0F, 1.0F);
        glVertex2f(normX + unit_width, normY);
        glTexCoord2f(1.0F, 0.0F);
        glVertex2f(normX + unit_width, normY + unit_height);
        glTexCoord2f(0.0F, 0.0F);
        glVertex2f(normX, normY + unit_height);
        glEnd();
    }
}
