package edu.mit.media.hlt.sensorgraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

import android.content.Intent;
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
	private static int pedoStep = 0;
	
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
		
		
		String myParam = "";
		Bundle extras = getIntent().getExtras();
	    if (extras != null)
	    {
	        myParam = extras.getString("toOpen");
	    }
	    
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		if(myParam.equals("0"))
			mViewPager.setCurrentItem(0);
		else if(myParam.equals("2"))
			mViewPager.setCurrentItem(2);
		else
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
    	    	
    }
    
    public void respondToRefreshLastFedButton(View view) {
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
	    			
	    			System.out.println(pedoStep);
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
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			/*dummyTextView.setText(Integer.toString(getArguments().getInt(
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

		public FoodSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_page_food,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			/*dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));*/
			return rootView;
		}
		
	
	}

}
