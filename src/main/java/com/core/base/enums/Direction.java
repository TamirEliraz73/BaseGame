package com.core.base.enums;

import com.badlogic.gdx.Input;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the four possible directions in a 2D space.
 *
 * @author Tamir Eliraz
 */
public enum Direction {
    /**
     * Represents the right direction.
     */
    RIGHT,
    
    /**
     * Represents the left direction.
     */
    LEFT,
    
    /**
     * Represents the upward direction.
     */
    UP,
    
    /**
     * Represents the downward direction.
     */
    DOWN;
    
    /**
     * Returns the corresponding {@link Direction} for the given {@link Input.Keys key input}.
     *
     * @param key
     *         the {@link Input.Keys key code} representing a direction (e.g., {@link Input.Keys UP},
     *         {@link Input.Keys W})
     * @return the {@link Direction} corresponding to the specified key,
     * or {@code null} if the key does not match any direction
     */
    @Contract(pure = true)
    public static @Nullable Direction getByKey(int key) {
        return switch (key) {
            case Input.Keys.UP, Input.Keys.W -> UP;
            case Input.Keys.DOWN, Input.Keys.S -> DOWN;
            case Input.Keys.LEFT, Input.Keys.A -> LEFT;
            case Input.Keys.RIGHT, Input.Keys.D -> RIGHT;
            default -> null;
        };
    }
    
    /**
     * Returns whether the {@link Direction direction} is along the X axis.
     *
     * @return {@code true} if the direction is {@link #LEFT} or {@link #RIGHT}, {@code false} otherwise
     */
    public boolean isHorizontal() {
        return this == LEFT || this == RIGHT;
    }
    
    /**
     * Returns whether the {@link Direction direction} is along the Y axis.
     *
     * @return {@code true} if the direction is {@link #UP} or {@link #DOWN}, {@code false} otherwise
     */
    public boolean isVertical() {
        return this == UP || this == DOWN;
    }
    
}