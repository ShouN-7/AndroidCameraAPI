package com.example.cameraapi_sample;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    // CameraViewクラスインスタンス
    CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        // 起動時にカメラパーミッションを要求する処理（Android6.0以降）
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CAMERA
                }, 1);
            }
        }else{

        }
        */

        // 情報バー非表示
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // カメラプレビューを表示するレイアウトを生成
        setContentView(R.layout.activity_main);

        // ディスプレイサイズの取得
        WindowManager windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int dispW = display.getWidth();
        int dispH = display.getHeight();

        FrameLayout frameLayout = findViewById(R.id.frame);
        LinearLayout linearLayout = new LinearLayout(this);
        cameraView = new CameraView(this);
        // プレビューサイズをディスプレイサイズに設定
        cameraView.setLayoutParams(new LinearLayout.LayoutParams(dispW, dispH));
        linearLayout.addView(cameraView);
        frameLayout.addView(linearLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null == cameraView.mCamera){
           try{
               cameraView.mCamera.setPreviewDisplay(cameraView.holder);
               cameraView.mCamera.startPreview();
           }catch(Exception e){
           }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(null != cameraView.mCamera){
            try{
                cameraView.mCamera.release();
            }catch(Exception e){
            }
        }
    }
}
