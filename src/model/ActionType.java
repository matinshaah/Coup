package model;

public enum ActionType {
    Income(null),
    ForeignAid(null),
    Coup(null),
    Tax(CardType.Duke),
    Assassinate(CardType.Assassin),
    Exchange(CardType.Ambassador),
    Steal(CardType.Captain);

    final CardType character;
    ActionType(CardType character){
        this.character = character;
    }

    public static ActionType getActionTypeByCardType(CardType cardType){
        for (ActionType a:
             ActionType.values()) {
            if(a.character==cardType) return a;
        }
        return null;
    }
}
