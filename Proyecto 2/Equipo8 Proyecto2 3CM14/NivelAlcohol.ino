#define sensor25 2
#define sensor50 3
#define sensor75 4
#define sensor100 5

int val = 0;
int phone = 0;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);  
  pinMode(sensor25, INPUT);
  pinMode(sensor50, INPUT);
  pinMode(sensor75, INPUT);
  pinMode(sensor100, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available()>0){
      phone = (Serial.read());
  }
  switch(phone){
    case '1':{
      val = digitalRead(sensor25);
      if(val>0){
        val = digitalRead(sensor50);
        if(val>0){
          val = digitalRead(sensor75);
          if(val>0){
            val = digitalRead(sensor100);    
            if(val>0){
              Serial.print("100.00\n");
              Serial.flush();
            }else{
              Serial.print("75.00\n");
              Serial.flush();
            }
          }else{
            Serial.print("50.00\n");
            Serial.flush();
          }
        }else{
          Serial.print("25.00\n");
          Serial.flush();
        }  
      }else{
        Serial.print("0.00\n");  
        Serial.flush();
      }
    }
    break;
  }
}
