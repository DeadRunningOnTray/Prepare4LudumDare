package org.dead_running_on_tray.prepare_4_ludum_dare.game.frame;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.*;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.location.Location;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route.Point;
import org.lwjgl.stb.STBEasyFont;

import java.util.ArrayList;
import java.util.Random;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.PLAYER_SPEED_Y;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.PLAYER_SPEED_X;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.NPC_SCALE;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.PLAYER_SCALE;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.frame.frame_state.FrameState.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.logic.WinOrLose.*;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.PLAYER_SPEED_X;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.GameObjectsConstants.PLAYER_SPEED_Y;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.NpcProcessor.*;

public class GameFrame extends Frame {
    private Location location;
    private Player player;
    private ArrayList<NPC> npcs;
    private ArrayList<Bullet> bullets;

    private long startTime;
    private long endTime;


    //shooting timer.
    private long shootLastTime;

    private Point lastPlayerVisiblePoint;
    private long lastVisibleTime = -1;
    private long now;

    //private ILocation.Option currentLocation = ILocation.Option.START;
    //private HashMap<ILocation.Option, ILocation> locationsMap;

    private Random random = new Random(/*42*/System.currentTimeMillis());

    private int getNPCBornX() {
        return RIGHT_BORN_ENEMY_SCREEN_WIDTH;
        //return (random.nextBoolean() ? LEFT_BORN_ENEMY_SCREEN_WIDTH : RIGHT_BORN_ENEMY_SCREEN_WIDTH);
    }

    private int getNPCBornY() {
        return -random.nextInt(Math.abs(MIN_BORN_ENEMY_SCREEN_HEIGHT_RANGE - MAX_BORN_ENEMY_SCREEN_HEIGHT_RANGE)) + (MAX_BORN_ENEMY_SCREEN_HEIGHT_RANGE);
    }

    public GameFrame(String locationPackage, String locationName, String playerPackage, String playerName, String npcPackage, String ... npcNames) {


        System.out.println("background path = " + BACKGROUND_PATH);
        System.out.println("player path = " + PLAYER_PATH);
        System.out.println("enemy path = " + ENEMY_PART_PATH);

        location = new Location(BACKGROUND_PATH);
        player = new Player(
                START_PLAYER_POS_X,
                START_PLAYER_POS_Y,
                1,
                PLAYER_SCALE,
                PLAYER_SCALE_WIDTH,
                PLAYER_PATH);
        bullets = new ArrayList<>();
        npcs = new ArrayList<>();
        for (int i = 0; i < npcNames.length; i++) {
            //String s = npcPackage.concat(npcNames[i].concat(EXTENSION));
            /*npcs.add(new NPC(
                0,//getNPCBornX(),
                -400,//getNPCBornY(),
                i,
                NPC_SCALE,
                PLAYER_PATH,
                "src/main/resources/routes/test_route"
            ));*/
            //npcs.add(new NPC(getNPCBornX(), getNPCBornY(), i, NPC_SCALE));
            npcs.add(new NPC(getNPCBornX(), getNPCBornY(), i, NPC_SCALE, NPC_SCALE_WIDTH, ENEMY_PART_PATH));
            npcs.add(new NPC(getNPCBornX(), getNPCBornY(), i, NPC_SCALE, NPC_SCALE_WIDTH, PLAYER_PATH));
        }
        npcs.get(0).addPointToRoute(new Point(-200f, -400f));
        npcs.get(0).addPointToRoute(new Point(300f, -450f));
        npcs.get(0).addPointToRoute(new Point(100f, -490f));

        npcs.get(0).setHealth(NPC_HEALTH);

        npcs.get(1).addPointToRoute(new Point(-100f, -470f));
        npcs.get(1).addPointToRoute(new Point(400f, -490f));

        npcs.get(1).setHealth(NPC_HEALTH);

        startTime = System.currentTimeMillis();

        System.out.println("END OF CONSTRUCTOR!");
    }

    @Override
    public void draw() {
        location.draw();
        for (NPC npc : npcs) {
            if (npc.isAlive()) {
                npc.draw();
            }
        }
        player.draw();
        for (Bullet b : bullets) {
            b.draw();
        }
    }

    private static int countOfTime(long start, long end) {
        return (int) (end - start);
    }

    @Override
    protected void changeState() {
        endTime = System.currentTimeMillis();

        if (isLooser(player, countOfTime(startTime, endTime))) {
            frameState = TO_LOOSE;
        }
        if (isWinner(player, countOfTime(startTime, endTime))) {
            frameState = TO_WIN;
        }
    }

    public void movePlayer(long win) {
        if (player.isAlive()) {
            if (player.isWalking()) {
                if (glfwGetKey(win, GLFW_KEY_W) == GL_TRUE || glfwGetKey(win, GLFW_KEY_UP) == GL_TRUE) {
                    player.move(0, PLAYER_SPEED_Y);
                } else if (glfwGetKey(win, GLFW_KEY_S) == GL_TRUE || glfwGetKey(win, GLFW_KEY_DOWN) == GL_TRUE) {
                    player.move(0, -PLAYER_SPEED_Y);
                }
            }

            if (glfwGetKey(win, GLFW_KEY_D) == GL_TRUE || glfwGetKey(win, GLFW_KEY_RIGHT) == GL_TRUE) {
                player.move(PLAYER_SPEED_X, 0);
            } else if (glfwGetKey(win, GLFW_KEY_A) == GL_TRUE || glfwGetKey(win, GLFW_KEY_LEFT) == GL_TRUE) {
                player.move(-PLAYER_SPEED_X, 0);
            }

            // Jump.
            if (player.isWalking() && glfwGetKey(win, GLFW_KEY_SPACE) == GL_TRUE) {
                player.jump();
            }

            // Attack.
            if (glfwGetKey(win, GLFW_KEY_Z) == GL_TRUE &&
                (System.currentTimeMillis() - shootLastTime) > SHOT_DELAY_MILLIS) {
                bullets.add(player.shoot());
                shootLastTime = System.currentTimeMillis();
            }

            //this.draw();

            PlayerJumpsProcessor.process(player);
        }
    }

    public void moveNPCs(long win) {
        for (NPC npc : npcs) {
            if (npc.isAlive()) {

                if (!npc.isInRoute(lastPlayerVisiblePoint) && NpcProcessor.isVisible(npc, player, VISIBLE_AREA_RADIUS, INVISIBLE_AREA_RADIUS)) {
                    Point playerCurrentPoint = player.getCoordinates();

                    process(npc);

                    Point current = playerCurrentPoint;

                    float xx = current.getX(), yy = current.getY();

                    lastPlayerVisiblePoint = new Point(xx, yy);
                    lastPlayerVisiblePoint.setPriority(current.getPriority());
                    lastPlayerVisiblePoint.setIsPlayer(current.getIsPlayer());

                    System.out.println("VISIBLE!");
                    npc.addVisiblePoint(lastPlayerVisiblePoint);
                } else {
                    System.out.println("INVISIBLE!!!");
                    npc.removeInvisiblePoint(lastPlayerVisiblePoint);
                }

                NpcProcessor.process(npc);
            }
        }
    }

    public void processObjects() {
        BulletsProcessor.process(bullets);
    }

    public void processDamage() {
        for (Bullet b : bullets) {
            for (NPC npc : npcs) {
                if (npc.isAlive() &&
                    b.getX() >= npc.getX() && b.getY() >= npc.getY()) {
                    npc.decHealth(PLAYER_DAMAGE);
                }
            }
        }
    }
}
