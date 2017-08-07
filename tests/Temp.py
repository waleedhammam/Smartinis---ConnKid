import mraa
import sys
from time import sleep

pot = mraa.Aio(5)   

while 1:
    potVal = float(pot.read())
    x = (potVal*5/1024)/0.137
    x=int(x)
    sleep(1)
    print x
    
