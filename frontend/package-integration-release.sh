#!/bin/sh

UNSINGED_APK="android-release-unsigned.apk"
UNSIGNED_ALIGNED_APK="stats-bookie-unsigned-aligned.apk"

DESKTOP_PATH="/home/nemanja/Desktop"
KEYSTORE_PATH="$DESKTOP_PATH/android-apps-release-key.jks"
RELEASE_APK_PATH="$DESKTOP_PATH/stats-bookie-integration-release.apk"
UNSIGNED_ALIGNED_DESKTOP="$DESKTOP_PATH/$UNSIGNED_ALIGNED_APK"

rm -rf cordova/platforms/android/build/outputs/apk/*

npm run build-integration
cd cordova
cordova build --release android

cd platforms/android/build/outputs/apk/release
zipalign -v -p 4 $UNSINGED_APK $UNSIGNED_ALIGNED_APK
mv $UNSIGNED_ALIGNED_APK $DESKTOP_PATH

cd $ANDROID_SDK_TOOLS

./apksigner sign --ks $KEYSTORE_PATH --out $RELEASE_APK_PATH $UNSIGNED_ALIGNED_DESKTOP
