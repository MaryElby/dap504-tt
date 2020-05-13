import com.company.Player;

import java.util.Random;

public class Main {

    public static void main(String[] args){

        int numberOfPlayers = 8;
        //tournament of 8 players
        //create a player object to test
        //Player player1 = new Player("Arthur","Atkinson");
        //System.out.println("Player1 is " + player1.firstName + " " + player1.lastName);

        //create an array of players
        Player playerArray[] = new Player[numberOfPlayers];
        //playerArray[0] = player1;

        for (int i=0;i<numberOfPlayers;i++)
        {
            String playerFirstName = "Firstname"+i;
            String playerSurName = "Surname"+i;
            int playerAge = 0;
            //int playerAge = 21+i;

            //int playerAge = ((int) (Math.random() * 80));


            //playerArray[i] = new Player(playerFirstName,playerSurName,playerAge);
            playerArray[i] = new Player(playerFirstName,playerSurName,playerAge);
        }
        for (Player thePlayer: playerArray) {

            Random rAge = new Random();

            thePlayer.age= rAge.nextInt(80);

            System.out.println(thePlayer.firstName + " " + thePlayer.lastName + " " + thePlayer.age);                    ;
        }
    }
}
