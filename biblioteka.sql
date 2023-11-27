-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 27 Lis 2023, 20:17
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
-- Struktura tabeli dla tabeli `egzemplarz`
--

CREATE TABLE `egzemplarz` (
  `ID_egzemplarzu` int(4) NOT NULL,
  `wydawnictwo` varchar(20) NOT NULL,
  `rok_wydania` date NOT NULL,
  `czy_dostepna` tinyint(1) NOT NULL,
  `ID_ksiazki` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `egzemplarz`
--

INSERT INTO `egzemplarz` (`ID_egzemplarzu`, `wydawnictwo`, `rok_wydania`, `czy_dostepna`, `ID_ksiazki`) VALUES
(1, 'Bellona', '1884-05-31', 1, 1),
(2, 'Bellona', '1884-05-31', 1, 1),
(3, 'Replika', '1889-06-02', 1, 2),
(4, 'W.A.B.', '2022-10-05', 1, 3),
(5, 'Prószyński i S-ka', '2023-09-05', 1, 4),
(6, 'Czarna Owca', '2023-10-20', 1, 5),
(7, 'NieZwykłe', '2022-03-08', 1, 6),
(8, 'Martel', '2023-09-13', 1, 7),
(9, 'Świat Książki', '2018-08-14', 1, 8),
(10, 'Czwarta Strona', '2022-05-17', 1, 9),
(11, 'Marginesy', '2018-05-08', 1, 10),
(12, 'Czarna Owca', '2014-10-14', 1, 11),
(13, 'Czarna Owca', '2014-10-14', 1, 11),
(14, 'Literack', '2018-03-21', 1, 12),
(15, 'Znak', '2016-09-12', 1, 13),
(16, 'Filia', '2020-05-08', 1, 14),
(17, 'Czarna Owca', '2019-02-14', 1, 15),
(18, 'PWN', '2016-08-25', 1, 16),
(19, 'SuperNowa', '1992-05-10', 1, 17),
(20, 'XYZ', '1873-11-19', 1, 18),
(21, 'XYZ', '1997-09-15', 1, 19),
(22, 'XYZ', '2009-03-12', 1, 20),
(23, 'Media Rodzina', '2000-04-27', 1, 21),
(24, 'XYZ', '1603-05-21', 1, 22),
(25, 'Clio', '2007-09-10', 1, 23),
(26, 'Znak', '2014-09-03', 1, 24),
(27, 'XYZ', '1873-11-19', 1, 18),
(28, 'Czarna Owca', '2016-11-10', 1, 25),
(29, 'Amber', '1954-07-29', 1, 26),
(30, 'Nasza Księgarnia', '2007-10-02', 1, 27);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ksiazka`
--

CREATE TABLE `ksiazka` (
  `ID_ksiazki` int(4) NOT NULL,
  `tytul` varchar(30) NOT NULL,
  `autor` varchar(30) NOT NULL,
  `kategoria` enum('klasyczna','naukowa','dla_dzieci','dla_młodziezy','kryminal','triller','fantastyka','podroznicza','historia','romans','popularnonaukowa','dramat') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `ksiazka`
--

INSERT INTO `ksiazka` (`ID_ksiazki`, `tytul`, `autor`, `kategoria`) VALUES
(1, 'Ogniem i mieczem', 'Henryk Sienkiewicz', 'klasyczna'),
(2, 'Dewajtis', 'Maria Rodziewiczówna', 'klasyczna'),
(3, 'Ukochane dziecko', 'Romy Hausmann', 'triller'),
(4, 'Holly', 'Stephen King', 'kryminal'),
(5, 'Kukułcze jajo', 'Camilla Läckberg', 'triller'),
(6, 'Clone', 'Aleksandra Negrońska', 'romans'),
(7, 'Świat POLI pytaj do woli', 'Irena Mąsior', 'dla_dzieci'),
(8, 'Gdzie śpiewają raki', 'Delia Owens', 'klasyczna'),
(9, 'Żar', 'Weronika Mathia', 'kryminal'),
(10, 'Żmijowisko', 'Wojciech Chmielarz', 'triller'),
(11, 'Poszukiwacz zwłok', 'Mieczysław Gorzka', 'triller'),
(12, 'Intryga nocnych motyli', 'Anna Zawadzka', 'kryminal'),
(13, 'Zagubiony świat', 'Krzysztof Nowak', 'dla_młodziezy'),
(14, 'Tajemnice starej willi', 'Joanna Kowalska', 'dramat'),
(15, 'Niebezpieczne uroki miłości', 'Joanna Sasin', 'romans'),
(16, ' Od Darwina do genomiki', 'Richard Dawkins', 'naukowa'),
(17, 'Miecz Przeznaczenia', 'Andrzej Sapkowski', 'fantastyka'),
(18, 'W 80 dni dookoła świata', 'Jules Verne', 'podroznicza'),
(19, 'Przygody popularnej nauki', 'Carl Sagan', 'popularnonaukowa'),
(20, 'Zrozumieć teorię względności', 'John Gribbin', 'naukowa'),
(21, 'Harry Potter', 'J.K. Rowling', 'dla_młodziezy'),
(22, 'Hamlet', 'William Shakespeare', 'dramat'),
(23, 'Dzieje Polski', 'Norman Davies', 'historia'),
(24, 'Sapiens: Od zwierząt do bogów', 'Yuval Noah Harari', 'historia'),
(25, 'Szeptani przez bakterie', 'Rob Knight', 'popularnonaukowa'),
(26, 'Władca Pierścieni', 'J.R.R. Tolkien', 'fantastyka'),
(27, 'Złodziejka książek', 'Markus Zusak', 'dla_młodziezy');

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
  `ID_egzemplarzu` int(4) NOT NULL,
  `ID_czytelnika` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `rezerwacja`
--

INSERT INTO `rezerwacja` (`nr_rezerwacji`, `od_kiedy`, `do_kiedy`, `ID_egzemplarzu`, `ID_czytelnika`) VALUES
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
  `ID_egzemplarzu` int(4) NOT NULL,
  `ID_czytelnika` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `wypozyczenie`
--

INSERT INTO `wypozyczenie` (`nr_wypozyczenia`, `od_kiedy`, `do_kiedy`, `kiedy_zwrocono`, `ID_egzemplarzu`, `ID_czytelnika`) VALUES
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
-- Indeksy dla tabeli `egzemplarz`
--
ALTER TABLE `egzemplarz`
  ADD PRIMARY KEY (`ID_egzemplarzu`),
  ADD KEY `ID_ksiazki` (`ID_ksiazki`);

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
  ADD KEY `ID_egzemplarzu` (`ID_egzemplarzu`),
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
  ADD KEY `ID_egzemplarzu` (`ID_egzemplarzu`),
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
-- AUTO_INCREMENT dla tabeli `egzemplarz`
--
ALTER TABLE `egzemplarz`
  MODIFY `ID_egzemplarzu` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT dla tabeli `ksiazka`
--
ALTER TABLE `ksiazka`
  MODIFY `ID_ksiazki` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

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
-- Ograniczenia dla tabeli `egzemplarz`
--
ALTER TABLE `egzemplarz`
  ADD CONSTRAINT `egzemplarz_ibfk_1` FOREIGN KEY (`ID_ksiazki`) REFERENCES `ksiazka` (`ID_ksiazki`);

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
  ADD CONSTRAINT `rezerwacja_ibfk_1` FOREIGN KEY (`ID_egzemplarzu`) REFERENCES `egzemplarz` (`ID_egzemplarzu`),
  ADD CONSTRAINT `rezerwacja_ibfk_2` FOREIGN KEY (`ID_czytelnika`) REFERENCES `uzytkownik` (`ID_uzytkownika`);

--
-- Ograniczenia dla tabeli `wypozyczenie`
--
ALTER TABLE `wypozyczenie`
  ADD CONSTRAINT `wypozyczenie_ibfk_1` FOREIGN KEY (`ID_egzemplarzu`) REFERENCES `egzemplarz` (`ID_egzemplarzu`),
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
