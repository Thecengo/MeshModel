package com.example.asuss.meshmodel;

/**
 * Created by asuss on 2.08.2017.
 */

public class Koordinat {

    public float ustX;
    public float ustY;
    public float ustZ;
    public float solAltX;
    public float solAltY;
    public float solAltZ;
    public float sagAltX;
    public float sagAltY;
    public float sagAltZ;

    public Koordinat(){

    }

    public void noktaAta(float ustX,float ustY,float ustZ,
                         float solAltX,float solAltY,float solAltZ,
                         float sagAltX,float sagAltY,float sagAltZ){
        this.ustX=ustX;
        this.ustY=ustY;
        this.ustZ=ustZ;
        this.solAltX=solAltX;
        this.solAltY=solAltY;
        this.solAltZ=solAltZ;
        this.sagAltX=sagAltX;
        this.sagAltY=sagAltY;
        this.sagAltZ=sagAltZ;

    }


    public  float [] ucgenKoordinatlarıAl(){
        float [] ucgenKoordinatları = {
                ustX,ustY,ustZ,
                solAltX,solAltY,solAltZ,
                sagAltX,sagAltY,sagAltZ
        };
        return ucgenKoordinatları;
    }
}
