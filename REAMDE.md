# Bluetooth web car
This is a personal project of mine to control a bluetooth car with a phone.
This project acts like a bridge between the phone and the bluetooth chip.

### Hardware used
For the bluetooth chip, I am using the HC-05. 
Because the HC-05 cannot communicate with an iPhone directly I had to find a workaround.
Furthermore I am using an Attiny85 microcontroller for the brain in the rc car.
The bluetooth chip will comminucate with the microcontroller via Serial,
just like the bluetooth chip is comminucating to connected bluetooth devices.

### Lifecycle
When a request is made for the index (http://localhost) the server will send the index.html.
This will contain the script to send POST requests back when changing the speed or steering.
When a POST request is received it parse the data and send it to the HC-05 via serial.
It is a requirement that the device running the server is paired with it before running.
When the Attiny receives a command it will simply process it and will change certain pins.
