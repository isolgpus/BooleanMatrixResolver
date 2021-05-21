package io.koth;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

class BooleanMatrixGeneratorTest {

    @Test
    void shouldResolveOutcomesWhenComparingTwoVariables() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes, expectOutcomes(
                outcome(false, comparison("cat == dog", false)),
                outcome(true, comparison("cat == dog", true))
        ));
    }

    @Test
    void shouldResolveOutcomesWhenComparingTwoVariablesAsNot() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat != dog");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes, expectOutcomes(
                        outcome(false, comparison("cat != dog", false)),
                        outcome(true, comparison("cat != dog", true))));
    }

    @Test
    void shouldResolveCombinationsWhenComparingTwoBooleanResultsWithAnd() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog && fish == mouse");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                expectOutcomes(
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false)
                        ),
                        outcome(false,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false)
                        ),
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true)
                        )
                )
        );
    }

    @Test
    void shouldResolveCombinationsWhenComparingTwoBooleanResultsWithOr() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog || fish == mouse");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                expectOutcomes(
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true)
                        )
                )
        );
    }



    @Test
    void shouldResolveCombinationsWhenComparingTwoBooleanResultsWithXOr() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog ^ fish == mouse");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                expectOutcomes(
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true)
                        ),
                        outcome(false,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true)
                        )
                )
        );
    }

    @Test
    void shouldResolveThreeCombinationsWithDoubleAnd() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog && fish == mouse && lion == monkey");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                expectOutcomes(
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", false)
                        ),
                        outcome(false,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", false)
                        ),
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", false)
                        ),
                        outcome(false,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", false)
                        ),
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", true)
                        ),
                        outcome(false,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", true)
                        ),
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", true)
                        )
                )
        );
    }

    @Test
    void shouldResolveThreeCombinationsWithDoubleOr() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog || fish == mouse || lion == monkey");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                expectOutcomes(
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", true)
                        )
                )
        );
    }

    @Test
    void shouldResolveThreeCombinationsWithDoubleXOr() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog ^ fish == mouse ^ lion == monkey");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                expectOutcomes(
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", false)
                        ),
                        outcome(false,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", true)
                        ),
                        outcome(false,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", true)
                        ),
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", true)
                        )
                )
        );
    }

    @Test
    void shouldAssesAndsBeforeOrs() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog && fish == mouse || lion == monkey");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                expectOutcomes(
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", false)
                        ),
                        outcome(false,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", false)
                        ),
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", true)
                        )
                )
        );
    }

    @Test
    @Disabled
    void shouldAssesAndsBeforeOrs2() {
        BooleanMatrixGenerator booleanMatrixGenerator = new BooleanMatrixGenerator();
        OutcomeMatrix outcomeMatrix = booleanMatrixGenerator.resolve("cat == dog || fish == mouse && lion == monkey");

        List<Outcome> outcomes = outcomeMatrix.getOutcomes();
        assertThat(outcomes,
                expectOutcomes(
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", false)
                        ),
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", false)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", false)
                        ),
                        outcome(false,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", false),
                                comparison("lion == monkey", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", false),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", true)
                        ),
                        outcome(true,
                                comparison("cat == dog", true),
                                comparison("fish == mouse", true),
                                comparison("lion == monkey", true)
                        )
                )
        );
    }

    private BooleanComparison comparison(String statement, boolean result) {
        return new BooleanComparison(statement, result);
    }


    private OutcomesMatcher expectOutcomes(Outcome... outcomes)
    {
        return new OutcomesMatcher(outcomes);
    }

    private Outcome outcome(final boolean result, final BooleanComparison... booleanComparisons)
    {
        return new Outcome(Arrays.asList(booleanComparisons), result);
    }
}