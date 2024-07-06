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
    public static final int WRIST_DOWN = 0;
    public static final int WRIST_UP = 1;
    DcMotor armMotor;
    Servo wristServo;
    DigitalChannel hallSensor;
    @Override
    public void init(HardwareMap hwMap) {
        armMotor = hwMap.get(DcMotor.class,"arm_motor");
        wristServo = hwMap.get(Servo.class,"wrist_servo");
        hallSensor = hwMap.get(DigitalChannel.class,"arm_hall");
        hallSensor.setMode(DigitalChannel.Mode.INPUT);
    }
    public boolean isArmDown(){
        return !hallSensor.getState();
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
