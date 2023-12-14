import java.util.List;

public interface PayrollInterface {

    /**
     * Calcule le salaire net total à partir des informations de paie.
     */
   public float netFromBrute(List<PayInfo> payInfos);

    /**
     * Génère un rapport de déductions pour une liste d'informations de paie.
     */
   public Rapport deductionRapport(List<PayInfo> payInfos);

    /**
     * Affiche les chèques de paie pour une liste d'informations de paie.
     */
    public void printPay(List<PayInfo> payInfos);

}

