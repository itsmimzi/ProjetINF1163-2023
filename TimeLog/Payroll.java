import java.util.List;

public interface Payroll {

    
   //  Calcule le salaire net total à partir des informations de paie.
     
   public double netFromBrute(List<PayInfo> payInfos);

    
     // Génère un rapport de déductions pour une liste d'informations de paie.
     
   public Rapport deductionRapport(List<PayInfo> payInfos);

   
     //Affiche les chèques de paie pour une liste d'informations de paie.
     
    public void printPay(List<PayInfo> payInfos);

}

