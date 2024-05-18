package dactilogame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu"),1000,800);
        stage.setScene(scene);
        stage.show();
        System.out.println(Thread.currentThread().getName());
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        /* Pour plus tard... 
        User user = new User("alexis");

        LinkedList<String> list = new LinkedList<>();
        list.add("test");
        list.add("test");
        list.add("test");
        list.add("test");
        list.add("test");
        Texte txt1 = new Texte(list);

        ModeJeu modeJeu = new Solo();
        Jeu jeu = new Jeu(user, txt1, modeJeu);
        */
        launch();
    }

}