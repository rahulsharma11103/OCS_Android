package com.aeon.myoncall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TermsConditions extends BaseActivity {


    public static boolean termsAndConditionCheck;
    public static String SCREEN_TYPE = "screen_type";
    public static String SCREEN_TYPE_REGISTER = "screen_type_register";


    Button btn_tcback, mDeclineBtn, mAcceptButton;
    WebView mWebView;
    LinearLayout mBottomButtonContainer;
    String mScreenType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setScreenName("Terms & Conditions Screen");
        disableScreenTrack();

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_conditions);

        mScreenType = getIntent().getStringExtra(SCREEN_TYPE);


        btn_tcback = (Button) findViewById(R.id.btn_tcback);
        mBottomButtonContainer = findViewById(R.id.bottom_button_container);
        mWebView = findViewById(R.id.web_view_tac);
        mDeclineBtn = findViewById(R.id.decline_btn);
        mAcceptButton = findViewById(R.id.accept_btn);


        if (!SCREEN_TYPE_REGISTER.equalsIgnoreCase(mScreenType)) {
            mBottomButtonContainer.setVisibility(View.GONE);
        }

        mAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termsAndConditionCheck = true;
                finish();
            }
        });

        mDeclineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termsAndConditionCheck = false;
                finish();
            }
        });

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/privacy_policy.html");

	/*
		txt_tctcs.setText("Lorem ipsum dolor sit er elit lamet, consectetaur cillium " +
				"adipisicing pecu, sed do eiusmod tempor incididunt ut labore et dolore " +
				"magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
				"laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
				"reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
				" Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
				"deserunt mollit anim id est laborum. Nam liber te conscient to factor " +
				"tum poen legum odioque civiuda.");*/

        btn_tcback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                finish();
            }
        });


    }

}
