# oopd-gu-chalmers Lab 1
Lab assignment 1 in the course Object-oriented Programming and Design, GU/Chalmers

See Canvas for instructions.

Uppgift 1: Grundläggande arv

Bekanta er med koden i repot. För närvarande finns det två Java-filer, Volvo240.java och Saab95.java. Just nu verkar klasserna ha ganska mycket gemensamt, eftersom båda representerar bilar.

    Skapa en arvshierarki där Volvo240 och Saab95 ingår, som eliminerar all kod-duplicering, och som följer Open-Closed Principle. Gör dessa ändringar utan att ändra på klassernas funktionalitet.
        Bör ni använda implementationsarv (subclassing) eller specifikationsarv (interfaces)? Varför? Kan båda användas? Vilka för- och nackdelar ser ni?
    I originalfilerna är alla metoder och variabler public, vilket exponerar allt till användaren. Ändra synligheten på de metoder och variabler som användaren inte behöver se eller känna till. 
        Vilken synlighet bör ni använda för de olika metoder och variabler som klasserna innehåller? Vilket gränssnitt bör ni exponera?

Se till att alla filer kompilerar och fortsätt till nästa uppgift.

 
Uppgift 2: Action Interfaces

I denna uppgift fortsätter vi bygga på föregående genom att vi nu också implementerar ett interface kallat Movable.

    Skapa en fil Movable.java, som deklarerar ett interface med följande metoder:

    void move();
    void turnLeft();
    void turnRight();

    Se till att era bilar implementerar interfacet Movable, med någon lämplig intern representation av deras riktning och position. Metoden move ska fungera så att beroende på riktning ökas (eller minskas) bilens x- eller y-koordinat med dess currentSpeed.

 
Uppgift 3: Testning

Testning är alltid viktigt vid design av program.

    Använd JUnit för att skriva tester med 100% coverage för era bilklasser. Även JUnit har mycket information på nätet som ni kan leta upp. Era tester inte behöver testa "allt"; det viktiga är att ni bekantar er med JUnit och förstår hur det fungerar.
        Ni behöver inte skriva kodkontrakt för era metoder. Det räcker att ni skriver testmetoder som testar metodernas funktionalitet med hjälp av assert/equals osv. Därefter testar ni så att alla JUnit-tester går igenom med "Run with coverage".

 
Uppgift 4: Sanity checks

Bilklassernas metoder har för närvarande inget sätt att kontrollera hur mycket farten kan ökas eller sänkas. Skriv om metoder (och dokumentation) så att:

    gas() och break() bara accepterar värden i intervallet [0,1],
    currentSpeed alltid ligger i intervallet [0, enginePower],
    Anrop till gas() inte kan resultera i att farten sänks, och
    Anrop till break() inte kan resultera i att farten höjs.

Hint: För var och en av dessa, best practice är att använda följande process:

    Skapa ett test som blir godkänt om metoden uppfyller villkoret, och annars fallerar.
    Kör testerna, och se att det fallerar.
    Modifiera koden.
    Kör testerna, och verifiera att det nya testet nu blir godkänt (och att alla gamla tester fortfarande också går igenom!)

#Lab 2

Uppgift 1: Extensibilitet

    Skapa en representation av en Scania-lastbil med modellnamn Scania. Ge den rimliga startvärden för relevanta fält. Lägg den i filen Scania.java i samma mapp.
    Scania ska införlivas i er arvs-hierarki från tidigare, men ha ytterligare funktionalitet: den har ett flak som kan höjas (tippas) och sänkas. Införliva detta i er design så att vi kan hålla reda på vilken vinkel flaket har för närvarande, samt funktioner för att höja och sänka det.

Följande förhållanden ska gälla:

    Vinkeln på flaket kan inte vara lägre än 0 eller högre än 70.
    Det är bara om lastbilen står stilla som flaket får ha en annan vinkel än 0. Flaket ska inte kunna höjas om lastbilen är i rörelse; och lastbilen ska inte kunna köra om flaket är uppfällt.

Lägg allt i Scania.java. Gör minst ett JUnit-test i er testklass.

 
Uppgift 2: Mer extensibilitet

Skapa en representation av en biltransport - dvs en långtradare som kan transportera bilar på flaket. Ge den ett valfritt modellnamn och filnamn.

Biltransporten ska på lämpligt sätt införlivas i er arvshierarki från tidigare. Likt Scania-lastbilen har den ett "flak" i form av en ramp som går att höja och sänka. Införliva detta i er design på lämpligt sätt.

Bilar ska kunna lastas på och av biltransporten. Biltransporten har ett maximalt antal bilar som den kan lasta. Bilar som ska lastas på biltransporten får inte vara för stora (gör ett eget antagande).

Följande förhållanden ska gälla:

    Biltransportens ramp har endast två lägen, uppe eller nere.
    Rampen kan endast vara nere om biltransporten står stilla.
    Bilar kan endast lastas om rampen är nere, och de befinner sig rimligt nära biltransporten (gör ett eget antagande, de exakta detaljerna är inte viktiga).
    Bilar kan endast lossas om rampen är nere. De bör då hamna rimligt nära biltransporten.
    Bilar kan endast lossas i omvänd ordning från hur de lastades, dvs den sista bilen som lastades måste vara först att lossas (first-in-last-out).
    Biltransporten ska inte kunna lasta på en annan biltransport.
    Under det att en bil är lastad på biltransporten ska dess position i världen alltid vara densamma som biltransportens position.

Viktiga saker att ha i åtanke under den här uppgiften:

    Undvik kodduplicering för funktionalitet som är gemensam för flera olika klasser, e.g. lastbil och biltransport.
    Fundera över behovet av polymorfism för olika ändamål, och hur det påverkar framtida extensibilitet.
    Fundera över hur ni bäst håller reda på de bilar som lastats - vilken sorts datastruktur är bäst för ändamålet?

Kom ihåg att skriva JUnit-tester för relevanta aspekter av er kod. Best practice: skriv unit-tester innan ni uppdaterar koden.

 
Uppgift 3: Parametrisk polymorfism

Skapa en representation av en bilverkstad. Följande aspekter ska hanteras:

    En verkstad ska kunna ta emot ("lasta"?) ett antal bilar, upp till något max-antal som kan variera mellan olika verkstäder.
    Vissa verkstäder ska bara kunna ta emot en viss sorts bilar; andra kan ta emot vilka bilar som helst.
    Att försöka lämna in "fel" sorts bil i en verkstad ska ge ett statiskt (compile time) fel.
    Vid uthämtning av en bil från verkstaden ska vi kunna få så precis typinformation som möjligt statiskt.
        Exempel: För en märkesverkstad som enbart hanterar Volvo 240 bör vi statiskt kunna veta att bilar som hämtas ut från verkstaden alltid är just Volvo 240.
