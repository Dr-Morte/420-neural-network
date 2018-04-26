//Richard Padilla
//722002479
//CSCE 420
//Due: April 23, 2018
//neuralnet.java


import java.util.*;
import java.lang.Math.*;
import java.io.*;

class neuralnet{
	
	//THESE ARE MATRICIES FOR LEARNING
	double[][] w1 = new double[35][32];				//weights between input and l1
	double[][] z2 = new double[26][32];				//layer 1 before activation
	double[][] a2 = new double[26][32];				//layer 1 after activation
	double[][] w2 = new double[32][26];				//weights between l1 and output
	double[][] z3 = new double[26][26];				//output before activation
	double[][] a3 = new double[26][32];				//layer 2 after activation
	double[][] yhat = new double[26][26];			//guestimation output from forward
	
	double[][] out_delta = new double[26][26];		//output delta for back propagation
	double[][] l1_delta = new double[26][32];		//layer 1 delta for back propagation
	
	double alpha = 0.07;							//learning rate alpha
	
	
	//Training Input Data
	double [][] input = {{0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1}, 					
						{1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0},				 
						{0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0},				 
						{1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0},
						{1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
						{0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1},
						{1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1},
						{0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0},
						{0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0},
						{1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1},
						{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
						{1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1},
						{0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0},
						{1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
						{0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1},
						{1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1},
						{0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0},
						{1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
						{1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0},
						{1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
						{1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
						{1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
						{1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1}};
	
	//Code to rotate input if needed
	//double[][] rotated_input = rotate(input);
				   
	//Training output Data
	double[][] output = {{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
						{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
						{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
						{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
						{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
						{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
						{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},	
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},	
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},	
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},	
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},	
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},	
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}};
	
	
	//MATRICIES FOR POST TRAINED NETWORK
	double[][] post_input = new double[1][35];
	double[][] post_output = new double[1][26];
	
	
	//constructor to set up neural network
	public neuralnet(){
		populate(w1);
		populate(w2);
	}
	
	//This is the forward propagation for training
	public void forward(){
		
		//gets the layer 1 values before activation
		z2 = dotp(input,w1);
		
		//applies activation to layer 1
		a2 = tan_activation(z2);
		
		//gets the output layer values before activation
		z3 = dotp(a2,w2);
		
		//applies activation to output layer
		yhat = sig_activation(z3);
		
		
		//System.out.print("This is yhat: \n\n");
		//printmatrix(yhat);
	}
	
	//This is the backward propagation for training
	public void backward(){
		
		//calculating the deltas for output layer and hidden layer
		out_delta = matrix_mult(sig_deriv(z3),matrix_subtr(output,yhat));
		l1_delta = matrix_mult(tan_deriv(z2),dotp(out_delta,transpose(w2)));
		
		//updating weights for w2
		for(int k = 0; k < z2.length; k++){
			for(int i = 0; i < w2.length; i++){
				for(int j = 0; j < w2[0].length; j++){
					w2[i][j] += alpha * z2[k][i]* out_delta[k][j];
				}
			}
		}
		
		
		//updating weights for w1
		for(int k = 0; k < input.length;k++){
			for(int i = 0; i < w1.length; i++){
				for(int j = 0; j < w1[0].length; j++){
					w1[i][j] += alpha * input[k][i] * l1_delta[k][j];
				}
			}
		}
	}
	
	
	//function to train the neural net. Forward and backward.
	public void learn(){
		//outputs accuracy to output.txt. 
		try{
			BufferedWriter wr = new BufferedWriter(new FileWriter("output.txt"));
			System.out.println("\n\nNeural Network is Learning.\n\nThis should take roughly 10 seconds...\n");
		 
		 
			//CHART FOR LEARNING RATE OF 1.5
		 
			// CHART FOR LEARNING RATE OF 0.01
			//1,000   iterations is about  20-70% accurate Takes roughly 1.5 seconds
			//10,000  iterations is about     96% accurate Takes roughly 10 seconds
			for(int i = 0; i < 400; i++){
				for(int j = 0; j<10;j++){
					forward();
					backward();
				}	
				System.out.println("Accuracy for iteration " + i + " is : " + accuracy() + " \n\n");
				wr.write(accuracy() + "\n");	
			}
		    wr.close();
		}
		catch(IOException ioe){
			System.out.println(ioe);
		}	
	}
	
	//forward propagation for post-trained network
	public void post_trained_forward(double[] in){
		
		//converts the input array into a matrix
		clone(in,post_input);
		
		//gets the layer 1 values before activation
		z2 = dotp(post_input,w1);
		
		//applies activation to layer 1
		a2 = tan_activation(z2);
		
		//gets the output layer values before activation
		z3 = dotp(a2,w2);
		
		//applies activation to output layer
		post_output = sig_activation(z3);
	}
	
	//Below are the list of helper functions I made
	
	//function to calculate noise tolerance
	public void noise(double[] in, int num, String let){
		
		double[] temp = in;		//allows input to be modified
		boolean[] checked = new boolean[35];
		double highest = 0.0; //keeps track of highest output
		int index = -1;	 //keeps track of index of highest output
		int tab = 0; 	//keeps track of tabulations
		Random r = new Random();	//allows random selection of bit to be flipped
		int flip = 0;
		
		//flips a bit at random
		for(int i = 0; i<35; i++){
			flip = r.nextInt(34);
			highest = 0;
			index = -1;
			
			if(!checked[flip]){
				if(temp[flip] == 0){
					temp[flip] = 1;
					checked[flip] = true;
				}
				else if(temp[flip] == 1){
					temp[flip] = 0;
					checked[flip] = true;
				}
			}
			
			//propagates the modified data
			post_trained_forward(temp);
			
				//finds the highest output for this propagation
				for(int k = 0; k < post_output[0].length;k++){
					if(highest < post_output[0][k]){
						highest = post_output[0][k];
						index = k;
					}
					
				}
				//increases if the network still recognizes the letter
				if(index == num){
					tab++;
				}
		}
		
		//prints the number of bits flipped before network failed to recognize letter
		System.out.println("# of tabulations for " + let + ": " + tab);
	}
	
	//function to calculate how accurate the Network is after X iterations
	public double accuracy(){
		
		double sum = 0;
		double avg = 0;
		
		//sum the output accuracies
		for(int i = 0; i < yhat.length; i++){
				sum += yhat[i][i];
		}
		//find the average
		avg = sum/(yhat.length);
		
		return avg;
	}
	
	//helper function for element-wise multiplication
	private double[][] matrix_mult(double[][]in,double[][]w){
		double[][] out = new double[in.length][in[0].length];
		for(int i = 0; i < in.length;i++){
			for(int j = 0; j < in[i].length; j++){
				out[i][j] = in[i][j] * w[i][j];
			}
		}
		return out;
	}
	
	//helper function for matrix subtraction
	private double[][] matrix_subtr(double[][]in,double[][]w){
		double[][] out = new double[in.length][in[0].length];
		for(int i = 0; i < in.length;i++){
			for(int j = 0; j < in[i].length; j++){
				out[i][j] = in[i][j] - w[i][j];
			}
		}
		return out;
	}
	
	//helper function to transpose matricies
	private double[][] transpose(double[][]in){
		double[][] out = new double[in[0].length][in.length];
		for(int i = 0; i < in.length;i++){
			for(int j = 0; j < in[i].length; j++){
				out[j][i] = in[i][j];
			}
		}
		return out;
	}
	
	//negates a matrix
	private double[][] neg(double[][]in){
		double[][] out = new double[in.length][in[0].length];
		for(int i = 0; i < in.length;i++){
			for(int j = 0; j < in[i].length;j++){
				out[i][j] = -in[i][j];
			}
		}
		return out;
	}
		
	//tanh activiation function		
	private double[][] tan_activation(double[][] n){
		double[][] out = new double[n.length][n[0].length];
		for(int i = 0; i < n.length;i++){
			for(int j = 0; j < n[i].length;j++){
				out[i][j] = Math.sinh(n[i][j])/Math.cosh(n[i][j]);
			}
		}
		return out;
	}
	
	//tanh derivitive function
	private double[][] tan_deriv(double[][] n){
		double[][] out = new double[n.length][n[0].length];
		for(int i = 0; i < n.length;i++){
			for(int j = 0; j < n[i].length;j++){
				out[i][j] = 1-(Math.pow(Math.sinh(n[i][j]),2)/Math.pow(Math.cosh(n[i][j]),2));
			}
		}
		return out;
	}
	
	//sigmoid derivative function
	private double[][] sig_deriv(double[][] n){
		double[][] out = new double[n.length][n[0].length];
		for(int i = 0; i < n.length;i++){
			for(int j = 0; j < n[i].length;j++){
				out[i][j] = (1/(1 + Math.pow(Math.E, -(n[i][j]))))*(1-(1/(1 + Math.pow(Math.E, -(n[i][j])))));
			}
		}
		return out;
	}
	
	//sigmoid activation function
	private double[][] sig_activation(double[][] n){
		double[][] out = new double[n.length][n[0].length];
		for(int i = 0; i < n.length;i++){
			for(int j = 0; j < n[i].length;j++){
				out[i][j] = 1/(1 + Math.pow(Math.E, -(n[i][j])));
			}
		}
		return out;
	}
	
	//helper function to populate matricies
	private void populate(double[][]in){
		
		int coinflip;	//provides a 50/50 chance of being a 0 or 1
		Random rng = new Random();
		for(int i=0;i<in.length;i++){
			for(int j=0;j<in[i].length;j++){
				coinflip = rng.nextInt(100);
				if(coinflip >50){
					in[i][j] = 0.1;
				}
				else{
					in[i][j] = -0.1;
				}
				
			}
		}
	}
	
	//helper function to convert arrays to matricies
	private void clone(double[]in, double[][] out){
		for(int i = 0; i < in.length; i++){
			out[0][i] = in[i];
		}
	}
	
	//helper function to convert matricies to arrays
	private void unclone(double[][] in, double[] out){
		for(int i = 0; i < in.length; i++){
			out[i] = in[0][i];
		}
	}
	
	//helper function for dot product
	private double[][] dotp(double[][]in, double[][]w){
		double[][] out = new double[in.length][w[0].length];
		for(int i = 0; i < in.length;i++){
			for(int j = 0; j < w[0].length; j++){
				for(int k = 0; k < in[0].length;k++){
					out[i][j] += (in[i][k]*w[k][j]);
				}
			}
		}
		return out;
	}
	
	//helper function to print matrix
	public void printmatrix(double[][]in){
		for(int i=0;i<in.length;i++){
			System.out.print("[");
			for(int j=0;j<in[i].length;j++){
				if(j!=in[i].length){
					System.out.print(in[i][j] + ",");
				}
				else{
					System.out.print(in[i][j]);
				}
			}
			System.out.print("]\n");
		}
		System.out.println("\n\n");
	}
	
	//rotates the input 90 degrees so it aligns with input from piazza
	private double[][] rotate(double[][] in){
		double[][] out = new double[in.length][in[0].length];
		double[] temp = new double[in[0].length];
		double[][] temp2 = new double[7][5];
		double[][] temp3 = new double[5][7];
		int index = 0;
		
		//for every row
		for(int i = 0; i < in.length; i++){
			
			//put row into array to become matrix
			for(int j=0; j<in[0].length; j++){
				temp[j] = in[i][j];
			}
			index = 0;
			//put array into matrix
			for(int j=0; j<7; j++){
				for(int k=0; k<5;k++){
					temp2[j][k] = temp[index];
					index++;
				}
			}
			//rotate matrix
			for(int j=0; j<5; j++){
				for(int k=0; k<7;k++){
					temp3[j][k] = temp2[6-k][j];
					index++;
				}
			}
			index = 0;
			//put matrix back into array
			for(int j=0; j<5; j++){
				for(int k=0; k<7;k++){
					temp[index] = temp3[j][k];
					index++;
				}
			}
			//put array into output matrix
			for(int j = 0; j <out[0].length;j++){
				out[i][j] = temp[j];
			}
		}
		return out;
	}
	
	//prints the output for letter 
	public void printoutput(String let){
		double highest = 0;
		double num = 0;
		
		for(int i = 0; i < post_output[0].length; i++){
			if(post_output[0][i] > highest){
				highest = post_output[0][i];
				num = i;
			}
		}
		System.out.println("Letter Guessed: " + String.valueOf((char)(num + 65)) + ". Highest output was :" + highest + ".\n");
	}
	
}