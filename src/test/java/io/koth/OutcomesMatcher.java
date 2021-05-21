package io.koth;

import org.hamcrest.collection.IsIterableContainingInOrder;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OutcomesMatcher extends IsIterableContainingInOrder<Outcome> {
    public OutcomesMatcher(Outcome... expectedOutcomes) {
        super(Arrays.stream(expectedOutcomes).map(OutcomeMatcher::new).collect(Collectors.toList()));
    }

}
