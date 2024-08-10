package org.firstinspires.ftc.teamcode.ftc16072.Util;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public class Navigation {
    Robot robot;

    static double TRANSLATIONAL_KP = 0.001;
    static double TRANSLATIONAL_KI = 0;
    static double TRANSLATIONAL_KD = 0;
    static double TRANSLATIONAL_KF = 0;

    static double ROTATIONAL_KP = 0.001;
    static double ROTATIONAL_KI = 0;
    static double ROTATIONAL_KD = 0;
    static double ROTATIONAL_KF = 0;

    PIDFController PIDx, PIDy, PIDh;

    public Navigation(Robot robot){
        this.robot = robot;
        PIDx = new PIDFController(TRANSLATIONAL_KP,TRANSLATIONAL_KI,TRANSLATIONAL_KD,TRANSLATIONAL_KF);
        PIDy = new PIDFController(TRANSLATIONAL_KP,TRANSLATIONAL_KI,TRANSLATIONAL_KD,TRANSLATIONAL_KF);
        PIDh = new PIDFController(ROTATIONAL_KP,ROTATIONAL_KI,ROTATIONAL_KD,ROTATIONAL_KF);

    }
    public void driveFieldRelative(double forwardSpeed, double strafeRightSpeed, double rotateCWSpeed){
        double robotAngle = robot.controlHub.getYaw(AngleUnit.RADIANS);
        //convert to polar
        double theta = Math.atan2(forwardSpeed, strafeRightSpeed);
        double r = Math.hypot(forwardSpeed, strafeRightSpeed);
        
        theta = AngleUnit.normalizeRadians(theta - robotAngle);
        
        //convert to cartesian
        double newForwardSpeed = r * Math.sin(theta);
        double newStrafeRightSpeed = r * Math.cos(theta);
        
        robot.mecanumDrive.move(newForwardSpeed,newStrafeRightSpeed,rotateCWSpeed);

    }

    public boolean driveToPositionIN(double desiredX,double desiredY,double desiredHeading){
        SparkFunOTOS.Pose2D currentPosition = robot.otos.getOtosPosition();

        double errorX = desiredX - robot.otos.
        double errorY;
        double errorH;



    }
}
