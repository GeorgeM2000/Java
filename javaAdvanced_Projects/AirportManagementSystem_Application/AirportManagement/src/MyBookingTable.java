import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class MyBookingTable extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	public JTable table = new JTable();

	
	public MyBookingTable() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 967, 425);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(10, 11, 931, 364);
		panel.add(scrollPanel);
		
		scrollPanel.setViewportView(table);
	}
}
