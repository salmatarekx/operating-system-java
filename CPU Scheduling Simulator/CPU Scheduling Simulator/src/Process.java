public class Process {
    public int arrivalTime;
    public int waitingTime;
    public int priority;
    public int burstTime;
    public int turnaroundTime;
    public String processColor;
    public String processName;
    public int remainingTime;
    public int quantum;
    public double FCAIfactor; // Changed to double for precision
    public boolean started;
    public int completionTime;

    public Process(int arrivalTime, int priority, int burstTime, int quantum, String processColor, String processName) {
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.burstTime = burstTime;
        this.quantum = quantum;
        this.processColor = processColor;
        this.processName = processName;
        this.remainingTime = burstTime;
        this.started = false;
    }

    // Getters and setters
    public int getArrivalTime() { return arrivalTime; }

    public int getWaitingTime() { return waitingTime; }

    public void setWaitingTime(int waitingTime) { this.waitingTime = waitingTime; }

    public int getPriority() { return priority; }

    public int getBurstTime() { return burstTime; }

    public int getTurnaroundTime() { return turnaroundTime; }

    public void setTurnaroundTime(int turnaroundTime) { this.turnaroundTime = turnaroundTime; }

    public String getProcessColor() { return processColor; }

    public String getProcessName() { return processName; }

    public int getRemainingTime() { return remainingTime; }

    public void setRemainingTime(int remainingTime) { this.remainingTime = remainingTime; }

    public int getQuantum() { return quantum; }

    public void setQuantum(int quantum) { this.quantum = quantum; }

    public double getFCAIfactor() { return FCAIfactor; } // Updated to return double

    public void setFCAIfactor(double FCAIfactor) { this.FCAIfactor = FCAIfactor; } // Updated to accept double

    public boolean hasStarted() { return started; }

    public void setStarted(boolean started) { this.started = started; }

    public int getCompletionTime() { return completionTime; } // Added getter for completionTime

    public void setCompletionTime(int completionTime) { this.completionTime = completionTime; } // Added setter for completionTime
}
