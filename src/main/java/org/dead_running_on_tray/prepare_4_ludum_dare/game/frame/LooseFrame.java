package org.dead_running_on_tray.prepare_4_ludum_dare.game.frame;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.objects.backgrounds.Background;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.*;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.GameConstants.EXTENSION;
import static org.dead_running_on_tray.prepare_4_ludum_dare.game.scale.Scale.BACKGROUND_SCALE;

public class LooseFrame extends Frame {
    private Background background = new Background(BACKGROUND_X, BACKGROUND_Y, 71, BACKGROUND_SCALE, LOSE_PATH);


    @Override
    public void draw() {
        background.draw();
    }

    @Override
    protected void changeState() {

    }
}
