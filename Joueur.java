

/**
 * Programme créant un <b>joueur</b> avec un nom unique, un score positif ou nul et des coordonnées dans un niveau
 * @author Victor
 */

public final class Joueur{
    private final String name; 
    private int score;
    private static int N = 0;
    private int x = -1;
    private int y = -1;
    /**
     * Créé un <b>joueur</b> avec un nom et un score
     * @param nm Nouveau nom
     */
    public Joueur(String nm){
        this.name = nm;
        this.score = 0;
        N++;
    }


    /**
     * On créé un nouveau joueur en utilisant le compteur de joueur N pour le nommer
     */
    public Joueur(){
        this("Joueur "+ (N+1));
    }


    /**
     * Affiche le nom et le score du joueur 
     */
    public void show(){
        System.out.println(this.name);
        System.out.println(this.score);
    }


    /**
     * Ce programme renvoie le nombre de joueur
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
     * Affiche le <i>nom</i>, le <i>score</i> et les coordonnées <i>x</i> et <i>y</i> sous la forme <i>nom</i> : <i>score</i> pts ; Coordonnees : (<i>x</i>,<i>y</i>) 
     */
    public String toString(){
       String point = (this.score > 1) ? " pts" : " pt";
       return (this.getName() + " : " + this.getScore() + point + " ; Coordonnees : (" + this.x + "," + this.y + ")");
    }


    /**
     * Vérifie l'égalité entre deux joueurs en vérifiant si les deux noms sont identiques
     * @param joueur Joueur à comparer
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

    /**
     * Rajoute les coordonnées du joueur dans le niveau
     */
    public void addCoordonnées(int x, int y){
        if(x>=0)
            this.x = x;
        if(y>=0)
            this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    /** 
     * Ajouter <i>newScore</i> au score du joueur puis vérifie si ce dernier est positif ou nul
     * @param newScore Score à ajouter ou retirer du score du joueur
     */
    public void setScore(int newScore){
        this.score += newScore;
        if(this.score < 0){
            this.score = 0;
        }
    }
}