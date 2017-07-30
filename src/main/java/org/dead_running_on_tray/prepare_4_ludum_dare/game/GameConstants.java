package org.dead_running_on_tray.prepare_4_ludum_dare.game;

public interface GameConstants {
    String TITLE = "P4LD";

    int JUMP_DURATION_MILLIS = 1000;
    int PAUSE_DELAY_MILLIS = 250;

    int PAUSE_FOR_MOVING = 60;

    int SCREEN_WIDTH = 800;
    int SCREEN_HEIGHT = 600;
  
    ///int START_PLAYER_POS_X = -3 * SCREEN_WIDTH / 4;
   // int START_PLAYER_POS_Y = - SCREEN_WIDTH / 4;

    int PLAYER_PRIORITY = 2;

    int BACKGROUND_SCALE = 4;

    int BACKGROUND_X = - SCREEN_WIDTH;
    int BACKGROUND_Y = - 10 * SCREEN_HEIGHT / 4;

    int START_PLAYER_POS_X = - SCREEN_WIDTH / 2;//0;
    int START_PLAYER_POS_Y = 0;

    int MIN_BORN_ENEMY_SCREEN_HEIGHT_RANGE = -SCREEN_HEIGHT / 2;
    int MAX_BORN_ENEMY_SCREEN_HEIGHT_RANGE = 0;

    int LEFT_BORN_ENEMY_SCREEN_WIDTH = -SCREEN_WIDTH / 2;
    int RIGHT_BORN_ENEMY_SCREEN_WIDTH = SCREEN_WIDTH / 2;

    int PLAYER_DAMAGE = 42;
    int NPC_DAMAGE = 45;

    float VISIBLE_AREA_RADIUS = 300.0f;
    float INVISIBLE_AREA_RADIUS = 00.42f;

    int PLAYER_HEALTH = 2000;
    int NPC_HEALTH = 1000;

    String IMG_PACKAGE = "src/main/resources/img/";

    String EXTENSION = ".png";

    String BACKGROUND_PACKAGE = "background/";
    String BACKGROUND_NAME = "start_location";
    String BACKGROUND_PATH = IMG_PACKAGE + BACKGROUND_PACKAGE + BACKGROUND_NAME + EXTENSION;

    String ENEMIES_PACKAGE = "enemies/";
    String ENEMY_NAME = "enemy0";
    String ENEMY_PART_PATH = IMG_PACKAGE + ENEMIES_PACKAGE + ENEMY_NAME + EXTENSION;

    String PLAYER_PACKAGE = "player/";
    String PLAYER_NAME = "frame_1_sqr";
    String PLAYER_PATH = IMG_PACKAGE + PLAYER_PACKAGE + PLAYER_NAME + EXTENSION;

    String LOCATION_PACKAGE = "location/";
    String LOCATION_NAME = "start_location";
    String LOCATION_PATH = IMG_PACKAGE + LOCATION_PACKAGE + LOCATION_NAME + EXTENSION;

}
