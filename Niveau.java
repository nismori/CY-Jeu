import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Classe créant un <b>Niveau</b> aleatoirement composé de classe Cellule ainsi qu'un <b>Joueur</b> '1' ou non
 */
public final class Niveau {
    private Cellule[][] niveau;
    private Joueur joueur;
    private Ennemi ennemi;
    private int nbPiece = 4;


    /**
     * Charge un niveau seulement à partir d'un fichier. Similaire à loadFile mais sans la notion de vie et de score. On créé un niveau par défaut si le niveau n'est pas trouvé
     * Le programme comble les cases non initialisées par des espaces ' '
     * @param fileName Chemin absolu du fichier
     * @param joueur Joueur à ajouter au niveau
     */
    public Niveau(String fileName, Joueur joueur, Ennemi ennemi, int x, int y){
        Path filePath = Paths.get(fileName);
        this.ennemi = ennemi;
        try {
            List<String> lines = Files.readAllLines(filePath);
            
            if (lines.isEmpty()) {
                throw new IOException();
            }

            int cols = lines.size();
            int max = lines.get(0).length();
            for(int i = 1; i < cols; i++) {
                if (lines.get(i).length() > max)
                    max = lines.get(i).length();       
            }
            int rows = max;
            Cellule[][] n = new Cellule[cols][rows];

            for (int i = 0; i < cols; i++) {
                String line = lines.get(i);
                for(int j = 0; j < rows; j++){
                    if(j<line.length())
                        n[i][j] = new Cellule(line.charAt(j),j,i);
                    else
                        n[i][j] = new Cellule(' ',j,i);
                }
            }

            this.niveau = n;
            this.setVoisins();

            if (this.isNiveauExist()){
                boolean isPiece = false;

                if(this.niveau[y][x].getValue() == '.')
                    isPiece = true;
                this.addPlayer(joueur, y, x);
                this.setCoordonneeWithFile();
                int compteur = this.numberOfPieces();
                if(compteur>0)
                    this.nbPiece = compteur;
                if(isPiece)
                    this.joueur.addScore(1);
            } 
            else{
                throw new IOException();
            }
        }
        catch (IOException e) {
            System.err.println("Le fichier " + fileName + " est vide ou corrompu. Un niveau par défaut a été créé.");
            this.niveau = new Cellule[10][20];
            for(int j=0; j<niveau[0].length; j++){
                this.niveau[0][j] = new Cellule('#',j,0);
                this.niveau[niveau.length-1][j] = new Cellule('#',j,niveau.length-1);
            }
            for(int i=0; i<niveau.length; i++){
                this.niveau[i][0] = new Cellule('#',0,i);
                this.niveau[i][niveau[0].length-1]= new Cellule('#',niveau[0].length-1,i);  
            }
            for(int i=1; i<niveau.length-1; i++){   
                for(int j=1; j<niveau[i].length-1; j++){
                    this.niveau[i][j] = new Cellule(' ',j,i);
                }
            }

            this.setVoisins();

            this.niveau[1][18].setValue('.');
            this.niveau[1][1].setValue('.');
            this.niveau[8][18].setValue('.');
            this.niveau[8][1].setValue('.');

            this.niveau[2][18].setValue('.');
            this.niveau[1][2].setValue('.');
            this.niveau[8][17].setValue('.');
            this.niveau[8][2].setValue('.');

            this.addPlayer(joueur,5,5);
        }
    }

    /**
     * Créé les voisins pour chaque cellule du tableau du Niveau.
     */
    public void setVoisins(){
        for (Cellule[] niveau1 : niveau) {
            for (Cellule niveau11 : niveau1) {
                niveau11.setVoisins(niveau);
            }
        }
    }


    /**
     * Permet de récupérer le Joueur d'un objet Niveau
     * @return L'objet Joueur dans le Niveau en paramètre
     */
    public Joueur getJoueur(){
        return this.joueur;
    }


    /**
     * Permet de récupérer l'Ennemi d'un objet Niveau
     * @return L'objet Ennemi dans le Niveau en paramètre
     */
    public Ennemi getEnnemi(){
        return this.ennemi;
    }
    

    /**
     * Permet de récupérer le Niveau d'un objet niveau. Permet de traiter des cas particuliers comme equals().
     * @return Le tableau de charactère du Niveau
     */
    public Cellule[][] getNiveau(){
        return this.niveau;
    }



    /**
     * Récupère la pièce en l'ajoutant au score, et la décomptant du nombre de pièce total
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public void getPiece(int x, int y){
        if(this.niveau[y][x].getValue() == '.'){
            this.niveau[y][x].setValue(' ');
            this.joueur.addScore(1);
            this.nbPiece--;
        }
    } 


    /**
     * Retire une vie au joueur s'il tombe sur un piège
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public void getPiege(int x, int y){
        if(this.niveau[y][x].getValue() == '*'){
            this.joueur.removeLife(1);
        }
    } 


    /**
     * Vérifie si la case est un piège
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si la case est un piège, false sinon
     */
    public boolean isPiege(int x, int y){
        return this.niveau[y][x].getValue() == '*';
    }

    
    /**
     * Vérifie si le niveau n'a plus de pièce. Si c'est le cas, il écrit VICTOIRE et finis le programme
     * @return S'il n'y a plus de pièce dans le niveau, on renvoie true, sinon on renvoie false
     */
    public boolean isFinishPiece(){
        return this.nbPiece == 0;
    }


    /**
     * Vérifie si le joueur n'a plus de vies. Si c'est le cas, il écrit GAME OVER et finis le programme
     * @return Si le joueur n'a plus de vies, on renvoie true, sinon on renvoie false
     */
    public boolean isFinishPiege(){
        return this.joueur.getLife() == 0;
    }
    


    /**
     * Vérifie si le Joueur '1' peut être placé
     * @param x Coordonnée x
     * @param y Cooronnée y
     * @return true si c'est le cas, false sinon
     */
    public boolean isPlayer(int x, int y){
        return this.niveau[y][x].getValue() != '#' && this.niveau[y][x].getValue() != '\0';
    }


    /**
     * Génère un booléan à partir d'un entier
     * @param x entier
     * @return true si x vaut 0, false sinon
     */
    public Boolean intToBoolean(int x){
        return x != 0;
    }


    /**
     * On vérifie si le Joueur peut être placé, puis on le place dans le Niveau aux coordonnées indiqués
     * Si le Joueur ne peut pas être placé, on le place sur le premier vide d'un parcours en boucle avec addPlayerDefault()
     * On vérifie aussi s'il y a une pièce à l'endroit où on place le Joueur et on l'ajoute au score si c'est le cas
     * Idem pour les pièges avec la vie
     * @param j Joueur provenant de la classe joueur
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public void addPlayer(Joueur j, int y, int x) {
        try{
            if(!isPlayer(x,y) || this.niveau[y][x].getValue() == '*')
                throw new Exception();
            this.joueur = j;
            this.joueur.addCoordonnees(x,y);
            this.getPiece(x,y);
            this.getPiege(x,y);
            this.joueur.setDefaultXandY(x,y);
            this.niveau[y][x].setPlayer(); 
        } 
        catch(Exception e){
            this.joueur = j;
            this.addPlayerDefault();
        }
    }


    /**
     * Si les coordonnées de placement du joueur ne sont pas conforme, on place le joueur sur la première case vide disponible. 
     */
    public void addPlayerDefault(){
        for(int i=0; i<this.niveau.length; i++){
            for(int j=0; j<this.niveau[i].length; j++){
                if(this.niveau[i][j].getValue() == ' '){
                    this.joueur.addCoordonnees(j,i);
                    this.getPiece(j,i);
                    this.getPiege(j,i);
                    this.joueur.setDefaultXandY(j,i);
                    this.joueur.addCoordonnees(j,i);
                    this.niveau[i][j].setPlayer(); 
                    return;
                }
            }
        } 
        System.err.println("Erreur : Le joueur ne peut pas être placé.");
        System.exit(1);
    }


    /**
     * Demande à l'utilisateur s'il veut rejouer. Le N correspond à n'importe quelle touche c'est normal, on a ce système dans plusieurs jeux
     * @return true si l'utilisateur veut rejouer, false sinon
     * @throws IOException En cas de problème de lecture
     */
    public boolean Retry() throws IOException{
        System.out.println("GAME OVER");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Voulez-vous rejouer (O/N) ? ");
        String input = reader.readLine().trim().toUpperCase();
        char answer = input.isEmpty() ? '\0' : input.charAt(0);
        return answer == 'o' || answer == 'O';
    }


    /**
     * Charge le jeu en demandant un nom de joueur. C'est la méthode de lancement sans argument
     * On enchaine les niveaux de 1 à 5, puis on finit le jeu
     * Si le joueur n'a plus de vie, on lui propose de recommencer du début
     * @param j Joueur à ajouter au niveau
     * @throws IOException En cas de problème de lecture
     */
    public int loadGame(int i) throws IOException{
        this.nbPiece = numberOfPieces();
        Deplacement d = new Deplacement(this);
        d.Movement(1000,null);
        if(this.joueur.getLife()==0){
            if(Retry()){
                this.joueur.setLife(3);
                this.joueur.resetScore();
                d.clear();
                return 1;
            }
            else{
                System.out.println("Merci d'avoir pris le temps de jouer à cette masterclass.");
                return -1;
            }
        }
        return i;
    }


    /**
     * Trouve le Joueur '1' dans le Niveau et donne les coordonnées x,y au Joueur
     */
    public void setCoordonneeWithFile(){
        Cellule[][] tab = this.niveau;
        for(int i=0; i<tab.length; i++){
            for(int j=0; j<tab[i].length; j++){
                if(tab[i][j].getValue() == '1'){
                    this.joueur.setX(j);
                    this.joueur.setY(i);
                    this.joueur.setDefaultXandY(j,i);
                }
            }
        }
    }


    /**
     * Compte le nombre de pièces contenu dans le niveau
     * @return Nombre de pièces dans le niveau
     */
    public int numberOfPieces(){
        int compteur = 0;
        Cellule[][] tab = this.niveau;
        for (Cellule[] tab1 : tab) {
            for (Cellule tab11 : tab1) {
                if (tab11.getValue() == '.') {
                    compteur++;
                }
            }
        }
        return compteur;
    }


    /**
     * Compte le nombre de pièges contenu dans le niveau
     * @return Nombre de pièces dans le niveau
     */
    public int numberOfPieges(){
        int compteur = 0;
        Cellule[][] tab = this.niveau;
        for (Cellule[] tab1 : tab) {
            for (Cellule tab11 : tab1) {
                if (tab11.getValue() == '.') {
                    compteur++;
                }
            }
        }
        return compteur;
    }


    /**
     * Vérifie si le Niveau est conforme
     * @return Si c'est le cas, on retourne true
     */
    public boolean isNiveauExist(){
        return this.niveau.length > 0 && this.niveau[0].length > 0;
    }


    /**
     * Affiche le Niveau en affichant le tableau niveau sous forme de String
     */
    @Override
    public String toString(){
        String tab = "";
        for (Cellule[] niveau1 : this.niveau) {
            for (Cellule niveau11 : niveau1) {
                if (niveau11.getPlayer() == '1') {
                    tab += niveau11.getPlayer();
                } else {
                    tab += niveau11.getValue();
                }
            }
            tab += '\n';
        }
        return tab;
    }


    @Override
    public int hashCode() {
        int result = 17;
        for (Cellule[] niveau1 : niveau) {
            for (Cellule niveau11 : niveau1) {
                result = 31 * result + (niveau11 != null ? niveau11.hashCode() : 0);
            }
        }
        result = 31 * result + (joueur != null ? joueur.hashCode() : 0);
        return result;
    }



    /**
     * Vérifie que deux Niveau sont égaux en vérifiant s'ils ont le meme objet et tableau 
     * @param niv Un Niveau
     * @return true si les deux niveaux sont identiques, false sinon
     */
    @Override
    public boolean equals(Object niv){
        if(niv instanceof Niveau n){
            Cellule[][] tab = n.getNiveau();
            if((this.niveau.length == tab.length) && (this.niveau[0].length == tab[0].length)){
                for(int i=0; i<tab.length; i++){
                    for(int j=0; j<tab[i].length; j++){
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