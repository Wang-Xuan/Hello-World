package com.example.broadcastbestpractice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;


           // 用于管理所有活动
public class ActivityCollector {
	
	public static List<Activity> activities = new ArrayList<Activity>();
	
	public static void addActivity(Activity activity){
		activities.add(activity);
	}


	public static void removaActivity(Activity activity){
		activities.remove(activity);
	}
	
	public static void finishAll(){
		for(Activity activity: activities){
			if(!activity.isFinishing()){
				activity.finish();
			}
		}
	}

}