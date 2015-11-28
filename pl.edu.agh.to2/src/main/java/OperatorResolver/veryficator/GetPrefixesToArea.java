package pl.edu.agh.veryficator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GetPrefixesToArea {
	private String euro = "Austria, Azory, Belgia, Bu³garia, Chorwacja, Cypr, Czechy, Dania, Estonia, Finlandia, Francja, Gibraltar, Grecja, Gujana Francuska, Gwadelupa, Hiszpania, Holandia, Irlandia, Islandia, Liechtenstein, Litwa, Luksemburg, £otwa, Madera, Malta, Martynika, Norwegia, Niemcy, Portugalia, Reunion, Rumunia, S³owacja, S³owenia, Szwecja, Watykan, Wêgry, Wielka Brytania, W³ochy, Wyspy Kanaryjskie";
	private String first = "Albania, Andora, Bia³oruœ, Boœnia i Hercegowina, Czarnogóra, Kosowo, Macedonia, Monako, Mo³dawia, San Marino, Serbia, Szwajcaria, Turcja, Ukraina, Wyspa Guernsey, Wyspa Jersey, Wyspa Man, Wyspy Owcze";
	private String second = "Kanada, Rosja, Stany Zjednoczone (USA), reszta œwiata";
	private String prefix = "Stany Zjednoczone (USA)	1	Wyspy Owcze	298	Wyspa Man	441624	Wyspa Jersey	441534	Wyspa Guernsey	441481	Serbia	381	Kosowo	383	Czarnogóra	382	Madera	351	Wyspy Kanaryjskie	34	Azory	292	Gwadelupa	590	Martynika	596	Afganistan	93	Alaska	1907	Albania	355	Algieria	213	Andora	376	Angola	244	Antyle Holenderskie	599	Arabia Saudyjska	966	Argentyna	54	Armenia	374	Australia	61	Austria	43	Azerbejd¿an	994	Bahrajn	973	Bangladesz	880	Belgia	32	Benin	229	Bia³oruœ	375	Boliwia	591	Boœnia i Hercegowina	387	Botswana	267	Brazylia	55	Brunei	673	Bu³garia	359	Burkina Faso	226	Burundi	257	Chile	56	Chiny	86	Chorwacja	385	Cook'a Wyspy	682	Cypr	357	Czad	235	Czechy	420	Dania	45	Diego Garcia	246	D¿ibuti	253	Egipt	20	Ekwador	593	Erytrea	291	Estonia	372	Etiopia	251	Falklandy	500	Fid¿i	679	Filipiny	63	Finlandia	358	Francja	33	Gabon	241	Gambia	220	Ghana	233	Gibraltar	350	Grecja	30	Grenlandia	299	Gruzja	995	Guam	1671	Gujana	592	Gujana Francuska	594	Gwinea	224	Gwinea Równikowa	240	Gwinea - Bissau	245	Hawaje	1808	Hiszpania	34	Holandia	31	Hong Kong	852	Indie	91	Indonezja	62	Irak	964	Iran	98	Irlandia	353	Islandia	354	Izrael	972	Japonia	81	Jemen	967	Jordania	962	Jugos³awia	381	Kambod¿a	588	Kamerun	237	Kanada	1	Kanaryjskie Wyspy	34	Katar	974	Kazachstan	7	Kenia	254	Kirgistan	996	Kiribati	686	Kolumbia	57	Komory	269	Kongo	242	Kongo Republika Demokrat.	234	Koera Po³udniowa	82	Koreañska RL-D	850	Kostaryka	506	Kuba	53	Kuwejt	965	Laos	856	Lesotho	266	Liban	961	Liberia	231	Libia	218	Liechtenstein	423	Litwa	370	Luksemburg	352	£otwa	371	Macedonia	389	Madagaskar	261	Makau	853	Malawi	265	Malediwy	960	Malezja	60	Mali	223	Malta	356	Mariany Pó³nocne (Saipan)	1670	Maroko	212	Marshalla Wyspy	692	Mauretania	222	Mauritius	230	Meksyk	52	Mo³dawia	373	Monako	377	Mongolia	976	Mozambik 	258	Myanmar (Birma)	95	Namibia	264	Nauru	674	Nepal	977	Niemcy	49	Niger	227	Nigeria	234	Nikaragua	505	Niue	683	Norfolk Wyspa	672	Norwegia	47	Nowa Kaledonia	687	Nowa Zelandia	64	Oman	968	Owcze Wyspy	298	Pakistan	 92	Palau	680	Palestyna	970	Panama	507	Papua Nowa Gwinea	675	Paragwaj	595	Peru	51	Polinezja Francuska	689	Polska	48	Portugalia	351	PuertoRico	1787	Republika Po³udniowej Afryki	27	Republika Œrodkowoafrykañska	236	Reunion	262	Rosja	7	Rumunia	40	Rwanda	250	Sain Christopher i Nevis	1869	Saint Lucia	1758	Sain Vincent	1809	Salomona Wyspy	677	Salwador	503	Samoa	684	Samoa Zachodnie	685	San Marino	378	Senegal	221	Seszele	248	Sierra Leone	232	Singapur	65	S³owacja	421	S³owenia	386	Somalia	252	Sri Lanka	94	Stany Zjednoczone Ameryki	1	Suazi	268	Sudan	249	Surinam	597	Syria	963	Szwajcaria	41	Szwecja	46	Œw. Heleny Wyspa	290	Œw. Piotra i Mikeleona Wyspy	508	Œw. Tomasza Wyspy	239	Tad¿ykistan	992	Tajlandia	66	Tajwan	886	Tanzania	255	Togo	228	Tokelau	690	Tonga	676	Tunezja	216	Turcja	90	Turkmenistan	993	Tursk (wyspy)	1649	Tuvalu	688	Uganda	256	Ukraina	380	Urugwaj	598	Uzbekistan	998	Vanuatu	678	Wallis i Futuna	681	Watykan	3906	Wenezuela	58	Wêgry	36	Wielka Brytania	44	Wietnam	84	W³ochy	39	Wniebowst¹pienia Wyspa	247	Wybrze¿e Koœci S³oniowej	225	Zambia	260	Zanzibar	259	Zielonego Przyl¹dka Wyspy	238	Zimbabwe	263	 	 	";

	public GetPrefixesToArea() {

		String[] prefixArray = prefix.split("\t");

		String[] euroArray = euro.split(", ");
		String[] firstArray = first.split(", ");
		String[] secondArray = second.split(", ");

		Map<String, Integer> nameToPrefix = new HashMap<>();

		for (int i = 0; i < prefixArray.length - 2; i += 2) {
			nameToPrefix.put(prefixArray[i], Integer.parseInt(prefixArray[i + 1].replace(" ", "")));
		}

		for (int i = 0; i < euroArray.length; i++) {
			nameToPrefix.remove(euroArray[i]);
			// System.out.print("\"" + nameToPrefix.get(euroArray[i]) + "\",");
		}

		for (int i = 0; i < firstArray.length; i++) {
			nameToPrefix.remove(firstArray[i]);
			// System.out.print("\"" + nameToPrefix.get(firstArray[i]) + "\",");
		}

		for (int i = 0; i < secondArray.length; i++) {
			// System.out.println(secondArray[i] + " : " +
			// nameToPrefix.get(secondArray[i]));
			// System.out.print("\"" + nameToPrefix.get(secondArray[i]) +
			// "\",");
		}
		for (Entry<String, Integer> e : nameToPrefix.entrySet()) {
			System.out.print("\"" + e.getValue() + "\",");
		}
	}

}
