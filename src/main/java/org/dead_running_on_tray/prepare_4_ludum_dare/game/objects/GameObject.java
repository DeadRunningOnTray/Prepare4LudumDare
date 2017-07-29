package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.texture.Texture;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_HEIGHT;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_WIDTH;
import static org.lwjgl.opengl.GL11.*;

public abstract class GameObject {
    private int id;
    private float x, y;
    private Texture sprite;

    GameObject(final int x, final int y, final int id, final String spritePath) {
        this.x = x;
        this.y = y;
        this.id = id;

        try {
            sprite = new Texture(spritePath);
        } catch (Exception e) {
            System.err.println("Error while reading sprite for game object!");
            sprite = null;
        }
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

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void draw() {
        sprite.bind();

        float unit_width = 2 * getWidth() / SCREEN_WIDTH;
        float unit_height = 2 * getHeight() / SCREEN_HEIGHT;

        System.out.println(unit_width + ", " + unit_height);

        float tx = 0.0F;
        float ty = 0.0F;

        glBegin(GL_QUADS);
        glTexCoord2f(tx, ty + 0.5F);
        glVertex2f(0.0F, 0.0F);
        glTexCoord2f(tx + 0.5F, ty + 0.5F);
        glVertex2f(unit_width, 0.0F);
        glTexCoord2f(tx + 0.5F, ty);
        glVertex2f(unit_width, unit_height);
        glTexCoord2f(tx, ty);
        glVertex2f(0.0F, unit_height);
        glEnd();

        //sprite.release();
    }
}
