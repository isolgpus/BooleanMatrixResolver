package io.koth;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.collection.IsIterableContainingInOrder;

import java.util.stream.Collectors;

public class OutcomeMatcher extends TypeSafeMatcher<Outcome> {
    private final Outcome expectedOutcome;

    public OutcomeMatcher(Outcome expectedOutcome) {
        this.expectedOutcome = expectedOutcome;
    }


    protected boolean matchesSafely(Outcome outcome) {
        boolean comparisonsMatch = new IsIterableContainingInOrder<>(
                expectedOutcome.comparisons.stream().map(BooleanComparisonMatcher::new).collect(Collectors.toList())
        ).matches(outcome.comparisons);

        return comparisonsMatch && expectedOutcome.result == outcome.result;
    }

    public void describeTo(Description description) {
        description.appendValue(expectedOutcome);
    }

}
