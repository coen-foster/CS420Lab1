import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.net.MalformedURLException;

public class Server {
    public static void main(String[] args) {
        try {
            // Create vector clocks and remote processes
            VectorClock vectorClock1 = new VectorClock(3); // Example size of 5
            RemoteProcess remoteProcess1 = new RemoteProcess(vectorClock1, 0);
            VectorClock vectorClock2 = new VectorClock(3); // Example size of 5
            RemoteProcess remoteProcess2 = new RemoteProcess(vectorClock2, 1);
            VectorClock vectorClock3 = new VectorClock(3); // Example size of 5
            RemoteProcess remoteProcess3 = new RemoteProcess(vectorClock3, 2);

            // Create the RMI registry
            LocateRegistry.createRegistry(1099);

            // Bind the remote process objects to the registry
            Naming.rebind("rmi://localhost/RemoteProcess1", remoteProcess1);
            Naming.rebind("rmi://localhost/RemoteProcess2", remoteProcess2);
            Naming.rebind("rmi://localhost/RemoteProcess3", remoteProcess3);

            System.out.println("Server is ready.");
        } catch (RemoteException e) {
            System.err.println("RemoteException occurred.");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.err.println("MalformedURLException occurred.");
            e.printStackTrace();
        }
    }
}
