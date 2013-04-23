package edu.mit.media.hlt.sensorgraph;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class FoodData extends Activity {

	private ImageView imageview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imageview = (ImageView)findViewById(R.id.imageView);
		setContentView(R.layout.activity_food_data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_data, menu);
		return true;
	}
	
	 /** Called when the user clicks the Activity Data Button */
    public void respondToDayButton () {
    	imageview.setImageResource(R.drawable.activity_day_graph);
    }
    
    /** Called when the user clicks the Activity Data Button */
    public void respondToWeekButton () {
    	imageview.setImageResource(R.drawable.activity_week_graph);
    }
    
	 /** Called when the user clicks the Activity Data Button */
    public void respondToMonthButton () {
    	imageview.setImageResource(R.drawable.activity_month_graph);
    }

}
