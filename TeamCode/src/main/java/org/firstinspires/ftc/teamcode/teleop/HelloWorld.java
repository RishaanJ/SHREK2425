package org.firstinspires.ftc.teamcode.teleop;

import org.firstinspires.ftc.teamcode.subsystem.Intake;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "IntakeTest")
@Config
public class HelloWorld extends LinearOpMode {
    private Intake intake;

    @Override
    public void runOpMode() {
        CRServo servo = hardwareMap.get(CRServo.class, "intakeServo");
        intake = new Intake(servo);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.x) {
                intake.runServo(1.0); // Full speed forward
            } else {
                intake.stopServo(); // Stop the servo
            }

            telemetry.addData("Servo Power", servo.getPower());
            telemetry.update();
        }
    }
}
