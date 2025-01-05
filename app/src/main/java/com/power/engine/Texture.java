package com.power.engine;

import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.TextPaint;
import android.graphics.BlendMode;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;

public class Texture {
    
	public static int loadTexture(String location)
	{
		final int[] textureHandle = new int[1];
		GLES30.glTexImage2D(GLES30.GL_TEXTURE_2D, 0, GLES30.GL_RGBA, 0, 0,
							0, GLES30.GL_RGBA, GLES30.GL_UNSIGNED_BYTE, null);
		
		GLES30.glDeleteTextures(1,textureHandle,0);
		GLES30.glGenTextures(1, textureHandle, 0);

		if (textureHandle[0] == 0)
		{
			throw new RuntimeException("Error generating texture name.");
		}

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;	// No pre-scaling

		// Read in the resource
		final Bitmap bitmap = BitmapFactory.decodeFile(location);

		// Bind to the texture in OpenGL
		GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureHandle[0]);

		// Set filtering
		GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
		GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_NEAREST);

		// Load the bitmap into the bound texture.
		GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, bitmap, 0);
		
		// Recycle the bitmap, since its data has been loaded into OpenGL.
		bitmap.recycle();
		
		//GLES30.glDeleteTextures(1,textureHandle,0);
		
		return textureHandle[0];
	}

    public static int loadText(String text, float size, float sx, float sy) {
        final int[] textureHandle = new int[1];
		GLES30.glTexImage2D(GLES30.GL_TEXTURE_2D, 0, GLES30.GL_RGBA, 0, 0,
							0, GLES30.GL_RGBA, GLES30.GL_UNSIGNED_BYTE, null);

		GLES30.glDeleteTextures(1,textureHandle,0);
		GLES30.glGenTextures(1, textureHandle, 0);

		if (textureHandle[0] == 0)
		{
			throw new RuntimeException("Error generating texture name.");
		}

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;	// No pre-scaling

		// Read in the resource
		final Bitmap bitmap = Bitmap.createBitmap(256,256, Bitmap.Config.ARGB_8888);
		
		Canvas cv = new Canvas(bitmap);
		
    	Paint textPaint = new Paint();
        textPaint.setTextSize(size);
        textPaint.setFakeBoldText(false);
        textPaint.setAntiAlias(true);
        textPaint.setARGB(255, 255, 255, 255);
         // If a hinting is available on the platform you are developing, you should enable it (uncomment the line below).
         //textPaint.setHinting(Paint.HINTING_ON);
        textPaint.setSubpixelText(true);
        textPaint.setXfermode(new PorterDuffXfermode(Mode.SCREEN));
		
		//cv.drawRect(0,0,bitmap.getWidth(),bitmap.getHeight(), paint2);
		
		cv.drawText(text,5,20, textPaint);

		// Bind to the texture in OpenGL
		GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureHandle[0]);

		// Set filtering
		GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
		GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_NEAREST);

		// Load the bitmap into the bound texture.
		GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, bitmap, 0);

		// Recycle the bitmap, since its data has been loaded into OpenGL.
		bitmap.recycle();

		//GLES30.glDeleteTextures(1,textureHandle,0);

		return textureHandle[0];
    }
}