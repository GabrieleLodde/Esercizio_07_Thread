import java.util.ArrayList;
import java.util.List;

public class Ambulatorio {
    //RISORSA, 
    // CLASSE thread-safe wait() / notify() / notifyAll()
    private int max_gatti;
    private int max_cani;
    private int num_gatti_in;
    private int num_cani_in;
    private int num_gatti_attesa;
    List <String> elencoGattiAttesa = new ArrayList<>();

    public Ambulatorio(int max_gatti, int max_cani){
        this.max_gatti = max_gatti;
        this.max_cani = max_cani;
        this.num_gatti_in = 0;
        this.num_cani_in = 0;
        this.num_gatti_attesa = 0;
    }

    public synchronized void entra(String tipo){
        String nome = Thread.currentThread().getName();
        while ( //Controllo disponibilità gatti/cani
            num_cani_in == max_cani || num_gatti_in == max_gatti || 
            //Controllo priorità
            ( tipo.equalsIgnoreCase("cane") && num_gatti_attesa > 0) ||
            // Controllo catti/cani in contemporanea
            ( tipo.equalsIgnoreCase("cane") && num_gatti_in > 0)  ||
            ( tipo.equalsIgnoreCase("gatto") && num_cani_in > 0) ){
            try{
                //L'animale deve aspettare
                System.out.println(tipo + " " + nome + " aspetta di entrare..."); 
                if( tipo.equalsIgnoreCase("gatto")){
                    if(!elencoGattiAttesa.contains(nome)){
                        elencoGattiAttesa.add(nome);
                        this.num_gatti_attesa ++;
                    }
                    System.out.println("NUM_GATTI_ATTESA " + num_gatti_attesa);
                }
                wait();
            }catch(Exception e) {}
        }
        // L'animale entra
        System.out.println("<--" + tipo + " " + nome + " entra in ambulatorio");
        if ( tipo.equalsIgnoreCase("gatto")){
            this.num_gatti_in++;
            this.num_gatti_attesa--;
            elencoGattiAttesa.remove(nome);
        }
        else if( tipo.equalsIgnoreCase("cane")){
            this.num_cani_in++;
        }
        System.out.println("NUM_CANI_IN " + num_cani_in);
        System.out.println("NUM_GATTI_IN " + num_gatti_in);
    }

    public synchronized void esci(String tipo){
        String nome = Thread.currentThread().getName();
        if ( tipo.equalsIgnoreCase("gatto")){
            this.num_gatti_in--;
        }
        else if( tipo.equalsIgnoreCase("cane")){
            this.num_cani_in--;
        }
        System.out.println("-->" + tipo + " " + nome + " esce dall'ambulatorio");
        notifyAll();
    }
}