package com.example.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private MyDatabaseHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
		Button createDatabase = (Button) findViewById(R.id.create_database);
		createDatabase.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dbHelper.getWritableDatabase();
			}
		});
		
		/* �������  --- P248 */
		Button addData = (Button) findViewById(R.id.add_data);
		addData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				// ��ʼ��װ��һ������
				values.put("name", "The Da Vinci Code");
				values.put("author", "Dan Brown");
				values.put("pages", 454);
				values.put("price", 16.96);
				// �����һ������
				db.insert("Book", null, values);
				values.clear();
				// ��ʼ��װ�ڶ�������
				values.put("name", "The Lost Symbol");
				values.put("author", "Dan Brown");
				values.put("pages", "510");
				values.put("price", 19.95);
				// ����ڶ�������
				db.insert("Book", null, values);
			}
		});
		
		/* ��������  --- P251 */
		
		Button updateData = (Button) findViewById(R.id.update_data);
		updateData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("price", 10.99);
				db.update("Book", values, "name = ?", new String[] {"The Da Vinci Code"});
			}
		});
		
		/* ɾ������ --- P254 */
		Button deleteButton = (Button) findViewById(R.id.delete_data);
		deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.delete("Book", "pages > ?", new String[] {"500"});
			}
		});
		
		/* ��ѯ���� --- P256 */
		Button queryButton = (Button) findViewById(R.id.query_data);
		queryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				// ��ѯBook�������е�����
				Cursor cursor = db.query("Book", null, null, null, null, null, null);
				if(cursor.moveToFirst()){
					do{
						String name = cursor.getString(cursor.getColumnIndex("name"));
						String author = cursor.getString(cursor.getColumnIndex("author"));
						int pages = cursor.getInt(cursor.getColumnIndex("pages"));
						double price = cursor.getDouble(cursor.getColumnIndex("price"));
						Log.d("MainActivity", "book name is " + name);
						Log.d("MainActivity", "book author is " + author);
						Log.d("MainActivity", "book pages is " + pages);
						Log.d("MainActivity", "book price is " + price);
					}while(cursor.moveToNext());
				}
				cursor.close();
			}
		});
		
		/* ʹ������ --- P260 */
		Button replaceData = (Button) findViewById(R.id.replace_data);
		replaceData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				// ��������
				db.beginTransaction();
				try {
					db.delete("Book", null, null);
					/*if(true){
						// �������ֶ��׳�һ���쳣,������ʧ��
						throw new NullPointerException();
					}*/
					ContentValues values = new ContentValues();
					values.put("name", "Game of Thrones");
					values.put("author", "George Martin");
					values.put("pages", "720");
					values.put("price", 20.85);
					db.insert("Book", null, values);
					// �����Ѿ�ִ�гɹ�
					db.setTransactionSuccessful();
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					// ��������
					db.endTransaction();
				}
			}
		});
		
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
