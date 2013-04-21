/*
  Sends sensor data to Arduino
  (needs SensorGraph and Amarino app installed and running on Android)
*/
 
#include <MeetAndroid.h>

MeetAndroid meetAndroid;
const int groundpin = A2;
const int powerpin = A0;
int sensorX = A5;
int sensorY = A4;
int sensorZ = A3;
int prevVal = 0;

void setup()  
{
  // use the baud rate your bluetooth module is configured to 
  // not all baud rates are working well, i.e. ATMEGA168 works best with 57600
  Serial.begin(9600); 
 
  // we initialize analog pin 5 as an input pin
  /*pinMode(sensorX, INPUT);
  pinMode(sensorY, INPUT);
  pinMode(sensorZ, INPUT);*/
   pinMode(groundpin, OUTPUT);
   pinMode(powerpin, OUTPUT);
   digitalWrite(groundpin, LOW); 
   digitalWrite(powerpin, HIGH);
}

void loop()
{
    int x, y, z, val;
   x = analogRead(sensorX);
   /*Serial.print("x:");
   Serial.println(x);*/
   y = analogRead(sensorY);
   /*Serial.print("y ");
   Serial.println(y);*/
   z = analogRead(sensorZ);
   /*Serial.print("z ");
   Serial.println(z);*/
   //val = x^2+y^2+z^2;
   val = x^2+y^2+z^2;
   //Serial.println(val);
   int diff = val - prevVal;
 
   if(diff > 100) {
     /*meetAndroid.send(diff);*/
   }
   meetAndroid.receive();
   meetAndroid.send(diff);
   prevVal = val; 
   
   // you need to keep this in your loop() to receive events
  // read input pin and send result to Android
 
  
  // add a little delay otherwise the phone is pretty busy
  delay(180);
}


