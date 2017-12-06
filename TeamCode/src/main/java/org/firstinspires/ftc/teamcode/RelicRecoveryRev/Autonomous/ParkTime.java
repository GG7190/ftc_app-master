package org.firstinspires.ftc.teamcode.RelicRecoveryRev.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RelicRecoveryRev.GGHardware;

/**
 * Created by User on 10/30/2017.
 */
@Autonomous(name="ParkTime", group = "Autonomous")
public class ParkTime extends LinearOpMode
{

    GGHardware robot  = new GGHardware();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        boolean finished = false;

        waitForStart();

        while (opModeIsActive()) {

            while (!finished) {

                robot.forwBakw(1.0);
                sleep(200);
                forwBackw(0); //turns motors off
                robot.RClaw.setPosition(robot.RCLAW_OPEN);
                robot.LClaw.setPosition(robot.LCLAW_OPEN);
                sleep(150);
                resetEncoders();
                runWithEncoders();
                forwBackw(.75);
                runEncodersUntil(1000);
                stop();
                finished = true;

            }
        }
    }
    boolean reachedPosition = false;



    public void forwBackw(double motorPwr)
    {
        robot.frontright.setPower(motorPwr);
        robot.frontleft.setPower(motorPwr);
        robot.backright.setPower(motorPwr);
        robot.backleft.setPower(motorPwr);
    }


    public void driftRight()
    {
        robot.frontright.setPower(1);
        robot.frontleft.setPower(-1);
        robot.backright.setPower(-1);
        robot.backleft.setPower(1);
    }

    public void driftLeft()
    {
        robot.frontright.setPower(-1);
        robot.frontleft.setPower(1);
        robot.backright.setPower(1);
        robot.backleft.setPower(-1);
    }


    public void turnRight()
    {
        robot.frontright.setPower(1);
        robot.frontleft.setPower(-1);
        robot.backright.setPower(1);
        robot.backleft.setPower(-1);
    }

    public void turnLeft()
    {
        robot.frontright.setPower(-1);
        robot.frontleft.setPower(1);
        robot.backright.setPower(-1);
        robot.backleft.setPower(1);
    }





}