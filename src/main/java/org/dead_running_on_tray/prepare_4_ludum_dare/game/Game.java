package org.dead_running_on_tray.prepare_4_ludum_dare.game;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.START_PLAYER_POS_X;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.START_PLAYER_POS_Y;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.location.ILocation;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.Player;
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
    private static ArrayList<Character> NPCs;


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

        win = glfwCreateWindow(800, 600, "win", 0, 0);
        glfwShowWindow(win);
        glfwMakeContextCurrent(win);

        GLContext.createFromCurrent();
        GL.createCapabilities(true);

        //Initializing objects.
        player = new Player(
            START_PLAYER_POS_X,
            START_PLAYER_POS_Y,
            1,
            "src/main/resources/img/player/frame_1.gif"
        );
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
                moveCharacters();
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
                if (glfwGetKey(win, GLFW_KEY_ESCAPE) == GL_TRUE) {
                    state = State.PAUSE;
                    System.err.println("Pause is set.");
                }
            }
        }
    }

    /**
     * Move all
     */
    private void moveCharacters() {

    }


    /**
     * Draw main menu buttons and decorations.
     */
    private void drawMainMenu() {
        //
    }

    /**
     *
     */
    private void drawScene() {

    }

    /**
     *
     */
    private void drawPauseSign() {

    }
}
