import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Program {
	public static void main(String[] args) throws IOException {
		Path fileName = Path.of("teksti.txt");
		String teksti = Files.readString(fileName).toLowerCase();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(teksti);

		ArrayList<Kirjain> suomiKirjaimet = new ArrayList<Kirjain>();
		suomiKirjaimet.add(new Kirjain('a', 0.1222));
		suomiKirjaimet.add(new Kirjain('b', 0.0028));
		suomiKirjaimet.add(new Kirjain('c', 0.0028));
		suomiKirjaimet.add(new Kirjain('d', 0.0104));
		suomiKirjaimet.add(new Kirjain('e', 0.0797));
		suomiKirjaimet.add(new Kirjain('f', 0.0019));
		suomiKirjaimet.add(new Kirjain('g', 0.0039));
		suomiKirjaimet.add(new Kirjain('h', 0.0185));
		suomiKirjaimet.add(new Kirjain('i', 0.01082));
		suomiKirjaimet.add(new Kirjain('j', 0.0204));
		suomiKirjaimet.add(new Kirjain('k', 0.0497));
		suomiKirjaimet.add(new Kirjain('l', 0.0576));
		suomiKirjaimet.add(new Kirjain('m', 0.032));
		suomiKirjaimet.add(new Kirjain('n', 0.0883));
		suomiKirjaimet.add(new Kirjain('o', 0.0561));
		suomiKirjaimet.add(new Kirjain('p', 0.0184));
		suomiKirjaimet.add(new Kirjain('q', 0.0001));
		suomiKirjaimet.add(new Kirjain('r', 0.0287));
		suomiKirjaimet.add(new Kirjain('s', 0.0786));
		suomiKirjaimet.add(new Kirjain('t', 0.0875));
		suomiKirjaimet.add(new Kirjain('u', 0.0501));
		suomiKirjaimet.add(new Kirjain('v', 0.0225));
		suomiKirjaimet.add(new Kirjain('w', 0.0009));
		suomiKirjaimet.add(new Kirjain('x', 0.0003));
		suomiKirjaimet.add(new Kirjain('y', 0.0174));
		suomiKirjaimet.add(new Kirjain('z', 0.0005));
		suomiKirjaimet.add(new Kirjain('å', 0.000));
		suomiKirjaimet.add(new Kirjain('ä', 0.0358));
		suomiKirjaimet.add(new Kirjain('ö', 0.0044));

		Collections.sort(suomiKirjaimet);

		ArrayList<Kirjain> koodiKirjaimet = new ArrayList<Kirjain>();

		for (Kirjain k : suomiKirjaimet) {
			koodiKirjaimet.add(new Kirjain(k.kirjain, 0.0));
		}

		char[] chars = teksti.toCharArray();
		int kirjaimetCount = 0;

		for (char c : chars) {
			if (c != ' ') {
				kirjaimetCount++;
				for (Kirjain k : koodiKirjaimet) {
					if (k.kirjain == c) {
						k.esiintymisKerrat++;
						break;
					}
				}
			}
		}

		for (Kirjain k : koodiKirjaimet) {
			k.yleisyys = (double)k.esiintymisKerrat / kirjaimetCount;
		}

		Collections.sort(koodiKirjaimet);

		ArrayList<KirjainPari> kirjainPariLista = new ArrayList<KirjainPari>();

		for (Kirjain k : koodiKirjaimet) {
			if (k.esiintymisKerrat > 0) {
				KirjainPari newKirjainPari = new KirjainPari(k.kirjain);
				kirjainPariLista.add(newKirjainPari);
			}
		}

		while(true) {
			double maxYleisyys = koodiKirjaimet.get(0).yleisyys;

			for (int i = 10; i >= 0; i--) {
				for (Kirjain k : suomiKirjaimet) {
					if (k.yleisyys > i * 0.01222) System.out.print(". ");
					else System.out.print("  ");
				}

				System.out.print("  ");

				for (Kirjain k : koodiKirjaimet) {
					if (k.yleisyys > i * maxYleisyys * 0.1) System.out.print(". ");
					else System.out.print("  ");
				}
				System.out.println();
			}

			for (Kirjain k : suomiKirjaimet) {
				System.out.print(k.kirjain + " ");
			}

			System.out.print("  ");

			for (Kirjain k : koodiKirjaimet) {
				for (KirjainPari p : kirjainPariLista) {
					if (p.koodattu == k.kirjain) {
						System.out.print(p.orginal + " ");

						break;
					}
				}
			}

			System.out.println("\n\nMuuta kirjainta (pelkkä ENTER lopettaa, oe = ö, ae = ä): ");
			String muutettavaKirjain = reader.readLine();

			if (muutettavaKirjain.length() < 1) {
				break;
			}

			System.out.println("Uusi kirjain (pelkkä ENTER lopettaa, oe = ö, ae = ä): ");
			String uusiKirjain = reader.readLine();

			if (uusiKirjain.length() < 1) {
				break;
			}

			char oldChar = muutettavaKirjain.charAt(0);
			char newChar = uusiKirjain.charAt(0);
			
			if (muutettavaKirjain.length() > 1) {
				if (muutettavaKirjain.charAt(0) == 'o' && muutettavaKirjain.charAt(0) == 'e') oldChar = 'ö';
				else if (muutettavaKirjain.charAt(0) == 'a' && muutettavaKirjain.charAt(0) == 'e') oldChar = 'ä';
			}

			if (uusiKirjain.length() > 1) {
				if (uusiKirjain.charAt(0) == 'o' && uusiKirjain.charAt(0) == 'e') newChar = 'ö';
				else if (uusiKirjain.charAt(0) == 'a' && uusiKirjain.charAt(0) == 'e') newChar = 'ä';
			}

			if (Character.isLetter(oldChar) && Character.isLetter(newChar)) {
				for (int i = 0; i < kirjainPariLista.size(); i++) {
					if (kirjainPariLista.get(i).orginal == oldChar) kirjainPariLista.get(i).orginal = newChar;
					else if (kirjainPariLista.get(i).orginal == newChar) kirjainPariLista.get(i).orginal = oldChar;
				}

				System.out.println(oldChar +" korvattiin kirjaimella " +newChar);
			}

			char[] merkkijono = teksti.toCharArray();

			for (char c : merkkijono) {
				if (c == ' ') System.out.print(c);
				else {
					for (KirjainPari k : kirjainPariLista) {
						if (k.koodattu == c) {
							System.out.print(k.orginal);
							break;
						}
					}
				}
			}

			System.out.println();
		}
	}
}

class Kirjain implements Comparable<Kirjain> {
	char kirjain;
	double yleisyys;
	int esiintymisKerrat;
	int sijoitus;

	public Kirjain(char kirjain, double yleisyys) {
		this.kirjain = kirjain;
		this.yleisyys = yleisyys;
		this.esiintymisKerrat = 0;
		sijoitus = 1;
	}

	@Override
	public int compareTo(Kirjain o) {
		return -Double.compare(this.yleisyys, o.yleisyys);
	}
}

class KirjainPari {
	char koodattu;
	char orginal;

	public KirjainPari(char koodattu) {
		this.koodattu = koodattu;
		this.orginal = koodattu;
	}
}