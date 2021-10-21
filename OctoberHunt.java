import java.util.*;
import java.lang.Math;

public class OctoberHunt {
    static void Game(int x, int y, int min, int max, int treasure_x, int treasure_y){
        for (int i = min; i <= max; i++){
            System.out.println("");
            for (int e = min; e <= max; e++){
                if (i == y && e == x) {
                    if (y == treasure_y && x == treasure_x){
                        System.out.print(" $");
                    }else{
                        System.out.print(" o");
                    }
                }else {
                    System.out.print(" *");
                }
            }
        }
    }
    public static void main(String[] arg){
        //Direction dictionary for w a s d
        Hashtable<String, Integer> directions = new Hashtable<String, Integer>();

        //Applying values to w a s d
        directions.put("w",-1);
        directions.put("s", 1);
        directions.put("a",-1);
        directions.put("d", 1);

        //Max and min values for board size and range
        int max = 16;
        int min = 1;

        //Player's beginning coordinates
        int player_x = max/2; 
        int player_y = max/2;

        //Treasure's coordinates
        int treasure_x = (int)(Math.random() * max);
        int treasure_y = (int)(Math.random() * max);
        while(true){
            if (treasure_x != player_x && treasure_y != player_y){
                break;
            }else{
                treasure_x = (int)(Math.random() * max);
                treasure_y = (int)(Math.random() * max);
            }
        }

        //int number = random.nextInt(max - min) + min;
        
        //Game loop
        while(true){
            Game(player_x, player_y, min, max, treasure_x, treasure_y);

            //Character direction
            System.out.println("\nWhich direction would you like to go?\n(" + treasure_x + ", " + treasure_y + ")");
            Scanner d = new Scanner(System.in);
            String direction = d.nextLine();

            if (direction.equals("y")){
                break;
            }else if (direction.equals("w") || direction.equals("s")){
                player_y += directions.get(direction);
                if (player_y > max){
                    player_y = min;
                }else if (player_y < min){
                    player_y = max;
                }

            }else if (direction.equals("a") || direction.equals("d")){
                player_x += directions.get(direction);
                if (player_x > max){
                    player_x = min;
                }else if (player_x < min){
                    player_x = max;
                }
            }else{System.out.println("Not a valid argument!");}
        }
    }
}