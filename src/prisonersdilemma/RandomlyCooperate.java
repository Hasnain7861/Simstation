package prisonersdilemma;

import java.util.*;
public class RandomlyCooperate extends Strategy
{
    private String strategy = "randomCooperate";
    public boolean cooperate()
    {
        Random random = new Random();
        return random.nextBoolean();
    }

    public String getStrategy() {
        return strategy;
    }
}

