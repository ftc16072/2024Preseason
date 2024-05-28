package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;

import java.util.Arrays;
import java.util.List;

public class MecanumDrive extends QQMechanism{
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;

    double speedMultiplier = 1.0;


    @Override
    public void init(HardwareMap hwMap) {
        frontLeftMotor = hwMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hwMap.get(DcMotor.class, "frontRightMotor");
        backLeftMotor = hwMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hwMap.get(DcMotor.class,"backRightMotor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        List<DcMotor> motors = Arrays.asList(frontLeftMotor, frontRightMotor,backLeftMotor,backRightMotor);
        for (DcMotor motor : motors){
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    @Override
    public List<QQTest> getTests() {
        final double TEST_SPEED = 0.2;

        return Arrays.asList(
                new TestMotor("Front Left", TEST_SPEED, frontLeftMotor),
                new TestMotor("Front Right", TEST_SPEED, frontRightMotor),
                new TestMotor("Back Left", TEST_SPEED, backLeftMotor),
                new TestMotor("Back Right", TEST_SPEED, backRightMotor)
        );
    }
    public void move (double forwardSpeed, double strafeRightSpeed, double turnClockwiseSpeed){
        double frontLeftPower = forwardSpeed + strafeRightSpeed + turnClockwiseSpeed;
        double frontRightPower = forwardSpeed - strafeRightSpeed - turnClockwiseSpeed;
        double backRightPower = forwardSpeed + strafeRightSpeed - turnClockwiseSpeed;
        double backLeftPower = forwardSpeed - strafeRightSpeed + turnClockwiseSpeed;

        setPowers(frontLeftPower,frontRightPower,backRightPower,backLeftPower);

    }
    private void setPowers(double frontLeftPower, double frontRightPower,double backRightPower, double backLeftPower){
        double maxSpeed = 1.0;

        frontLeftPower *= speedMultiplier;
        frontRightPower *= speedMultiplier;
        backLeftPower *= speedMultiplier;
        backRightPower *= speedMultiplier;

        frontLeftPower = Math.max(Math.abs(frontLeftPower), maxSpeed);
        frontRightPower = Math.max(Math.abs(frontRightPower), maxSpeed);
        backLeftPower = Math.max(Math.abs(backLeftPower), maxSpeed);
        backRightPower = Math.max(Math.abs(backRightPower), maxSpeed);

        backLeftPower /= maxSpeed;
        backRightPower /= maxSpeed;
        frontLeftPower /= maxSpeed;
        frontRightPower /= maxSpeed;

        frontRightMotor.setPower(frontRightPower);
        frontLeftMotor.setPower(frontLeftPower);
        backRightMotor.setPower(backRightPower);
        backLeftMotor.setPower(backLeftPower);
    }
}

