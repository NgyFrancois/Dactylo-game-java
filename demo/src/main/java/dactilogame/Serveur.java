/* 
 * Le serveur doit être capable de recevoir les mots tapés par les différents joueurs,
 * de les comparer aux mots à taper et de déterminer qui a gagné la manche en fonction de la vitesse et de la précision de frappe.
 * Le serveur doit également être capable de envoyer des messages aux différents clients pour leur indiquer qui a gagné la manche et mettre à jour leur score.
 */

package dactilogame;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
  // Port sur lequel le serveur va écouter les connexions des clients
  private static final int PORT = 1234;

  // Liste des utilisateurs connectés au serveur
  private List<User> users;

  public Serveur() {
    users = new ArrayList<>();
  }

  public void demarrer() {
    try {
      try (// Création d'un socket serveur qui va écouter les connexions sur le port spécifié
      ServerSocket socketServeur = new ServerSocket(PORT)) {
        System.out.println("Le serveur est en écoute sur le port " + PORT + "...");

        // Boucle infinie qui attend les connexions des clients et les accepte
        while (true) {
          // Attente d'une connexion d'un client
          Socket socketClient = socketServeur.accept();
          System.out.println("Nouveau client connecté : " + socketClient.getInetAddress().getHostAddress());

          // Création d'un nouvel utilisateur à partir du socket du client
          User user = new User(socketClient);

          // Ajout de l'utilisateur à la liste des utilisateurs connectés
          users.add(user);

          // Lancement d'un thread qui va gérer la communication avec l'utilisateur
          Thread threadCommunication = new Thread(new Runnable() {
            @Override
            public void run() {
              try {
                // Boucle de communication avec l'utilisateur
                while (true) {
                  // Réception d'un message de l'utilisateur
                  String message = user.recevoirMessage();

                  // Traitement du message
                  traiterMessage(user, message);
                }
              } catch (Exception e) {
                // Si une exception est levée, cela signifie que la communication avec l'utilisateur a été interrompue
                // On retire donc l'utilisateur de la liste des utilisateurs connectés
                users.remove(user);
                System.out.println("L'utilisateur " + user + " s'est déconnecté.");
              }
            }
          });
          threadCommunication.start();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Méthode qui va traiter les messages reçus de l'utilisateur
  private void traiterMessage(User user, String message) {
    System.out.println("A implementer");
  }
}
