//Written by Maged Hamdy [timer] + Brian Figueroa [sound]

import java.io.IOException;
import javax.swing.Timer;
import sun.audio.*;
import java.io.*;

//Applet is only for testing purposes

public class TimerMemory {
	public static int time = 0;
	public static int[] memory = {0,0,0,0};
	public static boolean[] memoryOn = {false,false,false,false};
	public static boolean[] memoryUp = {false,false,false,false};
	public static int stopwatch = 0;
	public static int beep = 60;
	public static boolean stopOn=false;
	
	public TimerMemory(){
		Timer timer = new Timer(1000, new TimerAction());
		timer.start();
		//Test Stuff, all things should be assigned
		memory[0] = 0;
		memory[1] = 0;
		memory[2] = 0;
		memory[3] = 0;
		memoryOn[0] = false;
		memoryOn[1] = false;
		memoryOn[2] = false;
		memoryOn[3] = false;
		memoryUp[0] = false;
		memoryUp[1] = false;
		memoryUp[2] = false;
		memoryUp[3] = false;
	}

	public static int getTimer(int timer) {
		//returns seconds on the timer
		return memory[timer - 1];
	}
	
	public static void addTimer(int timer,int add) {
		//returns seconds on the timer
		memory[timer - 1]+=add;
	}

	public static void setTimer(int timer,int set) {
		//returns seconds on the timer
		memory[timer - 1]=set;
	}

	public static void startTimer(int timer) {
		//whenever timer starts, timerUp is ALWAYS set to off
		memoryOn[timer - 1] = true;
		memoryUp[timer - 1] = false;
	}

	public static void stopTimer(int timer) {
		//stops the timer
		memoryOn[timer - 1] = false;
	}
	
	public static void switchTimer(int timer){
		memoryOn[timer-1]=!memoryOn[timer-1];
		if(memoryOn[timer-1]==true){
		memoryOn[timer - 1] = true;
		memoryUp[timer - 1] = false;
		}else{
			beep=60;
		}
	}

	public static void clearTimer(int timer) {
		//clears the timer
		memory[timer - 1] = 0;
	}

	public static synchronized void playSound() throws IOException {
		//plays the sound
		InputStream in = new FileInputStream("beep.wav");
		AudioStream as = new AudioStream(in);
		AudioPlayer.player.start(as);
	}
	public static void stopOn(){
		stopOn=true;
	}
	public static void stopOff(){
		stopOn=false;
	}
	public static void stopSwitch(){
		stopOn=!stopOn;
	}
	public static void clearStop(){
		stopOn=false;
		stopwatch=0;
	}
	public static int getStop(){
		return stopwatch;
	}
}
