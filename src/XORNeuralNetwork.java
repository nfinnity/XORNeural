import java.util.Random;

public class XORNeuralNetwork {

	//weights
	private double weight13, weight23, weight14, weight24, weight35, weight45;
	private double thresh3, thresh4, thresh5;
	//neuron outputs
	private double output3, output4, output5;
	//neuron error gradients
	private double delta3, delta4, delta5;
	//weight corrections
	private double dweight13, dweight14, dweight23, dweight24, dweight35, dweight45, dt3, dt4, dt5;
	//private static double learningConstant = 0.25;
	public double error;
	private Random generator = new Random();
	private double learningConstant, g34, g5;
	
	public XORNeuralNetwork(double learningCons)
	{
	    weight13 = -1 + 2*generator.nextDouble();
	    weight14 = -1 + 2*generator.nextDouble();
	    weight23 = -1 + 2*generator.nextDouble();
	    weight24 = -1 + 2*generator.nextDouble();
	    weight35 = -1 + 2*generator.nextDouble();
	    weight45 = -1 + 2*generator.nextDouble();
	    thresh3 = -1 + 2*generator.nextDouble();
	    thresh4 = -1 + 2*generator.nextDouble();
	    thresh5 = -1 + 2*generator.nextDouble();
	    g34 = 0.13;
	    g5 = 0.21;
	    this.learningConstant = learningCons;
	}
	
	double sigmoid(double exponent)
	{
	    return (1.0/(1 + Math.pow(Math.E, (-1) * exponent)));
	}
	
	void activateNeuron(int x1, int x2, int gd5)
	{
	    output3 = sigmoid(x1 * weight13 + x2 * weight23 - thresh3);
	    output4 = sigmoid(x1 * weight14 + x2 * weight24 - thresh4);        
	    if (output3 > 1 - g34 ) 
	    	output3 = 1;
	    if (output3 < g34) 
	    	output3 = 0;
	    if (output4 > 1- g34) 
	    	output4 = 1;
	    if (output4 < g34) 
	    	output4 = 0;
	    output5 = sigmoid(output3 * weight35 + output4 * weight45 - thresh5);
	    if (output5 > 1 - g5) 
	    	output5 = 1;
	    if (output5 < g5) 
	    	output5 = 0;
	    error = gd5 - output5;
	    
	    weightTraining(x1, x2);
	}

	void weightTraining(int x1, int x2)
	{
	    delta5 = output5 * (1 - output5) * error;
	    dweight35 = learningConstant * output3 * delta5;
	    dweight45 = learningConstant * output4 * delta5;
	    dt5 = learningConstant * (-1) * delta5;

	    delta3 = output3 * (1 - output3) * delta5 * weight35;
	    delta4 = output4 * (1 - output4) * delta5 * weight45;

	    dweight13 = learningConstant * x1 * delta3;
	    dweight23 = learningConstant * x2 * delta3;
	    dt3 = learningConstant * (-1) * delta3;
	    dweight14 = learningConstant * x1 * delta4;
	    dweight24 = learningConstant * x2 * delta4;
	    dt4 = learningConstant * (-1) * delta4;

	    weight13 = weight13 + dweight13;
	    weight14 = weight14 + dweight14;
	    weight23 = weight23 + dweight23;
	    weight24 = weight24 + dweight24;
	    weight35 = weight35 + dweight35;
	    weight45 = weight45 + dweight45;
	    thresh3 = thresh3 + dt3;
	    thresh4 = thresh4 + dt4;
	    thresh5 = thresh5 + dt5;
	}
}
