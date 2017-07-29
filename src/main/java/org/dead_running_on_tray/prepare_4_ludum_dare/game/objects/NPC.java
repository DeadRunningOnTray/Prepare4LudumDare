package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route.Point;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Non playable character.
 */
public class NPC extends Character {
    private ArrayList<Point> route;
    private int currentDestination = 0;

    public NPC(int x, int y, int id, String spritePath) {
        super(x, y, id, spritePath);
        route = new ArrayList<>();
    }

    public NPC(int x, int y, int id, String spritePath, ArrayList<Point> route) {
        super(x, y, id, spritePath);
        this.route = route;
    }

    public NPC(int x, int y, int id, String spritePath, String routeFile) {
        super(x, y, id, spritePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(routeFile)))) {
            String line = reader.readLine();

            while (line != null) {
                String[] coordinates = line.split(",");
                route.add(new Point(
                    Integer.parseInt(coordinates[0]),
                    Integer.parseInt(coordinates[1])
                ));
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error while reading characters route from file!");
            route = new ArrayList<>();
        }
    }

    private void nextPoint() {
        currentDestination++;

        if (currentDestination + 1 > route.size()) {
            currentDestination = 0;
        }
    }

    public void moveThroughRoute() {
        if (Math.abs(x - route.get(currentDestination).getX()) < 1) {
            x = route.get(currentDestination).getX();
        }

        if (Math.abs(y - route.get(currentDestination).getY()) < 1) {
            y = route.get(currentDestination).getY();
        }

        int dx = (int) x - route.get(currentDestination).getX();
        int dy = (int) y - route.get(currentDestination).getY();

        if (dx == 0 && dy == 0) {
            nextPoint();
        }

        if (dx > 0) {
            move(-1, 0);
        } else if (dx < -1) {
            move(1, 0);
        }

        if (dy > 0) {
            move(0, -1);
        } else if (dy < -1) {
            move(0, 1);
        }

        System.out.println(dx + " " + dy);
    }

    public void addPointToRoute(Point p) {
        route.add(p);
    }
}
