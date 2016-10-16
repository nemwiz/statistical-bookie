package aggregator;

import viewmodel.ViewModel;

public abstract class Aggregator {

    public abstract ViewModel getAggregatedCount() ;

    private long[] getCountFullTime() {
        return new long[]{};
    };

    private long[] getCountHalfTime() {
        return new long[]{};
    };

    private ViewModel mapArrayToViewModel(
            long[] countOfMatchFullTime,
            long[] countOfMatchHalfTime) {

        return null;
    }

}
