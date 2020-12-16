package com.example.exampleapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.example.exampleapp.App.FCM_CHANNEL_ID;

//import static com.example.exampleapp.App.FCM_CHANNEL_ID;

public class FCMMessageRecieverService extends FirebaseMessagingService {

    public static final String TAG = "MyTag";
    Bitmap bitmap;
    public  static  final String channelId="ChannelId";
    public static final String channelName="ChannelName";

    private static final String URL_STORE__TOKEN = "http://v1.api.nanocart.in/index.php/api/home/device_update";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "onMessageRecieved: called");
        Log.d(TAG, "onMessageRecieved: Message recieved from: " + remoteMessage.getFrom());

        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        if (remoteMessage.getNotification() != null)

        {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            String imageUri = remoteMessage.getData().get("image");
//            final Uri NOTIFICATION_SOUND_URI = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + BuildConfig.APPLICATION_ID + "/" + R.raw.hasty_ba_dum_tss);


            bitmap = getBitmapfromUrl(imageUri);

//            final Notification notification = new NotificationCompat.Builder(this, FCM_CHANNEL_ID)
//                    .setSmallIcon(R.drawable.newauto)
//                    .setContentTitle(title)
//                    .setContentText(body)
//                    .setStyle(new NotificationCompat.BigPictureStyle()
//                    .bigPicture(bitmap))
//                    .setColor(Color.BLUE)
//                    .setSound(NOTIFICATION_SOUND_URI)
//                    .build();
////            notification.sound = Uri.parse("android.resource://" + getApplication().getPackageName() + "/" + R.raw.hasty_ba_dum_tss);
//
//
//
//            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//            manager.notify(1002,notification);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getResources().getString(R.string.ChannelId))
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.van,Notification.COLOR_DEFAULT)
//                    .setColor(ContextCompat.getColor(this, R.color.green))
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tune))
                    .setDefaults(Notification.DEFAULT_VIBRATE)
//                    .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
//                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                    .setLargeIcon(bitmap)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(bitmap)
                            .bigLargeIcon(null))
//                    .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                    // this above code is used for showing long text, this code will only work when the app is in foreground in firebase side and when app is in backgeound in server side
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
                .setAutoCancel(true);
            Notification notification = builder.build();
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(getResources().getString(R.string.ChannelId), getResources().getString(R.string.channelName), NotificationManager.IMPORTANCE_DEFAULT);
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                        .build();
                channel.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tune), audioAttributes);
                notificationManager.createNotificationChannel(channel);
            }
            notificationManager.notify(1, notification);


        }
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "onMessageRecieved: Data Size: " + remoteMessage.getData().size());

            for (String key : remoteMessage.getData().keySet())
            {
                Log.d(TAG, "onMessageRecieved: Key: " + key + "Data: "+remoteMessage.getData().get(key));

            }

            Log.d(TAG, "onMessageRecieved: Data: " + remoteMessage.getData().toString());

        }
    }
        @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.d(TAG,"onDeleteMessages: called");

    }

    @Override
    public void onNewToken(String token) {
//        super.onNewToken(s);
        Log.d(TAG,"onNewToken: called");
//        sendRegistrationToServer(token);

    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }

    }
}
