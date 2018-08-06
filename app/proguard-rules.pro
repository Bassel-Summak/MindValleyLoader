# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile



### *************** Retrofit **************** ###

#  Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain service method parameters.
-keepclassmembernames,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

### *************** wave animation **************** ###
-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

#***********************************************#

#GreenDao
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn com.squareup.okhttp.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**
-keep public class net.sqlcipher.** {
    *;
}
-ignorewarnings

-keep class * {
    public private *;
}


-keep public class net.sqlcipher.database.** {
    *;
}
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
public static java.lang.String DB_NAME;
public static java.lang.String TABLENAME;
}
-keep class **$Properties

-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String DB_NAME;
public static java.lang.String TABLENAME;
}

# models
-keep class com.mulingo.mvp.model.** { *; }
-keep class com.mulingo.db.model.** { *; }

# Gson

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# WebView
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

#-keepattributes SourceFile,LineNumberTable

#-renamesourcefileattribute SourceFile




-dontwarn com.google.code.**
-dontwarn oauth.signpost.**

#twitter
-dontwarn twitter4j.**
-keep  class twitter4j.conf.PropertyConfigurationFactory
-keep class twitter4j.** { *; }



#facebook
-keep class com.facebook.** { *; }
-dontwarn com.facebook.**
-keepclassmembers class * implements java.io.Serializable {
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
