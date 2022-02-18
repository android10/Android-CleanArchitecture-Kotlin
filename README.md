![banner](https://user-images.githubusercontent.com/37318022/40349145-3e0c8d8a-5dd8-11e8-8654-5784f9ab7554.png)

# Android - Clean Architecture - Kotlin [![Build Status](https://travis-ci.org/android10/Android-CleanArchitecture-Kotlin.svg?branch=main)](https://travis-ci.org/android10/Android-CleanArchitecture-Kotlin) [![codecov](https://codecov.io/gh/android10/Android-CleanArchitecture-Kotlin/branch/main/graph/badge.svg?token=x1AtDcNuDv)](https://codecov.io/gh/android10/Android-CleanArchitecture-Kotlin)
The purpose of this repo is to follow up Clean Architecture principles by bringing them to Android. It is worth saying that the idea is to take advantage of the Kotlin Programming Language features plus also pull in lessons learned and ideas from other interesting approaches like Functional Programming.

## Blog post with implementation details explanation:
[Architecting Android… Reloaded](https://fernandocejas.com/2018/05/07/architecting-android-reloaded/)

## Android - Clean Architecture - Java:
There is another Android Clean Architecture repository written in Java: https://github.com/android10/Android-CleanArchitecture with really nice discussions: https://github.com/android10/Android-CleanArchitecture/issues.
Article, lessons learned and some more material coming up. Check the following known bugs and TODO list. 

## Other material worth reading:

[Architecting Android…The clean way?](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)

[Architecting Android…The evolution](http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/)

[Tasting Dagger 2 on Android](http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/)

[Clean Architecture…Dynamic Parameters in Use Cases](http://fernandocejas.com/2016/12/24/clean-architecture-dynamic-parameters-in-use-cases/)

### ----------------------------------------------------------------------------------------------

## Sample app
![](https://user-images.githubusercontent.com/1360604/31345866-679a221a-ad17-11e7-8097-7800edb677fa.gif)

### ----------------------------------------------------------------------------------------------

## Clean Architecture
![https://fernandocejas.com/2018/05/07/architecting-android-reloaded/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture-Kotlin/architecture/clean_architecture_reloaded_main.png)

### ----------------------------------------------------------------------------------------------

## Android 3 Layers Architecture
![https://fernandocejas.com/2018/05/07/architecting-android-reloaded/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture-Kotlin/architecture/clean_architecture_reloaded_layers.png)

### ----------------------------------------------------------------------------------------------

## UI Layer: MVVM 
![https://fernandocejas.com/2018/05/07/architecting-android-reloaded/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture-Kotlin/architecture/clean_architecture_reloaded_mvvm_app.png)

### ----------------------------------------------------------------------------------------------

## Data Layer: Repository 
![https://fernandocejas.com/2018/05/07/architecting-android-reloaded/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture-Kotlin/architecture/clean_archictecture_reloaded_repository.png)

### ----------------------------------------------------------------------------------------------

## Local Development
Here are some useful Gradle/adb commands for executing this example:

 * `./gradlew runApp` - Builds and install the debug apk on the current connected device.
 * `./gradlew compileApp` - Builds the debug apk.
 * `./gradlew runUnitTests` - Execute unit tests (both unit and integration).
 * `./gradlew runAcceptanceTests` - Execute acceptance and instrumentation tests in the connected device.
 * `./gradlew runTestCoverage` - Reports code coverage on tests within the Android codebase.
 * `./gradlew runStaticCodeAnalysis` - Run static analysis on the Android codebase.
 
## Discussions
Refer to the issues section: https://github.com/android10/Android-CleanArchitecture-Kotlin/issues
 
## License

    Copyright 2022 Fernando Cejas

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


![https://fernandocejas.com](https://github.com/android10/Sample-Data/blob/master/android10/android10_logo_big.png)

<a href="https://www.buymeacoffee.com/android10" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>
