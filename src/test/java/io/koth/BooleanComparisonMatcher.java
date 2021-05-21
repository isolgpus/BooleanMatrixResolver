package io.koth;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class BooleanComparisonMatcher extends TypeSafeMatcher<BooleanComparison> {
    private final BooleanComparison expectedBooleanComparison;

    public BooleanComparisonMatcher(BooleanComparison expectedBooleanComparison) {
        this.expectedBooleanComparison = expectedBooleanComparison;
    }

    @Override
    protected boolean matchesSafely(BooleanComparison booleanComparison) {
        return expectedBooleanComparison.comparisonText.equals(booleanComparison.comparisonText) &&
                expectedBooleanComparison.result == booleanComparison.result;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expectedBooleanComparison);
    }
}
