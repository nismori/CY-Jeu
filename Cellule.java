/**
 * Cette classe représente une case d'un Niveau.
 */
public class Cellule {
    private char caractere;
    private char joueur = ' ';
    private int x;
    private int y;
    private Cellule VGauche;
    private Cellule VDroit;
    private Cellule VHaut;
    private Cellule VBas;

    /**
     * Constructeur de la classe Cellule.
     * @param c le caractère représentant la cellule (pièce, mur, vide ou piège)
     * @param x position x de la cellule dans le tableau
     * @param y position y de la cellule dans le tableau
     */
    public Cellule(char c, int x, int y){
        this.x = x;
        this.y = y;
        switch(c){
            case(' '):
                caractere = ' ';
                break;
            case('*'):
                caractere = '*';
                break;
            case('.'):
                caractere = '.';
                break;
            case('#'):
                caractere = '#';
                break;
            default:
                caractere = ' ';
                break;
        }
    }

    /**
     * Constructeur des 4 voisins d'une classe Cellule (Gauche,Droit,Haut,Bas)
     * @param niveau Un tableau contenant pour chaque case une classe cellule
     */
    public void setVoisins(Cellule[][] niveau){
        //Vgauche = x-1 => x>=0 this.VGauche = niveau[x-1][y]
        //Vdroit = x+1 => x<niveau[0].length
        //Vhaut = y-1 => y>=0
        //Vbas = y+1 => y<niveau.length
        if(x-1 >= 0)
            this.VGauche = niveau[y][x-1];
        else
            this.VGauche = niveau[y][niveau[0].length-1];

        if(x+1 < niveau[0].length)
            this.VDroit = niveau[y][x+1];
        else
            this.VDroit = niveau[y][0];

        if(y-1 >= 0)
            this.VHaut = niveau[y-1][x];
        else
            this.VHaut = niveau[niveau.length-1][x];

        if(y+1 < niveau.length)
            this.VBas = niveau[y+1][x];
        else
            this.VBas = niveau[0][x];
    }


    /**
     * Cette méthode retourne le caractère représentant la cellule.
     * @return le caractère représentant la cellule
     */
    public char getValue(){
        return this.caractere;
    }


    /**
     * Cette méthode permet de modifier le caractère représentant la cellule.
     * @param caractere le nouveau caractère représentant la cellule
     */
    public void setValue(char caractere){
        this.caractere = caractere;
    }


    /**
     * Cette méthode récupère le caractère représentant le joueur.
     * @return le caractère représentant le joueur
     */
    public char getPlayer(){
        return this.joueur;
    }


    /**
     * Cette méthode permet d'ajouter le joueur à la case.
     */
    public void setPlayer(){
        this.joueur = '1';
    }


    /**
     * Cette méthode permet de supprimer le joueur de la case.
     */
    public void clearPlayer(){
        this.joueur = ' ';
    }


    /**
     * Cette méthode permet de récupérer la case voisine gauche de la cellule
     * @return case voisine gauche de la cellule
     */
    public Cellule getVoisinGauche(){
        return this.VGauche;
    }
    

    /**
     * Cette méthode permet de récupérer la case voisine droite de la cellule
     * @return case voisine droite de la cellule
     */
    public Cellule getVoisinDroit(){
        return this.VDroit;
    }


    /**
     * Cette méthode permet de récupérer la case voisine haute de la cellule
     * @return case voisine haute de la cellule
     */
    public Cellule getVoisinHaut(){
        return this.VHaut;
    }


    /**
     * Cette méthode permet de récupérer la case voisine basse de la cellule
     * @return case voisine basse de la cellule
     */
    public Cellule getVoisinBas(){
        return this.VBas;
    }


    /**
     * Cette méthode permet de récupérer la position x de la cellule dans le tableau
     * @return position x de la cellule dans le tableau
     */
    public int getX(){
        return this.x;
    }


    /**
     * Cette méthode permet de récupérer la position y de la cellule dans le tableau
     * @return position y de la cellule dans le tableau
     */
    public int getY(){
        return this.y;
    }


    /**
     * Affiche le caractère contenu par la cellule
     */
    public String toString(){
        return String.valueOf(caractere);
    }


    /**
     * Vérifie l'égalité entre deux Cellules
     * @return Les deux cellules sont égales si elles ont les mêmes voisines et sont au même endroit du tableau
     */
    public boolean equals(Object c){
        if(c instanceof Cellule){
            Cellule cellule = (Cellule) c;
            if((this.getX() == cellule.getX()) && (this.getY() == cellule.getY()) && (this.getVoisinBas() == cellule.getVoisinBas()) && (this.getVoisinDroit() == cellule.getVoisinDroit()) && (this.getVoisinGauche() == cellule.getVoisinGauche()) && (this.getVoisinHaut() == cellule.getVoisinHaut()) && (this.getValue() == cellule.getValue()) && (this.getPlayer() == cellule.getPlayer())) //Tous les arguments de la cellule
                return true;
        }
        return false;
    }
}