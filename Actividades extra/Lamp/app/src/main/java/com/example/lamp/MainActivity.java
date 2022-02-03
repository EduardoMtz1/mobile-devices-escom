package com.example.lamp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends Activity {
    private boolean encendida = false;
    private Camera cam;
    private Button jbn;
    @Override
    protected void onStop() {
        super.onStop();
        if (cam != null)
            cam.release();
    }
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jbn = (Button) findViewById(R.id.xbn);
        Context context = this;
        PackageManager pm = context.getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Log.e("err", "Dispositivo sin camera!");
            return;
        }
        try{
            cam = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        }catch(Exception e){
            Log.e(getString(R.string.app_name), "failed to open Camera");
            e.printStackTrace();
        }
        final Parameters p = cam.getParameters();
        jbn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (encendida) {
                    Log.i("info", "Lámpara apagada!");
                    p.setFlashMode(Parameters.FLASH_MODE_OFF);
                    cam.setParameters(p);
                    cam.stopPreview();
                    encendida = false;

                } else {
                    Log.i("info", "Lámpara encendida!");
                    p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.startPreview();
                    encendida = true;
                }
            }
        });
    }
}
