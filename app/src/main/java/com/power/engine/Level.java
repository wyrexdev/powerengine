package com.power.engine;

import android.opengl.GLSurfaceView;
import android.content.Context;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;
import android.view.View;
import android.opengl.GLES30;
import android.view.MotionEvent;
import android.opengl.GLU;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.ScaleGestureDetector;
import android.widget.Toast;
import android.view.View.*;

abstract public class Level {
    
    public Camera editorCam;
    
    public boolean isScreenTouched = false;
    
    public float getInputX, getInputY, width, height;
	public float x;
	public float y;
	public float px,py;
	public float zx;
	public float zy;
	public float zoom;
	
	public boolean run = false;
	
	public float[] combined;
	public float[] view;
	
	public float pointer;
	
	public abstract void onCreate();
	public abstract void onDraw();
	public abstract void onClickScreen();
	
	public View getView(Context context){
		return new GLView(context);
	}
	
    class GLView extends GLSurfaceView implements OnClickListener, OnTouchListener {
		ScaleGestureDetector sgd;
		
		public GLView(Context context){
			super(context);
			sgd = new ScaleGestureDetector(context, new ZoomListener());
			setEGLContextClientVersion(3);
			setRenderer(new GLRenderer());
        }

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			sgd.onTouchEvent(event);
			
			x = event.getX();
			y = event.getY();
			
            Input.x = event.getX() - (getResources().getDisplayMetrics().widthPixels/2);
			Input.y = event.getY() - (getResources().getDisplayMetrics().heightPixels/2);

			pointer = event.getPointerCount();
			
			switch(event.getAction()){
				case MotionEvent.ACTION_MOVE:
					if(event.getPointerCount() == 1){
						getInputX = zx + (x - px)/4;
						getInputY = zy + (y - py)/4;
					} 
					
					if(event.getPointerCount() == 2){
						/*zx = 0; x = 0; px = 0;
						zy = 0; y = 0; py = 0;*/
					}
					return true;
			}

			px = x;
			py = y;
			
			zx = getInputX;
			zy = getInputY;
			
            //Toast.makeText(getContext(), Float.toString(Input.x), 500).show();
            isScreenTouched = true;
            
    	    Input.isTouchScreen = event.getAction() != MotionEvent.ACTION_UP;
    	    return true;
		}
		
        @Override
	    public void onClick(View p1) {
	        onClickScreen();
       	}
       	
	    @Override
    	public boolean onTouch(View p1, MotionEvent event) {
    	    return true;
    	}
	}
	
	class ZoomListener extends SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			if(pointer == 2){
				zoom = detector.getScaleFactor();
			}
			//zoom = 0;
			return true;
		}
	}
    
	class GLRenderer implements GLSurfaceView.Renderer {
		
		@Override
		public void onSurfaceCreated(GL10 p1, EGLConfig p2) {
			editorCam = new Camera();
			editorCam.zoom = 50;
			editorCam.far = 1000;

			onCreate();
		}

		@Override
		public void onSurfaceChanged(GL10 p1, int p2, int p3) {
			editorCam.setResize(p2,p3);
            width = p2;
            height = p3;
			   //GLU.gluOrtho2D(p1, 0, p2, p3, 0);
		}

		@Override
		public void onDrawFrame(GL10 p1) {
			GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT|GLES30.GL_DEPTH_BUFFER_BIT);
			GLES30.glClearColor(1,1,1,1);
			
           //Input.isTouchScreen = false;
           isScreenTouched = false;
            
           GLES30.glViewport(0,0,(int)(width),(int)(height));
            
			GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
			
			GLES30.glEnable(GLES30.GL_BLEND);
			GLES30.glBlendFuncSeparate(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA, GLES30.GL_ONE, GLES30.GL_ONE_MINUS_SRC_ALPHA);

			combined = editorCam.combined;
			view = editorCam.view;
			
            SystemPE.width = (int)width;
            SystemPE.height = (int)height;
            
			if(run){
			    
			} else {
			    if(pointer == 1){
			        editorCam.setPosition(getInputX,-getInputY);
			    }
		    if(zoom > 1){
				if(pointer == 2){
					if(editorCam.zoom <= 50){

					} else {
						editorCam.zoom = editorCam.zoom - (zoom*3.5f);
					}
				}
			} else {
				if(pointer == 2){
					if(editorCam.zoom >= 300){

					} else {
						editorCam.zoom = editorCam.zoom + (zoom*3.5f);
					}
				}
			}
			editorCam.update();
			}

			zoom = 0;
			
			GLES30.glEnable(GLES30.GL_SAMPLE_ALPHA_TO_COVERAGE);
			
			onDraw();
		}
	}
}