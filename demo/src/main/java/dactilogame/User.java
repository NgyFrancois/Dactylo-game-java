package dactilogame;

import java.net.Socket;

public class User {
    public String name;
    private int pv; //Points de vie de l'utilisateur
    private double caractèresUtile=0;
    private double appuyeTouche=0;
    private double regularite=0;
    private double tempsTmp=0;

    public User(String name){
        this.name = name;
        this.pv = 100;
    }

    public User(Socket socketClient) {
    }

    public void setPv(int pv){
        this.pv = pv;
        //System.out.println("Votre nombre de PV est maintenant de " + this.getPv());
    }

    public int getPv(){
        return this.pv;
    }

    public void toucheFausse(){
        this.setPv(this.getPv()-2);
    }


    public double getCharacterUtile(){
        return caractèresUtile;
    }

    public void incrCar(){
        caractèresUtile++;
    }

    public void incrTouche(){
        appuyeTouche++;
    }

    public double getAppuyeTouche(){
        return appuyeTouche;
    }

    public void majReg(double d){
        if(regularite == 0){
            regularite = d;
            tempsTmp = d;
        }else{
            regularite = (regularite + (d-tempsTmp)) / 2; // d-tempsTmp car on veut juste le temps entre les 2 touches
            tempsTmp = d;
        }
    }

    // *10 pour avoir la regularité en seconde
    public double getRegularite(){
        return regularite*10;
    }

    public String recevoirMessage() {
        return null;
    }
}
