-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 26 Lis 2023, 11:09
-- Wersja serwera: 10.4.27-MariaDB
-- Wersja PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `biblioteka`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ksiazka`
--

CREATE TABLE `ksiazka` (
  `ID_ksiazki` int(4) NOT NULL,
  `tytul` varchar(30) NOT NULL,
  `autor` varchar(30) NOT NULL,
  `wydawnictwo` varchar(20) NOT NULL,
  `rok_wydania` date NOT NULL,
  `kategoria` enum('klasyczna','naukowa','dla_dzieci','dla_młodziezy','kryminal','triller','fantastyka','podroznicza','historia','romans','popularnonaukowa','dramat') NOT NULL,
  `czy_dostepna` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `ksiazka`
--

INSERT INTO `ksiazka` (`ID_ksiazki`, `tytul`, `autor`, `wydawnictwo`, `rok_wydania`, `kategoria`, `czy_dostepna`) VALUES
(1, 'Ogniem i mieczem', 'Henryk Sienkiewicz', 'Bellona', '1884-05-31', 'klasyczna', 1),
(2, 'Ogniem i mieczem', 'Henryk Sienkiewicz', 'Bellona', '1884-05-31', 'klasyczna', 1),
(3, 'Dewajtis', 'Maria Rodziewiczówna', 'Replika', '1889-06-02', 'klasyczna', 1),
(4, 'Ukochane dziecko', 'Romy Hausmann', 'W.A.B.', '2022-10-05', 'triller', 1),
(5, 'Holly', 'Stephen King', 'Prószyński i S-ka', '2023-09-05', 'kryminal', 1),
(6, 'Kukułcze jajo', 'Camilla Läckberg', 'Czarna Owca', '2023-10-20', 'triller', 1),
(7, 'Clone', 'Aleksandra Negrońska', 'NieZwykłe', '2022-03-08', 'romans', 1),
(8, 'Świat POLI pytaj do woli', 'Irena Mąsior', 'Martel', '2023-09-13', 'dla_dzieci', 1),
(9, 'Gdzie śpiewają raki', 'Delia Owens', 'Świat Książki', '2018-08-14', 'klasyczna', 1),
(10, 'Żar', 'Weronika Mathia', 'Czwarta Strona', '2022-05-17', 'kryminal', 0),
(11, 'Żmijowisko', 'Wojciech Chmielarz', 'Marginesy', '2018-05-08', 'triller', 1),
(12, 'Poszukiwacz zwłok', 'Mieczysław Gorzka', 'Czarna Owca', '2014-10-14', 'triller', 1),
(13, 'Poszukiwacz zwłok', 'Mieczysław Gorzka', 'Czarna Owca', '2014-10-14', 'triller', 1),
(14, 'Intryga nocnych motyli', 'Anna Zawadzka', 'Literack', '2018-03-21', 'kryminal', 1),
(15, 'Zagubiony świat', 'Krzysztof Nowak', 'Znak', '2016-09-12', 'dla_młodziezy', 1),
(16, 'Tajemnice starej willi', 'Joanna Kowalska', 'Filia', '2020-05-08', 'dramat', 1),
(17, 'Niebezpieczne uroki miłości', 'Joanna Sasin', 'Czarna Owca', '2019-02-14', 'romans', 1),
(18, ' Od Darwina do genomiki', 'Richard Dawkins', 'PWN', '2016-08-25', 'naukowa', 1),
(19, 'Miecz Przeznaczenia', 'Andrzej Sapkowski', 'SuperNowa', '1992-05-10', 'fantastyka', 0),
(20, 'W 80 dni dookoła świata', 'Jules Verne', 'XYZ', '1873-11-19', 'podroznicza', 1),
(21, 'Przygody popularnej nauki', 'Carl Sagan', 'XYZ', '1997-09-15', 'popularnonaukowa', 1),
(22, 'Zrozumieć teorię względności', 'John Gribbin', 'XYZ', '2009-03-12', 'naukowa', 1),
(23, 'Harry Potter', 'J.K. Rowling', 'Media Rodzina', '2000-04-27', 'dla_młodziezy', 1),
(24, 'Hamlet', 'William Shakespeare', 'XYZ', '1603-05-21', 'dramat', 1),
(25, 'Dzieje Polski', 'Norman Davies', 'Clio', '2007-09-10', 'historia', 1),
(26, 'Sapiens: Od zwierząt do bogów', 'Yuval Noah Harari', 'Znak', '2014-09-03', 'historia', 1),
(27, 'W 80 dni dookoła świata', 'Jules Verne', 'XYZ', '1873-11-19', 'podroznicza', 1),
(28, 'Szeptani przez bakterie', 'Rob Knight', 'Czarna Owca', '2016-11-10', 'popularnonaukowa', 1),
(29, 'Władca Pierścieni', 'J.R.R. Tolkien', 'Amber', '1954-07-29', 'fantastyka', 1),
(30, 'Złodziejka książek', 'Markus Zusak', 'Nasza Księgarnia', '2007-10-02', 'dla_młodziezy', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `oplata`
--

CREATE TABLE `oplata` (
  `nr_transakcji` int(11) NOT NULL,
  `metoda_zaplaty` enum('gotowka','przelew_bankowy','platnosc_mobilna','karta_kredytowa') NOT NULL,
  `kwota` int(4) NOT NULL,
  `data` date NOT NULL,
  `czy_zaplacono` tinyint(1) NOT NULL,
  `przekroczony_czas` int(4) NOT NULL,
  `nr_wypozyczenia` int(4) NOT NULL,
  `ID_czytelnika` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `oplata`
--

INSERT INTO `oplata` (`nr_transakcji`, `metoda_zaplaty`, `kwota`, `data`, `czy_zaplacono`, `przekroczony_czas`, `nr_wypozyczenia`, `ID_czytelnika`) VALUES
(1, 'karta_kredytowa', 10, '2023-09-24', 1, 17, 2, 6),
(2, 'karta_kredytowa', 10, '2023-09-24', 1, 11, 6, 10);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `recenzje`
--

CREATE TABLE `recenzje` (
  `ID_recenzji` int(11) NOT NULL,
  `ocena` int(1) NOT NULL,
  `komentarz` varchar(60) NOT NULL,
  `data` date NOT NULL,
  `ID_ksiazki` int(4) NOT NULL,
  `ID_czytelnika` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `recenzje`
--

INSERT INTO `recenzje` (`ID_recenzji`, `ocena`, `komentarz`, `data`, `ID_ksiazki`, `ID_czytelnika`) VALUES
(1, 6, 'Ciekawa książka, chociaż spodziewałem się czegoś lepszego', '2023-09-01', 7, 6),
(2, 8, 'Polecam dla najmłodszych', '2023-10-06', 8, 6),
(3, 9, 'Jedna z najlepszych książek jakie przeczytałem', '2023-10-23', 9, 7),
(4, 4, 'Fabuła dość przewidywalna', '2023-11-10', 10, 8),
(5, 8, 'Książka trzyma czytelnika w napięciu ', '2023-09-20', 12, 9);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rezerwacja`
--

CREATE TABLE `rezerwacja` (
  `nr_rezerwacji` int(11) NOT NULL,
  `od_kiedy` date NOT NULL,
  `do_kiedy` date NOT NULL,
  `ID_ksiazki` int(4) NOT NULL,
  `ID_czytelnika` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `rezerwacja`
--

INSERT INTO `rezerwacja` (`nr_rezerwacji`, `od_kiedy`, `do_kiedy`, `ID_ksiazki`, `ID_czytelnika`) VALUES
(1, '2023-10-30', '2023-11-02', 1, 5),
(2, '2023-09-05', '2023-09-08', 13, 5),
(3, '2023-08-07', '2023-08-14', 17, 10);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik`
--

CREATE TABLE `uzytkownik` (
  `ID_uzytkownika` int(4) NOT NULL,
  `imie` varchar(20) NOT NULL,
  `nazwisko` varchar(20) NOT NULL,
  `adres` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `haslo` varchar(50) DEFAULT NULL,
  `rola` enum('pracownik','administrator','czytelnik') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `uzytkownik`
--

INSERT INTO `uzytkownik` (`ID_uzytkownika`, `imie`, `nazwisko`, `adres`, `email`, `haslo`, `rola`) VALUES
(1, 'Dawid', 'Nowak', 'Złota 12, Bydgoszcz 45-534', 'dawid.nowak@gmail.com', 'M/9X01Ygm8so2qJnPTWjytqcDjRBAVXbfBcyQbvpD14=', 'administrator'),
(2, 'Martyna', 'Bielak', 'Focha 54, Kraków 65-768', 'martyna.bielak@gmail.com', 'M/9X01Ygm8so2qJnPTWjytqcDjRBAVXbfBcyQbvpD14=', 'pracownik'),
(3, 'Agnieszka', 'Kowalska', 'Zdobywców 88, Kraków 55-132', 'agnieszka.kowalska@gmail.com', 'M/9X01Ygm8so2qJnPTWjytqcDjRBAVXbfBcyQbvpD14=', 'pracownik'),
(4, 'Stanisław', 'Kwiatkoski', 'Długa 34, Zakopane 85-613', 'stanislaw.kwiatkoski@gmail.com', 'M/9X01Ygm8so2qJnPTWjytqcDjRBAVXbfBcyQbvpD14=', 'pracownik'),
(5, 'Miłosz', 'Ptak', 'Drzymały 15, Bochnia 23-702', 'milosz.ptak@gmail.com', 'M/9X01Ygm8so2qJnPTWjytqcDjRBAVXbfBcyQbvpD14=', 'czytelnik'),
(6, 'Damian', 'Kozak', 'Hetmańska 3, Warszawa 19-180', 'damian.kozak@gmail.com', 'M/9X01Ygm8so2qJnPTWjytqcDjRBAVXbfBcyQbvpD14=', 'czytelnik'),
(7, 'Radosław', 'Dudzic', 'Żegotka 41, Limanowa 11-292', 'radoslaw.dudzic@gmail.com', 'M/9X01Ygm8so2qJnPTWjytqcDjRBAVXbfBcyQbvpD14=', 'czytelnik'),
(8, 'Dominik', 'Duda', 'Pszczelarska 6, Kraków 55-786', 'dominik.duda@gamil.com', 'M/9X01Ygm8so2qJnPTWjytqcDjRBAVXbfBcyQbvpD14=', 'czytelnik'),
(9, 'Natalia', 'Ptaszek', 'Focha 66, Kraków 65-768', 'natalia.ptaszek@gmail.com', 'M/9X01Ygm8so2qJnPTWjytqcDjRBAVXbfBcyQbvpD14=', 'czytelnik'),
(10, 'Angelika', 'Tomala', 'Wodna 16, Warszawa 39-715', 'angelika.tomala@gmail.com', 'M/9X01Ygm8so2qJnPTWjytqcDjRBAVXbfBcyQbvpD14=', 'czytelnik'),
(11, 'Jan', 'Kowalski', 'Krakow', 'jkowalski1@mail.com', 'B9xByilF90f4C3TOyKgLfgxBlYc54h8bd0aktRk2FOA=', 'czytelnik');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wypozyczenie`
--

CREATE TABLE `wypozyczenie` (
  `nr_wypozyczenia` int(4) NOT NULL,
  `od_kiedy` date NOT NULL,
  `do_kiedy` date NOT NULL,
  `kiedy_zwrocono` date DEFAULT NULL,
  `ID_ksiazki` int(4) NOT NULL,
  `ID_czytelnika` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `wypozyczenie`
--

INSERT INTO `wypozyczenie` (`nr_wypozyczenia`, `od_kiedy`, `do_kiedy`, `kiedy_zwrocono`, `ID_ksiazki`, `ID_czytelnika`) VALUES
(1, '2023-07-04', '2023-09-04', '2023-09-02', 7, 6),
(2, '2023-08-09', '2023-10-09', '2023-10-26', 8, 6),
(3, '2023-08-23', '2023-10-23', '2023-10-22', 9, 7),
(4, '2023-09-14', '2023-11-14', '2023-11-11', 10, 8),
(5, '2023-07-26', '2023-09-26', '2023-09-20', 12, 9),
(6, '2023-06-22', '2023-08-22', '2023-09-02', 19, 9),
(7, '2023-09-06', '2023-11-06', '2023-11-04', 27, 6);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zgloszenia`
--

CREATE TABLE `zgloszenia` (
  `nr_zgloszenia` int(11) NOT NULL,
  `temat` varchar(20) NOT NULL,
  `opis_problemu` varchar(50) NOT NULL,
  `data` date NOT NULL,
  `ID_pracownika` int(4) NOT NULL,
  `ID_administratora` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `zgloszenia`
--

INSERT INTO `zgloszenia` (`nr_zgloszenia`, `temat`, `opis_problemu`, `data`, `ID_pracownika`, `ID_administratora`) VALUES
(1, 'Bezpieczeństwo', 'Proszę sprawdzić bezpieczeństwo danych klientów', '2023-10-28', 2, 1),
(2, 'Zgodność', 'Proszę o raport w sprawie wdrażania norm ISO', '2023-10-07', 3, 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `ksiazka`
--
ALTER TABLE `ksiazka`
  ADD PRIMARY KEY (`ID_ksiazki`);

--
-- Indeksy dla tabeli `oplata`
--
ALTER TABLE `oplata`
  ADD PRIMARY KEY (`nr_transakcji`),
  ADD KEY `nr_wypozyczenia` (`nr_wypozyczenia`),
  ADD KEY `ID_czytelnika` (`ID_czytelnika`);

--
-- Indeksy dla tabeli `recenzje`
--
ALTER TABLE `recenzje`
  ADD PRIMARY KEY (`ID_recenzji`),
  ADD KEY `ID_ksiazki` (`ID_ksiazki`),
  ADD KEY `ID_czytelnika` (`ID_czytelnika`);

--
-- Indeksy dla tabeli `rezerwacja`
--
ALTER TABLE `rezerwacja`
  ADD PRIMARY KEY (`nr_rezerwacji`),
  ADD KEY `ID_ksiazki` (`ID_ksiazki`),
  ADD KEY `ID_czytelnika` (`ID_czytelnika`);

--
-- Indeksy dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD PRIMARY KEY (`ID_uzytkownika`);

--
-- Indeksy dla tabeli `wypozyczenie`
--
ALTER TABLE `wypozyczenie`
  ADD PRIMARY KEY (`nr_wypozyczenia`),
  ADD KEY `ID_ksiazki` (`ID_ksiazki`),
  ADD KEY `ID_czytelnika` (`ID_czytelnika`);

--
-- Indeksy dla tabeli `zgloszenia`
--
ALTER TABLE `zgloszenia`
  ADD PRIMARY KEY (`nr_zgloszenia`),
  ADD KEY `ID_pracownika` (`ID_pracownika`),
  ADD KEY `ID_administratora` (`ID_administratora`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `oplata`
--
ALTER TABLE `oplata`
  MODIFY `nr_transakcji` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `recenzje`
--
ALTER TABLE `recenzje`
  MODIFY `ID_recenzji` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `rezerwacja`
--
ALTER TABLE `rezerwacja`
  MODIFY `nr_rezerwacji` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `ID_uzytkownika` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT dla tabeli `wypozyczenie`
--
ALTER TABLE `wypozyczenie`
  MODIFY `nr_wypozyczenia` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `zgloszenia`
--
ALTER TABLE `zgloszenia`
  MODIFY `nr_zgloszenia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `oplata`
--
ALTER TABLE `oplata`
  ADD CONSTRAINT `oplata_ibfk_1` FOREIGN KEY (`nr_wypozyczenia`) REFERENCES `wypozyczenie` (`nr_wypozyczenia`),
  ADD CONSTRAINT `oplata_ibfk_2` FOREIGN KEY (`ID_czytelnika`) REFERENCES `uzytkownik` (`ID_uzytkownika`);

--
-- Ograniczenia dla tabeli `recenzje`
--
ALTER TABLE `recenzje`
  ADD CONSTRAINT `recenzje_ibfk_1` FOREIGN KEY (`ID_ksiazki`) REFERENCES `ksiazka` (`ID_ksiazki`),
  ADD CONSTRAINT `recenzje_ibfk_2` FOREIGN KEY (`ID_czytelnika`) REFERENCES `uzytkownik` (`ID_uzytkownika`);

--
-- Ograniczenia dla tabeli `rezerwacja`
--
ALTER TABLE `rezerwacja`
  ADD CONSTRAINT `rezerwacja_ibfk_1` FOREIGN KEY (`ID_ksiazki`) REFERENCES `ksiazka` (`ID_ksiazki`),
  ADD CONSTRAINT `rezerwacja_ibfk_2` FOREIGN KEY (`ID_czytelnika`) REFERENCES `uzytkownik` (`ID_uzytkownika`);

--
-- Ograniczenia dla tabeli `wypozyczenie`
--
ALTER TABLE `wypozyczenie`
  ADD CONSTRAINT `wypozyczenie_ibfk_1` FOREIGN KEY (`ID_ksiazki`) REFERENCES `ksiazka` (`ID_ksiazki`),
  ADD CONSTRAINT `wypozyczenie_ibfk_2` FOREIGN KEY (`ID_czytelnika`) REFERENCES `uzytkownik` (`ID_uzytkownika`);

--
-- Ograniczenia dla tabeli `zgloszenia`
--
ALTER TABLE `zgloszenia`
  ADD CONSTRAINT `zgloszenia_ibfk_1` FOREIGN KEY (`ID_pracownika`) REFERENCES `uzytkownik` (`ID_uzytkownika`),
  ADD CONSTRAINT `zgloszenia_ibfk_2` FOREIGN KEY (`ID_administratora`) REFERENCES `uzytkownik` (`ID_uzytkownika`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
