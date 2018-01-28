package org.usfirst.frc.team6579.robot.subsystem;

/**
 * This interface can be used to identify a SubSystem.
 *
 * Created by Jiah on 14/2/17.
 * Copied over to PowerUp code on 28/01/2018 by Jiah Pang
 */
public interface SubSystem {

    /**
     * Any SubSystem must be able to publish statistics about itself when requested.
     * These statistics should be published to the SmartDashboard.
     */
    public void publishStats();

    /**
     * This will only get called during test periodic which is only done in the driver station.
     * This allows us to specifically test a subsystem without affecting our teleop code.
     */
    public void test();
}
