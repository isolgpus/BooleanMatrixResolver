package io.koth;

import java.util.Optional;

public enum Operator {
    AND("&&", (original, against) -> original && against),
    OR("||", (original, against) -> original || against),
    XOR("^", (original, against) -> original ^ against);

    private final String value;
    private final Operation operation;

    Operator(String value, Operation operation) {
        this.value = value;
        this.operation = operation;
    }

    public static Optional<Operator> resolveOperator(String instance) {
        for (Operator operator : Operator.values()) {
            if(operator.value.equals(instance))
            {
                return Optional.of(operator);
            }
        }
        return Optional.empty();

    }

    public boolean apply(boolean original, boolean against) {
        return operation.apply(original, against);
    }
}
