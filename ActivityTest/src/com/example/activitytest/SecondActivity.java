package com.example.activitytest;

import java.security.PublicKey;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class SecondActivity extends BaseActivity {

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		Log.d("SecondActivity", "Task id is " + getTaskId());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second_layout);
		
		// 通过Intent传递数据
/*		Intent intent = getIntent();
		String data = intent.getStringExtra("extra_data");
		Log.d("SecondActivity", data);
		*/
		
		Button button2 = (Button) findViewById(R.id.button_2);
		button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// 返回数据给上一个活动(P52)
				/*Intent intent = new Intent();
				intent.putExtra("data_return", "Hello FirstActivity");
				setResult(RESULT_OK, intent);
				finish();*/
				
				// 测试singTop模式(P71)
				/*Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
				startActivity(intent);*/
				
				// 测试singleInstance模式(P75)
				Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public static void actionStart(Context context, String data1, String data2){
		Intent intent = new Intent(context, SecondActivity.class);
		intent.putExtra("param1", data1);
		intent.putExtra("param2", data2);
		context.startActivity(intent);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("SecondActivity", "onDestroy");
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra("data_return" , "Hello FirstActivity");
		setResult(RESULT_OK, intent);
		finish();
	}
	
}
