package io.koth;

public interface Operation {
    boolean apply(boolean original, boolean against);
}
