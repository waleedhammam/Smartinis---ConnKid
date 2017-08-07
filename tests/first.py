from flask import Flask
import serial, sys, mraa, signal, atexit
from time import sleep
import pyupm_grovespeaker as upmGrovespeaker

app = Flask(__name__)

""" helper functions"""
def get_gps():
    ser = serial.Serial('/dev/ttyMFD1', 9600)
    for i in range(10):
        a = ser.readline()
        print a
    print a
    print "$GPGLL,31.87,N,30.62,E*77"
    return "$GPGLL,31.87,N,30.62,E*77"

def get_temp():
    pot = mraa.Aio(5)
    potVal = float(pot.read())
    x = (potVal*5/1024)/0.137
    x = int(x)
    sleep(1)
    print x
    return str(x)

def get_heart():
    m = 0
    pot =  mraa.Aio(0)
    for i in range(10):
        potv = float(pot.read())
        if potv > 599 :
            m= int( potv / 10)
            print m
        sleep(1)
    return str(m)  

def get_speaker():
    # Instantiate a Grove Speaker on digital pin D2
    mySpeaker = upmGrovespeaker.GroveSpeaker(2)

    #while True :

    # Play all 7 of the lowest notes
    mySpeaker.playAll()

    # Play a medium C-sharp
    mySpeaker.playSound('c', True, "med")

""" Server API"""
@app.route("/gps")
def gps():
    return get_gps()

@app.route("/temp")
def temp():
    return get_temp()

@app.route("/heart")
def heart():
    return get_heart()

@app.route("/speaker")
def speaker():
    get_speaker()
    return "speaker is running"     
if __name__ == "__main__":
    app.run('0.0.0.0')
