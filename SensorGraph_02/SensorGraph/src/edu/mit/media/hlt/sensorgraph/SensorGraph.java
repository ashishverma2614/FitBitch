/*
  SensorGraph - Example to use with Amarino 2.0
  Copyright (c) 2010 Bonifaz Kaufmann. 
  
  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2.1 of the License, or (at your option) any later version.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public
  License along with this library; if not, write to the Free Software
  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
package edu.mit.media.hlt.sensorgraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import at.abraxas.amarino.Amarino;
import at.abraxas.amarino.AmarinoIntent;

/**
 * <h3>Application that receives sensor readings from Arduino displaying it graphically.</h3>
 * 
 * This example demonstrates how to catch data sent from Arduino forwarded by Amarino 2.0.
 * SensorGraph registers a BroadcastReceiver to catch Intents with action string: <b>AmarinoIntent.ACTION_RECEIVED</b>
 * 
 * @author Bonifaz Kaufmann - April 2010
 *
 */
public class SensorGraph extends Activity {
	
	private static final String TAG = "SensorGraph";
	
	// change this to your Bluetooth device address 
	private static final String DEVICE_ADDRESS =  "20:13:01:23:00:55"; //"00:06:66:03:73:7B";
	
	private GraphView mGraph; 
	private TextView mValueTV;
	
	private ArduinoReceiver arduinoReceiver = new ArduinoReceiver();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        setContentView(R.layout.main);
        
        // get handles to Views defined in our layout file
        mGraph = (GraphView)findViewById(R.id.graph);
        mValueTV = (TextView) findViewById(R.id.value);
        
        mGraph.setMaxValue(1024);
        
        
    }
    
    /** Called when the user clicks the Send button */
    public void respondToButton(View view) {
        // Do something in response to button
    	
    	Intent intent = new Intent(this, MainPage.class);
   		intent.putExtra("toOpen", "0");
    	startActivity(intent);
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
			Intent i = new Intent(getApplicationContext(), MainPage.class);
			i.putExtra("pedoSteps",pedo);
			startActivity(i);
			System.out.println("pedooooo" + pedo);

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
					mValueTV.setText(data);
					try {
						// since we know that our string value is an int number we can parse it to an integer
						final int sensorReading = Integer.parseInt(data);
						mGraph.addDataPoint(sensorReading);
						if(sensorReading > 91 && address.equals("20:13:01:23:00:55")) {
							pedo++;
							String FILENAME = "hello_file";
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
							}
						}
					} 
					catch (NumberFormatException e) { /* oh data was not an integer */ }
				}
			}
		}
	}

}

