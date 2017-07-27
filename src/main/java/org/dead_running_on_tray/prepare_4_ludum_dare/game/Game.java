package org.dead_running_on_tray.prepare_4_ludum_dare.game;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLContext;

/**
 * Main game class.
 */
public class Game {

    private static long window;
    private static State state = State.MAIN_MENU;

    public Game() {
        init();

        while (glfwWindowShouldClose(window) != GL_TRUE) {
            loop();
        }

        glfwTerminate();
        System.out.println("...");
        System.out.println("Finished.");
    }

    private void init() {
        if (glfwInit() != GL_TRUE) {
            throw new IllegalStateException("Failed to initialize GLFW!");
        }

        window = glfwCreateWindow(800, 600, "window", 0, 0);
        glfwShowWindow(window);
        glfwMakeContextCurrent(window);

        GLContext.createFromCurrent();
        GL.createCapabilities(true);
    }

    private void loop() {
        switch (state) {
            case MAIN_MENU: {
                return;
            }

            case GAME: {
                glfwPollEvents();

                glClear(GL_COLOR_BUFFER_BIT);

                glBegin(GL_QUADS); {
                    glColor4f(255, 255, 255, 0);
                    glVertex2f(-0.5f, 0.5f);
                    glVertex2f(0.5f, 0.5f);
                    glVertex2f(0.5f, -0.5f);
                    glVertex2f(-0.5f, -0.5f);
                } glEnd();

                glfwSwapBuffers(window);
            }
        }
    }
}
