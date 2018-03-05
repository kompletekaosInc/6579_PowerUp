package org.usfirst.frc.team6579.robot.autonomous;

import org.usfirst.frc.team6579.robot.Robot;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;

public class StraightAuto extends AutoStrategy {


    @Override
    public void doStrategy(Robot robot) {


        Drivetrain drivetrain = robot.getDrivetrain();

        long beginTimeHardStop = System.currentTimeMillis();
        while (System.currentTimeMillis()-beginTimeHardStop < 3000) {

            drivetrain.setPower(0.5,0.5);
        }
    }

    //fill this out

}
