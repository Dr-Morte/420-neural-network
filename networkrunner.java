//Richard Padilla
//722002479
//CSCE 420
//Due: April 23, 2018
//networkrunner.java

//code loosely based on a project done in CSCE 315, with permission of Dr. Daugherity

import java.util.*;

class networkrunner{
	
	public static void main(String[] args){
		
		//Hard coded arrays for testing purposes
		double [] ina = {0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1};
		double [] inb = {1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0};				 
		double [] inc = {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0};				 
		double [] ind = {1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0};
		double [] ine = {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1};
		double [] inf = {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0};
		double [] ing = {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1};
		double [] inh = {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1};
		double [] ini = {0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0};
		double [] inj = {0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0};
		double [] ink = {1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1};
		double [] inl = {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1};
		double [] inm = {1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1};
		double [] inn = {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1};
		double [] ino = {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0};
		double [] inp = {1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0};
		double [] inq = {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1};
		double [] inr = {1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1};
		double [] ins = {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0};
		double [] inT = {1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0};
		double [] inu = {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0};
		double [] inv = {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0};
		double [] inw = {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
		double [] inx = {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1};
		double [] iny = {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0};
		double [] inz = {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1};
		
		//array to store user input in
		double[] userIN = new double[35];
		
		//creates Network and trains it
		neuralnet hal = new neuralnet();
		hal.learn();
		System.out.println("Training complete.\n\n\n");
		
		//welcome text
		System.out.println("Hello, I am HAL, a neural network built by Richard Padilla III.\n");
		System.out.println("I accept 5x7 letters in a 1x35 string of 0's and 1's.");
		System.out.println("Here is an example for the letter C: 0, 1, 1, 1, 0,1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0\n\n");
		System.out.println("which in a grid, matches the C from the Project specification: \n");
		System.out.println("0, 1, 1, 1, 0,\n1, 0, 0, 0, 1,\n1, 0, 0, 0, 0,\n1, 0, 0, 0, 0,\n1, 0, 0, 0, 0,\n1, 0, 0, 0, 1,\n0, 1, 1, 1, 0\n");
		System.out.println("If you need the 1x35 strings for any of the letters, I have provided them in 'testcases.txt'\n\n\n");
		
		//SCANNER TO GET USER INPUT
		Scanner in = new Scanner(System.in);
		String strUserIN = "";
		
		//loops until user is done with the program
		while(!strUserIN.equals("quit")){
			System.out.println("Please input a letter or 'quit' to end program: ");
			strUserIN = in.nextLine();
			
			if(strUserIN.equals("quit")){
				//quits program
			}
			else{
				//converts input line to an array for the network to use		
				int index = 0;	//counter for array index
				for(int i = 0; i < strUserIN.length();i++){
			
					//puts the input into the array
					if(strUserIN.charAt(i) == '0' || strUserIN.charAt(i) == '1'){
						userIN[index] = strUserIN.charAt(i)-48;
						index++;
					}
			
				}
					//evaluates the user input
					hal.post_trained_forward(userIN);
					hal.printoutput("user input ");
			}
			
		}	
		
			
			//finds the noise tolerance for a given letter
			hal.noise(ina,0,"a");
			hal.noise(inb,1,"b");
			hal.noise(inc,2,"c");
			hal.noise(ind,3,"d");
			hal.noise(ine,4,"e");
			hal.noise(inf,5,"f");
			hal.noise(ing,6,"g");
			hal.noise(inh,7,"h");
			hal.noise(ini,8,"i");
			hal.noise(inj,9,"j");
			hal.noise(ink,10,"k");
			hal.noise(inl,11,"l");
			hal.noise(inm,12,"m");
			hal.noise(inn,13,"n");
			hal.noise(ino,14,"o");
			hal.noise(inp,15,"p");
			hal.noise(inq,16,"q");
			hal.noise(inr,17,"r");
			hal.noise(ins,18,"s");
			hal.noise(inT,19,"t");
			hal.noise(inu,20,"u");
			hal.noise(inv,21,"v");
			hal.noise(inw,22,"w");
			hal.noise(inx,23,"x");
			hal.noise(iny,24,"y");
			hal.noise(inz,25,"z");
			
	}
}