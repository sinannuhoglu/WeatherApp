package com.sinannuhoglu.weatherapp.util

object CityListProvider {
    fun getCitiesByCountry(countryCode: String): List<String> {
        return when (countryCode) {
            "TR" -> listOf("Istanbul", "Ankara", "Izmir", "Bursa", "Antalya", "Adana")
            "DE" -> listOf("Berlin", "Hamburg", "Munich", "Cologne", "Frankfurt", "Stuttgart")
            "FR" -> listOf("Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes")
            "IT" -> listOf("Rome", "Milan", "Naples", "Turin", "Palermo", "Florence")
            "ES" -> listOf("Madrid", "Barcelona", "Valencia", "Seville", "Zaragoza", "Bilbao")
            "GB" -> listOf("London", "Birmingham", "Manchester", "Glasgow", "Liverpool", "Leeds")
            "NL" -> listOf("Amsterdam", "Rotterdam", "The Hague", "Utrecht", "Eindhoven", "Groningen")
            "BE" -> listOf("Brussels", "Antwerp", "Ghent", "Bruges", "Leuven", "Liege")
            "CH" -> listOf("Zurich", "Geneva", "Basel", "Bern", "Lausanne", "Lucerne")
            "AT" -> listOf("Vienna", "Graz", "Linz", "Salzburg", "Innsbruck", "Klagenfurt")
            "SE" -> listOf("Stockholm", "Gothenburg", "Malmö", "Uppsala", "Västerås", "Örebro")
            "NO" -> listOf("Oslo", "Bergen", "Stavanger", "Trondheim", "Drammen", "Fredrikstad")
            "FI" -> listOf("Helsinki", "Espoo", "Tampere", "Vantaa", "Oulu", "Turku")
            "DK" -> listOf("Copenhagen", "Aarhus", "Odense", "Aalborg", "Esbjerg", "Randers")
            "PL" -> listOf("Warsaw", "Krakow", "Gdansk", "Wroclaw", "Poznan", "Lodz")
            "CZ" -> listOf("Prague", "Brno", "Ostrava", "Plzen", "Liberec", "Olomouc")
            "HU" -> listOf("Budapest", "Debrecen", "Szeged", "Miskolc", "Pécs", "Győr")
            "PT" -> listOf("Lisbon", "Porto", "Braga", "Coimbra", "Faro", "Setubal")
            "GR" -> listOf("Athens", "Thessaloniki", "Patras", "Heraklion", "Larissa", "Volos")
            "RO" -> listOf("Bucharest", "Cluj-Napoca", "Timisoara", "Iasi", "Constanta", "Brasov")
            "BG" -> listOf("Sofia", "Plovdiv", "Varna", "Burgas", "Ruse", "Stara Zagora")
            "HR" -> listOf("Zagreb", "Split", "Rijeka", "Osijek", "Zadar", "Pula")
            "SK" -> listOf("Bratislava", "Kosice", "Presov", "Nitra", "Zilina", "Trnava")
            "SI" -> listOf("Ljubljana", "Maribor", "Celje", "Kranj", "Velenje", "Novo Mesto")
            "IE" -> listOf("Dublin", "Cork", "Limerick", "Galway", "Waterford", "Drogheda")
            "IS" -> listOf("Reykjavik", "Akureyri", "Kopavogur", "Hafnarfjordur", "Gardabaer", "Mosfellsbaer")

            "JP" -> listOf("Tokyo", "Osaka", "Kyoto", "Nagoya", "Sapporo", "Fukuoka")
            "CN" -> listOf("Beijing", "Shanghai", "Guangzhou", "Shenzhen", "Chengdu", "Wuhan")
            "IN" -> listOf("Delhi", "Mumbai", "Bangalore", "Hyderabad", "Chennai", "Kolkata")
            "SA" -> listOf("Riyadh", "Jeddah", "Mecca", "Medina", "Dammam", "Taif")
            "AE" -> listOf("Dubai", "Abu Dhabi", "Sharjah", "Ajman", "Al Ain", "Fujairah")
            "EG" -> listOf("Cairo", "Alexandria", "Giza", "Luxor", "Aswan", "Tanta")
            "IQ" -> listOf("Baghdad", "Basra", "Erbil", "Mosul", "Najaf", "Kirkuk")
            "IR" -> listOf("Tehran", "Mashhad", "Isfahan", "Shiraz", "Tabriz", "Ahvaz")
            "MA" -> listOf("Casablanca", "Rabat", "Marrakech", "Fez", "Tangier", "Agadir")
            "DZ" -> listOf("Algiers", "Oran", "Constantine", "Annaba", "Blida", "Batna")

            "US" -> listOf("New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia")
            "CA" -> listOf("Toronto", "Vancouver", "Montreal", "Calgary", "Ottawa", "Edmonton")
            "BR" -> listOf("São Paulo", "Rio de Janeiro", "Brasília", "Salvador", "Fortaleza", "Recife")
            "AR" -> listOf("Buenos Aires", "Córdoba", "Rosario", "Mendoza", "La Plata", "San Miguel")
            "MX" -> listOf("Mexico City", "Guadalajara", "Monterrey", "Puebla", "Tijuana", "Leon")
            "CO" -> listOf("Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena", "Cúcuta")
            "CL" -> listOf("Santiago", "Valparaíso", "Concepción", "La Serena", "Antofagasta", "Temuco")

            else -> emptyList()
        }
    }
}
