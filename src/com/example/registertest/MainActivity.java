package com.example.registertest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity {
	
	private Button btnRegister,btnCity;
	private EditText etPassword,etPassword2,etName,etCity;
	private RadioGroup rgSex;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//注册
		btnRegister=(Button)findViewById(R.id.btnRegister);
		btnCity=(Button)findViewById(R.id.btnCity);
		etPassword=(EditText)findViewById(R.id.etPassword);
		etPassword2=(EditText)findViewById(R.id.etPassword2);
		etName=(EditText)findViewById(R.id.etName);
		etCity=(EditText)findViewById(R.id.etCity);
		rgSex=(RadioGroup)findViewById(R.id.rgSex);
		
		//"注册"按钮的事件处理
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//验证用户输入信息的结果
				String checkResult=checkInfo();

				//如果结果不为空，则用对话框提示
				if(checkResult!=null) {
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
							
							//将密码框设置为空
							etPassword.setText("");
							etPassword2.setText("");
							
						}
					});
					//创建对话框并显示
					builder.create().show();
				} else {
					//注册信息符合要求，将数据放入Intent，进行传递
					Intent intent=new Intent(MainActivity.this,ResultActivity.class); 
					intent.putExtra("name", etName.getText().toString());
					intent.putExtra("psd", etPassword.getText().toString());
					intent.putExtra("gender",getGender());
					intent.putExtra("city", etCity.getText().toString());
					//启动一个新的Activity
					startActivity(intent);
				}
				

			}
			

			private String checkInfo() {
				// TODO Auto-generated method stub
				System.out.println(etName);
				//对用户名进行验证
				if(etName.getText().toString()==null||etName.getText().toString().equals("")){
						return "用户名不能为空";
					}
				//对密码进行验证
				if(etName.getText().toString().trim().length()<6||etPassword.getText().toString().trim().length()>15){
					    return "密码位数应该在6——15之间";
					}
				//对确认密码进行验证
				if(!etPassword.getText().toString().equals(etPassword2.getText().toString())){
						return "两次输入密码不一致";
					}
				return null;
				
			}
				
		});
		
		//"所在地"按钮的事件处理,主要是启动获取城市的Activity
		btnCity.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//创建需要启动的Activity的Intent
				Intent intent =new Intent(MainActivity.this,ChooseCityActivity.class);
				//启动指定Activity并等待返回的结果，其中0是请求码，用于标识该请求
				//请求码为0，前后要一致
				startActivityForResult(intent,0);
			}
			
		});
		
	}
	
	private String getGender() {
		int id = rgSex.getCheckedRadioButtonId();
		if (id == R.id.rbMale) {
			return "男";
		}else {
			return "女";
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//当requestCode，resultCode同时为0，也就是处理特定的效果
		if(requestCode==0&resultCode==0){
			Bundle bundle = data.getExtras();//取出intent里的Extras数据
			String resultCity = bundle.getString("city");//取出Bundle中的数据
			etCity.setText(resultCity);//修改city文本框的内容
		}
	}

}
