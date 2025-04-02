
import java.util.*;
public class SJF implements scheduler
{
    public List<Process> p;
    public double averageWT;
    public double averageTT;

    public SJF(List<Process> p)
    {
        this.p = new ArrayList<>(p);
        averageWT = 0;
        averageTT = 0;
    }
    List<String> executionOrder = new ArrayList<>();
    List<Process> executed = new ArrayList<>();
    int completionTime = 0;
    @Override
    public void execution()
    {
//        int completionTime = 0;

        p.sort(Comparator.comparingInt(p->p.arrivalTime));
        while(!p.isEmpty())
        {
            List<Process> tmp = new ArrayList<>();
            for(int i = 0 ; i < p.size()  ; i++)
            {
                Process proc = p.get(i);
                if(completionTime >= proc.arrivalTime && !executed.contains(proc))
                {
                    tmp.add(proc);
                }
            }

            if(!tmp.isEmpty())
            {
                tmp.sort(Comparator.comparingInt(p->p.burstTime));
                Process proc = tmp.get(0);
                executionOrder.add(proc.processName);
                completionTime += proc.burstTime;
                proc.turnaroundTime = completionTime - proc.arrivalTime;
                proc.waitingTime = proc.turnaroundTime - proc.burstTime;
                averageWT += proc.waitingTime;
                averageTT += proc.turnaroundTime;
                executed.add(proc);
                p.remove(proc);
            }
            else
            {
                completionTime++;
            }
        }

        if(!executed.isEmpty())
        {
            averageWT /= executed.size();
            averageTT /= executed.size();
        }

        display();
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
        GUI gui = new GUI("Shortest- Job  First Scheduler" , averageWT , averageTT , executed , executionOrder, completionTime);
    }
}