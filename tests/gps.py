import serial
from time import sleep

ser = serial.Serial('/dev/ttyMFD1', 9600)

ser.write("uBlox Neo 6M");
while True:
    a = ser.readline()
    print a
    sleep(1)

