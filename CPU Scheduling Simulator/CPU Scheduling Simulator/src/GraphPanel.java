import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

class GraphPanel extends JPanel {
    private List<Process> executed;
    private List<String> executionOrder;
    private int totalTime;

    public GraphPanel(List<Process> executed, List<String> executionOrder, int totalTime) {
        this.executed = executed;
        this.executionOrder = executionOrder;
        this.totalTime = totalTime;  // Total time to calculate the grid width
        this.setPreferredSize(new Dimension(1000, executed.size() * 50 + 50));
        this.setBackground(Color.WHITE);
    }

    private Color getColorByName(String colorName) {
        switch (colorName.toLowerCase()) {
            case "red": return Color.RED;
            case "blue": return Color.BLUE;
            case "yellow": return Color.YELLOW;
            case "green": return Color.GREEN;
            case "cyan": return Color.CYAN;
            case "magenta": return Color.MAGENTA;
            case "orange": return Color.ORANGE;
            case "gray": return Color.GRAY;
            default: return Color.BLACK;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int blockWidth = 50;  // Width of each time unit
        int blockHeight = 30;  // Height of each process block
        int startX = 50;  // Starting position for drawing (leave space for timeline)
        int startY = 50;  // Vertical offset for rows (leave space for labels)
        int rowHeight = blockHeight + 20;  // Height between rows

        // Draw the timeline
        g.setColor(Color.BLACK);
        for (int i = 0; i <= totalTime; i++) {
            int x = startX + i * blockWidth;
            g.drawString(String.valueOf(i), x + 15, startY - 10);  // Draw timeline numbers
            g.drawLine(x, startY, x, startY + executed.size() * rowHeight);  // Vertical grid line
        }

        // Draw process rows and their execution blocks
        for (int row = 0; row < executed.size(); row++) {
            Process process = executed.get(row);
            String processName = process.processName;
            String processColor = process.processColor;

            int currentX = startX;  // Reset to starting X for each row
            int currentY = startY + row * rowHeight;  // Position for each row

            // Label the row with the process name
            g.setColor(Color.BLACK);
            g.drawString(processName, 10, currentY + blockHeight / 2);

            // Draw blocks for this process's execution times
            g.setColor(getColorByName(processColor));
            for (int i = 0; i < executionOrder.size(); i++) {
                if (executionOrder.get(i).equals(processName)) {
                    g.fillRect(currentX, currentY, blockWidth, blockHeight);  // Draw filled block
                    g.setColor(Color.BLACK);
                    g.drawRect(currentX, currentY, blockWidth, blockHeight);  // Draw block outline
                    currentX += blockWidth;  // Move to the next time slot
                } else {
                    currentX += blockWidth;  // Skip if the process wasn't executed in this time unit
                }
                g.setColor(getColorByName(processColor));  // Reset the color after drawing outline
            }
        }
    }
}