package com.core.base.actor;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.core.base.enums.Direction;
import com.core.handler.AssetManagerHandler;
import com.core.handler.InputProcessorAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

/**
 * @author Tamir Eliraz
 * @see BaseActor
 * @see com.badlogic.gdx.scenes.scene2d.Actor
 */
public class BaseActor4D extends BaseActor implements InputProcessorAdapter {
    private HashMap<Direction, BaseAnimation> animationMap;
    private Direction currentDirection;
    
    public BaseActor4D(float x, float y, Stage stage, InputMultiplexer inputMultiplexer, float frameDuration,
                       String fileName, int cols,
                       Direction @NotNull ... directions) {
        super(x, y, stage);
        Texture texture = AssetManagerHandler.getInstance()
                .getTexture(fileName, Linear);
        animationMap = new HashMap<>() {{
            TextureRegion[][] map = TextureRegion.split(texture,
                    texture.getWidth() / cols, texture.getHeight() / 4);
            for (int dir = 0; dir < directions.length; dir++) {
                put(directions[dir], new BaseAnimation(BaseActor4D.this, map[dir]));
            }
        }};
        currentDirection = Direction.RIGHT;
        setAnimation(animationMap.get(currentDirection));
        if (inputMultiplexer != null) inputMultiplexer.addProcessor(this);
    }
    
    @Override
    public boolean keyDown(int keycode) {
        InputProcessorAdapter.super.keyDown(keycode);
        try {
            Direction temp = Direction.getByKey(keycode);
            setAnimation(animationMap.get(temp));
            currentDirection = temp;
            startAnimation();
        } catch (NullPointerException ignored) { return false; }
        return false;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        stopAnimation();
        return false;
    }
    
    @Override
    protected void update(float dt) {
        int N = 100;
        if (hasAnimation() && !getAnimation().isAnimationPaused()) {
            switch (currentDirection) {
                case RIGHT -> setX(getX() + N * dt);
                case LEFT -> setX(getX() - N * dt);
                case UP -> setY(getY() + N * dt);
                case DOWN -> setY(getY() - N * dt);
            }
        }
    }
}
