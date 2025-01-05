package com.power.engine;

public class Input {
    
    public static float x = 0;
    public static float y = 0;
    
    public static boolean isTouchScreen = false;
    
    public boolean isTouched(){
        return isTouchScreen;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
}
