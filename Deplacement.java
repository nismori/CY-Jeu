import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe permettant de déplacer le <b>joueur</b> dans le <b>niveau</b>
 * @author Victor
 */
public class Deplacement {
    private Niveau n;
    private int x = -1;
    private int y = -1;

    /**
     * Le déplacement est créé en fonction des limites du niveau.
     * @param niveau Récupère le niveau avec son joueur et ses coordonnées
     * Puis on créé x et y pour gagner du temps lors de leurs appels dans les fonctions qui viennent
     */
    public Deplacement(Niveau niveau){
        this.n = niveau;
        this.x = niveau.getJoueur().getX();
        this.y = niveau.getJoueur().getY();
    }
    

    /**
     * Permet de modifier les coordonnées et l'affichage du <b>Joueur</b> dans le <b>Niveau</b>
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public void setCoordonnees(int x, int y){
        this.n.getNiveau(n)[x][y] = '1';
        this.n.getJoueur().addCoordonnees(x,y);
        this.x = this.n.getJoueur().getX();
        this.y = this.n.getJoueur().getY();
    }


    /**
     * Efface le Joueur de la coordonnée
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public void setClearPlayer(int x, int y){
        this.n.getNiveau(n)[x][y] = ' ';
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
     * Récupère un caractère avec <i>getCaractere</i> et l'envoie à Movement qui va l'interpréter
     * @throws IOException En cas de problème de lecture du caractère
     */
    public void Movement() throws IOException{
        char caractere = getCaractere();
        while((caractere != 'Z' && caractere != 'Q') && (caractere != 'S' && caractere != 'D')){
            if(caractere != '\n')
                System.out.println("Votre caractère entré, '" + caractere + "' , n'est pas un des caractères de déplacement Z/Q/S/D.");
            caractere = getCaractere();
        }
        Movement(caractere);
    }


    /**
     * En fonction de commande, fait avancer le Joueur en le supprimant de son ancienne coordonnée et en ajoutant la nouvelle
     * Si le Joueur va dépasser les limites de la carte, on ne le "déplace" pas
     * On récupère la pièce sur la case où on se déplace
     * @param commande un caractère parmi {'Z','Q','S','D'}
     */
    public void Movement(char commande){
        switch(commande){
        case 'Z':
            if(this.n.isPlayer(x-1,y)){
                this.n.getPiece(x-1, y);
                this.n.getPiege(x-1, y);
                this.setClearPlayer(x,y);
                if(this.n.isPiege(x,y))
                    this.setCoordonnees(x-1,y);
            }
            break;
        case 'Q':
            if(this.n.isPlayer(x,y-1)){
                this.n.getPiece(x,y-1);
                this.n.getPiege(x,y-1);
                this.setClearPlayer(x,y);
                if(this.n.isPiege(x,y))
                    this.setCoordonnees(x,y-1);
            }
            break;
        case 'S':
            if(this.n.isPlayer(x+1,y)){
                this.n.getPiece(x+1,y);
                this.n.getPiege(x+1, y);
                this.setClearPlayer(x,y);
                if(this.n.isPiege(x,y))
                    this.setCoordonnees(x+1,y);
            }
            break;
        case 'D':
            if(this.n.isPlayer(x,y+1)){
                this.n.getPiece(x,y+1);
                this.n.getPiege(x,y+1);
                this.setClearPlayer(x,y);
                if(this.n.isPiege(x,y))
                    this.setCoordonnees(x,y+1);
            }
            break;
        default:
            break;
        }
    }


    /**
     * Permet de déplacer le Joueur x fois dans le Niveau. 
     * @param x Nombre de fois que le joueur se déplace
     * @throws IOException En cas de problème de lecture du caractère
     * S'il n'y a plus de pièce, on arrête le jeu
     */
    public void Movement(int x) throws IOException{
        System.out.println(this.n);
        for(int i=0; i<x; i++){
            System.out.println(this.n.getJoueur());
            if(this.n.isFinishPiece()){
                this.n.saveFile("niveau"+this.n.getJoueur().getName()+".txt");
                System.out.println("VICTOIRE");
                System.exit(0);
            }
            if(this.n.isFinishPiege()){
                this.n.saveFile("niveau"+this.n.getJoueur().getName()+".txt");
                System.out.println("GAME OVER");
                System.exit(0);
            }
            this.Movement(); 
            System.out.print("\033[H\033[2J");
            System.out.flush();
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
