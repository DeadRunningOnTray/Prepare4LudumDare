package org.dead_running_on_tray.prepare_4_ludum_dare.game;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.shared_loader.SharedLibraryLoader;

/**
 * Entry point.
 */
class Main {
    public static void main(String[] args) {
        SharedLibraryLoader.load(false);
        new Game();
    }
}
