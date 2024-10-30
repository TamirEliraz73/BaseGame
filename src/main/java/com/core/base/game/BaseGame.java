package com.core.base.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class BaseGame extends Game {
    private BaseScreen screen;
    
    @Override
    public void create() {
        screen = new BaseScreen(700, 800);
        setScreen(screen);
    }
    
    @Override
    public void dispose() {
        super.dispose();
        screen.dispose();
        Gdx.app.exit();
    }
}
