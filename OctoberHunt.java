import java.util.*;
public class OctoberHunt {
    static void Game(int x, int y, int min, int max){
        for (int i = min; i <= max; i++){
            System.out.println("");
            for (int e = min; e <= max; e++){
                if (i == y && e == x) {
                System.out.print(" o");
                }else {
                    System.out.print(" *");
                }
            }
        }
    }
    public static void main(String[] arg){
        //Direction dictionary for w a s d
        Hashtable<String, Integer> directions = new Hashtable<String, Integer>();

        //Player's beginning coordinates
        int player_x = 5; 
        int player_y = 5;

        //Max and min values for board size and range
        int max = 5;
        int min = 1;

        //Applying values to w a s d
        directions.put("w",-1);
        directions.put("s", 1);
        directions.put("a",-1);
        directions.put("d", 1);

        //int number = random.nextInt(max - min) + min;
        
        //Game loop
        while(true){
            Game(player_x,player_y,min,max);

            System.out.println("\nWhich direction would you like to go?");
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