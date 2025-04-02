import java.util.*;

public class Main
{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numOfProcess;
        System.out.println("Enter number of processes");
        numOfProcess = in.nextInt();

        int contextSwitching;
        System.out.println("Enter context switching");
        contextSwitching = in.nextInt();

        String processName;
        String processColor;
        int arrivalTime;
        int burstTime;
        int priorityNumber;
        int q;
        List<Process> processList = new ArrayList<>();

        for(int i = 0 ; i < numOfProcess ; i ++)
        {
            System.out.println("Enter name of process" + (i+1));
            processName = in.next();

            System.out.println("Enter color of process" + (i+1));
            processColor = in.next();

            System.out.println("Enter arrival time of process" + (i+1));
            arrivalTime = in.nextInt();

            System.out.println("Enter burst time of process" + (i+1));
            burstTime = in.nextInt();

            System.out.println("Enter priority number of process" + (i+1));
            priorityNumber = in.nextInt();

            System.out.println("Enter quantum number of process" + (i+1));
            q = in.nextInt();

            Process p = new Process(arrivalTime, priorityNumber, burstTime, q, processColor, processName);
            processList.add(p);
        }

        System.out.println("Priority Scheduler:\n");
        priorityScheduler ps = new priorityScheduler(processList, contextSwitching);
        ps.execution();

        System.out.println("\nShortest Job First Scheduler:\n");
        SJF S = new SJF(processList);
        S.execution();

        System.out.println("\nShortest- Remaining Time First Scheduler:\n");
        SRTF SR = new SRTF(processList, contextSwitching);
        SR.execution();

        System.out.println("\nFCAI Scheduler:\n");
        FCAIscheduler FCAI = new FCAIscheduler(processList, contextSwitching);
        FCAI.execution();
    }
}
