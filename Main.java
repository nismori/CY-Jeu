import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Classe principale Main
 */
public class Main {

    /**
     * Méthode pour lancer le jeu
     * @param args Argument éventuellement passé en paramètre *
     * @throws IOException En cas de problème de lecture
     */
    public static void main(String[] args) throws IOException{
        Niveau n = new Niveau();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Entrez un nom de joueur : ");
        String name = reader.readLine().trim();
        Joueur j = new Joueur(name);
        n.loadGame(j);
    }
}