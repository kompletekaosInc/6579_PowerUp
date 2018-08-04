package org.usfirst.frc.team6579.robot.autonomous;

import org.usfirst.frc.team6579.robot.Robot;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;
import org.usfirst.frc.team6579.robot.subsystem.Intake;
import org.usfirst.frc.team6579.robot.subsystem.Lift;

public class RightSideAuto extends AutoStrategy {

    public RightSideAuto() {
        //update logger class
        super.logger.getLogger(this.getClass().getName());
    }

    //Assuming robot is lined up with very far corner of base plate

    @Override
    public void doStrategy(Robot robot) {

        //Assuming robot is lined up with very far corner of base plate

        Drivetrain drivetrain = robot.getDrivetrain();
        Intake intake = robot.getIntake();
        Lift lift = robot.getLift();

        intake.intakeSuckIn(0.15);
        //drops the cube
        drivetrain.driveStraight(15, 0.40);
        drivetrain.hardStop();
        sleep(750);  // to give the cube time to drop

        //lift.raiseToHeight(20);

        float liftTimer1 = System.currentTimeMillis();
        while (System.currentTimeMillis()-liftTimer1<500){
            lift.liftUp(1);
        }

        if (!isSwitchLeft()){
            logger.info("Switch is to the right");

            drivetrain.driveStraight(370,0.40);//drives to side of switch //ToDo: find good power that works
            drivetrain.hardStop();
            drivetrain.turn(90,true);//rotates to face switch
            //lift.raiseToHeight(100);//lifts lift to the 1m mark

            // robot.getDrivetrain().driveStraight(35.5, 0.40);//drives closer to switch //ToDo: find good power that works

            //make drive for seconds
            long beginTimeToSwitch = System.currentTimeMillis();
            while (System.currentTimeMillis()-beginTimeToSwitch < 2000) {

                drivetrain.setPower(0.3,0.3);
            }

            intake.intakeSpitOut(1);//spits out cube
            sleep(1000);
            intake.stopIntake();//stops intake

            //stretch component
            drivetrain.driveStraight(100,-0.30);//check that backwards works for this
            lift.liftDown(0.5);
            sleep(2000);
            lift.stop();

        }
        else if (!isScaleLeft()) {
            logger.info("Switch is to the left and scale is to the right");
            logger.info("Scale is to the left");
            drivetrain.driveStraight(570,0.75);//drives to side of switch //ToDo: find distances
            drivetrain.hardStop();
            drivetrain.turn(45,true);//rotates to face switch
            lift.raiseToHeight(200);//raises lift to 1.97m
            drivetrain.driveStraight(55, 0.40);//drives closer to switch //ToDo: find good power that works

            intake.intakeSpitOut(1);//spits out cube
            sleep(1000);
            intake.stopIntake();//stops intake

            //extra stretch
//            drivetrain.turn(100,true);
//            long beginTimeHardStop = System.currentTimeMillis();
//            while (System.currentTimeMillis()-beginTimeHardStop < 3000) {
//
//                drivetrain.setPower(0.5,0.5);
//                intake.intakeSuckIn(1);
//            }



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

                drivetrain.setPower(0.4,0.4);
            }

        }

        System.out.println("Right Side Auto complete...");
    }
}

