package org.dead_running_on_tray.prepare_4_ludum_dare.game.location;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObject;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.backgrounds.Background;

import java.util.ArrayList;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;

/**
 * Game location class.
 */
public class Location implements ILocation {
    private Background background;
    private ArrayList<GameObject> backObjects;

    public Location(String path) {
        System.out.println("BACKGOUND PATH = " + BACKGROUND_PATH);
        background = new Background(BACKGROUND_X, BACKGROUND_Y, 0, BACKGROUND_SCALE, path);
        backObjects = new ArrayList<>();
    }

    public void draw() {
        for (GameObject o : backObjects) {
            o.draw();
        }

        background.draw();
    }
}
