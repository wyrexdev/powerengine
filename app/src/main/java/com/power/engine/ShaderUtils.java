package com.power.engine;

import android.opengl.GLES30;

public class ShaderUtils {
    
    public static int loadShader(int shaderType, String shaderCode){
		int shader = GLES30.glCreateShader(shaderType);
		
		GLES30.glShaderSource(shader, shaderCode);
		GLES30.glCompileShader(shader);
		
		return shader;
	}
    
}