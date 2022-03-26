package Domain.GamePlay;

import Domain.Obstacles.ExplosiveObstacle;
import Domain.Obstacles.FirmObstacle;
import Domain.Obstacles.MagicalObstacle;
import Domain.Obstacles.SuperObstacle;

import java.util.ArrayList;
import java.util.Random;

public class ObstacleFactory {
    /* OVERVIEW: ObstacleFactory is a factory that creates obstacles and put them in an arraylist.
     It includes mutable set of obstacles of any type respectively. Also we use this arraylist to save and laod the game state. */
    Random rand = new Random();
    ArrayList<SuperObstacle> simpleObsArray;
    Collision collision;
    private static ObstacleFactory instance;
    int savedHP;
    int savedScore;
    int paddleExpansion;
    int hexAbility;
    // AF(c) = { c.simpleObsArray[i] |0<=i<c.simpleObsArray.size()} 
    // The rep invariant is 
    // c.simpleObsArray not null &&
    // 0 < c.simpleObsArray.size() &&
    // There can be duplicates but the IDs of the all obstacles differs in the arraylist.
    
    
    //Constructors
    
    public ObstacleFactory() {
    // REQUIRES: collision instance
    // MODIFIES: savedHP, savedScore
    // EFFECTS: Variables are initialized to zero.
    
        collision = Collision.getInstance();
         savedHP = 0;
         savedScore = 0;
         paddleExpansion = 0;
         hexAbility = 0;
    }
    
    // Instance method
    
    public static ObstacleFactory getInstance() {
        if (instance == null)
            instance = new ObstacleFactory();
        return instance;
    }
    
    // methods
    
    public ArrayList<SuperObstacle> randomObstacleGenerator(int SimpleObstacleNum, int FirmObstacleNum,
            int explosiveObsNum, int magicalObsNum) {
    
    // REQUIRES: Global variables (numbers of the obstacles)
    // MODIFIES: simpleObsArray
    // EFFECTS: Obstacles are added respectively to arraylist.
    	
    	
        int maxRangeX = 1100;
        int minRangeX = 50;
        int maxRangeY = 400;
        int minRangeY = 50;
        int randomXRange;
        int randomYRange;
        int counter = 0;
        int TOTAL = SimpleObstacleNum + FirmObstacleNum + explosiveObsNum + magicalObsNum;

        randomXRange = (rand.nextInt(maxRangeX - minRangeX) + minRangeX);
        randomYRange = (rand.nextInt(maxRangeY - minRangeY) + minRangeY);
        simpleObsArray = new ArrayList<SuperObstacle>(TOTAL);

        SuperObstacle temp = new SuperObstacle(randomXRange, randomYRange);
        simpleObsArray.add(0, temp);

        for (int i = 1; i < TOTAL; i++) {
            randomXRange = (rand.nextInt(maxRangeX - minRangeX) + minRangeX);
            randomYRange = (rand.nextInt(maxRangeY - minRangeY) + minRangeY);

            SuperObstacle SomeObstacle = new SuperObstacle(randomXRange, randomYRange);
            simpleObsArray.add(i, SomeObstacle);

            counter++;
            for (int j = 0; j < counter; ++j) {

                while (collision.getRectangle(getArrayFromFactory().get(j))
                        .intersects(collision.getRectangle(getArrayFromFactory().get(counter)))) {
                    j = 0;
                    randomXRange = (rand.nextInt(maxRangeX - minRangeX) + minRangeX);
                    randomYRange = (rand.nextInt(maxRangeY - minRangeY) + minRangeY);
                    simpleObsArray.remove(i);
                    SomeObstacle.setPositionX(randomXRange);
                    SomeObstacle.setPositionY(randomYRange);
                    simpleObsArray.add(counter, SomeObstacle);
                }
            }
        }

        for (int i = TOTAL - FirmObstacleNum - explosiveObsNum - magicalObsNum; i < TOTAL - explosiveObsNum - magicalObsNum; i++) {

            int xpos = simpleObsArray.get(i).getPositionX();
            int ypos = simpleObsArray.get(i).getPositionY();
            FirmObstacle aaa = new FirmObstacle(xpos, ypos);
            simpleObsArray.set(i, aaa);
        }

        for (int i = TOTAL - explosiveObsNum - magicalObsNum; i < TOTAL - magicalObsNum; i++) {

            int xpos = simpleObsArray.get(i).getPositionX();
            int ypos = simpleObsArray.get(i).getPositionY();
            ExplosiveObstacle aaa = new ExplosiveObstacle(xpos, ypos);
            simpleObsArray.set(i, aaa);
        }
        
        for (int i = TOTAL - magicalObsNum; i < TOTAL; i++) {

            int xpos = simpleObsArray.get(i).getPositionX();
            int ypos = simpleObsArray.get(i).getPositionY();
            MagicalObstacle aaa = new MagicalObstacle(xpos, ypos);
            simpleObsArray.set(i, aaa);
        }

        return simpleObsArray;
    }
    
    
    public ArrayList<SuperObstacle> getArrayFromFactory() {
    
    //EFFECTS: Returns the simpleObsArray
    
        return simpleObsArray;
    }
    
    
    // simpleObsArrayList 
    
    // 
    
    

    public ArrayList<SuperObstacle> getObstaclesBySavedGame(String saveKey) {

    //REQUIRES: saveKey string
    //MODIFIES: simpleObsArray
    //EFFECTS: Returns to the simpObsArray 
    
        String[] array = saveKey.split("&", -1);
        simpleObsArray = new ArrayList<SuperObstacle>(array.length);

        int posx = 0;
        int posy = 0;
        int type = 0;

        for (int i = 0; i < array.length - 1; i++) {
            posx = 0;
            posy = 0;
            type = 0;
            
            String[] result = array[i].split("_", -1);

            posx = Integer.parseInt(result[0]);
            posy = Integer.parseInt(result[1]);
            type = Integer.parseInt(result[2]);
            this.savedHP = Integer.parseInt(result[3]);
            this.savedScore = Integer.parseInt(result[4]);
            this.paddleExpansion = Integer.parseInt(result[5]);
            this.hexAbility = Integer.parseInt(result[6]);
            

            if (type == 0) {
                SuperObstacle SomeObstacle = new SuperObstacle(posx, posy);
                simpleObsArray.add(i, SomeObstacle);

            }

            else if (type == 1) {
                FirmObstacle FirmObstacle = new FirmObstacle(posx, posy);
                simpleObsArray.add(i, FirmObstacle);
            }

            else if (type == 2) {
                ExplosiveObstacle ExplosiveObstacle = new ExplosiveObstacle(posx, posy);
                simpleObsArray.add(i, ExplosiveObstacle);
            }
            
            else if (type == 3) {
                MagicalObstacle MagicalObstacle = new MagicalObstacle(posx, posy);
                simpleObsArray.add(i, MagicalObstacle);
            }
        }
        
        return simpleObsArray;        
    }
    
    public int getSavedHp() {
    
    //EFFECTS: Returns to the savedHP
    
        return savedHP;
    }
    public int getSavedScore() {
    
    //EFFECTS: Returns to the savedScore
    
        return savedScore;
    }
    
    public int getPaddleExpansion() {

            return paddleExpansion;
        }
    
    public int getHexAbility() {
    
           return hexAbility;
       }
    
    
    
    public boolean repOk() {
			if (simpleObsArray == null) return false;
			if (simpleObsArray.size() < 0) return false;
			
			return true;
    }
    
}