package partA;
import java.util.Date;
import java.util.Scanner;
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



        int starterP = (int) (Math.random() * players.length);
        int answer;
        //boolean fiveOrSix;
        String newName;
        int attacked;

        do {//Do/while for the whole game, breaks once there's only one player left...

                //do{//Note the only difference between this and the driver is an if/else to check the player is still alive
                if (players[starterP].isAlive()) {
                    do{
                        System.out.println("Player " + starterP + ": " + players[starterP].getName() + " just don't enter a 6 and we'll be groovy: ");
                        System.out.println();
                        System.out.println("1. How many people aren't dead?");
                        System.out.println("2. See my stats bra");
                        System.out.println("3. See dem numbers bra");
                        System.out.println("4. Change my name");
                        System.out.println("5. Get dem gains");
                        System.out.println("6. Show him whassup ( could backfire but that's not your concern )");
                        System.out.println();
                        System.out.print("Don't over think it: ");
                        answer = keyIn.nextInt();
                        System.out.println();
                    }while(answer < 1 || answer > 6);
                    //The chain of player interactions: 6 is the else statement
                    if (answer == 1) {
                        //Shows how many players are still kickin!
                        System.out.println("You currently have "+Creature.getNumStillAlive()
                                +" friends, this will rapidly change as you're not very nice.");
                        System.out.println();

                        starterP = starterP;
                    } else if (answer == 2) {
                        //Shows the players stats
                        players[starterP].showStatus();
                        System.out.println();

                        starterP = starterP;
                    } else if (answer == 3) {


                        starterP = starterP;
                    } else if (answer == 4) {

                        String oldName = players[starterP].getName();
                        System.out.println("The old you: ");
                        System.out.println(oldName);
                        System.out.print("Enter your new name: ");
                        keyIn.nextLine();
                        newName = keyIn.nextLine();
                        players[starterP].setName(newName);
                        System.out.println();
                        players[starterP].showStatus();
                        System.out.println();

                        starterP = starterP;

                    } else if (answer == 5) {

                        String beforeP90X = players[starterP].toString();
                        int meatBallsBefore = players[starterP].getFoodUnits();
                        int theDiff;
                        players[starterP].earnFood();

                        theDiff = meatBallsBefore - players[starterP].getFoodUnits();
                        System.out.println();
                        System.out.println("Before P90X: " + beforeP90X);
                        System.out.println("After P90X: " + players[starterP].toString() + "... earned " + theDiff + " meatballs.");
                        System.out.println();

                        if (starterP == players.length - 1) {
                            starterP = 0;
                        } else {
                            ++starterP;
                        }


                    } else {//answer = 5
                        System.out.println("Look what you did! Now all the fun's over!");

                        String statsBefore = players[starterP].toString();
                        boolean makesSense;
                        do {
                            System.out.print("Enter your target's index (0 to " + ((players.length)-1) + ") : ");
                            attacked = keyIn.nextInt();
                            if (attacked >= 0 && attacked < players.length && attacked != starterP && players[attacked].isAlive()) {
                                makesSense = true;
                            } else {
                                makesSense = false;
                            }
                        } while (!makesSense);
                        int youFeelinLucky = (int) (Math.random() * 3) + 1;

                        if (youFeelinLucky == 3 && players[attacked].getFirePowerUnits() >= 2) {

                            System.out.println("Well someone shouldn't gamble!");
                            System.out.println("Before attacking "+ players[attacked].getName()+" had: "+ players[attacked].toString());

                            players[attacked].attacking(players[starterP]);

                            System.out.println("After attacking " + players[attacked].getName()+" had: "+ players[attacked].toString());
                            System.out.println("Attacked player "+players[starterP].getName()+" now has: "+ players[starterP].toString());
                            System.out.println("Happy about that decision? "+players[starterP].getName());

                            if (players[starterP].isAlive()) {
                                System.out.println(players[starterP].getName() + " is still kickin!");
                            } else {
                                System.out.println(players[starterP].getName() + " has fallen not so valiantly!!");
                            }
                            System.out.println();

                        } else if (youFeelinLucky < 3 && players[starterP].getFirePowerUnits() >= 2) {

                            players[starterP].attacking(players[attacked]);
                            System.out.println("Impressive, you beat the 1/3 odds ( heavy sarcasm )");
                            System.out.println("Before attacking " +players[starterP].getName()+" had: "+ statsBefore);
                            System.out.println("After attacking: " +players[starterP].getName()+" has: "+ players[starterP].toString());
                            System.out.print("The player who got stood up "+players[attacked].getName()+" now has: ");
                            System.out.println(players[attacked].toString());
                            if (players[attacked].isAlive()) {
                                System.out.println(players[attacked].getName() + " is still kickin!");
                            } else {
                                System.out.println(players[attacked].getName() + " has fallen not so valiantly!!");
                            }
                            System.out.println();

                        } else if(players[starterP].getFirePowerUnits() < 2 && players[attacked].getFirePowerUnits() >=2 ){

                            System.out.println("Check them stats before you start thowing shade!");
                            System.out.println();
                            System.out.println("Before attacking "+ players[attacked].getName()+" had: "+ players[attacked].toString());

                            players[attacked].attacking(players[starterP]);

                            System.out.println("After attacking " + players[attacked].getName()+" had: "+ players[attacked].toString());
                            System.out.println("Attacked player "+players[starterP].getName()+" now has: "+ players[starterP].toString());
                            System.out.println("Happy about that decision? "+players[starterP].getName());

                            if (players[starterP].isAlive()) {
                                System.out.println(players[starterP].getName() + " is still kickin!");
                            } else {
                                System.out.println(players[starterP].getName() + " has fallen not so valiantly!!");
                            }
                            System.out.println();

                        } else {
                            System.out.println("No fuego units!!! You both are a huge let down, check your stats next time!");
                        }

                        if (starterP == players.length - 1) {
                            starterP = 0;
                        } else {
                            ++starterP;
                        }
                    }


                } else {
                    System.out.println(players[starterP].getName() + " isn't still kickin!!");
                    if (starterP == players.length - 1) {
                        starterP = 0;
                    } else {
                        ++starterP;
                    }
                }



        } while (Creature.getNumStillAlive() > 1);//check this logic ENDS GAME DO/WHILE

        System.out.println("GAME HA TERMINADO!");
            //testing numStillAlive
            //System.out.println(getNumStillAlive());

        keyIn.close();

    }


}
