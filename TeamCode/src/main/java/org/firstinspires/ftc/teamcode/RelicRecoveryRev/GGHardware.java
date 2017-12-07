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
    //Claw Attachment servo positions

     //RCLAW_OPEN > RCLAW_CLOSE
     public final double RCLAW_CLOSE = 0.2;
     public final double RCLAW_OPEN = 0.6;
    //LCLAW_OPEN < LCLAW_CLOSE
     public final double LCLAW_OPEN = 0.15;
     public static double LCLAW_CLOSE = 0.55;

    //Positions for the colorArm
    public final double PIVOT_MIN_RANGE = 0.15;
    public final double PIVOT_MID_RANGE = 0.30;
    public final double PIVOT_MAX_RANGE = 0.90;

    // Instantiating DcMotor objects
    public DcMotor frontleft, frontright, backleft, backright ,lift1, pump;
    // Instantiating Servo objects
    public Servo pivot, LClaw, RClaw, suctionArm;
    //Floats used in telling the position of the joysticks. x & Y for joystick1. z & w for joystick2.
    public float x, y, z, w, pwr;
    //Used to prevent "ghost robot"
    public static double deadzone = 0.2;
    //Boolean used in the encoder methods to tell when the robot has reached the correct encoder value
    boolean reachedPosition = false;


    /* Constructor for our robot*/
    public GGHardware()
    {


    }

    /*Initialize Standard Hardware Interfaces*/
    public void init(HardwareMap ahwMap)
    {
        hwMap = ahwMap;
        //Four wheels
        frontleft = hwMap.get(DcMotor.class, "fleft");
        frontright = hwMap.get(DcMotor.class, "fright");
        backleft = hwMap.get(DcMotor.class, "bleft");
        backright = hwMap.get(DcMotor.class, "bright");

        //Objects common to both attachments
        pivot = hwMap.get(Servo.class, "pivot");
        lift1 = hwMap.get(DcMotor.class, "lift1");

        //Extra objects for the suction attachment
        pump = hwMap.get(DcMotor.class, "pump");
        suctionArm = hwMap.get(Servo.class, "suctionArm");

        //Extra objects for claw attachment
        LClaw = hwMap.get(Servo.class, "lclaw");
        RClaw = hwMap.get(Servo.class, "rclaw");


        // Set directions of the 4 wheels
        // Uncomment frontleft if it changes direction
            //frontleft.setDirection(DcMotor.Direction.REVERSE);
            backleft.setDirection(DcMotor.Direction.REVERSE);

    }


/*
 * Moves the Robot forward of backwards
 * motorPwr: wanted motor speed for all 4 wheels ( range: -1.0 - 1.0)
 */
    public void forwBakw(double motorPwr)
    {
        frontright.setPower(motorPwr);
        frontleft.setPower(motorPwr);
        backright.setPower(motorPwr);
        backleft.setPower(motorPwr);
    }

/*
 * Robot drifts to the right at a speed of 1.0
 *
 */
    public void driftRight()
    {
        frontright.setPower(1);
        frontleft.setPower(-1);
        backright.setPower(-1);
        backleft.setPower(1);
    }
/*
 * Robot drifts to the left at a speed of 1.0
 *
 */

    public void driftLeft()
    {
        frontright.setPower(-1);
        frontleft.setPower(1);
        backright.setPower(1);
        backleft.setPower(-1);
    }
/*
 * Robot turns to the right at a speed of 1.0
 */
    public void turnRight()
    {
        frontright.setPower(1);
        frontleft.setPower(-1);
        backright.setPower(1);
        backleft.setPower(-1);
    }
/*
 * Robot turns left at a speed of 1.0
 */
    public void turnLeft()
    {
        frontright.setPower(-1);
        frontleft.setPower(1);
        backright.setPower(-1);
        backleft.setPower(1);
    }
/*
 * Method that changes the value of reachedPosition to false if the robot has reached the specified encoder value
 * encoderAmount: encoder value the robot should stop at
 *
 */

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
                backleft.setPower(0.0);
                backright.setPower(0.0);
                frontleft.setPower(0.0);
                frontright.setPower(0.0);
            }

        }
    }
/*
 * Method that resets the value of the backleft encoder to zero using the STOP_AND_RESET_ENCODER RunMode
 */
    public void resetEncoders()
    {

        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
/*
 * Method that changes the RunMode of the motors to RUN_USING_ENCODER
 *  Must use this in order for the encoder to work
 */

    public void runWithEncoders()
    {
        if(backleft != null)
        {
            backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
