import java.io.IOException;

/**
 * Programme créant un <b>niveau</b> aleatoirement compose de '#' et de ' '
 * @author Victor
 */
public final class Niveau {
    private char[][] niveau;
    private Joueur joueur;

    /**
     * Assigner les valeurs '#' et ' ' au tableau
     */
    public Niveau(){
        this.niveau = new char[10][20];
        for(int j=0; j<niveau[0].length; j++){
            this.niveau[0][j] = '#';
            this.niveau[niveau.length-1][j] = '#';
        }
        for(int i=0; i<niveau.length; i++){
            this.niveau[i][0] = '#';
            this.niveau[i][niveau[0].length-1]= '#';
        }
        for(int i=1; i<niveau.length-1d; i++){   
            for(int j=1; j<niveau[0].length-1; j++){
                this.niveau[i][j] = ' ';
            }
        }
    }


    /**
     * Vérifie si le joueur '1' peut être placé
     * @return True si c'est le cas
     */
    public boolean isPlayer(int x, int y){
        if((x>=0 && x<this.niveau.length) && (y>=0 && y<this.niveau[0].length)){
            if(this.niveau[x][y] != '#'){
                return true;
            }
        }
        return false;
    }


    /**
     * On vérifie si le joueur peut être placé, puis on le place dans le Niveau 
     * @param j Joueur provenant de la classe joueur
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public void addPlayer(Joueur j, int x, int y) throws PlayerNotHereException {
        if(!isPlayer(x,y))
            throw new PlayerNotHereException("Le joueur n'est pas dans la carte ou est à un endroit inapproprié");
        j.addCoordonnées(x,y);
        this.niveau[x][y] = '1';
        this.joueur = j;
    }


    public void setCoordonnées(int x, int y) throws PlayerNotHereException {
        if(!isPlayer(x,y))
            throw new PlayerNotHereException("Le joueur n'est pas dans la carte ou est à un endroit inapproprié");
        this.niveau[x][y] = '1';
        this.joueur.addCoordonnées(x,y);
    }

    public void setClearPlayer(int x, int y){
        this.niveau[x][y] = ' ';
    }

    /**
     * Classe de l'exeption PlayerNotHere
     */
    class PlayerNotHereException extends RuntimeException {
        public PlayerNotHereException(String message) {
            super(message);
        }
    }

    public char getCaractere() throws IOException{
        System.out.print("Entrez un caractere : ");
        int charCode = System.in.read();
        return Character.toUpperCase((char) charCode);
    }

    public void setMovement() throws IOException{
        char caractere = getCaractere();
        while((caractere != 'Z' && caractere != 'Q') && (caractere != 'S' && caractere != 'D')){
            if(caractere != '\n')
                System.out.println("Votre caractère entré, " + caractere + ", n'est pas un des caractères de déplacement Z/Q/S/D.");
            caractere = getCaractere();
        }
        setMovement(caractere);
    }


    public void setMovement(char commande){
        switch(commande){
        case 'Z':
            this.setClearPlayer(joueur.getX(),joueur.getY());
            this.setCoordonnées(joueur.getX()-1,joueur.getY());
            break;
        case 'Q':
            this.setClearPlayer(joueur.getX(),joueur.getY());
            this.setCoordonnées(joueur.getX(),joueur.getY()-1);
            break;
        case 'S':
            this.setClearPlayer(joueur.getX(),joueur.getY());
            this.setCoordonnées(joueur.getX()+1,joueur.getY());
            break;
        case 'D':
            this.setClearPlayer(joueur.getX(),joueur.getY());
            this.setCoordonnées(joueur.getX(),joueur.getY()+1);
            break;
        default:
            break;
        }
    }

    /**
     * Affiche le tableau
     */
    public String toString(){
        String tab = "";
        for(int i=0; i<niveau.length-1; i++){
            for(int j=0; j<this.niveau[0].length; j++){
                tab += niveau[i][j];
            }
            tab += '\n';
        }
        for(int j=0; j<niveau[0].length; j++)
            tab += niveau[0][j];
        return tab;
    }


    /**
     * Permet de récupérer le niveau d'un objet niveau
     * @param niveau Un niveau
     * @return Le tableau du niveau
     */
    public char[][] getTab(Niveau niveau){
        return this.niveau;
    }


    /**
     * Vérifie que deux niveaux sont égaux en vérifiant s'ils ont le meme objet et tableau 
     * @param niv Un niveau
     */
    public boolean equals(Object niv){
        if(niv instanceof Niveau){
            Niveau n = (Niveau) niv;
            char[][] tab = getTab(n);
            if((this.niveau.length == tab.length) && (this.niveau[0].length == tab[0].length)){
                for(int i=0; i<tab.length; i++){
                    for(int j=0; j<tab[0].length; j++){
                        if(this.niveau[i][j] != tab[i][j]){
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}