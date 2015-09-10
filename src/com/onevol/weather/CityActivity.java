package com.onevol.weather;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.onevol.weather.R;
import com.onevol.weather.adapter.CityListAdatper;
import com.thinkland.juheapi.common.JsonCallBack;
import com.thinkland.juheapi.data.weather.WeatherData;

public class CityActivity extends Activity {

	private ListView lv_city;
	private List<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city);
		initViews();
		getCities();

	}

	private void initViews() {
		findViewById(R.id.iv_back).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		lv_city = (ListView) findViewById(R.id.lv_city);
	}

	private void getCities() {
		WeatherData data = WeatherData.getInstance();
		data.getCities(new JsonCallBack() {

			@Override
			public void jsonLoaded(JSONObject json) {
				// TODO Auto-generated method stub
				try {
					int code = json.getInt("resultcode");
					int error_code = json.getInt("error_code");
					if (error_code == 0 && code == 200) {

						list = new ArrayList<String>();
						JSONArray resultArray = json.getJSONArray("result");
						Set<String> citySet = new HashSet<String>();
						for (int i = 0; i < resultArray.length(); i++) {
							String city = resultArray.getJSONObject(i).getString("city");
							citySet.add(city);
						}
						list.addAll(citySet);
						Collections.sort(list, new PinyinSimpleComparator());
						CityListAdatper adatper = new CityListAdatper(CityActivity.this, list);
						lv_city.setAdapter(adatper);
						lv_city.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								Intent intent = new Intent();
								intent.putExtra("city", list.get(arg2));
								setResult(1, intent);
								save(list.get(arg2));
								finish();
							}
						});

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
    public void save(String p1) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", 0x0);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(p1);
        } catch(IOException e) {
            e.printStackTrace();
        }finally{
                try {
                	if( writer != null){
                     writer.close();
                	}
                } catch(IOException localIOException1) {
                    localIOException1.printStackTrace();
                }
            }
        }

}
