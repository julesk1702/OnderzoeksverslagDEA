# DEA Onderzoeksverslag
Jules Koster en Suzanne Coenen
Bart van der Wal
ITA-OOSE-A-f
Versie 1.0
30 maart 2023

___

## Inhoudsopgave

1. [Samenvatting](#1-samenvatting)
2. [Inleiding](#2-inleiding)
3. [Onderzoeksvraag](#3-onderzoeksvraag)
   1. [Deelvragen](#31-deelvragen)
4. [Onderzoeksmethoden](#4-onderzoeksmethoden)

## 1 Samenvatting



## 2 Inleiding

Suzanne en ik onderzoeken de mogelijkheden van databases. We willen met name de mogelijkheid onderzoeken of het mogelijk is een traditionele relationele database te vervangen door een niet-rationele. Om dit te bereiken zullen we onze aandacht richten op de voordelen, nadelen en potentiële veiligheidsrisico's die zich voor kunnen doen bij de migratie van MySQL naar een niet-relationele database. 

Ons onderzoek omvat informatie vergaren door middel van de SWOT analysis methode en het maken van een prototype voor de SpotiTube-applicatie, die de huidige relationele database zal vervangen door een niet-relationele, in dit geval MongoDB. Op die manier hopen we de verschillen tussen de twee databasesystemen aan te tonen, inclusief de potentiële voor- en nadelen van het gebruik van een niet-relationele database in plaats van een relationele. 

Met dit onderzoek hopen we inzicht te verschaffen om de keuze of je nu wel of niet zou overstappen van een relationele database naar een niet relationele database. Door de uitdagingen en mogelijkheden van niet-relationele databases te onderzoeken, hopen we een uitgebreid overzicht te geven van hun functionaliteit en geschiktheid voor verschillende toepassingen. 

## 3 Onderzoeksvraag

Onze hoofdvraag voor dit onderzoeksverslag luidt alsvolgt: “Wat zijn de voor- en nadelen van het vervangen van de relationele database door een niet relationele database?”. 

Om deze vraag te beantwoorden zullen we de verschillen tussen relationele en niet-relationele databases onderzoeken, inclusief hun structuur en functionaliteit. We zullen ook onderzoeken welke soorten gegevens het meest geschikt zijn voor elk type database en de schaalbaarheid en prestaties van beide. 

Om tot een uitgebreid beeld te komen, zullen we dus gebruik maken van verschillende onderzoeksmethoden. De methodes die wij zullen gebruiken binnen dit onderzoek zijn: Literature Study, SWOT Analysis, Usability Testing en Requirement Prioritization. Ook zullen we dit doen aanhand van een aantal deelvragen die we hebben opgesteld. 

Aan het eind van dit onderzoek hopen wij te beschikken over een nauwkeurig beeld van de voor- en nadelen van de vervanging van een relationele database door een niet-relationele database en hoe dit betrekking zal hebben op de SpotiTube applicatie. 

### 3.1 Deelvragen

## 4 Onderzoeksmethoden

## 5 Verschillende soorten niet-relationele databases

NoSQL-databases zijn een type databasebeheersysteem dat is ontworpen om grote hoeveelheden ongestructureerde data te verwerken. In tegenstelling tot traditionele relationele databases hebben NoSQL-databases geen vast schema of tabelstructuur nodig, wat ze zeer schaalbaar en flexibel maakt.

Er zijn vier hoofdtypen NoSQL-databases: documentgeoriënteerd, key-value, kolomgeoriënteerd en graph databases. Documentgeoriënteerde databases, zoals MongoDB en CouchDB, slaan gegevens op in een JSON-achtig formaat en staan flexibele en dynamische schema-ontwerp toe. Sleutel-waarde databases, zoals Redis en Riak, slaan gegevens op als een sleutel-waardepaar en zijn sterk geoptimaliseerd voor snelle lees- en schrijfoperaties.

### 5.1 Documentgeoriënteerde Databases

Documentgeoriënteerde databases zijn een type niet-relationele database dat gegevens opslaat in documenten in plaats van tabellen, zoals relationele databases. Elk document bevat alle informatie die nodig is om een specifiek object te beschrijven, inclusief de attributen en waarden, en kan worden opgeslagen in verschillende formaten zoals JSON of BSON. Documentgeoriënteerde databases zijn ontworpen om flexibel te zijn en laten vloeibare datamodellering toe, waardoor ze ideaal zijn voor toepassingen waarbij de gegevensstructuur complex is of vaak verandert. Ontwikkelaars kunnen de datamodellen snel en gemakkelijk aanpassen zonder zich zorgen te hoeven maken over de herstructurering van de hele database.

Een voordeel van documentgeoriënteerde databases is dat ze flexibeler zijn dan relationele databases. In plaats van een strikte structuur te hebben, kunnen documentgeoriënteerde databases werken met semi-gestructureerde gegevens, zoals JSON of XML. Hierdoor kunnen documentgeoriënteerde databases eenvoudig worden aangepast aan wijzigingen in de gegevensstructuren, waardoor ze zeer geschikt zijn voor toepassingen waarbij de structuur van de gegevens kan veranderen of onvoorspelbaar is.

Een nadeel van documentgeoriënteerde databases is dat ze minder geschikt zijn voor het uitvoeren van complexe query's. Doordat de  gegevens niet onderling zijn gekoppeld en in afzonderlijke documenten  zijn opgeslagen, kan het moeilijker zijn om bepaalde gegevens op te  halen dan in relationele databases.

Bij het maken van een datamodel voor een documentgeoriënteerde database  is het essentieel om te bedenken hoe de gegevens vaak zullen worden  opgevraagd of bijgewerkt, zodat het model hierop kan worden afgestemd.  Dit betekent dat ontwikkelaars het datamodel kunnen aanpassen aan de  specifieke eisen van de toepassing, zodat de toegang tot de gegevens zo  efficiënt mogelijk kan verlopen. Door deze aanpassingen kan de prestatie van de database worden verbeterd.

In conclusie bieden documentgeoriënteerde databases een flexibele aanpak voor de opslag van semi-gestructureerde gegevens en kunnen ze  gemakkelijk worden aangepast aan wijzigingen in de gegevensstructuren.  Echter, ze zijn minder geschikt voor het uitvoeren van complexe query's in vergelijking met relationele databases. Bij het maken van een datamodel voor een documentgeoriënteerde database is het belangrijk om te overwegen hoe de gegevens zullen worden opgevraagd en bijgewerkt, zodat het datamodel hierop kan worden afgestemd en de prestaties van de database kunnen worden verbeterd.

### 5.2  Key-value  Databases

Key-value databases zijn databases die gegevens opslaan als een key-value paar, waarbij elke key een unieke identifier is voor de data en de value de daadwerkelijke data zelf. Ze zijn populair vanwege hun eenvoud, schaalbaarheid en snelheid, en worden vaak gebruikt voor toepassingen die snel en efficiënt gegevens moeten opvragen.

Een van de belangrijkste voordelen van het gebruik van key-value databases is hun gebruiksgemak. Vanwege hun eenvoudige structuur zijn ze gemakkelijk te begrijpen en te gebruiken. Bovendien staan key-value databases bekend om hun snelheid en vermogen om een hoog volume aan transacties te verwerken. Dit maakt ze een populaire keuze voor toepassingen die snel en efficiënt gegevens moeten opvragen.

Bij vergelijking met andere typen databases, zoals relationele databases of document databases, hebben key-value databases hun voor- en nadelen. Zo bieden relationele databases sterke consistentie en ondersteuning voor complexe queries, maar kunnen ze traag en inflexibel zijn. Key-value databases offeren daarentegen enigszins aan consistentie in ruil voor schaalbaarheid en snelheid.

Een nadeel van een key-value database is dat het minder geschikt is voor complexe queries en zoekopdrachten die niet gebaseerd zijn op specifieke keys. Omdat de data in een key-value database is georganiseerd rond de keys, kan het moeilijker zijn om gegevens te vinden die niet direct aan een specifieke key zijn gekoppeld. Dit kan het minder geschikt maken voor bepaalde soorten toepassingen waarin gegevens vaak op verschillende manieren moeten worden geanalyseerd en gerelateerd.

In conclusie bieden key-value databases een eenvoudige en efficiënte manier om grote hoeveelheden data op te slaan en op te vragen. Hoewel ze mogelijk niet geschikt zijn voor alle toepassingen, bieden ze een krachtig instrument voor bedrijven die snelle en schaalbare opslag en opvraging van gegevens nodig hebben.

### 5.3 Kolomgeoriënteerde Databases

Kolomgeoriënteerde databases slaan gegevens op in kolommen in plaats van rijen, waardoor ze efficiënter zijn in het omgaan met grote hoeveelheden gegevens. In een rij-gebaseerde database worden alle gegevens voor een record opgeslagen in één rij, terwijl in een kolomgeoriënteerde database alle gegevens voor een kolom samen worden opgeslagen. Dit zorgt voor snellere verwerking en opvraging van grote datasets.

Kolomgeoriënteerde databases hebben ook een unieke compressietechniek die de opslagvereisten extreem erg kan verminderen. Ze comprimeren gegevens door kolommen te comprimeren in plaats van rijen, waardoor ze zeer efficiënt zijn in opslag en verwerking. Deze compressietechniek is vooral nuttig bij analytische verwerking, waar grote hoeveelheden gegevens snel moeten worden verwerkt.

Een ander voordeel van kolomgeoriënteerde databases is hun vermogen om spaarzame gegevens efficiënt te verwerken. Spaarzame gegevens verwijzen naar datasets waarbij veel waarden ontbreken. In een rij-gebaseerde database worden nog steeds lege ruimtes toegewezen, wat opslagruimte verspilt. In een kolomgeoriënteerde database worden echter geen lege ruimtes toegewezen, wat resulteert in efficiëntere opslag.

Een nadeel van kolomgeoriënteerde databases is dat het minder geschikt is voor het snel opvragen van volledige rijen met gegevens, omdat de gegevens per kolom worden opgeslagen en niet per rij. Als je bijvoorbeeld alle gegevens van een bepaalde persoon wilt opvragen, moet de database alle kolommen doorzoeken om de relevante gegevens op te halen. Dit kan leiden tot langere responstijden dan in een rij-geörienteerde database waar de gegevens van een rij bij elkaar staan.

In conclusie, kolomgeoriënteerde databases bieden verschillende  voordelen ten opzichte van rij-geörienteerde databases, waaronder  snellere verwerking en opvraging van grote datasets, efficiënte  compressietechnieken en betere verwerking van spaarzame gegevens.  Echter, een nadeel van kolom-geörienteerde databases is dat het minder  geschikt is voor het snel opvragen van volledige rijen met gegevens.

### 5.4 Graph Databases

Een graph-database is een speciaal type database dat gegevens opslaat in een grafische structuur, waarbij knooppunten, randen en eigenschappen worden gebruikt om gegevenspunten en hun relaties te representeren. Het knooppunt staat voor een gegevenspunt, de rand staat voor een relatie tussen twee knooppunten en eigenschappen kunnen worden toegevoegd om extra informatie te verstrekken. graph-databases zijn erg handig voor complexe gegevensrelaties, zoals in sociale netwerken, aanbevelingssystemen en kennisgrafieken.

Met graph-databases kun je gemakkelijk grote, complexe datasets behandelen, omdat de graph-structuur horizontaal kan worden geschaald naarmate de gegevens groeien. Ook zijn graph-databases intuïtiever voor gebruikers, omdat de visuele representatie van de gegevens gemakkelijker te begrijpen en te navigeren is dan traditionele tabulaire gegevens. Ten slotte zijn graph-databases bijzonder geschikt voor gegevens met meerdere, geneste relaties, omdat deze relaties als knooppunten en randen kunnen worden voorgesteld en daarmee de gegevensstructuur worden vereenvoudigd.

Een potentieel nadeel van graph-databases is dat ze minder efficiënt  kunnen zijn bij het uitvoeren van eenvoudige query's en het verwerken van grote hoeveelheden gegevens in vergelijking met traditionele  relationele databases. Dit komt omdat de graph-database de relaties tussen knooppunten moet doorzoeken om de gewenste informatie op te halen, wat in sommige gevallen meer tijd en middelen kan vergen dan een  eenvoudige query in een traditionele relationele database.

In conclusie is een graph-database een krachtige tool voor het opslaan  en beheren van complexe gegevensrelaties. Het biedt voordelen zoals  schaalbaarheid en intuïtieve visualisatie van gegevens. Maar, het kan minder efficiënt zijn bij het uitvoeren van eenvoudige query's en het verwerken van grote hoeveelheden gegevens in vergelijking met traditionele relationele databases. Daarom is het belangrijk om de toepassingsvereisten te evalueren om te bepalen of een graph-database de juiste keuze is voor een specifiek project.

## 6 Join-operaties in niet-relationele databases

>  Hoe kan men vergelijkbare resultaten behalen in een NoSQL-database, zoals die verkregen worden via een join-operatie in een SQL-database? 

SQL join-operaties zijn een fundamenteel aspect van SQL waarmee gegevens uit twee of meer tabellen kunnen worden gecombineerd op basis van een gespecificeerde join-voorwaarde. De join-voorwaarde bepaalt hoe de tabellen gerelateerd zijn en welke rijen uit elke tabel worden geselecteerd.

Wanneer men voor het eerst een niet-relationele database gebruikt, is het vanzelfsprekend dat er enige aanpassing nodig is bij het werken met gegevens. Niet-relationele databases hebben de laatste jaren veel populariteit gewonnen, vooral in toepassingen waarbij grote hoeveelheden gegevens moeten worden verwerkt, zoals big data-analyse, IoT en mobiele toepassingen. In tegenstelling tot relationele databases zijn niet-relationele databases niet beperkt tot het gebruik van tabellen en rijen om gegevens op te slaan. In plaats daarvan gebruiken ze verschillende datamodellen zoals documenten, sleutel-waardeparen en  grafieken om gegevens op te slaan.

Dus wat voor een invloed hebben deze verschillen op het gebruik van join-operaties, of iets vergelijkbaars? 



## Bronnen

Aho, A. V., Beeri, C., & Ullman, J. D. (1979). The theory of joins in relational databases. *ACM Transactions on Database Systems (TODS)*, *4*(3), 297-314.

Jatana, N., Puri, S., Ahuja, M., Kathuria, I., & Gosain, D. (2012). A survey and comparison of relational and non-relational database. *International Journal of Engineering Research & Technology*, *1*(6), 1-5.

Vera, H., Boaventura, W., Holanda, M., Guimaraes, V., & Hondo, F.  (2015, September). Data modeling for NoSQL document-oriented databases.  In *CEUR Workshop Proceedings* (Vol. 1478, pp. 129-135).

Baron, C. A. (2016). NoSQL key-value DBs riak and redis. *Database Systems Journal*, *6*(4), 3-10.

Matei, G., & Bank, R. C. (2010). Column-oriented databases, an alternative for analytical environment. *Database Systems Journal*, *1*(2), 3-16.
