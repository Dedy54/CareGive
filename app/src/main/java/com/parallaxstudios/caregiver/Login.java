package com.parallaxstudios.caregiver;

import com.parallaxstudios.caregiver.utill.utill.CustomActivity;
import com.parallaxstudios.caregiver.utill.utill.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends CustomActivity
{
	private EditText user;
	private EditText pwd;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		setTouchNClick(R.id.btnLogin);
		setTouchNClick(R.id.btnReg);

		user = (EditText) findViewById(R.id.input_email);
		pwd = (EditText) findViewById(R.id.input_password);
	}
	
	@Override
	public void onClick(View v)
	{
		super.onClick(v);
		if (v.getId() == R.id.btnReg)
		{
			startActivityForResult(new Intent(this, Register.class), 10);
		}
		else
		{
			String u = user.getText().toString();
			String p = pwd.getText().toString();
			if (u.length() == 0 || p.length() == 0)
			{
				Utils.showDialog(this, R.string.err_fields_empty);
				return;
			}else if (u.equals("dedy") && p.equals("dedy")){
				startActivity(new Intent(Login.this, DrawerActivity.class));
				finish();
			}
			else {
				Utils.showDialog(
						Login.this,
						getString(R.string.err_login));
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 10 && resultCode == RESULT_OK)
			finish();
	}
}
