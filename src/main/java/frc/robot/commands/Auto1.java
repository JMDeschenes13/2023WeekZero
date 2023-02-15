// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Drivetrain;
import frc.robot.subsystems.Wrist;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto1 extends ParallelCommandGroup {
  /** Creates a new Auto1. */
  public Auto1(Drivetrain drivetrain, Wrist wrist) {
    Drivetrain m_drivetrain = drivetrain;
    Wrist m_wrist = wrist;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutoDrive1(m_drivetrain),
      new WristToTopPeg(m_wrist)

    );
  }
}
