package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route;

import java.util.Comparator;

public class PointComparator<T extends Point> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        if (o1.getPriority() > o2.getPriority()) {
            return 1;
        } else if (o1.getPriority() < o2.getPriority()) {
            return -1;
        }
        return 0;
    }
}
