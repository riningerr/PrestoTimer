/*
 * Main application and action listeners written by Jimmy Phan and Steve Gansop
 * Display setup written by Richard Rininger
 * "Blink-while-set" written by Maged Hamdy
 * Up/Down written by Maged Hamdy
 * 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

/* TO DO LIST
 * ---------------------------------------------------------------
 * ALL DONE :)
 * ---------------------------------------------------------------
 * 
 * 
 */

public class PrestoApp extends JFrame {

	private static final long serialVersionUID = 1L;
	public static String mode = "Clock";
	public static int timerMode = 1;
	public static int setPlace = 0;
	static int blink = 0;

	// Define constructor for PrestoApp
	public PrestoApp() {

		setTitle("Presto Timer");
		setSize(450, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
	}

	public static void main(String[] args) {

		@SuppressWarnings("unused")
		final TimerMemory timerC = new TimerMemory();
		final Clock clk = new Clock();								// Instance of Clock class

		// Create new frame
		PrestoApp window = new PrestoApp();

		// Create panels for display and buttons
		JPanel display = new JPanel(new BorderLayout());
		JPanel buttons = new JPanel(new GridLayout(2, 4));
		

		// Add panels to container
		Container pane = window.getContentPane();
		pane.add(display, BorderLayout.CENTER);
		pane.add(buttons, BorderLayout.SOUTH);
		
		
		// Create buttons
		JButton memory = new JButton("MEMORY");
		JButton down = new JButton("DOWN");
		JButton up = new JButton("UP");
		JButton set = new JButton("SET");
		JButton clock = new JButton("CLOCK");
		JButton timer = new JButton("TIMER");
		JButton stopwatch = new JButton("STOPWATCH");
		JButton startstop = new JButton("START/STOP");
		JButton clear = new JButton("CLEAR");
		
		// Add buttons to panel
		buttons.add(memory);
		buttons.add(down);
		buttons.add(up);
		buttons.add(set);
		buttons.add(clock);
		buttons.add(timer);
		buttons.add(stopwatch);
		buttons.add(startstop);
		
		display.add(clear, BorderLayout.PAGE_END);
		
		// Display Setup (Ricky Rininger)
		Font dispFont = new Font("DS-Digital", Font.ITALIC, 100);	// Font for hh:mm:ss display
		final JLabel disp = new JLabel();							// Main time display
		disp.setFont(dispFont);
		display.add(disp);
		
		Font dateFont = new Font("DS-Digital", Font.ITALIC, 32);	// Font for date
		final JLabel topLine = new JLabel();						// Date/Day display
		topLine.setFont(dateFont);
		topLine.setPreferredSize(new Dimension(430,25));			// Leaves space even when nothing is displayed
		display.add(topLine, BorderLayout.PAGE_START);
		
		Font sideFont = new Font("DS-Digital", Font.ITALIC, 22);	// Font for side display
		final JLabel side = new JLabel();							// side display for timer mode, etc
		side.setFont(sideFont);
		side.setPreferredSize(new Dimension(75,150));
		display.add(side, BorderLayout.LINE_END);
		
		// Add ActionListeners to buttons
		memory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (mode == "Timer") {
					timerMode++;
					if (timerMode >= 5) {
						timerMode = 1;
					}
				}
			}
		});

		down.addActionListener(new ActionListener() {
			//adds values dwon to the max then resets if it goes under, i.e. 0 + down = 59
			//Maged
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mode == "Timer") {
					switch (setPlace) {
					case 1:
						if (TimerMemory.getTimer(timerMode)/3600 <= 0) {
							TimerMemory.addTimer(timerMode, 86400);
						}
						TimerMemory.addTimer(timerMode, -3600);
						break;
					case 2:
						if (((TimerMemory.getTimer(timerMode) % 3600) / 60) == 0) {
							System.out.println(TimerMemory.getTimer(timerMode) % 3600 / 60);
							TimerMemory.addTimer(timerMode, 3600);
						}
						TimerMemory.addTimer(timerMode, -60);
						break;
					case 3:
						TimerMemory.addTimer(timerMode, -1);
						if ((TimerMemory.getTimer(timerMode) % 60) == 59) {
							TimerMemory.addTimer(timerMode, 60);
						}
						break;
					}
				}
				if(mode=="Clock"){
					clk.removeDay();
				}
			}
		});

		up.addActionListener(new ActionListener() {
			//adds values up to the max then resets if it goes over, i.e. 59 + up = 0;
			//Maged
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mode == "Timer") {
					switch (setPlace) {
					case 1:
						TimerMemory.addTimer(timerMode, 3600);
						if (TimerMemory.getTimer(timerMode) >= 86400) {
							TimerMemory.addTimer(timerMode, -86400);
						}
						break;
					case 2:
						TimerMemory.addTimer(timerMode, 60);
						if (((TimerMemory.getTimer(timerMode) % 3600) / 60) == 0) {
							TimerMemory.addTimer(timerMode, -3600);
						}
						break;
					case 3:
						TimerMemory.addTimer(timerMode, 1);
						if ((TimerMemory.getTimer(timerMode) % 60) == 0) {
							TimerMemory.addTimer(timerMode, -60);
						}
						break;
					}
				}
				if(mode=="Clock"){
					switch(setPlace){
					case 1:
						clk.addYear();
						System.out.println("YEAR");
						break;
					case 2:
						clk.addMonth();
						break;
					case 3:
						clk.addDay();
						break;
					case 4:
						clk.addHour();
						break;
					case 5:
						clk.addMinute();
						break;
					case 6:
						clk.addSecond();
						break;
				}
				}
				//
			}
		});

		set.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(mode != "StopWatch") {
					setPlace++;
					if(mode!="Clock"){
					if (setPlace > 3) {
						setPlace = 0;
					}
					}if(mode=="Clock"){
						//Clock Mode has a different max since it has Day/Month/Year
						if (setPlace > 6) {
							setPlace = 0;
						}
						}
				}
			}
		});

		clock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Toggle military mode -RWR
				if(mode == "Clock") {
					clk.military();
				}
				// Set mode
				mode = "Clock";
			}
		});

		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mode = "Timer";
				setPlace=0;
			}
		});

		stopwatch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mode = "StopWatch";
			}
		});

		startstop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(setPlace == 0) {
					if (mode == "StopWatch") {
						TimerMemory.stopSwitch();
					}
					if (mode == "Timer") {
						TimerMemory.switchTimer(timerMode);
					}
				}
			}
		});

		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Clear Stopwatch
				if(mode == "StopWatch") {
					if(TimerMemory.stopOn != true) {					
					TimerMemory.clearStop();
					}
				}
				// Clear Timer
				else if(mode == "Timer") {			
					if(setPlace == 0) {
						TimerMemory.clearTimer(timerMode);
					}
				}
			}
		});

		// Display refresh timer
		Timer refresh = new Timer(100,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Top Display	-RWR
				if(mode == "Clock") {
					topLine.setText(clk.getDate());
				}
				else {
					topLine.setText("");
				}
				
				// Side Display	-RWR
				if(mode == "Timer") {
					side.setText("TIMER");
					topLine.setText("                                                 " + timerMode);
				}
				else {
					side.setText("");
				}

				// Main display	-Maged
				if(blink%12!=0){
					String dispText = "";
					if(mode == "StopWatch") {
						disp.setText(Convert.getFormatted(TimerMemory.getStop()));
					}
					if(mode == "Timer") {
						dispText = Convert.getFormatted(TimerMemory.getTimer(timerMode));
						disp.setText(dispText);
					}
					if(mode == "Clock") {
						clk.updateClock();
						disp.setText(clk.getTime());
					}
				}
			}
		});
		// Blink-while-set 		-Maged
		Timer refreshSwitch = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				blink++;
				@SuppressWarnings("unused")
				char[] dispText;
				String dispy = disp.getText();
				dispText = disp.getText().toCharArray();
				if(mode!="Clock"){
				if(blink%10==0){
					switch (setPlace) {
					case 1:
						disp.setText("    " +dispy.substring(2, dispy.length()));
						break;
					case 2:
						disp.setText(dispy.substring(0, 3)+"   "+dispy.substring(5, dispy.length()));
						break;
					case 3:
						disp.setText(dispy.substring(0, 5)+"    ");
						break;
					}
				}
				}
				//Clock mode has a different set of rules because of Day/Month/Year too
				if(mode=="Clock"){
				if(blink%10==0){
					String date = clk.getDate();
					switch (setPlace) {
					case 1:
						date = "   " +date.substring(2);
						break;
					case 2:
						date=date.substring(0, 3)+"  "+date.substring(5);
						break;
					case 3:
						date=date.substring(0,6)+"      "+date.substring(10);
						break;
					case 4:
						disp.setText("    " +dispy.substring(2, dispy.length()));
						break;
					case 5:
						disp.setText(dispy.substring(0, 3)+"   "+dispy.substring(5, dispy.length()));
						break;
					case 6:
						disp.setText(dispy.substring(0, 5)+"    ");
						break;
					}
					topLine.setText(date);
					}
				}
				}
		});
		refresh.start();
		refreshSwitch.start();
	}
}