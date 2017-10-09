# Android-CleanArchitecture-Kotlin
This is a WIP with new features and a more realistic example of my last Clean Architecture repo: https://github.com/android10/Android-CleanArchitecture 
Article, lessons learned and some more material coming up. Check the following known bugs and TODO list. 

Here is some material worth reading:

[Architecting Android…The clean way?](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)

[Architecting Android…The evolution](http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/)

[Tasting Dagger 2 on Android](http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/)

[Clean Architecture…Dynamic Parameters in Use Cases](http://fernandocejas.com/2016/12/24/clean-architecture-dynamic-parameters-in-use-cases/)

## Sample app
![](https://user-images.githubusercontent.com/1360604/31345866-679a221a-ad17-11e7-8097-7800edb677fa.gif)

## TODO List
- [ ] Write Acceptance Tests.
- [ ] Create Completable Use case abstraction.
- [ ] Implement cache for Offline mode.
- [ ] Restructure DI and use scoping. 
- [ ] Error Handling. 
- [ ] Pull to refresh.
- [ ] Loading data UI (spinner?).
- [ ] Clean up, code style and minor refactor.
- [ ] Write article with lessons learned. 


## Known bugs (Also WIP)
- [ ] One Leak within ```MoviesActivity.kt```
- [ ] Resize Images at API Level: https://github.com/android10/Sample-Data/tree/master/Android-CleanArchitecture-Kotlin


## Clean architecture
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture.png)

## Architectural approach
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture_layers.png)

## Architectural reactive approach
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture_layers_details.png)


## Local Development
Here are some useful Gradle/adb commands for executing this example:

 * `./gradlew clean build` - Build the entire example and execute unit and integration tests plus lint check.
 * `./gradlew deployDebug` - Install the debug apk on the current connected device.
 * `./gradlew runUnitTests` - Execute domain and data layer tests (both unit and integration).
 * `./gradlew runAcceptanceTests` - Execute espresso and instrumentation acceptance tests.
 
## Discussions
Refer to the issues section: https://github.com/android10/Android-CleanArchitecture-Kotlin/issues
 
## Code style
- [ ] For now use regular Kotlin code style. TODO: add a gradle task to install it. 

## License

    Copyright 2017 Fernando Cejas

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


![http://www.fernandocejas.com](https://github.com/android10/Sample-Data/blob/master/android10/android10_logo_big.png)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android--CleanArchitecture-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/909)
