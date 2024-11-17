package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;

@TeleOp(name = "HelloWorld")
@Config
public class HelloWorld extends LinearOpMode {
    private Intake intake;
    private Drive drive;

    @Override
    public void runOpMode() {
        // Initialize Intake subsystem
        CRServo servo = hardwareMap.get(CRServo.class, "intakeServo");
        intake = new Intake(servo);

        // Initialize Drive subsystem
        drive = new Drive(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            // Intake controls
            if (gamepad1.x) {
                intake.runServo(1.0); // Full speed forward
            } else {
                intake.stopServo(); // Stop the servo
            }

            // Mecanum drive controls
            double x = gamepad1.left_stick_x; // Strafing
            double y = -gamepad1.left_stick_y; // Forward/Backward (invert for FTC joystick)
            double rotation = gamepad1.right_stick_x; // Turning
            drive.drive(x, y, rotation);

            // Telemetry for debugging
            telemetry.addData("Servo Power", servo.getPower());
            telemetry.addData("Drive", "x: %.2f, y: %.2f, rotation: %.2f", x, y, rotation);
            telemetry.update();
        }

        // Stop motors and servos when the OpMode ends
        drive.stop();
        intake.stopServo();
    }
}
