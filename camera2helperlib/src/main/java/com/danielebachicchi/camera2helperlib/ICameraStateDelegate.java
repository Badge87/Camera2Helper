package com.danielebachicchi.camera2helperlib;

import android.hardware.camera2.CameraDevice;

public interface ICameraStateDelegate {
    /**
     * Called when {@link CameraHelper} has opened the camera.
     * @param cameraDevice the camera that has opened.
     * */
    void onCameraOpen(CameraDevice cameraDevice);
    /**
     * Called when {@link CameraHelper} has revelead that the camera is disconnected.
     * @param cameraDevice the camera that has disconnected.
     * */
    void onCameraDisconnected(CameraDevice cameraDevice);
    /**
     * Called when {@link CameraHelper} has revelead that the camera has some problem.
     * @param cameraDevice the camera that has some problem.
     * */
    void onCameraError(CameraDevice cameraDevice, int error);
}
