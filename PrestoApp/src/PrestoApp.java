import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;

/* TO DO LIST
 * 
 * START/STOP & CLEAR shouldn't work if SET is active
 * SET shouldn't work on STOPWATCH
 * Fix SET blinking inconsistency
 * Get CLOCK to update every second
 * Implement military time for CLOCK
 * 
 */


public class PrestoApp extends JFrame {

	private static final long serialVersionUID = 1L;
	public static String mode = "StopWatch";
	public static int timerMode = 1;
	public static int setPlace = 0;
	static int blink = 0;

	// Define constructor for PrestoApp
	public PrestoApp() {

		setTitle("Presto Timer");
		setSize(430, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		//////////////////////////////////////		DANGER TEST CODE
		
		
		
		
		
		
		//////////////////////////////////////
		
	}

	public static void main(String[] args) {

		@SuppressWarnings("unused")
		final TimerMemory timerC = new TimerMemory();
		final Clock clk = new Clock();

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
		
		// Display Setup
		Font hourMin = new Font("DS-Digital", Font.ITALIC, 100);	// Font for hh:mm:ss display
		final JLabel disp = new JLabel();
		disp.setFont(hourMin);
		display.add(disp);
		
		Font date = new Font("DS-Digital", Font.ITALIC, 32);
		final JLabel topLine = new JLabel();					// Font for date
		topLine.setFont(date);
		topLine.setPreferredSize(new Dimension(430,25));
		display.add(topLine, BorderLayout.PAGE_START);
		
		// Add ActionListeners to buttons
		memory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// DO SOMETHING
			}
		});

		down.addActionListener(new ActionListener() {

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
			}
		});

		up.addActionListener(new ActionListener() {

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
				//
			}
		});

		set.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setPlace++;
				if (setPlace > 3) {
					setPlace = 0;
				}
			}
		});

		clock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mode = "Clock";
				//Display date
				topLine.setText(clk.getDate());
			}
		});

		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (mode == "Timer") {
					timerMode++;
					if (timerMode >= 4) {
						timerMode = 1;
					}
				}
				mode = "Timer";
				setPlace=0;
			}
		});

		stopwatch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mode = "StopWatch";
				TimerMemory.clearStop();
			}
		});

		startstop.addActionListener(new ActionListener() {
			// STOPWATCH SPECIFIC
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mode == "StopWatch") {
					TimerMemory.stopSwitch();
				}
				if (mode == "Timer") {
					TimerMemory.switchTimer(timerMode);
				}
			}
		});

		clear.addActionListener(new ActionListener() {
			// STOPWATCH SPECIFIC
			@Override
			public void actionPerformed(ActionEvent e) {
				TimerMemory.clearStop();
			}
		});

		// Display refresh timer
		Timer refresh = new Timer(1,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
		Timer refreshSwitch = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				blink++;
				@SuppressWarnings("unused") // added to suppress warning for dispText below -RWR
				char[] dispText;
				String dispy = disp.getText();
				dispText = disp.getText().toCharArray();
				if(blink%2==0){
					switch (setPlace) {
					case 1:
						disp.setText("   " +dispy.substring(2, dispy.length()));
						break;
					case 2:
						disp.setText(dispy.substring(0, 3)+"   "+dispy.substring(5, dispy.length()));
						break;
					case 3:
						disp.setText(dispy.substring(0, 5)+"   ");
						break;
					}	
				}
			}
		});
		refresh.start();
		refreshSwitch.start();
	}
}