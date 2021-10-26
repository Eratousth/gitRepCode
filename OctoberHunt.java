import java.util.*;
import java.lang.Math;

/*
Hello! This is a small game called October Hunt. 

The objective of the game is to reach a hidden treasure on the map.
When you find the treasure the win counter goes up, meaning you gained a point.
There is an aid in the form of a little indicator saying how far you are from the treasure
but it does not say the direction.

Controls:
w - Up
s - Down
a - Left
d - Right

e - exits the game

(For Phoenix Force Robotics, Victor Perete McIntyre)
*/

public class OctoberHunt {
    static int random(int max){

        //Generates random value inbetween 1 and max
        return (int)(Math.random()*(max-1)) + 1;
    }
    static boolean Game(int x, int y, int min, int max, int treasure_x, int treasure_y){

        //Win boolean
        boolean ans = false;

        //Board generate
        for (int i = min; i <= max; i++){
            System.out.println("");
            for (int e = min; e <= max; e++){
                if (i == y && e == x) {
                    if (y == treasure_y && x == treasure_x){
                        System.out.print(" $");
                        ans = true;
                    }else{
                        System.out.print(" o");
                    }
                }else {
                    System.out.print(" *");
                }
                
            }
        }

        //If game is a win, then return win
        return ans;
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
        int max = 5;
        int min = 1;

        //Player's beginning coordinates
        int player_x = max/2; 
        int player_y = max/2;

        //Treasure's coordinates
        int treasure_x = random(max);
        int treasure_y = random(max);
        while(true){
            if (treasure_x != player_x && treasure_y != player_y){
                break;
            }else{
                treasure_x = random(max);
                treasure_y = random(max);
            }
        }

        //Point counter
        int wins = 0;
        
        //Game loop
        while(true){

            //Initializes game
            boolean game = Game(player_x, player_y, min, max, treasure_x, treasure_y);

            //Checks if game was a win and asks if to continue
            if (game){
                wins += 1;
                System.out.println("\nTotal points:\t" + wins + "\nDo you want to continue? [y/n]");
                Scanner yn = new Scanner(System.in);
                String choice = yn.nextLine();

                if (choice.equals("y")){
                    while(true){

                        //Repositions treasure
                        if (treasure_x != player_x && treasure_y != player_y){
                            Game(player_x, player_y, min, max, treasure_x, treasure_y);
                            break;
                        }else{
                            treasure_x = random(max);
                            treasure_y = random(max);
                        }
                    }
                }else {
                    break;
                }
            }

            //Distance between player and treasure
            int distance = (int)Math.sqrt((Math.pow(player_x - treasure_x,2) + Math.pow(player_y - treasure_y, 2)));

            //System.out.println("\nx = " + (player_x - treasure_x));
            //System.out.println("\ny = " + (player_y - treasure_y));
            
            //Character direction input and logic
            System.out.println("\nWhich direction would you like to go?\n(You are " + distance + " units away from treasure)");            Scanner d = new Scanner(System.in);
            String direction = d.nextLine();

            if (direction.equals("e")){
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
            }else{System.out.println("Not a valid direction!");}
        }
    }
}