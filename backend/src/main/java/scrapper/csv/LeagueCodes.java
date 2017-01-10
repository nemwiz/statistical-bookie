package scrapper.csv;

import com.google.common.collect.ImmutableMap;

public enum LeagueCodes {
    E0,
    E1,
    E2,
    E3,
    EC,
    SC0,
    SC1,
    SC2,
    SC3,
    D1,
    D2,
    I1,
    I2,
    SP1,
    SP2,
    F1,
    F2,
    N1,
    B1,
    P1,
    T1,
    G1;


    public static final ImmutableMap<String, String> leagueCodeToLeagueNameMap = new ImmutableMap.Builder<String, String>()
            .put(E0.name(), "Premier league")
            .put(E1.name(), "Championship")
            .put(E2.name(), "League One")
            .put(E3.name(), "League Two")
            .put(EC.name(), "Conference")
            .put(SC0.name(), "Premier league")
            .put(SC1.name(), "Division 1")
            .put(SC2.name(), "Division 2")
            .put(SC3.name(), "Division 3")
            .put(D1.name(), "Bundesliga 1")
            .put(D2.name(), "Bundesliga 2")
            .put(I1.name(), "Serie A")
            .put(I2.name(), "Serie B")
            .put(SP1.name(), "La Liga Primera")
            .put(SP2.name(), "La Liga Segunda")
            .put(F1.name(), "Ligue 1")
            .put(F2.name(), "Ligue 2")
            .put(N1.name(), "Eredivisie")
            .put(B1.name(), "Jupiler League")
            .put(P1.name(), "Liga 1")
            .put(T1.name(), "Futbol Ligi 1")
            .put(G1.name(), "Ethniki Katigoria")
            .build();

    public static final ImmutableMap<String, String> leagueCodeToCountryNameMap = new ImmutableMap.Builder<String, String>()
            .put(E0.name(), Countries.England.name())
            .put(E1.name(), Countries.England.name())
            .put(E2.name(), Countries.England.name())
            .put(E3.name(), Countries.England.name())
            .put(EC.name(), Countries.England.name())
            .put(SC0.name(), Countries.Scotland.name())
            .put(SC1.name(), Countries.Scotland.name())
            .put(SC2.name(), Countries.Scotland.name())
            .put(SC3.name(), Countries.Scotland.name())
            .put(D1.name(), Countries.Germany.name())
            .put(D2.name(), Countries.Germany.name())
            .put(I1.name(), Countries.Italy.name())
            .put(I2.name(), Countries.Italy.name())
            .put(SP1.name(), Countries.Spain.name())
            .put(SP2.name(), Countries.Spain.name())
            .put(F1.name(), Countries.France.name())
            .put(F2.name(), Countries.France.name())
            .put(N1.name(), Countries.Netherlands.name())
            .put(B1.name(), Countries.Belgium.name())
            .put(P1.name(), Countries.Portugal.name())
            .put(T1.name(), Countries.Turkey.name())
            .put(G1.name(), Countries.Greece.name())
            .build();
}

