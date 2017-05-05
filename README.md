# GradSoundCloudDemo
An Application built as apart of an interview process for Android Development
## Description
Connects to the soundcloud API & load a SoundCloud User information included their liked/favourited tracks.
### Requirements
* Data must be cached locally & refreshed every 2 minutes
* User notification of errors
* User SOLID principles
* All config changes must be handled dynamically
## Architecture
The application makes use of the following:

* MVP
* Android Loaders
* SQLite database
* Observer pattern for updates
* Alarm service for updates bound to the lifecycle

## Credit
Alot of the ideas for the structure of the application comes from googles own [samples](https://github.com/googlesamples)

