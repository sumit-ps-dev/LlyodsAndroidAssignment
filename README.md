# Llyods Android Assignment

This is a simple Kotlin MVVM-clean-architecture demonstration.Following technologies are used to
build this assignment

1. Kotlin
2. MVVM architecture
3. Retrofit 2 by Square
4. Moshi Json Converter
5. Hilt
6. Coroutines
7. Mockk for Unit testing.
8. Kotlin Extension


# Architecture flow
Activity/Fragment -> ViewModel -> UseCase -> Repository -> NetworkService


# Code Highlights
By using last.fm API to fetch the top artist list and render the TopArtsitFragment, which is
attached on MainActivity.
API detail : http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=<API-Key>&format=json
When user tap on any list item, currently opening a detail view of the artist in Webview.


# Build Info
Android Studio - 3.1.3, Compile SDK - 32, MinSDK - 21, Target - 32

# Libraries Used
Android Support Libraries, Hilt, Retrofit, RecyclerView, CardView, LiveData, Coroutines, Moshi,Mockk.