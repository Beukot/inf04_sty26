import random
import unittest


class Kosc:
    liczba_instancji = 0

    def __init__(self, wartosc=None):
        Kosc.liczba_instancji += 1
        self.obrazy_kosci = ["kosc0.png", "kosc1.png", "kosc2.png", "kosc3.png", "kosc4.png", "kosc5.png", "kosc6.png"]

        if wartosc is None:
            wylosowana = random.randint(1, 6)
            self.wartosc = wylosowana
            self.id_obrazku = wylosowana
            self.czy_dostepna = True
        else:
            self.wartosc = wartosc
            self.id_obrazku = wartosc
            self.czy_dostepna = True

    def losuj(self):
        if self.czy_dostepna:
            wylosowana = random.randint(1, 6)
            self.wartosc = wylosowana
            self.id_obrazku = wylosowana

    def zablokuj(self):
        self.czy_dostepna = False

    def wypisz_wynik(self):
        if self.wartosc == 1:
            print("jeden")
        elif self.wartosc == 2:
            print("dwa")
        elif self.wartosc == 3:
            print("trzy")
        elif self.wartosc == 4:
            print("cztery")
        elif self.wartosc == 5:
            print("pięć")
        elif self.wartosc == 6:
            print("sześć")


kosc1 = Kosc()
print("liczba instancji: ", kosc1.liczba_instancji)
print("wartosc kosci: ", kosc1.wartosc)
kosc1.wypisz_wynik()

wartosc = int(input("podaj wartosc kostki: "))

kosc2 = Kosc(wartosc)
print("liczba instancji: ", kosc2.liczba_instancji)
print("wartosc kosci: ", kosc2.wartosc)
kosc2.wypisz_wynik()

class testy_jednostkowe(unittest.TestCase):
    def test_obrazy_kosci(self):
        kosc3 = Kosc()
        self.assertTrue(1 <= kosc3.wartosc <= 6)
    def test_czy_wartosc_zostaje_po_blokadzie(self):
        kosc4 = Kosc(3)
        kosc4.zablokuj()
        self.assertTrue(kosc4.wartosc == 3)