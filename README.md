# Description

TastyTour is a restaurant finder mobile application built using the Kotlin programming language.

The app offers a clean and user-friendly interface, making it easy to navigate and find what 
you are looking for quickly. It uses Google Maps to display the location of the restaurant, 
and users can get directions to the restaurant directly from the app.

TastyTour also has a feature that allows users to search for and mark their favourite restaurants 
and save them for future reference.

TastyTour uses Firebase as its back-end infrastructure for storing restaurant data and user 
information securely. The following Firebase services are used in the app:

- Firebase Storage: Used to store and retrieve images of restaurants.
- Firebase Analytics: Provides insight into app usage and user engagement.
- Firebase Authentication: Used to authenticate users and protect data.
- Firebase Realtime Database: Stores restaurant and user data in real time.
  -Firebase Crashlytics: Provides developers with crash reports to identify and fix any 
- issues that arise during development or after deployment.

For more information on how Firebase can be used as a backend for mobile applications, 
please visit the [Firebase Website](https://firebase.google.com/)
 


# Dependencies list

androidx.core:core-ktx:1.9.0   

androidx.appcompat:appcompat:1.6.1

com.google.android.material:material:1.8.0

androidx.constraintlayout:constraintlayout:2.1.4

## Firebase
com.google.firebase:firebase-storage:20.1.0

com.google.firebase:firebase-analytics-ktx:21.2.0

com.google.firebase:firebase-auth-ktx:21.1.0

com.google.firebase:firebase-database-ktx:20.1.0

com.google.firebase:firebase-crashlytics-ktx:18.3.5

## Location
com.google.android.gms:play-services-maps:17.0.0

com.google.android.gms:play-services-location:21.0.1

com.github.bumptech.glide:glide:4.13.1

androidx.navigation:navigation-fragment-ktx:2.5.3

androidx.navigation:navigation-ui-ktx:2.5.3

annotationProcessor com.github.bumptech.glide:compiler:4.12.0

## Test Dependencies
junit:junit:4.13.2

androidx.test.ext:junit:1.1.5

androidx.test.espresso:espresso-core:3.5.1








