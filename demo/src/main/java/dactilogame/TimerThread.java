package dactilogame;

public class TimerThread extends Thread{
    private double time;
    public Solo solo;
    protected volatile boolean running = true;
    public double timeLevel;
    private double TempsTotal=0;

    public TimerThread(){}

    public void run(){
        while(running){
            try{
                sleep(100);
                time--;  // décrémente le temps comme une bombe pour rajouter un mot à 0
                TempsTotal++;
                //affichage du timer en temps réel dans la vue 
                this.solo.timer.setText(String.valueOf(this.time));
                if(solo.getUser1().getPv()<=0){
                    System.out.println("perdu");
                    arret();
                    // afficher fin.fxml
                }
                //solo.setTimer(Double.toString(time));

                if(time<=0){  // tout les n temps on ajoute le mot, et on recommence le chrono
                    ajouterMot();
                    this.time=timeLevel; 
                }

            }catch (InterruptedException e){
                e.printStackTrace();
            } 
        }
    }

    //Arrêt du ThreadTimer courant
    public void arret() { 
        running = false;
    }

    // Juste pour avoir acces au timer de la vue
    public void setSolo(Solo s){
        this.solo = s;
    }

    public double getTime(){
        return  time;
    }

    public void setTime(double time){
        timeLevel = time;
        this.time = time;
    }


    public void ajouterMot(){
        solo.ajouterMot(); // appel ajouter mot de Solo.java
    }

    public double getTempsTotal(){
        return TempsTotal;
    }
}
