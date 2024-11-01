package com.core.base.group;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface BaseBodyPart {
    @Contract(pure = true)
    @NotNull String fileName();
    
    default boolean getDefaultVisibility() { return true; }
}
