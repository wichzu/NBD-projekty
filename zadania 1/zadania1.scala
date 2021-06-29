import scala.annotation.tailrec

object zadanie1 {
  val DniTygodnia = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
  val Produkty = Map("krem z SPF50" -> 70, "krem nawilżający na dzień" -> 48, "krem nawilżający na noc" -> 100, "arganowy krem pod oczy" -> 150)
  val Liczby: List[Int] = List(345, 65, 11, 100, 0, -10, 0, 0)

  def main(args: Array[String]): Unit = {
    println("Punkt1:")
    println(Punkt1("a"))
    println(Punkt1("b"))
    println(Punkt1("c"))
    println("Punkt2:")
    println(Punkt2("a", DniTygodnia))
    println(Punkt2("b", DniTygodnia))
    println("Punkt3:")
    println(Punkt3(DniTygodnia))
    println("Punkt4:")
    println(Punkt4("a"))
    println(Punkt4("b"))
    println(Punkt4("c"))
    println("Punkt5")
    println(Punkt5)
    println("Punkt6:")
    Punkt6(("umiem liczyć do", 10, true))
    println("Punkt7:")
    println(Produkty.get("Żel do mycia twarzy"))
    println("Punkt8")
    println(Punkt8(Liczby))
    println("Punkt9:")
    println(Punkt9(Liczby))
    println("Punkt10:")
    println(Punkt10(Liczby))
  }

  ///1.	Stwórz 7 elementową listę zawierającą nazwy dni tygodnia. Napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
  def Punkt1(t: String): String = {
    var s: String = ""
    ///a.	Pętli for
    if (t.equals("a"))
      for (dzien <- DniTygodnia)
        s += dzien + ", "
    else if (t.equals("b")) {
      ///b.	Pętli for wypisując tylko dni z nazwami zaczynającymi się na „P
      for (dzien <- DniTygodnia if dzien.toLowerCase.startsWith("p"))
        s += dzien + ", "
    } else if (t.equals("c")) {
      ///c.	Pętli while
      var i: Int = 0
      while (i < 7) {
        s += DniTygodnia(i) + ", "
        i += 1
      }
    }
    s.substring(0, s.length - 2)
  }

  ///2.	Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
  def Punkt2(t: String, l: List[String]): String = {
    if (t == "a") {
      ///a.	Funkcji rekurencyjnej
      if (l.tail.isEmpty)
        return l.head
      return l.head + ", " + Punkt2("a", l.tail)
    }
    if (t == "b") {
      ///b.	Funkcji rekurencyjnej wypisując elementy listy od końca
      if (l.tail.isEmpty)
        return l.head
      return Punkt2("a", l.tail) + ", " + l.head
    } else return ""
  }

  ///3.	Stwórz funkcję korzystającą z rekurencji ogonowej do zwrócenia oddzielonego przecinkami stringa zawierającego elementy listy z ćwiczenia 1
  def Punkt3(ll: List[String]): String = {
    @tailrec
    def Punkt3(l: List[String], s: String): String = {
      if (l.tail.isEmpty)
        return s + l.head
      else Punkt3(l.tail, s + l.head + ", ")
    }

    Punkt3(ll, "")
  }
  ///4.	Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:

  def Punkt4(t: String): String = {
    var s: String = ""
    ///a.	Metody foldl
    if (t.equals("a")) {
      s = DniTygodnia.foldLeft("")(_ + _ + ", ")
      /// b.	Metody foldr
    } else if (t.equals("b")) {
      s = DniTygodnia.foldRight("")(_ + ", " + _)
      ///c.	Metody foldl wypisując tylko dni z nazwami zaczynającymi się na „P
    } else if (t.equals("c"))
      s = DniTygodnia.foldRight("") { (next, sum) => if (next.toLowerCase.startsWith("p")) next + ", " + sum else sum }
    return s.substring(0, s.length - 2)
  }
  ///5.	Stwórz mapę z nazwami produktów i cenami. Na jej podstawie wygeneruj drugą, z 10% obniżką cen. Wykorzystaj mechanizm mapowania kolekcji.

  val Punkt5 = Produkty.map({
    case (produkt, cena) => (produkt, cena * .9)
  })

  ///6.	Zdefiniuj funkcję przyjmującą krotkę z 3 wartościami różnych typów i wypisującą je
  def Punkt6(tuple: Tuple3[String, Int, Boolean]): Unit = {
    println(tuple)
  }
  ///7.	Zaprezentuj działanie Option na dowolnym przykładzie (np. mapy, w której wyszukujemy wartości po kluczu)

  val Punkt7 = Produkty.get("Żel do mycia twarzy")
  Punkt7 match {
    case Some(cena) => println(cena)
    case None => println("None")
  }

  ///8.	Napisz funkcję usuwającą zera z listy wartości całkowitych (tzn. zwracającą listę elementów mających wartości różne od 0).  Wykorzystaj rekurencję.
  def Punkt8(list: List[Int]): List[Int] = {
    @tailrec
    def UsunZbycha(remainingList: List[Int], result: List[Int]): List[Int] = {
      if (remainingList.isEmpty) result
      else if (remainingList.head != 0) {
        UsunZbycha(remainingList.tail, result.appended(remainingList.head))
      } else {
        UsunZbycha(remainingList.tail, result)
      }
    }

    UsunZbycha(list, List())
  }

  ///9.	Zdefiniuj funkcję, przyjmującą listę liczb całkowitych i zwracającą wygenerowaną na jej podstawie listę, w której wszystkie liczby zostały zwiększone o 1. Wykorzystaj mechanizm mapowania kolekcji.
  def Punkt9(list: List[Int]): List[Int] = {
    list.map(l => l + 1)
  }

  ///10.	Stwórz funkcję przyjmującą listę liczb rzeczywistych i zwracającą stworzoną na jej podstawie listę zawierającą wartości bezwzględne elementów z oryginalnej listy należących do przedziału <-5,12>. Wykorzystaj filtrowanie.
  def Punkt10(Liczby: List[Int]): List[Int] = {
    val ListaPrzefiltrowana = Liczby filter (l => l >= -5 && l <= 12)

    ListaPrzefiltrowana map (l => l.abs)
  }
}


