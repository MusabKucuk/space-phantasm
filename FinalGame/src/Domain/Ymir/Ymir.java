package Domain.Ymir;
import Domain.GamePlay.Game;

import java.util.Random;


import java.util.Timer;
import java.util.TimerTask;

public class Ymir {
    Random randomNum = new Random();
    Random abilityChooser = new Random();
    InfiniteVoidAbility infiniteVoidAbility = new InfiniteVoidAbility();
    DoubleAccelAbility doubleAccelAbility = new DoubleAccelAbility();
    HollowPurpleAbility hollowPurpleAbility = new HollowPurpleAbility();

    private boolean isCoinHead; // head is success which means ymir can use an ability.
    private int result;

    private int abilityType;


    public boolean coinFlip(){

        Timer myTimer = new Timer(); // oyun donunca devam ediyo. Durduralım bi ara.
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                result = randomNum.nextInt(2);
                if(result == 0 || !Game.getInstance().getIsRunning()){
                    setIsCoinHead(false);
                    System.out.println("Ymir flipped Tails!");
                }else{
                    setIsCoinHead(true);
                    abilityType = abilityChooser.nextInt(3);
                    System.out.println("Ymir flipped Heads! => " + abilityType);
                    switch (abilityType){
                        case 0:
                            infiniteVoidAbility.infiniteVoid();
                            break;
                        case 1:
                        	doubleAccelAbility.accelAbility();
                            break;
                        case 2:
                        	hollowPurpleAbility.hollowAbility();
                            break;
                        default:
                            break;
                    }
                }
            }

        };
        myTimer.schedule(task, 0, 30000);


        return isCoinHead;
    }
    public boolean isCoinHead() {
        return isCoinHead;
    }

    public void setIsCoinHead(boolean coinHead) {
        isCoinHead = coinHead;
    }
    public int getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(int abilityType) {
        this.abilityType = abilityType;
    }
}
