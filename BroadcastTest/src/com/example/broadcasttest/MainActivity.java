package com.example.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity 
{

	private IntentFilter intentFilter;
	
//	private NetworkChangeReceiver networkChangeReceiver;
	
	private LocalReceiver localReceiver;
	
	private LocalBroadcastManager localBroadcastManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
/*		// 动态注册监听网络变化
		intentFilter = new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		networkChangeReceiver = new NetworkChangeReceiver();
		registerReceiver(networkChangeReceiver, intentFilter);*/
		
		// 获取实例
		localBroadcastManager = LocalBroadcastManager.getInstance(this);
		
		// 发送自定义标准广播
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
				// 发送本地广播
				localBroadcastManager.sendBroadcast(intent);
				
/*				// 发送标准广播
//				sendBroadcast(intent);
				// 发送有序广播
				sendOrderedBroadcast(intent, null);*/
			}
		});
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
		localReceiver = new LocalReceiver();
		// 注册本地广播监听器
		localBroadcastManager.registerReceiver(localReceiver, intentFilter);
	}
	
	class LocalReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		
		// 取消注册
//		unregisterReceiver(networkChangeReceiver);
		unregisterReceiver(localReceiver);
	}
	
	class NetworkChangeReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			
			if(networkInfo != null && networkInfo.isAvailable())
			{
				Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();				
			}
			else
			{
				Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();		
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
