package com.power.engine;

import android.opengl.Matrix;

public class Camera {
    
	public float[] combined = new float[16];
	public float[] view = new float[16];
	
	public float zoom = 100;
	public float far = 1000;
	
	public String type = "perspective";
	
	public float pX, pY;
	
    public void setResize(float width, float height){
		float ratio = (float) width / height;
		
		Matrix.frustumM(combined, 0, -ratio, ratio, -1, 1, 1, far);
	}
	
	public void update(){
		Matrix.setIdentityM(view,0);
		Matrix.setLookAtM(view, 0,0,0,zoom/10,0,0,0,0,1,0);
		Matrix.translateM(view, 0, pX, pY, 0);
	}
	
	public void setPosition(float x, float y){
		pX = x/10; pY = y/10;
	}
}