package com.marlon.testmasivian.model;

public class Wheel {
    private int id;
    private boolean state;

    public Wheel() {
    }

    public Wheel(int id, boolean state) {
        this.id = id;
        this.state = state;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
}
