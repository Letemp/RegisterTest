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
		
		//ע��
		btnRegister=(Button)findViewById(R.id.btnRegister);
		btnCity=(Button)findViewById(R.id.btnCity);
		etPassword=(EditText)findViewById(R.id.etPassword);
		etPassword2=(EditText)findViewById(R.id.etPassword2);
		etName=(EditText)findViewById(R.id.etName);
		etCity=(EditText)findViewById(R.id.etCity);
		rgSex=(RadioGroup)findViewById(R.id.rgSex);
		
		//"ע��"��ť���¼�����
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//��֤�û�������Ϣ�Ľ��
				String checkResult=checkInfo();

				//��������Ϊ�գ����öԻ�����ʾ
				if(checkResult!=null) {
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
							
							//�����������Ϊ��
							etPassword.setText("");
							etPassword2.setText("");
							
						}
					});
					//�����Ի�����ʾ
					builder.create().show();
				} else {
					//ע����Ϣ����Ҫ�󣬽����ݷ���Intent�����д���
					Intent intent=new Intent(MainActivity.this,ResultActivity.class); 
					intent.putExtra("name", etName.getText().toString());
					intent.putExtra("psd", etPassword.getText().toString());
					intent.putExtra("gender",getGender());
					intent.putExtra("city", etCity.getText().toString());
					//����һ���µ�Activity
					startActivity(intent);
				}
				

			}
			

			private String checkInfo() {
				// TODO Auto-generated method stub
				System.out.println(etName);
				//���û���������֤
				if(etName.getText().toString()==null||etName.getText().toString().equals("")){
						return "�û�������Ϊ��";
					}
				//�����������֤
				if(etName.getText().toString().trim().length()<6||etPassword.getText().toString().trim().length()>15){
					    return "����λ��Ӧ����6����15֮��";
					}
				//��ȷ�����������֤
				if(!etPassword.getText().toString().equals(etPassword2.getText().toString())){
						return "�����������벻һ��";
					}
				return null;
				
			}
				
		});
		
		//"���ڵ�"��ť���¼�����,��Ҫ��������ȡ���е�Activity
		btnCity.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//������Ҫ������Activity��Intent
				Intent intent =new Intent(MainActivity.this,ChooseCityActivity.class);
				//����ָ��Activity���ȴ����صĽ��������0�������룬���ڱ�ʶ������
				//������Ϊ0��ǰ��Ҫһ��
				startActivityForResult(intent,0);
			}
			
		});
		
	}
	
	private String getGender() {
		int id = rgSex.getCheckedRadioButtonId();
		if (id == R.id.rbMale) {
			return "��";
		}else {
			return "Ů";
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
		//��requestCode��resultCodeͬʱΪ0��Ҳ���Ǵ����ض���Ч��
		if(requestCode==0&resultCode==0){
			Bundle bundle = data.getExtras();//ȡ��intent���Extras����
			String resultCity = bundle.getString("city");//ȡ��Bundle�е�����
			etCity.setText(resultCity);//�޸�city�ı��������
		}
	}

}
