#include <unistd.h>
#include <iostream>
#include <signal.h>
#include <stdio.h>
#include "grovescam.h"

using namespace std;
using namespace upm;

int main (int argc, char **argv)
{
//! [Interesting]

  // Instantiate a Grove Serial Camera on UART 0
  upm::GROVESCAM* camera = new upm::GROVESCAM(0);

  // make sure port is initialized properly.  115200 baud is the default.
  if (!camera->setupTty())
    {
      cerr << "Failed to setup tty port parameters" << endl;
      return 1;
    }

  if (camera->init())
    cout << "Initialized..." << endl;
  else
    cout << "init() failed" << endl;

  if (camera->preCapture())
    cout << "preCapture succeeded..." << endl;
  else
    cout << "preCapture failed." << endl;

  if (camera->doCapture())
    cout << "doCapture succeeded..." << endl;
  else
    cout << "doCapture failed." << endl;

  cout << "Image size is " << camera->getImageSize() << " bytes" << endl;

  if (camera->getImageSize() > 0)
    {
      cout << "Storing image.jpg..." << endl;
      if (camera->storeImage("image.jpg"))
        cout << "storeImage succeeded..." << endl;
      else
        cout << "storeImage failed." << endl;
    }
//! [Interesting]

  delete camera;
  return 0;
}
