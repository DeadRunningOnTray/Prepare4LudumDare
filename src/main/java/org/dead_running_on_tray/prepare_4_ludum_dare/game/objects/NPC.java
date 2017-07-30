package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;


import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route.Point;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route.Route;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Non playable character.
 */
public class NPC extends Character {
    /*protected ArrayList<Point> route = new ArrayList<>();*/
    protected Route route = new Route();
    private int currentDestination = 0;

    public NPC(int x, int y, int id, int scale, String spritePath) {
        super(x, y, id, scale, spritePath);
        //route = new ArrayList<>();
    }

    public NPC(int x, int y, int id, int scale, String spritePath, Route route) {
        super(x, y, id, scale, spritePath);
        this.route = route;
    }

    public NPC(int x, int y, int id, int scale, int scaleWidth, String spritePath) {
        super(x, y, id, scale, scaleWidth, spritePath);
    }

    /**
     * Custom constructor.
     * Parse text file that consists of lines of pairs "x,y".
     * These pairs are point of NPC walking route.
     * @param x - coordinate.
     * @param y - coordinate.
     * @param id - NPC identification number.
     * @param spritePath - image file path.
     * @param routeFile - file consists of pairs "x,y".
     */
    public NPC(int x, int y, int id, int scale, String spritePath, String routeFile) {
        super(x, y, id, scale, spritePath);
        //route = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(routeFile)))) {
            String line = reader.readLine();

            while (line != null) {
                String[] coordinates = line.split(",");
                route.add2Route(new Point(
                    Integer.parseInt(coordinates[0]),
                    Integer.parseInt(coordinates[1])
                ));
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error while reading characters route from file!");
        }
    }

    /*void nextPoint() {
        currentDestination++;

        if (currentDestination + 1 > route.size()) {
            currentDestination = 0;
        }
    }*/

    public Point getCurrentDestination() {
        return route.getCurrentDestination();
        /*return route.get(currentDestination);*/
    }

    public void addPointToRoute(Point p) {
        route.add2Route(p);
    }

    public void removePointFromRoute(Point p) {
        route.removePointFromRoute(p);
    }
}
