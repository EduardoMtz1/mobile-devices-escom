// Definiciones de pines
#define carga 13    //Pin 13 para cargar el capacitor
#define descarga 12 //Pin 12 para descargar el capacitor 
#define tension A0  //Pin A0 para realizar las mediciones de carga

// Definiciones de constantes
#define resistencia 37.61       // Resistencia del pull-up interno expresada en kilo ohms del arduino, pata 13
#define capacitor_parasito 3.15 // Capacitor parasito

// Variables
long tiempo = 0;              //Guarda el tiempo de carga
float capacitor = 0.0;        //Guarda el valor de capacidad
float capacitor_micro = 0.0;  //Guarda el valor de un capacitor en micros
float capacitor_mili = 0.0;   //Guarda el valor de un capacitor en milis
int phone = 9;
void setup() {
  Serial.begin(9600);       //Inicializo la comunicación serial
  pinMode(descarga,INPUT);  //Pin de descarga como entrada de alta impedancia
}

void loop() {
if(Serial.available()>0){
      phone = (Serial.read());
    }
switch(phone){
  case '1':
  {
      pinMode(carga,INPUT_PULLUP);      //Pin de carga como entrada con pull-up
      tiempo = micros();                //Guardo el instante de tiempo
      while (analogRead(tension)<648);  //Mido hasta llegar al 63% de tensión, o sea 1 TAU
      tiempo = micros() - tiempo;       //Obtengo el tiempo transcurrido
      pinMode(carga,INPUT);             //Pin de carga como entrada con pull-up
      pinMode(descarga,OUTPUT);         //Pin de descarga como salida
      digitalWrite(descarga,LOW);       //Activo la descarga
      capacitor = tiempo/resistencia;   //Calculo el valor de capacidad
      if (capacitor >= capacitor_parasito)  // Ingreso si el valor de capacidad es mayor al de la capacidad parasita
      { 
        if (capacitor < 1000)               //Pregunta si es menor que 1 micro
        {
          Serial.print("El valor del capacitor es: ");  
          Serial.print(capacitor);                      
          Serial.println("nF");                         
        }
        else
        {
          if (capacitor < 1000000)          // Pregunta si es menor que 1 mili
          {
            capacitor_micro = (float) capacitor/1000;   //Paso a la unidad de uF
            Serial.print("El valor del capacitor es: ");
            Serial.print(capacitor_micro);              
            Serial.println("uF");                       
          }
          else
          {
            if (capacitor <= 4700000)       // Limitando las lecturas a 4,7 mF
            {
              capacitor_mili = (float) capacitor/1000000; //Paso a la unidad de mF
              Serial.print("El valor del capacitor es: ");
              Serial.print(capacitor_mili);               
              Serial.println("mF");                       
            }
            else
            {
              Serial.print("El valor del capacitor es muy grande");//Imprimo texto para capacitores mayores a 4.7mF
            }
          }
        }
      }
      else
      {
        Serial.println("El valor del capacitor es: 0nF");         
      }
      while(analogRead(tension) != 0);    //Espera hasta que se descargue por completo el capacitor
      pinMode(descarga,INPUT);            //Pin de descarga en alta impedancia
      delay(1000);                        //Espera de 1 segundo
    }
    break;
  }
  
  
}
