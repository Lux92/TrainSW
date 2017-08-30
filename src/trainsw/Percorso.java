package trainsw;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.hibernate.Session;

public class Percorso {

    private Integer staz_par;
    private Integer staz_arr;
    private Integer id_per;
    private Integer distanzaparziale = 0;
    private List<Fermata> fermate;
    private List<Collegamento> collegamenti;
    private List<Tratta> tratte;
    private Tratta tratta;

    public Percorso() {
        this.fermate = new ArrayList<Fermata>();
        this.collegamenti = new ArrayList<Collegamento>();
        this.tratte = new ArrayList<Tratta>();
    }

    public Percorso(Integer id_per, Integer staz_par, Integer staz_arr) {
        this.staz_par = staz_par;
        this.staz_arr = staz_arr;
        this.id_per = id_per;
        this.fermate = new ArrayList<Fermata>();
        this.collegamenti = new ArrayList<Collegamento>();
        this.tratte = new ArrayList<Tratta>();
    }

    public List<Fermata> calcolaPercorso() {
        List<Fermata> fermate = new ArrayList<>();
        List<Collegamento>collegamenti=new ArrayList<>();
        int staz_succ=this.staz_par;
        for (Collegamento collegamento : this.collegamenti) {
            if(collegamento.getId_stazione_a()==staz_succ  && staz_succ != this.staz_arr){
               collegamenti.add(collegamento);
               Fermata fermata=new Fermata();
               fermata.setId_stazione(collegamento.getId_stazione_a());
               fermata.setStazione(collegamento.getStazione_a());
               fermata.setDistanza_p(this.distanzaparziale);
               staz_succ=collegamento.getId_stazione_b();
               calcolaDistanzaParziale(collegamento.getDistanza());
               fermate.add(fermata);
               }
            if(collegamento.getId_stazione_b()==this.staz_arr ) {
                Fermata fermata=new Fermata();
               fermata.setId_stazione(collegamento.getId_stazione_b());
               fermata.setStazione(collegamento.getStazione_b());
               fermata.setDistanza_p(this.distanzaparziale);
               
               calcolaDistanzaParziale(collegamento.getDistanza());
               fermate.add(fermata);
             }
        }
        this.collegamenti=collegamenti;
        return fermate;
    }

    public void calcolaDistanzaParziale(Integer distanza) {
        this.distanzaparziale = this.distanzaparziale + distanza;
    }

    public void addcollegamenti(Collegamento col) {
        collegamenti.add(col);
    }

    public void addFermate(Fermata fr) {
        this.fermate.add(fr);
    }

    public Integer getStaz_par() {
        return staz_par;
    }

    public void setStaz_par(Integer staz_par) {
        this.staz_par = staz_par;
    }

    public Integer getStaz_arr() {
        return staz_arr;
    }

    public void setStaz_arr(Integer staz_arr) {
        this.staz_arr = staz_arr;
    }

    public Integer getId_per() {
        return id_per;
    }

    public void setId_per(Integer id_per) {
        this.id_per = id_per;
    }

    public List<Fermata> getFermate() {
        return fermate;
    }

    public void setFermate(List<Fermata> fermate) {
        this.fermate = fermate;
    }

    public List<Collegamento> getCollegamenti() {
        return collegamenti;
    }

    public void setCollegamenti(List<Collegamento> collegamenti) {
        this.collegamenti = collegamenti;
    }

    public Integer getDistanzaparziale() {
        return distanzaparziale;
    }

    public void setDistanzaparziale(Integer distanzaparziale) {
        this.distanzaparziale = distanzaparziale;
    }

    public List<Tratta> getTratte() {
        return tratte;
    }

    public void setTratte(List<Tratta> tratte) {
        this.tratte = tratte;
    }

    public void createTratta() {
        Tratta tr = new Tratta();
        this.tratta = tr;
        for (Fermata fermata : fermate) {
            tr.addFermata(fermata);
        }
    }

    public void ConfermaTratta() {
        this.tratte.add(tratta);

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(tratta);

        session.getTransaction().commit();
        session.close();
    }

    public List<FermataOrario> InserisciTreno(Treno tr) {
        tratta.inserisciTreno(tr);
        return tratta.calcolaOrari();

    }

    public void InserisciOrario(Date data) {
        tratta.setData_p(data);

    }

    public boolean RicercaFermata(int id_sta) {
        for (Fermata fermata : this.fermate) {
            if (fermata.getStazione().getId_stazione() == id_sta) {
                return true;
            }
        }
        return false;

    }

    public List<Tratta> RicercaTrattaOrario(int id_sta_par) {
        List<Tratta> tratte = null;
        for (Tratta tratta : this.tratte) {
            if (tratta.ricercaFermata(id_sta_par)) {
                tratte.add(tratta);
            }
        }
        return tratte;
    }

    public int DistanzaFermate(int id_sta_par,int id_sta_arrivo){
    int distanza = 0;
        if (RicercaFermata(id_sta_par) && RicercaFermata(id_sta_arrivo)) {
            distanza = getDistanzaFermata(id_sta_arrivo) - getDistanzaFermata(id_sta_par);
        }
        return distanza;
    }

    public int getDistanzaFermata(int id_sta) {
        for (Fermata fermata : this.fermate) {
            if (fermata.getStazione().getId_stazione() == id_sta) {
                return fermata.getDistanza_p();
            }
        }
        return 0;

    }

}
