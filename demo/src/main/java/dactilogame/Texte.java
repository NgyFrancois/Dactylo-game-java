package dactilogame;

import java.util.LinkedList;
import java.util.Random;

public class Texte {
    public LinkedList<Mot> texte = new LinkedList<>();
    public LinkedList<String> motAlea = new LinkedList<>(); // Liste de mot aléatoire pour alimenter le texte


    public Texte(){ 
        motAlea.add("sourcils");
        motAlea.add("lasagne");
        motAlea.add("poivre");
        motAlea.add("description");
        motAlea.add("flamme");
        motAlea.add("musique");
        motAlea.add("gentil");
        motAlea.add("vieux");
        motAlea.add("tiroir");
        motAlea.add("poils");
        motAlea.add("digestion");
        motAlea.add("parler");
        motAlea.add("alphabet");
        motAlea.add("marqueur");
        motAlea.add("rouille");
        motAlea.add("vagabond");
        motAlea.add("toilettes");
        motAlea.add("altitude");
        motAlea.add("galaxie");
        motAlea.add("machine");
        motAlea.add("vivant");
        motAlea.add("savon");
        motAlea.add("batterie");
        motAlea.add("logiciel");
        motAlea.add("livre");

        int i=0;
        while(i<10){ // pour mettre 10 mot aléatoire dans le texte de solo
            texte.add(new Mot(motAlea.get(new Random().nextInt(25))));
            i++;
        }
    }

    static boolean estMajuscule(char ch){
        int ascii = (int) ch;
        //[A..Z]
        if((ascii>=65 && ascii<=90) 
        //les lettres accentuées
        || (ascii>=192 && ascii<=223))
        return true;
        return false;
    }

    static boolean estMinuscule(char ch){
        int ascii = (int) ch;
        //[a..z]
        if((ascii>=97 && ascii<=122) 
        //les lettres accentuées
        || (ascii>=224 && ascii<=255))
        return true;
        return false;
    }

    public void addFirst(Mot s){ // équivalent de push dans une pile
        this.texte.addFirst(s);
    }

    // public void addLast(String s){
    //     this.texte.addLast(s);
    // }

    public void ajouteMot(){
        String nouveau_mot = motAlea.get(new Random().nextInt(25));
        Mot mot = new Mot(nouveau_mot);
        // 1 chance sur 15 que le mot soit un mot bonus
        if(new Random().nextInt(15) == 1){
            mot.setBonus(true);
        }
        addFirst(mot);
        System.out.println("Mot ajouté :"+nouveau_mot);
    }

    public void RemoveLast(){
        texte.removeLast();
    }

    public Mot getLast(){
        return texte.getLast();
    }

    public Mot getElement(int i){
        return texte.get(i);
    }

    public Mot pop(){
        Mot res = getLast();
        RemoveLast();
        return res;
    }

    public int getLength(){
        return texte.size();
    }

    public boolean isEmpty(){
        return this.texte.isEmpty();
    }

}
