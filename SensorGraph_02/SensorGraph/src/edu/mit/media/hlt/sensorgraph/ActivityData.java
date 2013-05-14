package edu.mit.media.hlt.sensorgraph;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class ActivityData extends Activity {

	
	private ImageView imageview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		imageview = (ImageView)findViewById(R.id.imageView);
		getMenuInflater().inflate(R.menu.activity_data, menu);
		return true;
	}
	
	
	 /** Called when the user clicks the Activity Data Button */
   public void respondToDayButton (View view) {
   	imageview.setImageResource(R.drawable.activity_day_graph);
   }
   
   /** Called when the user clicks the Activity Data Button */
   public void respondToWeekButton (View view) {
   	imageview.setImageResource(R.drawable.activity_week_graph);
   }
   
	 /** Called when the user clicks the Activity Data Button */
   public void respondToMonthButton (View view) {
   	imageview.setImageResource(R.drawable.activity_month_graph);
   }

}
