public class Ennemi extends Joueur{

    public Ennemi(String nom, int x, int y){
        super(nom);
        this.x = x;
        this.y = y;
    }


    public void Test(){
        System.out.println(super.getName());
    }
}   
