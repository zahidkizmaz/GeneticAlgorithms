
package geneticalgorithms;

import infinite.monkey.problem.Population;


public class GeneticAlgorithms {


    public static void main(String[] args) {
        int populationSize = 150;
        Population population = new Population(populationSize);
                
        while (!population.isDone()) { 
            population.generateNewPop();
            System.out.println(population.toString());
        }
        
        
    }
    
}
