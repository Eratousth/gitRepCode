import java.util.*;
import java.lang.Math;

/*
Hello! This is a small game called October Hunt. 

The objective of the game is to reach a hidden treasure on the map.
When you find the treasure the win counter goes up, meaning you gained a point!
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
    static int coordinateGen(int exception, int max){

        //Generates coordinate value and checks to make sure it does not match another coordinate value
        int goal = random(max);
        while (true){
            if (goal == exception){
                goal = random(max);
            }else{
                break;
            }
        }
        return goal;
    }
    static boolean Game(int x, int y, int max_x, int max_y, int treasure_x, int treasure_y){

        //Defines text colors
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";

        boolean win = false;

        //Board generate
        for (int by = 1; by <= max_y; by++){
            System.out.println("");
            for (int bx = 1; bx <= max_x; bx++){
                if (by == y && bx == x) {
                    if (y == treasure_y && x == treasure_x){

                        //Treasure print with yellow text
                        System.out.print(ANSI_YELLOW+"$ "+ANSI_RESET);
                        win = true;
                    }else{

                        //Player print with green text
                        System.out.print(ANSI_GREEN+"O "+ANSI_RESET);
                    }
                }else {
                    System.out.print("* \u001B[0m");
                }
                
            }
        }

        //If game is a win, then return win
        return win;
    }
    public static void main(String[] arg){

        //Direction dictionary for w a s d
        Hashtable<String, Integer> directions = new Hashtable<String, Integer>();

        //Applying values to w a s d
        directions.put("w",-1);
        directions.put("s", 1);
        directions.put("a",-1);
        directions.put("d", 1);

        //Max y and x values for board size and range
        int max_x = 5;
        int max_y = 5;

        //Player's beginning coordinates
        int player_x = (int)Math.ceil(max_x/2); 
        int player_y = (int)Math.ceil(max_y/2);

        //Treasure's coordinates
        int treasure_x = coordinateGen(player_x, max_x);
        int treasure_y = coordinateGen(player_y, max_y);

        //Point counter
        int wins = 0;
        
        //Game loop
        while(true){

            //Initializes game
            boolean game = Game(player_x, player_y, max_x, max_y, treasure_x, treasure_y);

            //Checks if game was a win and asks if to continue
            if (game){
                wins += 1;
                System.out.println("\nTotal points:\t" + wins + "\nDo you want to continue? [y/n]");
                Scanner yn = new Scanner(System.in);
                String choice = yn.nextLine();

                if (choice.equals("y")){

                    //Generates new treasure coordinates and restarts game
                    treasure_x = coordinateGen(player_x, max_x);
                    treasure_y = coordinateGen(player_y, max_y);
                    Game(player_x, player_y, max_x, max_y, treasure_x, treasure_y);
                }else {
                    break;
                }
            }

            //Distance between player and treasure
            int distance = (int)Math.ceil(Math.sqrt((Math.pow(player_x - treasure_x,2) + Math.pow(player_y - treasure_y, 2))));

            //System.out.println("\nx = " + (player_x - treasure_x));
            //System.out.println("\ny = " + (player_y - treasure_y));
            
            //Character direction input and logic
            System.out.println("\nWhich direction would you like to go?\n(You are " + distance + " units away from treasure)");
            Scanner d = new Scanner(System.in);
            String direction = d.nextLine();

            if (direction.equals("e")){
                break;
            }else if (direction.equals("w") || direction.equals("s")){
                player_y += directions.get(direction);

                //If character goes over or under board y range, loops around
                if (player_y > max_y){
                    player_y = 1;
                }else if (player_y < 1){
                    player_y = max_y;
                }
            }else if (direction.equals("a") || direction.equals("d")){
                player_x += directions.get(direction);

                //If character goes over or under board x range, loops around
                if (player_x > max_x){
                    player_x = 1;
                }else if (player_x < 1){
                    player_x = max_x;
                }
            }else{System.out.println("Not a valid direction!");}
        }
    }
}