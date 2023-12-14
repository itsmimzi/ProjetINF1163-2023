import java.util.List;

public class PayrollSystem implements PayrollInterface {


	@Override
    public float netFromBrute(List<PayInfo> payInfos) {
        // Calcul du salaire brut total
        float salaireBrutTotal = calculerSalaireBrutTotal(payInfos);
        
        // Calcul des d�ductions totales
        float deductionsTotales = calculerDeductionsTotales(payInfos);

        // Appliquer d'autres r�gles sp�cifiques au calcul du salaire net total si n�cessaire
        float tauxNet = 0.6f; // taux d�duction 60%

        // Calcul du salaire net total
        return salaireBrutTotal * tauxNet - deductionsTotales;
    }

    /**
     * G�n�re un rapport de d�ductions pour une liste d'informations de paie.
     *
     * @param payInfos Liste des informations de paie.
     * @return Le rapport de d�ductions.
     */

	@Override
    public Rapport deductionRapport(List<PayInfo> payInfos) {
        Rapport rapport = new Rapport();

        for (PayInfo payInfo : payInfos) {
            int idEmploye = payInfo.getIdEmploye();
            float salaireBrut = payInfo.calculerSalaireBrut();

            // Ajouter des d�tails sp�cifiques de d�duction au rapport
            // Exemple de d�duction : 40% du salaire brut
            float deduction = salaireBrut * 0.4f;

            rapport.ajouterDeduction(idEmploye, deduction);
        }

        return rapport;
    }


    @Override
    public void printPay(List<PayInfo> payInfos) {
        for (PayInfo payInfo : payInfos) {
            System.out.println("Ch�que de paie pour l'employ� " + payInfo.getIdEmploye() + ":");
            System.out.println("Salaire brut: " + payInfo.calculerSalaireBrut());
            // Ajouter d'autres d�tails de paie selon vos besoins
            System.out.println("---------------------------------------------");
        }
    }

    // M�thode pour calculer le salaire brut total � partir des informations de paie
    private float calculerSalaireBrutTotal(List<PayInfo> payInfos) {
        float salaireBrutTotal = 0;

        for (PayInfo payInfo : payInfos) {
            salaireBrutTotal += payInfo.calculerSalaireBrut();
        }

        return salaireBrutTotal;
    }

    
    // M�thode pour calculer les d�ductions totales � partir des informations de paie
    private float calculerDeductionsTotales(List<PayInfo> payInfos) {
        float deductionsTotales = 0;

        for (PayInfo payInfo : payInfos) {
            // Ajouter ici la logique pour calculer les d�ductions sp�cifiques
            // taux d�duction : 40% du salaire brut
            deductionsTotales += payInfo.calculerSalaireBrut() * 0.4f;
        }

        return deductionsTotales;
    }

}
