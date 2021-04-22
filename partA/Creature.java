package partA;
import java.util.Date;


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

    //Getter and setter methods
    public void setName(String name) { this.name = name; }

    public String getName() {
        return name;
    }

    public static int getNumStillAlive() {
        return numStillAlive;
    }

    public void setHealthUnit(int x) {
        this.healthUnits = x;
    }

    public void setFoodUnit(int x) {
        this.foodUnits = x;
    }

    public void setFirePowerUnits(int x) {
        this.firePowerUnits = x;
    }

    public void reduceFirePowerUnits(int x) {
        this.firePowerUnits = firePowerUnits - x;
    }

    public int getFoodUnits() {
        return this.foodUnits;
    }

    public int getHealthUnits() {
        return this.healthUnits;
    }

    public int getFirePowerUnits() {
        return this.firePowerUnits;
    }

    /*
    This will be called to convert
    -   6 food units to a health unit
    -   4 units of health to a fire power unit
    -   Health can't drop below 4 is the hard parameter
     */
    private void normalizeUnits() {
        int originalFood = this.foodUnits;
        int originalHealth = this.healthUnits;
        int originalFire = this.firePowerUnits;

        if (originalFood >= 6) {
            while (originalFood >= 6) {
                this.setFoodUnit(originalFood - FOOD2HEALTH);
                originalFood = this.getFoodUnits();

                this.setHealthUnit(++originalHealth);
                originalHealth = this.getHealthUnits();
            }
        }

        if (originalHealth >= 4) {
            while (originalHealth >= 4 && (originalHealth - HEALTH2POWER) >= 4) {
                this.setHealthUnit(originalHealth - HEALTH2POWER);
                originalHealth = this.getHealthUnits();

                this.setFirePowerUnits(++originalFire);
                originalFire = this.getFirePowerUnits();
            }
        }
    }

    //date methods with a reference to the static variable numStillAlive, which decrements by one if someone dies
    public Date getDateCreated() {
        return this.dateCreated;
    }

    private void died() {
        numStillAlive -= 1;
        Date dateDied = new Date();
        this.dateDied = dateDied;
    }

    public Date getDateDied() {
        return this.dateDied;
    }


    public boolean isAlive() {
        boolean alive;
        if (this.dateDied == null) {
            alive = true;
        } else {
            alive = false;
        }
        return alive;
    }
    //...itszero() calls on the died method for the reference creature if true
    public boolean healthFoodUnitsZero() {
        if (healthUnits <= 0 && foodUnits <= 0) {
            this.died();
            return true;
        } else {
            return false;
        }
    }


    public void earnFood() {
        int foodOrig = this.foodUnits;
        int lard = (int) (Math.random() * 16);
        this.setFoodUnit(foodOrig + lard);
        this.normalizeUnits();
    }

    public void attacking(Creature player) {
        int attackerHealth = this.healthUnits;
        int attackerFood = this.foodUnits;
        int attackerFireUnits = this.firePowerUnits;
        int attackedHealth = player.healthUnits;
        int attackedFood = player.foodUnits;

        if (attackerFireUnits > 0) {
            int healthSwap = (int) Math.round(attackedHealth / 2.0);
            int foodSwap = (int) Math.round(attackedFood / 2.0);

            player.setHealthUnit(attackedHealth - healthSwap);
            player.setFoodUnit(attackedFood - foodSwap);
            player.normalizeUnits();

            this.setHealthUnit(attackerHealth + healthSwap);
            this.setFoodUnit((attackerFood + foodSwap));
            this.reduceFirePowerUnits(2);
            this.normalizeUnits();

            player.healthFoodUnitsZero();
            if (player.healthFoodUnitsZero()) {
                System.out.println(player.getName() + " has left us, look at what you did " + this.getName());
            }
        } else {
            System.out.println("You have no power here!");
        }
    }


    public String toString() {//Meatballs = foodunits, abs = health units, fuego units = fire power units
        String fancyPrint = this.foodUnits + " spicy meatballs, " + this.healthUnits + " abs, " + this.firePowerUnits +
                " fuego units...";
        return fancyPrint;
    }

    public void showStatus() {//Made it look fancy
        System.out.println("Spicey Meat Balls\t\tAbdominal Muscles\t\tFuego Units\t\tName");
        System.out.println("-----------------\t\t-----------------\t\t-----------\t\t----");
        System.out.println(this.foodUnits + "\t\t\t\t\t\t" + this.healthUnits + "\t\t\t\t\t\t" + this.firePowerUnits + "\t\t\t\t"
                + this.name);
        System.out.print("Date of birth: ");
        System.out.println(this.getDateCreated());
        System.out.print("Date of deadness: ");
        if (this.isAlive()) {
            System.out.println("is still kickin!");
        } else {
            System.out.println(this.getDateDied());
        }
    }
    //default constructor for the creature class
    public Creature(String newName) {
        numStillAlive += 1;
        this.dateCreated = new Date();
        this.setName(newName);
        this.foodUnits = (int) (Math.random() * 12) + 1;//( rand int 0-11 ) + 1 cast to an integer...
        this.healthUnits = (int) (Math.random() * 7) + 1;
        this.firePowerUnits = (int) (Math.random() * 10) + 1;
        this.normalizeUnits();

    }

}


