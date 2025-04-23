import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Classe représentant une matière
class Matiere {
    private String nom;
    private double note;
    private double coefficient;

    public Matiere(String nom, double note, double coefficient) {
        this.nom = nom;
        this.note = note;
        this.coefficient = coefficient;
    }

    public double getNote() {
        return note;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public String getNom() {
        return nom;
    }

    // Calcul du total : note * coefficient
    public double calculerTotal() {
        return note * coefficient;
    }
}

// Classe représentant un élève
class Eleve implements Comparable<Eleve> {
    private String nom;
    private String prenom;
    private String classe;
    private String sexe;
    private String matricule;
    private ArrayList<Matiere> matieres;
    private double moyenneGenerale;

    public Eleve(String nom, String prenom, String classe, String sexe, String matricule) {
        this.nom = nom;
        this.prenom = prenom;
        this.classe = classe;
        this.sexe = sexe;
        this.matricule = matricule;
        this.matieres = new ArrayList<>();
    }

    public void ajouterMatiere(Matiere matiere) {
        matieres.add(matiere);
    }

    public void calculerMoyenneGenerale() {
        double sommeNotes = 0;
        double sommeCoefficients = 0;

        for (Matiere matiere : matieres) {
            sommeNotes += matiere.calculerTotal(); // Utiliser le total calculé
            sommeCoefficients += matiere.getCoefficient();
        }

        this.moyenneGenerale = sommeCoefficients > 0 ? sommeNotes / sommeCoefficients : 0;
    }

    public double getMoyenneGenerale() {
        return moyenneGenerale;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getClasse() {
        return classe;
    }

    public String getSexe() {
        return sexe;
    }

    public String getMatricule() {
        return matricule;
    }

    public ArrayList<Matiere> getMatieres() {
        return matieres;
    }

    @Override
    public int compareTo(Eleve autreEleve) {
        return Double.compare(autreEleve.getMoyenneGenerale(), this.moyenneGenerale); // Tri décroissant
    }
}

public class BulletinScolaire {
    // Liste des matières avec leurs coefficients par défaut
    private static final String[] MATIERES = {
        "Français", "Anglais", "Mathématiques", "Physique", 
        
    };
    
    private static final double[] COEFFICIENTS = {
        4, 3, 5, 4
    };

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Eleve> eleves = new ArrayList<>();

        // Saisie des informations des élèves
        System.out.println("Combien d'élèves souhaitez-vous ajouter ?");
        int nombreEleves = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne

        for (int i = 0; i < nombreEleves; i++) {
            System.out.println("\nSaisie des informations pour l'élève " + (i + 1) + ":");
            
            System.out.println("Nom de l'élève :");
            String nomEleve = scanner.nextLine();

            System.out.println("Prénom de l'élève :");
            String prenomEleve = scanner.nextLine();

            System.out.println("Classe de l'élève :");
            String classeEleve = scanner.nextLine();

            System.out.println("Sexe de l'élève (M/F) :");
            String sexeEleve = scanner.nextLine();

            System.out.println("Matricule de l'élève :");
            String matriculeEleve = scanner.nextLine();

            Eleve eleve = new Eleve(nomEleve, prenomEleve, classeEleve, sexeEleve, matriculeEleve);

            System.out.println("\nSaisie des notes pour " + nomEleve + " " + prenomEleve + ":");
            
            // Saisie des notes pour chaque matière prédéfinie
            for (int j = 0; j < MATIERES.length; j++) {
                System.out.println("Note en " + MATIERES[j] + " (coefficient " + COEFFICIENTS[j] + ") :");
                double note = scanner.nextDouble();
                scanner.nextLine(); // Consommer le retour à la ligne
                
                // Vérification que la note est valide
                while (note < 0 || note > 20) {
                    System.out.println("Note invalide! La note doit être entre 0 et 20. Veuillez réessayer:");
                    note = scanner.nextDouble();
                    scanner.nextLine();
                }
                
                Matiere matiere = new Matiere(MATIERES[j], note, COEFFICIENTS[j]);
                eleve.ajouterMatiere(matiere);
            }

            eleve.calculerMoyenneGenerale();
            eleves.add(eleve);
        }

        // Classement des élèves par moyenne générale
        Collections.sort(eleves);

        // Générer un fichier texte pour chaque élève
        for (int i = 0; i < eleves.size(); i++) {
            Eleve eleve = eleves.get(i);
            String nomFichier = eleve.getNom() + "_" + eleve.getPrenom() + "_Bulletin.txt";
            FileWriter writer = new FileWriter(nomFichier);

            // En-tête du bulletin
            writer.write("==============================================\n");
            writer.write("            BULLETIN SCOLAIRE\n");
            writer.write("==============================================\n\n");
            
            writer.write("Élève: " + eleve.getNom() + " " + eleve.getPrenom() + "\n");
            writer.write("Classe: " + eleve.getClasse() + "\n");
            writer.write("Sexe: " + eleve.getSexe() + "\n");
            writer.write("Matricule: " + eleve.getMatricule() + "\n\n");

            writer.write("----------------------------------------------\n");
            writer.write("MATIÈRES                NOTE    COEF    TOTAL\n");
            writer.write("----------------------------------------------\n");
            
            // Détails des matières
            for (Matiere matiere : eleve.getMatieres()) {
                writer.write(String.format("%-20s %6.2f %6.1f %8.2f\n", 
                    matiere.getNom(), 
                    matiere.getNote(), 
                    matiere.getCoefficient(),
                    matiere.calculerTotal()));
            }
            
            writer.write("----------------------------------------------\n");
            writer.write(String.format("MOYENNE GÉNÉRALE: %30.2f\n", eleve.getMoyenneGenerale()));
            writer.write(String.format("CLASSEMENT: %35d/%d\n", (i + 1), eleves.size()));
            writer.write("==============================================\n");

            writer.close();
            System.out.println("Bulletin généré pour " + eleve.getNom() + " " + eleve.getPrenom() + " " + nomFichier + ")");
        }

        scanner.close();;
}
}
