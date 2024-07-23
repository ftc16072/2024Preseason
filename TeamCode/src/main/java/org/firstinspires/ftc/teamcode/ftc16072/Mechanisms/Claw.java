package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestColorRangeSensor;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestServo;

import java.util.Arrays;
import java.util.List;

public class Claw extends QQMechanism{
    final double LEFT_OPEN_POSITION = 1.0;
    final double RIGHT_OPEN_POSITION = 0.0;
    final double LEFT_CLOSED_POSITION = 0.7;
    final double RIGHT_CLOSED_POSITION = 0.4;
    public static double RIGHT_SENSOR_THRESHOLD_MM = 70; //placeholder number
    public static double LEFT_SENSOR_THRESHOLD_MM = 70; //placeholder


    public Servo leftClaw;
    public Servo rightClaw;
    public ColorRangeSensor leftSensor;
    public ColorRangeSensor rightSensor;
    ElapsedTime leftTimer;
    ElapsedTime rightTimer;
    @Override
    public void init(HardwareMap hwMap) {
        leftClaw = hwMap.get(Servo.class,"left_claw");
        rightClaw = hwMap.get(Servo.class,"right_claw");
        leftSensor = hwMap.get(ColorRangeSensor.class,"left_sensor");
        rightSensor = hwMap.get(ColorRangeSensor.class, "right_sensor");
        leftTimer = new ElapsedTime();
        rightTimer = new ElapsedTime();

    }
    public boolean isLeftClosed;
    public boolean isRightClosed;

    public void closeLeft(){
        leftClaw.setPosition(LEFT_CLOSED_POSITION);
        if(leftTimer.seconds() > 0.5) {
            leftTimer.reset();
        }else if(leftTimer.seconds() > 0.3){
            isLeftClosed = true;
        }
    }
    public void openLeft(){
        leftClaw.setPosition(LEFT_OPEN_POSITION);
        isLeftClosed = false;
    }
    public void openRight(){
        rightClaw.setPosition(RIGHT_OPEN_POSITION);
        isRightClosed = false;
    }
    public void closeRight(){
        rightClaw.setPosition(RIGHT_CLOSED_POSITION);
        if (rightTimer.seconds() > 0.5){
            rightTimer.reset();
        } else if (rightTimer.seconds() > 0.3) {
            isRightClosed = true;
        }
    }

    public boolean isLeftPixelInReach(){
        if(leftSensor.getDistance(DistanceUnit.MM) < LEFT_SENSOR_THRESHOLD_MM){
            return true;
        } return false;
    }
    public boolean isRightPixelInReach(){
        if(rightSensor.getDistance(DistanceUnit.MM) < RIGHT_SENSOR_THRESHOLD_MM){
            return true;
        } return false;
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
