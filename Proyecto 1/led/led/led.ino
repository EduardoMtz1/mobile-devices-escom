int phone = 9;
int estado = 0;
void setup() {
  Serial.begin(9600);
  pinMode(7,OUTPUT);
}

void loop() {
  if(Serial.available()>0){
      phone = (Serial.read());
    }
  switch(phone){
      case '0':
      {
        digitalWrite(7,LOW);  
        if(estado == 1){
          Serial.println("Off\n");
          Serial.flush();
          estado = 0;
        }
        
      }
      break;

      case'1':
      {
        digitalWrite(7,HIGH);  
        if(estado == 0){
          Serial.println("On\n");
          Serial.flush();
          estado = 1;
        }
      }
    }
}
