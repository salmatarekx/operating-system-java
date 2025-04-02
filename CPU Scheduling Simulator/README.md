# CPU Scheduling Simulator

This project implements a CPU scheduling simulator that demonstrates various CPU scheduling algorithms with a graphical user interface. The simulator allows users to visualize and compare different scheduling algorithms in real-time.

## Features

- Multiple CPU scheduling algorithms:
  - Shortest Job First (SJF)
  - Shortest Remaining Time First (SRTF)
  - Priority Scheduling
  - FCFS (First Come First Serve)
- Interactive GUI for process visualization
- Real-time execution visualization
- Process management with customizable parameters
- Step-by-step execution tracking

## Project Structure

```
src/
├── Main.java                 # Main entry point of the application
├── GUI.java                  # Graphical User Interface implementation
├── GraphPanel.java           # Panel for visualizing process execution
├── Process.java              # Process class definition
├── scheduler.java            # Base scheduler interface
├── FCAIscheduler.java        # First Come First Serve implementation
├── SJF.java                  # Shortest Job First implementation
├── SRTF.java                 # Shortest Remaining Time First implementation
├── priorityScheduler.java    # Priority Scheduling implementation
├── ExecutionStep.java        # Class for tracking execution steps
└── ExecutionInterval.java    # Class for managing execution intervals
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- Java Swing (included in JDK)

## Getting Started

1. Clone the repository
2. Navigate to the project directory
3. Compile the project:
   ```bash
   javac src/*.java
   ```
4. Run the application:
   ```bash
   java -cp src Main
   ```

## Usage

1. Launch the application
2. Select the desired scheduling algorithm
3. Add processes with their respective parameters (burst time, arrival time, priority)
4. Start the simulation
5. Use the step-by-step execution to observe the scheduling process
6. View the execution timeline and statistics

## Scheduling Algorithms

### First Come First Serve (FCFS)
- Processes are executed in the order they arrive
- Non-preemptive scheduling
- Simple implementation with no priority consideration

### Shortest Job First (SJF)
- Process with the shortest burst time is executed first
- Non-preemptive scheduling
- Optimizes average waiting time

### Shortest Remaining Time First (SRTF)
- Preemptive version of SJF
- Process with shortest remaining time gets CPU
- Better for interactive systems

### Priority Scheduling
- Processes are executed based on priority
- Can be preemptive or non-preemptive
- Higher priority processes get CPU first

## Contributing

Feel free to submit issues and enhancement requests!

