package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois acheteur=new Gaulois("a",5);
		
		etal.acheterProduit(5,acheteur);
		
		System.out.println("Fin du test");
	}

}
