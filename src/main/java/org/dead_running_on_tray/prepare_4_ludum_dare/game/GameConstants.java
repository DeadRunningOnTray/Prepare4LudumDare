package org.dead_running_on_tray.prepare_4_ludum_dare.game;

public interface GameConstants {
    String TITLE = "P4LD";

    int PAUSE_DELAY_MILLIS = 250;

    int PAUSE_FOR_MOVING = 60;
    int SHOT_DELAY_MILLIS = 250;

    int SCREEN_WIDTH = 800;
    int SCREEN_HEIGHT = 500;


    ///int START_PLAYER_POS_X = -3 * SCREEN_WIDTH / 4;
   // int START_PLAYER_POS_Y = - SCREEN_WIDTH / 4;


    int BACKGROUND_X = - SCREEN_WIDTH;
    int BACKGROUND_Y = - 2 * SCREEN_HEIGHT;

    int START_PLAYER_POS_X = -800;//0;
    int START_PLAYER_POS_Y = -500;

    int MIN_BORN_ENEMY_SCREEN_HEIGHT_RANGE = START_PLAYER_POS_Y;
    int MAX_BORN_ENEMY_SCREEN_HEIGHT_RANGE = MIN_BORN_ENEMY_SCREEN_HEIGHT_RANGE + 30;

    int LEFT_BORN_ENEMY_SCREEN_WIDTH = -SCREEN_WIDTH / 2;
    int RIGHT_BORN_ENEMY_SCREEN_WIDTH = SCREEN_WIDTH / 2;

    int PLAYER_DAMAGE = 42;
    int NPC_DAMAGE = 45;

    float VISIBLE_AREA_RADIUS = 600.0f;
    float INVISIBLE_AREA_RADIUS = 0.0f;

    int PLAYER_HEALTH = 2000;
    int NPC_HEALTH = 1000;


    float LOW_BORDER = -500f;
    float HIGH_BORDER = -300f;

    String IMG_PACKAGE = "src/main/resources/img/";

    String EXTENSION = ".png";

    String BACKGROUND_PACKAGE = "background/";
    String BACKGROUND_NAME = "start_location_laptop";
    String BACKGROUND_PATH = IMG_PACKAGE + BACKGROUND_PACKAGE + BACKGROUND_NAME + EXTENSION;

    String FRAMES_PACKAGE = IMG_PACKAGE + "frames/";

    String INSTRUCTION = "instruction1";
    String START = "startscreen1";
    String LOSE = "lose1";
    String WIN = "win1";

    String START_PATH = IMG_PACKAGE + BACKGROUND_PACKAGE + START + EXTENSION;
    String INSTRUCTION_PATH = IMG_PACKAGE + BACKGROUND_PACKAGE + INSTRUCTION + EXTENSION;
    String LOSE_PATH = IMG_PACKAGE + BACKGROUND_PACKAGE + LOSE + EXTENSION;
    String WIN_PATH = IMG_PACKAGE + BACKGROUND_PACKAGE + WIN + EXTENSION;

    int INSTRUCTION_SCALE = 1;
    int START_SCALE = 1;
    int LOSE_SCALE = 1;
    int WIN_SCALE = 1;

    String ENEMIES_PACKAGE = "enemies/";
    String ENEMY_NAME = "enemy2";
    String ENEMY_PART_PATH = IMG_PACKAGE + ENEMIES_PACKAGE + ENEMY_NAME + EXTENSION;

    String PLAYER_PACKAGE = "player/";
    String PLAYER_NAME = "frame1";
    String PLAYER_PATH = IMG_PACKAGE + PLAYER_PACKAGE + PLAYER_NAME + EXTENSION;

    String LOCATION_PACKAGE = "location/";
    String LOCATION_NAME = "start_location_laptop";
    String LOCATION_PATH = IMG_PACKAGE + LOCATION_PACKAGE + LOCATION_NAME + EXTENSION;

    String OTHER_OBJECTS_PACKAGE = "objects/";

    String BULLET_NAME = "bullet";
    String BULLET_PATH = IMG_PACKAGE + OTHER_OBJECTS_PACKAGE + BULLET_NAME + EXTENSION;
}
