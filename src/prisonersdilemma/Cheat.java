package prisonersdilemma;

public class Cheat extends Strategy
{
    private String strategy = "Cheat";
    public boolean cooperate()
    {
        return false;
    }

    public String getType() {
        return strategy;
    }
}