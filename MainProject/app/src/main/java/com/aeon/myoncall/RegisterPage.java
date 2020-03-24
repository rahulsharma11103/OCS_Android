package com.aeon.myoncall;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RegisterPage extends BaseActivity implements OnClickListener {

    Typeface angelinaa;

    TextView txt_wcc, txt_oneee, txt_twooo;
    EditText edt_oncallidd, edt_fname, edt_lname, edt_email, edt_passone, edt_passtwo;
    Button btn_register;
    // dialog init
    private Dialog Alert_dialogg;
    TextView txt_msg, txt_errr;
    Button btn_okk;
    RelativeLayout main_splash;

    SharedPreferences sh_Pref;
    Editor toEdit;
    TextView mPrivacyPolicy;
    CheckBox mPrivacyCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setScreenName("Register Screen");
        //disableTrackReport();

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_act);

        initialize();
        angelinaa = Typeface.createFromAsset(this.getAssets(), "angelina.TTF");
        CallDialogue("1");
        /*
         * txt_wcc.setTypeface(angelinaa); txt_oneee.setTypeface(angelinaa);
         * txt_twooo.setTypeface(angelinaa);
         * edt_oncallidd.setTypeface(angelinaa);
         * edt_fname.setTypeface(angelinaa); edt_lname.setTypeface(angelinaa);
         * edt_email.setTypeface(angelinaa); edt_passone.setTypeface(angelinaa);
         * edt_passtwo.setTypeface(angelinaa);
         * btn_register.setTypeface(angelinaa);
         */

        sh_Pref = getSharedPreferences("register_details", MODE_PRIVATE);

    }

    private void initialize() {
        // TODO Auto-generated method stub
        txt_wcc = (TextView) findViewById(R.id.txt_wcc);
        txt_oneee = (TextView) findViewById(R.id.txt_oneee);
        txt_twooo = (TextView) findViewById(R.id.txt_twooo);
        edt_oncallidd = (EditText) findViewById(R.id.edt_oncallidd);
        edt_fname = (EditText) findViewById(R.id.edt_fname);
        edt_lname = (EditText) findViewById(R.id.edt_lname);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_passone = (EditText) findViewById(R.id.edt_passone);
        edt_passtwo = (EditText) findViewById(R.id.edt_passtwo);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        main_splash = (RelativeLayout) findViewById(R.id.main_splash);
        mPrivacyPolicy = findViewById(R.id.policy_text);
        mPrivacyCheck = findViewById(R.id.policy_check_box);
        mPrivacyPolicy.setOnClickListener(this);
        main_splash.getBackground().setAlpha(80);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int id = v.getId();
        if (id == R.id.btn_register) {
            String oncall_id = edt_oncallidd.getText().toString();
            String fname = edt_fname.getText().toString();
            String lname = edt_lname.getText().toString();
            String email = edt_email.getText().toString();
            String passone = edt_passone.getText().toString();
            String passtwo = edt_passtwo.getText().toString();
            if (!oncall_id.equalsIgnoreCase("") && !fname.equalsIgnoreCase("")
                    && !lname.equalsIgnoreCase("") && !email.equalsIgnoreCase("")
                    && !passone.equalsIgnoreCase("") && !passtwo.equalsIgnoreCase("")) {

                if (passone.equals(passtwo)) {

                    toEdit = sh_Pref.edit();
                    toEdit.putString("on_call_id", oncall_id);
                    toEdit.putString("first_name", fname);
                    toEdit.putString("last_name", lname);
                    toEdit.putString("password", passone);
                    toEdit.putString("email_id", email);
                    toEdit.putString("login_flag", "true");
                    toEdit.commit();

                    // on complete regstration ove to Tab actiity
                    Intent i_mainpage = new Intent(getApplicationContext(), TabPage.class);
                    startActivity(i_mainpage);
                    finish();
                } else {
                    CallDialogue("3");
                }

            } else {

                CallDialogue("2");
            }
        } else if (id == R.id.policy_text) {
            Intent intent = new Intent(this, TermsConditions.class);
            intent.putExtra(TermsConditions.SCREEN_TYPE, TermsConditions.SCREEN_TYPE_REGISTER);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPrivacyCheck.setChecked(TermsConditions.termsAndConditionCheck);
    }

    private void CallDialogue(String here) {

        Alert_dialogg = new Dialog(RegisterPage.this);
        Alert_dialogg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Alert_dialogg.setContentView(R.layout.dialog_error);
        Alert_dialogg.setCancelable(false);

        txt_msg = (TextView) Alert_dialogg.findViewById(R.id.shshs);
        btn_okk = (Button) Alert_dialogg.findViewById(R.id.btn_ok);
        txt_errr = (TextView) Alert_dialogg.findViewById(R.id.txt_errr);
        if (here.equalsIgnoreCase("2")) {
            txt_msg.setText("You must complete all fields");
            txt_errr.setText("Error");
        } else if (here.equalsIgnoreCase("1")) {
            txt_msg.setText("An email account is required for a \r\n majority of functions"
                    + " in this application.\r\n This can be set up through the settings\r\n of the device.");
            txt_errr.setText("Please Note");
        } else if (here.equalsIgnoreCase("3")) {
            txt_msg.setText("Your entered password do not match");
            txt_errr.setText("Error");
        }

        Alert_dialogg.show();
        btn_okk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Alert_dialogg.dismiss();

            }
        });
    }

}
