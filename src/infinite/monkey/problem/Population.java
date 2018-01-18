
package infinite.monkey.problem;

import java.util.ArrayList;


public class Population {
    
    ArrayList<DNA> pop = new ArrayList();
    
    public Population(int size){
        for (int i = 0; i < size; i++) {
            pop.add(new DNA(DNA.TARGET.length()));
        }        
    }
            
    
}
