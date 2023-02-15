// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Drivetrain;

public class ChargeStationLevel extends CommandBase {
  Drivetrain m_drivetrain;
  PIDController m_PIDController = new PIDController(.05,0,0);
  /** Creates a new ChargeStationLevel. */
  public ChargeStationLevel(Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    m_PIDController.setSetpoint(0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.drive(m_PIDController.calculate(m_drivetrain.m_gyro.getPitch()),0,0,true);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_drivetrain.m_gyro.getPitch() - 3 > -3 || m_drivetrain.m_gyro.getPitch() + 3 < 3){
      return true;
    }
    else{
      return false;
    }
  }
}
