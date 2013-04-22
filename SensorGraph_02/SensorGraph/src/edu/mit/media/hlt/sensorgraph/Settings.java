package edu.mit.media.hlt.sensorgraph;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Settings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		//addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void respondToSendButton(View view) {
		Intent intent = new Intent(this, MainPage.class);
    	startActivity(intent);
    	
		Toast.makeText(Settings.this,
				"Data Sent", Toast.LENGTH_SHORT).show();
    	
	}
	
/*	public void addListenerOnButton() {
		
		final RadioGroup radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
		Button btnDisplay = (Button) findViewById(R.id.btnDisplay);
	 
		btnDisplay.setOnClickListener(new OnClickListener() {
	 
			@Override
			public void onClick(View v) {
	 
			        // get selected radio button from radioGroup
				int selectedId = radioSexGroup.getCheckedRadioButtonId();
	 
				// find the radiobutton by returned id
			        RadioButton radioSexButton = (RadioButton) findViewById(selectedId);
	 
				Toast.makeText(Settings.this,
					radioSexButton.getText(), Toast.LENGTH_SHORT).show();
	 
			}
		});
	}*/

}
