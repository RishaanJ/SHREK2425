package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;

@TeleOp(name = "MainTeleop", group = "TeleOp")
public class MainTeleop extends OpMode {
    private Drive drive;
    private Intake intake;

    @Override
    public void init() {
        drive = new Drive(hardwareMap);
        intake = new Intake(hardwareMap.get(CRServo.class, "intakeServo"));

        telemetry.addLine("TeleOp Initialized");
    }

    @Override
    public void loop() {
        // Controller 1 for driving
        double x = -gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rotation = gamepad1.right_stick_x;
        drive.drive(x, y, rotation);

        if (gamepad1.left_bumper) {
            intake.runServo(1.0);
        } else if (gamepad1.left_trigger > 0.1) {
            intake.runServo(-1.0);
        } else {
            intake.stopServo();
        }

        telemetry.addData("Driving", "x: %.2f, y: %.2f, rotation: %.2f", x, y, rotation);
        telemetry.addLine("Intake Running");
        telemetry.update();
    }
}
