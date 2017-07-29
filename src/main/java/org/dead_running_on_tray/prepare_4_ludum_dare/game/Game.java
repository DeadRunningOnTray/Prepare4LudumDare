package org.dead_running_on_tray.prepare_4_ludum_dare.game;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.State.*;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.frame.*;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.frame.frame_state.FrameState;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.location.ILocation;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.Character;
import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.Player;
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
    private static State state = START_FRAME/*GAME*/;// Default state.

    private static ILocation.Option currentLocation = ILocation.Option.START;
    private static HashMap<ILocation.Option, ILocation> locationsMap;

    private static Player player;
    private static ArrayList<Character> NPCs;

    private static Texture pauseSign;



    private Frame frame = new StartFrame();



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
        /*player = new Player(
            START_PLAYER_POS_X,
            START_PLAYER_POS_Y,
            1,
            PLAYER_SCALE,
            PLAYER_PATH
        );

        NPCs = new ArrayList<>();*/
    }

    private void gameLoop() {
        glfwPollEvents();
        processInput();
        glClear(GL_COLOR_BUFFER_BIT);

        FrameState frameState = frame.getFrameState();

        switch (state) {
            case START_FRAME: {
                switch (frameState) {
                    case TO_GAME: {
                        frame = new GameFrame(BACKGROUND_PACKAGE, BACKGROUND_NAME, PLAYER_PACKAGE, PLAYER_NAME, ENEMIES_PACKAGE, ENEMY_NAME);
                        state = GAME;
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case GAME: {
                switch (frameState) {
                    case TO_LOOSE: {
                        frame = new LooseFrame();
                        state = LOOSE_FRAME;
                        break;
                    }
                    case TO_WIN: {
                        frame = new WinFrame();
                        state = WIN_FRAME;
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case LOOSE_FRAME: {
                switch (frameState) {
                    case TO_START: {
                        frame = new StartFrame();
                        state = START_FRAME;
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case WIN_FRAME: {
                switch (frameState) {
                    case TO_START: {
                        frame = new StartFrame();
                        state = START_FRAME;
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            default: {
                break;
            }
        }

        /*switch (state) {
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
        }*/

        glfwSwapBuffers(win);
    }


    /**
     * Process user buttons pressing.
     */
    private void processInput() {

        switch (state) {
            case START_FRAME: {
                if (glfwGetKey(win, GLFW_KEY_ENTER) == GL_TRUE) {
                    frame.setFrameState(FrameState.TO_GAME);
                    System.out.println("FROM START TO GAME!!!!");
                    //System.err.println("Pause is unset.");
                }
                break;
            }
            case WIN_FRAME:
            case LOOSE_FRAME: {
                if (glfwGetKey(win, GLFW_KEY_ENTER) == GL_TRUE) {
                    frame.setFrameState(FrameState.TO_LOOSE);
                    System.out.println("FROM WIN/LOOSE TO START!!!!");
                    //System.err.println("Pause is unset.");
                }
                break;
            }

            case MAIN_MENU: {
                break;
            }

            case PAUSE: {
                if (glfwGetKey(win, GLFW_KEY_ESCAPE) == GL_TRUE) {
                    state = GAME;
                    System.err.println("Pause is unset.");
                }
                break;
            }

            case GAME: {
                ((GameFrame) frame).movePlayer(win);
                /*if (glfwGetKey(win, GLFW_KEY_W) == GL_TRUE || glfwGetKey(win, GLFW_KEY_UP) == GL_TRUE) {
                    player.move(0, 0.5f);
                } else if (glfwGetKey(win, GLFW_KEY_S) == GL_TRUE || glfwGetKey(win, GLFW_KEY_DOWN) == GL_TRUE) {
                    player.move(0, -0.5f);
                }*/

                /*
                if (glfwGetKey(win, GLFW_KEY_W) == GL_TRUE || glfwGetKey(win, GLFW_KEY_UP) == GL_TRUE) {
                    player.move(1, 0);
                } else if (glfwGetKey(win, GLFW_KEY_S) == GL_TRUE || glfwGetKey(win, GLFW_KEY_DOWN) == GL_TRUE) {
                    player.move(-1, 0);
                }
                */

                if (glfwGetKey(win, GLFW_KEY_ESCAPE) == GL_TRUE) {
                    state = State.PAUSE;
                    System.err.println("Pause is set.");
                }

                break;
            }
        }
    }

    /**
     * Move all NPC according to their inner algorithm.
     */
    private void processAI() {

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
