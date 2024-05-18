Dactylo-game

Dactylogame est un jeu de frappe de mots amusant et stimulant dans lequel les joueurs doivent taper le mot affiché à l'écran aussi rapidement et précisément que possible.

Comment lancer le jeu
    - placer vous dans demo depuis votre terminale
    - lancer mvn compile , mvn package , java -jar target/demo-0.1.0.jar
    - Si ca ne marche pas on peut aussi le compiler grace a VsCode et l'extension runCode de celui-ci, grace au bouton en haut à droite ("run java")

Comment jouer

    - Lorsque vous allez executer le jeu, une page "Menu" apparaitra où il faudra choisir entre le Solo ou le Multi-joueur
    - En cliquant sur Solo , vous serez redirigé sur ce mode de jeu.
    - Appuyez sur Start pour lancer une partie, vous verrez une liste de mots apparaitre à l'écran. Le mot le plus haut est le mot courant que vous devez taper le plus rapidement et precisement possible.
    - la touche SPACE du clavier sert à valider (ou non) le mot courant
    - Le bouton "Exit" en bas de la page sert à quitter le jeu
    - On peut revenir en arrière si on a fait une faute en appuyant sur "Back_Slash" sur le clavier ce qui nous fera perdre des points
    - La partie continue tant que le nombre de point de vie n'est pas ecoulé
    - A la fin une page de statistiques s'affichera et nous donnera notre vitesse / precision / regularite
    - Le mode multijoueur n'est pas fonctionnel.

Règles

    Solo :
    - On débute le jeu avec 100 points de vie
    - Si vous tapez un mot incorrectement, vous perdrez 2 vies, si vous arrivez a 0 points de vie, le jeu s'arrête.
    - Si vous validez un mot incorrect vous perdrez 5 vies.
    - vous avez un chronomètre qui rajoute un mot à chaque fois qu'il a fini de s'écouler.
    - Les mots bonus vous rendent 10 pv.


Problèmes

    - Mode multi pas fini
    - Les mots bonus (couleur ne sont pas coloré, pourtant il y a bien un settFill avec la couleur sur le mot avant de l'ajouter)
    - Le code peut envoyer des avertissement "Thread in javafx" de facon "aléatoire" on ne sais pas trop pourquoi ni comment
    - les stats de fin sont bizzare (précision est affiché comme infini alors que je fais juste le nbre de caractère utile divisé par
    le nbre de touche appuyé , le tout multiplié par 100 pour avoir un pourcentage).