import java.io.IOException;

public class Main {
    public static void oldCommands(Joueur j1, Joueur j2, Joueur j3, Joueur b, Joueur j4, Niveau n1, Niveau n2,Niveau n3){
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

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException{
        Joueur j1 = new Joueur("Alice");
        Joueur j2 = new Joueur("Bob");
        Joueur j3 = new Joueur("BOB");
        Joueur b = j2;
        Joueur j4 = new Joueur();

        Niveau n1 = new Niveau();
        n1.addPlayer(j1, 1, 1); 
        Niveau n2 = new Niveau();

        /**
         * Fonction pour afficher les vielles commandes d'ancien niveau
         */
        //oldCommands(j1,j2,j3,b,j4,n1,n2,n3);

        if(args.length != 0){
            String fileName = args[0];
            n2.loadFile(fileName);
            System.out.println(n2);
            System.out.println("Fichier '" + fileName + "' chargé avec succès depuis la commande 'java -jar Main.jar niveauBob.txt'");
            System.out.println();
        }
        else{
            n2.addPlayer(j2, 1, 1);
        }
        Deplacement d = new Deplacement(n2);
        d.Movement(500);
        System.out.println(n2);
        n2.saveFile("niveau"+n2.getJoueur().getName()+".txt");
        System.out.println();
    }
}