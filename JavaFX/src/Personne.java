import java.util.ArrayList;
import java.util.List;

public class Personne {
    private int CIN;
    private String nom;
    private String prenom;
    private String civilité;
    private List<NumTel> numeros;

    public Personne(int CIN, String nom, String prenom, String civilité, List<NumTel> numeros) {
        this.CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        this.civilité = civilité;
 if (numeros != null) {
            this.numeros = new ArrayList<>(numeros);
        } else {
            this.numeros = new ArrayList<>();
        }    }

    public int getNumcin() {
        return CIN;
    }

    public void setNumcin(int numcin) {
        this.CIN = numcin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCivilite() {
        return civilité;
    }

    public void setCivilite(String civilite) {
        this.civilité = civilite;
    }

    public List<NumTel> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<NumTel> numeros) {
        this.numeros = numeros;
    }
}
