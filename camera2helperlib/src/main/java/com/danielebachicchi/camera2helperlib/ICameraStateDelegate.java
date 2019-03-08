package com.danielebachicchi.camera2helperlib;

import android.hardware.camera2.CameraDevice;

public interface ICameraStateDelegate {
    void onCameraOpen(CameraDevice cameraDevice);
    void onCameraDisconnected(CameraDevice cameraDevice);
    void onCameraError(CameraDevice cameraDevice, int error);
}
