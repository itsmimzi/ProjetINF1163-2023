import java.util.List;

public class PayrollSystem implements Payroll {


	@Override
    public double netFromBrute(List<PayInfo> payInfos) {
        // Calcul du salaire brut total
        double salaireBrutTotal = calculerSalaireBrutTotal(payInfos);
        
        // Calcul des deductions totales
        double deductionsTotales = calculerDeductionsTotales(payInfos);
        
        /// taux déduction 60% du salaire but
        double tauxNet = 0.6; 

        // Calcul du salaire net total
        return (salaireBrutTotal * tauxNet) - deductionsTotales;
    }

    /**
     * Genere un rapport de deductions pour une liste d'informations de paie.
     *
     * @param payInfos Liste des informations de paie.
     * @return Le rapport de deductions.
     */

	@Override
    public Rapport deductionRapport(List<PayInfo> payInfos) {
        Rapport rapport = new Rapport();

        for (PayInfo payInfo : payInfos) {
            int idEmploye = payInfo.getIdEmploye();
            double salaireBrut =  payInfo.calculerSalaireBrut();

            double deduction = salaireBrut * 0.6; //0.6 est le taux de deduction sur salaire brute pour avoir le salaire Net

            rapport.ajouterDeduction(idEmploye, deduction);
        }

        return rapport;
    }


    @Override
    public void printPay(List<PayInfo> payInfos) {
        for (PayInfo payInfo : payInfos) {
            System.out.println("Cheque de paie pour l'employe " + payInfo.getIdEmploye() + ":");
            System.out.println("Salaire brut: " + payInfo.calculerSalaireBrut());
            
            System.out.println("---------------------------------------------");
        }
    }

}
