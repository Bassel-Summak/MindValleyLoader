# MindValleyLoader 
MindValleyLoader is a simple demo application with a library to load images with users information from web service using JSON API and display them on Android device. 

<img align="left" width="100" height="100" src="https://dl.dropboxusercontent.com/s/swu83qlijjo6bzp/app_launcher.png">


**Links**

Video Review:
https://www.dropbox.com/s/c0qgfnksvurrpl1/MindValleyLoader%20Review.mp4?dl=0

Demo APK:
https://www.dropbox.com/s/fqm2ul7yozifklr/MindValleyLoader%20Demo.apk?dl=0

**Notes**
_________
- Splash UI has been implemented.
- Dagger 2 Injection, Butterknife, Retrofit and MVP design has been used in the application.
- Three test classes have been implemented, two of them are unit tests to test some ToolsFunctions' methods and Presenter class, the last one is androidTest to test CachedMemory Class. 
- Custom Library named "MindvalleyLoader" has been developed and implemented to cache and load pictures from API.
- The UI will display full name, categories, the number of likes, personal picture, cover picture and profile creation date.
- Animation on cards element has been implemented.
- Custom font has been implemented.
- SwipeToRefresh view has been implemented to update the list.
- Fab button has been implemented for auto smooth scrolling to the top of the list after reaching down elements
- The URL "http://pastebin.com/raw/wgkJgazE" that the app uses doesn't support pagination; even thought paging mechanism and design has been implemented by calling the next page from the API when the user reaches the end of the list. Because pagination is not supported by the web service, API will return the same result again.
- Pinch to zoom with two fingers has been implemented on the cover image.

**MindValleyLoader Documentation**
______________
As mentioned before, MindValleyLoader is a library to cache images on device cache memory and load them on images container

**How to use**

MindValleyLoader is very simple, all you need to do is to initilize a new object from it the send the url with the view container you want to load the url with:

```
String url = "https://cdn.vox-cdn.com/thumbor/E9UYx5TzZEry7dALAEW0hvMepTo=/0x0:1600x800/920x613/filters:focal(672x272:928x528):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/55717465/google_ai_photography_street_view_2.0.jpg";
ImageView imageView = (ImageView) findViewById (R.id.imageview);  
MindValleyImageLoader imageLoader =  new MindValleyImageLoader(appCompatActivity);
imageLoader.DisplayImage(url, imageView); // Any view that extends from ImageView can be used
```

The library will download the url from web and load it on the view then cache it in the memory cache, when you recall the same url the library will provide the picture without internet connection.

**Cache memory limitation**

When the cached memory reach the limited value, the library will only load new urls without caching them, besides, all previous images will stay in the cache memory.
The default value is 128 mb, you can change it by calling **setCacheMemory_limit(int limitValue)** method:

```
int value = 25000;
imageLoader.setCacheMemory_limit(value);
```

**Extra functions**

You can clear the memory cache by calling **clearAllCache()** method:

```
imageLoader.clearAllCache();
```

while the image is being downloaded from the internet, The library will load default holder in the image container, the default holder is grey color, but that can be changed by calling **setDefaultDrawable(int res)** method:

```
imageLoader.setDefaultDrawable(R.drawable.res); // any resource from drawable or color values
```

*This app has been developed for MindValley as a test app*
