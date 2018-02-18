package org.usfirst.frc.team6579.robot.autonomous;

import org.usfirst.frc.team6579.robot.Robot;

public class LeftSideAuto extends AutoStrategy {
    @Override
    public void doStrategy(Robot robot) {
        robot.getLift().lift2(100);
        robot.getDrivetrain().driveEncoderGyro(360,40);

        //turn 45 degrees to the side with the switch

        if (isSwitchLeft()==true){
            robot.getDrivetrain().gyroTurn(45,false);
            //robot.getDrivetrain().driveEncoderGyro(10,40);//ToDo: find good power that works


        }
        else{
            //robot.getDrivetrain().gyroTurn(45,false);
            robot.getDrivetrain().driveEncoderGyro(300,40);//ToDo: find good power that works
            robot.getDrivetrain().gyroTurn(90,false);

        }

        //robot.getLift().lift2(100);
        robot.getDrivetrain().driveEncoderGyro(100,40);
        robot.getIntake().intakeSpitOut(1);

    }
}
