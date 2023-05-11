public class Animale extends Thread{
    private String tipo;
    private Ambulatorio amb;

    public Animale(String nome, String tipo, Ambulatorio amb){
        super(nome);
        this.tipo = tipo;
        this.amb = amb;
    }

    @Override 
    public void run(){
        //animale entra
        amb.entra(tipo);
        //animale viene visitato/curato
        // Simulo un tempo di visita/cura dell'animale
        long tempoVisita = (int) (Math.random()*3000) + 2000;
        try {
            Thread.sleep(tempoVisita);
        } catch (Exception e) {}
        //animale esce
        amb.esci(tipo);
    }

}