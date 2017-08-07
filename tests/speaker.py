
import time, sys, signal, atexit
import pyupm_grovespeaker as upmGrovespeaker

# Instantiate a Grove Speaker on digital pin D2
mySpeaker = upmGrovespeaker.GroveSpeaker(2)

#while True :

    # Play all 7 of the lowest notes
mySpeaker.playAll()

    # Play a medium C-sharp
mySpeaker.playSound('c', True, "med")
