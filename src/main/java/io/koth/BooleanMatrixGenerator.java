package io.koth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BooleanMatrixGenerator {
    public OutcomeMatrix resolve(final String booleanStatement) {
        final LogicParser logicParser = new LogicParser();
        final List<BooleanCheck> booleanChecks = logicParser.parse(booleanStatement);

        List<Outcome> outcomes = new ArrayList<>();

        int possibleIterations = (int)Math.pow(2, booleanChecks.size());

        for (int i = 0; i < possibleIterations; i++) {

            List<ComparisonForAssesment> booleanComparisons = new ArrayList<>();

            for (int booleanCheckIndex = 0; booleanCheckIndex < booleanChecks.size(); booleanCheckIndex++) {
                BooleanCheck booleanCheck = booleanChecks.get(booleanCheckIndex);
                booleanComparisons.add(new ComparisonForAssesment(new BooleanComparison(booleanCheck.statement, (i & 1 << booleanCheckIndex) > 0), booleanCheck.nextOperator));
            }
            outcomes.add(new Outcome(booleanComparisons.stream().map(c -> c.booleanComparison).collect(Collectors.toList()), assesResult(booleanComparisons)));
        }


        return new OutcomeMatrix(outcomes);
    }

    private boolean assesResult(List<ComparisonForAssesment> booleanComparisons) {
        boolean result = false;

        for (int i = 0; i < booleanComparisons.size(); i++) {
            ComparisonForAssesment comparisonForAssesment = booleanComparisons.get(i);
            if(i == 0)
            {
                result = comparisonForAssesment.booleanComparison.result;
            }
            else
            {
                ComparisonForAssesment previousComparison = booleanComparisons.get(i - 1);
                Operator operator = previousComparison.nextOperator.orElseThrow((() -> new RuntimeException("Unable to resolve operator")));
                result = operator.apply(result, comparisonForAssesment.booleanComparison.result);
            }

        }

        return result;
    }
}
