package com.example.listviewtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

//	private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango", "blue", "black", "red", "yellow", "white", "green", "orange", "pink", "Fuck", "you", "!"};
	private List<Fruit> fruitList = new ArrayList<Fruit>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initFruits();
		FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
		ListView listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(adapter);
	}
	
	private void initFruits() {
		Fruit apple = new Fruit("Apple", R.drawable.apple);
		fruitList.add(apple);
		Fruit banana = new Fruit("Banana", R.drawable.banana);
		fruitList.add(banana);
		Fruit orange = new Fruit("Orange", R.drawable.orange);
		fruitList.add(orange);
		Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon);
		fruitList.add(watermelon);
		Fruit pear = new Fruit("Pear", R.drawable.pear);
		fruitList.add(pear);
		Fruit grape = new Fruit("Grape", R.drawable.grape);
		fruitList.add(grape);
		Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple);
		fruitList.add(pineapple);
		Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry);
		fruitList.add(strawberry);
		Fruit cherry = new Fruit("Cherry", R.drawable.cherry);
		fruitList.add(cherry);
		Fruit mango = new Fruit("mango", R.drawable.mango);
		fruitList.add(mango);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
