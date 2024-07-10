package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestServo;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestSwitch;

import java.util.Arrays;
import java.util.List;

public class Arm extends QQMechanism{
    public static final double TEST_SPEED = 0.4;
    public static final double WRIST_DOWN = 0;
    public static final double WRIST_UP = 1;
    public static final double kP = 0.01;
    public static final double MAX_SPEED = 1;
    public static final double ARM_TOLERANCE_THRESHOLD = 20;
    int desiredPosition;
    double armPower;
    int currentPos;
    int error;
    DcMotor armMotor;
    Servo wristServo;
    DigitalChannel hallSensor;
    @Override
    public void init(HardwareMap hwMap) {
        armMotor = hwMap.get(DcMotor.class,"arm_motor");
        wristServo = hwMap.get(Servo.class,"wrist_servo");
        hallSensor = hwMap.get(DigitalChannel.class,"arm_hall");
        hallSensor.setMode(DigitalChannel.Mode.INPUT);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public boolean isArmDown(){
        return !hallSensor.getState();
    }

    public void armToGround(){//make a node once behaviour trees are implemented
        if (isArmDown()){
            armMotor.setPower(0.0);
        }else {
            armMotor.setPower(-0.5);
        }
    }

    public void setDesiredPosition(int desiredPosition){
        this.desiredPosition = desiredPosition;

    }

    @Override
    public void update(){
        currentPos = armMotor.getCurrentPosition();
        error = desiredPosition - currentPos;
        armPower = error * kP;
        armPower = Math.max(armPower, MAX_SPEED);
        if (Math.abs(error) < ARM_TOLERANCE_THRESHOLD){
            armMotor.setPower(0.0);
        }else {
            armMotor.setPower(armPower);
        }
    }


    @Override
    public List<QQTest> getTests() {
        return Arrays.asList(
                new TestMotor("arm up", TEST_SPEED, armMotor),
                new TestMotor("arm down", -TEST_SPEED, armMotor),
                new TestServo("wrist", WRIST_DOWN, WRIST_UP,wristServo),
                new TestSwitch("hall",hallSensor));
    }
}
