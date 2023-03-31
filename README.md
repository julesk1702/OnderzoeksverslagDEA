# DEA Onderzoeksverslag
Jules Koster (1653865) en Suzanne Coenen (1666029)
Bart van der Wal
ITA-OOSE-A-f
Versie 1.0
31 maart 2023

___

## Inhoudsopgave

1. [Inleiding](#1-inleiding)
   1. [Onderwerp](#11-onderwerp)
   2. [Onderzoeksvraag](#12-onderzoeksvraag)
   3. [Deelvragen](#13-deelvragen)
   4. [Onderzoeksmethoden](#14-onderzoeksmethoden)

2. [Theoretisch Kader en Literatuuronderzoek](#2-theoretisch-kader-en-literatuuronderzoek)
   1. [Verschillende Soorten Niet-Relationele Databases](#21-verschillende-soorten-niet-relationele-databases)
      1. [Documentgeoriënteerde Databases](#211-documentgeoriënteerde-databases)
      2. [Key-value Databases](#212-key-value-databases)
      3. [Kolomgeoriënteerde Databases](#213-kolomgeoriënteerde-databases)
      4. [Graph Databases](#214-graph-databases)

   2. [Join-Operaties in Niet-Relationele Databases](#22-join-operaties-in-niet-relationele-databases)
       	1. [Join-Operaties in MongoDB](#221-join-operaties-in-mongodb)
       	2. [Join-Operaties in Redis](#222-join-operaties-in-redis)
       	3. [Join-Operaties in Apache Cassandra](#223-join-operaties-in-apache-cassandra)
       	4. [Join-Operaties in Neo4j](#224-join-operaties-in-neo4j)
   3. [Conclusie](#23-conclusie)
3. [Onderzoeksresultaten](#3-onderzoeksresultaten)
4. [Conclusie](#4-conclusie)
5. [Bronnen](#5-bronnen)


## 1 Inleiding

Suzanne en ik onderzoeken de mogelijkheden van databases. We willen met name de mogelijkheid onderzoeken of het mogelijk is een traditionele relationele database te vervangen door een niet-rationele. Om dit te bereiken zullen we onze aandacht richten op de voordelen, nadelen en potentiële veiligheidsrisico's die zich voor kunnen doen bij de migratie van MySQL naar een niet-relationele database. 

Ons onderzoek omvat informatie vergaren door middel van de SWOT analysis methode en het maken van een prototype voor de SpotiTube-applicatie, die de huidige relationele database zal vervangen door een niet-relationele, in dit geval MongoDB. Op die manier hopen we de verschillen tussen de twee databasesystemen aan te tonen, inclusief de potentiële voor- en nadelen van het gebruik van een niet-relationele database in plaats van een relationele. 

Met dit onderzoek hopen we inzicht te verschaffen om de keuze of je nu wel of niet zou overstappen van een relationele database naar een niet relationele database. Door de uitdagingen en mogelijkheden van niet-relationele databases te onderzoeken, hopen we een uitgebreid overzicht te geven van hun functionaliteit en geschiktheid voor verschillende toepassingen. 

### 1.1 Onderwerp

Het onderwerp van dit onderzoek is: “Het vervangen van een relationele SQL-database door een niet relationele (NoSQL) database.” Dit onderwerp richt zich op het verkennen van de mogelijkheden en uitdagingen van het vervangen van het al bestaande SQL database door een niet relationele database. Het onderzoek zal zich onder andere richten op de verschillen tussen SQL en NoSQL databases, wat de voor- en nadelen zijn van het gebruik van een NoSQL database ten opzichte van een SQL database, en de prestaties en schaalbaarheid van een NoSQL database in vergelijking met SQL databases. Door deze aspecten te onderzoeken, kan worden gekeken of het vervangen van een QL-database door een NoSQL-database gunstig is voor de Spotitube applicatie.

### 1.2 Onderzoeksvraag

Onze hoofdvraag voor dit onderzoeksverslag luidt alsvolgt: “Wat zijn de voor- en nadelen van het vervangen van de relationele database door een niet relationele database?”. 

Om deze vraag te beantwoorden zullen we de verschillen tussen relationele en niet-relationele databases onderzoeken, inclusief hun structuur en functionaliteit. We zullen ook onderzoeken welke soorten gegevens het meest geschikt zijn voor elk type database en de schaalbaarheid en prestaties van beide. 

Om tot een uitgebreid beeld te komen, zullen we dus gebruik maken van verschillende onderzoeksmethoden. De methodes die wij zullen gebruiken binnen dit onderzoek zijn: Literature Study, SWOT Analysis, Usability Testing en Requirement Prioritization. Ook zullen we dit doen aanhand van een aantal deelvragen die we hebben opgesteld. 

Aan het eind van dit onderzoek hopen wij te beschikken over een nauwkeurig beeld van de voor- en nadelen van de vervanging van een relationele database door een niet-relationele database en hoe dit betrekking zal hebben op de SpotiTube applicatie. 

### 1.3 Deelvragen

Om tot een conclusie te komen op de hoofdvraag hebben we een drietal deelvragen bedacht om het beantwoorden van de hoofdvraag makkelijker te maken. De drie hoofdvragen luiden als volgt:

1.	Wat zijn de belangrijkste verschillen tussen de verschillende en populairste soorten niet-relationele databases en welke is het meest geschikt voor de specifieke behoeften en vereisten van de applicatie?

2.	Hoe kan men vergelijkbare resultaten behalen in een NoSQL-database, zoals die verkregen worden via een join-operatie in een SQL-database? 

3.	Welke criteria moeten worden vervuld om een niet-relationele database te kunnen inzetten ter vervanging van een relationele database in een applicatie?

4.	Wat is het verschil in snelheid tussen het ophalen van gegevens uit een niet-relationele database en een relationele database? 


### 1.4 Onderzoeksmethoden

Wij maken binnen dit onderzoek gebruik van de volgende onderzoeksmethoden:

Voor de methode Library gebruiken we ‘Literature Study’ en ‘SWOT analysis’, voor de methode Lab gebruiken we ‘Non-functional test’ en als laatste voor de methode Workshop gebruiken wij ‘Requirement Prioritization’.

Deelvraag 1 wordt beantwoord door gebruik te maken van de onderzoeksmethode ‘Literature Study’. Deelvraag 2 beantwoorden we door gebruik te maken van ‘SWOT analysis’, deelvraag 3 gaan we beantwoorden door de methode ‘Requirement Prioritization’ toe te passen en als laatste wordt deelvraag 4 beantwoordt door gebruik te maken van ‘Non-functional test’.


## 2 Theoretisch Kader en Literatuuronderzoek

### 2.1 Verschillende Soorten Niet-Relationele Databases

Niet-relationele databases zijn een type databasebeheersysteem dat is ontworpen om grote hoeveelheden ongestructureerde data te verwerken. In tegenstelling tot traditionele relationele databases hebben niet-relationele databases geen vast schema of tabelstructuur nodig, wat ze zeer schaalbaar en flexibel maakt.

Er zijn vier hoofdtypen niet-relationele databases: documentgeoriënteerd, key-value, kolomgeoriënteerd en graph databases. In dit hoofdstuk zullen we beschrijven wat elk databasetype inhoud en wat de voor- en nadelen hiervan zijn.

#### 2.1.1 Documentgeoriënteerde Databases

Documentgeoriënteerde databases zijn een type niet-relationele database dat gegevens opslaat in documenten in plaats van tabellen, zoals relationele databases. Elk document bevat alle informatie die nodig is om een specifiek object te beschrijven, inclusief de attributen en waarden, en kan worden opgeslagen in verschillende formaten zoals JSON of BSON.

Een voordeel van documentgeoriënteerde databases is dat ze flexibeler zijn dan relationele databases. In plaats van een strikte structuur te hebben, kunnen documentgeoriënteerde databases werken met semi-gestructureerde gegevens, zoals JSON of XML. Hierdoor kunnen documentgeoriënteerde databases eenvoudig worden aangepast aan wijzigingen in de gegevensstructuren, waardoor ze zeer geschikt zijn voor toepassingen waarbij de structuur van de gegevens kan veranderen of onvoorspelbaar is. Naast de flexibiliteit van documentgeoriënteerde databases, bieden ze ook voordelen op het gebied van snelheid. Doordat documentgeoriënteerde  databases gebruik maken van documenten die informatie bevatten die is gerelateerd aan elkaar in één document, kunnen grote hoeveelheden informatie tegelijkertijd worden opgehaald en verwerkt.

Een nadeel van documentgeoriënteerde databases is dat ze minder geschikt zijn voor het uitvoeren van complexe query's. Doordat de  gegevens niet onderling zijn gekoppeld en in afzonderlijke documenten zijn opgeslagen, kan het moeilijker zijn om bepaalde gegevens op te halen dan in relationele databases.

In conclusie bieden documentgeoriënteerde databases een flexibele aanpak voor de opslag van semi-gestructureerde gegevens en kunnen ze gemakkelijk worden aangepast aan wijzigingen in de gegevensstructuren. Echter, ze zijn minder geschikt voor het uitvoeren van complexe query's in vergelijking met relationele databases. Bij het maken van een datamodel voor een documentgeoriënteerde database is het belangrijk om te overwegen hoe de gegevens zullen worden opgevraagd en bijgewerkt, zodat het datamodel hierop kan worden afgestemd en de prestaties van de database kunnen worden verbeterd.

#### 2.1.2  Key-value Databases

Key-value databases zijn databases die gegevens opslaan als een key-value paar, waarbij elke key een unieke identifier is voor de data en de value de daadwerkelijke data zelf. Ze zijn populair vanwege hun eenvoud, schaalbaarheid en snelheid, en worden vaak gebruikt voor toepassingen die snel en efficiënt gegevens moeten opvragen.

Een van de belangrijkste voordelen van het gebruik van key-value databases is hun gebruiksgemak. Vanwege hun eenvoudige structuur zijn ze gemakkelijk te begrijpen en te gebruiken. Bovendien staan key-value databases bekend om hun snelheid en vermogen om een hoog volume aan transacties te verwerken. Dit maakt ze een populaire keuze voor toepassingen die snel en efficiënt gegevens moeten opvragen.

Een nadeel van een key-value database is dat het minder geschikt is voor complexe queries en zoekopdrachten die niet gebaseerd zijn op specifieke keys. Omdat de data in een key-value database is georganiseerd rond de keys, kan het moeilijker zijn om gegevens te vinden die niet direct aan een specifieke key zijn gekoppeld. Dit kan het minder geschikt maken voor bepaalde soorten toepassingen waarin gegevens vaak op verschillende manieren moeten worden geanalyseerd en gerelateerd.

In conclusie bieden key-value databases een eenvoudige en efficiënte manier om grote hoeveelheden data op te slaan en op te vragen. Hoewel ze mogelijk niet geschikt zijn voor alle toepassingen, bieden ze een krachtig instrument voor bedrijven die snelle en schaalbare opslag en opvraging van gegevens nodig hebben.

#### 2.1.3 Kolomgeoriënteerde Databases

Kolomgeoriënteerde databases slaan gegevens op in kolommen in plaats van rijen, waardoor ze efficiënter zijn in het omgaan met grote hoeveelheden gegevens. In een rij-gebaseerde database worden alle gegevens voor een record opgeslagen in één rij, terwijl in een kolomgeoriënteerde database alle gegevens voor een kolom samen worden opgeslagen. Dit zorgt voor snellere verwerking en opvraging van grote datasets.

Kolomgeoriënteerde databases hebben ook een unieke compressietechniek die de opslagvereisten extreem erg kan verminderen. Ze comprimeren gegevens door kolommen te comprimeren in plaats van rijen, waardoor ze zeer efficiënt zijn in opslag en verwerking. Deze compressietechniek is vooral nuttig bij analytische verwerking, waar grote hoeveelheden gegevens snel moeten worden verwerkt.

Een ander voordeel van kolomgeoriënteerde databases is hun vermogen om spaarzame gegevens efficiënt te verwerken. Spaarzame gegevens verwijzen naar datasets waarbij veel waarden ontbreken. In een rij-gebaseerde database worden nog steeds lege ruimtes toegewezen, wat opslagruimte verspilt. In een kolomgeoriënteerde database worden echter geen lege ruimtes toegewezen, wat resulteert in efficiëntere opslag.

Een nadeel van kolomgeoriënteerde databases is dat het minder geschikt is voor het snel opvragen van volledige rijen met gegevens, omdat de gegevens per kolom worden opgeslagen en niet per rij. Als je bijvoorbeeld alle gegevens van een bepaalde persoon wilt opvragen, moet de database alle kolommen doorzoeken om de relevante gegevens op te halen. Dit kan leiden tot langere responstijden dan in een rij-geörienteerde database waar de gegevens van een rij bij elkaar staan.

In conclusie, kolomgeoriënteerde databases bieden verschillende voordelen ten opzichte van rij-geörienteerde databases, waaronder  snellere verwerking en opvraging van grote datasets, efficiënte compressietechnieken en betere verwerking van spaarzame gegevens.  Echter, een nadeel van kolom-geörienteerde databases is dat het minder geschikt is voor het snel opvragen van volledige rijen met gegevens.

#### 2.1.4 Graph Databases

Een graph-database is een speciaal type database dat gegevens opslaat in een grafische structuur, waarbij knooppunten, randen en eigenschappen worden gebruikt om gegevenspunten en hun relaties te representeren. Het knooppunt staat voor een gegevenspunt, de rand staat voor een relatie tussen twee knooppunten en eigenschappen kunnen worden toegevoegd om extra informatie te verstrekken. graph-databases zijn erg handig voor complexe gegevensrelaties, zoals in sociale netwerken, aanbevelingssystemen en kennisgrafieken.

Met graph-databases kun je gemakkelijk grote, complexe datasets behandelen, omdat de graph-structuur horizontaal kan worden geschaald naarmate de gegevens groeien. Ook zijn graph-databases intuïtiever voor gebruikers, omdat de visuele representatie van de gegevens gemakkelijker te begrijpen en te navigeren is dan traditionele tabulaire gegevens. Ten slotte zijn graph-databases bijzonder geschikt voor gegevens met meerdere, geneste relaties, omdat deze relaties als knooppunten en randen kunnen worden voorgesteld en daarmee de gegevensstructuur worden vereenvoudigd.

Een nadeel van graph-databases is dat ze minder efficiënt kunnen zijn bij het uitvoeren van eenvoudige query's en het verwerken van grote hoeveelheden gegevens in vergelijking met traditionele relationele databases. Dit komt omdat de graph-database de relaties tussen knooppunten moet doorzoeken om de gewenste informatie op te halen, wat in sommige gevallen meer tijd en middelen kan vergen dan een eenvoudige query in een traditionele relationele database.

In conclusie is een graph-database een krachtige tool voor het opslaan en beheren van complexe gegevensrelaties. Het biedt voordelen zoals schaalbaarheid en intuïtieve visualisatie van gegevens. Maar, het kan minder efficiënt zijn bij het uitvoeren van eenvoudige query's en het verwerken van grote hoeveelheden gegevens in vergelijking met traditionele relationele databases. Daarom is het belangrijk om de toepassingsvereisten te evalueren om te bepalen of een graph-database de juiste keuze is voor een specifiek project.

### 2.2 Join-Operaties in Niet-Relationele Databases

SQL join-operaties zijn een fundamenteel aspect van SQL waarmee gegevens uit twee of meer tabellen kunnen worden gecombineerd op basis van een gespecificeerde join-voorwaarde. De join-voorwaarde bepaalt hoe de tabellen gerelateerd zijn en welke rijen uit elke tabel worden geselecteerd.

Wanneer men voor het eerst een niet-relationele database gebruikt, is het vanzelfsprekend dat er enige aanpassing nodig is bij het werken met gegevens. Dus wat voor een invloed hebben deze aanpassingen op het gebruik van join-operaties, of iets vergelijkbaars? Dit is natuurlijk verschillend voor elk type niet-relationele database. We zullen de join-operaties voor de vier hoofdtypes beschreven in '[Verschillende Soorten Niet-Relationele Databases](#21-verschillende-soorten-niet-relationele-databases)' in dit hoofdstuk onderzoeken; of, om meer specifiek te zijn, de populairste database in elk type.

In dit hoofdstuk zullen we dus join-operaties, of vergelijkbare functionaliteiten, in de databases MongoDB, Redis, Apache Cassandra en Neo4j onderzoeken.

### 2.2.1 Join-Operaties in MongoDB

MongoDB heeft geen klassieke join-operaties zoals je ziet in relationele databases; in plaats daarvan maakt MongoDB gebruik van lookup-aggregaties om gegevens uit meerdere collecties te combineren op basis van gemeenschappelijke velden.

In MongoDB wordt een lookup-aggregatie uitgevoerd met behulp van de  $lookup-operator, waarmee de gebruiker gegevens uit meerdere collecties kan combineren door een veld in één collectie te matchen met een veld in een andere collectie. Het resultaat is een nieuwe collectie die de gecombineerde gegevens van beide collecties bevat, vergelijkbaar met het resultaat van een join-operatie in een relationele database.

Stel we hebben twee database tabellen; 'bestellingen' en 'klanten'. De 'bestellingen'-collectie bevat informatie over bestellingen van klanten, inclusief de klant-ID, terwijl de 'klanten'-collectie informatie bevat over elke klant, inclusief hun naam en adres.

De 'bestellingen'-collectie heeft dan bijvoorbeeld deze data:

```json
{
  "_id": 1,
  "klant_id": 123,
  "product_naam": "VoorbeeldProduct",
  "hoeveelheid": 2,
  "prijs": 50.0
}
```

De 'klanten'-collectie heeft dan bijvoorbeeld deze data:

```json
{
  "_id": 123,
  "naam": "Emma Jong",
  "adres": "Mauritskade 57, 1092 AD Amsterdam"
}
```

Ons doel is om de gegevens van beide collecties te combineren om een lijst te krijgen van alle bestellingen met de bijbehorende klantinformatie, zoals hun naam en adres.

Dit kunnen we doen door een $lookup-aggregatie operatie te gebruiken in MongoDB. Hier is een query als voorbeeld:

```markdown
db.bestellingen.aggregate([
  {
    $lookup:
      {
        from: "klanten",
        localField: "klant_id",
        foreignField: "_id",
        as: "klant_info"
      }
  }
])
```

Dit zou dan het resultaat zijn van die query:

```json
{    
     "_id": 1,    
     "klant_id": 123,    
     "product_naam": "VoorbeeldProduct",    
     "hoeveelheid": 2,    
     "prijs": 50.0,    
     "klant_info": [      
         {        
             "_id": 123,        
             "naam": "Emma Jong",        
             "adres": "Mauritskade 57, 1092 AD Amsterdam"      
         }    
     ]
}
```

Dit is een voorbeeld van een MongoDB lookup-query die gegevens combineert uit twee collecties op basis van een gemeenschappelijk veld en het resultaat in een nieuwe collectie weergeeft. In dit geval worden de 'bestellingen' en 'klanten' collecties gecombineerd op basis van de 'klant_id'. Het resultaat bevat de gegevens van een bestelling met de bijbehorende klantinformatie, weergegeven als een ingesloten document met het veld 'klant_info'. Dit is vergelijkbaar met het resultaat van een join-operatie in een relationele database, maar in plaats van het samenvoegen van rijen, worden hier documenten gecombineerd.

### 2.2.2 Join-Operaties in Redis

Redis ondersteunt geen joins zoals traditionele relationele databases. Er zijn echter enkele workarounds om vergelijkbare functionaliteit te bereiken in Redis.

Een aanpak is om de Redis datastructuur genaamd "Sorted Sets" te gebruiken samen met de opdrachten "ZINTERSTORE" en "ZUNIONSTORE".

Bijvoorbeeld, laten we zeggen dat we twee datasets hebben in Redis, "bestellingen" en "klanten". Elke "bestelling" is geassocieerd met een "klant" door middel van een "klant_id" veld. We kunnen een join nabootsen door voor elke dataset een gesorteerde set te maken, met het "klant_id" veld als score en het volledige object als waarde.

Voor de "bestellingen" dataset:

```sql
ZADD bestellingen 123 '{"bestelling_id": 1, "klant_id": 123, "product_naam": "VoorbeeldA", "hoeveelheid": 2, "prijs": 10}' 
```

Voor de "klanten" dataset:

```sql
ZADD klanten 123 '{"klant_id": 123, "naam": "Emma Jong", "adres": "Mauritskade 57, 1092 AD Amsterdam"}' 
```

Om een join tussen de twee datasets na te bootsen, kunnen we de "ZINTERSTORE" opdracht gebruiken om een nieuwe gesorteerde set te maken die alleen de objecten bevat waarin de "klant_id" zowel in "bestellingen" als in "klanten" voorkomt. De opdrachtsyntax is als volgt:

```sql
ZINTERSTORE output 2 bestellingen klanten WEIGHTS 1 0
```

Met behulp van Redis wordt een nieuwe gesorteerde set genaamd "output"  gecreëerd, waarin alleen objecten voorkomen waarvan het "klant_id" veld  zowel in "bestellingen" als in "klanten" aanwezig is. Door de parameter  "WEIGHTS 1 0" te gebruiken, wordt Redis geïnstrueerd om de score van de  "bestellingen" dataset als de uiteindelijke score voor de output set te  gebruiken en de score van de "klanten" dataset te negeren.

Om de inhoud van de 'output' set te kunnen zien gebruik je het volgende commando:

```sql
ZRANGE output 0 -1 WITHSCORES
```

De resulterende output set zal de volgende elementen hebben:

```sql
1. "{"bestelling_id":1,"klant_id":123,"product_naam":"VoorbeeldA","hoeveelheid":2,"prijs":10}"
2. "123"
```

Dit geeft aan dat er één order is geassocieerd met klant-ID 123. De ordergegevens zijn opgenomen als een JSON-object en de klant-ID is opgenomen als score.

Over het algemeen kan deze aanpak een manier bieden om join-achtige functionaliteit in Redis na te bootsen met behulp van gesorteerde sets en het "ZINTERSTORE" commando. Het kan echter extra verwerking vereisen aan de applicatiezijde om de resulterende output sets te verwerken.

### 2.2.3 Join-Operaties in Apache Cassandra

In Apache Cassandra is er geen JOIN-operatie zoals in traditionele relationele databases, omdat Cassandra is ontworpen om te optimaliseren voor schrijfoperaties en horizontaal te schalen over een groot aantal knooppunten. In plaats daarvan biedt Cassandra een techniek die heet "materialized views". 

Materialized views zijn vooraf berekende tabellen die de resultaten van een query op de basistabel bevatten. Materialized views kunnen worden gemaakt om zoekopdrachten te ondersteunen die filtering op een specifieke kolom inhouden, en de materialized weergave wordt automatisch bijgewerkt als de gegevens in de basistabel veranderen.

Hier is een voorbeeld van het gebruik van materialized views in Apache Cassandra:

Stel we hebben de volgende twee tabellen:

```cassandra
CREATE TABLE klanten (
   id int PRIMARY KEY,
   naam text,
   adres text
);

CREATE TABLE bestellingen (
   id int PRIMARY KEY,
   klant_id int,
   product_naam text,
   prijs decimal
);
```

We willen nu een query uitvoeren waardoor we alle bestellingen van een specifieke klant kunnen opzoeken. Dit kunnen we doen met de materialized view:

```cassandra
CREATE MATERIALIZED VIEW bestellingen_by_klant AS
   SELECT klant_id, naam, adres, product_naam, prijs
   FROM bestellingen
   WHERE klant_id IS NOT NULL AND id IS NOT NULL
   PRIMARY KEY (klant_id, id);
```

In de materialized view selecteren we de kolommen die we willen opnemen in de query, en gebruiken we de klant_id als partition key en de bestelling-id als clustering key. Dit stelt ons in staat om efficiënt te zoeken naar alle bestellingen voor een bepaalde klant.

Nu kunnen we de materialized view bevragen om alle bestellingen voor een specifieke klant op te halen:

```cassandra
SELECT * FROM bestellingen_by_klant WHERE klant_id = 123;
```

Dit geeft alle bestellingen weer voor klant 123, samen met de product- en prijsinformatie en het adres en de naam van de klant.

Naarmate er nieuwe bestellingen aan de bestellingen-tabel worden toegevoegd, wordt de materialized view automatisch bijgewerkt om deze wijzigingen weer te geven. Dit zorgt ervoor dat de materialized view altijd up-to-date en accuraat is.

### 2.2.4 Join-Operaties in Neo4j

In Neo4j kan je een join uitvoeren tussen knooppunten in een grafiek met behulp van de Cypher-querytaal. De Cypher-querytaal is de querytaal die gebruikt wordt in Neo4j om  gegevens te manipuleren en te bevragen in de grafische database. Het is  een op tekst gebaseerde taal, vergelijkbaar met SQL, die speciaal is  ontworpen voor het werken met grafen. Met Cypher kan je nodes en  relaties in een grafiek selecteren, filteren en transformeren, en  complexe query's uitvoeren om patronen en relaties in de gegevens te  ontdekken. Cypher-query's zijn leesbaar en expressief, en bieden veel  flexibiliteit en kracht bij het werken met complexe gegevensstructuren.

Hier is een voorbeeld van hoe een join werkt in Neo4j met de "bestellingen" en "klanten" entiteiten:

Stel dat we de volgende informatie hebben:

```sql
CREATE (:Bestelling {id: 1, product_naam: "VoorbeeldA", prijs: 10, klant_id: 1});
CREATE (:Bestelling {id: 2, product_naam: "VoorbeeldB", prijs: 20, klant_id: 2});
```

```sql
CREATE (:Klant {id: 1, naam: "Emma Jong", adres: "Mauritskade 57, 1092 AD Amsterdam"});
CREATE (:Klant {id: 2, naam: "Noah Jong", adres: "Mauritskade 57, 1092 AD Amsterdam"});
```

We kunnen deze twee entiteiten aan elkaar koppelen met behulp van een join query in Cypher. Een eenvoudige join query om de namen van de klanten op te halen die bestellingen hebben geplaatst, zou er als volgt uitzien:

```sql
MATCH (o:Bestelling)-[:PLACED_BY]->(c:Klant)
RETURN c.naam, o.product_naam, o.prijs
```

Deze query maakt een match tussen de "Bestelling" en "Klant" knooppunten, die zijn verbonden met een "PLACED_BY" relatie. Vervolgens retourneert de query de naam van de klant, het product dat is besteld en de prijs van de bestelling.

Door deze join query te gebruiken, kunnen we de gegevens uit beide entiteiten combineren en antwoorden krijgen op vragen als "welke klant heeft welke bestellingen geplaatst?" of "welke bestellingen heeft een specifieke klant geplaatst?".

### 2.3 Criteria voor het kiezen tussen relationele en niet-rationele databases in een applicatie

Er zijn meerdere criteria’s waar je rekening mee moet houden om uiteindelijk tot de conclusie te kunnen komen welke database je wilt gebruiken voor de applicatie. In dit hoofdstuk ga ik ze allemaal langs wat ons een goed beeld moet geven om uiteindelijk tot een conclusie te komen. 

Allereerst moeten we het hebben over de schaalbaarheid. Niet relationele databases zijn over het algemeen ontworpen om ‘side by side’ ofwel horizontaal te schalen. Dit betekent dat een niet relationele database grotere hoeveelheden van data en verkeer aan kan zonder prestatieverlies te lijden.

De volgende criteria is flexibiliteit. Een niet relationele database slaat de gegevens in een niet in een tabel vorm op maar is meer te vergelijken met een soort documenten structuur. ‘Een document kan zeer gedetailleerd zijn en tegelijkertijd een reeks verschillende soorten informatie in verschillende formaten bevatten’ (MongoDB, 2023). Dit kan handig zijn als de applicatie bijvoorbeeld te maken krijgt met ongestructureerde data of wanneer het datamodel in de loop der tijd kan veranderen.

Het laatste criteria is de performance. Doordat een relationele database veel tabellen kan hebben waar op hun plaats weer veel gegevens in zit steeds blijft groeien. Wordt de tijd die nodig is om de queries goed uit te voeren ook steeds groter. Dit probleem heb je niet bij het gebruik van een niet relationele database. Dit komt omdat een niet relationele database de gegevens als het ware naast elkaar opslaat. Hierdoor ondervind je dus niet tot nauwelijks verminderingen van performances naarmate de hoeveelheid gegevens groeit.

### 2.4 Conclusie

Er zijn dus verschillende type niet-relationele databases; elk type gaat op een verschillende manier met data om. Maar, welke niet-relationele database type is de beste oplossing voor de Spotitube applicatie?

Een samenvatting van de voor- en nadelen van de verschillende niet-relationele database types:

- Documentgeoriënteerde databases:
  - Voordelen: veranderingen in gegevensstructuren zijn makkelijker aan te passen en grote datasets ophalen gaat vlotter
  - Nadelen: minder geschikt voor complexe queries
- Key-value databases:
  - Voordelen: gebruiksgemak, snelheid en schaalbaarheid.
  - Nadelen: minder geschikt voor complexe queries en zoekopdrachten die niet gebaseerd zijn op specifieke keys.
- Kolomgeoriënteerde databases:
  - Voordelen: efficiënt bij het omgaan met grote hoeveelheden gegevens en het uitvoeren van analytische queries.
  - Nadelen: minder geschikt voor transacties en het uitvoeren van operaties op individuele records.
- Graph databases:
  - Voordelen: geschikt voor het opslaan van complexe relaties en het uitvoeren van complexe queries.
  - Nadelen: minder geschikt voor transacties en minder efficiënt bij het opslaan van grote hoeveelheden data.

De Spotitube applicatie heeft behoefte aan een schaalbare database die in staat is om grote hoeveelheden data tegelijk op te halen. Belangrijk is dat de database flexibel is, zodat het gemakkelijk is om nieuwe velden toe te voegen aan de opgeslagen tracks zonder dat er veel nullable velden worden gecreëerd. Door te kiezen voor een dergelijke database kan de Spotitube applicatie snel en efficiënt werken met grote hoeveelheden data en tegelijkertijd gemakkelijk worden uitgebreid en onderhouden.

Hoewel alle niet-relationele database types hun eigen voordelen hebben, lijkt de documentgeoriënteerde database het meest geschikt voor deze specifieke toepassing. De populairste documentgeoriënteerde is MongoDB; deze is gratis en open-source, wat het een goede optie maakt voor onze applicatie.

Het voordeel van MongoDB is dat het gebruik maakt van documenten die informatie bevatten die is gerelateerd aan elkaar in één document, wat de efficiëntie en snelheid van het ophalen van grote datasets kan verbeteren. Bovendien is MongoDB flexibel en schaalbaar, waardoor het gemakkelijk kan worden aangepast aan veranderingen in gegevensstructuren en kan meegroeien met de groei van de applicatie. Daarnaast kunnen er gemakkelijk relaties tussen documenten worden gelegd door middel van MongoDB's  lookup-functionaliteit. Deze functionaliteit maakt het mogelijk om gegevens uit meerdere collecties samen te voegen en te combineren op basis van een gemeenschappelijk veld, vergelijkbaar met het uitvoeren van een join in een relationele database.

Concluderend gaan we onderzoeken of MongoDB, als niet-relationele database, de Spotitube applicatie net zo goed of beter kan laten  functioneren dan de huidige relationele database.

## 3 ONDERZOEKSRESULTATEN

Voor dit onderzoeksverslag hebben Suzanne en ik gebruik gemaakt van de onderzoeksmethoden ‘Non-Functional test’ en ‘Requirements prioritization’. Zoals een van onze deelvragen luid: ‘Wat is het verschil in snelheid tussen het ophalen van gegevens uit een niet-relationele database en een relationele database?’ hebben wij dit onderzocht door in zowel een relationele database (in dit geval dus MySQL) als in een niet relationele database (in dit geval MongoDB) te testen hoe lang het duurde voor de database om de gevraagde gegevens op te vragen. Wij denken dat uit deze testen zal blijken dat MongoDB sneller zal zijn dan een traditionele database zoals MySQL.

Om er voor te zorgen dat de resultaten van dit onderzoek betrouwbaar waren hebben we er voor gekozen om beide databases tienduizend keer de testen uit te voeren. Hieruit kwam de gegevens die te zien zijn in ‘Tabel 1’. Zoals te zien is in de tabel is de niet relationele database MongoDB aanzienlijk sneller dan de relationele database MySQL. Het gemiddelde verschil is ongeveer 7 seconden, dit lijkt misschien weinig maar dit kan al een aanzienlijk effect hebben op hoe de gebruiker de interactie met de applicatie ervaart.

Dit is een uitwerking van een van de methodes die we gebruikt hebben om de snelheid te testen van de verschillende databases.
```Java
public void testGetAllPlaylists() {
        long[] mysqlDurations = new long[ITERATIONS];
        long[] mongoDurations = new long[ITERATIONS];
        for (int i = 0; i < mysqlDurations.length; i++) {
            mysqlDurations[i] = MySQLExecutions.getAllPlaylists(connection);
        }
        for (int i = 0; i < mongoDurations.length; i++) {
            mongoDurations[i] = MongoDBExecutions.getAllPlaylists(database);
        }
        long mysqlDuration = calculateAverage(mysqlDurations);
        long mongoDuration = calculateAverage(mongoDurations);
        long mysqlTotal = calculateTotal(mysqlDurations);
        long mongoTotal = calculateTotal(mongoDurations);

        printResults("getAllPlaylists", mongoDuration, mysqlDuration, mongoTotal, mysqlTotal);
    }
```
In deze methode ‘testGetAllPlaylists’ initieren we eerst twee arrays van het type ‘long’ waar zometeen de totale tijd die de query nodig had om uit te voeren in opgeslagen wordt. Dit wordt vervolgens gedaan door voor de totale lengte van de array de tijd op te halen. Uiteindelijk wordt het gemiddelde uitgerekend en de totale tijd van alle query’s bij elkaar en wordt dit uitgeprint in de console log. Die gegevens hebben we dus in ‘Tabel 1’ toegevoegd.

| **Geteste methode** | **Snelheid van MongoDB** | **Snelheid van MySQL** | **Verschil** |
| --- | --- | --- | --- |
| getAllPlaylists | 6610ms | 12006ms | 5396ms |
| getAllTracks | 16828ms | 27886ms | 11058ms |
| getTracksInPlaylistById | 2917ms | 7722ms | 4805ms |
| calculateTrackLengthInSeconds | 22198ms | 29486ms | 7288ms |

_Tabel 1: Resultaten testen snelheid_

## 4 CONCLUSIE

Onze hoofdvraag voor dit onderzoek was “Wat zijn de voor- en nadelen van het vervangen van de relationele database door een niet relationele database?”. Zoals uit de onderzoeken is gebleken heeft het gebruiken van een niet relationele database een groot aantal voordelen en zou het een goed alternatief zijn om de relationele database te vervangen voor de applicatie SpotiTube. Voor het onderzoek hebben wij gebruik gemaakt van de MongoDB er is gebleken dat een niet relationele database vele malen sneller is dan een relationele database. Dit heeft als voordeel dat de performance niet leidt onder grote hoeveelheden aan data op halen uit de database, hierdoor kan de gebruiker optimaal gebruik maken van Spotitube. Andere voordelen zijn, zoals al eerder benoemd, de schaalbaarheid en flexibiliteit van de niet relationele database. MongoDB is erg flexibel met accepteren van verschillende soorten data types en kan gemakkelijk uitbreiden omdat hij horizontaal schaalt en niet afhankelijk is van tabellen.

Een van de nadelen van het gebruik van een niet relationele database is dat het mogelijk minder accurate data kan bevatten. Relationele databases maken gebruik van ‘primary keys’ en ‘foreign keys’ dit zorgt er voor dat er geen dubbele informatie voorkomt en dus de gegevens meer accuraat zijn omdat er nooit dubbele informatie voor kan komen.

Echter lijken de voordelen van MongoDB te voldoen aan de behoeften van de Spotitube applicatie denk hier aan bijvoorbeeld het kunnen opslaan van grote datasets en zonder dat de performance er veel onder lijdt gegevens ophalen uit de database. Dus wij denken dat die voordelen zwaarder wegen dan de mogelijke nadelen en zien geen bezwaren tegen het implementeren van een niet relationele database zoals Mongo.

## Bronnen

Aho, A. V., Beeri, C., & Ullman, J. D. (1979). The theory of joins in relational databases. *ACM Transactions on Database Systems (TODS)*, *4*(3), 297-314.

Janata, N., Puri, S., Ahuja, M., Kathuria, I., & Gosain, D. (2012). A survey and comparison of relational and non-relational database. *International Journal of Engineering Research & Technology*, *1*(6), 1-5.

Vera, H., Boaventura, W., Holanda, M., Guimaraes, V., & Hondo, F.  (2015, September). Data modeling for NoSQL document-oriented databases. In *CEUR Workshop Proceedings* (Vol. 1478, pp. 129-135).

Baron, C. A. (2016). NoSQL key-value DBs riak and redis. *Database Systems Journal*, *6*(4), 3-10.

Matei, G., & Bank, R. C. (2010). Column-oriented databases, an alternative for analytical environment. *Database Systems Journal*, *1*(2), 3-16.

Angles, R. (2018, May). The Property Graph Database Model. In *AMW*.

Rithika, S. (2022, 29 december). *Understanding MongoDB Joins | 5 Critical Aspects*. Learn | Hevo. https://hevodata.com/learn/mongodb-joins/

*$lookup (aggregation) — MongoDB Manual*. (Z.d.). https://www.mongodb.com/docs/manual/reference/operator/aggregation/lookup/

Akhtar, A. (2023). Popularity Ranking of Database Management Systems. *arXiv preprint arXiv:2301.00847*.

*Redis*. (z.d.). Redis. https://redis.io/

*Apache Cassandra | Apache Cassandra Documentation*. (z.d.). Apache Cassandra. https://cassandra.apache.org/

OpenCredo. (2022, 15 juni). *Everything you need to know about Cassandra Materialized Views - OpenCredo*. https://opencredo.com/blogs/everything-need-know-cassandra-materialized-views/

E. (2022b, maart 9). *You Want Some Join Context? Neo4j Has You Covered*. Neo4j Graph Data Platform. https://neo4j.com/blog/join-context-neo4j/

MongoDB. (2023, Januari 1). What Is a Non-Relational Database? Opgeroepen op Maart 31, 2023, van MongoDB.

ICT Research Methods. (2022, Juni 10). Methods. Opgeroepen op Maart 31, 2023, van ictresearchmethods: https://ictresearchmethods.nl/Methods

OnderwijsOnline (z.d.-b). https://han.onderwijsonline.nl/
