package io.koth;

import java.util.Optional;

public class BooleanCheck {
    public final String statement;
    public final Optional<Operator> nextOperator;

    public BooleanCheck(String statement, Optional<Operator> nextOperator) {

        this.statement = statement;
        this.nextOperator = nextOperator;
    }
}
