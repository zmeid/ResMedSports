# ResMed Sports

![Snapshot](https://i.imgur.com/sbGB2qml.png)


# Used components/libraries/design patterns:

- 100% Kotlin
- Kotlin Coroutines(instead of RxJava and Java threads)
- Dagger2
- MVVM with Repository Pattern
- View binding
- Livedata
- Retrofit
- OkHttp logging interceptor
- CardView
- Timber
- Recyclerview
- Swipte-to-refresh
- JUnit
- Mockito
- Espresso

# Some notes:

- Configuration changes are handled gracefully. If we rotate the device when:
  - Recyclerview has data, there will not be any API call. Data will be simply returned from viewmodel.
- Http logging are enabled only in debug mode.
- I use "Region - endRegion" to make code cleaner.
- Timber is configured with custom TimberLineNumberDebugTree which provides clickable logs to navigate developer to the point where log was generated.
- If GET request fails, there will be an error message with "Get Results" button.


# Tests:
- Since testing was not in requirements, to set examples; i implemented one instrumented test with Espresso and one unit test with JUnit and Mockito.

# Tasks before publishing the app:
- Enable ProGuard.
- Run full lint inspection(should be done before every commit).
- Run static code analysis.
- Implement crashlytic.
- Use firebase test lab.
- Add multi-language support.

