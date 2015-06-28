package com.example.tasbeeh;

import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.text.Editable;
import android.widget.EditText;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.view.KeyEvent;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)

public class MainActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";
	final Context context=this;
	TextView display,tasbih;
	Button Up,Down,Reset,Mytimer;
	int lastcount,counter;//counter
	CharSequence dialogInput;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//
		setContentView(R.layout.activity_main);
	   // public int lastcount,counter=lastcount;
		Up=(Button)findViewById(R.id.Upbutt);
		Down=(Button)findViewById(R.id.Dbutt);
		Reset=(Button)findViewById(R.id.Rebutt);
		display=(TextView)findViewById(R.id.count);
		Mytimer =(Button)findViewById(R.id.button1);
		tasbih=(TextView)findViewById(R.id.shumaar);
		Up.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); //touch on vibrate
				vb.vibrate(100);
				counter++;
			    display.setText(""+counter);
				
			}
		});
		Down.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Vibrator vb=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE); //touch on vibrate
				vb.vibrate(100);
				if(counter!=0)
				{
				 counter--;
				 display.setText(""+counter);
				}//if	
				else if(counter==0)
				{
				 Context context = getApplicationContext();
				 CharSequence text = "Cannot count below Zero!";
				 int duration = Toast.LENGTH_SHORT;
				 Toast toast =Toast.makeText(context, text, duration);
				 toast.show();
				}//elseif	
			}
		});
		Reset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); //touch on vibrate
				vb.vibrate(100);
				counter=0;
				 display.setText(""+counter);
				 tasbih.setText("Tasbeeh-e-Shumaar");
				 CharSequence text = "Refreshed!";
				 int duration= Toast.LENGTH_SHORT;
				 Toast toast = Toast.makeText(context, text, duration);
				 toast.show();
			}
		});
		Mytimer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			AlertDialog.Builder alert= new AlertDialog.Builder(context);
			alert.setTitle("Personalize Me..");
            final EditText input = new EditText(context);
			alert.setView(input);
			input.setHint("what do you wanna recite..");
			
			alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface Dialog, int WhichButton) {
					// TODO Auto-generated method stub
					Editable value=input.getText();
					tasbih.setText(""+value);
				}
			});
			
			alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					
				}
			});
			alert.show();
			}
		});
 		
		
		
	}//OnCreate

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch(keyCode)
		{
		case KeyEvent.KEYCODE_VOLUME_UP:
			event.startTracking();
			return true;
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			event.startTracking();
			return true;
		}//switch
		return super.onKeyDown(keyCode, event);
		
	}//OnKeyDown
	

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		switch(keyCode)
		{
		case KeyEvent.KEYCODE_VOLUME_UP:
			if(event.isTracking()&& !event.isCanceled())
				vibratorService();
				counter++;
			    display.setText(""+counter);
			    return true;
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			if(counter!=0)
			{
			 vibratorService();
		     counter--;
			 display.setText(""+counter);
			}//if
			toastService();
			display.setText(""+counter);
			return true;
		case KeyEvent.KEYCODE_BACK:
			 super.finish();
			 
		}//switch
		return super.onKeyUp(keyCode, event);
	}//OnKeyUp

	@Override 
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}//OnCreateOptionsMenu

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
//		case R.id.saveid:
//			 savefunc();
//			 break;
//		case R.id.openid:
//			 openfunc();
//			 break;
		case R.id.about:
        	MyIntent();
			//setContentView(R.layout.aboutpage);
			break;
		default:
			break; 
		}//switch
		return true;
	}//onOptionsItemSelected

	public void MyIntent()
	{
		 Intent IntentAboutus = new Intent("com.example.tasbeeh.ABOUTUS");
		 startActivity(IntentAboutus);
	}//MyIntent
	
	public void savefunc()
	{
	 SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	 SharedPreferences.Editor editor= settings.edit();
	 //int your_home_score=0;
	 editor.putInt("homescore", counter);
	 editor.apply();
	 
	 SharedPreferences tempdata = getSharedPreferences(PREFS_NAME,0);
	 SharedPreferences.Editor editor1= tempdata.edit();
	 editor1.putInt("homescore1", 0);
	 editor.commit();
	}//savefunc
	
	public void openfunc()
	{
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		int homescore = settings.getInt("homescore", 0);
		display.setText(""+homescore);
		dialogInput=tasbih.getText();
		tasbih.setText(""+dialogInput);
		
	    SharedPreferences tempdata = getSharedPreferences(PREFS_NAME,0);
	    int homescore1 = tempdata.getInt("homescore1", 0);
	    display.setText(""+homescore1);
	    dialogInput = tasbih.getText();
	    tasbih.setText(""+dialogInput);
	}//openfunc
	
	public void vibratorService()
	{
		Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); //touch on vibrate
		vb.vibrate(100);	 
	}//vibratorService
	
	public void toastService()
	{
	    
	    CharSequence text="Cannot count below zero!";
	    int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
	    toast.show();
	}//toastService
}//MainActivity
