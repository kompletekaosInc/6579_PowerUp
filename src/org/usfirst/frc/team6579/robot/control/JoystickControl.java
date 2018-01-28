package org.usfirst.frc.team6579.robot.control;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * This is a barebones for a joystick controller
 *
 * Created 28/01/2018 by Jiah Pang
 */
public abstract class JoystickControl implements RobotControl {

    protected Joystick stick;


    public JoystickControl(int port){

        stick = new Joystick(port);

    }




    @Override
    public void giveCommands(Robot robot) {
        processStickButtons(robot);
        processThrottle(robot);
        //drive?
    }

    /**
     * This method is basically a scheduler of the methods to be called by the joystick during a match
     * @param robot
     */
    private void processStickButtons(Robot robot){

        if(stick.getRawButton(1)){
            processButton1(robot);
        }
        else if (stick.getRawButton(2)){
            processButton2(robot);
        }
        else if (stick.getRawButton(3)){
            processButton3(robot);
        }
        else if (stick.getRawButton(4)){
            processButton4(robot);
        }
        else if (stick.getRawButton(5)){
           processButton5(robot);
        }
        else if (stick.getRawButton(6)){
            processButton6(robot);
        }
        else if (stick.getRawButton(7)){
            processButton7(robot);
        }
        else if (stick.getRawButton(8)){
            processButton8(robot);
        }
        else if (stick.getRawButton(9)){
            processButton9(robot);
        }
        else if (stick.getRawButton(10)){
            processButton10(robot);
        }
        else if (stick.getRawButton(11)){
            processButton11(robot);
        }
        else if (stick.getRawButton(12)){
            processButton12(robot);
        }
        else{
            processNoButtons(robot);
        }
    }

    /**
     * Process any specific events for button 1 on the joystick.
     * @param robot
     */
    protected void processButton1( Robot robot )
    {
        //This is the opportunity for a subclass to create button one
    }
    /**
     * Process any specific events for button 2 on the joystick.
     * @param robot
     */
    protected void processButton2( Robot robot )
    {
        //This is the opportunity for a subclass to create button two
    }
    /**
     * Process any specific events for button 3 on the joystick.
     * @param robot
     */
    protected void processButton3( Robot robot )
    {
        //This is the opportunity for a subclass to create button three
    }
    /**
     * Process any specific events for button 4 on the joystick.
     * @param robot
     */
    protected void processButton4( Robot robot )
    {
        //This is the opportunity for a subclass to create button four
    }
    /**
     * Process any specific events for button 5 on the joystick.
     * @param robot
     */
    protected void processButton5( Robot robot )
    {
        //This is the opportunity for a subclass to create button five
    }
    /**
     * Process any specific events for button 6 on the joystick.
     * @param robot
     */
    protected void processButton6( Robot robot )
    {
        //This is the opportunity for a subclass to create button six
    }
    /**
     * Process any specific events for button 7 on the joystick.
     * @param robot
     */
    protected void processButton7( Robot robot )
    {
        //This is the opportunity for a subclass to create button seven
    }
    /**
     * Process any specific events for button 8 on the joystick.
     * @param robot
     */
    protected void processButton8( Robot robot )
    {
        //This is the opportunity for a subclass to create button eight
    }
    /**
     * Process any specific events for button 9 on the joystick.
     * @param robot
     */
    protected void processButton9( Robot robot )
    {
        //This is the opportunity for a subclass to create button nine
    }
    /**
     * Process any specific events for button 10 on the joystick.
     * @param robot
     */
    protected void processButton10( Robot robot )
    {
        //This is the opportunity for a subclass to create button ten
    }
    /**
     * Process any specific events for button 11 on the joystick.
     * @param robot
     */
    protected void processButton11( Robot robot )
    {
        //This is the opportunity for a subclass to create button eleven
    }
    /**
     * Process any specific events for button 12 on the joystick.
     * @param robot
     */
    protected void processButton12( Robot robot )
    {
        //This is the opportunity for a subclass to create button twelve
    }
    /**
     * Process any specific events for no buttons on the joystick.
     * @param robot
     */
    protected void processNoButtons( Robot robot )
    {
        //This is the opportunity for a subclass to create no button
    }
    /**
     * Process any specific events for throttle on the joystick.
     * @param robot
     */
    protected void processThrottle( Robot robot )
    {
        //This is the opportunity for a subclass to create throttle
    }
}
