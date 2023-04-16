package prisonersdilemma;

public class Tit4Tat extends Strategy
{
    private String strategy = "Tit4Tat";
    public boolean cooperate()
    {
        if (myprisoner.hasPartnerCheated()) {
            return false;
        }
        else {
            return true;
        }
    }

    public String getType() {
        return strategy;
    }
}
