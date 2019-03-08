package com.danielebachicchi.camera2helperlib;

import android.media.Image;

public interface ICameraHelperDelegate {

    void onAsyncImageDetected(Image image, CameraHelper.ImageSaver saver);
    void onCameraPermissionNotAccepted(CameraHelper helper);

}
