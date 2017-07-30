package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

public class PlayerJumpsProcessor {
    private static long lastTimeJump = -1;

    public static void process(Player p) {
        if (lastTimeJump == -1 && p.isInJump()) {
            lastTimeJump = System.currentTimeMillis();
            p.move(0, 1);

            System.err.println("Jumped");
        } /*else if (p.isInJump() && (System.currentTimeMillis() - lastTimeJump) > JUMP_DURATION) {

        }*/
    }
}
