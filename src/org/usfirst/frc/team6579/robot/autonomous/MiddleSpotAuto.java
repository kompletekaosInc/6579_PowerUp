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

        robot.getDrivetrain().driveEncoderGyro(15, 0.5);
        sleep(750);
        robot.getIntake().intakeSuckIn(0.15);
        robot.getDrivetrain().driveEncoderGyro(40, 0.45);
        robot.getDrivetrain().hardStop();

        robot.getLift().lift2(100);
        robot.getDrivetrain().gyroTurn(50, isSwitchLeft()); // if isSwitchLeft() is true, turn left; if false, turn right
        robot.getDrivetrain().driveEncoderGyro(150, 0.45);
        robot.getDrivetrain().gyroTurn(50, !isSwitchLeft()); // if isSwitchLeft() is true, turn right; if false, turn left

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
