/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainsw;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrea
 */
public class PercorsoTest {
    
    public PercorsoTest() {
    }

    @Test
    public void testcalcolapercorso() {
        Percorso percorso =new Percorso(1, 2, 3);
         System.out.println(percorso.getStaz_arr()+" "+percorso.getStaz_par());
        Collegamento col=new Collegamento(1, 1, 2, 20, new Stazione(1, 2, "pippo","pippolandia"),new Stazione(2, 2, "pippo","pippolandia"));
        Collegamento col2=new Collegamento(3, 2, 3, 20, new Stazione(2, 2, "pippo","pippolandia"),new Stazione(3, 2, "pippo","pippolandia"));
        percorso.addcollegamenti(col);
        percorso.addcollegamenti(col2);
       List<Fermata> fermate= percorso.calcolaPercorso();
      for (Fermata fermata :fermate){
          System.out.println(fermata.getStazione().toString());
     
      }
                
                
                
    }
    
}
