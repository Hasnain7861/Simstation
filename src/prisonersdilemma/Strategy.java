package prisonersdilemma;

public abstract class Strategy {

    Prisoner myprisoner;
    abstract boolean cooperate();

    abstract String getType();

}
