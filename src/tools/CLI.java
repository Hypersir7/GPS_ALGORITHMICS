package tools;

public class CLI{
	public static void displayTypeWriter(String txt){
        for(char c : txt.toCharArray()){
            System.out.print(c);
            System.out.flush();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static void showLogo(){
    	System.out.println("\n\n");
        System.out.println("======================================");
   		System.out.println("||   	🚆 GPS Transport CLI 🚏     ||");
        System.out.println("======================================");
    }

    public static void showLoadingMsg(){
    	displayTypeWriter("🔄⏳ Chargement des données en cours...");
    }

    public static void showDataLoaded(){
    	displayTypeWriter("✅ Les données sont prêtes...");
    }
    public static void showSearchingStart(){
    	displayTypeWriter("🔎 ⏳Recherche du plus cours chemin en cours...");
    }
    public static void showSearchingEnd(long duration){
    	displayTypeWriter("⏳ Recherche terminée en " + duration + " ms");
    }
	public static void showResultTitle(){
    	displayTypeWriter("➡️ Résultat");
    }
}