package io.koth;

import java.util.List;

public class OutcomeMatrix {

    private final List<Outcome> outcomes;

    public OutcomeMatrix(List<Outcome> outcomes) {

        this.outcomes = outcomes;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }
}
