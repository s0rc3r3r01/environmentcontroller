package com.federico.mocks;

import com.federico.interfaces.HVAC;

public class HVACMock implements HVAC {

    int roomTemperature = 75;
    boolean isHeatEnabled = false;
    boolean isCoolEnabled = false;
    boolean isFanEnabled = false;

    @Override
    public void heat(boolean enable) {
        isHeatEnabled = enable;
    }

    @Override
    public void cool(boolean enable) {
        isCoolEnabled = enable;
    }

    @Override
    public void fan(boolean enable) {
        isFanEnabled = enable;

    }

    @Override
    public int GetTemp() {
        return roomTemperature;
    }

    public void setColdRoom() {
        roomTemperature = 50;
    }

    public void setHotRoom() {
        roomTemperature = 100;
    }

    public boolean getHeatStatus() {
        return isHeatEnabled;
    }

    public boolean getCoolStatus() {
        return isCoolEnabled;
    }

    public boolean getFanStatus(){
        return isFanEnabled;
    }

    public void resetMock() {
        isCoolEnabled = false;
        isFanEnabled = false;
        isHeatEnabled = false;
        roomTemperature = 75;
    }
}
