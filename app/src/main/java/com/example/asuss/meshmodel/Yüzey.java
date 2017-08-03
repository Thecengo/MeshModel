package com.example.asuss.meshmodel;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by asuss on 1.08.2017.
 */

public class Yüzey extends GLSurfaceView {
    private final MyRenderer myRenderer;

    private final float TOUCH_SCALE_FACTOR  = 180.0f/320;
    private float mPreviousX;
    private float mPreviousY;

    public Yüzey(Context context) {
        super(context);

        setEGLContextClientVersion(2);

        myRenderer=new MyRenderer();

        setRenderer(myRenderer);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);


    }
    @Override
    public boolean onTouchEvent(MotionEvent e){
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()){
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                if( y > getHeight() / 2){
                    dx = dx * -1;
                }
                if(x < getWidth()/2){
                    dy = dy * -1;
                }
                myRenderer.setAngle(
                        myRenderer.getAngle() +
                                ((dx + dy) * TOUCH_SCALE_FACTOR));
                requestRender();
        }
        mPreviousX = x;
        mPreviousY = y;

        return true;
    }
}
