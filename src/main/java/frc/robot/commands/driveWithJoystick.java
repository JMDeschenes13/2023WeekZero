// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Drivetrain;

public class driveWithJoystick extends CommandBase {
  Drivetrain m_drivetrain;
  Joystick m_driveJoystick;
  final SlewRateLimiter m_xspeedLimiter;
  final SlewRateLimiter m_yspeedLimiter;
  final SlewRateLimiter m_rotLimiter;
  boolean m_fieldRelative;

  /** Creates a new driveWithJoystick. */
  public driveWithJoystick(Drivetrain drivetrain, boolean fieldRelative, Joystick driveJoystick) {
    m_drivetrain = drivetrain;
    m_driveJoystick = driveJoystick;
    m_fieldRelative = fieldRelative;
    // Get the x speed. We are inverting this because Xbox controllers return
    // negative values when we push forward.
    
    m_xspeedLimiter = new SlewRateLimiter(12);
    m_yspeedLimiter = new SlewRateLimiter(12);
    m_rotLimiter = new SlewRateLimiter(10);
    addRequirements(drivetrain);
}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    final var xSpeed =
    -m_xspeedLimiter.calculate(MathUtil.applyDeadband(m_driveJoystick.getY() * -1, 0.2))
        * Drivetrain.kMaxSpeed;

// Get the y speed or sideways/strafe speed. We are inverting this because
// we want a positive value when we pull to the left. Xbox controllers
// return positive values when you pull to the right by default.
final var ySpeed =
    m_yspeedLimiter.calculate(MathUtil.applyDeadband(m_driveJoystick.getX() , 0.2))
        * Drivetrain.kMaxSpeed;

// Get the rate of angular rotation. We are inverting this because we want a
// positive value when we pull to the left (remember, CCW is positive in
// mathematics). Xbox controllers return positive values when you pull to
// the right by default.
final var rot =
    m_rotLimiter.calculate(MathUtil.applyDeadband(m_driveJoystick.getZ(), 0.2))
        * Drivetrain.kMaxAngularSpeed;
m_drivetrain.updateOdometry();
m_drivetrain.drive(xSpeed, ySpeed, rot, m_fieldRelative);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
