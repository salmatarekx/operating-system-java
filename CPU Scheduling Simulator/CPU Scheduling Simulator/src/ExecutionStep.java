public class ExecutionStep {
    private int startTime, endTime;
    private Process process;

    public ExecutionStep(int startTime, int endTime, Process process) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.process = process;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public Process getProcess() {
        return process;
    }
}
