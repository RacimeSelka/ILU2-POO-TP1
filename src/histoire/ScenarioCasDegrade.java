package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois acheteur=new Gaulois("a",5);
		
		
		
		try {
			etal.acheterProduit(8,acheteur);
			etal.acheterProduit(0,acheteur);
			
			
		} catch (IllegalStateException e) {
			System.err.println("Erreur : " + e.getMessage());
		}
		catch (IllegalArgumentException e) {
			System.err.println("Erreur : " + e.getMessage());
		} 
		
		System.out.println("Fin du test");
	}

}
