package org.dead_running_on_tray.prepare_4_ludum_dare.game.location;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObject;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.backgrounds.Background;

import java.util.ArrayList;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_HEIGHT;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_WIDTH;

/**
 * Game location class.
 */
public class Location implements ILocation {
    private Background background;
    private ArrayList<GameObject> backObjects;

    public Location(String path) {
        background = new Background (
            -SCREEN_WIDTH,
            -3 * SCREEN_HEIGHT / 2,
            0,
            2,
            path
        );
        backObjects = new ArrayList<>();
    }

    public void draw() {
        for (GameObject o : backObjects) {
            o.draw();
        }

        background.draw();
    }
}
