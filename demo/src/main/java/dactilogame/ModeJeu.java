package dactilogame;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class ModeJeu extends Jeu{

    public User user1;
    public Multi multi;
    public Solo solo;
    public int motCorrect=0;
    

    

    @FXML
    protected TextField myText; // emplacement où on écris

    @FXML
    protected TextArea textVue;  // mot à écrire

    @FXML
    protected Button bouton_start;

    @FXML
    private TextField curseur;
    private int emplacementCurseur=0;

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user){
        this.user1 = user;
    }
    
    public ModeJeu(){
        super();
        super.modejeu = this;
    }

    public int getCurseur(){
        return emplacementCurseur;
    }

    @FXML
    public void setCurseur(int newCurseur){
        if(newCurseur==0){
            this.curseur.setText("Y");
            this.emplacementCurseur = 0;
        }else{
            this.emplacementCurseur = newCurseur;
            String res="";
            for(int i=0;i<emplacementCurseur;i++){
                res+=" ";
            } 
            res+="Y";
            curseur.setText(res);
        }
    }


    @FXML
    void effacer(KeyEvent event) {
        if(text.getLength()>0){
            if(event.getCode() == KeyCode.BACK_SPACE && getCurseur() != 0){ // si on appuye sur backspace
                setCurseur(getCurseur()-1);
            }
        }  
    }

    @FXML
    private void goMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    void key_typed(KeyEvent event){
        char tmp = event.getCharacter().charAt(0);
        //Verification des caractères
        if((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z') || tmp == ' '){  // on verifie que tmp est bien un cractere est pas un code (ex : BACK_SPACE , ALT, etc) sauf SPACE
            verifTouche(tmp);
        }
    }

    public void verifTouche(char tmp){
        //System.out.print("Vous avez tapé la touche : " + tmp);
        //Si on termine un mot...
        if(tmp == ' '){
            finMot(); 
        }
        else if(getCurseur() >= super.text.getLast().getNom().length()){ // Si on a écris qql chose de plus gros que le mot
            //System.out.println(", mauvaise réponse");
            user1.toucheFausse();
            setCurseur(getCurseur()+1);
        }
        else if(tmp == super.text.getLast().getNom().charAt(getCurseur())){
            //System.out.println(", bonne réponse !");
            setCurseur(getCurseur()+1);
            user1.incrCar(); // on incremente le nbre de caractere utile
            user1.majReg(solo.time.getTempsTotal());
        }
        else{
           //System.out.println(", mauvaise réponse");
           user1.toucheFausse();
           setCurseur(getCurseur()+1);
       }

       try { // on met a jour les pv dans la vu au cas où l'on est perdu des pv a cause d'une mauvaise touche
        solo.majPv();
    } catch (Exception e) {
        System.out.println("verifTouche()");
    }
    }

    public void finMot(){
        if(super.text.getLength()>0){
            System.out.println("FIN DE MOT.");
            //Affiche dans le terminal le dernier mot tapé par le user
            System.out.println(myText.getText());
            //On vérifie que le mot est correcte
            motCorrect(myText.getText());
            //Pop le plus haut mot
            textVue.deleteText(0, super.text.getLast().getNom().length() +1);
            //System.out.println(super.text.getLast().getNom());
            super.text.RemoveLast();
            //Nettoyage de la zone d'ecriture
            myText.clear();
            //Remise du curseur à zero
            setCurseur(0);
        }
    }

    //Si oui ou non le mot tapé par le user est valide
    public boolean motCorrect(String mot) {
        if(solo!=null){
            solo.motCorrect(mot);
        }else{
            multi.motCorrect(mot);
        }

        String res = super.text.getLast().getNom() + " ";
        if(res.equals(mot)){
            //System.out.println("Correct !");
            return true;
        }else{
            //System.out.println("Incorrect");
            return false;
        }
    }

    
    @FXML
    void bouton_start_action(ActionEvent event) {
        for(int i=super.text.getLength()-1; i > 0 ; i--){ // Met dans la vue le text à écrire (du dernier au premier comme c'est une pile)
            textVue.appendText(super.text.getElement(i).getNom()+"\n"); 
        }
        bouton_start.setDisable(true);
        myText.setDisable(false);;
    }

    @FXML
    void text_vue_maj(){
        this.textVue.setText(""); //reset la vue
        for(int i=super.text.getLength()-1; i > 0 ; i--){
            if(text.getElement(i).getBonus()){
                Text mot = new Text(super.text.getElement(i).getNom()+"\n");
                mot.setFill(Color.GREEN);
                textVue.appendText(mot.getText());
            }else{
                textVue.appendText(super.text.getElement(i).getNom()+"\n");
            }
        }
    }

    //retourne la vitesse (MPM)
    public double MPM(User user){
        double res= (user.getCharacterUtile() / (solo.time.getTempsTotal() * 600 )) / 5; // *600 car on veut des minutes et gettemps renvoie en 0,1s
        return res;
    }

    public double Precision(User user){
        return (user.getCharacterUtile() / user.getAppuyeTouche()) *100;
    }

    public double Regularite(User user){
        return user.getRegularite();
    }

}