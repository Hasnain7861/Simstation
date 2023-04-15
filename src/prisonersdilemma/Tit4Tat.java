package prisonersdilemma;

public class Tit4Tat extends Strategy
{
    private String strategy = "Tit4Tat";
    public boolean cooperate()
    {
        if (prisoner.hasPartnerCheated()) {
            return false;
        }
        return true;
    }

    public String getStrategy() {
        return strategy;
    }
}
