import java.util.ArrayList;
import java.util.List;
public class App {
    public static void main(String[] args) throws Exception {
        // Istanzio la risorsa ambulatorio
        Ambulatorio amb = new Ambulatorio(2, 2);
        List<Animale> elenco = new ArrayList<>();

        elenco.add( new Animale("Fido", "Cane", amb));
        elenco.add( new Animale("Fulmine", "Cane", amb));
        elenco.add( new Animale("Briciola", "Gatto", amb));
        elenco.add( new Animale("Nina", "Gatto", amb));
        elenco.add( new Animale("Black", "Cane", amb));
        elenco.add( new Animale("Gioia", "Gatto", amb));

        // Costrutto più antico
        /*for( int i = 0; i < elenco.size(); i ++){
            elenco.get(i).start();
        }*/


        // Costrutto migliore e più elegante
        for ( Animale a : elenco ) {
            a.start();
        }

    }
}