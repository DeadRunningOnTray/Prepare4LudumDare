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

    public int getWidth() {
        return sprite.getWidth();
    }

    public int getHeight() {
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

        float unit_width = 0.7f;
        float unit_height = 0.7f;

        glBegin(GL_QUADS); {
            glTexCoord2f(0, 0);
            glVertex2f(-unit_width, unit_height);
            glTexCoord2f(1, 0);
            glVertex2f(unit_width, unit_height);
            glTexCoord2f(1, 1);
            glVertex2f(unit_width, -unit_height);
            glTexCoord2f(0, 1);
            glVertex2f(-unit_width, -unit_height);
        } glEnd();
    }
}
