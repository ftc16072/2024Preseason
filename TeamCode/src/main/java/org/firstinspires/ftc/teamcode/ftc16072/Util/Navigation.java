package org.firstinspires.ftc.teamcode.ftc16072.Util;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public class Navigation {
    Robot robot;
    public Navigation(Robot robot){
        this.robot = robot;
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
}
