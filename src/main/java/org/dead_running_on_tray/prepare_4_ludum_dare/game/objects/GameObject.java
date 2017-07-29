package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.UNIFIED_SPRITE_FILE_FORMAT;
import static org.newdawn.slick.util.ResourceLoader.getResourceAsStream;

public abstract class GameObject {
    private int id;
    private double x, y;
    private Texture sprite;

    GameObject(final int x, final int y, final int id, final String spritePath) {
        this.x = x;
        this.y = y;
        this.id = id;

        try {
            sprite = TextureLoader.getTexture(UNIFIED_SPRITE_FILE_FORMAT, getResourceAsStream(spritePath));
        } catch (Exception e) {
            System.err.println("Error while reading " + UNIFIED_SPRITE_FILE_FORMAT + " sprite for game object!");
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
        return sprite.getImageWidth();
    }

    public int getHeight() {
        return sprite.getImageHeight();
    }

    public Texture getSprite() {
        return sprite;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }
}
