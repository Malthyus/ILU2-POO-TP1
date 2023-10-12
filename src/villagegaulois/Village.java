package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtal);
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

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

//	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
//		
//	}

	private static class Marche {
		private Etal[] etals;
		private int nbEtals;

		private Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			this.nbEtals = nbEtals;
			for (int i = 0; i < nbEtals; i++)
				etals[i] = new Etal();
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalsLibre() {
			int iEtal = -1;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].isEtalOccupe())
					iEtal = i;
			}
			return iEtal;
		}

		private Etal[] trouverEtals(String produit) {
			int nbEOccup = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].isEtalOccupe())
					nbEOccup++;
			}
			Etal[] etalsOccup = new Etal[nbEOccup];
			nbEOccup = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].isEtalOccupe()) {
					etalsOccup[nbEOccup] = etals[i];
					nbEOccup++;
				}
			}
			return etalsOccup;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			Etal vendeur = null;
			int i = 0;
			while (i < nbEtals && vendeur == null) {
				if (etals[i].getVendeur() == gaulois)
					vendeur = etals[i];
			}
			return vendeur;
		}

		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int etalsVide = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				} else
					etalsVide++;
			}
			if (etalsVide != 0)
				chaine.append(String.format("Il reste %d étals non utilisés dans le marché", etalsVide));
			return chaine.toString();
		}
	}

}