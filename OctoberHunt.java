import java.util.*;
public class OctoberHunt {
    static void Game(int x, int y){
        for (int i = 1; i <= 9; i++){
            System.out.println("");
            for (int e = 1; e <= 9; e++){
                if (i == y && e == x) {
                System.out.print(" o");
                } 
                else {
                    System.out.print(" *");
                }
            }
        }
    }
    public static void main(String[] arg){
        Hashtable<String, Integer> directions = new Hashtable<String, Integer>();

        int player_x = 5; 
        int player_y = 5;

        directions.put("w",-1);
        directions.put("s", 1);
        directions.put("a",-1);
        directions.put("d", 1);
        
        while(true){
            Game(player_x,player_y);

            System.out.println("\nWhich direction would you like to go?");
            Scanner d = new Scanner(System.in);
            String direction = d.nextLine();

            if (direction.equals("y")){
                break;
            }else if (direction.equals("w") || direction.equals("s")){
                if (player_y >= 1 || player_y <= 9){
                    player_y += directions.get(direction);
                }else{;}

            }else if (direction.equals("a") || direction.equals("d")){
                if (player_y >= 1 || player_y <= 9){
                    player_x += directions.get(direction);
                }else{;}
            }else{System.out.println("Not a valid argument!");}
        }
    }
}
//victor you are a very round person

