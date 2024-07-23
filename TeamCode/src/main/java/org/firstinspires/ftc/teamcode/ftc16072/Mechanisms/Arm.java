package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import android.icu.text.Transliterator;

import com.acmerobotics.dashboard.config.Config;
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
@Config
public class Arm extends QQMechanism{
    public static final double TEST_SPEED = 0.4;
    public static final double WRIST_DOWN = 0;
    public static final double WRIST_UP = 1;
    public static final double kP = 0.01;
    public static final double MAX_SPEED = 1;
    public static final double ARM_TOLERANCE_THRESHOLD = 10;
    int positionOffset = 0;
    public int desiredPosition;
    public double armPower;
    public int currentPos;
    public int error;
    public DcMotor armMotor;
    public Servo wristServo;
    DigitalChannel hallSensor;


    public static double WRIST_INTAKE_POS = 0.92;
    public static double WRIST_TRANSFER_POS = 0.2;
    public static double WRIST_PLACING_BOTTOM_POS = 0.35;
    public static double WRIST_PLACING_TOP_POS = 0.5;
    public static int INTAKE_POSITION = 385;
    public static int CLIMBING_POSITION = 4800;
    public static int CLIMBED_POSITION = 3000;
    public static  int ROW_HEIGHT_IN_TICKS = 500;
    public static int MAX_SCORE_POSITION = 7500;
    public static int MIN_SCORE_POSITION = 5500;
    public static final int SCORING_THRESHOLD = 5000;
    public int scorePosition = 7500;

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
            resetArmPosition();
        }else {
            armMotor.setPower(-0.3);
        }
    }

    public void setDesiredPosition(int desiredPosition){
        this.desiredPosition = desiredPosition;

    }
    public void resetArmPosition(){
        positionOffset = armMotor.getCurrentPosition();
    }

    @Override
    public void update(){
        currentPos = armMotor.getCurrentPosition() - positionOffset;
        error = desiredPosition - currentPos;
        armPower = error * kP;
        armPower = Math.signum(armPower)*Math.min(Math.abs(armPower), MAX_SPEED);
        if (Math.abs(error) < ARM_TOLERANCE_THRESHOLD){
            armMotor.setPower(0.0);
        }else {
            armMotor.setPower(armPower);
        }
    }
    public void pixelRowUp() {
        scorePosition -= ROW_HEIGHT_IN_TICKS;
        scorePosition = Math.max(scorePosition, MIN_SCORE_POSITION);
    }
    public void pixelRowDown() {
        scorePosition += ROW_HEIGHT_IN_TICKS;
        scorePosition = Math.min(scorePosition, MAX_SCORE_POSITION);
}

public void setWristToScorePos(){
        double wristRange = WRIST_PLACING_TOP_POS - WRIST_PLACING_BOTTOM_POS;
        double armRange = MAX_SCORE_POSITION - MIN_SCORE_POSITION;

        double armOffset = desiredPosition - MIN_SCORE_POSITION;
        double wristOffset = armOffset * (wristRange/armRange);

        double desiredWristPos = wristOffset + WRIST_PLACING_BOTTOM_POS;

        desiredWristPos = Math.max(desiredWristPos, WRIST_PLACING_BOTTOM_POS);
        desiredWristPos = Math.min(desiredWristPos, WRIST_PLACING_TOP_POS);
        wristServo.setPosition(desiredWristPos);
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
