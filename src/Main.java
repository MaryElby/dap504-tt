import com.company.Player;

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
            int playerAge = 21+i;
            playerArray[i] = new Player(playerFirstName,playerSurName,playerAge);

        }
        for (Player thePlayer: playerArray) {
            System.out.println(thePlayer.firstName + " " + thePlayer.lastName + " " + thePlayer.age);                    ;

        }
    }
}
