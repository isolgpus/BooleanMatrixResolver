package io.koth;

import java.util.Optional;

public class ComparisonForAssesment {
    public final BooleanComparison booleanComparison;
    public final Optional<Operator> nextOperator;
    public final int scopeDepth;

    public ComparisonForAssesment(BooleanComparison booleanComparison, Optional<Operator> nextOperator, int scopeDepth) {
        super();
        this.booleanComparison = booleanComparison;
        this.nextOperator = nextOperator;
        this.scopeDepth = scopeDepth;
    }
}
