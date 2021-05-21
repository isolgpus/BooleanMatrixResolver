package io.koth;

import java.util.*;
import java.util.stream.Collectors;

public class BooleanConditionBuilder {

    private List<String> rawInput = new ArrayList<>();

    public Optional<BooleanCheck> handleInstance(String instance) {
        Optional<Operator> operator = Operator.resolveOperator(instance);
        if(operator.isPresent())
        {

            return retrieveRawInputAndClear(operator);
        }
        else
        {
            rawInput.add(instance);
        }
        return Optional.empty();
    }

    private Optional<BooleanCheck> retrieveRawInputAndClear(final Optional<Operator> operator) {
        Optional<BooleanCheck> booleanCondition = Optional.of(new BooleanCheck(rawInput.stream().collect(Collectors.joining(" ")), operator));
        this.rawInput.clear();
        return booleanCondition;
    }

    public Optional<BooleanCheck> flush() {

        return retrieveRawInputAndClear(Optional.empty());
    }
}
