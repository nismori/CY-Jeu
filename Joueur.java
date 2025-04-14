import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe créant un <b>Joueur</b> avec un nom unique, un score positif ou nul et des coordonnées dans un <b>Niveau</b>
 * @author Victor
 */
public final class Joueur{
    private final String name; 
    private int score;
    private static int N = 0;
    private int x = -1;;
    private int y = -1;
    private int default_x = -1;
    private int default_y = -1;
    private int life = 3;
    
    /**
     * Créé un <b>Joueur</b> avec un nom et un score
     * @param nm Nouveau nom
     */
    public Joueur(String nm){
        this.name = nm;
        this.score = 0;
        N++;
    }


    /**
     * On créé un nouveau Joueur en utilisant le compteur de Joueur N pour le nommer
     */
    public Joueur(){
        this("Joueur "+ (N+1));
    }


    /**
     * Récupère un caractère que l'utilisateur a saisi
     * @throws IOException En cas de problème de lecture du caractère
     * @return Ce qui est renvoyé sous la forme d'un caractère majuscule
     */
    public String getJoueur() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Entrez un nom : ");
        String input = reader.readLine().trim().toUpperCase();
        return input.isEmpty() ? "" : input;
    }


    /**
     * Ce programme renvoie le nombre de Joueur
     * @return Renvoie le compteur
     */
    public static int getN() {
        return N;
    }


    /**
     * Ce programme renvoie le nom
     * @return Renvoie le nom
     */
    public String getName(){
        return this.name;
    }


    /**
     * Ce programme renvoie le score
     * @return Renvoie le score
     */
    public int getScore(){
        return this.score;
    }


        /**
     * Rajoute les coordonnées du Joueur dans le niveau
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public void addCoordonnees(int x, int y){
        if(x>=0)
            this.x = x;
        if(y>=0)
            this.y = y;
    }


    /**
     * Récupère la valeur de x du Joueur et la renvoie
     * @return Valeur de x
     */
    public int getX(){
        return this.x;
    }


    /**
     * Met la coordonnee x au Joueur
     * @param x Coordonnee x
     */
    public void setX(int x){
        this.x = x;
    }


    /**
     * Récupère la valeur de y du Joueur et la renvoie
     * @return Valeur de y
     */
    public int getY(){
        return this.y;
    }

    /**
     * Met la coordonnée y au Joueur
     * @param y Coordonnée y
     */
    public void setY(int y){
        this.y = y;
    }


    /**
     * Récupère la valeur de default_x du Joueur et la renvoie
     * @return Valeur de default_x
     */
    public int getDefaultX(){
        return this.default_x;
    }


    /**
     * Récupère la valeur de default_y du Joueur et la renvoie 
     * @return Valeur de default_y
     */
    public int getDefaultY(){
        return this.default_y;
    }


    /**
     * Met les coordonnées x et y par défaut au Joueur
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public void setDefaultXandY(int x, int y){
        this.default_x = x;
        this.default_y = y;
    }


    /** 
     * Ajouter newScore au score du Joueur puis vérifie si ce dernier est positif ou nul
     * @param newScore Score à ajouter ou retirer du score du Joueur
     */
    public void addScore(int newScore){
        this.score += newScore;
        if(this.score < 0){
            this.score = 0;
        }
    }

    /**
     * Met le score du Joueur à 0
     */
    public void resetScore(){
        this.score = 0;
    }


    /**
     * Récupère le nombre de vie du Joueur et la renvoie
     * @return Nombre de vies
     */
    public int getLife(){
        return this.life;
    }


    /**
     * Retire x vie(s) au Joueur
     * @param x Nombre de vies 
     */
    public void removeLife(int x){
        this.life -= x;
    }


    /**
     * Met x vie(s) au Joueur
     * @param x Nombre de vies
     */
    public void setLife(int x){
        this.life = x;
    }


    /**
     * Affiche le nom, le score, le nombre de vie(s) et les coordonnées x et y sous la forme nom : <i>score</i> pts ; Nombre de vies : <i>life</i> vie(s) Coordonnees : <i>(x,y)</i> 
     */
    public String toString(){
       String point = (this.score > 1) ? " pts" : " pt";
       return (this.getName() + " : " + this.getScore() + point + " ; Nombre de vies : " + this.getLife() + " vie(s) ; Coordonnees : (" + this.x + "," + this.y + ")");
    }


    /**
     * Vérifie l'égalité entre deux Joueur en vérifiant si les deux noms sont identiques
     * @param joueur Joueur à comparer
     * @return true si les noms des Joueurs sont identiques, false sinon
     */
    public boolean equals(Object joueur){
        if(joueur instanceof Joueur){
            Joueur j = (Joueur) joueur;
            if(this.getName().equalsIgnoreCase(j.getName())){
                return true;
            }
        }
        return false;
    }
}