import upm_pulsensor.*;

public class PulsensorSample {
	static class PulsensorCallback extends Callback {
		public PulsensorCallback() {
			super();
		}
		public void run (clbk_data arg) {
			System.out.println();
		}
	}

	public static void main (String[] args) throws 
InterruptedException {
		// ! [Interesting]
		Callback obj_call = new PulsensorCallback();
		Pulsensor p = new Pulsensor(obj_call);
		p.start_sampler();
		Thread.sleep(1000);
		p.stop_sampler();
		// ! [Interesting]
	}
}
