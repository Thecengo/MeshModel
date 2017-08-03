package com.example.asuss.meshmodel;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.provider.Settings;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by asuss on 1.08.2017.
 */

public class MyRenderer implements GLSurfaceView.Renderer{

    private final float [] mMVPMatrix = new float[16];
    private final float [] mProjectionMatrix = new float[16];
    private final float [] mViewMatrix = new float[16];

    private final float [] mRotationMatrix = new float[16];

    public volatile float mAngle;

    public final static int UCGEN_ADEDİ = 2;
    private Ucgen ucgen;
    Ucgen ucgen1;
    public Koordinat koordinat;
    public Koordinat koordinat1;
   //public Ucgen [] ucgenAdedi = new Ucgen[2];
    ArrayList<Ucgen> ucgenler = new ArrayList<Ucgen>();
    ArrayList<Koordinat> koordinatlar= new ArrayList<Koordinat>();

    public void koordinatNesnesiOlustur(){
        for(int i =0;i<UCGEN_ADEDİ;i++)
        koordinatlar.add(new Koordinat());
    }
    public void koordinatAta(){
        koordinatNesnesiOlustur();
        koordinatlar.get(0).noktaAta(-0.2f,0.3f,0.0f,
                -0.4f,-0.2f,0.0f,
                0.0f,-0.2f,0.0f);
        koordinatlar.get(1).noktaAta(-0.2f,0.3f,0.0f,
                0.1f,-0.2f,0.0f,
                0.3f,-0.4f,0.0f);
    }
    public void ucgenOlustur(){
        koordinatAta();
        for(int i = 0;i <UCGEN_ADEDİ;i++)
            ucgenler.add(new Ucgen(koordinatlar.get(i)));

    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        /*koordinat = new Koordinat();
        koordinat1= new Koordinat();
        koordinat.noktaAta(-0.2f,0.3f,0.0f,
                -0.4f,-0.2f,0.0f,
                0.0f,-0.2f,0.0f);
        ucgen = new Ucgen(koordinat);

        koordinat1.noktaAta(-0.2f,0.3f,0.0f,
                0.1f,-0.2f,0.0f,
                0.3f,-0.4f,0.0f);
        ucgen1 = new Ucgen(koordinat1);
    }

*/
        ucgenOlustur();
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0,0,width,height);

        float ratio = (float)width/height;
        Matrix.frustumM(mProjectionMatrix,0,-ratio,ratio,-1,1,3,7);

        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        Matrix.multiplyMM(mMVPMatrix,0,mProjectionMatrix,0,mViewMatrix,0);


    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        float [] strach = new float[16];

       // long time = SystemClock.uptimeMillis()%4000L;
        //float angle = 0.90f*((int)time);
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

       // Matrix.setRotateM(mRotationMatrix,0,mAngle,1,0.62f,0f);
        Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, 1.0f);
        Matrix.multiplyMM(strach,0,mMVPMatrix,0,mRotationMatrix,0);


      //  ucgen.draw(strach,koordinat);
       // ucgen1.draw(strach,koordinat1);
        for(int i = 0;i<UCGEN_ADEDİ;i++){
            ucgenler.get(i).draw(strach,koordinatlar.get(i));
        }


    }
    public static int loadShader(int tip,String shaderCode){
        int shader =GLES20.glCreateShader(tip);

        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);
        return  shader;

    }
    public float getAngle() {
        return mAngle;
    }


    public void setAngle(float angle) {
        mAngle = angle;
    }
}
