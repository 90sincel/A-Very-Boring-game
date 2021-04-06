package partA;
import java.util.Date;
import java.util.Scanner;
/*
So far
-   Build array of creatures
-   Fill array with creatures and display their specs ( do this with toString )
-
 */
public class CreatureDriver {

    public static void main(String[] args){

        System.out.println("[--------------------------------------------------------]");
        System.out.println("Welcome to a painfully efficient way to lose your friends!");
        System.out.println("[--------------------------------------------------------]");

        //player array: very neat idea
        Scanner keyIn = new Scanner(System.in);
        int numPlayers;
        do{
            System.out.print("How many people ya got??? (min of 2, max of 8) ");
            numPlayers = keyIn.nextInt();
            System.out.println();

            if (numPlayers < 2 || numPlayers > 8){
                System.out.println("*** 2 fingers, 8 fingers, there ya go... ***");
            }
        }while(numPlayers < 2 || numPlayers > 8);
        Creature[] players = new Creature[numPlayers];
        keyIn.nextLine();//Used to catch input (enter) after inputting numPlayers

        // Filling the array with (hopefully) appropriate names
        for (int i = 0; i < players.length; i++ ){
            System.out.print("What is the name of your least favourite politician, sorry creature? ");
            String name = keyIn.nextLine();
            System.out.println();
            players[i] = new Creature(name);
            players[i].showStatus();
        }
        //It's game time
        //Randomly select which player starts
        //Rotate through players starting at that number, display who's turn it is
        //Have players interact via 6 choices
        //Ends when there's only one player left
        int start = (int)(Math.random() * players.length);
        boolean oneLeft = false;

        while(oneLeft == false){


            System.out.println("Creature #"+start+": "+players[start].getName()+" what's on your mind?");
            System.out.println("1. How many people aren't dead?");
            System.out.println("2. See my stats bra");
            System.out.println("3. See dem numbers bra");
            System.out.println("4. Change my name");
            System.out.println("5. Get dem gains");
            System.out.println("6. Show him whatsup ( could backfire but that's not your concern )");


            //Logic for handling a loop break.
            int aliveCount = players.length;
            for (int i = 0; i<players.length; i++){
                if (players[i].isAlive()){
                    aliveCount++;
                }
            }
            if (aliveCount > 1){
                oneLeft = false;
            }else{
                oneLeft = true;
            }
        }





        keyIn.close();
    }

}
