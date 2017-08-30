package trainsw;

public abstract class Promozione {
	
	String nome;
        int punti;

	public abstract double calcolaPromozione(double prezzo);

	public String getNome() {
		return nome;
	}
	
		
	public Promozione getPromozione(String nome)	{
		return null;
	}

    public Promozione(String nome, int punti) {
        this.nome = nome;
        this.punti = punti;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }
	
}
