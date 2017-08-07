

import serial
import time
ser = serial.Serial()
ser.port="/dev/ttyMFD1"
ser.baudrate=38400
ser.open()
ser.isOpen()
while 1:
    x = ser.readline()
    time.sleep(1)
    print(x)
