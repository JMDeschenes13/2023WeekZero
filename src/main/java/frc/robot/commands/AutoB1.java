// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Drivetrain;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Wrist;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoB1 extends ParallelCommandGroup {
  
  /** Creates a new AutoB1. */
  public AutoB1(Drivetrain drivetrain, Arm arm, Wrist wrist) {
    Drivetrain m_drivetrain = drivetrain;
    Wrist m_wrist = wrist;
    Arm m_arm = arm;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutoDriveB1(m_drivetrain),
      new ArmExtend(m_arm),
      new WristToTopPeg(m_wrist)
    );
  }
}
