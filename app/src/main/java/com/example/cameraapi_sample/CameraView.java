package com.example.cameraapi_sample;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

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
            // カメラパラメータ設定（例）
            // カメラの設定リストを取得
            Camera.Parameters parameters = mCamera.getParameters();
            // 端末がサポートするプレビューサイズリストを取得
            List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
            // プレビューサイズを設定
            parameters.setPreviewSize(previewSizes.get(2).width, previewSizes.get(2).height);
            // パラメータをカメラに反映する
            mCamera.setParameters(parameters);
            // プレビュー画面が90度回転する問題への対策
            mCamera.setDisplayOrientation(90);

            // プレビューにサーフェスホルダーを渡してプレビューを開始
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
