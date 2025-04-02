import java.util.*;

public class SRTF implements scheduler
{
    public List<Process> p;
    public int contextSwitching;
    public double averageWT;
    public double averageTT;

    // This map will store execution intervals for each process
    Map<String, List<ExecutionInterval>> executionIntervalsMap = new HashMap<>();

    public SRTF(List<Process> p , int contextSwitching)
    {
        this.p = new ArrayList<>(p);
        this.contextSwitching = contextSwitching;
        averageWT = 0;
        averageTT = 0;
    }
    List<String> executionOrder = new ArrayList<>();
    List<Process> executed = new ArrayList<>();
    int completionTime = 0;
    @Override
    public void execution()
    {
        //int completionTime = 0;
        Process last= null;

        p.sort(Comparator.comparingInt(p->p.arrivalTime));

        while(!p.isEmpty())
        {
            List<Process> tmp = new ArrayList<>();
            for(int i = 0 ; i < p.size()  ; i++)
            {
                Process proc = p.get(i);
                if(completionTime >= proc.arrivalTime && proc.remainingTime > 0)
                {
                    tmp.add(proc);
                }
            }

            if(!tmp.isEmpty())
            {
                tmp.sort(Comparator.comparingInt(p->p.remainingTime));
                Process proc = tmp.get(0);
                if(last != null && last != proc)
                {
                    completionTime +=contextSwitching;
                }

                // Add an execution interval for the current process
                addExecutionInterval(proc.processName, completionTime, completionTime + 1);

                executionOrder.add(proc.processName);
                proc.remainingTime--;
                completionTime++;

                if(proc.remainingTime == 0)
                {
                    proc.turnaroundTime = completionTime - proc.arrivalTime + contextSwitching;
                    proc.waitingTime = proc.turnaroundTime - proc.burstTime;
                    executed.add(proc);
                    p.remove(proc);
                }
                last = proc;
            }
            else
            {
                completionTime++;
            }
        }

        for(int i = 0 ; i < executed.size() ; i++)
        {
            Process proc = executed.get(i);
            averageWT += proc.waitingTime;
            averageTT += proc.turnaroundTime;
        }

        averageWT /= executed.size();
        averageTT /= executed.size();

        display();
    }

    private void addExecutionInterval(String processName, int startTime, int endTime) {
        executionIntervalsMap.putIfAbsent(processName, new ArrayList<>());
        executionIntervalsMap.get(processName).add(new ExecutionInterval(startTime, endTime));
    }

    @Override
    public void display()
    {
        System.out.println("executing order " + String.join(" -> " , executionOrder));
        executed.sort(Comparator.comparing(p->p.processName));
        for(int i = 0 ; i < executed.size() ; i++)
        {
            Process proc = executed.get(i);
            System.out.println("process " + proc.processName + " waiting time " + proc.waitingTime);
            System.out.println("process " + proc.processName + " turnaround time " + proc.turnaroundTime);
            System.out.println("\n");
        }

        System.out.println("Average waiting time " + averageWT);
        System.out.println("Average turnaround time " + averageTT);
        GUI gui = new GUI("Shortest- Remaining Time First" , averageWT , averageTT , executed , executionOrder, completionTime);
    }
}
