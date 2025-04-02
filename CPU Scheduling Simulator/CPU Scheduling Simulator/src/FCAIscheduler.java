/*import java.util.*;

public class FCAIscheduler implements scheduler {
    private List<Process> processes;
    private int contextSwitchingTime;

    public FCAIscheduler(List<Process> processes, int contextSwitchingTime) {
        this.processes = new ArrayList<>(processes);
        this.contextSwitchingTime = contextSwitchingTime;
    }

    @Override
    public void execution() {
        int currentTime = 0;
        Process[] lastExecutedProcess = {null}; // To make it effectively final for lambda usage.
        List<Process> completedProcesses = new ArrayList<>(); // Store completed processes.

        System.out.println("Time | Process Executed | Remaining Burst Time | Updated Quantum | Priority | FCAI Factor | Action Details");

        while (!processes.isEmpty()) {
            int finalCurrentTime = currentTime;
            processes.forEach(p -> p.setFCAIfactor(calculateFCAIFactor(p, finalCurrentTime)));
            processes.sort(Comparator.comparingDouble(Process::getFCAIfactor));

            Process currentProcess = processes.remove(0);

            if (lastExecutedProcess[0] != null && lastExecutedProcess[0] != currentProcess) {
                currentTime += contextSwitchingTime;
                System.out.printf("Context Switching: Adding %d units to time.%n", contextSwitchingTime);
            }

            String initialFCAIFormula = calculateFCAIFormula(currentProcess, currentTime);

            int timeSlice = Math.min(currentProcess.getQuantum(), currentProcess.getRemainingTime());
            if (timeSlice == currentProcess.getQuantum()) {
                timeSlice = (int) (timeSlice * 0.4);
            }

            int previousBurstTime = currentProcess.getRemainingTime();
            currentTime += timeSlice;
            currentProcess.setRemainingTime(previousBurstTime - timeSlice);

            String updatedFCAIFormula = currentProcess.getRemainingTime() > 0
                    ? calculateFCAIFormula(currentProcess, currentTime)
                    : "Completed";

            int previousQuantum = currentProcess.getQuantum();
            if (currentProcess.getRemainingTime() > 0) {
                currentProcess.setQuantum(previousQuantum + 2);
            }

            String actionDetails = currentProcess.getRemainingTime() > 0
                    ? String.format("Process %s preempts, runs for %d units, remaining burst = %d.",
                    currentProcess.getProcessName(), timeSlice, currentProcess.getRemainingTime())
                    : String.format("Process %s completes execution.", currentProcess.getProcessName());

            System.out.printf("%d-%d | %s | %d -> %d | %s | %d | %s -> %s | %s%n",
                    currentTime - timeSlice, currentTime, currentProcess.getProcessName(),
                    previousBurstTime, currentProcess.getRemainingTime(),
                    previousQuantum > 0 ? (previousQuantum + " -> " + currentProcess.getQuantum()) : "Completed",
                    currentProcess.getPriority(),
                    initialFCAIFormula, updatedFCAIFormula, actionDetails);

            if (currentProcess.getRemainingTime() > 0) {
                processes.add(currentProcess);
            } else {
                currentProcess.setCompletionTime(currentTime);
                currentProcess.setTurnaroundTime(currentProcess.getCompletionTime() - currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());
                completedProcesses.add(currentProcess);
            }

            lastExecutedProcess[0] = currentProcess;
        }

        displayResults(completedProcesses);
    }

    private double calculateFCAIFactor(Process p, int currentTime) {
        int V1 = 10; // Normalization factor for arrival time
        int V2 = 10; // Normalization factor for burst time
        return (10 - p.getPriority()) + (p.getArrivalTime() - currentTime) / (double) V1 + p.getRemainingTime() / (double) V2;
    }

    private String calculateFCAIFormula(Process p, int currentTime) {
        int V1 = 10; // Normalization factor for arrival time
        int V2 = 10; // Normalization factor for burst time
        return String.format("(10 - %d) + (%d - %d)/%.1f + %d/%.1f",
                p.getPriority(), p.getArrivalTime(), currentTime, (double) V1, p.getRemainingTime(), (double) V2);
    }

    private void displayResults(List<Process> completedProcesses) {
        System.out.println("\nResults for FCAI Scheduling:");
        for (Process p : completedProcesses) {
            System.out.printf("Process %s: Waiting Time = %d, Turnaround Time = %d, Completion Time = %d%n",
                    p.getProcessName(), p.getWaitingTime(), p.getTurnaroundTime(), p.getCompletionTime());
        }
    }

    @Override
    public void display() {
        System.out.println("Displaying FCAI Scheduler information.");
    }
}
*/
import java.util.*;

public class FCAIscheduler implements scheduler {
    private List<Process> processes;
    private int contextSwitchingTime;

    public FCAIscheduler(List<Process> processes, int contextSwitchingTime) {
        this.processes = new ArrayList<>(processes);
        this.contextSwitchingTime = contextSwitchingTime;
    }

    @Override
    public void execution() {
        int currentTime = 0;
        Process[] lastExecutedProcess = {null}; // To make it effectively final for lambda usage.
        List<Process> completedProcesses = new ArrayList<>(); // Store completed processes.

        System.out.println("Time | Process Executed | Remaining Burst Time | Updated Quantum | Priority | FCAI Factor | Action Details");

        while (!processes.isEmpty()) {
            int finalCurrentTime = currentTime;
            processes.forEach(p -> p.setFCAIfactor(calculateFCAIFactor(p, finalCurrentTime)));
            processes.sort(Comparator.comparingDouble(Process::getFCAIfactor));

            Process currentProcess = processes.remove(0);

            if (lastExecutedProcess[0] != null && lastExecutedProcess[0] != currentProcess) {
                currentTime += contextSwitchingTime;
                System.out.printf("Context Switching: Adding %d units to time.%n", contextSwitchingTime);
            }

            String initialFCAIFormula = calculateFCAIFormula(currentProcess, currentTime);

            int timeSlice = Math.min(currentProcess.getQuantum(), currentProcess.getRemainingTime());
            if (timeSlice == currentProcess.getQuantum()) {
                timeSlice = (int) (timeSlice * 0.4);
            }

            int previousBurstTime = currentProcess.getRemainingTime();
            currentTime += timeSlice;
            currentProcess.setRemainingTime(Math.max(0, previousBurstTime - timeSlice));

            String updatedFCAIFormula = currentProcess.getRemainingTime() > 0
                    ? calculateFCAIFormula(currentProcess, currentTime)
                    : "Completed";

            int previousQuantum = currentProcess.getQuantum();
            if (currentProcess.getRemainingTime() > 0) {
                currentProcess.setQuantum(previousQuantum + 2);
            }

            String actionDetails = currentProcess.getRemainingTime() > 0
                    ? String.format("Process %s preempts, runs for %d units, remaining burst = %d.",
                    currentProcess.getProcessName(), timeSlice, currentProcess.getRemainingTime())
                    : String.format("Process %s completes execution.", currentProcess.getProcessName());

            System.out.printf("%d-%d | %s | %d -> %d | %s | %d | %s -> %s | %s%n",
                    currentTime - timeSlice, currentTime, currentProcess.getProcessName(),
                    previousBurstTime, currentProcess.getRemainingTime(),
                    previousQuantum > 0 ? (previousQuantum + " -> " + currentProcess.getQuantum()) : "Completed",
                    currentProcess.getPriority(),
                    initialFCAIFormula, updatedFCAIFormula, actionDetails);

            if (currentProcess.getRemainingTime() > 0) {
                processes.add(currentProcess);
            } else {
                currentProcess.setCompletionTime(currentTime);
                int turnaroundTime = currentProcess.getCompletionTime() - currentProcess.getArrivalTime();
                int waitingTime = turnaroundTime - currentProcess.getBurstTime();

                currentProcess.setTurnaroundTime(turnaroundTime);
                currentProcess.setWaitingTime(waitingTime);
                completedProcesses.add(currentProcess);
            }

            lastExecutedProcess[0] = currentProcess;
        }

        // Calculate and display the averages
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        for (Process p : completedProcesses) {
            totalWaitingTime += p.getWaitingTime();
            totalTurnaroundTime += p.getTurnaroundTime();
        }

        double averageWaitingTime = totalWaitingTime / completedProcesses.size();
        double averageTurnaroundTime = totalTurnaroundTime / completedProcesses.size();

        System.out.printf("\nAverage Waiting Time: %.2f%n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f%n", averageTurnaroundTime);
    }

    private double calculateFCAIFactor(Process p, int currentTime) {
        int V1 = 10; // Normalization factor for arrival time
        int V2 = 10; // Normalization factor for burst time
        return (10 - p.getPriority()) + (p.getArrivalTime() - currentTime) / (double) V1 + p.getRemainingTime() / (double) V2;
    }

    private String calculateFCAIFormula(Process p, int currentTime) {
        int V1 = 10; // Normalization factor for arrival time
        int V2 = 10; // Normalization factor for burst time
        return String.format("(10 - %d) + (%d - %d)/%.1f + %d/%.1f",
                p.getPriority(), p.getArrivalTime(), currentTime, (double) V1, p.getRemainingTime(), (double) V2);
    }

    private void displayResults(List<Process> completedProcesses) {
        System.out.println("\nResults for FCAI Scheduling:");
        for (Process p : completedProcesses) {
            System.out.printf("Process %s: Waiting Time = %d, Turnaround Time = %d, Completion Time = %d%n",
                    p.getProcessName(), p.getWaitingTime(), p.getTurnaroundTime(), p.getCompletionTime());
        }
    }

    @Override
    public void display() {
        System.out.println("Displaying FCAI Scheduler information.");
    }
}
