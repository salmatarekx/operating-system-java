import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GUI extends JPanel {
    JFrame frame = new JFrame();

    JPanel panel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel statePanel = new JPanel();
    JPanel tablePanel = new JPanel();
    JPanel tableFrame = new JPanel(new BorderLayout());

    // Graph graphPanel ;
    GraphPanel graphPanel;

    JLabel label1;
    JLabel label2;
    JLabel label3;

    JTextArea textArea1;
    JTextArea textArea2;
    JTextArea textArea3;

    JTable table;


    public GUI(String scheduleName, double AWT, double ATAT, List<Process> executed, List<String> executionOrder, int totalTime) {
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BorderLayout());
        frame.setSize(1256, 652);

        this.graphPanel = new GraphPanel(executed, executionOrder , totalTime);
        this.panel.add(graphPanel, BorderLayout.CENTER);

        label1 = new JLabel("CPU Scheduling Graph");
        label1.setFont(new Font("Bold", Font.BOLD, 18));
        label1.setForeground(Color.red);
        label1.setBackground(Color.DARK_GRAY);
        label1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label1, BorderLayout.NORTH);

        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(Color.DARK_GRAY);
        label2 = new JLabel("Processes Information");
        label2.setFont(new Font("Bold", Font.BOLD, 18));
        label2.setForeground(Color.red);
        label2.setHorizontalAlignment(SwingConstants.LEFT);
        label2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        tablePanel.add(label2, BorderLayout.NORTH);

        String[] Column = {"PROCESS", "COLOR", "NAME", "PID", "PRIORITY"};
        DefaultTableModel tableModel = new DefaultTableModel(Column, 0);
        table = new JTable(tableModel);
        table.setShowGrid(false);
        table.setForeground(Color.lightGray);
        table.setBackground(Color.GRAY);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        int PID = 3280;
        for (int i = 0; i < executed.size(); i++) {
            Process p = executed.get(i);
            Object[] row = {i, p.processColor, p.processName, PID++, p.priority};
            tableModel.addRow(row);
        }

        table.getTableHeader().setFont(new Font("Bold", Font.BOLD, 14));
        table.getTableHeader().setForeground(Color.RED);
        table.getTableHeader().setBackground(Color.GRAY);
        table.getTableHeader().setBorder(BorderFactory.createEmptyBorder());

        tableFrame.setBackground(Color.DARK_GRAY);
        tableFrame.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));
        tableFrame.add(table.getTableHeader(), BorderLayout.NORTH);
        tableFrame.add(table, BorderLayout.CENTER);
        tablePanel.add(tableFrame, BorderLayout.CENTER);
        panel.add(tablePanel, BorderLayout.EAST);

        statePanel.setBackground(Color.DARK_GRAY);
        statePanel.setLayout(new BoxLayout(statePanel, BoxLayout.Y_AXIS));

        label3 = new JLabel("<html><u>Statistics</u><html>");
        label3.setFont(new Font("Italic", Font.ITALIC, 18));
        label3.setForeground(Color.red);
        label3.setBackground(Color.DARK_GRAY);
        label3.setAlignmentX(Component.LEFT_ALIGNMENT);
        statePanel.add(label3);

        textArea1 = new JTextArea("Schedule Name: " + scheduleName);
        textArea1.setEditable(false);
        textArea1.setFont(new Font("Italic", Font.ITALIC, 18));
        textArea1.setForeground(Color.white);
        textArea1.setBackground(Color.DARK_GRAY);
        textArea1.setAlignmentX(Component.LEFT_ALIGNMENT);
        statePanel.add(textArea1);

        textArea2 = new JTextArea("AWT: " + AWT);
        textArea2.setEditable(false);
        textArea2.setFont(new Font("Italic", Font.ITALIC, 18));
        textArea2.setForeground(Color.white);
        textArea2.setBackground(Color.DARK_GRAY);
        textArea2.setAlignmentX(Component.LEFT_ALIGNMENT);
        statePanel.add(textArea2);

        textArea3 = new JTextArea("ATAT: " + ATAT);
        textArea3.setEditable(false);
        textArea3.setFont(new Font("Italic", Font.ITALIC, 18));
        textArea3.setForeground(Color.white);
        textArea3.setBackground(Color.DARK_GRAY);
        textArea3.setAlignmentX(Component.LEFT_ALIGNMENT);
        statePanel.add(textArea3);

        southPanel.setLayout(new BorderLayout());
        southPanel.setBackground(Color.DARK_GRAY);
        southPanel.add(statePanel, BorderLayout.WEST);

        panel.add(southPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

    }
}