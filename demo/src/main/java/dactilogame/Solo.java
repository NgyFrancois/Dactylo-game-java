package dactilogame;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Solo extends ModeJeu{

public Solo(){
    super();
    super.solo = this;
}


@FXML
public Text timer;
private int niveau =1;
@FXML
private Text pv;
 
@FXML
private Text niveau_vue;

TimerThread time = new TimerThread();

public double getVitesse(){
    double res = 3 * Math.pow(0.9, niveau); // fonction pour la vitesse d'enchainement des mots
    return res;
}

@FXML
void bouton_start_action(ActionEvent event) {
    super.bouton_start_action(event);
    super.setUser1(new User("user1"));
    //Timer pour l'ajout de chaque mots
    time.setSolo(this);
    // x1000 car 1000 = 1seconde pour le thread, on a décider de mettre en deci-seconde pour que ca soit ni trop grand ni trop petit pour l'affichage
    time.setTime(getVitesse()*10);
    timer.setText(String.valueOf(time.getTime()));
    time.start();
}

    @FXML
    private void goMenu() throws IOException {
        App.setRoot("menu");
        this.time.arret();
    }

    @FXML
    private void goFin() throws IOException {
        //affiche une fenetre de stats afin d'afficher la fin
        TextArea stats = new TextArea("Statistiques\n");
        if(user1!=null){ // A regler ( user null quand on l'appel )
        stats.appendText("Vitesse : " + String.valueOf(MPM(user1)) + "caractères utiles par minutes\n");
        stats.appendText("Precision : " + String.valueOf(Precision(user1)) + "% de caractère utiles\n");
        stats.appendText("Regularite : " + String.valueOf(Regularite(user1)) + "temps en secondes entre 2 caractères utiles");
        }
        StackPane root = new StackPane(stats);
        Scene scene = new Scene(root, 600, 500);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Statistique");
        stage.show();
        App.setRoot("fin");
    }


@FXML 
void setTimer(String t){
    timer.setText(t);
}

@FXML
public void ajouterMot(){
    // Si on a plus de 10 mot , on supprime le mot en cours (on perd de la vie) pour rajouter le nouveau mot
    if(this.text.getLength()>10){
        this.text.RemoveLast();
        this.user1.setPv(this.user1.getPv()-5);
        super.myText.clear();   // on supprime le texte en cours comme ce n'est plus le meme mot
    }
    this.text.ajouteMot();
    text_vue_maj();
    try {
        majPv();
    } catch (Exception e) {
        System.out.println("majPv()");
    }
}

@FXML
public void majPv() throws IOException{
    int pv = super.getUser1().getPv();
    if(pv <= 0){
        this.time.arret();
        this.goFin();
    }
    else{
        String maj = "Points de vie :"+String.valueOf(pv);
        this.pv.setText(maj);
    }
}

public boolean motCorrect(String mot) {
    String res = text.getLast().getNom() + " ";
    if(res.equals(mot)){
        if(super.text.getLast().getBonus()){
            user1.setPv( user1.getPv() +10 ); // rend 10 pv au joueur si c'est un mot bonus
        }
        motCorrect++;
        maj_niveau();
        try {
            majPv();
        } catch (Exception e) {
            System.out.println("motCorrect()");
        }
        return true;
    }else{
        user1.setPv(user1.getPv()-5);
        try {
            majPv();
        } catch (Exception e) {
            System.out.println("motCorrect()");
        }
        return false;
    }
}

@FXML
//met a jour la vue avec les nouvelles infos
public void maj_niveau(){
    if(motCorrect%100 == 0){
        niveau++; // tout les 100 mots correct on gagne un niveau
        time.setTime(getVitesse()*10); // met la nouvelle vitesse au timer
    }
    String s = String.format("Niveau: %d                           Mot correct : %d",niveau,motCorrect);
    niveau_vue.setText(s);
}
}


