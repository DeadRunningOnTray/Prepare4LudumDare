package org.dead_running_on_tray.prepare_4_ludum_dare.game.frame;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.location.Location;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.NPC;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.Player;

import java.util.ArrayList;
import java.util.Random;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.NPC_SCALE;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.PLAYER_SCALE;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.frame.frame_state.FrameState.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.logic.WinOrLose.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class GameFrame extends Frame {
    private Location location;
    private Player player;
    private ArrayList<NPC> npcs;

    private long startTime;
    private long endTime;

    //private ILocation.Option currentLocation = ILocation.Option.START;
    //private HashMap<ILocation.Option, ILocation> locationsMap;

    private Random random = new Random(42/*System.currentTimeMillis()*/);

    private int getNPCBornX() {
        return (random.nextBoolean() ? LEFT_BORN_ENEMY_SCREEN_WIDTH : RIGHT_BORN_ENEMY_SCREEN_WIDTH);
    }

    private int getNPCBornY() {
        return -random.nextInt(-MIN_BORN_ENEMY_SCREEN_HEIGHT_RANGE) + MAX_BORN_ENEMY_SCREEN_HEIGHT_RANGE;
    }

    public GameFrame(String locationPackage, String locationName, String playerPackage, String playerName, String npcPackage, String ... npcNames) {

        System.out.println("background path = " + BACKGROUND_PATH);
        System.out.println("player path = " + PLAYER_PATH);
        System.out.println("enemy path = " + ENEMY_PART_PATH);

        location = new Location(/*locationPackage.concat(locationName.concat(EXTENSION))*/PLAYER_PATH) {
        }/*new StartLocation()*/;
        player = new Player(
                START_PLAYER_POS_X,
                START_PLAYER_POS_Y,
                1,
                PLAYER_SCALE,
                /*playerPackage + playerName + EXTENSION*/PLAYER_PATH);
        npcs = new ArrayList<>();
        for (int i = 0; i < npcNames.length; i++) {
            //String s = npcPackage.concat(npcNames[i].concat(EXTENSION));
            npcs.add(new NPC(getNPCBornX(), getNPCBornY(), i, NPC_SCALE, /*npcPackage.concat(npcNames[i].concat(EXTENSION)))*/PLAYER_PATH));
        }

        // draw it!
        this.draw();
        startTime = System.currentTimeMillis();

        System.out.println("END OF CONSTRUCTOR!");
    }

    @Override
    public void draw() {
        location.draw();
        player.draw();
        System.out.println("DRAW PLAYER!!!");
        for (NPC npc : npcs) {
            npc.draw();
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
        if (glfwGetKey(win, GLFW_KEY_W) == GL_TRUE || glfwGetKey(win, GLFW_KEY_UP) == GL_TRUE) {
            player.move(0, 0.5f);
        } else if (glfwGetKey(win, GLFW_KEY_S) == GL_TRUE || glfwGetKey(win, GLFW_KEY_DOWN) == GL_TRUE) {
            player.move(0, -0.5f);
        }
    }

}
