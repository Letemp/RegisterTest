package com.example.registertest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button registerBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//注册按钮的事件处理
		registerBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//验证用户输入信息的结果
				String checkResult=checkInfo();
				//如果结果不为空，则用对话框提示
				if(checkResult!=null){
					Builder builder=new AlertDialog.Builder(MainActivity.this);
					//设置对话框标题
					builder.setTitle("出错提示");
					//设置对话框内容信息
					builder.setMessage(checkResult);
					builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						
						//为对话框添加按钮及事件处理
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					
					}
				
			}
		

			private String checkInfo() {
				// TODO Auto-generated method stub
				return null;
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
