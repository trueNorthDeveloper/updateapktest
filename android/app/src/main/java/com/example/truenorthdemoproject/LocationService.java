// package com.example.truenorthdemoproject;

// import android.Manifest;
// import android.app.Notification;
// import android.app.NotificationChannel;
// import android.app.NotificationManager;
// import android.app.Service;
// import android.content.Intent;
// import android.content.pm.PackageManager;
// import android.location.Location;
// import android.location.LocationListener;
// import android.location.LocationManager;
// import android.os.Build;
// import android.os.Handler;
// import android.os.IBinder;
// import android.provider.Settings;

// import androidx.annotation.Nullable;
// import androidx.core.app.ActivityCompat;
// import androidx.core.app.NotificationCompat;

// public class LocationService extends Service {

//     private static final String CHANNEL_ID = "location_channel";
//     private LocationManager locationManager;
//     private LocationListener locationListener;
//     private Handler handler = new Handler();
//     private Runnable runnable;

//     @Nullable
//     @Override
//     public IBinder onBind(Intent intent) {
//         return null;
//     }

//     @Override
//     public void onCreate() {
//         super.onCreate();
//         createNotificationChannel();
//         startForegroundServiceNotification();
//         startLocationUpdates();
//     }

//     @Override
//     public int onStartCommand(Intent intent, int flags, int startId) {
//         return START_STICKY;
//     }

//     private void startForegroundServiceNotification() {
//         Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
//                 .setContentTitle("Location Service Running")
//                 .setContentText("Tracking your location")
//                 .setSmallIcon(R.mipmap.ic_launcher)
//                 .build();
//         startForeground(1, notification);
//     }

//     private void startLocationUpdates() {
//         locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

//         locationListener = new LocationListener() {
//             @Override
//             public void onLocationChanged(Location location) {
//                 double lat = location.getLatitude();
//                 double lon = location.getLongitude();
//                 System.out.println("📍 Latitude: " + lat + ", Longitude: " + lon);
//                 // sendToServer(lat, lon); // Optional API call
//             }

//             @Override
//             public void onStatusChanged(String provider, int status, android.os.Bundle extras) {}

//             @Override
//             public void onProviderEnabled(String provider) {}

//             @Override
//             public void onProviderDisabled(String provider) {}
//         };

//         runnable = new Runnable() {
//             @Override
//             public void run() {
//                 checkAndRequestLocation();
//                 handler.postDelayed(this, 10000); // Run every 10 seconds
//             }
//         };
//         handler.post(runnable);
//     }

//     private void checkAndRequestLocation() {
//         // Permission check
//         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                 != PackageManager.PERMISSION_GRANTED) {
//             System.out.println("⚠️ Permission not granted! Opening app settings...");
//             Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//             intent.setData(android.net.Uri.parse("package:" + getPackageName()));
//             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//             startActivity(intent);
//             return;
//         }

//         // GPS check
//         if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//             System.out.println("⚠️ GPS OFF! Opening location settings...");
//             Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//             startActivity(intent);
//             return;
//         }

//         // Request location update
//         locationManager.requestLocationUpdates(
//                 LocationManager.GPS_PROVIDER,
//                 5000, // Minimum 5 seconds between updates
//                 0,
//                 locationListener
//         );
//     }

//     private void createNotificationChannel() {
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//             NotificationChannel channel = new NotificationChannel(
//                     CHANNEL_ID,
//                     "Location Tracking",
//                     NotificationManager.IMPORTANCE_LOW
//             );
//             NotificationManager manager = getSystemService(NotificationManager.class);
//             manager.createNotificationChannel(channel);
//         }
//     }

//     @Override
//     public void onDestroy() {
//         super.onDestroy();
//         handler.removeCallbacks(runnable);
//         if (locationManager != null && locationListener != null) {
//             locationManager.removeUpdates(locationListener);
//         }
//         System.out.println("🛑 Location service stopped.");
//     }
// }
//----------------------------------------------------------------
// package com.example.truenorthdemoproject;

// import android.Manifest;
// import android.app.Notification;
// import android.app.NotificationChannel;
// import android.app.NotificationManager;
// import android.app.Service;
// import android.content.Intent;
// import android.content.pm.PackageManager;
// import android.location.Location;
// import android.location.LocationListener;
// import android.location.LocationManager;
// import android.os.Build;
// import android.os.Handler;
// import android.os.IBinder;
// import android.provider.Settings;

// import androidx.annotation.Nullable;
// import androidx.core.app.ActivityCompat;
// import androidx.core.app.NotificationCompat;

// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;

// public class LocationService extends Service {

//     private static final String CHANNEL_ID = "location_channel";
//     private LocationManager locationManager;
//     private LocationListener locationListener;
//     private Handler handler = new Handler();
//     private Runnable runnable;

//     @Nullable
//     @Override
//     public IBinder onBind(Intent intent) {
//         return null;
//     }

//     @Override
//     public void onCreate() {
//         super.onCreate();
//         createNotificationChannel();

//         Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
//                 .setContentTitle("Location Service Running")
//                 .setContentText("Tracking your location")
//                 .setSmallIcon(R.mipmap.ic_launcher)
//                 .build();

//         startForeground(1, notification);

//         startLocationUpdates();
//     }

//     private void startLocationUpdates() {
//         locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

//         locationListener = location -> {
//             double lat = location.getLatitude();
//             double lon = location.getLongitude();
//             System.out.println("📍 Latitude: " + lat + ", Longitude: " + lon);
//           //  sendToServer(lat, lon);
//         };

//         runnable = new Runnable() {
//             @Override
//             public void run() {
//                 checkAndRequestLocation();
//                 // Retry every 10 seconds
//                 handler.postDelayed(this, 10000);
//             }
//         };
//         handler.post(runnable);
//     }

//     private void checkAndRequestLocation() {
//         // Check permission
//         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                 != PackageManager.PERMISSION_GRANTED) {
//             System.out.println("⚠️ Permission not granted! Opening app settings...");
//             Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//             intent.setData(android.net.Uri.parse("package:" + getPackageName()));
//             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//             startActivity(intent);
//             return;
//         }

//         // Check GPS
//         if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//             System.out.println("⚠️ GPS OFF! Opening location settings...");
//             Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//             startActivity(intent);
//             return;
//         }

//         // Request location updates
//         locationManager.requestLocationUpdates(
//                 LocationManager.GPS_PROVIDER,
//                 5000,
//                 0,
//                 locationListener
//         );
//     }

//     // private void sendToServer(double lat, double lon) {
//     //     new Thread(() -> {
//     //         try {
//     //             URL url = new URL("https://your-springboot-api.com/api/location");
//     //             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//     //             conn.setRequestMethod("POST");
//     //             conn.setRequestProperty("Content-Type", "application/json");
//     //             conn.setDoOutput(true);

//     //             String json = "{\"userId\": \"123\", \"latitude\": " + lat + ", \"longitude\": " + lon + "}";
//     //             OutputStream os = conn.getOutputStream();
//     //             os.write(json.getBytes());
//     //             os.flush();
//     //             os.close();

//     //             int responseCode = conn.getResponseCode();
//     //             System.out.println("🌐 API Response Code: " + responseCode);

//     //             conn.disconnect();
//     //         } catch (Exception e) {
//     //             e.printStackTrace();
//     //         }
//     //     }).start();
//     // }

//     private void createNotificationChannel() {
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//             NotificationChannel channel = new NotificationChannel(
//                     CHANNEL_ID,
//                     "Location Tracking",
//                     NotificationManager.IMPORTANCE_DEFAULT
//             );
//             NotificationManager manager = getSystemService(NotificationManager.class);
//             manager.createNotificationChannel(channel);
//         }
//     }

//     @Override
//     public void onDestroy() {
//         super.onDestroy();
//         handler.removeCallbacks(runnable);
//         if (locationManager != null && locationListener != null) {
//             locationManager.removeUpdates(locationListener);
//         }
//         System.out.println("🛑 Location service stopped.");
//     }
// }
//-----------------------------------------------------------------
// package com.example.truenorthdemoproject;

// import android.Manifest;
// import android.app.NotificationChannel;
// import android.app.NotificationManager;
// import android.app.PendingIntent;
// import android.app.Service;
// import android.content.Context;
// import android.content.Intent;
// import android.content.pm.PackageManager;
// import android.location.Location;
// import android.location.LocationListener;
// import android.location.LocationManager;
// import android.os.Build;
// import android.os.Handler;
// import android.os.IBinder;
// import android.provider.Settings;

// import androidx.annotation.Nullable;
// import androidx.core.app.ActivityCompat;
// import androidx.core.app.NotificationCompat;

// public class LocationService extends Service {

//     private static final String CHANNEL_ID = "location_channel";
//     private LocationManager locationManager;
//     private LocationListener locationListener;
//     private Handler handler = new Handler();
//     private Runnable runnable;

//     @Nullable
//     @Override
//     public IBinder onBind(Intent intent) {
//         return null;
//     }

//     @Override
//     public void onCreate() {
//         super.onCreate();
//         createNotificationChannel();

//         // Foreground notification
//         NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                 .setContentTitle("Location Service Running")
//                 .setContentText("Tracking your location")
//                 .setSmallIcon(R.mipmap.ic_launcher)
//                 .setPriority(NotificationCompat.PRIORITY_LOW);

//         startForeground(1, builder.build());
//  registerGpsSwitchReceiver();
//         startLocationUpdates();
//     }

//     @Override
//     public int onStartCommand(Intent intent, int flags, int startId) {
//         // Ensure service restarts if killed
//         return START_STICKY;
//     }
// private void registerGpsSwitchReceiver() {
//     gpsSwitchStateReceiver = new BroadcastReceiver() {
//         @Override
//         public void onReceive(Context context, Intent intent) {
//             if (intent.getAction() != null &&
//                 intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
//                 // GPS ON/OFF changed, try fetching location again
//                 checkAndRequestLocation();
//             }
//         }
//     };
//     IntentFilter filter = new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION);
//     filter.addAction(Intent.ACTION_PROVIDER_CHANGED); // some devices
//     registerReceiver(gpsSwitchStateReceiver, filter);
// }
//     private void startLocationUpdates() {
//         locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

//         locationListener = new LocationListener() {
//             @Override
//             public void onLocationChanged(Location location) {
//                 double lat = location.getLatitude();
//                 double lon = location.getLongitude();
//                 System.out.println("📍 Latitude: " + lat + ", Longitude: " + lon);
//                 // sendToServer(lat, lon); // Optional
//             }
//         };

//         runnable = new Runnable() {
//             @Override
//             public void run() {
//                 checkAndRequestLocation();
//                 handler.postDelayed(this, 10000); // Retry every 10 seconds
//             }
//         };
//         handler.post(runnable);
//     }

//     private void checkAndRequestLocation() {
//         // Permission check
//         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                 != PackageManager.PERMISSION_GRANTED) {
//             System.out.println("⚠️ Permission not granted! Opening app settings...");
//             openAppSettings();
//             return;
//         }

//         // GPS check
//         if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//             System.out.println("❌ GPS is OFF! Opening location settings...");
//             openLocationSettings();
//             return;
//         }

//         // Request location updates
//         locationManager.requestLocationUpdates(
//                 LocationManager.GPS_PROVIDER,
//                 5000,
//                 0,
//                 locationListener
//         );
//     }

//     private void openLocationSettings() {
//         Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//         startActivity(intent);
//     }

//     private void openAppSettings() {
//         Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//         intent.setData(android.net.Uri.parse("package:" + getPackageName()));
//         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//         startActivity(intent);
//     }

//     private void createNotificationChannel() {
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//             NotificationChannel channel = new NotificationChannel(
//                     CHANNEL_ID,
//                     "Location Tracking",
//                     NotificationManager.IMPORTANCE_LOW
//             );
//             NotificationManager manager = getSystemService(NotificationManager.class);
//             manager.createNotificationChannel(channel);
//         }
//     }

   
//    @Override
// public void onDestroy() {
//     super.onDestroy();
//     handler.removeCallbacks(runnable);
//     if (locationManager != null && locationListener != null) {
//         locationManager.removeUpdates(locationListener);
//     }
//     if (gpsSwitchStateReceiver != null) {
//         unregisterReceiver(gpsSwitchStateReceiver);
//     }
//     System.out.println("🛑 Location service stopped.");
// }
// }
//----------------------------------------------
// package com.example.truenorthdemoproject;

// import android.Manifest;
// import android.app.AlarmManager;
// import android.app.NotificationChannel;
// import android.app.NotificationManager;
// import android.app.PendingIntent;
// import android.app.Service;
// import android.content.BroadcastReceiver;
// import android.content.Context;
// import android.content.Intent;
// import android.content.IntentFilter;
// import android.content.pm.PackageManager;
// import android.location.Location;
// import android.location.LocationListener;
// import android.location.LocationManager;
// import android.net.Uri;
// import android.os.Build;
// import android.os.Handler;
// import android.os.IBinder;
// import android.os.PowerManager;
// import android.os.SystemClock;
// import android.provider.Settings;

// import androidx.annotation.Nullable;
// import androidx.core.app.ActivityCompat;
// import androidx.core.app.NotificationCompat;

// public class LocationService extends Service {

//     private static final String CHANNEL_ID = "location_channel";
//     private LocationManager locationManager;
//     private LocationListener locationListener;
//     private Handler handler = new Handler();
//     private Runnable runnable;
//     private BroadcastReceiver gpsSwitchReceiver;

//     @Nullable
//     @Override
//     public IBinder onBind(Intent intent) {
//         return null;
//     }

//     @Override
//     public void onCreate() {
//         super.onCreate();
//         createNotificationChannel();
//         startForegroundNotification();
//         startLocationUpdates();
//         registerGpsSwitchReceiver(); // 
//         requestBatteryOptimization(); // ✅ Ask user to exclude app from battery optimization
//     }

//     private void startForegroundNotification() {
//         NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                 .setContentTitle("Location Service Running")
//                 .setContentText("Tracking your location")
//                 .setSmallIcon(R.mipmap.ic_launcher)
//                 .setPriority(NotificationCompat.PRIORITY_LOW);

//                     startForeground(1, builder.build());
//     }

//     @Override
//     public int onStartCommand(Intent intent, int flags, int startId) {
//         // Ensures service restarts automatically if killed
//         return START_STICKY;
//     }

//     private void startLocationUpdates() {
//         locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

//         locationListener = new LocationListener() {
//             @Override
//             public void onLocationChanged(Location location) {
//                 double lat = location.getLatitude();
//                 double lon = location.getLongitude();
//               //  System.out.println("📍 Latitude: " + lat + ", Longitude: " + lon);
//             }

//             @Override
//             public void onProviderEnabled(String provider) {
//                 System.out.println("📡 Provider enabled: " + provider);
//             }

//             @Override
//             public void onProviderDisabled(String provider) {
//                 System.out.println("📴 Provider disabled: " + provider);
//             }
//         };

//         runnable = new Runnable() {
//             @Override
//             public void run() {
//                 checkAndRequestLocation();
//                 handler.postDelayed(this, 10000); // check every 10 seconds
//             }
//         };
//         handler.post(runnable);
//     }

//     /**
//      * Checks permissions and GPS state, then requests location updates.
//      */
//    private void checkAndRequestLocation() {
//     if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//             != PackageManager.PERMISSION_GRANTED) {
//         System.out.println("⚠️ Permission not granted! Opening app settings...");
//         openAppSettings();
//         return;
//     }

//     if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//         System.out.println("❌ GPS is OFF! (Cannot open settings in background)");
//         showGpsOffNotification();
//         return;
//     }

//     // Remove previous listeners to avoid duplicates
//     locationManager.removeUpdates(locationListener);

//     // ✅ Request continuous updates
//     locationManager.requestLocationUpdates(
//             LocationManager.GPS_PROVIDER,
//             5000,
//             0,
//             locationListener
//     );

//     System.out.println("✅ Location updates requested successfully.");

//     // ✅ Immediately try to get last known location (for instant fix)
//     Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//     if (lastLocation != null) {
//         double lat = lastLocation.getLatitude();
//         double lon = lastLocation.getLongitude();
//       //  System.out.println("📍 Last known Latitude: " + lat + ", Longitude: " + lon);
//     } else {
//         System.out.println("ℹ️ No last known location available yet.");
//     }

//     // ✅ (Optional) Force a one-time current location on Android 11+
//     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//         locationManager.getCurrentLocation(LocationManager.GPS_PROVIDER, null, getMainExecutor(), location -> {
//             if (location != null) {
//                 double lat = location.getLatitude();
//                 double lon = location.getLongitude();
//             //   System.out.println("📍 Immediate fix Latitude: " + lat + ", Longitude: " + lon);
//                 //se
//             } else {
//                 System.out.println("ℹ️ Current location request returned null (still waiting for GPS fix).");
//             }
//         });
//     }
// }


//     /**
//      * Show notification prompting user to enable GPS manually.
//      * This works safely even when the app is killed or running in background.
//      */
//     private void showGpsOffNotification() {
//         NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                 .setContentTitle("GPS is OFF")
//                 .setContentText("Tap to enable location services")
//                 .setSmallIcon(R.mipmap.ic_launcher)
//                 .setPriority(NotificationCompat.PRIORITY_HIGH)
//                 .setAutoCancel(true);

//         // When tapped, open location settings
//         Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//         PendingIntent pendingIntent = PendingIntent.getActivity(
//                 this,
//                 0,
//                 intent,
//                 PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
//         );
//         builder.setContentIntent(pendingIntent);

//         NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//         manager.notify(2, builder.build());
//     }

//     /**
//      * Detect GPS ON/OFF dynamically while the service is running.
//      */
//     private void registerGpsSwitchReceiver() {
//     gpsSwitchReceiver = new BroadcastReceiver() {
//         @Override
//         public void onReceive(Context context, Intent intent) {
//             if (intent.getAction() != null &&
//                 intent.getAction().equals(LocationManager.PROVIDERS_CHANGED_ACTION)) {

//                 LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                 boolean isGpsOn = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

//                 if (isGpsOn) {
//                     System.out.println("✅ GPS turned ON! Restarting location updates...");

//                     // Wait 1 second before requesting location updates
//                     handler.postDelayed(() -> {
//                         // Recreate a new listener to avoid stale callback issues
//                         locationListener = new LocationListener() {
//                             @Override
//                             public void onLocationChanged(Location location) {
//                                 double lat = location.getLatitude();
//                                 double lon = location.getLongitude();
//                                 float accuracy = location.getAccuracy(); // in meters
//                                  long timestamp = location.getTime();
//                                // System.out.println("📍 Latitude: " + lat + ", Longitude: " + lon);
//                                 sendLocationToServer(lat, lon, accuracy, timestamp);
//                             }

//                             @Override
//                             public void onProviderEnabled(String provider) {
//                                 System.out.println("📡 Provider enabled: " + provider);
//                             }

//                             @Override
//                             public void onProviderDisabled(String provider) {
//                                 System.out.println("📴 Provider disabled: " + provider);
//                             }
//                         };

//                         checkAndRequestLocation();
//                     }, 1000);

//                 } else {
//                     System.out.println("❌ GPS turned OFF!");
//                     showGpsOffNotification();
//                 }
//             }
//         }
//     };

//     IntentFilter filter = new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION);
//     registerReceiver(gpsSwitchReceiver, filter);
// }
// private void sendLocationToServer(double lat, double lon, float accuracy, long timestamp) {
//     // Example (use your networking client here: Retrofit, Volley, HttpURLConnection, etc.)
//     System.out.println("🚀 Sending to server: Lat=" + lat + " Lon=" + lon + " Acc=" + accuracy);
// }

//     private void openAppSettings() {
//         Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//         intent.setData(Uri.parse("package:" + getPackageName()));
//         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//         startActivity(intent);
//     }

//     private void createNotificationChannel() {
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//             NotificationChannel channel = new NotificationChannel(
//                     CHANNEL_ID,
//                     "Keep Location Turned ON",
//                     NotificationManager.IMPORTANCE_LOW
//             );
//             NotificationManager manager = getSystemService(NotificationManager.class);
//             manager.createNotificationChannel(channel);
//         }
//     }

//     private void requestBatteryOptimization() {
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//             PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//             if (!pm.isIgnoringBatteryOptimizations(getPackageName())) {
//                 Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
//                 intent.setData(Uri.parse("package:" + getPackageName()));
//                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                 startActivity(intent);
//             }
//         }
//     }

//     /**
//      * Ensures the service restarts if the user removes the app from Recents.
//      */
//     @Override
//     public void onTaskRemoved(Intent rootIntent) {
//         super.onTaskRemoved(rootIntent);
//         Intent restartServiceIntent = new Intent(getApplicationContext(), LocationService.class);
//         restartServiceIntent.setPackage(getPackageName());
//         PendingIntent restartServicePendingIntent = PendingIntent.getService(
//                 getApplicationContext(),
//                 1,
//                 restartServiceIntent,
//                 PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE
//         );
//         AlarmManager alarmService = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//         alarmService.set(
//                 AlarmManager.ELAPSED_REALTIME,
//                 SystemClock.elapsedRealtime() + 3000, // restart after 3 seconds
//                 restartServicePendingIntent
//         );
//     }

//     @Override
//     public void onDestroy() {
//         super.onDestroy();
//         handler.removeCallbacks(runnable);
//         if (locationManager != null && locationListener != null) {
//             locationManager.removeUpdates(locationListener);
//         }
//         if (gpsSwitchReceiver != null) {
//             unregisterReceiver(gpsSwitchReceiver);
//         }
//         System.out.println("🛑 Location service stopped.");
//     }
// }
//--------------------------------------------------------------------------------------------
package com.example.truenorthdemoproject;
import android.content.SharedPreferences; 
import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
///------------------------------------------
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
//import com.example.truenorthdemoproject.BuildConfig;

public class LocationService extends Service {

    private static final String CHANNEL_ID = "location_channel";
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Handler handler = new Handler();
    private Runnable runnable;
    private BroadcastReceiver gpsSwitchReceiver;

    // 🧩 Store last known coordinates (for periodic sending if needed)
    private double lastLat = 0.0;
    private double lastLon = 0.0;
    private float lastAccuracy = 0f;
    private long lastTimestamp = 0L;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        checkForUpdate(this);
        createNotificationChannel();
        startForegroundNotification();
        startLocationUpdates();
        registerGpsSwitchReceiver(); 
        requestBatteryOptimization(); 
    }
     public static void checkForUpdate(Context context) {
        System.out.println("version check comment-------------------");
        String versionUrl ="https://sscpl.org.in/app/version.json";  // change to your own URL

        new Thread(() -> {
            try {
                // Fetch version.json
                URL url = new URL(versionUrl);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();

                JSONObject json = new JSONObject(sb.toString());
                int latestVersionCode = json.getInt("latest_version_code");
                String apkUrl = json.getString("apk_url");
                String changelog = json.optString("changelog", "");

                int currentVersion = 0;
                try {
                    currentVersion = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0).versionCode;
                } catch (Exception e) {
                    e.printStackTrace(); 
                }


                //int currentVersion = BuildConfig.VERSION_CODE;

                if (latestVersionCode > currentVersion) {
                    // Run dialog on UI thread
                    new Handler(Looper.getMainLooper()).post(() -> {
                        new AlertDialog.Builder(context)
                                .setTitle("Update Available")
                                .setMessage("A new version of the app is available.\n\n" + changelog)
                                .setCancelable(false)
                                .setPositiveButton("Update", (dialog, which) -> downloadAndInstallApk(context, apkUrl))
                                .show();
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

 private static void downloadAndInstallApk(Context context, String apkUrl) {
        try {
            String fileName = "update.apk";

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl));
            request.setTitle("Downloading update...");
            request.setDescription("Please wait");
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);

            Toast.makeText(context, "Download started. Check notification to install.", Toast.LENGTH_LONG).show();

        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No download manager found", Toast.LENGTH_SHORT).show();
        }
    }

    
    private void startForegroundNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Location Service Running")
                .setContentText("Tracking your location")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_LOW);

        startForeground(1, builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY; // service restarts automatically if killed
    }

    private void startLocationUpdates() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                float accuracy = location.getAccuracy();
                long timestamp = location.getTime();

                // store for reuse
                lastLat = lat;
                lastLon = lon;
                lastAccuracy = accuracy;
                lastTimestamp = timestamp;

                // ✅ Always send to server
                sendLocationToServer(lat, lon, accuracy, timestamp);
            }

            @Override
            public void onProviderEnabled(String provider) {
                System.out.println("📡 Provider enabled: " + provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                System.out.println("📴 Provider disabled: " + provider);
            }
        };

        runnable = new Runnable() {
            @Override
            public void run() {
                checkAndRequestLocation();
                handler.postDelayed(this, 10000); // recheck every 10 seconds
            }
        };
        handler.post(runnable);
    }

    private void checkAndRequestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            System.out.println("⚠️ Permission not granted! Opening app settings...");
            openAppSettings();
            return;
        }

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            System.out.println("❌ GPS is OFF! (Cannot open settings in background)");
            showGpsOffNotification();
            return;
        }

        // Avoid duplicate listeners
        locationManager.removeUpdates(locationListener);

        // ✅ Request continuous GPS updates
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0,
                locationListener
        );

        System.out.println("✅ Location updates requested successfully.");

        // ✅ Try to get last known location immediately
        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            double lat = lastLocation.getLatitude();
            double lon = lastLocation.getLongitude();
            float acc = lastLocation.getAccuracy();
            long ts = lastLocation.getTime();

            // store and send immediately
            lastLat = lat;
            lastLon = lon;
            lastAccuracy = acc;
            lastTimestamp = ts;
            sendLocationToServer(lat, lon, acc, ts);
        }

        // ✅ One-time current location (Android 11+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            locationManager.getCurrentLocation(LocationManager.GPS_PROVIDER, null, getMainExecutor(), location -> {
                if (location != null) {
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();
                    float acc = location.getAccuracy();
                    long ts = location.getTime();

                    lastLat = lat;
                    lastLon = lon;
                    lastAccuracy = acc;
                    lastTimestamp = ts;

                    sendLocationToServer(lat, lon, acc, ts);
                } else {
                    System.out.println("ℹ️ Current location request returned null.");
                }
            });
        }
    }

    private void showGpsOffNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("GPS is OFF")
                .setContentText("Tap to enable location services")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(2, builder.build());
    }

    private void registerGpsSwitchReceiver() {
        gpsSwitchReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() != null &&
                        intent.getAction().equals(LocationManager.PROVIDERS_CHANGED_ACTION)) {

                    LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    boolean isGpsOn = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                    if (isGpsOn) {
                        System.out.println("✅ GPS turned ON! Restarting location updates...");

                        handler.postDelayed(() -> {
                            checkAndRequestLocation();
                        }, 1000);
                    } else {
                        System.out.println("❌ GPS turned OFF!");
                        showGpsOffNotification();
                    }
                }
            }
        };

        IntentFilter filter = new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION);
        registerReceiver(gpsSwitchReceiver, filter);
    }

    // ✅ Replace with your real network call (Retrofit, Volley, etc.)
    private void sendLocationToServer(double lat, double lon, float accuracy, long timestamp) {
        System.out.println("🚀 Sending to server: Lat=" + lat + " Lon=" + lon + " Acc=" + accuracy);
        // Java
   SharedPreferences preferences = getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE);
    String myValue = preferences.getString("flutter.loginId", null);
    System.out.println("UserID = " + myValue);

        // Example:
        // ApiClient.sendLocation(lat, lon, accuracy, timestamp);
    }

    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Keep Location Turned ON",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

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

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Intent restartServiceIntent = new Intent(getApplicationContext(), LocationService.class);
        restartServiceIntent.setPackage(getPackageName());
        PendingIntent restartServicePendingIntent = PendingIntent.getService(
                getApplicationContext(),
                1,
                restartServiceIntent,
                PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE
        );
        AlarmManager alarmService = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmService.set(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 3000, // restart after 3 seconds
                restartServicePendingIntent
        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
        if (gpsSwitchReceiver != null) {
            unregisterReceiver(gpsSwitchReceiver);
        }
        System.out.println("🛑 Location service stopped.");
    }
}

