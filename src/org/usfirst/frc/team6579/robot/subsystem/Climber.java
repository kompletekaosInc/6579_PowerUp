package org.usfirst.frc.team6579.robot.subsystem;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
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

    private VictorSP armMotor = new VictorSP(7);


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
