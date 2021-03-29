# CarbonTest
Sample App to fetch Data from an API
Fetches data from https://dummyapi.io/data/. 
This app uses 
A paginated API, it lazy loads the info onto the screen and caches the data the db as the user scrolls 

## Features
* Clean Architecture with MVVM
* View components
* Koin
* Kotlin Gradle DSL
* Unit Tests

## Libraries
- [Android Paging Library](https://developer.android.com/topic/libraries/architecture/paging/) - The Paging Library helps you load and display small chunks of data at a time.
- [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Presenter for persisting view state across config changes
- [Room](https://developer.android.com/training/data-storage/room) - Provides abstraction layer over SQLite
- [Retrofit](https://square.github.io/retrofit/) - type safe http client   
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - logs HTTP request and response data.
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) - web server for testing HTTP clients ,verify requests and responses on the star wars api with the retrofit client.
- [Robolectric](http://robolectric.org/) - Unit test on android framework.
- [Koin](https://insert-koin.io/) - handles dependency injection
- [Kotlin Gradle DSL](https://guides.gradle.org/migrating-build-logic-from-groovy-to-kotlin)
