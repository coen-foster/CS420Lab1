import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VectorClockInterface extends Remote {
    int[] getClock() throws RemoteException;
    void increment(int processId) throws RemoteException;
    void update(VectorClockInterface remoteClock, int processId) throws RemoteException;
    int[] merge(int[] clock1, int[] clock2) throws RemoteException;
}
