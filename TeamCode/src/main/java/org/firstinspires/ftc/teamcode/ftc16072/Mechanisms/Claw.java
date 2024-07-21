package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestColorRangeSensor;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestServo;

import java.util.Arrays;
import java.util.List;

public class Claw extends QQMechanism{
    double LEFT_OPEN_POSITION = 1.0;
    double RIGHT_OPEN_POSITION = 0.0;
    double LEFT_CLOSED_POSITION = 0.7;
    double RIGHT_CLOSED_POSITION = 0.3;
    Servo leftClaw;
    Servo rightClaw;
    ColorRangeSensor leftSensor;
    ColorRangeSensor rightSensor;
    @Override
    public void init(HardwareMap hwMap) {
        leftClaw = hwMap.get(Servo.class,"left_claw");
        rightClaw = hwMap.get(Servo.class,"right_claw");
        leftSensor = hwMap.get(ColorRangeSensor.class,"left_sensor");
        rightSensor = hwMap.get(ColorRangeSensor.class, "right_sensor");

    }

    @Override
    public List<QQTest> getTests() {
        return Arrays.asList(
                new TestServo("claw left", LEFT_CLOSED_POSITION, LEFT_OPEN_POSITION,leftClaw),
                new TestServo("claw right", RIGHT_CLOSED_POSITION, RIGHT_OPEN_POSITION, rightClaw),
                new TestColorRangeSensor("color left",leftSensor),
                new TestColorRangeSensor("color right", rightSensor)
        );
    }
}
