package com.core.base.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Tamir Eliraz
 * @see com.badlogic.gdx.scenes.scene2d.Actor
 */
public abstract class BaseActor extends Actor {
    private BaseAnimation animation;
    
    public BaseActor() { this(0, 0); }
    
    public BaseActor(float x, float y) {
        super();
        setPosition(x, y);
        animation = null;
    }
    
    public BaseActor(@NotNull Vector2 position) { this(position.x, position.y); }
    
    public BaseActor(@NotNull Vector2 position, Stage stage) { this(position.x, position.y, stage); }
    
    public BaseActor(float x, float y, Group group) {
        this(x, y);
        addInto(group);
    }
    
    public BaseActor(float x, float y, Stage stage) {
        this(x, y);
        addInto(stage);
    }
    
    @Override
    public void act(float dt) {
        super.act(dt);
        if (hasAnimation() && isVisible()) animation.act(dt);
        update(dt);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (hasAnimation() && isVisible()) animation.draw(batch);
    }
    
    public boolean hasAnimation() { return animation != null; }
    
    public @Nullable BaseAnimation getAnimation() { return animation; }
    
    public void startAnimation() { animation.startAnimation(); }
    
    public void stopAnimation() { animation.stopAnimation(); }
    
    public BaseActor addInto(@NotNull Stage stage) {
        try { stage.addActor(this); } catch (NullPointerException ignored) { } return this;
    }
    
    public BaseActor addInto(@NotNull Group group) {
        try { group.addActor(this); } catch (NullPointerException ignored) { } return this;
    }
    
    private BaseActor setAnimation(String filename, int rows, int framesPerRow) {
        this.animation = new BaseAnimation(this, filename, rows, framesPerRow);
        return this;
    }
    
    protected BaseActor setAnimation(@NotNull BaseAnimation animation) {
        this.animation = animation.init();
        return this;
    }
    
    public void dispose() { animation.dispose(); }
    
    protected abstract void update(float dt);
}
