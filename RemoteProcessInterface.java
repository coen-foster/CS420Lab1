import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteProcessInterface extends Remote {
    VectorClockInterface getVectorClock() throws RemoteException;
    void sendEvent(RemoteProcessInterface eventId, VectorClockInterface data) throws RemoteException;
    public int getProcessId() throws RemoteException;
}
