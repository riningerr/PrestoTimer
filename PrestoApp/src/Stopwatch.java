/***********************************************************************************
 * 
 * This class is designed as a stopwatch.
 * 
 * An instance must be created to use this class and its methods.
 * 
 * The methods of interest are:
 * -	getElapsed() - this method provides elapsed time
 * -	startWatch() - this method starts/pauses the stopwatch
 * -	clearWatch() - this method clears/resets the stopwatch
 * 
 * EXAMPLE: Display in an application window
 * 
 *
public class PrestoApp extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// Define constructor for PrestoApp
	public PrestoApp() {
		
		setTitle("Presto Timer");
		setSize(500,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
		public static void main(String[] args) {
			
		// Create instance of Stopwatch (STOPWATCH SPECIFIC)
		Stopwatch sw = new Stopwatch();
		
		// Create new frame
		PrestoApp window = new PrestoApp();
		
		// Create panels for display and buttons
		JPanel display = new JPanel();
		JPanel buttons = new JPanel(new GridLayout(2,4));
		
		// Add panels to container
		Container pane = window.getContentPane();
		pane.add(display, BorderLayout.CENTER);
		pane.add(buttons, BorderLayout.SOUTH);
		
		// Display Setup
		JLabel disp = new JLabel();
		display.add(disp);
		
		// Create buttons
		JButton startstop = new JButton("START/STOP");
		JButton clear = new JButton("CLEAR");
		
		// Add buttons to panel
		buttons.add(startstop);
		buttons.add(clear);
		
		// Add ActionListeners to buttons
		startstop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sw.startWatch();
			}
		});
	
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sw.clearWatch();
			}
		});
		
		// Display refresh timer
		Timer refresh = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				disp.setText(sw.getElapsed());
			}
		});
		refresh.start();
	}
}
 * 
 * @author riningerr
 * 
 * CHANGELOG
 * ---------
 * 
 *
 **********************************************************************************/
public class Stopwatch {

	private long timeStart = 0;
	private long timeNow = 0;
	private long elapsedSec = 0;
	
	// Boolean variables
	public boolean started = false;
	public boolean pause = false;
	
	public Stopwatch(){
		System.out.println("I AM CREATED");
	}
	
	// Start Stopwatch
	public void startWatch() {
		if(started == false) {
			timeStart = System.currentTimeMillis();
			started = true;
		}
		else {
			pause = !pause;
			if(pause == false) {
				timeStart = timeNow;
			}
		}
	}
	
	// Clear Stopwatch
	public void clearWatch() {
		if(pause == true) {
			// Reset time values
			timeStart = 0;
			timeNow = 0;
			// Reset booleans
			started = false;
			pause = false;
		}
	}
	
	// Get Time
	public String getElapsed() {
		if(started == true) {
			if(pause == false) {
				timeNow = System.currentTimeMillis();
			}
//			elapsedSec = (timeNow - timeStart)/1000; 
//			String elapsedTime = Convert.getFormatted((int) elapsedSec);
//			return elapsedTime;
		}
			elapsedSec = (timeNow - timeStart)/1000; 
			String elapsedTime = Convert.getFormatted((int) elapsedSec);
			return elapsedTime;
	}
}