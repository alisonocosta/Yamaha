/**
 * 
 */
package br.com.yamaha.sistemagarantia.controller.helper;

import java.util.ArrayList;

import br.com.yamaha.sistemagarantia.view.TesteGraf;

/**
 * @author user
 *
 */
public class ComposicaoDadosGrafico {
	
	protected ArrayList array = new ArrayList();

    public ComposicaoDadosGrafico() {
    	
        array.add(new TesteGraf("JANEIRO", "CD", 20));
        array.add(new TesteGraf("FEVEREIRO", "CD", 30));
        array.add(new TesteGraf("MARCO", "CD", 40));
        array.add(new TesteGraf("ABRIL", "CD", 50));
        array.add(new TesteGraf("MAIO", "CD", 60));
        array.add(new TesteGraf("JUNHO", "CD", 70));

        array.add(new TesteGraf("JANEIRO", "DVD", 10));
        array.add(new TesteGraf("FEVEREIRO", "DVD", 70));
        array.add(new TesteGraf("MARCO", "DVD", 140));
        array.add(new TesteGraf("ABRIL", "DVD", 10));
        array.add(new TesteGraf("MAIO", "DVD", 54));
        array.add(new TesteGraf("JUNHO", "DVD", 23));
    }

    public ArrayList getArray() {
        return array;
    }


    public void setArray(ArrayList array) {
        this.array = array;
    }


}
