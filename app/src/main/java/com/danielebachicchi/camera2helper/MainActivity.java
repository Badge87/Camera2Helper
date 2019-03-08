package com.danielebachicchi.camera2helper;

import android.graphics.Color;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraCharacteristics;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.danielebachicchi.camera2helperlib.AutoFitTextureView;
import com.danielebachicchi.camera2helperlib.CameraHelper;
import com.danielebachicchi.camera2helperlib.ICameraHelperDelegate;

public class MainActivity extends AppCompatActivity implements ICameraHelperDelegate {

    private CameraHelper _cameraHelper;
    private Button _btnLiveData;
    private Button _btnFormat;
    private Button _btnCameraType;
    private int[] _cameraTypes = new int[] {CameraCharacteristics.LENS_FACING_BACK,CameraCharacteristics.LENS_FACING_FRONT,CameraCharacteristics.LENS_FACING_EXTERNAL};
    private int[] _formats = new int[]{
            ImageFormat.JPEG,
            ImageFormat.YUV_420_888,
            ImageFormat.YUV_422_888,
            ImageFormat.YUV_444_888,
            ImageFormat.NV16,
            ImageFormat.NV21,
            ImageFormat.RAW10,
            ImageFormat.RAW12,
            ImageFormat.RAW_SENSOR,
            ImageFormat.FLEX_RGB_888,
            ImageFormat.FLEX_RGBA_8888,
            ImageFormat.DEPTH16,
            ImageFormat.DEPTH_POINT_CLOUD,
            ImageFormat.RGB_565,
            };
    private int _currentFormatIndex = 0;
    private int _currentCameraTypeIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoFitTextureView autoFitTextureView = findViewById(R.id.textureView);
        Button btnPhoto = findViewById(R.id.btn_photo);
        _btnLiveData = findViewById(R.id.btn_live_data);
        _btnCameraType = findViewById(R.id.btn_camera_type);
        _btnFormat = findViewById(R.id.btn_format);

        _cameraHelper = new CameraHelper(autoFitTextureView,this,this);
        _cameraHelper.set_imageFormat(_formats[0]);
        _cameraHelper.set_targetCamera(_cameraTypes[0]);



        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _cameraHelper.takePicture();
            }
        });
        _btnLiveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _cameraHelper.set_liveData(!_cameraHelper.is_liveData());
                refreshUI();
            }
        });

        findViewById(R.id.btn_livedata_increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _cameraHelper.set_liveDataMillisecondDelay(_cameraHelper.get_liveDataMillisecondDelay() + 500);
                refreshUI();
            }
        });
        findViewById(R.id.btn_livedata_decrease).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _cameraHelper.set_liveDataMillisecondDelay(_cameraHelper.get_liveDataMillisecondDelay() - 500);
                refreshUI();
            }
        });
        findViewById(R.id.btn_format).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _currentFormatIndex ++;
                if(_currentFormatIndex >= _formats.length)
                    _currentFormatIndex = 0;

                _cameraHelper.set_imageFormat(_formats[_currentFormatIndex]);
                refreshUI();

            }
        });

        _btnCameraType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _currentCameraTypeIndex ++;
                if(_currentCameraTypeIndex >= _cameraTypes.length)
                    _currentCameraTypeIndex = 0;

                _cameraHelper.set_targetCamera(_cameraTypes[_currentCameraTypeIndex]);
                refreshUI();
            }
        });


        refreshUI();

    }

    private void refreshUI(){
        _btnLiveData.setBackgroundColor(_cameraHelper.is_liveData() ? Color.GREEN : Color.RED);
        _btnLiveData.setText(String.format("Live Data \n(%s sec)", (double) _cameraHelper.get_liveDataMillisecondDelay() / 1000.d));
        _btnFormat.setText(getDescriptionForFormat(_cameraHelper.get_imageFormat()));
        _btnCameraType.setText(getDescriptionForCameraType(_cameraHelper.get_targetCamera()));
    }
    private String getDescriptionForCameraType(int cameraType){
        String result = "unknow";
        if(cameraType == CameraCharacteristics.LENS_FACING_FRONT){
            result = "Front";
        }else if(cameraType == CameraCharacteristics.LENS_FACING_BACK){
            result = "Back";
        }else if(cameraType == CameraCharacteristics.LENS_FACING_EXTERNAL){
            result = "Ext";
        }
        return result;
    }
    private String getDescriptionForFormat(int format){
        String result = "unknow";
        if(format == ImageFormat.JPEG)
            result = "jpeg";
        else if(format == ImageFormat.YUV_444_888)
            result = "yuv_444_888";
        else if(format == ImageFormat.YUV_420_888)
            result = "yuv_420_888";
        else if(format == ImageFormat.YUV_422_888)
            result = "yuv_422_888";
        else if(format == ImageFormat.RGB_565)
            result = "rgb_565";
        else if(format == ImageFormat.DEPTH16)
            result = "Depth16";
        else if(format == ImageFormat.DEPTH_POINT_CLOUD)
            result = "Depth_point_cloud";
        else if(format == ImageFormat.FLEX_RGB_888)
            result = "flex_rgb_888";
        else if(format == ImageFormat.FLEX_RGBA_8888)
            result = "flex_rgba_888";
        else if(format == ImageFormat.NV16)
            result = "nv16";
        else if(format == ImageFormat.NV21)
            result = "nv21";
        else if(format == ImageFormat.RAW10)
            result = "raw10";
        else if(format == ImageFormat.RAW12)
            result = "raw12";
        else if(format == ImageFormat.RAW_PRIVATE)
            result = "rawPrivate";
        else if(format == ImageFormat.RAW_SENSOR)
            result = "raw_sensor";

        return result;
    }

    @Override
    protected void onResume() {
        super.onResume();
        _cameraHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        _cameraHelper.onPause();
    }

    @Override
    public void onAsyncImageDetected(Image image, CameraHelper.ImageSaver saver) {

    }

    @Override
    public void onCameraPermissionNotAccepted(CameraHelper helper) {
        helper.requestCameraPermission(getSupportFragmentManager());

    }
}
