package com.core.base.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.core.handler.AssetManagerHandler;
import org.jetbrains.annotations.NotNull;

/**
 * @author Tamir Eliraz
 * @see com.badlogic.gdx.graphics.g2d.Animation
 * @see com.badlogic.gdx.graphics.g2d.TextureRegion
 */
public class BaseAnimation extends Animation<TextureRegion> {
    /**
     * The {@link BaseActor} associated with this animation.
     * This actor is used to display the animated frames on the screen.
     */
    private BaseActor actor;
    private float elapsedTime;
    private boolean animationPaused;
    /**
     * The default duration for each frame in seconds if not specified.
     * This value is used when creating animations that do not provide a custom frame duration.
     */
    public static final float DEFAULT_FRAME_DURATION = 0.1f;
    
    /**
     * <p></p>Constructs a {@code BaseAnimation} for a specified {@link BaseActor} using a sprite sheet.
     * Divides the image into frames based on the specified number of rows and columns,
     * creating a sequence of {@link TextureRegion}s for animation.</p>
     *
     * <p>The method reads each row of frames from left to right, starting from the top row,
     * and applies a looping or non-looping animation based on the {@code loop} parameter.</p>
     *
     * <p>See the sprite sheet layout:</p>
     * <pre>
     * +---+---+---+---+
     * | 1 | 2 | 3 | 4 |  <-- Row 1 (Top row, frames loaded left to right)
     * +---+---+---+---+
     * | 5 | 6 | 7 | 8 |  <-- Row 2
     * +---+---+---+---+
     * | 9 |10 |11 |12 |  <-- Row 3
     * +---+---+---+---+
     * </pre>
     *
     * @param actor
     *         the {@link BaseActor} for which this animation is applied
     * @param fileName
     *         the file name of the sprite sheet to load
     * @param rows
     *         the number of rows in the sprite sheet
     * @param framesPerRow
     *         the number of columns in the sprite sheet
     * @param frameDuration
     *         the duration of each frame in seconds
     * @param loop
     *         whether the animation should loop
     */
    public BaseAnimation(@NotNull BaseActor actor, String fileName,
                         int rows, int framesPerRow,
                         float frameDuration, boolean loop) {
        super(frameDuration, new Array<>() {{
            Texture texture = AssetManagerHandler.getInstance().getTexture(fileName, Texture.TextureFilter.Linear);
            for (TextureRegion[] row : TextureRegion.split(texture,
                    texture.getWidth() / framesPerRow,
                    texture.getHeight() / rows))
                for (TextureRegion frame : row)
                    add(frame);
        }});
        this.actor = actor;
        if (loop) setLoop();
        else setNormal();
        init();
    }
    
    /**
     * Constructs a {@code BaseAnimation} for a specified {@link BaseActor} using a sprite sheet.
     * Divides the image into frames based on the specified number of rows and columns,
     * creating a sequence of {@link TextureRegion}s for animation.
     *
     * <p>The method reads each row of frames from left to right, starting from the top row,
     * and applies a looping animation based on the {@code loop} parameter.</p>
     *
     * <p>The default duration for each frame is set to {@link BaseAnimation#DEFAULT_FRAME_DURATION} (0.1 seconds) if
     * not specified.</p>
     *
     * <p>See the sprite sheet layout:</p>
     * <pre>
     *  +---+---+---+---+
     *  | 1 | 2 | 3 | 4 |  <-- Row 1 (Top row, frames loaded left to right)
     *  +---+---+---+---+
     *  | 5 | 6 | 7 | 8 |  <-- Row 2
     *  +---+---+---+---+
     *  | 9 |10 |11 |12 |  <-- Row 3
     *  +---+---+---+---+
     *  </pre>
     *
     * @param actor
     *         the {@link BaseActor} for which this animation is applied
     * @param fileName
     *         the file name of the sprite sheet to load
     * @param rows
     *         the number of rows in the sprite sheet
     * @param framesPerRow
     *         the number of columns in the sprite sheet
     * @param loop
     *         whether the animation should loop
     */
    public BaseAnimation(@NotNull BaseActor actor, String fileName,
                         int rows, int framesPerRow, boolean loop) {
        this(actor, fileName, rows, framesPerRow, DEFAULT_FRAME_DURATION, loop);
    }
    
    /**
     * Constructs a {@code BaseAnimation} for a specified {@link BaseActor} using a sprite sheet.
     * Divides the image into frames based on the specified number of rows and columns,
     * creating a sequence of {@link TextureRegion}s for animation.
     *
     * <p>The method reads each row of frames from left to right, starting from the top row,
     * and applies a looping animation based on the {@code loop} parameter. By default,
     * the animation will loop indefinitely.</p>
     *
     * <p>See the sprite sheet layout:</p>
     * <pre>
     * +---+---+---+---+
     * | 1 | 2 | 3 | 4 |  <-- Row 1 (Top row, frames loaded left to right)
     * +---+---+---+---+
     * | 5 | 6 | 7 | 8 |  <-- Row 2
     * +---+---+---+---+
     * | 9 |10 |11 |12 |  <-- Row 3
     * +---+---+---+---+
     * </pre>
     *
     * @param actor
     *         the {@link BaseActor} for which this animation is applied
     * @param fileName
     *         the file name of the sprite sheet to load
     * @param rows
     *         the number of rows in the sprite sheet
     * @param framesPerRow
     *         the number of columns in the sprite sheet
     */
    public BaseAnimation(@NotNull BaseActor actor, String fileName,
                         int rows, int framesPerRow) {
        this(actor, fileName, rows, framesPerRow, DEFAULT_FRAME_DURATION, true);
    }
    
    /**
     * Constructs a {@code BaseAnimation} for a specified {@link BaseActor} using the provided frames.
     * This constructor allows you to specify the duration of each frame and pass a variable number of
     * {@link TextureRegion} frames for the animation.
     *
     * <p>Each frame will be displayed in the order they are provided. The animation will play
     * sequentially through the frames.</p>
     *
     * @param actor
     *         the {@link BaseActor} for which this animation is applied
     * @param frameDuration
     *         the duration for which each frame is displayed
     * @param frames
     *         variable number of {@link TextureRegion} frames to be used in the animation
     */
    public BaseAnimation(@NotNull BaseActor actor, float frameDuration, TextureRegion @NotNull ... frames) {
        super(frameDuration, new Array<>() {{
            for (TextureRegion frame : frames)
                add(frame);
        }});
        this.actor = actor;
        setLoop();
        init();
    }
    
    
    /**
     * Constructs a {@code BaseAnimation} for a specified {@link BaseActor} using the provided frames.
     * This constructor allows you to pass a variable number of {@link TextureRegion} frames for the animation.
     * The default frame duration is set to {@link BaseAnimation#DEFAULT_FRAME_DURATION} (0.1 seconds).
     *
     * <p>Each frame will be displayed in the order they are provided. The animation will play
     * sequentially through the frames.</p>
     *
     * @param actor
     *         the {@link BaseActor} for which this animation is applied
     * @param frames
     *         variable number of {@link TextureRegion} frames to be used in the animation
     */
    public BaseAnimation(@NotNull BaseActor actor, TextureRegion... frames) {
        this(actor, DEFAULT_FRAME_DURATION, frames);
    }
    
    public boolean isAnimationPaused() {
        return animationPaused;
    }
    
    public void act(float dt) {
        if (!isAnimationPaused()) elapsedTime += dt;
    }
    
    public void draw(@NotNull Batch batch) {
        Color color = actor.getColor();
        batch.setColor(color.r, color.g, color.b, color.a);
        batch.draw(getKeyFrame(elapsedTime),
                actor.getX(), actor.getY(), actor.getOriginX(), actor.getOriginY(),
                actor.getWidth(), actor.getHeight(), actor.getScaleX(), actor.getScaleY(), actor.getRotation());
    }
    
    public BaseAnimation resetAnimation() { elapsedTime = 0; return this; }
    
    public BaseAnimation startAnimation() { return setAnimationPaused(false); }
    
    public BaseAnimation stopAnimation() { return setAnimationPaused(true); }
    
    public BaseAnimation setAnimationPaused(boolean animationPaused) {
        this.animationPaused = animationPaused;
        return this;
    }
    
    public BaseAnimation init() {
        TextureRegion tr = getKeyFrame(0);
        float width = tr.getRegionWidth();
        float height = tr.getRegionHeight();
        actor.setSize(width, height);
        actor.setOrigin(width / 2.0f, height / 2.0f);
        return this;
    }
    
    public BaseActor getActor() { return actor; }
    
    public BaseAnimation setLoop() { setPlayMode(PlayMode.LOOP); return this; }
    
    public BaseAnimation setNormal() { setPlayMode(PlayMode.NORMAL); return this; }
    
    public void dispose() {
    
    }
    
}
