//Executes every second

//Written by Maged Hamdy

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class TimerAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
			TimerMemory.time++;
		if(TimerMemory.stopOn){
			if(TimerMemory.stopwatch<86400){
				TimerMemory.stopwatch+=1;
			}
		}
		//System.out.println(Convert.getHours(TimerMemory.stopwatch)+":"+Convert.getMinutes(TimerMemory.stopwatch)+":"+Convert.getSeconds(TimerMemory.stopwatch));
		//for every memory slot
		for(int i=0;i<4;i++){
			//is the timer ticking?
			if(TimerMemory.memoryOn[i]){
				//is the memory going up (already reached 0)
				if(TimerMemory.memoryUp[i]){
					//count goes up
					TimerMemory.memory[i]++;
				}else{
					//count goes down
					TimerMemory.memory[i]--;
				}
				//if memory is 0
				if(TimerMemory.memory[i]==0&&!TimerMemory.memoryUp[i]){
					//System.out.println("TIMER "+ (i+1) + " WENT OFF HOLY MOLY SHIT");
					//memory is up since we reached 0
					TimerMemory.memoryUp[i]=true;
					//restart the beeper to 0 (go for another 60 seconds)
					TimerMemory.beep=0;
				}
			}
				if(TimerMemory.memory[i]>=86400){
					System.out.println("TOO BIG");
					TimerMemory.memory[i]=86399;
				}
			}
		//this controls the beeper, shoots every second
		TimerMemory.beep+=1;
		if(TimerMemory.beep<=60){
			if(TimerMemory.beep%1==0){
				try {
					TimerMemory.playSound();
				} catch (IOException e) {
					e.printStackTrace();
				}
				}
			}
		}
	}