

Code scanner library untuk [Android](https://developer.android.com), menggunakan [ZXing](https://github.com/zxing/zxing)

The library membutuhkan akses Camera, dengan minimum SDK version 21

### Features
* Auto focus and flash light control
* Touch focus


### Usage 

Step 1. Add dependency:
```gradle
dependencies {
    implementation 'me.dm7.barcodescanner:zxing:1.9'
}
```

Step 2. Add on gradle.properties:
```gradle
android.useAndroidX=true
android.enableJetifier=true
```

Add camera permission and hardware feature to AndroidManifest.xml (Don't forget about dynamic permissions on API >= 23):

```xml
<uses-permission android:name="android.permission.CAMERA"/>

<uses-feature
    android:name="android.hardware.camera"
    android:required="false"/>
```


### Preview
![Preview screenshot](https://rsachmadmochtar.net/github/Barcode_Scanner.jpeg)
