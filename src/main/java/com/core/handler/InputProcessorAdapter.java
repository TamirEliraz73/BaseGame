package com.core.handler;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.BitSet;

/**
 * This interface extends the original interface (see {@link InputProcessor}),
 * making it easier to use without additional code, as it provides a default {@code false} return value for all methods.
 * Additionally, it maintains a static {@link BitSet} that tracks all currently pressed buttons;
 * therefore, it supports checking whether a specific button is pressed at a given time,
 * as well as determining if any button is pressed at the moment.
 *
 * @author Tamir Eliraz
 * @see com.badlogic.gdx.InputProcessor
 */
public interface InputProcessorAdapter extends InputProcessor {
    public static final BitSet keys = new BitSet(256);
    
    /**
     * {@inheritDoc} – and signing {@link Input.Keys keys} as pressed at {@link #keys}
     *
     * @param keycode
     *         {@inheritDoc}
     * @return {@inheritDoc} – and {@code false} by default
     */
    @Override
    public default boolean keyDown(int keycode) { keys.set(keycode); return false; }
    
    /**
     * {@inheritDoc} – and un-signing {@link Input.Keys keys} as pressed at {@link #keys}
     *
     * @param keycode
     *         {@inheritDoc}
     * @return {@inheritDoc}  – and {@code false} by default
     */
    @Override
    public default boolean keyUp(int keycode) { keys.clear(keycode); return false; }
    
    /**
     * Checks if a specific {@link Input.Keys keys} pressed. In other words, check if {@link #keys} signed the {@link Input.Keys keys}.
     *
     * @param keycode
     *         one of the constants in {@link Input.Keys}
     * @return {@code true} if key pressed, else {@code false}
     */
    public default boolean isKeyPressed(int keycode) { return keys.get(keycode); }
    
    /**
     * Checks if any {@link Input.Keys keys} pressed
     *
     * @return {@code true} if any key pressed, else {@code false}
     */
    public default boolean isAnyKeyPressed() { return getNumberOfKeysPressed() > 0; }
    
    /**
     * @return how many {@link Input.Keys keys} currently pressed
     */
    default int getNumberOfKeysPressed() { return keys.size(); }
    
    /**
     * {@inheritDoc}
     *
     * @param {@inheritDoc}
     * @return {@inheritDoc}  – and {@code false} by default
     */
    @Override
    public default boolean keyTyped(char character) { return false; }
    
    
    /**
     * {@inheritDoc}
     *
     * @param {@inheritDoc}
     * @return {@inheritDoc}  – and {@code false} by default
     */
    @Override
    public default boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    
    /**
     * {@inheritDoc}
     *
     * @param {@inheritDoc}
     * @return {@inheritDoc}  – and {@code false} by default
     */
    @Override
    public default boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    
    /**
     * {@inheritDoc}
     *
     * @param {@inheritDoc}
     * @return {@inheritDoc}  – and {@code false} by default
     */
    @Override
    public default boolean touchCancelled(int screenX, int screenY, int pointer, int button) { return false; }
    
    /**
     * {@inheritDoc}
     *
     * @param {@inheritDoc}
     * @return {@inheritDoc}  – and {@code false} by default
     */
    @Override
    public default boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    
    /**
     * {@inheritDoc}
     *
     * @param {@inheritDoc}
     * @return {@inheritDoc}  – and {@code false} by default
     */
    @Override
    public default boolean mouseMoved(int screenX, int screenY) { return false; }
    
    /**
     * {@inheritDoc}
     *
     * @param {@inheritDoc}
     * @return {@inheritDoc}  – and {@code false} by default
     */
    @Override
    public default boolean scrolled(float amountX, float amountY) { return false; }
}
