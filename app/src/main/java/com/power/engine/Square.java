package com.power.engine;

import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import android.opengl.GLES30;
import android.opengl.Matrix;
import java.util.zip.ZipError;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.opengl.GLUtils;

public class Square extends Component {

	public FloatBuffer vertexBuffer;
	public FloatBuffer textureBuffer;
	public FloatBuffer coordBuffer;
	public ShortBuffer drawListBuffer;

	public int COORDS_PER_VERTEX = 3;

	public int program;

	static float squareCoords[] = {
		-0.5f,  0.5f, 0.0f,   // top left
		-0.5f, -0.5f, 0.0f,   // bottom left
		0.5f, -0.5f, 0.0f,   // bottom right
		0.5f,  0.5f, 0.0f };

	final float[] textureCoordinateData =
	{
		-1f, 0f,
		-1f, 1f,
		0f, 1f,
		0f, 0f
	};

	public short drawOrder[] = { 0, 1, 2, 0, 2, 3};

	public int positionHandle, colorHandle, matrixHandle, textureHandle, texCoordHandle;

	public int vertexCount = squareCoords.length / COORDS_PER_VERTEX;
	public int vertexStride = COORDS_PER_VERTEX * 4;

	public float[] squareMatrix = new float[16];
    
	public int texture = 0;
	
	@Override
	public void create(){
		ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(squareCoords);
		vertexBuffer.position(0);

		ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * 2);
		dlb.order(ByteOrder.nativeOrder());
		drawListBuffer = dlb.asShortBuffer();
		drawListBuffer.put(drawOrder);
		drawListBuffer.position(0);

		ByteBuffer tcbb = ByteBuffer.allocateDirect(textureCoordinateData.length * 4);
		tcbb.order(ByteOrder.nativeOrder());
		coordBuffer = tcbb.asFloatBuffer();
		coordBuffer.put(textureCoordinateData);
		coordBuffer.position(0);

		int vertexShader = ShaderUtils.loadShader(GLES30.GL_VERTEX_SHADER, vertexShaderCode);
		int fragmentShader = ShaderUtils.loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderCode);

		program = GLES30.glCreateProgram();

		GLES30.glAttachShader(program, vertexShader);
		GLES30.glAttachShader(program, fragmentShader);

		GLES30.glLinkProgram(program);
		
		if(textureBool){
			texture = Texture.loadTexture(location);
		}
	}

	@Override
	public void draw(float[] combined, float[] view){
		if(visible){
		    //GLES30.glEnable(GLES30.GL_BLEND);
		
		GLES30.glUseProgram(program);

		Matrix.setIdentityM(squareMatrix ,0);

		Matrix.multiplyMM(squareMatrix, 0, combined, 0, view, 0);

		Matrix.translateM(squareMatrix, 0, pX, pY, 0);

		Matrix.rotateM(squareMatrix, 0, rX, 1, 0, 0);
		Matrix.rotateM(squareMatrix, 0, rY, 0, 1, 0);
		Matrix.rotateM(squareMatrix, 0, rZ, 0, 0, 1);

		Matrix.scaleM(squareMatrix, 0, sX, sY, 0);

		matrixHandle = GLES30.glGetUniformLocation(program, "uMVPMatrix");
		GLES30.glUniformMatrix4fv(matrixHandle, 1, false, squareMatrix, 0);
		
		positionHandle = GLES30.glGetAttribLocation(program, "position");
		GLES30.glEnableVertexAttribArray(positionHandle);
		GLES30.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES30.GL_FLOAT, false, vertexStride, vertexBuffer);

		colorHandle = GLES30.glGetUniformLocation(program, "color");
		GLES30.glUniform4fv(colorHandle, 1, color, 0);

		if(textureBool){
			texCoordHandle = GLES30.glGetAttribLocation(program, "coordinate");
			GLES30.glVertexAttribPointer(texCoordHandle, 2, GLES30.GL_FLOAT, false, 0, coordBuffer);
			GLES30.glEnableVertexAttribArray(texCoordHandle);

			textureHandle = GLES30.glGetUniformLocation(program, "u_Texture");
			//GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture);
			GLES30.glUniform1f(textureHandle, 0);
			//GLES30.glDeleteTextures(1,Texture.loadTexture(location),0);
			//GLES30.glGenTextures(1,Texture.loadTexture(location),0);
		} else {

		}

		GLES30.glDrawArrays(
			GLES30.GL_TRIANGLE_FAN, 0, vertexCount);

		GLES30.glDisableVertexAttribArray(positionHandle);
		GLES30.glDisableVertexAttribArray(texCoordHandle);
		
		//GLES30.glDeleteTextures(1,new int[]{texture},0);
		
		//GLES30.glDisable(GLES30.GL_BLEND);
		}
	}
}