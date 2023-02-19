// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;





import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
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
import frc.robot.commands.WristToDrive;
import frc.robot.commands.WristToFeederStation;
import frc.robot.commands.WristToMiddlePeg;
import frc.robot.commands.WristToMiddleShelf;
import frc.robot.commands.WristToStart;
import frc.robot.commands.WristToTopPeg;
import frc.robot.commands.WristToTopShelf;
import frc.robot.commands.driveWithJoystick;
import frc.robot.commands.ArmExtend;
import frc.robot.commands.ArmRetract;
import frc.robot.commands.AutoCommandB;
import frc.robot.commands.AutoCommandF;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.ChargeStationLevel;

//Auto Commands

import frc.robot.commands.AutoCommandSelector;


/** Add your docs here. */
public class RobotContainer {

    private DriverStation.Alliance allianceColor;
    private static final String kAutoA= "A";
    private static final String kAutoB = "B";
    private static final String kAutoC = "C";
    private static final String kAutoD = "D";
    private static final String kAutoE = "E";
    private static final String kAutoF = "F";
    private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();


    

   
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
    private final WristToStart m_wristToStart = new WristToStart(m_wrist);
    private final ArmExtend m_armExtend = new ArmExtend(m_arm);
    private final ArmRetract m_armRetract = new ArmRetract(m_arm);
    private final DrivetrainLockWheels m_lockWheels = new DrivetrainLockWheels(m_drivetrain);
    private final ChargeStationLevel m_level = new ChargeStationLevel(m_drivetrain);
    private final WristToDrive m_wristToDrive = new WristToDrive(m_wrist);
    
    



    public RobotContainer(){
        allianceColor = DriverStation.getAlliance();
        m_chooser.setDefaultOption("A", kAutoA);
        m_chooser.addOption("B", kAutoB);
        m_chooser.addOption("C", kAutoC);
        m_chooser.addOption("D", kAutoD);
        m_chooser.addOption("E", kAutoE);
        SmartDashboard.putData("Auto choices", m_chooser);
        SmartDashboard.putString("alliance", allianceColor.name());

        
        

        configureBindings();
        m_drivetrain.setDefaultCommand(new driveWithJoystick(m_drivetrain, !m_driveJoystick.getRawButton(2), m_driveJoystick));
    }

    private void configureBindings(){

        new JoystickButton(m_actuatorJoystick, Button.kLeftBumper.value).onTrue(m_clawClose);
        new JoystickButton(m_actuatorJoystick, Button.kRightBumper.value).onTrue(m_clawOpen);
        new JoystickButton(m_driveJoystick, 4).onTrue(m_armRetract);
        new JoystickButton(m_driveJoystick, 6).onTrue(m_armExtend);
        //new JoystickButton(m_driveJoystick, 12).onTrue(m_lockWheels);
        //new JoystickButton(m_driveJoystick, 1).onTrue(m_level);
        new JoystickButton(m_actuatorJoystick, Button.kA.value).onTrue(m_wristToBottom);
        new JoystickButton(m_actuatorJoystick, Button.kB.value).onTrue(m_wristToFeederStation);
        new JoystickButton(m_actuatorJoystick, Button.kX.value).onTrue(m_wristToMiddlePeg);
        new JoystickButton(m_actuatorJoystick, Button.kY.value).onTrue(m_wristToStart);
        new JoystickButton(m_actuatorJoystick, Button.kStart.value).onTrue(m_wristToTopPeg);
        new JoystickButton(m_actuatorJoystick, Button.kBack.value).onTrue(m_wristToTopShelf);
        new JoystickButton(m_actuatorJoystick, Button.kRightStick.value).onTrue(m_wristToMiddleShelf);
        new JoystickButton(m_actuatorJoystick, Button.kLeftStick.value).onTrue(m_wristToDrive);



       


    }

    public Command getAutonomousCommand() {
        m_autoSelected = m_chooser.getSelected();
        Command m_autoCommand = new AutoCommandF(m_drivetrain, m_arm, m_wrist, m_claw);
        return m_autoCommand;
        }
            
      

    
}

