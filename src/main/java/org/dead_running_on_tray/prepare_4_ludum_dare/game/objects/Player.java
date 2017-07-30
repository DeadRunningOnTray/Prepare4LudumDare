package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route.Point;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.DynamicsType.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;

/**
 * Game player class.
 */
public class Player extends Character{
    private int progress = 0;
    private DynamicsType dyn = WALK;

    public Player(int x, int y, int id, int scale, String spritePath) {
        super(x, y, id, scale, spritePath);
        this.setDamage(PLAYER_DAMAGE);
        this.setHealth(PLAYER_HEALTH);
    }

    public Player(int x, int y, int id, int scale, int scaleWidth, String spritePath) {
        super(x, y, id, scale, scaleWidth, spritePath);
    }

    public int getProgress() {
        return progress;
    }

    protected void setProgress(int progress) {
        this.progress = progress;
    }

    public void incProgress(int work) {
        progress += work;
    }

    @Override
    public void move(float dx, float dy) {
        if (inversedX && dx > 0 || dx < 0 && !inversedX) {
            inversedX = !inversedX;
        }

        //if ((getX() + dx > RIGHT_BORDER && getX() + dx < LEFT_BORDER))
        {

            if (dyn == JUMP || (getX() + dx < RIGHT_BORDER && getX() + dx > LEFT_BORDER)) {
                System.out.println("XXXXXXX!");
                coordinates.move(dx, 0);
            }
            if (dyn == JUMP || (getY() + dy > LOW_BORDER && getY() + dy < HIGH_BORDER)) {
                System.out.println("YYYYYY!");
                coordinates.move(0, dy);
            }
            /*if ((dyn == JUMP || (getY() + dy > LOW_BORDER && getY() + dy < HIGH_BORDER))) {
                coordinates.move(dx, dy);
            } else {
                coordinates.move(dx, 0);
            }*/

            if (getY() + dy >= HIGH_BORDER && dy < 0) {
                coordinates.move(dx, dy);
            }
        }

        normX = coordinates.getX() / SCREEN_WIDTH;
        normY = coordinates.getY() / SCREEN_HEIGHT;
    }

    @Override
    public Point getCoordinates() {
        Point p = super.getCoordinates();
        p.setPriority(PLAYER_PRIORITY);
        p.setIsPlayer(true);
        return p;
    }

    public void jump() {
        dyn = JUMP;
    }

    public void fall() {
        dyn = FALL;
    }

    public void land() {
        dyn = WALK;
    }

    public boolean isWalking() {
        return dyn == WALK;
    }

    public boolean isInJump() {
        return dyn == JUMP;
    }

    public boolean isFalling() {
        return dyn == FALL;
    }
}