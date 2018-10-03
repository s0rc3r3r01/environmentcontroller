package com.federico.interfaces;

public interface HVAC {
    public void heat(boolean enabled);
    public void cool(boolean enabled);
    public void fan(boolean enabled);
    public int GetTemp();
}
