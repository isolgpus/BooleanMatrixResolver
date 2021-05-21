package io.koth;

import java.util.Optional;

public class ComparisonForAssesment {
    public final BooleanComparison booleanComparison;
    public final Optional<Operator> nextOperator;

    public ComparisonForAssesment(BooleanComparison booleanComparison, Optional<Operator> nextOperator) {
        super();
        this.booleanComparison = booleanComparison;
        this.nextOperator = nextOperator;
    }
}
