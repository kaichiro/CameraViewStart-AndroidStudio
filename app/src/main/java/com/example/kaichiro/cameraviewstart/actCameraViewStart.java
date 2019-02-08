package com.example.kaichiro.cameraviewstart;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wonderkiln.camerakit.CameraView;

public class actCameraViewStart extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 1;

    CameraView mCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_camera_view_start);

        mCameraView = findViewById(R.id.camera);

        checkCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.start();
    }

    @Override
    protected void onPause() {
        mCameraView.stop();
        super.onPause();
    }

    private void checkCamera() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CAMERA) {
            if (grantResults.length > 0) {
                if (grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //TODO ABRO A CAMERA
                } else {
                    showError(R.string.error_permission_not_granted);
                }
            } else {
                showError(R.string.permission_request_canceled);
            }
        }
    }

    public void showError(int idStringDescription) {
        new MaterialDialog.Builder(this)
                .title(R.string.title_error)
                .content(idStringDescription)
                .positiveText(R.string.label_ok)
                .show();
    }
}
