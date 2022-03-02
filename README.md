# WeatherApp

This is a shopping list implemented with Kotlin in Android Studio.

Implemented features:
- two activities: the first Activity holds the list of cities that the user stores
in the application and when a city is selected it’s weather details is displayed on another
Activity.
- RecyclerView that supports adding and removing
cities. ScrollingActivity template is a good choice. Cities can be added by clicking on a
FloatingActionButton that shows a Dialog where the user can enter the city name.
- FloatingActionButton for adding cities.
- Editing city names.
- Details Activity appears when a city name is clicked (city names is passed as an Intent
parameter to the details Activity). The details Activity
displays the weather information with an icon/image that refers to the weather.
- Icon downloaded based on the icon field of the retrieved JSON result using Glide library: https://openweathermap.org/img/w/10d.png

Reference: Ekler, Péter (2021) AIT2021Fall/WeatherDemo_ArchComponents [Source Code] https://github.com/peekler/AIT2021Fall/tree/main/WeatherDemo_ArchComponents
