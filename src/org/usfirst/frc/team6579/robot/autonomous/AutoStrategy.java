package org.usfirst.frc.team6579.robot.autonomous;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;

import java.util.logging.Logger;


/**
 * An abstract class defining what an autonomous strategy for Steamworks.  This class has some helper methods
 * that most strategies will need.  Simply subclass and implement <i>start</i>.
 *
 * Created by Jiah on 26/2/17.
 * Copied over to PowerUp code on 28/01/2018 by Jiah Pang
 */
public abstract class AutoStrategy {
    protected Logger logger = Logger.getLogger( this.getClass().getName() );

    // can be overridden but the default is to drive for 2 seconds to get to baseline
    protected long getMsToBaseline() {
        return 1800;//changed from 2000
    }

    // can be overridden but default is to drive for 5 seconds
    protected long getMsForVisionTracking() {
        return 5000;
    }

    private boolean switchIsLeft = true;
    private boolean scaleIsLeft = true;


    public AutoStrategy(){
        readFieldSetup();


    }

    private void readFieldSetup() {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        logger.info("Game data:" + gameData);

        if (gameData.length() > 0 ) {
            if (gameData.charAt(0) == 'L') {
                switchIsLeft = true;
            } else {
                switchIsLeft = false;
            }
            if (gameData.charAt(1) == 'L') {
                scaleIsLeft = true;
            } else {
                scaleIsLeft = false;
            }
        }
        else {
            // no game data???, assume both switch and scale are to the left
            logger.info("No game data?  Assuming things are left");
        }
    }

    /**
     * This code is basically auto.init This is the main method to implement the autostrategy.  It will run
     * just one single pass through the code, triggered by Robot.autoInit().  Subclass must provide an implementation
     * of this method.
     */
    public void start(Robot robot)
    {
        logger.info("start autoStrategy");
        readFieldSetup();
        doStrategy(robot);

    }

    public abstract void doStrategy(Robot robot);



    protected  boolean isSwitchLeft(){
        return switchIsLeft;
    }

    protected  boolean isScaleLeft(){
        return scaleIsLeft;
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void end(){

    }
}
