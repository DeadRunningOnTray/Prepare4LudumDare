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
        System.out.println("IN ADD2ROUTE!");
        //System.out.println();

        /*try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        if (!additionalPoints.contains(p)) {
            System.out.println("ADD IT!");
            additionalPoints.add(p);
            routePriority.add(p);
        }
        else {
            System.out.println("DONT ADD IT!!!");
        }

        System.out.println();
    }

    public void removePointFromRoute(Point p) {
        path.remove(p);
    }

    public void removeAdditionalPoint(Point p) {
        System.out.println("IN ROUTE!!");

        System.out.println();
        System.out.println("1 = " + additionalPoints.size());
        System.out.println("2 = " + additionalPoints.contains(p));
        System.out.println("3 = " + routePriority.peek());
        System.out.println();

        /*try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        if (additionalPoints.size() > 0 && additionalPoints.contains(p)) {
            System.out.println("IN INNER OF IF!!!!");
            additionalPoints.remove(p);
            routePriority.remove(p);
        }
    }

    public Point getAdditional() {
        return routePriority.peek();
    }

    public void nextIndex() {
        int size = path.size();
        //index++;
        index = ((size + (index + 1) % size) % size);
    }

    public boolean haveAdditionalPoint() {
        return additionalPoints.size() > 0;
    }

    public Point getCurrentDestination() {
        Point p = null;
        System.out.println("PEEK = " + routePriority.peek());
        System.out.println();

        /*try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        if (additionalPoints.size() > 0 && routePriority.peek().getPriority() > 0) {
            System.out.println(routePriority.peek());
            p = routePriority.peek();
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
