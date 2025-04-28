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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Entrez un nom de joueur : ");
        String name = reader.readLine().trim();
        Joueur j = new Joueur(name);
        Ennemi e = new Ennemi("Everybody wants to be my ennemy",0,0);
        for(int i=1;i<6;i++){
            Niveau n = new Niveau("Niveau" + i + ".txt", j, e, 1 , 1);
            i = n.loadGame(i);
            if(n.getJoueur().getLife() != 0 && i==5){
                System.out.println(n);
                System.out.println(n.getJoueur());
                System.out.println("Merci d'avoir pris le temps de jouer à cette masterclass.");
            }
        }
    }
}