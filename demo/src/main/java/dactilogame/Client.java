/*
 * Le client doit être capable de recevoir les mots à taper du serveur,
 * de détecter les entrées clavier et de les envoyer au serveur pour être comparées aux mots à taper.
 * Le client doit également être capable de traiter les messages reçus du serveur et de mettre à jour l'interface utilisateur en conséquence
 * (affichage du score, du gagnant de la manche, etc.).
 */

package dactilogame;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  // Adresse IP du serveur
  private static final String SERVEUR_IP = "127.0.0.1";

  // Port sur lequel le serveur écoute les connexions des clients
  private static final int PORT = 1234;

  // Socket de communication avec le serveur
  private Socket socket;

  // Score du client
  private int score;

  public Client() {
    try {
      // Création d'un socket client qui se connecte au serveur sur l'adresse IP et le port spécifiés
      socket = new Socket(SERVEUR_IP, PORT);
      System.out.println("Connexion au serveur réussie.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void demarrer() {
    try {
      // Lancement d'un thread qui va gérer la communication avec le serveur
      Thread threadCommunication = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            // Boucle de communication avec le serveur
            while (true) {
              // Réception d'un message du serveur
              String message = recevoirMessage();

              // Traitement du message
              traiterMessage(message);
            }
          } catch (Exception e) {
            // Si une exception est levée, cela signifie que la communication avec le serveur a été interrompue
            // On affiche un message d'erreur et on arrête le programme
            System.out.println("Erreur de communication avec le serveur : " + e.getMessage());
            System.exit(-1);
          }
        }
      });
      threadCommunication.start();

      try (// Boucle infinie qui attend les entrées clavier du client et les envoie au serveur
      Scanner scanner = new Scanner(System.in)) {
        while (true) {
          // Attente d'une entrée clavier
          String message = scanner.nextLine();

          // Envoi du message au serveur
          envoyerMessage(message);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void envoyerMessage(String message) throws IOException {
    // Envoi du message au serveur via le socket de communication
    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
    writer.println(message);
  }

  public String recevoirMessage() throws IOException {
    try (// Réception du message du serveur via le socket de communication
    Scanner scanner = new Scanner(socket.getInputStream())) {
      return scanner.nextLine();
    }
  }

  private void traiterMessage(String message) {
    System.out.println("");
  }
}
