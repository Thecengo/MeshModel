package com.example.asuss.meshmodel;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by asuss on 1.08.2017.
 */

public class Ucgen {

    private FloatBuffer vertexBuffer;



    static final int KOSE_BASI_KOORDİNAT_SAYISI =3;

    private final String vertexShaderCode =

            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    float [] renk = {0.60f,0.76f,0.22f,0.0f};

    private final int mProgram;

    private int mPositionHandle;
    private int mColorHandle;

   // private final int vertexCount = ucgenKoordinatlarıAl().length/KOSE_BASI_KOORDİNAT_SAYISI;
   // private final int vertexCount=3;
    private final int vertexStride = KOSE_BASI_KOORDİNAT_SAYISI*4;

    private int mMVPMatrixHandle;

    public Ucgen(Koordinat k){

       bufferaKoordinatKoy(k);

        int vertexShader = MyRenderer.loadShader(GLES20.GL_VERTEX_SHADER,vertexShaderCode);
        int fragmentShader =MyRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,fragmentShaderCode);

        mProgram=GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram,vertexShader);
        GLES20.glAttachShader(mProgram,fragmentShader);
        GLES20.glLinkProgram(mProgram);



    }
    public void draw(float [] mVPMatrix,Koordinat k){

        GLES20.glUseProgram(mProgram);

        mPositionHandle = GLES20.glGetAttribLocation(mProgram,"vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        GLES20.glVertexAttribPointer(mPositionHandle,KOSE_BASI_KOORDİNAT_SAYISI,GLES20.GL_FLOAT,
                false,vertexStride,bufferaKoordinatKoy(k));

        mColorHandle = GLES20.glGetUniformLocation(mProgram,"vColor");
        GLES20.glUniform4fv(mColorHandle,1,renk,0);

        mMVPMatrixHandle=GLES20.glGetUniformLocation(mProgram,"uMVPMatrix");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mVPMatrix, 0);



        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,MyRenderer.UCGEN_ADEDİ*3);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

    public FloatBuffer bufferaKoordinatKoy(Koordinat k){

        ByteBuffer bb = ByteBuffer.allocateDirect(k.ucgenKoordinatlarıAl().length*4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer =bb.asFloatBuffer();
        vertexBuffer.put(k.ucgenKoordinatlarıAl());
        vertexBuffer.position(0);
        return vertexBuffer;
    }

}
