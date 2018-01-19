
package infinite.monkey.problem;

import java.util.ArrayList;
import java.util.Random;



public class Population {
    Random r = new Random();
    ArrayList<DNA> pop;
    ArrayList<DNA> matingPool;
    private int popSize, genNumber;
    DNA superDNA;
    
    public Population(int size){
        popSize = size;
        pop = new ArrayList();
        for (int i = 0; i < popSize; i++) {
            pop.add(new DNA(DNA.TARGET.length()));
        }        
    }
    
    private ArrayList<DNA> createMatingPool(){

        matingPool = new ArrayList();
        double maxFitness = 0;
        
        for (DNA dna : pop) {
            if (dna.getFitness() > maxFitness) {
                maxFitness = dna.getFitness();
                this.superDNA = dna;
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
        info.append(superDNA);
        info.append("\n");
        info.append("Generation Number: ");
        info.append(genNumber);
        info.append("\n");
        info.append("Population Number: ");
        info.append(pop.size());
        info.append("\n");
        info.append("Mutation Rate (%): ");
        info.append(DNA.MUTATE_RATE * 100);
        info.append("\n");
        return  info.toString();
    }
    
       
}
