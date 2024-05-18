package dactilogame;

public class Mot {
private String nom;
private boolean bonus = false;

public Mot(String nom){
    this.nom=nom;
}

public String getNom(){
    return nom;
}

public boolean getBonus(){
    return bonus;
}

public void setBonus(boolean b){
    bonus = b;
}
}
