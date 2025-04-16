/**
 * Cette classe représente une case d'un Niveau.
 */
public class Cellule {
    private char caractere;
    private char joueur = ' ';

    /**
     * Constructeur de la classe Cellule.
     * @param x le caractère représentant la cellule (pièce, mur, vide ou piège)
     */
    public Cellule(char x){
        switch(x){
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
}   
