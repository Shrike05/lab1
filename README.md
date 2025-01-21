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

 