package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Route {
    //private PriorityQueue<Point> routePriority = new PriorityQueue<>(new PointComparator<>());
    private ArrayList<Point> path = new ArrayList<>();
    private int index = 0;

    public void add2Route(Point p) {
        path.add(p);
    }

    public void removePointFromRoute(Point p) {
        path.remove(p);
    }

    private int nextIndex() {
        int size = path.size();
        return ((size + (index + 1) % size) % size);
    }

    public Point getCurrentDestination() {
        Point p = null;
        if (path.size() > 0) {
            p = path.get(index);
            index = nextIndex();
        } else {
            System.err.println("In getCurrentDestination()");
        }
        return p;
    }
}
