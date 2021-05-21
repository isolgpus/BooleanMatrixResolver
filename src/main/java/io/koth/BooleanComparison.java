package io.koth;

public class BooleanComparison {
    public final String comparisonText;
    public final boolean result;

    public BooleanComparison(String comparisonText, boolean result) {
        this.comparisonText = comparisonText;
        this.result = result;
    }

    @Override
    public String toString() {
        return "BooleanComparison{" +
                "comparisonText='" + comparisonText + '\'' +
                ", result=" + result +
                '}';
    }
}
