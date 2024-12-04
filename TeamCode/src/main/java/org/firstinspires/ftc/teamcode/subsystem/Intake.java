package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;

public class Intake {
    private CRServo intakeServo;

    // init servo
    public Intake(CRServo intakeServo) {
        this.intakeServo = intakeServo;
    }

    // function to run servo provide a power input
    public void runServo(double power) {
        intakeServo.setPower(power);
    }

    // func to stop servo
    public void stopServo() {
        intakeServo.setPower(0);
    }
}
