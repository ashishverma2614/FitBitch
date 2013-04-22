#include <MeetAndroid.h>

#include <EEPROMEx.h>

//#include <EEPROM.h>

#include <Time.h>
   
  MeetAndroid meetAndroid; 
  int fsrPin = 0;     // the FSR and 10K pulldown are connected to a0
  int fsrReading;     // the analog reading from the FSR resistor divider
  int fsrVoltage;     // the analog reading converted to voltage
  unsigned long fsrResistance;  // The voltage converted to resistance, can be very big so make "long"
  unsigned long fsrConductance; 
  long fsrForce;       // Finally, the resistance converted to force
  int outputCount = 1;
  double WeightTotal = 0;
  double initialFoodWeight;
  double zeroWeight;
  double prevWeight;
  double initialPrev;
  double foodDiff;
  int address = 0;
  boolean foodInBowl = false;

  
  struct keyValuePair {
    time_t key;
    double value;
  };
  
   
  void setup(void) {
    Serial.begin(9600);   // We'll send debugging information via the Serial monitor
  }
   
  void loop(void) {
    fsrReading = analogRead(fsrPin);  
 /*   Serial.print("Analog reading = "); */
 /*     Serial.println(fsrReading); */
      
      if (outputCount == 1) {
        prevWeight = fsrReading;
        initialPrev = fsrReading;
      }
      
      /*
      if (outputCount <= 30) {
        WeightTotal += fsrReading;
      }
      
      if (outputCount == 25) {
        Serial.print("Average zero weight analog = ");
        zeroWeight = WeightTotal/25.0;
        Serial.println(zeroWeight);
      }*/
      
      /*if (outputCount > 30) {
        
        if (fsrReading > zeroWeight + 15) {
          Serial.println("Food added!");
          Serial.print("Food weight: ");
          Serial.print(fsrReading - zeroWeight);
            Serial.println("g");
          }
        }*/
        /*
        meetAndroid.receive();
        meetAndroid.send(3); */

        if (foodInBowl == true) {
          foodDiff = fsrReading - initialPrev;
          if (foodDiff > 15) {
            keyValuePair keyValueInput[2];
            keyValueInput[0].key = now();
            keyValueInput[0].value = initialFoodWeight;
            
    /*        address = EEPROM.getAddress(8);
            
            EEPROM.writeBlock(address, keyValueInput,2);
            Serial.println("This is in EEPROM: ");
            Serial.print(keyValueInput[0].key);
            Serial.print("\t");
            Serial.println(keyValueInput[0].value); */
            
            // send int of weight of food, to be parsed and put in the sensor graph
            meetAndroid.receive();
            meetAndroid.send((int)initialFoodWeight);
            
            // send time as string, to be displayed by Android but not parsed by sensor graph
            Serial.println(now());
            
            
            /*
            Serial.print("Current time: ");
            Serial.println(now());
           Serial.print("Food weight: ");
           Serial.println(initialFoodWeight); */
           foodInBowl = false;
          }
          else {
            foodInBowl = false;
          }
        }
        else {
          if (fsrReading - prevWeight > 15) {
           initialFoodWeight = fsrReading - prevWeight;
           initialPrev = prevWeight;
           foodInBowl = true;
          }
        }
    
/*      Serial.println();
              
      Serial.println("--------------------"); */
      delay(1000);
    outputCount++;
    prevWeight = fsrReading;
  }
