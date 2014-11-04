package view;
/**
 * 
 * @author wat wattanagaroon
 * @version 2014/9/10
 */
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import controller.Controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Table;

import javax.swing.JProgressBar;
import javax.xml.ws.WebServiceException;


public class Gui extends JFrame{
	private JTextField textField;
	private String input;
	private Controller controller;
	private JTable tablePane;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private Working work;
	private JProgressBar progressBar;
	private boolean isRun = false;
	private JButton submitButt;
	
	public Gui() {
		super("WWW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{

			controller = new Controller();
		} catch (WebServiceException e){

			NotiEror();
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		
		submitButt = new JButton("Submit");
		submitButt.setBounds(257, 53, 117, 29);
		panel.add(submitButt);
		
		JLabel lblNewLabel_1 = new JLabel("Some");
		lblNewLabel_1.setBounds(110, 0, 166, 34);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(56, 52, 189, 28);
		panel.add(textField);
		textField.setColumns(10);
		String col[] = {"City"};
		tableModel = new DefaultTableModel(col,0);
		tablePane = new JTable(tableModel);
//		tablePane.setBounds(56, 121, 318, 107);
		scrollPane = new JScrollPane(tablePane);
		scrollPane.setBounds(56, 121, 318, 107);
		panel.add(scrollPane);
//		panel.add(tablePane);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(66, 92, 146, 20);
		panel.add(progressBar);
		
		JLabel lblProgress = new JLabel("Progress");
		lblProgress.setBounds(227, 93, 61, 16);
		panel.add(lblProgress);
		
		submitButt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isRun){// false gonna work
				progressBar.setValue(0);
				input = textField.getText().toString();	
				work = new Working();
				work.execute();
				submitButt.setText("Cancel");
				isRun = true;
				}
				else{// cancel
					submitButt.setText("Submit");
					progressBar.setValue(0);
					work.cancel(true);
					System.out.println("EIEIEIEEI");
					isRun = false;
				}
			System.out.println(isRun);
				
			}
		});
		

	}
	/**
	 * If it catch it will come to this method and notify 
	 */
	private void NotiEror() {
		JOptionPane.showMessageDialog(null, "No internet Connection \n ","Error",JOptionPane.ERROR_MESSAGE);
		setEnabled(false);
	}
	/**
	 * result of soap will show
	 */
	private void response(){
		try{
		tableModel.setRowCount(0);
		progressBar.setValue(80);
		if(controller.getList() != null){
			Table[] list = controller.getList();
			for(Table table : list){
				String row[] = {table.getCity()};
				tableModel.addRow(row);
			}
		}
		else{
			String result[] = {"No Result"};
			tableModel.addRow(result);
		}
		isRun = false;
		progressBar.setValue(100);
		submitButt.setText("Submit");
		}catch (WebServiceException e){
			NotiEror();
		}
	}
	
	public void run() {
		setSize(new Dimension(450, 350));
		setVisible(true);
	}
	
	class Working extends SwingWorker<List<Table>, String>{

		@Override
		protected List<Table> doInBackground() throws Exception {
			progressBar.setValue(50);
			controller.connect(input);
			return null;
		}

		@Override
		protected void done() {
			response();
			super.done();
		}
		
	}
}
