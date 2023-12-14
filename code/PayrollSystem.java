import java.util.List;

public class PayrollSystem implements PayrollInterface {


	@Override
    public float netFromBrute(List<PayInfo> payInfos) {
        // Calcul du salaire brut total
        float salaireBrutTotal = calculerSalaireBrutTotal(payInfos);
        
        // Calcul des déductions totales
        float deductionsTotales = calculerDeductionsTotales(payInfos);

        // Appliquer d'autres règles spécifiques au calcul du salaire net total si nécessaire
        float tauxNet = 0.6f; // taux déduction 60%

        // Calcul du salaire net total
        return salaireBrutTotal * tauxNet - deductionsTotales;
    }

    /**
     * Génère un rapport de déductions pour une liste d'informations de paie.
     *
     * @param payInfos Liste des informations de paie.
     * @return Le rapport de déductions.
     */

	@Override
    public Rapport deductionRapport(List<PayInfo> payInfos) {
        Rapport rapport = new Rapport();

        for (PayInfo payInfo : payInfos) {
            int idEmploye = payInfo.getIdEmploye();
            float salaireBrut = payInfo.calculerSalaireBrut();

            // Ajouter des détails spécifiques de déduction au rapport
            // Exemple de déduction : 40% du salaire brut
            float deduction = salaireBrut * 0.4f;

            rapport.ajouterDeduction(idEmploye, deduction);
        }

        return rapport;
    }


    @Override
    public void printPay(List<PayInfo> payInfos) {
        for (PayInfo payInfo : payInfos) {
            System.out.println("Chèque de paie pour l'employé " + payInfo.getIdEmploye() + ":");
            System.out.println("Salaire brut: " + payInfo.calculerSalaireBrut());
            // Ajouter d'autres détails de paie selon vos besoins
            System.out.println("---------------------------------------------");
        }
    }

    // Méthode pour calculer le salaire brut total à partir des informations de paie
    private float calculerSalaireBrutTotal(List<PayInfo> payInfos) {
        float salaireBrutTotal = 0;

        for (PayInfo payInfo : payInfos) {
            salaireBrutTotal += payInfo.calculerSalaireBrut();
        }

        return salaireBrutTotal;
    }

    
    // Méthode pour calculer les déductions totales à partir des informations de paie
    private float calculerDeductionsTotales(List<PayInfo> payInfos) {
        float deductionsTotales = 0;

        for (PayInfo payInfo : payInfos) {
            // Ajouter ici la logique pour calculer les déductions spécifiques
            // taux déduction : 40% du salaire brut
            deductionsTotales += payInfo.calculerSalaireBrut() * 0.4f;
        }

        return deductionsTotales;
    }

}
