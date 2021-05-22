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
                booleanComparisons.add(new ComparisonForAssesment(new BooleanComparison(booleanCheck.statement, (i & 1 << booleanCheckIndex) > 0), booleanCheck.nextOperator, booleanCheck.scopeDepth));
            }

            outcomes.add(new Outcome(booleanComparisons.stream().map(c -> c.booleanComparison).collect(Collectors.toList()), assesResult(booleanComparisons, new RecursiveAssessmentState())));
        }


        return new OutcomeMatrix(outcomes);
    }

    private boolean assesResult(final List<ComparisonForAssesment> booleanComparisons, final RecursiveAssessmentState recursiveAssessmentState) {
        boolean result = false;

        boolean isFirstIterationOfLoop = true;
        while(recursiveAssessmentState.index < booleanComparisons.size())
        {

            ComparisonForAssesment comparisonForAssesment = booleanComparisons.get(recursiveAssessmentState.index);
            if(comparisonForAssesment.scopeDepth < recursiveAssessmentState.scopeDepth)
            {
                recursiveAssessmentState.index--; // rewind so we can reasses again on the correct depth
                recursiveAssessmentState.scopeDepth--;
                break;
            }
            else
            {
                if(isFirstIterationOfLoop)
                {
                    isFirstIterationOfLoop = false;
                    if(comparisonForAssesment.scopeDepth > recursiveAssessmentState.scopeDepth)
                    {
                        recursiveAssessmentState.scopeDepth++;
                        result = assesResult(booleanComparisons, recursiveAssessmentState);
                    }
                    else
                    {
                        result = comparisonForAssesment.booleanComparison.result;
                    }
                }
                else
                {
                    ComparisonForAssesment previousComparison = booleanComparisons.get(recursiveAssessmentState.index - 1);
                    Operator operator = previousComparison.nextOperator.orElseThrow((() -> new RuntimeException("Unable to resolve operator")));
                    if(comparisonForAssesment.scopeDepth > recursiveAssessmentState.scopeDepth)
                    {
                        recursiveAssessmentState.scopeDepth++;
                        result = operator.apply(result, assesResult(booleanComparisons, recursiveAssessmentState));
                    }
                    else
                    {
                        result = operator.apply(result, comparisonForAssesment.booleanComparison.result);

                    }
                }
            }


            recursiveAssessmentState.index++;
        }

        return result;
    }


    private static final class RecursiveAssessmentState
    {
        int index = 0;
        int scopeDepth = 0;
    }
}
