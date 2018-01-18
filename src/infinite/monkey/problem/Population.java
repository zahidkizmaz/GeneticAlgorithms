
package infinite.monkey.problem;

import java.util.ArrayList;
import java.util.Random;



public class Population {
    
    ArrayList<DNA> pop = new ArrayList();
    
    public Population(int size){
        for (int i = 0; i < size; i++) {
            pop.add(new DNA(DNA.TARGET.length()));
        }        
    }
    
    public ArrayList<DNA> createMatingPool(){
        Random r = new Random();
        ArrayList<DNA> matingPool = new ArrayList();
        double maxFitness = 0;
        
        for (DNA dna : pop) {
            if (dna.getFitness() > maxFitness) {
                maxFitness = dna.getFitness();
            }
        }
        for (DNA dna : pop) {
            double fit = dna.getFitness() * (100 / maxFitness);
            fit =(int) Math.floor(fit);
            for (int i = 0; i < fit; i++) {
                matingPool.add(dna);
            }
        }
        
        return  matingPool;
    }
            
    
}
