import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteProcess extends UnicastRemoteObject implements RemoteProcessInterface {
    private static final long serialVersionUID = 1L;
	private VectorClockInterface vectorClock;
    private int process;

    public RemoteProcess(VectorClockInterface vectorClock, int process) throws RemoteException {
        super();
        this.vectorClock = vectorClock;
        this.process = process;
    }

    @Override
    public VectorClockInterface getVectorClock() throws RemoteException {
        return vectorClock;
    }
    
    @Override
    public int getProcessId() throws RemoteException {
        return process;
    }

    @Override
    public void sendEvent(RemoteProcessInterface eventId, VectorClockInterface data) throws RemoteException {
        this.vectorClock.increment(this.process);
        eventId.getVectorClock().update(this.vectorClock, eventId.getProcessId());
    }
}
