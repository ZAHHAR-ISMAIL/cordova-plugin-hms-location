## 6.16.0.300
- Updated HMS core plugin to 6.16.0.302 (16KB supported)
- Updated Gradle wrapper to 8.13

## 6.12.0.301
**New Features**

- Ionic 7 support has been added.

## 6.12.0.300
**Modified Features**

- Updated the Base-SDK to improve performance and stability.
- Added Devices Supporting Geofence.

## 6.11.0.301
**New Features**

- Android API 33 support has been added.
- Added the convertCoord method for converting WGS84 coordinates into GCJ02 coordinates.
- Added LonLat, which is a coordinate object returned after coordinate type conversion.
- Added coordinateType in HWLocation and LocationRequest.

**Modified Features**

- Added PRIORITY_HIGH_ACCURACY_AND_INDOOR (location request type) to PriorityConstants, which is used to check whether location type is indoor location or fused location.
- Optimized callback parameters of the enableBackgroundLocation and disableBackgroundLocation methods in FusedLocationService.
- Updated the device types supported by the geofence service.
- Updated the device types supported by the activity identification service.

## 6.4.0.300
**Modified Features**

- Deleted the capability of prompting users to install HMS Core (APK).

## 6.3.0.300
**New Features**

- Opened the background location service for non-Huawei Android phones, and added the enableBackgroundLocation and disableBackgroundLocation methods to FusedLocationService.
- Supported fused location on non-Huawei phones.
- Added setLogConfig and getLogConfig methods for log recording function.
- Added getFromLocation and getFromLocationName methods for geocoding function.

**Modified Features**

- Enhanced the activity identification function.
- Canceled support of the geofence function on non-Huawei Android phones.

**Deleted Features**

- Deleted the hasActivityRecognitionPermission and requestActivityRecognitionPermission methods in ActivityIdentificationService.
- Deleted the hasLocationPermission and requestLocationPermission methods in FusedLocationService.

## 5.1.0.304
**Modified Features**

- Resolved the issue about Cordova Version 10.x.
- Fixed minor issues on the demo projects.

## 5.1.0.303
- Fixed the issue that occurs when location enable dialog is canceled.
- Updated fields for checking whether GNSS is supported and whether the GNSS switch is turned on.
- Added the following fields to the LocationSettingsStates interface: isGnssPresent, isGnssUsable.
- Deprecated the following fields in the LocationSettingsStates interfaces: isGpsPresent, isGpsUsable.

## 5.0.0.301
**New Features**

- Added the getNavigationContextState function.
- Added the enableLogger and disableLogger methods.

## 4.0.4.300
- Added Ionic support with ionic-native wrapper.
- Added typescript support.
- Added the requestLocationUpdatesEx function.
- Fixed some critical bugs.

## 4.0.3.300
- Initial release.