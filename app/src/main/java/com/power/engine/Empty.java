package com.power.engine;

import java.util.ArrayList;

public class Empty {
    
    ArrayList<Component> components = new ArrayList<Component>();
	
    public Empty(){
		
	}
	
    boolean update = true;
    
	public void initComponents(){
		if(update){
		    for(int i = 0; i < components.size(); i++){
		    	components.get(i).create();
	    	}
	    	update = false;
		}
	}
	
	public void draw(float[] combined, float[] view){
		for(int i = 0; i < components.size(); i++){
			components.get(i).draw(combined, view);
		}
	}
	
	public void addComponent(Component comp){
		components.add(comp);
	}
	
	public void setPosition(float x, float y){
		for(int i = 0; i < components.size(); i++){
			components.get(i).pX = x;
			components.get(i).pY = y;
		}
		//draw();
	}
	
	public void setRotation(float x, float y, float z){
		for(int i = 0; i < components.size(); i++){
			components.get(i).rX = x;
			components.get(i).rY = y;
			components.get(i).rZ = z;
		}
		//draw();
	}
	
	public void setScale(float x, float y){
		for(int i = 0; i < components.size(); i++){
			components.get(i).sX = x;
			components.get(i).sY = y;
		}
		//draw();
	}
	
	public void clearComponents(){
		for(int i = 0; i < components.size(); i++){
			components.remove(i);
		}
	}
    
    public void setColor(float r, float g, float b, float a){
        for(int i = 0; i < components.size(); i++){
			components.get(i).color = new float[]{r,g,b,a};
		}
	}
    
    public void setTexture(String location){
		for(int i = 0; i < components.size(); i++) {
		    components.get(i).textureBool = true;
            
            components.get(i).vertexShaderCode =
			"uniform mat4 uMVPMatrix;" +
			"attribute vec4 position;" +
			"attribute vec2 coordinate; " +
			"varying vec2 v_TexCoordinate; " +
			"void main() {" +
			"   gl_Position = uMVPMatrix * position;" +
			"   v_TexCoordinate = coordinate; " +
			"}";

	        components.get(i).fragmentShaderCode = 
			"precision mediump float; " +
			"uniform vec4 color;" +
			"uniform sampler2D u_Texture; " +
			"varying vec2 v_TexCoordinate; " +
			"void main() {" +
			"   gl_FragColor = color * texture2D(u_Texture, v_TexCoordinate); " +
			"}";
 
	    	components.get(i).location = location;
		}
	}
}