package partA;
import java.util.Date;
import java.util.concurrent.Callable;

/*
TO-DO
-   How does the Class Creature describe what an object KNOWS and what it DOES??
-   toString**
-       display like the example
-   isDead needs to display "still alive"!

 */

public class Creature {
    private static int FOOD2HEALTH = 6;
    private static int HEALTH2POWER = 4;
    private static int numStillAlive = 0;
    private String name;
    private int foodUnits;
    private int healthUnits;
    private int firePowerUnits;
    private Date dateCreated;
    private Date dateDied = null;

    //mutator methods
    public void setName(String name) {

        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static int getNumStillAlive(){
        return numStillAlive;
    }
    public void setHealthUnit(int x){
        this.healthUnits = x;
    }
    public void setFoodUnit(int x){
        this.foodUnits = x;
    }
    public void setFirePowerUnits(int x){
        this.firePowerUnits = x;
    }
    public void reduceFirePowerUnits(int x){
        this.firePowerUnits = firePowerUnits - x;
    }

    public int getFoodUnits(){
        return this.foodUnits;
    }
    public int getHealthUnits(){
        return this.healthUnits;
    }
    public int getFirePowerUnits(){
        return this.firePowerUnits;
    }

    private void normalizeUnits(){
        int originalFood = this.foodUnits;
        int originalHealth = this.healthUnits;
        int originalFire = this.firePowerUnits;

        if(originalFood >= 6){
            while(originalFood>=6){
                this.setFoodUnit(originalFood-FOOD2HEALTH);
                originalFood = this.getFoodUnits();

                this.setHealthUnit(++originalHealth);
                originalHealth = this.getHealthUnits();
            }
        }

        if(originalHealth>=4){
            while(originalHealth >=4 && (originalHealth - HEALTH2POWER) >= 4){
                this.setHealthUnit(originalHealth - HEALTH2POWER);
                originalHealth = this.getHealthUnits();

                this.setFirePowerUnits(++originalFire);
                originalFire = this.getFirePowerUnits();
            }
        }
    }

    //date stuff
    public Date getDateCreated(){
        return this.dateCreated;
    }
    private void died(){
        Date dateDied = new Date();
        this.dateDied = dateDied;
    }
    public Date getDateDied(){ return this.dateDied; }


    //END MUTATORS/ACCESSORS
    public boolean isAlive(){
        boolean alive;
        if (this.dateDied == null){
            alive = true;
        }else{
            alive = false;
        }
        return alive;
    }
    public boolean healthFoodUnitsZero(){
        if(healthUnits <= 0 && foodUnits <= 0){
            this.died();
            return true;
        }else{
            return false;
        }
    }


    public void earnFood(){
        int foodOrig = this.foodUnits;
        int lard = (int) (Math.random() * 16);
        this.setFoodUnit(foodOrig + lard);
        this.normalizeUnits();
    }

    public void attacking(Creature player){
        int attackerHealth = this.healthUnits;
        int attackerFood = this.foodUnits;
        int attackerFireUnits = this.firePowerUnits;
        int attackedHealth = player.healthUnits;
        int attackedFood = player.foodUnits;

        if (attackerFireUnits > 0){
            int healthSwap = (int) Math.round(attackedHealth/2.0);
            int foodSwap = (int) Math.round(attackedFood/2.0);
            player.setHealthUnit(attackedHealth - healthSwap);
            player.setFoodUnit(attackedFood - foodSwap);

            this.setHealthUnit(attackerHealth + healthSwap);
            this.setFoodUnit((attackerFood + foodSwap));
            this.reduceFirePowerUnits(2);

            this.normalizeUnits();
            player.healthFoodUnitsZero();
            if(player.healthFoodUnitsZero()){
                System.out.println(player.getName()+" has left us, look at what you did "+this.getName());
            }
        }else{
            System.out.println("You have no power here!");
        }
    }


    public String toString(){//displays date of birth
        String fancyPrint = " "+this.dateCreated;
        return fancyPrint;
    }

    public void showStatus(){// NEED TO TAKE A CLOSER LOOK AT THIS: NEEDS TO RETURN A STRING** NOT VOID
        System.out.println("Food units\t\tHealth Units\t\tFire POWA\t\tName");
        System.out.println("----------\t\t------------\t\t---------\t\t----");
        System.out.println(this.foodUnits+"\t\t\t\t"+this.healthUnits+"\t\t\t\t\t"+this.firePowerUnits+"\t\t\t\t"
                +this.name);
        System.out.print("Date of birth: ");
        System.out.println(this.toString());
        System.out.print("Date of deadness: ");
        if (this.isAlive()){
            System.out.println("is still kickin!");
        }else{
            System.out.println(this.getDateDied());
        }
    }
    /*
    This will be called to convert
    -   6 health units to a food unit
    -   4 units of health to a fire power unit
    -   both of these units can't drop below their initialised values
     */

    public Creature(String newName){
        numStillAlive += 1;
        dateCreated = new Date();
        setName(newName);
        foodUnits = (int) (Math.random() * 12)+1;//( rand int 0-11 ) + 1 cast to an integer...
        healthUnits = (int) (Math.random() * 7)+1;
        firePowerUnits = (int) (Math.random() * 10)+1;
        normalizeUnits();

    }


static class CreatureTestDrive {
        public static void main(String[] args){
            Creature p1 = new Creature("Bob");
            Creature p2 = new Creature("Jared");
            String bobName = p1.getName();
            String jaredName = p2.getName();
            Date bornP1 = p1.dateCreated;
            Date bornP2 = p2.dateCreated;

            System.out.println(p1.toString());
            p1.showStatus();
            System.out.println(p2.toString());
            p2.showStatus();

            /*
            int food = p1.getFoodUnits();
            int health = p1.getHealthUnits();
            int fire = p1.getFirePowerUnits();
            System.out.println(bobName + " > Food: "+food+"\tHealth: "+health+"\tFirePower: "+fire+"\tborn"+bornP1);


            int food2 = p2.getFoodUnits();
            int health2 = p2.getHealthUnits();
            int fire2 = p2.getFirePowerUnits();
            System.out.println(jaredName + " > Food: "+food2+"\tHealth: "+health2+"\tFirePower: "+fire2+ "\tborn: "+bornP2);

            p1.attacking(p2);
            p1.attacking(p2);
            System.out.println(bobName+"\t"+p1.getFoodUnits()+"\t"+ p1.getHealthUnits()+"\t"+p1.getFirePowerUnits());
            System.out.println(jaredName+"\t"+p2.getHealthUnits()+"\t"+p2.getFoodUnits());
            System.out.println(p2.getDateDied());
            */
    }
}
}
