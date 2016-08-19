import mraa
import sys
from time import sleep
pot = mraa.Aio(0)
while 1:
    potval=float(pot.read())
    print potval
    sleep(1)
