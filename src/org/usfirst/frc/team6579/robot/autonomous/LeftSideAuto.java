package org.usfirst.frc.team6579.robot.autonomous;

import org.usfirst.frc.team6579.robot.Robot;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;
import org.usfirst.frc.team6579.robot.subsystem.Intake;
import org.usfirst.frc.team6579.robot.subsystem.Lift;

public class LeftSideAuto extends AutoStrategy {

    public LeftSideAuto() {
        super.logger.getLogger(this.getClass().getName());
    }

    @Override
    public void doStrategy(Robot robot) {

        //Assuming robot is lined up with very far corner of base plate

        Drivetrain drivetrain = robot.getDrivetrain();
        Intake intake = robot.getIntake();
        Lift lift = robot.getLift();

        //drops the cube
        drivetrain.driveStraight(15, 40);
        drivetrain.hardStop();
        sleep(500);  // to give the cube time to drop

        lift.raiseToHeight(20);

        if (isSwitchLeft()){
            logger.info("Scale is to the right and Switch is to the left");

            drivetrain.driveStraight(370,0.60);//drives to side of switch //ToDo: find good power that works
            drivetrain.hardStop();
            drivetrain.turn(90,false);//rotates to face switch
            lift.raiseToHeight(80);//lifts lift to the 1m mark

            // robot.getDrivetrain().driveStraight(35.5, 0.40);//drives closer to switch //ToDo: find good power that works

            //make drive for seconds
            long beginTimeToSwitch = System.currentTimeMillis();
            while (System.currentTimeMillis()-beginTimeToSwitch < 2000) {

                drivetrain.setPower(0.5,0.5);
            }

            intake.intakeSpitOut(1);//spits out cube
            sleep(1000);
            intake.stopIntake();//stops intake

            //stretch component
            drivetrain.driveStraight(50,-40);//check that backwards works for this
            lift.liftDown(1);
            sleep(2000);

        }
        else if (isScaleLeft()) {
            logger.info("Scale is to the left");
            drivetrain.driveStraight(625,0.60);//drives to side of switch //ToDo: find distances
            drivetrain.turn(30,false);//rotates to face switch
            lift.raiseToHeight(180);//raises lift to 2m
            drivetrain.driveStraight(50, 0.40);//drives closer to switch //ToDo: find good power that works

            intake.intakeSpitOut(1);//spits out cube
            sleep(1000);
            intake.stopIntake();//stops intake

            //stretch
            drivetrain.driveStraight(50,-40);//check that backwards works for this
            lift.liftDown(1);
            sleep(3000);

            //extra stretch
            drivetrain.turn(100,false);
            long beginTimeHardStop = System.currentTimeMillis();
            while (System.currentTimeMillis()-beginTimeHardStop < 3000) {

                drivetrain.setPower(0.5,0.5);
                intake.intakeSuckIn(1);
            }



        }

        else{
            //drive past baseline
//            logger.info("Switch & Scale are to the left... doing baseline");
//            robot.getDrivetrain().driveStraight(415,0.40);//ToDo: find good power that works
//            robot.getDrivetrain().turn(90,false);
//            robot.getDrivetrain().driveStraight(523.5, 0.40);
//            robot.getDrivetrain().turn(90, false);
//            robot.getDrivetrain().driveStraight(140.5, 0.40);
//            robot.getLift().raiseToHeight(100);
//            robot.getIntake().intakeSpitOut(1);
//            sleep(1000);
//            robot.getIntake().stopIntake();

            long beginTimeHardStop = System.currentTimeMillis();
            while (System.currentTimeMillis()-beginTimeHardStop < 3000) {

                drivetrain.setPower(0.5,0.5);
            }

        }

        System.out.println("Left Side Auto complete...");

    }
}

