package helper;

import java.util.List;

public class Counter {

    private static boolean acceptBoolean(boolean myBooleanValue) {
         return myBooleanValue;
    }

    public static long count(List<?> matchesWithCollecterModel, boolean value) {

        return matchesWithCollecterModel.stream()
                .filter(collecterModel -> acceptBoolean(value))
                .count();

    }
}
