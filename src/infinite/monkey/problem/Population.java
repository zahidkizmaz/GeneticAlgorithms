
package infinite.monkey.problem;

import java.util.ArrayList;
import java.util.Random;



public class Population {
    Random r = new Random();
    ArrayList<DNA> pop;
    ArrayList<DNA> matingPool;
    private int popSize;
    
    public Population(int size){
        popSize = size;
        pop = new ArrayList();
        for (int i = 0; i < popSize; i++) {
            pop.add(new DNA(DNA.TARGET.length()));
        }        
    }
    
    public ArrayList<DNA> createMatingPool(){

        matingPool = new ArrayList();
        double maxFitness = 0;
        
        for (DNA dna : pop) {
            if (dna.getFitness() > maxFitness) {
                maxFitness = dna.getFitness();
            }
        }
        for (DNA dna : pop) {
            double fit = dna.getFitness() * (100 / maxFitness);
            fit =(int) Math.floor(fit) * 100;
            for (int i = 0; i < fit; i++) {
                matingPool.add(dna);
            }
        }
        return  matingPool;
    }
            
    private void generateNewPop(){
        pop = new ArrayList<>();
        for (int i = 0; i < popSize; i++) {
            DNA parent1 = matingPool.get(r.nextInt(matingPool.size()-1));
            DNA parent2 = matingPool.get(r.nextInt(matingPool.size()-1));
            DNA child = parent1.crossover(parent2);
            child.mutate();
            pop.add(child);
        }
        
    }
    
}
