package com.power.engine;

public class ScreenUtil {
    
    public Empty ek1,ek2,ek3,ek4;
    public Square k;
    
    public void init(){
        ek1 = new Empty();
        ek1.setPosition(0,0);
        ek1.setScale(20,1);
        ek1.addComponent(k);
    }
    
    public void draw(){
        
    }
}
