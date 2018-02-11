package org.usfirst.frc.team6579.robot.subsystem;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is responsible for the climber subsystem on the robot.
 * The climber subsystem has an arm that places the carabiner on the rung, and then a winch system to lift the robot
 *
 * PORTS:
 * ToDo: fill ports for climber
 *
 * This mechanism is solely controlled by the operator
 *
 * Created by Jiah Pang on the 28/01/2018
 */
public class Climber implements SubSystem {
    private PowerDistributionPanel pdp = new PowerDistributionPanel();

    private Spark climberWinch1 = new Spark(1);
    private Spark climberWinch2 = new Spark(2);
    private SpeedControllerGroup climberWinch = new SpeedControllerGroup(climberWinch1,climberWinch2);

    private VictorSP armMotor = new VictorSP(0);


    public void raiseArm()
    {
        armMotor.set(0.7);
    }

    public void lowerArm()
    {
        armMotor.set(-0.7);
    }

    public void stopArm() {
        armMotor.set(0);
    }

    /**
     * This method powers the winch in a positive direction, raising the robot off the ground
     * @param power
     */
    public void winchRaise(double power){
        climberWinch.set(power);
    }

    /**
     * Same same but different
     * Lowers the robot by powering the winch in a negative direction
     * @param power
     */
    public void winchLower(double power){
        climberWinch.set(-power);
    }

    @Override
    public void publishStats() {
        SmartDashboard.putNumber("Climber Arm Power", armMotor.get());
        SmartDashboard.putNumber("Arm Motor Current", pdp.getCurrent(11));
    }

    @Override
    public void test() {
        //test arm

        //test winch
    }
}
