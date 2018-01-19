
package geneticalgorithms;

import infinite.monkey.problem.Population;


public class GeneticAlgorithms {


    public static void main(String[] args) {
        int populationSize = 250;
        Population population = new Population(populationSize);
                
        while (!population.isDone()) { 
            population.generateNewPop();
            System.out.println(population.toString());
        }
        System.out.println("------------Final Phrase--------------");
        System.out.println(population.toString());
    }
    
}
