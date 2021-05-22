package io.koth;

import java.util.*;
import java.util.stream.Collectors;

import static io.koth.Operator.AND;

public class BooleanConditionBuilder {

    private List<String> rawInput = new ArrayList<>();
    private final ScopeDepthTracker scopeDepthTracker = new ScopeDepthTracker();
    public Optional<BooleanCheck> handleInstance(String instance) {
        Optional<Operator> operator = Operator.resolveOperator(instance);

        if(operator.isPresent())
        {
            if(operator.get() == AND)
            {
                scopeDepthTracker.toggleArtificialAndDepth();
            }
            return retrieveRawInputAndClear(operator);
        }
        else
        {
            rawInput.add(instance);
        }
        return Optional.empty();
    }

    private Optional<BooleanCheck> retrieveRawInputAndClear(final Optional<Operator> operator) {
        Optional<BooleanCheck> booleanCondition = Optional.of(new BooleanCheck(rawInput.stream().collect(Collectors.joining(" ")), operator, scopeDepthTracker.resolveScopeDepth()));
        if(operator.isPresent())
        {
            if(operator.get() != AND)
            {
                scopeDepthTracker.untoggleArtificialAndDepth();
            }
        }
        this.rawInput.clear();
        return booleanCondition;
    }

    public Optional<BooleanCheck> flush() {

        return retrieveRawInputAndClear(Optional.empty());
    }
}
