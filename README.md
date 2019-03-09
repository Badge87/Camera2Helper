# Camera2 Helper Library

Camera2HelperLib is a simple and lightweight library created to facilitate the use of Android's Camera2 API.

## Including in your project
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

```gradle
implementation 'com.danielebachicchi:camera2helperlib:0.0.3'
```

## Requirements
* Android SDK 23 or higher.

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