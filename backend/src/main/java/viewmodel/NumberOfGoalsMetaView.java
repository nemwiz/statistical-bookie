package viewmodel;

public class NumberOfGoalsMetaView implements ViewModel{

    private NumberOfGoalsView bothTeams;
    private NumberOfGoalsView homeTeam;
    private NumberOfGoalsView awayTeam;

    public NumberOfGoalsMetaView(NumberOfGoalsView bothTeams, NumberOfGoalsView homeTeam, NumberOfGoalsView awayTeam) {
        this.bothTeams = bothTeams;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public NumberOfGoalsView getBothTeams() {
        return bothTeams;
    }

    public NumberOfGoalsView getHomeTeam() {
        return homeTeam;
    }

    public NumberOfGoalsView getAwayTeam() {
        return awayTeam;
    }

    @Override
    public String toString() {
        return "NumberOfGoalsMetaView{" +
                "bothTeams=" + bothTeams +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                '}';
    }
}
