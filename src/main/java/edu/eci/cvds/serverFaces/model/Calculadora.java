package edu.eci.cvds.serverFaces.model;

import javax.faces.bean.ManagedBean;

import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;

@SuppressWarnings("deprecation")
@ManagedBean(name = "calculadoraBean")
@SessionScoped
@ApplicationScoped

public class Calculadora {
	private ArrayList<Double> numbers = new ArrayList<Double>();
	private double mean = 0;
    private double standardDeviation = 0;
    private double variance = 0;
    private double mode = 0;

	public Calculadora(){}
	
	public void setNumbers(String numbers){
        this.numbers = stringToDouble(numbers);
        setValues(this.numbers);
    }
	
	public ArrayList<Double> getNumbers(){
        return numbers;
    }
	
	public int getSize() {
		return numbers.size();
	}
	
	public void setValues(ArrayList<Double> numbers) {
		calculateMean(numbers);
        calculateStandartDesviation(numbers);
        calculateVariance(numbers);
        calculateMode(numbers);
	}
	
	public double getMean(){
        return mean;
    }
	
	public double getVariance(){
        return variance;
    }
	
	public double getStandardDeviation(){
        return standardDeviation;
    }
	
	public double getMode(){
        return mode;
    }
	
	public Double calculateMean(ArrayList<Double> numbers) {
		Double suma=(double) 0;
		for(Double i:numbers) {
			suma+=i;
		}
		mean=(double)Math.round((suma/numbers.size()) * 100d) / 100d;
		return mean;
	}
	
	public Double calculateVariance(ArrayList<Double> numbers) {
        double suma = 0;
        for (Double i : numbers){
            suma += Math.pow((i - mean), 2);
        }
        variance=(double)Math.round((suma/numbers.size()) * 100d) / 100d;
        return variance;
	}
	
	public Double calculateStandartDesviation(ArrayList<Double> numbers) {
		standardDeviation=(double)Math.round((Math.sqrt(calculateVariance(numbers))) * 100d) / 100d;
        return standardDeviation;
	}
	
	public Double calculateMode(ArrayList<Double> numbers) {
		int maximoNumRepe = 0;
        mode = 0;
        for (Double i : numbers){
            int repe=0;
            for(Double j : numbers){
                if (i == j){
                    repe ++;
                }
                if (repe>maximoNumRepe){
                	mode = i;
                    maximoNumRepe = repe;
                }
            }
        }
        return mode;
	}
	
	public ArrayList<Double> stringToDouble(String lista) {
        String[] splitList = lista.trim().split(";");
        ArrayList<Double> listaConvertida = new ArrayList<Double>();
        for (int i= 0; i<splitList.length; i++){
            Double conver = Double.parseDouble(splitList[i]);
            listaConvertida.add(conver);
        }
        return listaConvertida;
    }
	
	public void restart() {
		this.numbers = new ArrayList<Double>();
		this.mean = 0;
		this.standardDeviation = 0;
		this.variance = 0;
		this.mode = 0;
	}
}