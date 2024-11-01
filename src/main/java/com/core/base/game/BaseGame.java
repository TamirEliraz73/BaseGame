package com.core.base.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.core.base.actor.BaseActor;
import com.core.base.group.BaseGroup;

/**
 * @author Tamir Eliraz
 * @see com.badlogic.gdx.Game
 */
public class BaseGame extends Game {
    private BaseScreen screen;
    
    public BaseGame addActor(BaseActor actor) { screen.addActor(actor); return this; }
    
    public BaseGame addActor(BaseGroup group) { screen.addActor(group); return this; }
    
    public Stage getStage() { return screen.getStage(); }
    
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
