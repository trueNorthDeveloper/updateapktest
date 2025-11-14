// package com.example.truenorthdemoproject;

// import android.content.Context;
// import android.content.Intent;
// import android.location.LocationManager;
// import android.os.Build;
// import android.provider.Settings;

// import io.flutter.embedding.android.FlutterActivity;
// import io.flutter.embedding.engine.FlutterEngine;
// import io.flutter.plugin.common.MethodChannel;

// public class MainActivity extends FlutterActivity {
//     private static final String CHANNEL = "location_service";

//     @Override
//     public void configureFlutterEngine(FlutterEngine flutterEngine) {
//         super.configureFlutterEngine(flutterEngine);

//         new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
//                 .setMethodCallHandler((call, result) -> {
//                     if (call.method.equals("startService")) {
//                       //  requestBatteryOptimization();
//                         if (isLocationEnabled()) {
//                             Intent intent = new Intent(this, LocationService.class);
//                             startForegroundService(intent);
//                             result.success("Service Started");
//                         } else {
//                             openLocationSettings();
//                             result.error("GPS_OFF", "Please enable GPS", null);
//                         }
//                     } else if (call.method.equals("stopService")) {
//                         Intent intent = new Intent(this, LocationService.class);
//                         stopService(intent);
//                         result.success("Service Stopped");
//                     } else {
//                         result.notImplemented();
//                     }
//                 });
//     }

//     private boolean isLocationEnabled() {
//         LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//         return lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
//                 lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//     }

//     private void openLocationSettings() {
//         Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//         startActivity(intent);
//     }

//     private void requestBatteryOptimization() {
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//             Intent intent = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
//             startActivity(intent);
//         }
//     }
// }
package com.example.truenorthdemoproject;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.os.PowerManager;
import android.net.Uri;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "location_service";

    @Override
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);

        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler((call, result) -> {
                    if (call.method.equals("startService")||call.method.equals("updateVersion")) {
                        // Check if location is enabled
                        if (isLocationEnabled()) {
                            startLocationService();
                            startUpdate();
                            result.success("Service Started");
                        } else {
                            openLocationSettings();
                            result.error("GPS_OFF", "Please enable GPS", null);
                        }
                    } else if (call.method.equals("stopService")) {
                        stopLocationService();
                        result.success("Service Stopped");
                    } else {
                        result.notImplemented();
                    }
                });
    }

    // Check if either GPS or Network location is enabled
    private boolean isLocationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
               lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // Open location settings for the user
    private void openLocationSettings() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // Start the foreground LocationService safely for all Android versions
    private void startLocationService() {
        Intent serviceIntent = new Intent(this, LocationService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8+ requires foreground service for background tasks
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }

        // Optional: prompt user to disable battery optimizations
        requestBatteryOptimization();
    }
    //for update start......................
    private void startUpdate()
    {
        Intent serviceIntent = new Intent(this, LocationService.class);

    }
  
    // Stop the LocationService
    private void stopLocationService() {
        Intent serviceIntent = new Intent(this, LocationService.class);
        stopService(serviceIntent);
    }

    // Prompt user to disable battery optimizations for the app
    private void requestBatteryOptimization() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(getPackageName())) {
                Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }
}

// package com.example.truenorthdemoproject;

// import io.flutter.embedding.android.FlutterActivity;
// import io.flutter.embedding.engine.FlutterEngine;
// import io.flutter.plugin.common.MethodChannel;
// import android.content.Intent;
// import android.location.LocationManager;
// import android.provider.Settings;
// import android.content.Context;

// public class MainActivity extends FlutterActivity {
//     private static final String CHANNEL = "location_service";

//     @Override
//     public void configureFlutterEngine(FlutterEngine flutterEngine) {
//         super.configureFlutterEngine(flutterEngine);

//         new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
//                 .setMethodCallHandler((call, result) -> {
//                     if (call.method.equals("startService")) {
//                         if (isLocationEnabled()) {
//                             Intent intent = new Intent(this, LocationService.class);
//                             startForegroundService(intent);
//                             result.success("Service Started");
//                         } else {
//                             result.error("GPS_OFF", "Please enable GPS", null);
//                         }
//                     } else if (call.method.equals("stopService")) {
//                         Intent intent = new Intent(this, LocationService.class);
//                         stopService(intent);
//                         result.success("Service Stopped");
//                     } else {
//                         result.notImplemented();
//                     }
//                 });
//     }

//     private boolean isLocationEnabled() {
//         LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//         return lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
//                lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//     }
// }
