package edu.mit.media.hlt.sensorgraph;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import edu.mit.media.hlt.sensorgraph.SensorGraph.ArduinoReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import at.abraxas.amarino.Amarino;
import at.abraxas.amarino.AmarinoIntent;

public class MainPage extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	int countFood = 0;
	int countActivity = 0;
	public static int pedoStep = 0;
	private static long date = 0;
	
	public static String getDate() {
		String strDateFormat = "HH:mm:ss a";
	    SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		return sdf.format(new Date(date));
	}
	
	public static int getPedo() {
		/*String FILENAME = "hello_file";
    	FileInputStream fis;
    	byte[] bs = new byte[4];
    	try {
			fis = openFileInput(FILENAME);
			int i = fis.read(bs);
			
			System.out.println("Number of bytes read: "+i);
	        
	        ByteBuffer wrapped = ByteBuffer.wrap(bs);
	        wrapped.order(ByteOrder.LITTLE_ENDIAN);
	        i = wrapped.getInt();
	        pedoStep += i;
			fis.close();
			
			File dir = getFilesDir();
			File file = new File(dir, FILENAME);
			file.delete();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	System.out.println("refrash" + pedoStep);*/
		return pedoStep;
	}
	
	public void updateTextView(String toThis) {

        TextView textView = (TextView) findViewById(R.id.pedo_steps);;
        textView.setText(toThis);

        return;
        
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		//super.onCreate(savedInstanceState);
        
        /*setContentView(R.layout.main);
        
        // get handles to Views defined in our layout file
       /* mGraph = (GraphView)findViewById(R.id.graph);
        mValueTV = (TextView) findViewById(R.id.value);
        
        mGraph.setMaxValue(1024);
		
		String myParam = "";
		Bundle extras = getIntent().getExtras();
	    if (extras != null)
	    {
	        myParam = extras.getString("toOpen");
	    }
	    */
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		/*if(myParam.equals("0"))
			mViewPager.setCurrentItem(0);
		else if(myParam.equals("2"))
			mViewPager.setCurrentItem(2);
		else*/
			mViewPager.setCurrentItem(1);

		
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main_page, menu);
		menu.add(1,1,menu.FIRST, "Generate Report").setIcon(R.drawable.ic_menu_send);;
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case 1:
			Intent intent = new Intent(this, Settings.class);
	    	startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/** Called when the user clicks the Send button */
    public void respondToMainActivityButton(View view) {
        // Do something in response to button
    	/*Intent intent = new Intent(this, SensorGraph.class);
    	startActivity(intent);*/
    	
    	
    	countActivity++;
    	if((countActivity / 5) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.activity_0);
    	}
    	else if((countActivity / 5 + 2) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.activity_1);
    	}
    	else if((countActivity / 5 + 1) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.activity_2);
    	}
    	
    }
	
    /** Called when the user clicks the Send button */
    public void respondToMainFoodButton(View view) {
        // Do something in response to button
    	/*Intent intent = new Intent(this, SensorGraph2.class);
    	startActivity(intent);*/
    	countFood++;
    	if((countFood / 5) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.food_0);
    	}
    	else if((countFood / 5 + 2) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.food_1);
    	}
    	else if((countFood / 5 + 1) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.food_2);
    	}
    	
    }
	
	
	/** Called when the user clicks the Send button */
    public void respondToFitnessButton(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, SensorGraph.class);
    	startActivity(intent);
    }
    
    /** Called when the user clicks the Send button */
    public void respondToFoodButton(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, SensorGraph2.class);
    	startActivity(intent);
    }
    
    /** Called when the user clicks the Send button */
    public void respondToActivityImageButton(View view) {
        // Do something in response to button

    	if((countActivity / 5) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.activity_page_0);
    	}
    	else if((countActivity / 5 + 2) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.activity_page_1);
    	}
    	else if((countActivity / 5 + 1) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.activity_page_2);
    	}
    }
    
    /** Called when the user clicks the Send button */
    public void respondToFoodImageButton(View view) {
        // Do something in response to button

    	if((countFood / 5) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.food_page_0);
    	}
    	else if((countFood / 5 + 2) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.food_page_1);
    	}
    	else if((countFood / 5 + 1) % 3 == 0 ) {
	    	ImageButton i = (ImageButton) view;
	        i.setImageResource(R.drawable.food_page_2);
    	}
    }
    
    public void respondToRefreshPedoButton(View view) {
    	// Do something in response to button
    	
    	String FILENAME = "hello_file";
    	FileInputStream fis;
    	byte[] bs = new byte[4];
    	try {
			fis = openFileInput(FILENAME);
			int i = fis.read(bs);
			
			System.out.println("Number of bytes read: "+i);
	        
	        ByteBuffer wrapped = ByteBuffer.wrap(bs);
	        wrapped.order(ByteOrder.LITTLE_ENDIAN);
	        i = wrapped.getInt();
	        pedoStep = i;
			fis.close();
			
			File dir = getFilesDir();
			File file = new File(dir, FILENAME);
			file.delete();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	    	
    }
    
    public void respondToRefreshLastFedButton(View view) {
    	// Do something in response to button
    	/*String FILENAME = "hello_file2";
    	FileInputStream fis;
    	DataInputStream dis;
    	byte[] bs = new byte[8];
    	System.out.println("poopies");
    	try {
			fis = openFileInput(FILENAME);
			dis = new DataInputStream(fis);
			date = dis.readLong();*/
    	//this was commented twice
			/*
			//long l = fis.read(bs);
			int a = fis.read(bs);
			//int b = fis.read(bs);
			System.out.println("a: " + a);
			
			//long l = (long)a << 32 | b & 0xFFFFFFFFL;
			System.out.println("Number of bytes read: "+a);
			
	        ByteBuffer wrapped = ByteBuffer.wrap(bs);
	        //wrapped.order(ByteOrder.LITTLE_ENDIAN);
	        date = wrapped.getLong();*/
	        //end of commenting twice
    	/*
	        System.out.println("hi " + date);
	        dis.close();
			fis.close();
			
			File dir = getFilesDir();
			File file = new File(dir, FILENAME);
			file.delete();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
    	    	
    }
    
    
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			/*Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;*/
			
			Fragment f = null;
		    switch(position){
		    case 0:
		    {
			    f = new DummySectionFragment();//YourFragment
			    // set arguments here, if required
			    Bundle args = new Bundle();
			    f.setArguments(args);
			    break;
		    }
		    case 1:
		    {
		        f = new HomeSectionFragment();//YourFragment
		        // set arguments here, if required
		        Bundle args = new Bundle();
		        f.setArguments(args);
		        break;
		    }
		    case 2:
		    {   
		        f = new FoodSectionFragment();//YourFragment
		        // set arguments here, if required
		        Bundle args = new Bundle();
		        f.setArguments(args);
		        break;
		    }   
		    default:
		      throw new IllegalArgumentException("not this many fragments: " + position);
		    }


		    return f;
		    
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		
		private Runnable mTicker;
	    private Handler mHandler;
	    TextView mStep;
	    
	    //private static int pedoStep = 0;
		
	    private boolean mClockStopped = false;

		
		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_page_dummy,
					container, false);
			/*TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.pedo_steps);*/
			
	        mStep = (TextView) rootView.findViewById(R.id.pedo_steps);
	        
	        
			mHandler = new Handler();

	        mTicker = new Runnable() {
	            public void run() {
	                if(mClockStopped) return;
	                //mCalendar.setTimeInMillis(System.currentTimeMillis());
	                mStep.setText(Integer.toString(getPedo()));
	                mStep.invalidate();
	                //mHandler.postAtTime(mTicker, next);
	    			mStep.setText(Integer.toString(getPedo()));
	    			
	    			//System.out.println("Steps: " + pedoStep);
	    			mHandler.postDelayed(this, 1000);
	            }
	        };
	        
	        mHandler.postDelayed(mTicker, 1000);
	        //mTicker.run();
			
			
			return rootView;
		}
		
		@Override
	    public void onResume()
	    {
	        super.onResume();
	        mClockStopped = false;
	    }

	    @Override
	    public void onPause()
	    {
	        mClockStopped = false;        
	        super.onPause();
	    }
		
		
		
	}
	
	public static class HomeSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public HomeSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_page_home,
					container, false);
			/*TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));*/
			return rootView;
		}
		
	}
	
	public static class FoodSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		private Runnable mTicker;
	    private Handler mHandler;
	    TextView mStep;
		
	    private boolean mClockStopped = false;
	    
		public FoodSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_page_food,
					container, false);
			
			mStep = (TextView) rootView.findViewById(R.id.bowl_weight);
	        
	        
			mHandler = new Handler();

	        mTicker = new Runnable() {
	            public void run() {
	                if(mClockStopped) return;
	                //mCalendar.setTimeInMillis(System.currentTimeMillis());
	                mStep.setText(getDate());
	                mStep.invalidate();
	                //mHandler.postAtTime(mTicker, next);
	    			mStep.setText(getDate());
	    			
	    			//System.out.println("Date: " + getDate());
	    			mHandler.postDelayed(this, 1000);
	            }
	        };
	        
	        mHandler.postDelayed(mTicker, 1000);
	        //mTicker.run();
			
			/*dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));*/
			return rootView;
		}
		
	
	}
	
	private static final String TAG = "SensorGraph";
	
	// change this to your Bluetooth device address 
	private static final String DEVICE_ADDRESS =  "20:13:01:23:00:55"; //"00:06:66:03:73:7B";
	
	private GraphView mGraph; 
	private TextView mValueTV;
	
	private ArduinoReceiver arduinoReceiver = new ArduinoReceiver();

    
    /** Called when the user clicks the Send button */
    public void respondToButton(View view) {
        // Do something in response to button
    	
    	/*Intent intent = new Intent(this, MainPage.class);
   		intent.putExtra("toOpen", "0");
    	startActivity(intent);*/
    }
    
	@Override
	protected void onStart() {
		super.onStart();
		// in order to receive broadcasted intents we need to register our receiver
		registerReceiver(arduinoReceiver, new IntentFilter(AmarinoIntent.ACTION_RECEIVED));
		
		// this is how you tell Amarino to connect to a specific BT device from within your own code
		Amarino.connect(this, DEVICE_ADDRESS);
	}


	@Override
	protected void onStop() {
		super.onStop();
		
		// if you connect in onStart() you must not forget to disconnect when your app is closed
		Amarino.disconnect(this, DEVICE_ADDRESS);
		
		// do never forget to unregister a registered receiver
		unregisterReceiver(arduinoReceiver);
	}
	

	/**
	 * ArduinoReceiver is responsible for catching broadcasted Amarino
	 * events.
	 * 
	 * It extracts data from the intent and updates the graph accordingly.
	 */
	public class ArduinoReceiver extends BroadcastReceiver {
		private int pedo = 0;
		
		public int getPedo() {
			/*Intent i = new Intent(getApplicationContext(), MainPage.class);
			i.putExtra("pedoSteps",pedo);
			startActivity(i);*/
			return pedo;
		}
		
		
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String data = null;
			
			// the device address from which the data was sent, we don't need it here but to demonstrate how you retrieve it
			final String address = intent.getStringExtra(AmarinoIntent.EXTRA_DEVICE_ADDRESS);
			
			// the type of data which is added to the intent
			final int dataType = intent.getIntExtra(AmarinoIntent.EXTRA_DATA_TYPE, -1);
			
			// we only expect String data though, but it is better to check if really string was sent
			// later Amarino will support differnt data types, so far data comes always as string and
			// you have to parse the data to the type you have sent from Arduino, like it is shown below
			if (dataType == AmarinoIntent.STRING_EXTRA){
				data = intent.getStringExtra(AmarinoIntent.EXTRA_DATA);
				
				if (data != null){
					//mValueTV.setText(data);
					try {
						// since we know that our string value is an int number we can parse it to an integer
						final int sensorReading = Integer.parseInt(data);
						//mGraph.addDataPoint(sensorReading);
						if(sensorReading > 110) {
							pedoStep++;
							/*String FILENAME = "hello_file";
							FileOutputStream fos;
							try {
								File dir = getFilesDir();
								File file = new File(dir, FILENAME);
								boolean deleted = file.delete();
								//if (deleted) {
									System.out.println(FILENAME);
									fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
									fos.write(pedo);
									fos.close();
								//}
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}*/
						}
					} 
					catch (NumberFormatException e) { /* oh data was not an integer */ }
				}
			}
		}
	}
	
	

}
