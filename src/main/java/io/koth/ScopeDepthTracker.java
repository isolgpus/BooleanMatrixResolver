package io.koth;

public class ScopeDepthTracker {
    private boolean[] useArtificialAndDepth = new boolean[128];


    private int scopeDepth = 0;
    private int registeredScopeDecrementation = 0;

    public void toggleArtificialAndDepth() {
        useArtificialAndDepth[scopeDepth] = true;
    }

    public int resolveScopeDepth()
    {
        int artificialAndDepthTotal = 0;
        for (int i = 0; i <= scopeDepth; i++) {
            if(useArtificialAndDepth[i])
            {
                artificialAndDepthTotal++;
            }
        }
        return scopeDepth + artificialAndDepthTotal;
    }

    public void untoggleArtificialAndDepth() {
        useArtificialAndDepth[scopeDepth] = false;
    }

    public void incrementScopeDepth() {
        scopeDepth++;
    }

    public void registerDecrementScopeDepth() {
        registeredScopeDecrementation--;
    }

    public void applyDecrements() {
        this.scopeDepth -= registeredScopeDecrementation;
        registeredScopeDecrementation = 0;
    }

}
