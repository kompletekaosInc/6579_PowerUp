package org.usfirst.frc.team6579.robot.control;


import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team6579.robot.Robot;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;

/**
 * This class is for the driver to control their subsystems.
 * Subsystems: drivetrain & intake
 *
 * Created 28/01/2018 by Jiah Pang
 */
public class DriverControl extends JoystickControl  {

    //private Joystick stick = new Joystick(0);

    /**
     * Driver will be using a Joystick from port 0.
     */
    public DriverControl(){
        super(0);

    }

    /**
     * Arcade drive method used for driving the robot around. Only used by this class
     * @param drivetrain
     */
    private void arcadeDrive(Drivetrain drivetrain){

        drivetrain.arcadeDiffDrive(stick.getX(),stick.getY());

    }

    /**
     * This controls the robot and should only be used during TeleOp
     * @param robot
     */
    @Override
    public void giveCommands(Robot robot) {
        super.giveCommands(robot);

        arcadeDrive(robot.getDrivetrain());

    }

    /**
     * These following methods are all for overriding the buttons on the joystick
     * @param robot
     */
    protected void processButton1(Robot robot ){

    }
    protected void processButton2(Robot robot ) {

    }
    protected void processButton3(Robot robot ) {

    }
    protected void processButton4(Robot robot ) {

    }
    protected void processButton5(Robot robot ) {

    }
    protected void processButton6(Robot robot ) {

    }
    protected void processButton7(Robot robot ) {

    }
    protected void processButton8(Robot robot ) {

    }
    protected void processButton9(Robot robot ) {

    }
    protected void processButton11(Robot robot ) {

    }
    protected void processButton10(Robot robot) {

    }
    protected void processButton12(Robot robot ) {

    }
    protected void processNoButtons(Robot robot ) {

    }

    //@Override
    protected void processThrottle(Robot robot) {
        //super.processThrottle(robot);


    }
}
