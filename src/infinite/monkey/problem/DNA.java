
package infinite.monkey.problem;

import java.util.Random;


public class DNA {
    public static final double MUTATE_RATE = 0.02;
    public static final String TARGET = "To be or not to be";
    private final Random rn = new Random();
    private String genes;
    
    
    public DNA(int len){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < len; i++) {
            str.append(createRandomChar());
        }
        genes = str.toString();
    }
    
    private char createRandomChar(){
       
        int c = rn.nextInt(63) + 60;
        if (c == 63) return (char)32;
        return (char) c;
    }
    
    public double getFitness() {
        double score = 0;
        for (int i = 0; i < genes.length(); i++) {
            if (genes.charAt(i) == TARGET.charAt(i)) {
                score +=1.0;
            }
        }
        return score/TARGET.length();
    }    

    
    public DNA crossover(DNA partner){
        DNA child = new DNA(0);
        StringBuilder childGenes = new StringBuilder();
        for (int i = 0; i < genes.length(); i++) {
            if (rn.nextBoolean()) {
                childGenes.append(this.genes.charAt(i));
            }else{
                childGenes.append(partner.genes.charAt(i));
            }
        }
        child.setGenes(childGenes.toString());
        return child;
    }
    
    public void mutate(){
        for (int i = 0; i < this.genes.length(); i++) {
            if (rn.nextDouble() < MUTATE_RATE) {
                this.genes = genes.replace(genes.charAt(i), createRandomChar());
            }
        }
    }

    public boolean isSuper(){
        return this.getFitness() == 1.0;
    }
    
    public void setGenes(String genes) {
        this.genes = genes;
    }
        
    @Override
    public String toString() {
        return this.genes + " - Fitness:" + getFitness()*100;
    }
    
    
}
