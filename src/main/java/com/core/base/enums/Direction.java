package com.core.base.enums;

import com.badlogic.gdx.Input;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public enum Direction {
    RIGHT, LEFT, UP, DOWN;
    
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
}
