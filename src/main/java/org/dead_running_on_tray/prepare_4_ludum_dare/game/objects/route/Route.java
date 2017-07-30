package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Route {
    private PriorityQueue<Point> routePriority = new PriorityQueue<>(new PointComparator<>());
    private HashSet<Point> additionalPoints = new HashSet<>();
    private ArrayList<Point> path = new ArrayList<>();
    private int index = 0;

    public void add2Path(Point p) {
        path.add(p);
        routePriority.add(p);
    }

    public void add2Route(Point p) {
        additionalPoints.add(p);
        routePriority.add(p);
    }

    public void removePointFromRoute(Point p) {
        path.remove(p);
    }

    public void removeAdditionalPoint(Point p) {
        if (additionalPoints.size() > 0 && additionalPoints.contains(p)) {
            additionalPoints.remove(p);
            routePriority.remove(p);
        }
    }

    public void nextIndex() {
        int size = path.size();
        //index++;
        index = ((size + (index + 1) % size) % size);
    }

    public Point getCurrentDestination() {
        Point p = null;
        if (routePriority.peek().getPriority() > 0) {

        } else {
            if (path.size() > 0) {
                p = path.get(index);
                //nextIndex();
            } else {
                System.err.println("In getCurrentDestination()");
            }
        }
        return p;
    }
}
