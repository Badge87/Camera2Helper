package com.danielebachicchi.camera2helperlib;

import android.media.Image;

public interface ICameraHelperDelegate {

    /**
     * Called When {@link CameraHelper} has one new image ready. this method is called outside the main Thread.
     * @param image the image ready
     * @param saver the {@link com.danielebachicchi.camera2helperlib.CameraHelper.ImageSaver} that handles this Image
     * */
    void onAsyncImageDetected(Image image, CameraHelper.ImageSaver saver);
    /**
     * Called when CAMERA permission are not yet accepted during the setup of the {@link CameraHelper}
     * */
    void onCameraPermissionNotAccepted(CameraHelper helper);

}
