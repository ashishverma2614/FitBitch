package edu.mit.media.hlt.sensorgraph;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FoodData extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_data, menu);
		return true;
	}

}
