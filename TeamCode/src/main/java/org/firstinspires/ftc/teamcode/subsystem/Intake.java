package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;

public class Intake {
    private CRServo intakeServo;

    // Constructor to initialize the servo
    public Intake(CRServo intakeServo) {
        this.intakeServo = intakeServo;
    }

    // Method to run the servo at a specific power
    public void runServo(double power) {
        intakeServo.setPower(power);
    }

    // Method to stop the servo
    public void stopServo() {
        intakeServo.setPower(0);
    }
}
