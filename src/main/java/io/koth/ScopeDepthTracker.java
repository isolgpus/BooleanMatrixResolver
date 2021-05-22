package io.koth;

public class ScopeDepthTracker {
    private boolean useArtificialAndDepth = false;
    private int scopeDepth = 0;

    public void toggleArtificialAndDepth() {
        useArtificialAndDepth = true;
    }

    public int resolveScopeDepth()
    {
        return scopeDepth + (useArtificialAndDepth ? 1 : 0);
    }

    public void untoggleArtificialAndDepth() {
        useArtificialAndDepth = false;
    }
}
