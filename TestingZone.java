import partA.Creature;

import java.util.Scanner;
public class TestingZone {

    public static void main(String[] args){
        Scanner keyIn = new Scanner(System.in);
        Creature[] players = {new Creature("jeremy"),new Creature("kyle"), new Creature("alabaster"),
                new Creature("steffany")};

        //Place the naming and array logic here if you can't get the driver to work

        int starterP = (int) (Math.random() * players.length);
        int answer;
        //boolean fiveOrSix;
        String newName;
        int attacked;

        do{
            //do{//Note the only difference between this and the driver is an if/else to check the player is still alive
                if (players[starterP].isAlive()) {
                    System.out.println("Player " + starterP + ": " + players[starterP].getName() + " just don't enter a 6 and we'll be groovy: ");
                    System.out.println("1. How many people aren't dead?");
                    System.out.println("2. See my stats bra");
                    System.out.println("3. See dem numbers bra");
                    System.out.println("4. Change my name");
                    System.out.println("5. Get dem gains");
                    System.out.println("6. Show him whassup ( could backfire but that's not your concern )");
                    answer = keyIn.nextInt();

                    if (answer == 1) {


                        starterP = starterP;
                    } else if (answer == 2) {

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
                        players[starterP].showStatus();


                        starterP = starterP;

                    } else if (answer == 5) {

                        String beforeP90X = players[starterP].toString();
                        int meatBallsBefore = players[starterP].getFoodUnits();
                        int theDiff;
                        players[starterP].earnFood();

                        theDiff = meatBallsBefore - players[starterP].getFoodUnits();

                        System.out.println("Before P90X: "+beforeP90X);
                        System.out.println("After P90X: "+players[starterP].toString()+"... earned "+theDiff+" meatballs.");
                        System.out.println();

                        if (starterP == players.length - 1) {
                            starterP = 0;
                        } else {
                            ++starterP;
                        }


                    } else {//answer = 5
                        String statsBefore = players[starterP].toString();
                        boolean makesSense;
                        do{
                            System.out.print("Enter your target's index (0 to "+players.length+") : ");
                            attacked = keyIn.nextInt();
                            if( attacked >= 0 && attacked < players.length && attacked != starterP && players[attacked].isAlive() ){
                                makesSense = true;
                            }else{
                                makesSense = false;
                            }
                        }while (!makesSense);
                        int youFeelinLucky = (int) (Math.random() * 3)+1;

                        if(youFeelinLucky == 3 && players[attacked].getFirePowerUnits() >= 2){

                            players[attacked].attacking(players[starterP]);
                            System.out.println("Well someone shouldn't gamble!");
                            System.out.println("Before attacking: "+statsBefore);
                            System.out.println("After attacking: "+players[attacked].toString());
                            System.out.println("Attacked player: "+players[starterP].toString());
                            if(!players[starterP].isAlive()){
                                System.out.println(players[attacked].getName()+" has fallen not so valiantly!!");
                            }else{
                                System.out.println(players[starterP].getName()+" is still kickin!");
                            }System.out.println();

                        }else if (youFeelinLucky < 3 && players[starterP].getFirePowerUnits() >=2){

                            players[starterP].attacking(players[attacked]);
                            System.out.println("Impressive, you beat the 1/3 odd ( heavy sarcasm )");
                            System.out.println("Before attacking: "+statsBefore);
                            System.out.println("After attacking: "+players[starterP].toString());
                            System.out.println("Attacked player: "+players[attacked].toString());
                            if(!players[attacked].isAlive()){
                                System.out.println(players[attacked].getName()+" has fallen not so valiantly!!");
                            }else{
                                System.out.println(players[attacked].getName()+" is still kickin!");
                            }System.out.println();

                        }else{
                            System.out.println("No fuego units!!! You both are a huge let down, check your stats next time!");
                        }

                        if (starterP == players.length - 1) {
                            starterP = 0;
                        } else {
                            ++starterP;
                        }
                    }



                }else{
                    System.out.println(players[starterP].getName()+" isn't still kickin!!");
                    if (starterP == players.length - 1) {
                        starterP = 0;
                    } else {
                        ++starterP;
                    }
                    answer = -1;
                }

        }while(answer != -1);//use answer == 6 to simplify
                /*
                switch (answer){
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        fiveOrSix = true;
                        break;
                    default:
                        fiveOrSix = false;
                }


                //the options are adjusted here, 1 == change name, 4 == work for food, and 5 == attack player
                String newName;
                int attacked;
                if (answer == 1){

                    String oldName = players[starterP].getName();
                    System.out.println("The old you: ");
                    System.out.println(oldName);
                    System.out.print("Enter your new name: ");
                    keyIn.nextLine();
                    newName = keyIn.nextLine();

                    players[starterP].setName(newName);
                    players[starterP].showStatus();

                }else if(answer == 4){

                    String beforeP90X = players[starterP].toString();
                    int meatBallsBefore = players[starterP].getFoodUnits();
                    int theDiff;
                    players[starterP].earnFood();

                    theDiff = meatBallsBefore - players[starterP].getFoodUnits();

                    System.out.println("Before P90X: "+beforeP90X);
                    System.out.println("After P90X: "+players[starterP].toString()+"... earned "+theDiff+" meatballs.");
                    System.out.println();

                } else if (answer == 5){
                    String statsBefore = players[starterP].toString();
                    boolean makesSense;
                    do{
                        System.out.print("Enter your target's index (0 to "+players.length+") : ");
                        attacked = keyIn.nextInt();
                        if( attacked >= 0 && attacked < players.length && attacked != starterP && players[attacked].isAlive() ){
                            makesSense = true;
                        }else{
                            makesSense = false;
                        }
                    }while (!makesSense);
                    int youFeelinLucky = (int) (Math.random() * 3)+1;

                    if(youFeelinLucky == 3 && players[attacked].getFirePowerUnits() >= 2){

                        players[attacked].attacking(players[starterP]);
                        System.out.println("Well someone shouldn't gamble!");
                        System.out.println("Before attacking: "+statsBefore);
                        System.out.println("After attacking: "+players[attacked].toString());
                        System.out.println("Attacked player: "+players[starterP].toString());
                        if(!players[starterP].isAlive()){
                            System.out.println(players[attacked].getName()+" has fallen not so valiantly!!");
                        }else{
                            System.out.println(players[starterP].getName()+" is still kickin!");
                        }System.out.println();

                    }else if (youFeelinLucky < 3 && players[starterP].getFirePowerUnits() >=2){

                        players[starterP].attacking(players[attacked]);
                        System.out.println("Impressive, you beat the 1/3 odd ( heavy sarcasm )");
                        System.out.println("Before attacking: "+statsBefore);
                        System.out.println("After attacking: "+players[starterP].toString());
                        System.out.println("Attacked player: "+players[attacked].toString());
                        if(!players[attacked].isAlive()){
                            System.out.println(players[attacked].getName()+" has fallen not so valiantly!!");
                        }else{
                            System.out.println(players[attacked].getName()+" is still kickin!");
                        }System.out.println();

                    }else{
                        System.out.println("No fuego units!!! You both are a huge let down, check your stats next time!");
                    }

                }else{
                    answer = answer;
                }

            /*
            }while(!fiveOrSix);
            answer = answer;
            if (starterP == players.length-1){
                starterP = 0;
            }else{
                starterP += 1;
            }

             */






        keyIn.close();

    }

}
