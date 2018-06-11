package com.example.cameraapi_sample;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    // CameraViewクラスインスタンス
    CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 情報バー非表示
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // カメラプレビューを表示するレイアウトを生成
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = findViewById(R.id.frame);
        LinearLayout linearLayout = new LinearLayout(this);
        cameraView = new CameraView(this);
        cameraView.setLayoutParams(new LinearLayout.LayoutParams(720, 1280));
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
