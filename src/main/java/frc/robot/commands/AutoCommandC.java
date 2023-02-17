// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Drivetrain;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Wrist;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoCommandC extends SequentialCommandGroup {
  /** Creates a new AutoCommandC. */
  Wrist m_wrist;
  Arm m_arm;
  Claw m_claw;
  Drivetrain m_drivetrain;
  public AutoCommandC(Drivetrain drivetrain, Arm arm, Wrist wrist, Claw claw) {
    m_wrist = wrist;
    m_arm = arm;
    m_claw = claw;
    m_drivetrain = drivetrain;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new WristToStart(m_wrist),
      new AutoToTopPeg(m_drivetrain, m_arm, m_wrist),
      new ClawOpen(m_claw),
      new AutoC1(m_drivetrain, m_wrist),
      new ClawClose(m_claw),
      new AutoC2(m_drivetrain, m_wrist),
      new ClawOpen(m_claw),
      new AutoC3(m_drivetrain, m_wrist)

    );
  }
}
