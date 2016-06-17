
#include "SD.h"
#define SD_ChipSelectPin 4
#include "TMRpcm.h"
#include "SPI.h"
TMRpcm tmrpcm;

int hello_threshold_array[4];
int hello_array_index=0;//declaring array and its variables
boolean is_hello ;

void setup(){
  Serial.begin(9600);
  tmrpcm.speakerPin = 9;
  tmrpcm.setVolume(6);

  if (!SD.begin(SD_ChipSelectPin)) {
    Serial.println("SD fail");
    return;
   }
void loop(){
  int acc_x = analogRead(A0)-352 , acc_y=analogRead(A1)-352 , acc_z=analogRead(A2)-352 ;//creating accelerometer readings
  int hello_threshold = /* !!!! type a threshold */ , hello_threshold_count=0 ;
  int flex_thumb = analogRead(A3) , flex_index = analogRead(A4) , flex_middle = analogRead(A5) , flex_ring = analogRead(A6) , flex_pinky = analogRead(A7) ;//reading flex sensor values
  
  Serial.print("ax = ");
  Serial.print(acc_x);
  Serial.print("\t");
  
  Serial.print("ay = ");
  Serial.print(acc_y);
  Serial.print("\t");
  
  Serial.print("az = ");
  Serial.print(acc_z);
  Serial.println("\t");
  
  delay(50);
  
  hello_threshold_array[hello_array_index] = acc_x ;
  hello_array_index = (hello_array_index+1)%4; // creating hello_threshold_array
  
  for(int j=0 ; j<4 ; j++) {
    if( /* !!!! add flex condition */ hello_threshold_array[j] > hello_threshold && acc_y > 65 ){
      hello_threshold_count++ ;
      if(x = 4) is_hello = true ;
    }
  }//check if the word is hello
  
  if(is_hello) {
    Serial.println("hello") ;
    tmrpcm.play("hello.wav");
    delay(1000);
  }// give hello output
  
}




