# Camera2 Helper Library

![Image](https://raw.githubusercontent.com/Badge87/Camera2Helper/master/extra/images/banner.png)

Camera2HelperLib is a simple and lightweight library created to facilitate the use of Android's Camera2 API.

## <img  width="30" height="30" src="https://github.com/Badge87/Camera2Helper/blob/master/extra/images/ico_install.png?raw=true"> Including in your project 
Add jitpack.io to your project's repositories:


```

allProjects {
  repositories {
    google() 
    // required to find the project's artifacts
    // place last
    maven { url "https://www.jitpack.io" }
  }
}

```

Add this to your app's build.gradle dependencies

```
gradle implementation 'com.danielebachicchi:camera2helperlib:0.0.4'

```

## <img  width="30" height="30" src="https://github.com/Badge87/Camera2Helper/blob/master/extra/images/ico_usage.png?raw=true"> Usage
Camera Helper is an helper class that handle all the process of the open, close and processing image of the camera. In order to initialize it you have to pass to the constructor:
* <b>AutoFitTextureView textureView</b> 
The AutoFitTextureView inside the layout.xml of your activity, fragment or view.
* <b>Activity activity</b>
The current activity (or fragment activity).
* <b>ICameraHelperDelegate delegate</b>
The cameraHelperDelegate (can be the activity/fragment itself or other classes, as you wish).

Usually you create the instance of the CameraHelper during onCreate of your Activity / Fragment.

```java

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Do your work
        
        //retrieve the autofitTextureView inside the layout
        AutoFitTextureView autoFitTextureView = findViewById(R.id.textureView);
        _cameraHelper = new CameraHelper(autoFitTextureView,this,this);
    }
    
```
 
In order for the class to function properly you have to call inside the onPause and onResume the respective methods of the CameraHelper:

```java

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

```

## LiveMode
With Live Mode active, you don't have to manually request to take a picture. CameraHelper will capture a picture in background (outside the main thread) and it communicates it to your delegate.
This mode is helpfull especially for processing in real-time what the camera is seeing, like, for example, barcode or qrcode detect feature.
To enable the liveMode:

 ```java
 
 //enable the liveMode.
 _cameraHelper.set_liveData(true);
//set the interval from one picture to another (in milliseconds).
_cameraHelper.set_liveDataMillisecondDelay(2000);
 
 ```

## ICameraHelperDelegate
The delegate has the following method that need to implements:
 
 ```java
 
 void onAsyncImageDetected(Image image, CameraHelper.ImageSaver saver);
 void onCameraPermissionNotAccepted(CameraHelper helper);
 
 ```

* <b>onAsyncImageDetected</b> 
When the CameraHelper has an imageAvaiable (after picture request or during liveMode) 
* <b>onCameraPermissionNotAccepted</b>
When CameraHelper start to initialize but need the CAMERA permission. You can handle the request yourself or call the default method of CameraHelperClass :

```java

    @Override
    public void onCameraPermissionNotAccepted(CameraHelper helper) {
        //you can call this helper method or handle yourself. As you wish :)
        helper.requestCameraPermission(getSupportFragmentManager());

    }

```

## Requirements
* Android SDK 23 or higher.

## Screenshots
<p align="center">
<img  height="400" src="https://github.com/Badge87/Camera2Helper/blob/master/extra/images/screenshot_01.png?raw=true">  
</p>

## Maintainers
This project is created and mantained by:
* [Daniele Bachicchi](http://github.com/badge87)

### License

```

Copyright 2019 Daniele Bachicchi

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
```