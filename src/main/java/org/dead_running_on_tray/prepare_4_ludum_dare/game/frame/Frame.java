package org.dead_running_on_tray.prepare_4_ludum_dare.game.frame;

import org.dead_running_on_tray.prepare_4_ludum_dare.game.frame.frame_state.FrameState;

import static org.dead_running_on_tray.prepare_4_ludum_dare.game.frame.frame_state.FrameState.LIVE;

public abstract class Frame {
    protected FrameState frameState = LIVE;

    public abstract void draw();

    protected abstract void changeState();

    public FrameState getFrameState() {
        return frameState;
    }

    public void setFrameState(FrameState frameState) {
        this.frameState = frameState;
    }
}
