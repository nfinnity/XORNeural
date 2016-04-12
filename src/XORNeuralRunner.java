import java.util.Random;
import java.util.Scanner;

import org.jfree.ui.RefineryUtilities;
 
public class XORNeuralRunner {
	
	private final static int MAX_EPOCHS = 10000;
	static Scanner reader = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		int epochs = 1;
		boolean loop = true;
		double sumSqrError;
		
	    System.out.println("Enter learning constant value: ");
	    double cons = reader.nextDouble();
	    XORNeuralNetwork network = new XORNeuralNetwork(cons);
	    double error[][] = new double[MAX_EPOCHS][MAX_EPOCHS];
	    int count = 0;
	    
	    while(loop)
	    {
			if(epochs >= MAX_EPOCHS)
			{
			    System.out.println("Learning will take more than " + MAX_EPOCHS + " epochs, so program has terminated.");
			    break;
			}
				
			network.activateNeuron(1,1,0);
			sumSqrError = Math.pow(network.error, 2);
			network.activateNeuron(0,1,1);
			sumSqrError += Math.pow(network.error, 2);
			network.activateNeuron(1,0,1);
			sumSqrError += Math.pow(network.error, 2);
			network.activateNeuron(0,0,0);
			sumSqrError += Math.pow(network.error, 2);

	        error[count][0] = epochs;
	        error[count][1] = sumSqrError;
	        System.out.println(epochs + " " + sumSqrError);

	        if (sumSqrError < 0.001)
	        {
	            loop = false;
	        }
	        epochs++;
	        count++;
	    }
	    Graph errorPlot = new Graph("Error Plot", error);
	    errorPlot.pack();
	    RefineryUtilities.centerFrameOnScreen(errorPlot);
	    errorPlot.setVisible(true);
	}
}