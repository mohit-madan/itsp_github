
#include "SD.h"
#define SD_ChipSelectPin 4
#include "TMRpcm.h"
#include "SPI.h"
TMRpcm tmrpcm;

int acc_x_array[4];
int acc_x_array_index=0;
int acc_z_array[4];
int acc_z_array_index=0;//declaring array and its variables
boolean is_hello , is_yes , is_love ,is_you , is_like;
int hello_yes_threshold_acc_x = /* !!!! type a threshold */ , hello_yes_threshold_acc_y = /* !!!! */ , you_threshold_acc_z = /* !!!! */ , you_threshold_acc_y = /* !!!! */ ;

void setup(){
  Serial.begin(9600);
  tmrpcm.speakerPin = 9;
  tmrpcm.setVolume(6);

  if (!SD.begin(SD_ChipSelectPin)) {
    Serial.println("SD fail");
    return;
   }
  } 
void loop(){
  int acc_x = analogRead(A5)-352 , acc_y=analogRead(A6)-352 , acc_z=analogRead(A7)-352 ;//creating accelerometer readings
  int  hello_threshold_acc_x_count=0 , yes_threshold_acc_x_count=0 ,you_threshold_acc_z_count=0 ;
  int flex_thumb = analogRead(A0) , flex_index = analogRead(A1) , flex_middle = analogRead(A2) , flex_ring = analogRead(A3) , flex_pinky = analogRead(A4) ;//reading flex sensor values
  
  Serial.print("ax = ");
  Serial.print(acc_x);
  Serial.print("\t");
  
  Serial.print("ay = ");
  Serial.print(acc_y);
  Serial.print("\t");
  
  Serial.print("az = ");
  Serial.print(acc_z);
  Serial.println("\t");
  Serial.print("\n")

  Serial.print("thumb = ");
  Serial.print(flex_thumb);
  Serial.print("\t");

  Serial.print("index = ");
  Serial.print(flex_index);
  Serial.print("\t");

  Serial.print("middle = ");
  Serial.print(flex_middle);
  Serial.print("\t");

  Serial.print("ring = ");
  Serial.print(flex_ring);
  Serial.print("\t");

  Serial.print("pinky = ");
  Serial.print(flex_pinky);
  Serial.println("\t");
  Serial.print("\n")
  delay(50);
  //check hello and yes
  acc_x_array[acc_x_array_index] = acc_x ;
  acc_x_array_index = (acc_x_array_index+1)%4; 

  acc_z_array[acc_x_array_index] = acc_x ;
  acc_z_array_index = (acc_x_array_index+1)%4; // creating acc_x_array and acc_z_array
  
  for(int j=0 ; j<4 ; j++) {
    if( acc_x_array[j] > hello_yes_threshold_acc_x && acc_y > hello_yes_threshold_acc_y ){
      if(/* !!!! add flex sensor threshold for hello */) {
        hello_threshold_acc_x_count++ ;
        if(hello_threshold_acc_x_count = 4) is_hello = true ;
      }
      if(/* !!!! add flex sensor threshold for yes */) {
        yes_threshold_acc_x_count++ ;
        if(yes_threshold_acc_x_count = 4) is_yes = true ;
      }

    }
  }
//check for you 
  for(int j=0 ; j<4 ; j++) {
    if(/* !!!! add flex sensor threshold for hello */ acc_z_array[j] > you_threshold_acc_z && acc_y > you_threshold_acc_y ){
      you_threshold_acc_z_count++ ;
      if(you_threshold_acc_z_count = 4) is_you = true ;
    }
  
  //check for "i love you"
  if(/* !!!! add flex sensor threshold for " i love you " */) {
    is_love = true ;
  }

  if(/* !!!! add flex sensor threshold for like*/) {
    is_like = true ;
  }

 
  
//give outputs
  if(is_hello) {

    Serial.println("hello") ;
    tmrpcm.play("hello.wav");
    delay(1000);
  }// give hello output

  if(is_yes) {

    Serial.println("yes") ;
    tmrpcm.play("yes.wav");
    delay(1000);
  }// give yes output

  if(is_love) {
    Serial.println("i love you") ;
    tmrpcm.play("love.wav");
    delay(1000);
  }// give "i love you " output

  if(is_you) {
    Serial.println("you") ;
    tmrpcm.play("you.wav");
    delay(1000);
  }

  if(is_like) {
    Serial.println("like") ;
    tmrpcm.play("like.wav");
    delay(1000);
  }

  
}






