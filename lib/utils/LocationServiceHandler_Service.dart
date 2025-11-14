import 'package:flutter/services.dart';

class LocationServiceHandler {
  static const platform = MethodChannel('location_service');
  //this method is used to start the location servie in background
  static Future<void> startLocationService()async
  {
    try {
      await platform.invokeMethod('startService');
      await platform.invokeMethod('updateVersion');
     // print("✅ Location Service started successfully");
    } on PlatformException catch (e) {
      if (e.code == 'PERMISSION_DENIED') {
        print("⚠️ Permission denied: ${e.message}");
        throw Exception("PermissionDenied");
      } else {
        print("❌ Error: ${e.message}");
        rethrow;
      }
    }
  }

  //this method  is used to stop the location service in background
  static Future<void> stoplocationService()async
  {
    try{
       await platform.invokeMethod('stopService');
      print("🛑 Location service stopped");
    }on PlatformException catch (e) {
      print("Error stopping location service: ${e.message}");
    }}
}
