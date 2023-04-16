package prisonersdilemma;

public class Cooperate extends Strategy {
    private String strategy = "Cooperate";
    public boolean cooperate()
    {
        return true;
    }

    public String getType() {
        return strategy;
    }

}
