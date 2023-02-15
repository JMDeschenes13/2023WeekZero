// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Drivetrain;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Wrist;

public class AutoCommandSelector extends CommandBase {
  String m_autoSelected;
  Drivetrain m_drivetrain;
  Arm m_arm;
  Wrist m_wrist;
  Claw m_claw;
  /** Creates a new AutoCommandSelector. */
  public AutoCommandSelector(Drivetrain drivetrain, Arm arm, Wrist wrist, Claw claw, String autoSelected) {
    m_autoSelected = autoSelected;
    m_drivetrain = drivetrain;
    m_arm = arm;
    m_wrist = wrist;
    m_claw = claw;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain, arm, wrist, claw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(m_autoSelected == "A"){
      new AutoCommandA(m_drivetrain, m_arm, m_wrist, m_claw);
    }
    else{
      if(m_autoSelected == "B"){
        new AutoCommandB(m_drivetrain, m_arm, m_wrist, m_claw);
      }

    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
