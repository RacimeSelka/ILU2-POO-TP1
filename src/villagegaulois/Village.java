package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche=new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
		if (chef==null) {
			throw new VillageSansChefException("Le village n'a pas de chef !");
		}
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		int nEtal=marche.trouverEtalLibre();
		StringBuilder chaine = new StringBuilder();
		if (nEtal!=-1) {
			marche.utiliserEtal(nEtal, vendeur, produit, nbProduit);
			chaine.append(vendeur+" cherche un endroit pour vendre "+nbProduit+" "+produit+".\n"+"Le vendeur "+vendeur+"vend ses "+produit+" a l'etal n "+nEtal);
		} else {
			chaine.append("il n y a plus d'etal disponible");
		}
		return chaine.toString();
		
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] VendeurProduit =marche.trouverEtals(produit);
		if (VendeurProduit.length==0) {
			chaine.append("Il n'y a pas de vendeur qui propose des fleurs au marché.\n");
		}else if (VendeurProduit.length==1) {
			chaine.append("seul le vendeur "+VendeurProduit[0].getVendeur()+" propose des "+produit+" au marche");
		}else {
			chaine.append("les vendeurs qui propose des "+produit+"sont:\n");
			for (int i = 0; i < VendeurProduit.length; i++) {
				chaine.append("- "+VendeurProduit[i].getVendeur()+"\n");
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal etalLib=rechercherEtal(vendeur);
		return etalLib.libererEtal();
		
	}
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		if (marche.etals.length>0) {
			chaine.append("Le marché du village "+nom+" possède plusieurs étals :\n");
			chaine.append(marche.afficherMarche());
		} else {
			chaine.append("le marche est vide");
		}
		return chaine.toString();
		
	}
	
	
	
	
	
	
	class Marche {
		
		int nbEtals;
		Etal[] etals;
		
		Marche(int nbEtals) {
			this.nbEtals=nbEtals;
			etals=new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				etals[i]=new Etal();
			}
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		int trouverEtalLibre() {
			int a=-1;
			for (int i = 0; i < nbEtals; i++) {
				if (!(etals[i].isEtalOccupe())) {
					a=i;
					break;
				}
			}
			return a;
		}
		
		Etal[] trouverEtals(String produit) {
			int x=0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].contientProduit(produit)) {
					x+=1;
				}
			}
			Etal[] tabProduit=new Etal[x];
			int y=0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].contientProduit(produit)) {
					tabProduit[y]=etals[i];
					y++;
				}
				
			}
			
			return tabProduit;
			
		}
		
		Etal trouverVendeur(Gaulois gaulois) {
			Etal etalGaulois=null;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].getVendeur()==gaulois) {
					etalGaulois=etals[i];
				}
			}
			return etalGaulois;
		}
		
		String afficherMarche() {
			int nbEtalVide=0;
			StringBuilder chaine = new StringBuilder();
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}else {
					nbEtalVide++;
				}
			}
			chaine.append("Il reste " +nbEtalVide + " étals non utilisés dans le marché.\n.");
			return chaine.toString();
		}
		
		 
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}