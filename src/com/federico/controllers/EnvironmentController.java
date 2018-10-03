package com.federico.controllers;

/*
* Rules : After turning heat off, run the fan for five mins
* Don't turn the cooler on within 3 minutes of turning it off
 */


import com.federico.interfaces.HVAC;

public class EnvironmentController {


    HVAC hvac;
    int coolerGracePeriod = 0;
    int fanCoolDownPeriod = 0;

    public void tick() {
        int currentRoomTemperature = hvac.GetTemp();
        if (tooHot(currentRoomTemperature)) {
            handleRoomTooHot();
        }
        if (tooCold(currentRoomTemperature)) {
            handleRoomTooCold();
        }
        reduceGracePeriod();
        checkIfFanShouldBeTurnedOff();
    }

    private void checkIfFanShouldBeTurnedOff() {
        if (fanCoolDownPeriod == 1) {
            fanCoolDownPeriod-- ;
            turnOffFan();
        }
        else if (fanCoolDownPeriod > 0) {
            fanCoolDownPeriod--;
        }
    }

    private void reduceGracePeriod() {
        if (coolerGracePeriod > 0)  { coolerGracePeriod-- ;}
    }

    private void handleRoomTooCold() {
        turnOffCool();
        turnOnHeat();
    }

    public void turnOffCool() {
        hvac.cool(false);
        hvac.fan(false);
    }

    public void turnOffFan() {
        hvac.fan(false);
    }

    public void turnOffHeat() {
        hvac.heat(false);
        startFanCoolDown();
    }

    private void startFanCoolDown() {
        fanCoolDownPeriod = 5;
    }


    private boolean tooCold(int currentRoomTemperature) {
        if (currentRoomTemperature < 75) { return true;}
        return false;
    }

    private void handleRoomTooHot() {
        turnOffHeat();
        turnOnCool();
    }

    private boolean tooHot(int currentRoomTemperature) {
        if ( currentRoomTemperature > 77) { return true; }
        return false;
    }


    public EnvironmentController(HVAC concreteHVAC) {
        hvac = concreteHVAC;
    }


    public void turnOnCool() {
        if (! beingWithinGracePeriod()) {
            hvac.cool(true);
            hvac.fan(true);
        }
    }
    public void turnOnHeat() {
            hvac.heat(true);
            hvac.fan(true);
    }

    private boolean beingWithinGracePeriod() {
        if (coolerGracePeriod > 0) { return true; }
        return false;
    }


}
