package org.usfirst.frc.team6579.robot.subsystem;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * This class is responsible for the lift mechanism on the robot. The lift mechanism is used to lift the intake from floor level to scale height.
 *
 * The lift mechanism has one toughbox mini with a single miniCIM motor, an encoder and a magnetic limit switch
 *
 * PORTS:
 * ToDo: list ports for lift subsystem here
 *
 * The lift mechanism will be solely controlled by the operator
 *
 * Created by Jiah Pang on 28/01/2018
 */
public class Lift implements SubSystem {

    private VictorSP liftMotor = new VictorSP(7);//should be port nine for testing on 2.2.18 Jiah Pang //changed to port 7 8.2.18 Jiah

    /**
     * Lifts the mechanism
     * @param power
     */
    public void liftUp(double power){
        liftMotor.set(power);
    }

    /**
     * Lowers the lift mechanism.
     *
     * Always use a positive power, the code will make the power negative.
     * @param power
     */
    public void liftDown(double power){
        liftMotor.set(-power);
    }

    public void stop(){
        liftMotor.set(0);
    }

    @Override
    public void publishStats() {

    }

    @Override
    public void test() {
        //up

        //test down

        //test limits
    }
}
