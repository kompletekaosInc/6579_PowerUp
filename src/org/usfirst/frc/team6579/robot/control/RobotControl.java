package org.usfirst.frc.team6579.robot.control;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team6579.robot.Robot;

/**
 *This interface is to be implemented by the two controlling classes
 *
 * Created 28/01/2018 by Jiah Pang
 */
public interface RobotControl {

//    //create joysticks
//    protected Joystick stick;
//
//
//    /**
//     * Hide the no argument constructor, you must use the constructor that
//     * specifies the joystick port to subclass RobotControl.
//     */
//    private RobotControl()
//    {
//        // final
//    }
//
//    /**
//     * This initialises the RobotControl object
//     */
//    public RobotControl( int port ) {
//
//        //define sticks
//        stick = new Joystick( port );
//
//    }

    /**
     * This is the method that controls the robot
     * This should only be used in TeleOp
     * @param robot
     */
    public void giveCommands(Robot robot);
}
