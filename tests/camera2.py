import sys
import pyupm_grovescam as upmGrovescam

# Instantiate a Grove Serial Camera on UART 0
camera = upmGrovescam.GROVESCAM(0)

# make sure port is initialized properly. 115200 baud is the default.
if (not camera.setupTty()):
	print "Failed to setup tty port parameters"
	sys.exit(1)

if (camera.init()):
        print "Initialized..."
else:
        print "init() failed"

if (camera.preCapture()):
        print "preCapture succeeded..."
else:
        print "preCapture failed."

if (camera.doCapture()):
        print "doCapture succeeded..."
else:
        print "doCapture failed."

print "Image size is", camera.getImageSize(), "bytes"

if (camera.getImageSize() > 0):
        print "Storing image.jpg..."
        if (camera.storeImage("image.jpg")):
                print "storeImage succeeded..."
        else:
                print "storeImage failed."

print "Exiting."
sys.exit(0)
