import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

/**
 * Classe permettant de déplacer le <b>Joueur</b> dans le <b>Niveau</b>
 * @author Victor
 */
public class Deplacement {
    private final Niveau n;
    private final List<Ennemi> tabEnnemis;
    private final Joueur j;
    private int x = -1;
    private int y = -1;

    /**
     * Le déplacement est créé en fonction des limites du Niveau.
     * @param niveau Récupère le Niveau avec son Joueur et ses coordonnées
     * Puis on créé x et y pour gagner du temps lors de leurs appels dans les fonctions qui viennent
     */
    public Deplacement(Niveau niveau){
        this.n = niveau;
        this.tabEnnemis = niveau.getTabEnnemis();
        this.j = niveau.getJoueur();
    }
    

    /**
     * Permet de modifier les coordonnées et l'affichage du <b>Joueur</b> dans le <b>Niveau</b>
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public void setJoueurCoordonnees(int x, int y){
        this.n.getNiveau()[y][x].setJoueur();
        this.j.addCoordonnees(x,y);
        this.x = x;
        this.y = y;
    }


    /**
     * Permet de modifier les coordonnées et l'affichage du <b>Joueur</b> dans le <b>Niveau</b>
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public void setEnnemiCoordonnees(int x, int y, Ennemi e){
        this.n.getNiveau()[y][x].setEnnemi();
        e.addCoordonnees(x,y);
        this.x = x;
        this.y = y;
    }


    /**
     * Efface le Joueur de la coordonnée
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public void setClearJoueur(int x, int y){
        this.n.getNiveau()[y][x].clearJoueur();
    }


    /**
     * Récupère un caractère que l'utilisateur a saisi
     * @throws IOException En cas de problème de lecture du caractère
     * @return Ce qui est renvoyé sous la forme d'un caractère majuscule
     */
    public char getCaractere() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Entrez un caractère (Z/Q/S/D) : ");
        String input = reader.readLine().trim().toUpperCase();
        return input.isEmpty() ? '\0' : input.charAt(0);
    }


    /**
     * Récupère un caractère avec <i>getCaractere</i> et l'envoie à Movement(char commande) qui va l'interpréter
     * @throws IOException En cas de problème de lecture du caractère
     */
    public void joueurMovement() throws IOException{
        char caractere = getCaractere();
        while((caractere != 'Z' && caractere != 'Q') && (caractere != 'S' && caractere != 'D')){
            if(caractere != '\n')
                System.out.println("Votre caractère entré, '" + caractere + "' , n'est pas un des caractères de déplacement Z/Q/S/D.");
            caractere = getCaractere();
        }
        joueurMovement(caractere);
    }


    /**
     * On envoie le joueur sur la coordonnée de la cellule voisine en fonction de la commande du joueur (Z/Q/S/D) et on le supprime de son ancienne position
     * On récupère la pièce sur la case où se déplace le Joueur
     * On renvoie le Joueur à sa position par défaut s'il tombe sur un piège, calculée lors du lancement du programme. On lui fait aussi perdre une vie
     * @param commande un caractère parmi {'Z','Q','S','D'}
     */
    public void joueurMovement(char commande){
        int newX; int newY;
        this.x = j.getX();
        this.y = j.getY();
        switch(commande){
            case 'Z' -> {
                newX = this.n.getNiveau()[y][x].getVoisinHaut().getX();
                newY = this.n.getNiveau()[y][x].getVoisinHaut().getY();
                if(this.n.isPlayer(newX,newY)){
                    this.n.getPiece(newX,newY);
                    this.n.getPiege(newX,newY);
                    this.setClearJoueur(x,y);
                    if(this.n.isPiege(newX,newY)){
                        this.setJoueurCoordonnees(this.n.getJoueur().getDefaultX(),this.n.getJoueur().getDefaultY());
                    }
                    else
                        this.setJoueurCoordonnees(newX,newY);
                }
            }
            case 'Q' -> {
                newX = this.n.getNiveau()[y][x].getVoisinGauche().getX();
                newY = this.n.getNiveau()[y][x].getVoisinGauche().getY();
                if(this.n.isPlayer(newX,newY)){
                    this.n.getPiece(newX,newY);
                    this.n.getPiege(newX,newY);
                    this.setClearJoueur(x,y);
                    if(this.n.isPiege(newX,newY)){
                        this.setJoueurCoordonnees(this.n.getJoueur().getDefaultX(),this.n.getJoueur().getDefaultY());
                    }
                    else
                        this.setJoueurCoordonnees(newX,newY);
                }
            }
            case 'S' -> {
                newX = this.n.getNiveau()[y][x].getVoisinBas().getX();
                newY = this.n.getNiveau()[y][x].getVoisinBas().getY();
                if(this.n.isPlayer(newX,newY)){
                    this.n.getPiece(newX,newY);
                    this.n.getPiege(newX,newY);
                    this.setClearJoueur(x,y);
                    if(this.n.isPiege(newX,newY)){
                        this.setJoueurCoordonnees(this.n.getJoueur().getDefaultX(),this.n.getJoueur().getDefaultY());
                    }
                    else
                        this.setJoueurCoordonnees(newX,newY);
                }
            }
            case 'D' -> {
                newX = this.n.getNiveau()[y][x].getVoisinDroit().getX();
                newY = this.n.getNiveau()[y][x].getVoisinDroit().getY();
                if(this.n.isPlayer(newX,newY)){
                    this.n.getPiece(newX,newY);
                    this.n.getPiege(newX,newY);
                    this.setClearJoueur(x,y);
                    if(this.n.isPiege(newX,newY)){
                        this.setJoueurCoordonnees(this.n.getJoueur().getDefaultX(),this.n.getJoueur().getDefaultY());
                    }
                    else
                        this.setJoueurCoordonnees(newX,newY);
                }
            }
            default -> {
            }
        }
    }


    public void ennemisMovement(){
        for(int i=0; i<tabEnnemis.size(); i++){
            ennemiMovement(tabEnnemis.get(i));
        }
    }
    

    public void ennemiMovement(Ennemi e){
        this.x = e.getX();
        this.y = e.getY();
        Random rand = new Random();
        Integer direction = rand.nextInt(3);
        int newX; int newY;
        switch(direction){
            case 0 -> {
                newX = this.n.getNiveau()[y][x].getVoisinHaut().getX();
                newY = this.n.getNiveau()[y][x].getVoisinHaut().getY();
                if(this.n.isPlayer(newX,newY)){
                    this.n.getPiege(newX,newY);
                    this.setClearEnnemi(x,y);
                    if(this.n.isPiege(newX,newY)){
                        this.setEnnemiCoordonnees(this.n.getJoueur().getDefaultX(),this.n.getJoueur().getDefaultY(),e);
                    }
                    else
                        this.setEnnemiCoordonnees(newX,newY,e);
                }
            }
        }

    }


    /**
     * Permet de déplacer le Joueur x fois dans le Niveau. 
     * @param x Nombre de fois que le joueur se déplace
     * @param fileName Nom du fichier en argument
     * @throws IOException En cas de problème de lecture du caractère
     * Si le niveau n'a plus de pièces ou le joueur de vies, on arrête de déplacer le joueur. C'est loadGame() qui prend la suite.
     */
    public void Movement(int x, String fileName) throws IOException{
        System.out.println(this.n);
        for(int i=0; i<x; i++){
            System.out.println(this.n.getJoueur());
            if(this.n.isFinishPiece()){
                clear();
                break;
            }
            if(this.n.isFinishPiege()){
                break;
            }
            this.joueurMovement();
            this.ennemisMovement();
            clear();
            System.out.println("\n" + this.n);
        }
    }


    /**
     * Efface les commandes précédentes du terminal
     */
    public void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}
