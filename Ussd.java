package exercice2;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ussd{
    private static String currentScreen = "Servicce USSD", previousScreen = "";

    //getter/setter
    public static String getCurrentScreen (){
        return Ussd.currentScreen;
    }
    public static void setCurrentScreen (String currentScreen){
        Ussd.currentScreen = currentScreen;
    }

    public static String getPreviousScreen (){
        return Ussd.previousScreen;
    }
    public static void setPreviousScreen (String previousScreen){
        Ussd.previousScreen = previousScreen;
    }
    

	public static void showScreen (String code){
        String message = "";
        if (Ussd.getCurrentScreen().equals("Servicce USSD")){
		    if (code.equals("#123#")){
                Ussd.setCurrentScreen("ACCUEIL");
                message = "Bienvenue sur #123#\n 1: Mon solde\n 2: Mon numéro";
                
            }
            else{
                
                JOptionPane.showMessageDialog(null, "Erreur : code USSD invalide.");
                System.exit(0);
            }
        }
        else if (Ussd.getCurrentScreen().equals("ACCUEIL")){
            Ussd.setPreviousScreen(Ussd.getCurrentScreen());
            if (code.equals("1")){
                Ussd.setPreviousScreen(Ussd.getCurrentScreen());
                Ussd.setCurrentScreen("SOLDE");
                message = "Il vous reste 56 min d’appels et 450 Mo. \n 8: Retour";
            }
        
            else if (code.equals("2") ){
                Ussd.setCurrentScreen("NUMERO");
                message = "Mon numéro de téléphone est 0699999999. \n 8: Retour";
            }
            else {
                Ussd.setCurrentScreen("ERREUR");
                message = "Erreur : entrée invalide. \n 8: Retour";

            }
        }
        else if (Ussd.getCurrentScreen().equals("SOLDE") ||Ussd.getCurrentScreen().equals("NUMERO")){
            if (code.equals("8")){//Pour retouner à l'écran d'accueil à partir des écrans SOLDE ou NUMERO
                Ussd.setCurrentScreen("Servicce USSD");
                showScreen("#123#");  //fonction récursive
            }
            else {
                Ussd.setPreviousScreen(Ussd.getCurrentScreen());
                Ussd.setCurrentScreen("ERREUR");
                message = "Erreur : entrée invalide. \n 8: Retour";
            }
        }
        else if (Ussd.getCurrentScreen().equals("ERREUR")){
            if (code.equals("8")){
                //Pour retourner aux écrans SOLDE ou NUMERO à partir de l'écran ERREUR
                    System.out.println("Test");
                if (Ussd.previousScreen.equals("SOLDE")){//Erreur commise à partir de l'écran solde
                    //Ecran "solde"  = écran "accueil" + code "1"
                    Ussd.setCurrentScreen("ACCUEIL"); 
                    showScreen("1");
                }
                else if (Ussd.previousScreen.equals("NUMERO")){
                    //Ecran "numero" = écran "accueil" + code "2"
                    Ussd.setCurrentScreen("ACCUEIL");
                    showScreen("2");
                }
                else if (Ussd.previousScreen.equals("ACCUEIL")){
                    //Accueil = écran "Servicce USSD" + code "#123#"
                    Ussd.setCurrentScreen("Servicce USSD");
                    showScreen("#123#");  
                }    
            }
            else{//Erreur commise à partir de l'écran ERREUR
                Ussd.setCurrentScreen("ERREUR");
                message = "Erreur : entrée invalide. \n 8: Retour";
            }
        }
                
        

            String option = JOptionPane.showInputDialog( null, message, currentScreen, JOptionPane.PLAIN_MESSAGE );
            showScreen(option);
           


	}

    public static void main(String[] args) {
        // A l'exécution du programme, un écran s'affiche pour permettre à l'utilisateur de taper le code ussd
        String code = JOptionPane.showInputDialog(null, "Saisir le code USSD", "Service USSD", JOptionPane.PLAIN_MESSAGE );
        showScreen(code);

    }
}
