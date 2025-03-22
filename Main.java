package Jeu;

public class Main {
    public static void main(String[] args){
        Joueur j1 = new Joueur("Alice");
        Joueur j2 = new Joueur("Bob");
        Joueur j3 = new Joueur("BOB");
        Joueur b = j2;
        for(int i = 100; i<=1000; i+=100){
            j1.setScore(i);
        }
        j2.setScore(-100);
        //j1.show();
        //j2.show();
        //System.out.println(j1);
        System.out.println("La joueuse Alice n'est pas égale à la chaîne 'Alice' : "+j1.equals("Alice"));
        System.out.println("La joueuse Alice n'est pas égale au joueur Bob : "+j1.equals(j2));
        System.out.println("Le joueur Bob est égal à un nouveau joueur BOB : "+j2.equals(j3));
        System.out.print("Le joueur Bob n'est pas == au joueur BOB : ");
        System.out.println(j2==j3);
        System.out.print("Le joueur Bob est == à b où b est une nouvelle référence au joueur Bob : ");
        System.out.println(j2==b);
        Joueur j4 = new Joueur();
        System.out.println(j4); //test de toString pour Joueur
        System.out.println("Nombre de joueurs = "+Joueur.getN());
        Niveau n1 = new Niveau();
        String a = n1.toString();
        System.out.println("Egalite entre chaine et Object Niveau : "+n1.equals(a));
        Niveau n2 = new Niveau();
        System.out.println("Egalite entre deux niveaux identiques : "+n2.equals(n1));
        n1.addPlayer(j1,1,1);
        System.out.println(n1); //test de toString pour Niveau
        System.out.println(j1); //test de toString pour le Joueur dans le Niveau
    }
}