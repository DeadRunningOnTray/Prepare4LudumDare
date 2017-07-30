package org.dead_running_on_tray.prepare_4_ludum_dare.game.frame;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.backgrounds.Background;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.EXTENSION;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.INSTRUCTION;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.BACKGROUND_SCALE;

public class StartFrame extends Frame {


    private Background background; //= new Background(BACKGROUND_X, BACKGROUND_Y, 7, INSTRUCTION_SCALE, FRAMES_PACKAGE + START);

    public StartFrame() {

        System.out.println(START_PATH);

        background = new Background(BACKGROUND_X, BACKGROUND_Y, 72, BACKGROUND_SCALE, START_PATH);

        //System.exit(0);
    }

    @Override
    public void draw() {
        System.out.println(FRAMES_PACKAGE + START);
        background.draw();
    }

    @Override
    protected void changeState() {

    }
}
