package com.core.base.group;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.core.base.actor.BaseActor4D;
import com.core.base.enums.Direction;
import com.core.handler.InputProcessorAdapter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public abstract class BaseGroup extends Group implements InputProcessorAdapter {
    private HashMap<? extends BaseBodyPart, BaseActor4D> bodyParts;
    
    public BaseGroup(float x, float y, Stage stage) {
        this(x, y, stage, null, false);
    }
    
    public BaseGroup(float x, float y, Stage stage, InputMultiplexer inputMultiplexer) {
        this(x, y, stage, inputMultiplexer, false);
    }
    
    public BaseGroup(float x, float y, Stage stage, InputMultiplexer inputMultiplexer, boolean autoMotion) {
        bodyParts = new HashMap<>() {{
            for (BaseBodyPart bodyPart : BaseGroup.this.values()) {
                BaseActor4D body = addBodyPart(x, y, bodyPart.fileName(), inputMultiplexer);
                put(bodyPart, body);
                addActor(body);
                body.setVisible(bodyPart.getDefaultVisibility());
                if (autoMotion) body.startAnimation();
            }
        }};
        if (stage != null) stage.addActor(this);
        if (inputMultiplexer != null) inputMultiplexer.addProcessor(this);
    }
    
    @Contract("_, _, _, _ -> new")
    private @NotNull BaseActor4D addBodyPart(float x, float y, String filename, InputMultiplexer inputMultiplexer) {
        return new BaseActor4D(x, y, null, inputMultiplexer, 0.1f, filename, 9,
                Direction.UP, Direction.LEFT,
                Direction.DOWN, Direction.RIGHT);
    }
    
    protected abstract BaseBodyPart[] values();
    
    protected <BodyPart extends BaseBodyPart> void switchBodyPartVisibility(BodyPart bodyPart) {
        bodyParts.get(bodyPart).setVisible(!bodyParts.get(bodyPart).isVisible());
    }
}