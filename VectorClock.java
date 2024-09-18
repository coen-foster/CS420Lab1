import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class VectorClock extends UnicastRemoteObject implements VectorClockInterface {
    private static final long serialVersionUID = 1L;
    private int[] clock;

    public VectorClock(int size) throws RemoteException {
        super();
        clock = new int[size];
    }

    @Override
    public int[] getClock() throws RemoteException {
        return clock;
    }

    @Override
    public void increment(int processId) throws RemoteException {
        clock[processId]++;
    }

    @Override
    public void update(VectorClockInterface remoteClock, int processId) throws RemoteException {
    	int[] remote_clock = remoteClock.getClock();
        for (int i = 0; i < clock.length; i++) {
            clock[i] = Math.max(clock[i], remote_clock[i]);
        }
        this.increment(processId);
    }

    @Override
    public int[] merge(int[] clock1, int[] clock2) throws RemoteException {
        int[] mergedClock = new int[clock1.length];
        for (int i = 0; i < mergedClock.length; i++) {
            mergedClock[i] = Math.max(clock1[i], clock2[i]);
        }
        return mergedClock;
    }
}
