package io.koth;

import java.util.List;

public class Outcome {
    public final List<BooleanComparison> comparisons;
    public final boolean result;

    public Outcome(List<BooleanComparison> comparisons, boolean result) {

        this.comparisons = comparisons;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Outcome{" +
                "comparisons=" + comparisons +
                ", result=" + result +
                '}';
    }
}
