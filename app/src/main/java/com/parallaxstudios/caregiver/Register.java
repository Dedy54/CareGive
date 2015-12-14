package com.parallaxstudios.caregiver;

import com.parallaxstudios.caregiver.utill.utill.CustomActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends CustomActivity
{
	private EditText user;
	private EditText pwd;
	private EditText repwd;
	private EditText email;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		setTouchNClick(R.id.btnReg);

		user = (EditText) findViewById(R.id.input_name);
		email = (EditText) findViewById(R.id.input_email);
		pwd = (EditText) findViewById(R.id.input_password);
		repwd = (EditText) findViewById(R.id.input_reepassword);
	}

	@Override
	public void onClick(View v)
	{
		super.onClick(v);

		String u = user.getText().toString();
		String p = pwd.getText().toString();
		String rp = repwd.getText().toString();
		String e = email.getText().toString();
		if (u.length() != 0 && p.length() != 0 && e.length() != 0 && rp.length() != 0 && p.equals(rp)&& p.length()>=6  )
		{
			final ProgressDialog dia = ProgressDialog.show(this, null,getString(R.string.alert_wait));
			dia.dismiss();
			showSuccess("Please verify your email, then click the login button");
		}else if(u.isEmpty() || e.isEmpty() || p.isEmpty()  || rp.isEmpty()){
            showIssue("Please makes sure you fill out all the fields properly!");
        }else if(!p.equals(rp)){
            showIssue("Oops your passwords do not match, please try again");
        }else if(p.length()<6){
            showIssue("Please make sure your password is at least six characters long");
        }
    }
		private void showSuccess(String issue)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(issue)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
		private void showIssue(String issue)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(issue)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }
		


