package io.koth;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

class BooleanMatrixGeneratorTest {

    @Test
    void shouldResolveOutcomesWhenComparingTwoVariables() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();

        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat == dog"),
                        outcome(false, false),
                        outcome(true, true)
                ));
    }

    @Test
    void shouldResolveOutcomesWhenComparingTwoVariablesAsNot() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat != dog");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat != dog"),
                        outcome(false, false),
                        outcome(true, true)
                ));
    }

    @Test
    void shouldResolveCombinationsWhenComparingTwoBooleanResultsWithAnd() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog && fish == mouse");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat == dog", "fish == mouse"),
                        outcome(false, false, false),
                        outcome(true, false, false),
                        outcome(false, true, false),
                        outcome(true, true, true)
                ));
    }

    @Test
    void shouldResolveCombinationsWhenComparingTwoBooleanResultsWithOr() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog || fish == mouse");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat == dog", "fish == mouse"),
                        outcome(false, false, false),
                        outcome(true, false, true),
                        outcome(false, true, true),
                        outcome(true, true, true)
                ));
    }

    @Test
    void shouldResolveCombinationsWhenComparingTwoBooleanResultsWithXOr() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog ^ fish == mouse");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat == dog", "fish == mouse"),
                        outcome(false, false, false),
                        outcome(true, false, true),
                        outcome(false, true, true),
                        outcome(true, true, false)
                ));
    }

    @Test
    void shouldResolveThreeCombinationsWithDoubleAnd() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog && fish == mouse && lion == monkey");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat == dog", "fish == mouse", "lion == monkey"),
                        outcome(false, false, false, false),
                        outcome(true, false, false, false),
                        outcome(false, true, false, false),
                        outcome(true, true, false, false),
                        outcome(false, false, true, false),
                        outcome(true, false, true, false),
                        outcome(false, true, true, false),
                        outcome(true, true, true, true)
                ));
    }

    @Test
    void shouldResolveThreeCombinationsWithDoubleOr() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog || fish == mouse || lion == monkey");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat == dog", "fish == mouse", "lion == monkey"),
                        outcome(false, false, false, false),
                        outcome(true, false, false, true),
                        outcome(false, true, false, true),
                        outcome(true, true, false, true),
                        outcome(false, false, true, true),
                        outcome(true, false, true, true),
                        outcome(false, true, true, true),
                        outcome(true, true, true, true)
                ));
    }

    @Test
    void shouldResolveThreeCombinationsWithDoubleXOr() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog ^ fish == mouse ^ lion == monkey");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat == dog", "fish == mouse", "lion == monkey"),
                        outcome(false, false, false, false),
                        outcome(true, false, false, true),
                        outcome(false, true, false, true),
                        outcome(true, true, false, false),
                        outcome(false, false, true, true),
                        outcome(true, false, true, false),
                        outcome(false, true, true, false),
                        outcome(true, true, true, true)
                ));
    }

    @Test
    void shouldAssessAndsBeforeOrs() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog && fish == mouse || lion == monkey");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat == dog", "fish == mouse", "lion == monkey"),
                        outcome(false, false, false, false),
                        outcome(true, false, false, false),
                        outcome(false, true, false, false),
                        outcome(true, true, false, true),
                        outcome(false, false, true, true),
                        outcome(true, false, true, true),
                        outcome(false, true, true, true),
                        outcome(true, true, true, true)
                ));
    }
    @Test
    void shouldAssessAndsBeforeOrs2() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog || fish == mouse && lion == monkey");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat == dog", "fish == mouse", "lion == monkey"),
                        outcome(false, false, false, false),
                        outcome(true, false, false, true),
                        outcome(false, true, false, false),
                        outcome(true, true, false, true),
                        outcome(false, false, true, false),
                        outcome(true, false, true, true),
                        outcome(false, true, true, true),
                        outcome(true, true, true, true)
                ));
    }

    @Test
    void shouldPrioritiseScopeOverOrder() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog && (fish == mouse || lion == monkey)");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();

        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat == dog", "fish == mouse", "lion == monkey"),
                        outcome(false, false, false, false),
                        outcome(true, false, false, false),
                        outcome(false, true, false, false),
                        outcome(true, true, false, true),
                        outcome(false, false, true, false),
                        outcome(true, false, true, true),
                        outcome(false, true, true, false),
                        outcome(true, true, true, true)
        ));
    }

    @Test
    @Disabled
    void shouldPrioritiseScopeOverAnd() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("(cat == dog || fish == mouse) && lion == monkey");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();

        assertThat(outcomes,
                buildExpectedOutcomes(
                        statements("cat == dog", "fish == mouse", "lion == monkey"),
                        outcome(false, false, false, false),
                        outcome(true, false, false, false),
                        outcome(false, true, false, false),
                        outcome(true, true, false, false),
                        outcome(false, false, true, false),
                        outcome(true, false, true, true),
                        outcome(false, true, true, true),
                        outcome(true, true, true, true)
                ));
    }

    private String[] statements(String... s) {
        return s;
    }


    private OutcomesMatcher buildExpectedOutcomes(String[] statements, boolean[]... outcomes)
    {
        List<Outcome> builtOutcomes = new ArrayList<>();
        for (boolean[] outcome : outcomes) {
            List<BooleanComparison> comparisons = new ArrayList<>();

            for (int i = 0; i < statements.length; i++) {
                final String statement = statements[i];
                comparisons.add(new BooleanComparison(statement, outcome[i]));

            }

            builtOutcomes.add(new Outcome(comparisons, outcome[outcome.length - 1]));
        }
        return new OutcomesMatcher(builtOutcomes.toArray(new Outcome[0]));
    }

    private boolean[] outcome(boolean... argsAndResult)
    {
        return argsAndResult;
    }

}