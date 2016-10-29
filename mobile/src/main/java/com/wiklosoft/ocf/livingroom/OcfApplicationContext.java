package com.wiklosoft.ocf.livingroom;

import android.app.Application;

import com.wiklosoft.ocf.OcfControlPoint;

/**
 * Created by Pawel Wiklowski on 29.10.16.
 */

public class OcfApplicationContext extends Application{
    static OcfControlPoint mOcfControlPoint = new OcfControlPoint();

    public static OcfControlPoint getOcfControlPoint(){
        return mOcfControlPoint;
    }
}
