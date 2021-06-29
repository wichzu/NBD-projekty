object Zadanie2 {

  ///1.	Wykorzystaj Pattern Matching w funkcji przyjmującej parametr typu String. Dla stringów odpowiadających nazwom dni tygodnia funkcja ma zwrócić „Praca” i „Weekend” (odpowiednio dla dni roboczych i wolnych), dla pozostałych napisów „Nie ma takiego dnia”.
  def main(args: Array[String]) = {
    val ListaDni = List("poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziela")
    print("punkt 1:")
    CzyDzisiajMamyWolne(ListaDni)
    print("\n")


    def CzyDzisiajMamyWolne(ListaDni: List[String]) = {
      for (d <- ListaDni) println(CoZaDzien(d))
    }

    def CoZaDzien(day: String): String = {
      day match {
        case "poniedziałek" => "Praca"
        case "wtorek" => "Praca"
        case "środa" => "Praca"
        case "czwartek" => "Praca"
        case "piątek" => "Praca"
        case "sobota" => "Weekend"
        case "niedziela" => "Weekend"
        case _ => "Nie ma takiego dnia"
      }
    }
  }
  ///2.	Zdefiniuj klasę KontoBankowe z metodami wplata i wyplata oraz własnością stanKonta - własność ma być tylko do odczytu. Klasa powinna udostępniać konstruktor przyjmujący początkowy stan konta oraz drugi, ustawiający początkowy stan konta na 0.


  class KontoBankowe(private var _stanKonta: BigDecimal = 0) {

    def stanKonta: BigDecimal = _stanKonta

    def wplata(wartosc: BigDecimal): Unit = {
      _stanKonta = stanKonta + wartosc
    }

    def wyplata(wartosc: BigDecimal): Unit = {
      _stanKonta = stanKonta - wartosc
    }

    override def toString: String = s"Konto: <${_stanKonta.toString}>"
  }

  def test(konto: KontoBankowe, title: String): Unit = {
    println("Punkt2:")
    println(s"Test: $title")
    println("Bieżący stan konta: " + konto.stanKonta)
    println("Wpłacono 550.")
    konto.wplata(550)
    println("Bieżący stan konta: " + konto.stanKonta)
    println("Wypłacono 100.")
    konto.wyplata(100)
    println("Bieżący stan konta: " + konto.stanKonta)
  }

  val kontoBankowe = new KontoBankowe(20000)
  test(kontoBankowe, "20000 - initialized bank account")

  val kontoBankoweZero = new KontoBankowe()
  test(kontoBankoweZero, "Zero-initialized bank account")

  ///3.	Zdefiniuj klasę Osoba z własnościami imie i nazwisko. Stwórz kilka instancji tej klasy. Zdefiniuj funkcję, która przyjmuje obiekt klasy osoba i przy pomocy Pattern Matching wybiera i zwraca napis zawierający przywitanie danej osoby. Zdefiniuj 2-3 różne przywitania dla konkretnych osób (z określonym imionami lub nazwiskami) oraz jedno domyślne.

  case class Osoba(var imie: String, var nazwisko: String)
  val Ziomeczek1 = new Osoba("Ziomek", "Ziomkowski")
  val Ziomeczek2 = new Osoba("Ziomisław", "Ziomisławowski")
  val Ziomeczek3 = new Osoba("Ziomson", "Ziomsonow")
  val Ziomeczka = new Osoba("Ziomeczka", "Ziomeczkowa")
  def Powitanko(someone: Osoba): String = someone match {
    case Osoba("Ziomson", "Ziomsonow") => s"Hejka!"
    case Osoba("Ziomek", "Ziomkowski") => s"Siemka"
    case Osoba("Ziomeczka", "Ziomeczkowa")  => s"Bążur"
    case _                              => "Uszanowanko"
  }
  println("Punkt3:")
  println(Powitanko(Ziomeczek1))
  println(Powitanko(Ziomeczek2))
  println(Powitanko(Ziomeczek3))
  println(Powitanko(Ziomeczka))

  ///4.	Zdefiniuj funkcję przyjmującą dwa parametry - wartość całkowitą i funkcję operującą na wartości całkowitej. Zastosuj przekazaną jako parametr funkcję trzykrotnie do wartości całkowitej i zwróć wynik.

  def Punkt4(int: Int, funkcja: Int => Int) = funkcja(funkcja(funkcja(int)))

  println("Punkt4:")
  println(Punkt4(1, x => x ^ 4))
  println(Punkt4(2, x => 3 * x))

  ///5.	Zdefiniuj klasę Osoba i trzy traity: Student, Nauczyciel, Pracownik. Osoba powinna mieć własności read only: imie, nazwisko, podatek. Pracownik powinien mieć własność pensja (z getterem i seterem). Student i Pracownik powinni przesłaniać własność podatek – dla Studenta zwracamy 0, dla Pracownika 20% pensji. Nauczyciel powinien dziedziczyć z Pracownika, dla niego podatek zwraca 10% pensji. Stwórz obiekty z każdym z traitów, pokaż jak podatek działa dla każdego z nich. Stwórz obiekty z traitami Student i Pracownik, pokaż jak podatek zadziała w zależności od kolejności w jakiej te traity zostały dodane przy tworzeniu obiektu.
println("Punkt5 i ostatni (w Scali)")
  class Osoba1(private var _imie: String, private var _nazwisko: String, private var _podatek: Double) {
    def imie1: String = _imie
    def nazwisko1: String = _nazwisko
    def podatek1: Double = _podatek
  }
  trait Student extends Osoba1 {
    override def podatek1: Double = 0
  }

  trait Pracownik extends Osoba1 {
    var pensja: Int = 0

    override def podatek1: Double = 0.2 * pensja
  }

  trait Nauczyciel extends Pracownik {
    override def podatek1: Double = 0.1 * pensja
  }


  val student = new Osoba1("Anna", "Mucha", 20) with Student
  val nauczyciel = new Osoba1("Dżoana", "Krupa", 10) with Nauczyciel
  nauczyciel.pensja = 2800
  val pracownik = new Osoba1("Katarzyna", "Cichopek", 30) with Pracownik
  pracownik.pensja = 3000

  println("podatek od bycia studentem: " + student.podatek1, "podatek od bycia nauczycielem:" + nauczyciel.podatek1, "podatek od pracowania:" + pracownik.podatek1)

  val trocheStudentATrochePracownik = new Osoba1("Anna", "Maria", 10) with Student with Pracownik
  trocheStudentATrochePracownik.pensja = 1000
  val trochePracownikATrocheStudent = new Osoba1("Beata", "Kozidrak", 20) with Pracownik with Student
  trochePracownikATrocheStudent.pensja = 2000

  println("Podatek jak jestes studentem i pracownikiem:" + trocheStudentATrochePracownik.podatek1, "Podatek jak jestes pracownikiem i studentem:" + trochePracownikATrocheStudent.podatek1)

}
