from time import sleep
import serial
import mraa
rate=[10]
pot = mraa.Aio(0)
sample = 0
last = 0
t =512
p=512
th=512
n=512
Pulse = False
firstBeat = True      
secondBeat = True
ibi =600
BPM = 0
amp = 100
while True:
    runningTotal = 0 
    bot = float(pot.read())
    sample +=2
    n=sample -last
    if (bot < th) and (n > (ibi/5)*3) :
        if (bot < t):
            t = bot
    if (bot > th) and (bot > p) :
        p = bot
    if n > 250 :
        if(bot > th) and (Pulse == False) and (n > (ibi/5)*3):
            Pulse = True
            ibi = sample  - last
            last = sample
            if firstBeat:
                firstBeat = False  
                continue
            if secondBeat :
                secondBeat = False
                for i in range(0,10):
                    rate.append(ibi)
            for i in range(0,9) :
                rate[i] = rate[i+1]              
                runningTotal += rate[i]
            rate[9] = ibi                          
            runningTotal += rate[9]               
            runningTotal /= 10 
            BPM = 60000/runningTotal 
    if (bot < th) and (Pulse == True) :
        Pulse = False          
        amp = p - t                           
        th = amp/2 + t                    
        p = th                            
        t = th
    if n > 2500:
        th = 512
        P = 512                               
        T = 512
        last = sample      
        firstBeat = True                      
        secondBeat = True
    sleep(0.2)
    print BPM
    print ibi             
           
          

   

        
