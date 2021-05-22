package io.koth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LogicParser {

    List<BooleanCheck> conditions = new ArrayList<>();
    final BooleanConditionBuilder booleanCheckBuilder = new BooleanConditionBuilder();

    public List<BooleanCheck> parse(String booleanStatement) {

        StringBuilder currentWord = new StringBuilder();
        for (char c : booleanStatement.toCharArray()) {
            if(c == ' ')
            {
                handleInstance(currentWord.toString()).ifPresent(conditions::add);
                currentWord = new StringBuilder();
            }
            else if (c == '(' || c == ')')
            {
                handleInstance(String.valueOf(c));
            }
            else
            {
                currentWord.append(c);
            }
        }

        handleInstance(currentWord.toString()).ifPresent(conditions::add);
        booleanCheckBuilder.flush().ifPresent(conditions::add);
        return conditions;
    }

    private Optional<BooleanCheck> handleInstance(String instance) {
        return booleanCheckBuilder.handleInstance(instance);
    }
}
