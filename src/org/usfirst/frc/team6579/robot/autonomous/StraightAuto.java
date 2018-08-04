package org.usfirst.frc.team6579.robot.autonomous;

import org.usfirst.frc.team6579.robot.Robot;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;
import org.usfirst.frc.team6579.robot.subsystem.Intake;
import org.usfirst.frc.team6579.robot.subsystem.Lift;

public class StraightAuto extends AutoStrategy {


    @Override
    public void doStrategy(Robot robot) {


        Drivetrain drivetrain = robot.getDrivetrain();
        Intake intake = robot.getIntake();
        Lift lift = robot.getLift();

        intake.intakeSuckIn(0.15);
        //drops the cube
        drivetrain.driveStraight(15, 0.40);
        drivetrain.hardStop();
        sleep(750);  // to give the cube time to drop


        //lift.raiseToHeight(20);

        long beginTimeHardStop = System.currentTimeMillis();
        while (System.currentTimeMillis()-beginTimeHardStop < 3000) {

            drivetrain.setPower(0.5,0.5);
        }
    }

    //fill this out

}
