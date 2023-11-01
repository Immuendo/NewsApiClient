# # NewsApiClient

Android application that pings backend for a list of news articles upon launch. Utilizes searchview to query list for results matching submitted characters. Click implementation of any item in the list will attempt to navigate to associated url and display within webview. Uses RoomDB on the backend to save and delete articles. The three screens of the application are "ArticlesFragment" or the Home screen, "SavedArticlesFragment" for the list of saved articles, and "InfoFragment" to display webview. 

Technologies: Recyclerview, Kotlin, Retrofit, Room, Navigation Graph, NavArgs, CoRoutines, ItemTouchHelper, MockWebServer, Clean architecture
