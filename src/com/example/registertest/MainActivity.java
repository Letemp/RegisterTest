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
		
		//ע�ᰴť���¼�����
		registerBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��֤�û�������Ϣ�Ľ��
				String checkResult=checkInfo();
				//��������Ϊ�գ����öԻ�����ʾ
				if(checkResult!=null){
					Builder builder=new AlertDialog.Builder(MainActivity.this);
					//���öԻ������
					builder.setTitle("������ʾ");
					//���öԻ���������Ϣ
					builder.setMessage(checkResult);
					builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
						
						//Ϊ�Ի�����Ӱ�ť���¼�����
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
