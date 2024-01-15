public class NumTel {
    private int id; 
    private String valeur;
    private char type;
    private int personne_CIN; 

    public NumTel(String valeur, char type, int personne_CIN) {
        this.valeur = valeur;
        this.type = type;
        this.personne_CIN = personne_CIN;
    }

    public NumTel(int id, String valeur, char type, int personne_CIN) {
        this.id = id;
        this.valeur = valeur;
        this.type = type;
        this.personne_CIN = personne_CIN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getPersonne_CIN() {
        return personne_CIN;
    }

    public void setPersonne_CIN(int personne_CIN) {
        this.personne_CIN = personne_CIN;
    }

}
