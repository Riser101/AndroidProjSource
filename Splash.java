package com.example.tasbeeh;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//
        setContentView(R.layout.splash);
        Thread timer=new Thread(){
        	public void run()
        	{
        		try
        		{
        		 sleep(3000);	
        		}//try
        		catch(InterruptedException e)
        		{
        		 e.printStackTrace();	
        		}//catch
                /*catch(ActivityNotFoundException ea)
                {
                	ea.printStackTrace();
                	
                }*/
        		/*catch(Exception ex)
        		{
        			ex.printStackTrace();
        		}*/
       
        		finally
        		{
        		 Intent MainAct = new Intent("com.example.tasbeeh.MAINACTIVITY");
        		 startActivity(MainAct);
        		}//finally
        	}//run
        };//thread
        timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
