package com.federico.controllers;

import com.federico.mocks.HVACMock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EnvironmentControllerTest {
    HVACMock hvacMock = new HVACMock();
    EnvironmentController environmentController = new EnvironmentController(hvacMock);

    @org.junit.Before
    public void reset() {
        hvacMock.resetMock();
    }

    @org.junit.Test
    public void tick_TemperatureInRange_NoAction() {
        environmentController.tick();
        assertFalse(hvacMock.getCoolStatus());
        assertFalse(hvacMock.getHeatStatus());
        assertFalse(hvacMock.getFanStatus());
    }

    @org.junit.Test
    public void tick_TemperatureTooHot_CoolDown() {
        hvacMock.setHotRoom();
        environmentController.tick();
        assertTrue(hvacMock.getCoolStatus());
        assertFalse(hvacMock.getHeatStatus());
        assertTrue(hvacMock.getFanStatus());
    }

    @org.junit.Test
    public void tick_TemperatureTooCold_WarmUp() {
        hvacMock.setColdRoom();
        environmentController.tick();
        assertTrue(hvacMock.getHeatStatus());
        assertTrue(hvacMock.getFanStatus());
        assertFalse(hvacMock.getCoolStatus());
    }

    @org.junit.Test
    public void tick_Within3MinsOfTurningCoolerOff_DontTurnItOn() {
        hvacMock.resetMock();
        environmentController.turnOnCool();
        environmentController.turnOffCool();
        for (int i = 0 ; i<2 ; i++) {
            environmentController.tick();
            environmentController.turnOnCool();
            assertTrue(hvacMock.getCoolStatus());
        }
    }
    @org.junit.Test
    public void tick_AfterTurningHeatOff_KeepFanRunningFor5Mins() {
        hvacMock.resetMock();
        environmentController.turnOnHeat();
        environmentController.turnOffHeat();
        for (int i = 0 ; i<4 ; i++) {
            environmentController.tick();
            assertTrue(hvacMock.getFanStatus());
        }
        environmentController.tick();
        assertFalse(hvacMock.getFanStatus());
    }
}