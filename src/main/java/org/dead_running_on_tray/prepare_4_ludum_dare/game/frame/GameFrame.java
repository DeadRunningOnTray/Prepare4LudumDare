package org.dead_running_on_tray.prepare_4_ludum_dare.game.frame;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.location.Location;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.Character;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.NPC;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.NpcRouteProcessor;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.Player;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.route.Point;

import java.util.ArrayList;
import java.util.Random;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.NPC_SCALE;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.PLAYER_SCALE;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.frame.frame_state.FrameState.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.logic.WinOrLose.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.NpcRouteProcessor.*;

public class GameFrame extends Frame {
    private Location location;
    private Player player;
    private ArrayList<NPC> npcs;

    private long startTime;
    private long endTime;

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
        return -random.nextInt(-MIN_BORN_ENEMY_SCREEN_HEIGHT_RANGE) + MAX_BORN_ENEMY_SCREEN_HEIGHT_RANGE;
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
                PLAYER_PATH);
        npcs = new ArrayList<>();
        for (int i = 0; i < npcNames.length; i++) {
            //String s = npcPackage.concat(npcNames[i].concat(EXTENSION));
            npcs.add(new NPC(getNPCBornX(), getNPCBornY(), i, NPC_SCALE, ENEMY_PART_PATH));
        }
        npcs.get(0).addPointToRoute(new Point(0f, 0f));
        npcs.get(0).addPointToRoute(new Point(0.5f, 0.3f));

        startTime = System.currentTimeMillis();

        System.out.println("END OF CONSTRUCTOR!");
    }

    @Override
    public void draw() {
        location.draw();
        for (NPC npc : npcs) {
            npc.draw();
        }
        player.draw();
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
        if (glfwGetKey(win, GLFW_KEY_W) == GL_TRUE || glfwGetKey(win, GLFW_KEY_UP) == GL_TRUE) {
            player.move(0, 0.5f);
        } else if (glfwGetKey(win, GLFW_KEY_S) == GL_TRUE || glfwGetKey(win, GLFW_KEY_DOWN) == GL_TRUE) {
            player.move(0, -0.5f);
        }
        if (glfwGetKey(win, GLFW_KEY_A) == GL_TRUE || glfwGetKey(win, GLFW_KEY_RIGHT) == GL_TRUE) {
            player.move(1f, 0);
        } else if (glfwGetKey(win, GLFW_KEY_D) == GL_TRUE || glfwGetKey(win, GLFW_KEY_LEFT) == GL_TRUE) {
            player.move(-1f, 0);
        }
    }

    /*
    * todo in NPC
    * */

    public boolean isVisible(NPC npc, Character character, float radius, float radius1) {
        Point npcPoint = npc.getCoordinates();
        Point characterPoint = character.getCoordinates();

        float npcHeight = npc.getUnit_height();
        float npcWidth = npc.getUnit_width();
        float characterHeight = character.getUnit_height();
        float characterWidth = character.getUnit_width();

        float x1 = npcPoint.getX() - npcWidth / 2, x2 = characterPoint.getX() - characterWidth / 2;
        float y1 = npcPoint.getY() - npcHeight / 2, y2 = character.getY() - characterHeight / 2;

        float dx = x1 - x2;
        float dy = y1 - y2;
        return dx * dx + dy * dy < radius * radius && dx * dx + dy * dy > radius1 * radius1;
    }

    /*
    * end of todo in NPC
    * */

    public void moveNPCs(long win) {
        for (NPC npc : npcs) {

            // todo
            // make method for visible area of NPC

            if (isVisible(npc, player, VISIBLE_AREA_RADIUS, INVISIBLE_AREA_RADIUS)) {
                Point playerCurrentPoint = player.getCoordinates();

                float xx = playerCurrentPoint.getX(), yy = playerCurrentPoint.getY();

                //lastPlayerVisiblePoint = new Point(xx, yy);

                npc.addPointToRoute(player.getCoordinates());
            } else {
                //npc.removePointFromRoute(player.getCoordinates());
            }

            process(npc);
        }
    }

}
