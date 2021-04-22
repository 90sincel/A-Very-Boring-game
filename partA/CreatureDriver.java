package partA;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class CreatureDriver {

//Very fun project
//NOTE: spiceymeatballs = food units, abs = health units, fuego units = fire power units
    public static void main(String[] args){
        System.out.println("[--------------------------------------------------------]");
        System.out.println("Welcome to a painfully efficient way to lose your friends!");
        System.out.println("[--------------------------------------------------------]");

        //player array: very neat idea
        Scanner keyIn = new Scanner(System.in);
        int numPlayers;
        do{//Logic for creating an array of players
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
            System.out.println();
        }


        //Main game logic, startP will be the index of the player who's turn it is
        int starterP = (int) (Math.random() * players.length);
        int answer;

        String newName;//For option 4 changing the name
        int attacked;//For option 6, is the index of the attacked player

        do {//Do/while for the whole game, breaks once there's only one player left...


                if (players[starterP].isAlive()) {//Skips turn if player is dead
                    do{
                        System.out.println("Player " + starterP + ": " + players[starterP].getName() + " just don't enter a 6 and we'll be groovy: ");
                        System.out.println();
                        System.out.println("1. How many people aren't dead?");
                        System.out.println("2. How many brownie points do I have?");
                        System.out.println("3. How many brownie points does everyone have?");
                        System.out.println("4. I need to be a new me (name change)");
                        System.out.println("5. Get dem gains");
                        System.out.println("6. Show them whatsup ( could backfire but that's not your concern )");
                        System.out.println();
                        System.out.print("Don't over think it: ");
                        answer = keyIn.nextInt();
                        if(answer>6 || answer<1){
                            System.out.println("One hand plus one finger is???");
                        }
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
                        for(int i=0; i<players.length; i++){
                            players[i].showStatus();
                            System.out.println();
                        }
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
                        //Takes the difference of initial food and food earned
                        String beforeP90X = players[starterP].toString();
                        int meatBallsBefore = players[starterP].getFoodUnits();
                        int theDiff;
                        players[starterP].earnFood();

                        theDiff = Math.abs(players[starterP].getFoodUnits() - meatBallsBefore);
                        System.out.println();
                        System.out.println("Before P90X: " + beforeP90X);
                        System.out.println("After P90X: " + players[starterP].toString() + "... earned " + theDiff + " meatballs.");
                        System.out.println();

                        //Logic for incrementing the players turn, same for options 6
                        if (starterP == players.length - 1) {
                            starterP = 0;
                        } else {
                            ++starterP;
                        }


                    } else {//answer = 5
                        System.out.println("Look what you did! Now all the fun's over!");
                        System.out.println();
                        String statsBefore = players[starterP].toString();//Attacking players original stats
                        boolean makesSense;

                        do {//Checks correct input, can't be the players index or a dead player
                            System.out.print("Enter your target's index (0 to " + ((players.length)-1) + " AND NOT YOURS! ) : ");
                            attacked = keyIn.nextInt();
                            if (attacked >= 0 && attacked < players.length && attacked != starterP && players[attacked].isAlive()) {
                                makesSense = true;
                            }else if(attacked == starterP){
                                makesSense = false;
                                System.out.println("Try again! That's you dummy!");
                            }else {
                                System.out.println("He's not kickin!!");
                                makesSense = false;
                            }
                        } while (!makesSense);

                        int youFeelinLucky = (int) (Math.random() * 3) + 1;
                        String statsBeforeAttacked = players[attacked].toString();//The stats of the player getting picked on before the slapping

                        boolean hasPower1 = players[starterP].getFirePowerUnits() >= 2;//check for power
                        boolean hasPower2 = players[attacked].getFirePowerUnits() >= 2;//check for power

                        System.out.println();

                        if ( hasPower1 == false && hasPower2 == true ) {//player successfully attacks


                            System.out.println("Check them stats before you start thowing shade! You will now be slapped by "+players[attacked].getName());
                            System.out.println();
                            System.out.println("Before attacking "+ players[attacked].getName()+" had: "+ players[attacked].toString());

                            players[attacked].attacking(players[starterP]);

                            System.out.println("After attacking " + players[attacked].getName()+" had: "+ players[attacked].toString());
                            System.out.println();
                            System.out.println("Attacked player "+players[starterP].getName()+" now has: "+ players[starterP].toString());
                            System.out.println("Happy about that decision? "+players[starterP].getName());

                            if (players[starterP].isAlive()) {
                                System.out.println(players[starterP].getName() + " is still kickin!");
                            } else {
                                System.out.println(players[starterP].getName() + " has fallen not so valiantly!!");
                            }
                            System.out.println();




                        } else if ( hasPower2 == true && youFeelinLucky == 3 ) {//player loses gamble and gets attacked


                            System.out.println("Well someone shouldn't gamble! You get slapped by "+players[attacked].getName());
                            System.out.println();
                            System.out.println("Before attacking "+ players[attacked].getName()+" had: "+ players[attacked].toString());

                            players[attacked].attacking(players[starterP]);

                            System.out.println("After attacking " + players[attacked].getName()+" had: "+ players[attacked].toString());
                            System.out.println();
                            System.out.println("Attacked player "+players[starterP].getName()+" now has: "+ players[starterP].toString());
                            System.out.println("Happy about that decision? "+players[starterP].getName());

                            if (players[starterP].isAlive()) {
                                System.out.println(players[starterP].getName() + " is still kickin!");
                            } else {
                                System.out.println(players[starterP].getName() + " has fallen not so valiantly!!");
                            }
                            System.out.println();



                        } else if( hasPower1 == true && youFeelinLucky != 3 ){// player doesn't have resources and gets attacked by opponent

                            players[starterP].attacking(players[attacked]);
                            System.out.println("Impressive, you beat the 1/3 odds ( heavy sarcasm ) you slap "
                                    +players[attacked].getName()+"!");
                            System.out.println();
                            System.out.println("Before attacking " +players[starterP].getName()+" had: "+ statsBefore);
                            System.out.println("After attacking: " +players[starterP].getName()+" has: "+ players[starterP].toString());
                            System.out.println();
                            System.out.print("The player who got stood up ( "+players[attacked].getName()+" ) now has: ");
                            System.out.println(players[attacked].toString());
                            System.out.println();
                            if (players[attacked].isAlive()) {
                                System.out.println(players[attacked].getName() + " is still kickin!");
                            } else {
                                System.out.println(players[attacked].getName() + " has fallen not so valiantly!!");
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


                } else {//Refers to the original if statement and cycles to the next player if the current one isn't alive
                    System.out.println(players[starterP].getName() + " isn't still kickin!!");
                    if (starterP == players.length - 1) {
                        starterP = 0;
                    } else {
                        ++starterP;
                    }
                }



        } while (Creature.getNumStillAlive() > 1);//ENDS GAME DO/WHILE

        System.out.println("El JUEGO HA TERMINADO!!!!");
        System.out.println();
        for (int i=0; i<players.length; i++){//Displays winner
            players[i].showStatus();
            System.out.println();
            if (players[i].isAlive()){
                System.out.println((players[i].getName()).toUpperCase()+", you have no more friends!!");
            }
        }

        keyIn.close();

    }


}
