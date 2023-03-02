package com.menkashah.voicecallvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {
EditText  userIDeditText;
TextView HeyuserTextView;
ZegoSendCallInvitationButton voiceCallBtn, videoCallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_call);
        userIDeditText = findViewById (R.id.seconduserIdedittext);
        HeyuserTextView = findViewById (R.id.usercall);
        voiceCallBtn = findViewById (R.id.voice_call_btn);
        videoCallBtn = findViewById (R.id.video_call_btn);


        String userID = getIntent ().getStringExtra ("USER_ID");
        HeyuserTextView.setText ("Hey "+userID);

        userIDeditText.addTextChangedListener (new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String targetUserIDD = userIDeditText.getText ().toString ().trim ();
                setVoiceCall (targetUserIDD);
                setVideoCall (targetUserIDD);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
     void setVoiceCall(String targetUserID){
         voiceCallBtn.setIsVideoCall(false);
         voiceCallBtn.setResourceID("zego_uikit_call");
         voiceCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser (targetUserID)));
    }
    void setVideoCall(String targetUserID){
        videoCallBtn.setIsVideoCall(true);
        videoCallBtn.setResourceID("zego_uikit_call");
        videoCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));

    }
}