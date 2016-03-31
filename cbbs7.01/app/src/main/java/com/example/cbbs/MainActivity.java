package com.example.cbbs;



import com.example.cbbs.control.MyImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends Activity {
	MyImageView instand,history,setter,gps;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instand = (MyImageView) findViewById(R.id.c_instand);
        history = (MyImageView) findViewById(R.id.c_history);
        setter =(MyImageView) findViewById(R.id.c_setter);
        gps=(MyImageView) this.findViewById(R.id.c_gps);
        
        instand.setOnClickIntent(new MyImageView.OnViewClickListener() {
			
			@Override
			public void onViewClick(MyImageView view) {
				// TODO Auto-generated method stub
				//Toast.makeText(MainActivity.this, "即时信息", 1000).show();
				Intent intent=new Intent(MainActivity.this,InstantActivity.class);
				startActivity(intent);
			}
		
        
        });
        
        history.setOnClickIntent(new MyImageView.OnViewClickListener() {
			
			@Override
			public void onViewClick(MyImageView view) {
				// TODO Auto-generated method stub
				//Toast.makeText(MainActivity.this, "历史信息", 1000).show();
				Intent intent=new Intent(MainActivity.this,HistoryActivity.class);
				startActivity(intent);
			}
		});
        
        setter.setOnClickIntent(new MyImageView.OnViewClickListener() {
			
			@Override
			public void onViewClick(MyImageView view) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "设置", 1000).show();
			}
		});
        
        gps.setOnClickIntent(new MyImageView.OnViewClickListener() {
			
			@Override
			public void onViewClick(MyImageView view) {
				// TODO Auto-generated method stub
				//Toast.makeText(MainActivity.this, "定位", 1000).show();
				Intent intent=new Intent(MainActivity.this,GpsActivity.class);
				startActivity(intent);
			}
		});
    }
    
    
}
