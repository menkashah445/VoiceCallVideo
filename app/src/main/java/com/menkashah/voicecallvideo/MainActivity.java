package com.menkashah.voicecallvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    EditText userIDeditText;
//1768309343
//    4dda78268da58602b05530dfa735cf40944ff40635c0f757e12616ba956f575d
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);


        startButton = findViewById (R.id.btn);
        userIDeditText = findViewById (R.id.edittext);

        startButton.setOnClickListener (view -> {
            String userID = userIDeditText.getText ().toString ().trim ();
            if (userID.isEmpty ()) {
                return;
            }
            startService (userID);
            Intent i = new Intent (MainActivity.this,CallActivity.class);
            i.putExtra ("userId",userID);
            startActivity (i);
        });
    }

     void startService(String userID){
         Application application = getApplication (); // Android's application context
         long appID = 1768309343;   // yourAppID
         String appSign = "4dda78268da58602b05530dfa735cf40944ff40635c0f757e12616ba956f575d";  // yourAppSign
//         String userID = userID; // yourUserID, userID should only contain numbers, English characters, and '_'.
         String userName = userID;   // yourUserName

         ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
         callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
         ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
         notificationConfig.sound = "zego_uikit_sound_call";
         notificationConfig.channelID = "CallInvitation";
         notificationConfig.channelName = "CallInvitation";
         ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
     }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        ZegoUIKitPrebuiltCallInvitationService.unInit ();
    }
}