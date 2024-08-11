package org.firstinspires.ftc.teamcode.ftc16072.Util;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDFController {
    double kP;
    double kI;
    double kD;
    double kF;

    double sumErrors;
    double lastError;
    ElapsedTime timer = new ElapsedTime();

    public PIDFController(double kP,double kI,double kD,double kF){
        this.kP = kP;
        this.kD = kD;
        this.kI = kI;
        this.kF = kF;
    }
    public void reset(){
        sumErrors = 0;
        lastError = 0;
        timer.reset();
    }
    public double calculate(double desired, double current){
        double error = desired - current;
        double derivative = (error-lastError)/timer.seconds();
        sumErrors += error * timer.seconds();

        double result = (error*kP) + (sumErrors*kI) + (derivative*kD) + kF;

        timer.reset();
        lastError = error;

        return result;

    }

}
