// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Drivetrain;

public class DrivetrainLockWheels extends CommandBase {
  /** Creates a new DrivetrainLockWheels. */
  Drivetrain m_drivetrain;
  public DrivetrainLockWheels(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      SwerveModuleState[] moduleStates = {
        new SwerveModuleState(0,new Rotation2d((7*Math.PI)/4)),
        new SwerveModuleState(0, new Rotation2d((5*Math.PI)/4)),
        new SwerveModuleState(0, new Rotation2d((Math.PI/4))),
        new SwerveModuleState(0 , new Rotation2d((3*Math.PI)/4))
  
      };
      m_drivetrain.setModuleStates(moduleStates);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0,0,0,true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
