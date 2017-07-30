package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route;

import java.util.PriorityQueue;

public class Route {
    private PriorityQueue<Point> route = new PriorityQueue<>(new PointComparator<Point>());

    public void add2Route(Point p) {
        route.add(p);
    }

    public void removeFromRoute(Point p) {
        route.remove(p);
    }

    public Point getDestination() {
        return route.remove();
    }
}
