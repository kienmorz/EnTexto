package com.example.lucifer.entexto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Key;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import com.example.lucifer.entexto.TrippleDe;

public class WriteMsg extends AppCompatActivity {
    Button button,button1;
    EditText editText,editText2;
    String Seckey1="90812ij895qw1239p7183529";
    String Seckey2="908178pk546sa39pa718352a";
    String Seckey3="097123478956123losd8354p";
    String encdtext1 = null;
    String encdtext2 = null;
    String encdtext3 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_new);
        button=(Button)findViewById(R.id.send_btn);
        editText=(EditText)findViewById(R.id.address);
        editText2=(EditText)findViewById(R.id.message);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=editText.getText().toString();
                String text=editText2.getText().toString();
                String SecKey="9874563210987456";
                if(number.length()==10 && SecKey.length()>0 && text.length()>0 && SecKey.length()==16 ){
                    try {
                        encdtext1 = TrippleDe._encrypt(text, Seckey1).toString();
                        encdtext2 = TrippleDe._encrypt(encdtext1, Seckey2).toString();
                        encdtext3 = TrippleDe._encrypt(encdtext2, Seckey3).toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    byte[] encryptedMsg= encryptSMS(SecKey,encdtext3);
                    String msgString = byte2hex(encryptedMsg);
                    sendSMS(number,msgString);
                    finish();
                }else{
                    Toast.makeText(
                            getBaseContext(),
                            "Please enter proper phone number and enter the text properly",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    public static String encryption(String ency){
        String Seckey1="90812ij895qw1239p7183529";
        String Seckey2="908178pk546sa39pa718352a";
        String Seckey3="097123478956123losd8354p";
        String encdtext1 = null;
        String encdtext2 = null;
        String encdtext3 = null;
        String SecKey="9874563210987456";
        if(SecKey.length()>0 && ency.length()>0 && SecKey.length()==16 ){
            try {
                encdtext1 = TrippleDe._encrypt(ency, Seckey1).toString();
                encdtext2 = TrippleDe._encrypt(encdtext1, Seckey2).toString();
                encdtext3 = TrippleDe._encrypt(encdtext2, Seckey3).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] encryptedMsg= encryptSMS(SecKey,encdtext3);
            String encyrpted_text = byte2hex(encryptedMsg);
            return encyrpted_text;
        }else{

            return null;
        }

    }
    public static void sendSMS(String recNumString, String encryptedMsg) {
        try {
            // get a SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            // Message may exceed 160 characters
            // need to divide the message into multiples
            ArrayList<String> parts = smsManager.divideMessage(encryptedMsg);
            smsManager.sendMultipartTextMessage(recNumString, null, parts,
                    null, null);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // utility function
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs += ("0" + stmp);
            else
                hs += stmp;
        }

        return hs.toUpperCase();
    }

    public static byte[] encryptSMS(String secretKeyString,
                                    String msgContentString) {
        try {
            byte[] returnArray;
            // generate AES secret key from user input
            Key key = generateKey(secretKeyString);
            // specify the cipher algorithm using AES
            Cipher c = Cipher.getInstance("AES");
            // specify the encryption mode
            c.init(Cipher.ENCRYPT_MODE, key);
            // encrypt
            returnArray = c.doFinal(msgContentString.getBytes());
            return returnArray;
        } catch (Exception e) {
            e.printStackTrace();
            byte[] returnArray = null;
            return returnArray;
        }
    }

    private static Key generateKey(String secretKeyString) throws Exception {
        // generate secret key from string
        Key key = new SecretKeySpec(secretKeyString.getBytes(), "AES");
        return key;
    }
}
