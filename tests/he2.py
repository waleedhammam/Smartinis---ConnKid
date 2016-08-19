import mraa
import sys
from time import sleep
pot =  mraa.Aio(0)
while 1:
    potv = float(pot.read())
    if potv > 599 :
        m= int( potv / 10)
        print m
    sleep(1)
