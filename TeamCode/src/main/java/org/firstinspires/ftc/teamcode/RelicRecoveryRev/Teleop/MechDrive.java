package org.firstinspires.ftc.teamcode.RelicRecoveryRev.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.RelicRecoveryRev.GGHardware;

/**
 * Created by User on 10/30/2017.
 */

@TeleOp(name="MTeleOp",group="TeleOp")
public class MechDrive extends LinearOpMode {

    GGHardware robot = new GGHardware();
    private ElapsedTime  runtime= new ElapsedTime();

    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        boolean elevatorButtonPressed = false; //is "open pivot" button currently pressed
        waitForStart();
        boolean funnelPressed = false;

        while (opModeIsActive()) {
            getJoyVals();

            robot.pwr = robot.y; //this can be tweaked for exponential power increase

            robot.frontright.setPower(Range.clip(robot.pwr - robot.x + robot.z, -1, 1));
            robot.backleft.setPower(Range.clip(robot.pwr - robot.x - robot.z, -1, 1));
            robot.frontleft.setPower(Range.clip(robot.pwr + robot.x - robot.z, -1, 1));
            robot.backright.setPower(Range.clip(robot.pwr + robot.x + robot.z, -1, 1));

            //opens elevator, if pressed wont run again until you let go of the button
            if (gamepad2.right_bumper) {
                robot.RClaw.setPosition(robot.RCLAW_CLOSE);
                robot.LClaw.setPosition(robot.LCLAW_CLOSE);

            }
            if (gamepad2.left_bumper) {
                robot.RClaw.setPosition(robot.RCLAW_OPEN);
                robot.LClaw.setPosition(robot.LCLAW_OPEN);

            }

            /*if (gamepad2.left_trigger > 10)
            {robot.RClaw.setPosition(robot.CLAWS_MID_RANGE);
                robot.LClaw.setPosition(robot.CLAWS_MID_RANGE);
            } */

            telemetry.addData("elevator pressed ", elevatorButtonPressed);
            telemetry.update();
            //lift up
            if (gamepad2.y)
            {
                robot.lift1.setPower(1);
                robot.lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                //lift2.setPower(.9);
            }
            //lift down
            else if (gamepad2.a)
            {
                robot.lift1.setPower(-1);
                robot.lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                //lift2.setPower(-.9);
            }
            //stop lift
            else
            {
                robot.lift1.setPower(0);
            }
            if(gamepad2.dpad_down)
            {
                robot.pivot.setPosition(robot.PIVOT_MAX_RANGE);
            }
            if(gamepad2.dpad_up)
            {
                robot.pivot.setPosition(robot.PIVOT_MIN_RANGE);
            }
            if(gamepad2.dpad_right)
            {
                robot.pivot.setPosition(robot.PIVOT_MAX_RANGE);
            }
            //belts up
            /*if (gamepad2.x)
            {
                if(funnelPressed)
                {
                    robot.funnel.setPower(0.0);
                    funnelPressed = false;
                }
                else
                {
                    robot.funnel.setPower(1.0);
                    funnelPressed = true;
                }
                sleep(250);
            }
            */
        }
    }

    public void getJoyVals() {
        float pwrFactor = (float)1;
        float pwrFactorY = (float)0.5;
        robot.y = pwrFactorY * gamepad1.right_stick_y; // joyval = -1 to 1; forward right joy 0 = stop -1 = reverse 1 = forward
        robot.x = pwrFactor * gamepad1.right_stick_x; //-1 to 1
        robot.z = pwrFactor * gamepad1.left_stick_x;  //-1 to 1
        robot.w = pwrFactor * gamepad1.left_stick_y;  //-1 to 1
        //updates joystick values

        if (Math.abs(robot.x) < robot.deadzone) robot.x = 0;
        if (Math.abs(robot.y) < robot.deadzone) robot.y = 0;
        if (Math.abs(robot.z) < robot.deadzone) robot.z = 0;
        if (Math.abs(robot.w) < 0.9) robot.w = 0;
        //checks deadzones
    }

    boolean reachedPosition = false;

   /* public void runEncodersUntil(int encoderAmount)
    {
        encoderAmount = Math.abs(encoderAmount);
        while(!(Math.abs(robot.lift3.getCurrentPosition()) > encoderAmount))
        {
            telemetry.addData("Encoder Value", robot.lift3.getCurrentPosition());
            telemetry.update();

        }
    }

    public void resetEncoders()
    {
        robot.lift3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runWithEncoders()
    {
        if(robot.lift3 != null)
        {
            robot.lift3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }*/

   /* public void openElevator()
    {
        resetEncoders();
        runWithEncoders();
        robot.lift3.setPower(-.75);
        runEncodersUntil(50);
        robot.lift3.setPower(0);

    }*/

    /*public void closeElevator()
    {
        resetEncoders();
        runWithEncoders();
        robot.lift3.setPower(.75);
        runEncodersUntil(50);
        robot.lift3.setPower(0);
    }*/
}



