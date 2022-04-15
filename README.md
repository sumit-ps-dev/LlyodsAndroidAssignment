# Llyods Android Assignment

This is a simple Kotlin MVVM-clean-architecture demonstration. To build this project I have used
below standard libraries of Android

1. Retrofit 2 by Square for Networking
2. Moshi Json Converter
3. Hilt Dependency Injection
4. Coroutines
5. Mockk for Unit testing.
6. Glide Image Loading


# Architecture flow
Activity/Fragment -> ViewModel -> UseCase -> Repository -> NetworkService


# Code Highlights
By using last.fm API to fetch the top artist list and render the TopArtsitFragment, which is
attached on MainActivity.

API detail : http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=<API-Key>&format=json

When user tap on any list item, currently opening a detail view of the artist in Webview.


# Build Info
Android Studio - Bumblebee, Compile SDK - 32, MinSDK - 21, Target - 32
