import mraa
import sys

pot = mraa.GPio(3)   

while 1:
    potVal = float(pot.write(20))
    print potVal
