package org.firstinspires.ftc.teamcode.RelicRecoveryRev;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
/**
 * Created by User on 10/29/2017.
 */

public class GGHardware
{
    /* Public OP Mode Members*/
    public DcMotor motor1 = null;

    ColorSensor sensorColor;
    DistanceSensor sensorDistance;

    /* Local OP Mode Members*/
    HardwareMap hwMap  = null;
    private ElapsedTime period = new ElapsedTime();

    public final double RCLAW_CLOSE = 0.2;
  public final double RCLAW_OPEN = 0.6;
    public final double LCLAW_OPEN = 0.15;
    public final double PIVOT_MIN_RANGE = 0.15;
    public final double PIVOT_MID_RANGE = 0.30;
    public final double PIVOT_MAX_RANGE = 0.90;
    public static double LCLAW_CLOSE = 0.55;
    public DcMotor frontleft, frontright, backleft, backright ,lift1, funnel, pump, lift3;
    public Servo pivot, LClaw, RClaw, suctionArm;
    public float x, y, z, w, pwr;
    public static double deadzone = 0.2;

    /* Constructor*/
    public GGHardware()
    {


    }

    /*Initialize Standard Hardware Interfaces*/
    public void init(HardwareMap ahwMap)
    {
        hwMap = ahwMap;
        frontleft = hwMap.get(DcMotor.class, "fleft");
        frontright = hwMap.get(DcMotor.class, "fright");
        backleft = hwMap.get(DcMotor.class, "bleft");
        backright = hwMap.get(DcMotor.class, "bright");
        //lift3 = hwMap.get(DcMotor.class, "lift3");
        lift1 = hwMap.get(DcMotor.class, "lift1");
        funnel = hwMap.get(DcMotor.class, "funnel");
        LClaw = hwMap.get(Servo.class, "lclaw");
        RClaw = hwMap.get(Servo.class, "rclaw");
        suctionArm = hwMap.get(Servo.class, "suctionArm");
        pump = hwMap.get(DcMotor.class, "pump");
        //lift2 = hwMap.get(DcMotor.class, "lift2");
        pivot = hwMap.get(Servo.class, "pivot");
       // belt1 = hwMap.get(DcMotor.class, "belt1");bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbnbnbnnnnnnnnnnnnnnnnssnnnnssnhnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnssssssssssssssssssssssssssssnnbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb\.
        //belt2 = hwMap.get(DcMotor.class, "belt2");

        // get a reference to the color sensor.
        //sensorColor = hwMap.get(ColorSensor.class, "sensor_color_distance");

        // get a reference to the distance sensor that shares the same name.
        //sensorDistance = hwMap.get(DistanceSensor.class, "sensor_color_distance");

        //frontleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setDirection(DcMotor.Direction.REVERSE);

    }

    boolean reachedPosition = false;



    public void forwBakw(double motorPwr)
    {
        frontright.setPower(motorPwr);
        frontleft.setPower(motorPwr);
        backright.setPower(motorPwr);
        backleft.setPower(motorPwr);
    }


    public void driftRight()
    {
        frontright.setPower(1);
        frontleft.setPower(-1);
        backright.setPower(-1);
        backleft.setPower(1);
    }

    public void driftLeft()
    {
        frontright.setPower(-1);
        frontleft.setPower(1);
        backright.setPower(1);
        backleft.setPower(-1);
    }


    public void turnRight()
    {
        frontright.setPower(1);
        frontleft.setPower(-1);
        backright.setPower(1);
        backleft.setPower(-1);
    }

    public void turnLeft()
    {
        frontright.setPower(-1);
        frontleft.setPower(1);
        backright.setPower(-1);
        backleft.setPower(1);
    }


    public void runEncodersUntil(int encoderAmount)
    {
        reachedPosition = false;

        while(!reachedPosition)
        {
            if(Math.abs(backleft.getCurrentPosition()) > encoderAmount)
            {
                reachedPosition = true;
            }
            else
            {

            }

        }
    }

    public void resetEncoders()
    {
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runWithEncoders()
    {
        if(backleft != null)
        {
            backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
