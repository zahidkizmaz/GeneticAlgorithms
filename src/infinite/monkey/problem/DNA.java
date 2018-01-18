
package infinite.monkey.problem;

import java.util.Random;


public class DNA {
    public static final String TARGET = "To be or not to be!";
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
       
        int c = rn.nextInt(62) + 60;
        return (char) c;
    }
    
    public double getFitness() {
        int score = 0;
        for (int i = 0; i < genes.length(); i++) {
            if (genes.charAt(i) == TARGET.charAt(i)) {
                score++;
            }
        }
        return (double)score/TARGET.length();
    }    

    @Override
    public String toString() {
        return this.genes;
    }
    
    
}
