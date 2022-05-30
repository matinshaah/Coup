package model;

public enum CardType {
    Captain(0),
    Assassin(1),
    Contessa(2),
    Ambassador(3),
    Duke(4);
    final int id;
    CardType (int id){
        this.id=id;
    }
    public static CardType getTypeById(int id){
        for (CardType t:
             CardType.values()) {
            if(t.id==id) return t;

        }
        return null;
    }
}
