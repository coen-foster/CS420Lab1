import java.rmi.Naming;

public class Client {
	public static void printVectorClock(RemoteProcessInterface remoteProcess) {
		try {
			int[] vectorClock = remoteProcess.getVectorClock().getClock();
			int processId = remoteProcess.getProcessId();
			
	        System.out.print("Vector Clock for process " + (processId + 1) + ": [");
	        for (int i = 0; i < vectorClock.length; i++) {
	            System.out.print(vectorClock[i]);
	            if (i < vectorClock.length - 1) {
	                System.out.print(", ");
	            }
	        }
	        System.out.println("]");
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
    public static void main(String[] args) {
        try {
            RemoteProcessInterface process1 = (RemoteProcessInterface) Naming.lookup("rmi://localhost/RemoteProcess1");
            RemoteProcessInterface process2 = (RemoteProcessInterface) Naming.lookup("rmi://localhost/RemoteProcess2");
            RemoteProcessInterface process3 = (RemoteProcessInterface) Naming.lookup("rmi://localhost/RemoteProcess3");
            
            RemoteProcessInterface[] send_processes = new RemoteProcessInterface[] {process1, process3, process2, process1};
            RemoteProcessInterface[] recieve_processes = new RemoteProcessInterface[] {process3, process2, process1, process3};

            for(int i = 0; i < send_processes.length; i++) {
	            // Print initial vector clocks
	            printVectorClock(send_processes[i]);
	            printVectorClock(recieve_processes[i]);
	
	            // Complete send event
	            send_processes[i].sendEvent(recieve_processes[i], send_processes[i].getVectorClock());
	            System.out.println("Process " + (send_processes[i].getProcessId() + 1) + " sends to process " + (recieve_processes[i].getProcessId() + 1));
	
	            // Print the vector clock after the send event
	            printVectorClock(send_processes[i]);
	            printVectorClock(recieve_processes[i]);

        	}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
