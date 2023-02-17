// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;




import edu.wpi.first.math.MathUtil;

import edu.wpi.first.math.filter.SlewRateLimiter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//Subsystems
import frc.robot.subsystems.Wrist;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Arm;

//Commands
//import frc.robot.commands.ActuateClaw;
import frc.robot.commands.ClawClose;
import frc.robot.commands.ClawOpen;
import frc.robot.commands.DrivetrainLockWheels;
import frc.robot.commands.WristToBottom;
import frc.robot.commands.WristToFeederStation;
import frc.robot.commands.WristToMiddlePeg;
import frc.robot.commands.WristToMiddleShelf;
import frc.robot.commands.WristToTopPeg;
import frc.robot.commands.WristToTopShelf;
import frc.robot.commands.ArmExtend;
import frc.robot.commands.ArmRetract;
import frc.robot.Constants.OIConstants;

//Auto Commands

import frc.robot.commands.AutoCommandSelector;


/** Add your docs here. */
public class RobotContainer {

    private static final String kAutoA= "A";
    private static final String kAutoB = "B";
    private static final String kAutoC = "C";
    private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();


    private final SlewRateLimiter m_xspeedLimiter = new SlewRateLimiter(5);
    private final SlewRateLimiter m_yspeedLimiter = new SlewRateLimiter(5);
    private final SlewRateLimiter m_rotLimiter = new SlewRateLimiter(5);

   
    private final Joystick m_driveJoystick = new Joystick(OIConstants.kdriveJoystick);
    private final Joystick m_actuatorJoystick = new Joystick(OIConstants.kactuatorJoystick);


    // subsystems
    private final Claw m_claw = new Claw();
    private final Wrist m_wrist = new Wrist();
    private final Arm m_arm = new Arm();
    private final Drivetrain m_drivetrain = new Drivetrain();


    // commands 
    //private final ActuateClaw m_actuateClaw = new ActuateClaw(m_claw);
    private final ClawClose m_clawClose = new ClawClose(m_claw);
    private final ClawOpen m_clawOpen = new ClawOpen(m_claw);
    private final WristToBottom m_wristToBottom = new WristToBottom(m_wrist);
    private final WristToFeederStation m_wristToFeederStation = new WristToFeederStation(m_wrist);
    private final WristToMiddlePeg m_wristToMiddlePeg = new WristToMiddlePeg(m_wrist);
    private final WristToMiddleShelf m_wristToMiddleShelf = new WristToMiddleShelf(m_wrist);
    private final WristToTopPeg m_wristToTopPeg = new WristToTopPeg(m_wrist);
    private final WristToTopShelf m_wristToTopShelf = new WristToTopShelf(m_wrist);
    private final ArmExtend m_armExtend = new ArmExtend(m_arm);
    private final ArmRetract m_armRetract = new ArmRetract(m_arm);
    private final DrivetrainLockWheels m_lockWheels = new DrivetrainLockWheels(m_drivetrain);
    
    



    public RobotContainer(){
        m_chooser.setDefaultOption("A", kAutoA);
        m_chooser.addOption("B", kAutoB);
        m_chooser.addOption("C", kAutoC);
        SmartDashboard.putData("Auto choices", m_chooser);

        
        

        configureBindings();
        m_drivetrain.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> driveWithJoystick(true)));
    }

    private void configureBindings(){

        new JoystickButton(m_actuatorJoystick, Button.kLeftBumper.value).onTrue(m_clawClose);
        new JoystickButton(m_actuatorJoystick, Button.kRightBumper.value).onTrue(m_clawOpen);
        //new JoystickButton(m_driveJoystick, 3).onTrue(m_actuateClaw);
        //new JoystickButton(m_driveJoystick, 1).onTrue(m_clawOpen);
        //new JoystickButton(m_driveJoystick, 2).onTrue(m_clawClose);
        //new JoystickButton(m_driveJoystick, 7).onTrue(m_wristToBottom);
        //new JoystickButton(m_driveJoystick, 8).onTrue(m_wristToFeederStation);
        //new JoystickButton(m_driveJoystick, 9).onTrue(m_wristToMiddlePeg);
        //new JoystickButton(m_driveJoystick, 10).onTrue(m_wristToMiddleShelf);
        //new JoystickButton(m_driveJoystick, 11).onTrue(m_wristToTopPeg);
        //new JoystickButton(m_driveJoystick, 12).onTrue(m_wristToTopShelf);
        new JoystickButton(m_driveJoystick, 4).onTrue(m_armRetract);
        new JoystickButton(m_driveJoystick, 6).onTrue(m_armExtend);
        new JoystickButton(m_driveJoystick, 12).onTrue(m_lockWheels);
        new JoystickButton(m_actuatorJoystick, Button.kA.value).onTrue(m_wristToBottom);
        new JoystickButton(m_actuatorJoystick, Button.kB.value).onTrue(m_wristToFeederStation);
        new JoystickButton(m_actuatorJoystick, Button.kX.value).onTrue(m_wristToMiddlePeg);
        new JoystickButton(m_actuatorJoystick, Button.kY.value).onTrue(m_wristToMiddleShelf);
        new JoystickButton(m_actuatorJoystick, Button.kStart.value).onTrue(m_wristToTopPeg);
        new JoystickButton(m_actuatorJoystick, Button.kBack.value).onTrue(m_wristToTopShelf);



       

    }

    public Command getAutonomousCommand() {
        m_autoSelected = m_chooser.getSelected();
        return new AutoCommandSelector(m_drivetrain, m_arm, m_wrist, m_claw, m_autoSelected);
        }
            
      

    private void driveWithJoystick(boolean fieldRelative) {
        // Get the x speed. We are inverting this because Xbox controllers return
        // negative values when we push forward.
        final var xSpeed =
            -m_xspeedLimiter.calculate(MathUtil.applyDeadband(m_driveJoystick.getY(), 0.15))
                * Drivetrain.kMaxSpeed;
    
        // Get the y speed or sideways/strafe speed. We are inverting this because
        // we want a positive value when we pull to the left. Xbox controllers
        // return positive values when you pull to the right by default.
        final var ySpeed =
            -m_yspeedLimiter.calculate(MathUtil.applyDeadband(m_driveJoystick.getX(), 0.1))
                * Drivetrain.kMaxSpeed;
    
        // Get the rate of angular rotation. We are inverting this because we want a
        // positive value when we pull to the left (remember, CCW is positive in
        // mathematics). Xbox controllers return positive values when you pull to
        // the right by default.
        final var rot =
            -m_rotLimiter.calculate(MathUtil.applyDeadband(m_driveJoystick.getZ(), 0.1))
                * Drivetrain.kMaxAngularSpeed;
        m_drivetrain.updateOdometry();
        m_drivetrain.drive(xSpeed, ySpeed, rot, fieldRelative);
      }
}

