package com.example.FLogin;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FLoginActivity extends Activity {
	
	Button   mButton;
	EditText mEdit;
	private TextView OTPdisplay;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        TextView txt = (TextView) findViewById(R.id.textView3);
        Typeface font = Typeface.createFromAsset(getAssets(), "Flux_Architect_Regular.ttf");
        txt.setTypeface(font);
        
        mButton = (Button)findViewById(R.id.button1);
        mEdit   = (EditText)findViewById(R.id.editText1);
        OTPdisplay=(TextView)findViewById(R.id.textView3);

        mButton.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                	OTPdisplay.setText( generate_OTP( mEdit.getText().toString() )  );
                }
            });
        
        
    }
    
    /* Used variables */
    int H,M,S;
    int tH,tM,tS; 
    int xH,xM,xS;
    int bH,bM,bS;
    int control;
    
    /* calculate hash using MD5 */
    private String md5(String in) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }
    
    /* calculate hash using SHA1 */
    public static String sha1(String data)
    {
        try
        {
            byte[] b = data.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.reset();
            md.update(b);
            byte messageDigest[] = md.digest();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < messageDigest.length; i++)
            {
                result.append(Integer.toString((messageDigest[i] & 0xff) + 0x100, 16).substring(1));
            }

            return result.toString();

        } catch (NoSuchAlgorithmException e)
        {

          //  Log.e("ARTags", "SHA1 is not a supported algorithm");
        }
        return null;
    }
    
    
    /* which hash should i use? */
    String hash(int x, String in) {
    	
    	if ( x == 0 )
    		return md5(in);
    	else
    		return sha1(in);
    	
    }
    
    
    int multiply, start, algorithm;
    
    /* Compute the OTP */
    public String generate_OTP(String ceva){
    	
    	String result;	

    	/* secrets in token */
    	multiply = Integer.parseInt( ceva.substring(3, 4) ) ;
    	start    = Integer.parseInt( ceva.substring(4, 5) ) ;
    	algorithm= Integer.parseInt( ceva.substring(5, 6) ) ;
 
    	result=ceva.toLowerCase();
    	
    	/* chain hashing */
    	for ( int i=0; i<multiply; i++ )
    		result = hash(algorithm%2,result);
    	
    	return result.substring(start*2, start*2+6).toUpperCase();
    }
    
}