package org.dead_running_on_tray.prepare_4_ludum_dare.game;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.location.ILocation;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.*;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.Character;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.texture.Texture;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLContext;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Main game class.
 */
class Game {


    private static long win;
    private static State state = State.GAME;// Default state.

    private static ILocation.Option currentLocation = ILocation.Option.START;
    private static HashMap<ILocation.Option, ILocation> locationsMap;

    private static Player player;
    private static ArrayList<NPC> NPCs;

    private static Texture pauseSign;


    Game() {
        init();

        while (glfwWindowShouldClose(win) != GL_TRUE) {
            gameLoop();
        }

        glfwTerminate();
        System.exit(0);
    }


    /**
     * Initialize default state.
     */
    private void init() {
        if (glfwInit() != GL_TRUE) {
            throw new IllegalStateException("GLFW failed to initialize!");
        }

        win = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, TITLE, 0, 0);
        glfwShowWindow(win);
        glfwMakeContextCurrent(win);

        GLContext.createFromCurrent();
        GL.createCapabilities(true);
        glEnable(GL_TEXTURE_2D);


        // TODO this block.
        // Initialize pause sign sprite.
        // pauseSign = new Texture("src/main/resources/img/pause/pause_sign.png");

        // Initializing objects.
        player = new Player(
            START_PLAYER_POS_X,
            START_PLAYER_POS_Y,
            1,
            "src/main/resources/img/player/frame_1_sqr.png"
        );

        NPCs = new ArrayList<>();
        NPCs.add(new NPC(
            0,
            0,
            2,
            "src/main/resources/img/player/frame_1_sqr.png",
            "src/main/resources/routes/test_route"
        ));
    }


    private void gameLoop() {
        glfwPollEvents();
        processInput();
        glClear(GL_COLOR_BUFFER_BIT);

        switch (state) {
            case MAIN_MENU: {
                drawMainMenu();
                break;
            }

            case PAUSE: {
                drawScene();
                drawPauseSign();
                break;
            }

            case GAME: {
                processAI();
                drawScene();
            }
        }

        glfwSwapBuffers(win);
    }


    /**
     * Process user buttons pressing.
     */
    private void processInput() {
        switch (state) {
            case MAIN_MENU: {
                break;
            }

            case PAUSE: {
                if (glfwGetKey(win, GLFW_KEY_ESCAPE) == GL_TRUE) {
                    state = State.GAME;
                    System.err.println("Pause is unset.");
                }
                break;
            }

            case GAME: {
                if (glfwGetKey(win, GLFW_KEY_W) == GL_TRUE || glfwGetKey(win, GLFW_KEY_UP) == GL_TRUE) {
                    player.move(0, 0.5f);
                } else if (glfwGetKey(win, GLFW_KEY_S) == GL_TRUE || glfwGetKey(win, GLFW_KEY_DOWN) == GL_TRUE) {
                    player.move(0, -0.5f);
                }

                if (glfwGetKey(win, GLFW_KEY_D) == GL_TRUE || glfwGetKey(win, GLFW_KEY_RIGHT) == GL_TRUE) {
                    player.move(1, 0);
                } else if (glfwGetKey(win, GLFW_KEY_A) == GL_TRUE || glfwGetKey(win, GLFW_KEY_LEFT) == GL_TRUE) {
                    player.move(-1, 0);
                }

                if (glfwGetKey(win, GLFW_KEY_ESCAPE) == GL_TRUE) {
                    state = State.PAUSE;
                    System.err.println("Pause is set.");
                }
            }
        }
    }

    /**
     * Move all NPC according to their inner route.
     */
    private void processAI() {
        for (NPC npc : NPCs) {
            NpcRouteProcessor.process(npc);
        }
    }


    /**
     * Draw main menu buttons and decorations.
     */
    private void drawMainMenu() {
        //
    }

    /**
     * Draw every game object im memory.
     */
    private void drawScene() {
        for (Character npc : NPCs) {
            npc.draw();
        }

        player.draw();
    }

    /**
     * Draw pause sign.
     */
    private void drawPauseSign() {

    }
}
