package io.koth;

import java.util.Optional;

public class BooleanCheck {
    public final String statement;
    public final Optional<Operator> nextOperator;
    public final int scopeDepth;

    public BooleanCheck(String statement, Optional<Operator> nextOperator, int scopeDepth) {

        this.statement = statement;
        this.nextOperator = nextOperator;
        this.scopeDepth = scopeDepth;
    }
}
