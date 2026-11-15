
/*
Zadanie 2 (medium, 25 minut)

        Napisz metodę, która zwraca długość najdłuższego podciągu (ciągłego fragmentu) stringa,
        w którym żaden znak się nie powtarza.

java
int longestUniqueSubstring(String s)

"abcabcbb"  -> 3   // "abc"
        "bbbbb"     -> 1   // "b"
        "pwwkew"    -> 3   // "wke" (nie "pwke", bo to nie jest ciągły fragment)
        ""          -> 0
        "dvdf"      -> 3   // "vdf", podchwytliwy przypadek
*/

import java.util.*;

public class Zad2 {
    public static void main(String[] args) {
        longestUniqueSubstring("abcabcbb");
    }

    /*
     Plan:
     1. Mapa: znak -> ostatni indeks, pod którym go widziałem.
     2. right idzie po stringu.
     3. Jeśli znak jest w mapie I jego ostatni indeks jest w oknie (>= left),
        przesuwam left na ostatni indeks + 1.
     4. Zapisuję bieżący indeks znaku w mapie, aktualizuję max.
     Złożoność: O(n) czasu (jedno przejście, bez wewnętrznej pętli),
                O(k) pamięci, k = liczba różnych znaków.
    */


/*    "abcabcbb"	3 ("abc")	podstawowy przypadek
"bbbbb"	1 ("b")	same powtórzenia
"pwwkew"	3 ("wke")	najdłuższy fragment nie jest na początku
""	0	pusty string
"dvdf"	3 ("vdf")	left ma przeskoczyć tylko pierwsze d, nie wszystko
"abc"	3	brak powtórzeń, wynik liczony do samego końca
"abba"	2 ("ab" / "ba")	stary wpis w mapie, left nie może się cofnąć
"a"	1	jeden znak
" "	1	spacja to też znak
"au"	2	dwa różne znaki
"tmmzuxt"	5 ("mzuxt")	duplikat t jest poza ramką
"anviaj"	5 ("nviaj")	duplikat w środku ramki*/
    public static int longestUniqueSubstring(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int left = 0;
        int max = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastIndex.containsKey(c) && lastIndex.get(c) >= left) {
                left = lastIndex.get(c) + 1;
            }
            lastIndex.put(c, right);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
