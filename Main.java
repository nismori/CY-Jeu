import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Classe principale Main
 */
public class Main {
    /**
     * Méthode pour afficher les commandes demandés dans les anciens niveaux
     */
    public static void oldCommands(){
        Joueur j1 = new Joueur("Alice");
        Joueur j2 = new Joueur("Bob");
        Joueur j3 = new Joueur("BOB");
        Joueur b = j2;
        Joueur j4 = new Joueur();

        Niveau n1 = new Niveau();
        n1.addPlayer(j1, 1, 1); 
        Niveau n2 = new Niveau();
        Niveau n3 = new Niveau();
        System.out.println();
        System.out.println(j1);
        System.out.println("La joueuse Alice n'est pas égale à la chaîne 'Alice' : "+j1.equals("Alice"));
        System.out.println("La joueuse Alice n'est pas égale au joueur Bob : "+j1.equals(j2));
        System.out.println("Le joueur Bob est égal à un nouveau joueur BOB : "+j2.equals(j3));
        System.out.print("Le joueur Bob n'est pas == au joueur BOB : ");
        System.out.println(j2==j3);
        System.out.print("Le joueur Bob est == à b où b est une nouvelle référence au joueur Bob : ");
        System.out.println(j2==b);
        System.out.println(j4); //test de toString pour Joueur
        System.out.println("Nombre de joueurs = "+Joueur.getN());
        String a = n1.toString();
        System.out.println("Egalite entre chaine et Object Niveau : "+n1.equals(a));
        System.out.println("Egalite entre deux niveaux identiques : "+n2.equals(n1));
        System.out.println(n1.getJoueur());
        System.out.println(n1);
        n3.saveFile("/root/testIOException.txt");  
        System.out.println();
    }

    /**
     * Méthode pour lancer le jeu
     * @param args Argument éventuellement passé en paramètre *
     * @throws IOException En cas de problème de lecture
     */
    public static void main(String[] args) throws IOException{
        String fileName = null;
        Niveau n = new Niveau();

        //oldCommands(j1,j2,j3,b,j4,n1,n2,n3);

        if(args.length != 0){
            fileName = args[0];
            n.loadFile(fileName);
            Deplacement d = new Deplacement(n);
            d.Movement(1000,fileName);
            System.out.println("ERROR ou vous êtes trèèèèèèès nul");
            System.exit(0);
        }
        else{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Entrez un nom de joueur : ");
            String name = reader.readLine().trim();
            Joueur j = new Joueur(name);
            n.loadGame(j);
        }
    }
}