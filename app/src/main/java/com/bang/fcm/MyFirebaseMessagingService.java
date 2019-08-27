package com.bang.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.bang.R;
import com.bang.module.home.MainActivity;
import com.bang.module.home.profile.mypost.MyPostActivity;
import com.bang.module.home.profile.otheruserProfile.OtherUserProfileActivity;
import com.bang.module.home.survey.activity.SurveyDetailActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private String CHANNEL_ID = "com.bang";// The id of the channel.
    CharSequence name = "Abc";// The user-visible name of the channel.
    String notification_type, title, by_user_id, survey_id,body,newsfeed_id,bang_request_id;
    private NotificationModel notificationModel;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i("notification4254", "" + remoteMessage.getData().toString());

        if (remoteMessage.getData().containsKey("notification_type")) {
            notificationModel = new NotificationModel();
            notificationModel.setNotification_type(remoteMessage.getData().get("notification_type"));
            notificationModel.setBang_request_id(remoteMessage.getData().get("bang_request_id"));
            notificationModel.setBody(remoteMessage.getData().get("body"));
            notificationModel.setBy_user_id(remoteMessage.getData().get("by_user_id"));
            notificationModel.setNewsfeed_id(remoteMessage.getData().get("newsfeed_id"));
            notificationModel.setTitle(remoteMessage.getData().get("title"));
            notificationModel.setSurvey_id(remoteMessage.getData().get("survey_id"));
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Notificatons", notificationModel);
        sendNotification(remoteMessage.getTtl(), title, intent, notificationModel);
        }
      /*  notification_type = remoteMessage.getData().get("notification_type");
        body = remoteMessage.getData().get("body");
        title = remoteMessage.getData().get("title");
        by_user_id = remoteMessage.getData().get("by_user_id");
        survey_id = remoteMessage.getData().get("survey_id");
        newsfeed_id = remoteMessage.getData().get("newsfeed_id");
        bang_request_id = remoteMessage.getData().get("bang_request_id");*/
      //  click_action = remoteMessage.getData().get("click_action");
      //  sound = remoteMessage.getData().get("sound");



    private void sendNotification(int id, String title, Intent intent , NotificationModel notificationModal) {
        assert intent != null;
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int iUniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), iUniqueId, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        int importance = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            importance = NotificationManager.IMPORTANCE_HIGH;
        }
        NotificationChannel mChannel;
        NotificationCompat.Builder notificationBuilder;
            notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle(notificationModal.getTitle())
                    .setContentText(notificationModal.getBody())
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                    .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                    .setPriority(Notification.PRIORITY_HIGH);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                notificationBuilder.setSmallIcon(R.drawable.logo);
                notificationBuilder.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
            } else {
                notificationBuilder.setSmallIcon(R.drawable.logo);
            }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(mChannel);

        }
        assert notificationManager != null;
        notificationManager.notify(iUniqueId, notificationBuilder.build());
    }

    private void sendChatNotification(String title, String body, String uid, String type) {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("type", type);
        intent.putExtra("title", title);
        intent.putExtra("body", body);
        intent.putExtra("uid", uid);


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        int iUniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), iUniqueId, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        int importance = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            importance = NotificationManager.IMPORTANCE_HIGH;
        }
        NotificationChannel mChannel;

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(this.title)
                .setContentText(this.body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setSmallIcon(R.drawable.logo);
            notificationBuilder.setColor(getResources().getColor(R.color.colorPrimary));
        } else {
            notificationBuilder.setSmallIcon(R.drawable.logo);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(mChannel);

        }
        assert notificationManager != null;
        notificationManager.notify(iUniqueId, notificationBuilder.build());
    }

   /* private void sendAdminNotification(String list_type, String title, String body, String type, String click_action, String notify_for, String notification_id) {

        int iUniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //String CHANNEL_ID = "my_channel_01";// The id of the channel.
      //  CharSequence name = "Abc";// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = null;

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(this.title)
                .setContentText(this.body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL);


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setSmallIcon(R.drawable.logo);
            notificationBuilder.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        } else {
            notificationBuilder.setSmallIcon(R.drawable.logo);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setShowBadge(true);
            mChannel.enableLights(true);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(mChannel);
        }
        assert notificationManager != null;
        notificationManager.notify(iUniqueId, notificationBuilder.build());
    }*/
}