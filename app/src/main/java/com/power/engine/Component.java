package com.power.engine;

abstract public class Component {
    
    public String vertexShaderCode =
	"uniform mat4 uMVPMatrix;" +
	"attribute vec4 position;" +
	"void main() {" +
	"   gl_Position = uMVPMatrix * position;" +
	"}";

	public String fragmentShaderCode =
	"precision mediump float; " +
	"uniform vec4 color;" +
	"void main() {" +
	"   gl_FragColor = color; " +
	"}";

    
    public abstract void create();
	public abstract void draw(float[] combined, float[] view);
    
	public float pX, pY, rX, rY, rZ, sX, sY;
    public float color[] = {1.0f, 1.0f, 1.0f, 1f};
        
    public String location;
    public boolean textureBool = false;
    public boolean visible = true;
}