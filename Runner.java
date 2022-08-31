import java.io.*;
import java.util.ArrayList;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Runner {
	static int arean = 0;
	static String manager = "";
	static String mm = "00";
	static String dd = "00";
	static String yyyy = "0000";
	static int bumber = 1;
	static String docPath = "";
	static int lateState = 0;
	
	public Runner() {
		//create swing elements
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton go = new JButton("Run");
		JLabel namesPV = new JLabel("Guards Preveiew:");
		JButton getNames = new JButton("Check Names");
		JTextArea names = new JTextArea(); names.setLineWrap(true); //note 2 lines on one
		JLabel managerLabel = new JLabel("Manager Submitting:");
		JTextField managerField = new JTextField();
		JLabel areaLabel = new JLabel("Area:");
		JRadioButton grayson = new JRadioButton("Grayson");
		JRadioButton hmill = new JRadioButton("Hamilton Mill");
		JRadioButton nofu = new JRadioButton("North Fulton");
		JRadioButton ng = new JRadioButton("North Gwinnett");
		JRadioButton ptc = new JRadioButton("Peachtree Corners");
		JRadioButton sofo = new JRadioButton("South Forsyth");
		JRadioButton sh = new JRadioButton("South Hall");
		JRadioButton wefo = new JRadioButton("West Forsyth");
		ButtonGroup area = new ButtonGroup();
		JTextField month = new JTextField();
		JTextField day = new JTextField();
		JTextField year = new JTextField();
		JLabel monthL = new JLabel("Month (MM):");
		JLabel dayL = new JLabel("Day (DD):");
		JLabel yearL = new JLabel("Year (YYYY):");
		JLabel insNumL = new JLabel("Which inservice is this?");
		JTextField insNum = new JTextField();
		JButton chooseFile = new JButton("Choose Inservice Doc CSV");
		JLabel filePath = new JLabel("");
		JLabel lateAbs = new JLabel("Late or Absent?");
		JRadioButton late = new JRadioButton("Late");
		JRadioButton abs = new JRadioButton("Absent");
		ButtonGroup lateAbsG = new ButtonGroup();
		
		//add radio buttons to group
		area.add(grayson);
		area.add(hmill);
		area.add(nofu);
		area.add(ng);
		area.add(ptc);
		area.add(sofo);
		area.add(sh);
		area.add(wefo);
		
		lateAbsG.add(abs);
		lateAbsG.add(late);
		
		//action listener
		ActionListener listen = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == getNames) {
					bumber = Integer.parseInt(insNum.getText());
					names.setText(ALS(getNames()));
				}
				if(e.getSource() == go) {
					manager = managerField.getText();
					mm = month.getText();
					dd = day.getText();
					yyyy = year.getText();
					bumber = Integer.parseInt(insNum.getText());
					input(getNames(), arean);
				}
				if(e.getSource() == grayson) {
					arean = 1;
				}
				if(e.getSource() == hmill) {
					arean = 2;
				}
				if(e.getSource() == nofu) {
					arean = 3;
				}
				if(e.getSource() == ng) {
					arean = 4;
				}
				if(e.getSource() == ptc) {
					arean = 5;
				}
				if(e.getSource() == sofo) {
					arean = 6;
				}
				if(e.getSource() == sh) {
					arean = 7;
				}
				if(e.getSource() == wefo) {
					arean = 8;
				}
				if(e.getSource() == chooseFile) {
					JFileChooser fileChooser = new JFileChooser();
					if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						File doc = new File(fileChooser.getSelectedFile().getAbsolutePath());
						docPath = doc.toString();
						filePath.setText(docPath);
						chooseFile.setText("Change File");
					}
					
				}
				if(e.getSource() == late) {
					lateState = 2;
				}
				if(e.getSource() == abs) {
					lateState = 1;
				}
				
			}
		};
		
		//add file chooser
		chooseFile.addActionListener(listen);
		
		//add radio events
		grayson.addActionListener(listen);
		hmill.addActionListener(listen);
		nofu.addActionListener(listen);
		ng.addActionListener(listen);
		ptc.addActionListener(listen);
		sofo.addActionListener(listen);
		sh.addActionListener(listen);
		wefo.addActionListener(listen);
		
		late.addActionListener(listen);
		abs.addActionListener(listen);
		
		//add button events
		go.addActionListener(listen);
		getNames.addActionListener(listen);
		//panic.addActionListener(listen);
		
		//formatting
		panel.setBorder(BorderFactory.createEmptyBorder());
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		//c.insets = new Insets();
		
		//add choose file to panel
		c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(chooseFile,c);
		c.gridx = 1;
	    c.gridy = 0;
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(filePath,c);
		
		//add radio buttons to panel
		c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(areaLabel, c);
		c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(grayson,c);
		c.gridx = 0;
	    c.gridy = 3;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(hmill,c);
		c.gridx = 0;
	    c.gridy = 4;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(nofu, c);
		c.gridx = 0;
	    c.gridy = 5;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(ng, c);
		c.gridx = 0;
	    c.gridy = 6;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(ptc, c);
		c.gridx = 0;
	    c.gridy = 7;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(sofo, c);
		c.gridx = 0;
	    c.gridy = 8;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(sh, c);
		c.gridx = 0;
	    c.gridy = 9;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(wefo, c);
		
		//add date fields
		c.gridx = 0;
	    c.gridy = 10;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0;
		panel.add(monthL, c);
		c.gridx = 0;
	    c.gridy = 11;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0;
		panel.add(month,c);
		c.gridx = 1;
	    c.gridy = 10;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
		panel.add(dayL,c);
		c.gridx = 1;
	    c.gridy = 11;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
		panel.add(day,c);
		c.gridx = 2;
	    c.gridy = 10;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
		panel.add(yearL,c);
		c.gridx = 2;
	    c.gridy = 11;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
		panel.add(year,c);
		
		//add manager text field
		c.gridx = 0;
	    c.gridy = 12;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(managerLabel,c);
		c.gridx = 0;
	    c.gridy = 13;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(managerField,c);
		
		//add inservice number
		c.gridx = 0;
	    c.gridy = 14;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(insNumL,c);
		c.gridx = 0;
	    c.gridy = 15;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
	    panel.add(insNum,c);
		
	    //add tier violation type selector
	    c.gridx = 0;
	    c.gridy = 16;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(lateAbs,c);
		c.gridx = 0;
	    c.gridy = 17;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
	    panel.add(abs,c);
	    c.gridx = 0;
	    c.gridy = 18;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
	    panel.add(late,c);
	    
	    //add names text box
		c.gridx = 0;
	    c.gridy = 19;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		panel.add(namesPV,c);
		c.gridx = 0;
	    c.gridy = 20;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1;
		panel.add(names,c);
		
		//add buttons
		c.gridx = 0;
	    c.gridy = 21;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.5;
		panel.add(getNames,c);
		c.gridx = 1;
	    c.gridy = 21;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.5;
		panel.add(go,c);
		//PANIC
		/*c.gridx = 0;
	    c.gridy = 19;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
		panel.add(panic,c);*/
		
		//add everything to window
		frame.add(panel, BorderLayout.PAGE_START);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Inservice Auto Violator");
		frame.setResizable(false);
		frame.pack();
		frame.setSize(500, 650);
		frame.setVisible(true);
	}
	

	public static void main(String args[]){
		new Runner();
	}
	
	public static ArrayList<String> getNames() {
		String l;
		try {
			//creates list of names for robot to write up
			ArrayList<String> a = new ArrayList<String>();
			BufferedReader count = new BufferedReader(new FileReader(docPath));
			
			//count lines in document
			int lines = 0;
			while(count.readLine() != null) {
				lines++;
			}
			count.close();
			
			//
			BufferedReader b = new BufferedReader(new FileReader(docPath));
			for(int i = 0; i < 20; i++) {
				b.readLine();
			}
			if(lateState == 1) {
				String abs;
				for(int i = 0; i < lines-20; i++) {
					l = b.readLine();
					String[] line = l.split(",");
					if(line.length < 3 + bumber*2 - 1) {
						abs = line[0] + " " + line[1];
						System.out.println(abs);
						a.add(abs);
					}
				}
				b.close();
			}
			if(lateState == 2) {
				String abs;
				for(int i = 0; i < lines-20; i++) {
					l = b.readLine();
					String[] line = l.split(",");
					if(line.length == 3 + bumber*2 - 1) {
						if(line[3 + bumber*2 - 2].equals("LATE")) {
							abs = line[0] + " " + line[1];
							System.out.println(abs);
							a.add(abs);
						}
					}
				}
				b.close();
			}
			return a;
		}catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
	
	public static String ALS(ArrayList<String> AL) {
		String out = "";
		for(int i = 0; i < AL.size(); i++) {
			out = out + AL.get(i) + ", ";
		}
		return out;
	}
	
	public static void input(ArrayList<String> a, int area) {
		try {
			//repeated strings
			//String mm = "06";
			//String dd = "02";
			//String yyyy = "2022";
			String reason1 = "inservice no show";
			String reason2 = "late to inservice";
			//String me = "Charles Phillips";
			
			//robot inputs names into tier violation form
			Robot r = new Robot();
			r.delay(5000);
			for(int i = 0; i < a.size(); i++) {
				for(int j = 0; j < 3; j++) {
					tab(r);
					r.delay(500);
				}
				for(int j = 1; j < arean; j++) {
					r.keyPress(KeyEvent.VK_DOWN);
					r.delay(250);
				}
				r.delay(500);
				tab(r);
				stringType(mm,r);
				r.delay(500);
				stringType(dd,r);
				r.delay(500);
				stringType(yyyy,r);
				r.delay(500);
				tab(r);
				stringType(a.get(i),r);
				r.delay(500);
				tab(r);
				if(lateState == 1) {
					stringType(reason1,r);
				}
				if(lateState == 2) {
					stringType(reason2,r);
				}
				r.delay(500);
				tab(r);
				stringType(manager,r);
				r.delay(500);
				tab(r);
				tab(r);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				r.delay(2000);
				tab(r);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				r.delay(2000);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static void stringType(String s, Robot r) {
		String cur = s;
		char c;
		int d = cur.length(), e = 0, f = 0;
		while(e < d) {
			c = cur.charAt(e);
			f = (int) c;
			r.keyPress(KeyEvent.getExtendedKeyCodeForChar(f));
			r.keyRelease(KeyEvent.getExtendedKeyCodeForChar(f));
			e++;
		}
	}
	
	public static void tab(Robot r) {
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
	}

}


