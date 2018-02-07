
package infinite.monkey.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;



public class Population {
    Random r = new Random();
    ArrayList<DNA> pop;
    ArrayList<DNA> matingPool;
    private int popSize, genNumber;
    private DNA superDNA;
    
    public Population(int size){
        popSize = size;
        pop = new ArrayList();
        for (int i = 0; i < popSize; i++) {
            pop.add(new DNA(DNA.TARGET.length()));
        }        
    }
    
    private double getPopAvgFitness(){
        ArrayList<Double> fitnesses = new ArrayList<>();
        for(DNA d : pop){
            fitnesses.add(d.getFitness());
        }
        
        return fitnesses.stream().mapToDouble(v -> v).average().getAsDouble();
    }
    
    private ArrayList<DNA> createMatingPool(){

        matingPool = new ArrayList();
        double avgFit = getPopAvgFitness();
        for (DNA dna : pop) {
            double fit = dna.getFitness() * (10 / this.getSuperDNA().getFitness());
            fit =(int) Math.floor(fit);
            if (dna.getFitness() <= avgFit) {
                fit= 0;
            }
            for (int i = 0; i < fit; i++) {
                matingPool.add(dna);
            }
        }
        return  matingPool;
    }

    public DNA getSuperDNA() {
        double maxFitness = 0.0;
        DNA max = pop.get(0);
        for (DNA dna : pop) {
            if (dna.getFitness() > maxFitness) {
                maxFitness = dna.getFitness();
                max = dna;
            }
        }
        this.superDNA = max;
        return superDNA;
    }
       
    public boolean isDone(){
        for (DNA dna : pop) {
            if (dna.isSuper()) {
                return true;
            }
        }
        return false;
    }
            
    public void generateNewPop(){
        createMatingPool();
        pop = new ArrayList<>();
        for (int i = 0; i < popSize; i++) {
            DNA parent1 = matingPool.get(r.nextInt(matingPool.size()-1));
            DNA parent2 = matingPool.get(r.nextInt(matingPool.size()-1));
            DNA child = parent1.crossover(parent2);
            child.mutate();
            pop.add(child);
        }
        genNumber++;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Goal phrase: ");
        info.append(DNA.TARGET);
        info.append("\n");
        info.append("Best phrase: ");
        info.append(getSuperDNA());
        info.append("\n");
        info.append("Generation Number: ");
        info.append(genNumber);
        info.append("\n");
        info.append("Population Number: ");
        info.append(pop.size());
        info.append("\n");
        info.append("Mating Pool Size: ");
        info.append(matingPool.size());
        info.append("\n");
        info.append("Mutation Rate (%): ");
        info.append(DNA.MUTATE_RATE * 100);
        info.append("\n");
        return info.toString();
    }
    
       
}
