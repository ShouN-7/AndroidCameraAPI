package com.example.cameraapi_sample;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Created by shori.nagano on 2018/06/11.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback{
    SurfaceHolder holder;
    Camera mCamera;

    public CameraView(Context context){
        super(context);
        mCamera = Camera.open();
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        try{
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        }catch(IOException e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }
    public void surfaceChanged(SurfaceHolder holder, int int1, int int2, int int3)
    {

    }
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        if(null != mCamera){
            try{
                mCamera.release();
            }catch(Exception e){

            }
        }
    }

}
