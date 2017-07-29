package org.dead_running_on_tray.prepare_4_ludum_dare.game.location;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObject;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.backgrounds.Background;

import java.util.ArrayList;

/**
 * Game location class.
 */
public class Location implements ILocation {
    private Background background;
    private ArrayList<GameObject> backObjects;

    public Location(String path) {
        background = new Background(0, 0, 0, 1, path);
        backObjects = new ArrayList<>();
    }

    public void draw() {
        for (GameObject o : backObjects) {
            o.draw();
        }

        background.draw();
    }
}
