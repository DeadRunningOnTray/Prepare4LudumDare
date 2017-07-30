package org.dead_running_on_tray.prepare_4_ludum_dare.game.objects;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.SCREEN_HEIGHT;

public interface GameObjectsConstants {
    int MAX_HEALTH = 100;

    float PLAYER_SPEED_X = 2.0f;
    float PLAYER_SPEED_Y = 1.0f;

    float NPC_SPEED_X = 1.0f;
    float NPC_SPEED_Y = 1.0f;

    float BULLET_SPEED = 5.50f;

    float JUMP_Y_INCREMENT = 5;
    int JUMP_DURATION_MILLIS = 750;
}
