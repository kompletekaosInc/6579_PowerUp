package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team6579.robot.Robot;

public class MiddleSpotAuto extends AutoStrategy {

    public MiddleSpotAuto() {
        // update logger class
        super.logger.getLogger( this.getClass().getName() );
    }

    @Override
    public void doStrategy(Robot robot) {

        logger.info("Starting MiddleSpotAuto strategy");

        double autoSpeed=0.5;

        //This is the knocking down of the intake, the wait is to make sure the cube falls before we drive off again
        robot.getDrivetrain().driveEncoderGyro(15, autoSpeed);
        sleep(750);

        robot.getIntake().intakeSuckIn(0.15); //this is to make sure the cube stays in the robot, runs until we spit the cube back out

        //Drive to just in front of the cube stack
        robot.getDrivetrain().driveEncoderGyro(50, 0.5);
        robot.getDrivetrain().hardStop();

        robot.getLift().lift2(100);

        if (isSwitchLeft()){
            robot.getDrivetrain().gyroTurn(60,true);
            robot.getDrivetrain().driveEncoderGyro(150,autoSpeed);
            robot.getDrivetrain().gyroTurn(60,false);
        }
        else{
            robot.getDrivetrain().gyroTurn(50,false);
            robot.getDrivetrain().driveEncoderGyro(120,autoSpeed);
            robot.getDrivetrain().gyroTurn(50,true);
        }

        //RIP GRIFFIN's COOL BOOL LOGIC FFFFFFFFFFFFFF
//        robot.getLift().lift2(100);
//        robot.getDrivetrain().gyroTurn(50, isSwitchLeft()); // if isSwitchLeft() is true, turn left; if false, turn right
//        robot.getDrivetrain().driveEncoderGyro(150, 0.45);
//        robot.getDrivetrain().gyroTurn(50, !isSwitchLeft()); // if isSwitchLeft() is true, turn right; if false, turn left

//        robot.getDrivetrain().setPower(0.5, 0.5);
//        sleep(2000);

        long beginTimneHardStop = System.currentTimeMillis();
        while (System.currentTimeMillis()-beginTimneHardStop < 1000) {

            robot.getDrivetrain().setPower(0.5,0.5);
        }

        robot.getIntake().intakeSpitOut(1);
        sleep(1000);
        robot.getIntake().stopIntake();
        robot.getDrivetrain().stop();

        System.out.println("Ended MiddleSpotAuto");

    }
}
