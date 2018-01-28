package org.usfirst.frc.team6579.robot.autonomous;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;


/**
 * An abstract class defining what an autonomous strategy for Steamworks.  This class has some helper methods
 * that most strategies will need.  Simply subclass and implement <i>start</i>.
 *
 * Created by Jiah on 26/2/17.
 * Copied over to PowerUp code on 28/01/2018 by Jiah Pang
 */
public abstract class AutoStrategy {

    // can be overridden but the default is to drive for 2 seconds to get to baseline
    protected long getMsToBaseline() {
        return 1800;//changed from 2000
    }

    // can be overridden but default is to drive for 5 seconds
    protected long getMsForVisionTracking() {
        return 5000;
    }


    /**
     * This code is basically auto.init This is the main method to implement the autostrategy.  It will run
     * just one single pass through the code, triggered by Robot.autoInit().  Subclass must provide an implementation
     * of this method.
     */
    public abstract void start(Robot robot);

//    /**
//     * This method drives the robot from the starting wall to the baseline.
//     * Leverages the constant secondsToBaseline to determine how long this will take.
//     * TODO: put the power 0.4 into a constant.
//     * @param robot
//     */
//    protected void driveToBaseLine(Robot robot) {
//        long msDrivingForwardStartTime = 0;
//        robot.getDrivetrain().resetGyro();
//        double followAngle = robot.getDrivetrain().getModGyroAngle();
//
//        msDrivingForwardStartTime = System.currentTimeMillis();
//        while ((System.currentTimeMillis() - msDrivingForwardStartTime) < getMsToBaseline())
//        {
//            robot.publishSubSystemStats();
//            robot.getDrivetrain().followGyro(0.4,followAngle);
//        }
//        robot.getDrivetrain().stop();
//    }



}
