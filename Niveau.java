import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;

/**
 * Classe créant un <b>Niveau</b> aleatoirement compose de <i>mur</i> '#', de <i>vide</i> ' ', de <i>pièces</i> '.' de <i>pièges</i> '*' ainsi qu'un <b>Joueur</b> '1'
 */
public final class Niveau {
    private char[][] niveau;
    private Joueur joueur;
    private int nbPiece = 4;

    /**
     * Assigner les valeurs '#', ' ', '.' et '*' au tableau
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
        this.niveau[1][18] = '.';
        this.niveau[1][1] = '.';
        this.niveau[8][18] = '.';
        this.niveau[8][1] = '.';

        this.niveau[2][18] = '*';
        this.niveau[1][2] = '*';
        this.niveau[8][17] = '*';
        this.niveau[8][2] = '*';
    }


    /**
     * Permet de récupérer le Joueur d'un objet Niveau
     * @return L'objet Joueur dans le Niveau en paramètre
     */
    public Joueur getJoueur(){
        return this.joueur;
    }


    /**
     * Permet de récupérer le Niveau d'un objet niveau
     * @param niveau Permet de traiter des cas particuliers comme equals()
     * @return Le double tableau du Niveau en paramètre
     */
    public char[][] getNiveau(Niveau niveau){
        return this.niveau;
    }



    /**
     * Récupère la pièce en l'ajoutant au score, et la décomptant du nombre de pièce total
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public void getPiece(int x, int y){
        if(this.niveau[x][y] == '.'){
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
        if(this.niveau[x][y] == '*'){
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
        if(this.niveau[x][y] == '*')
            return true;
        return false;
    }

    
    /**
     * Vérifie si le niveau n'a plus de pièce. Si c'est le cas, il écrit VICTOIRE et finis le programme
     * @return S'il n'y a plus de pièce dans le niveau, on renvoie true, sinon on renvoie false
     */
    public boolean isFinishPiece(){
        if(this.nbPiece == 0){
            return true;
        }
        return false;
    }


    /**
     * Vérifie si le joueur n'a plus de vies. Si c'est le cas, il écrit GAME OVER et finis le programme
     * @return Si le joueur n'a plus de vies, on renvoie true, sinon on renvoie false
     */
    public boolean isFinishPiege(){
        if(this.joueur.getLife() == 0){
            return true;
        }
        return false;
    }
    


    /**
     * Vérifie si le Joueur '1' peut être placé
     * @param x Coordonnée x
     * @param y Cooronnée y
     * @return true si c'est le cas, false sinon
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
     * Génère un booléan à partir d'un entier
     * @param x entier
     * @return true si x vaut 0, false sinon
     */
    public Boolean Boolean(int x){
        if(x==0){
            return false;
        }
        else{
            return true;
        }
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
    public void addPlayer(Joueur j, int x, int y) {
        try{
            if(!isPlayer(x,y))
                throw new Exception();
            this.joueur = j;
            this.joueur.addCoordonnees(x,y);
            this.getPiece(x,y);
            this.getPiege(x,y);
            this.joueur.setDefaultXandY(x,y);
            this.niveau[x][y] = '1';
        } 
        catch(Exception e){
            this.joueur = j;
            this.addPlayerDefault();
        }
    }


    /**
     * Si les coordonnées de placement du joueur ne sont pas conforme, on place le joueur sur une case aléatoire. 
     * Pour ça on choisit un nombre entre 0 et 19 et si ce dernier est 0, alors on le place sur cette case. 
     * Sinon on le place sur la première case disponible à la fin
     */
    public void addPlayerDefault(){
        int n = 0; int x = 0; int y = 0;
        for(int i=0; i<this.niveau.length; i++){
            for(int j=0; j<this.niveau[0].length; j++){
                if(this.niveau[i][j] == ' '){
                    if(n == 0){
                        x = i;
                        y = j;
                        n = 1;
                    }
                    Random rand = new Random();
                    int random_number = rand.nextInt(20);
                    Boolean bool = Boolean(random_number); 
                    if(!bool){
                        this.joueur.addCoordonnees(i, j);
                        this.getPiece(i,j);
                        this.getPiege(i,j);
                        this.joueur.setDefaultXandY(x,y);
                        this.niveau[i][j] = '1';
                        return;
                    }
                }
            }
        }
        if(n==1){
            this.joueur.addCoordonnees(x, y);
            this.getPiece(x, y);
            this.getPiege(x, y);
            this.joueur.setDefaultXandY(x,y);
            this.joueur.addCoordonnees(x, y);
            this.niveau[x][y] = '1';
            return;
        }
    } 


    /**
     * Le fichier est sauvegardé dans le fichier <i>fileName</i>. Si le nom du fichier essaye d'accéder à des données systèmes, le programme renvoie une erreur
     * On enregistre le nom, le score et le nombre de vie du Joueur puis le Niveau dans le fichier
     * @param fileName Chemin absolu du fichier
     */
    public void saveFile(String fileName) {
        Path filePath = Paths.get(fileName);
        try {
            String n = this.toString();
            String nom = this.joueur.getName();
            String score = String.valueOf(this.joueur.getScore());
            String life = String.valueOf(this.joueur.getLife());
            Files.write(filePath, List.of(nom), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(filePath,List.of(score), StandardOpenOption.APPEND);
            Files.write(filePath,List.of(life), StandardOpenOption.APPEND);
            Files.write(filePath,List.of(n), StandardOpenOption.APPEND);
            //System.out.println("Fichier '" + fileName + "' écrit avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }


    /**
     * Cette fonction récupère le Niveau d'un fichier
     * On récupère d'abord le Joueur avec son nom, son score et sa vie dans les trois premières lignes
     * Puis le Niveau qui prend le reste du fichier
     * On déduit le nombre de pièces à récupérer avec le nombre de pièces restantes dans le Niveau. S'il n'y en a plus, on les remet toutes
     * Si le joueur n'a plus de vies, on créé un niveau par défaut avec 3 vies
     * Si on a une erreur, on créé un niveau par défaut. Il y a des erreurs personnalisés en fonction de l'erreur 
     * @param fileName Chemin absolu du fichier
     */
    public void loadFile(String fileName) {
        Path filePath = Paths.get(fileName);
        Joueur joueur_default = new Joueur("Bob");
        try {
            List<String> lines = Files.readAllLines(filePath);
            
            if (lines.isEmpty()) {
                throw new IOException();
            }

            String name = lines.get(0); 
            int score = Integer.valueOf(lines.get(1));
            int life = Integer.valueOf(lines.get(2));
    
            List<String> remainingLines = lines.subList(3, lines.size());
            
            int rows = remainingLines.size()-1;
            int cols = remainingLines.get(0).length();
            char[][] niveau = new char[rows][cols];
            
            for (int i = 0; i < rows; i++) {
                String line = remainingLines.get(i);
                for (int j = 0; j < line.length(); j++) {
                    niveau[i][j] = line.charAt(j);
                }
            }

            if(life == 0){
                throw new IllegalStateException();
            }

            this.niveau = niveau;

            Joueur j = new Joueur(name); //Le mot clé final empêche toute modification du nom du joueur actuel
            this.joueur = j;
            this.joueur.addScore(score);
            this.joueur.setLife(life);
            this.setCoordonneeWithFile();

            if (this.isNiveauExist()) {
                int compteur = this.numberOfPieces();
                if(compteur>0){
                    this.nbPiece = compteur;
                }
                else{
                    this.niveau[1][18] = '.';
                    this.niveau[1][1] = '.';
                    this.niveau[8][18] = '.';
                    this.niveau[8][1] = '.';
                }
                if(this.niveau[this.getJoueur().getX()][this.getJoueur().getY()] == '.'){
                    this.joueur.addScore(1);
                    this.nbPiece--;
                    this.niveau[this.getJoueur().getX()][this.getJoueur().getY()] = '1';
                }
                if(this.niveau[this.getJoueur().getX()][this.getJoueur().getY()] == '*'){
                    this.joueur.removeLife(1);
                    this.niveau[this.getJoueur().getX()][this.getJoueur().getY()] = '1';
                }
            }
            else{
                throw new IOException();
            }
        }
        catch (IOException e) {
            System.err.println("Le fichier " + fileName + " est vide ou corrompu. Un niveau par défaut a été créé.");
            Niveau n = new Niveau();
            this.niveau = n.niveau;
            this.addPlayer(joueur_default,5,5);
        }
        catch (IllegalStateException e){
            System.err.println("Le joueur n'a plus de vies. Un niveau par défaut a été créé.");
            this.addPlayer(joueur_default,5,5);
        }
    }

    /**
     * Charge un niveau seulement à partir d'un fichier. Similaire à loadFile mais sans la notion de vie et de score.
     * @param fileName Chemin absolu du fichier
     * @param joueur Joueur à ajouter au niveau
     */
    public void loadNiveau(String fileName, Joueur joueur){
        Path filePath = Paths.get(fileName);
        try {
            List<String> lines = Files.readAllLines(filePath);
            
            if (lines.isEmpty()) {
                throw new IOException();
            }
            
            int rows = lines.size();
            int cols = lines.get(0).length();
            char[][] niveau = new char[rows][cols];
            
            for (int i = 0; i < rows; i++) {
                String line = lines.get(i);
                for (int j = 0; j < line.length(); j++) {
                    niveau[i][j] = line.charAt(j);
                }
            }

            this.niveau = niveau;
            this.addPlayer(joueur, 1, 1);
            this.setCoordonneeWithFile();

            if (this.isNiveauExist()) {
                int compteur = this.numberOfPieces();
                if(compteur>0){
                    this.nbPiece = compteur;
                    if(this.niveau[this.getJoueur().getX()][this.getJoueur().getY()] == '.'){
                        this.joueur.addScore(1);
                        this.nbPiece--;
                        this.niveau[this.getJoueur().getX()][this.getJoueur().getY()] = '1';
                    }
                    if(this.niveau[this.getJoueur().getX()][this.getJoueur().getY()] == '*'){
                        this.joueur.removeLife(1);
                        this.niveau[this.getJoueur().getX()][this.getJoueur().getY()] = '1';
                    }
                }
            } 
            else{
                throw new IOException();
            }
        }
        catch (IOException e) {
            System.err.println("Le fichier " + fileName + " est vide ou corrompu. Un niveau par défaut a été créé.");
        }
    }


    /**
     * Demande à l'utilisateur s'il veut rejouer
     * @return true si l'utilisateur veut rejouer, false sinon
     * @throws IOException En cas de problème de lecture
     */
    public boolean Retry() throws IOException{
        System.out.println("GAME OVER");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Voulez-vous rejouer(O/N) ? ");
        String input = reader.readLine().trim().toUpperCase();
        char answer = input.isEmpty() ? '\0' : input.charAt(0);
        if(answer == 'o' || answer == 'O')
            return true;
        else
            return false;
    }


    /**
     * Charge le jeu en demandant un nom de joueur. C'est la méthode de lancement sans argument
     * On enchaine les niveaux de 1 à 5, puis on finit le jeu
     * Si le joueur n'a plus de vie, on lui propose de recommencer du début
     * @param j Joueur à ajouter au niveau
     * @throws IOException En cas de problème de lecture
     */
    public void loadGame(Joueur j) throws IOException{
        for(int i=1;i<6;i++){
            this.loadNiveau("Niveau" + i + ".txt", j);
            this.nbPiece = numberOfPieces();
            Deplacement d = new Deplacement(this);
            d.Movement(1000,null);
            if(this.joueur.getLife()==0){
                if(Retry()){
                    i = 0;
                    this.joueur.setLife(3);
                    this.joueur.resetScore();
                    d.clear();
                    continue;
                }
                else
                    System.out.println("Merci d'avoir pris le temps de jouer à cette masterclass.");
                    break;
            }
        }
        if(this.joueur.getLife() != 0){
            System.out.println(this);
            System.out.println(this.getJoueur());
            System.out.println("Merci d'avoir pris le temps de jouer à cette masterclass.");
        }
    }



    /**
     * Trouve le Joueur '1' dans le Niveau et donne les coordonnées x,y au Joueur
     */
    public void setCoordonneeWithFile(){
        char[][] tab = this.niveau;
        for(int i=0; i<tab.length; i++){
            for(int j=0; j<tab[0].length; j++){
                if(tab[i][j] == '1'){
                    this.joueur.setX(i);
                    this.joueur.setY(j);
                    this.joueur.setDefaultXandY(i,j);
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
        char[][] tab = this.niveau;
        for(int i=0; i<tab.length; i++){
            for(int j=0; j<tab[0].length; j++){
                if(tab[i][j] == '.'){
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
        char[][] tab = this.niveau;
        for(int i=0; i<tab.length; i++){
            for(int j=0; j<tab[0].length; j++){
                if(tab[i][j] == '.'){
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
        if(this.niveau.length > 0 && this.niveau[0].length > 0){
            return true;
        }
        return false;
    }


    /**
     * Affiche le tableau
     */
    public String toString(){
        String tab = "";
        for(int i=0; i<this.niveau.length; i++){
            for(int j=0; j<this.niveau[0].length; j++){
                tab += this.niveau[i][j];
            }
            tab += '\n';
        }
        return tab;
    }


    /**
     * Vérifie que deux Niveau sont égaux en vérifiant s'ils ont le meme objet et tableau 
     * @param niv Un Niveau
     */
    public boolean equals(Object niv){
        if(niv instanceof Niveau){
            Niveau n = (Niveau) niv;
            char[][] tab = getNiveau(n);
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