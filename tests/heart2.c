#include <string.h>
#include <unistd.h>
#include "pulsensor.h"
#include <signal.h>

using namespace upm;

int doWork = 0;

void
sig_handler(int signo)
{
    printf("got signal\n");
    if (signo == SIGINT) {
        printf("exiting application\n");
        doWork = 1;
    }
}

void
handler (clbk_data data) {
    printf ("callback data (%d)\n", data);
}

int
main(int argc, char **argv)
{
//! [Interesting]
    Pulsensor *sensor = new Pulsensor(handler);
    
    sensor->start_sampler();
    while (!doWork) {
        usleep (5);
    }
    sensor->stop_sampler();
//! [Interesting]
    return 0;
}
