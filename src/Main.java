/*
printa kryss med char in denna diagram
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] inputPrice = new int[24];                                                                                      // array for inputPrice

        String[] tidenArray = new String[inputPrice.length];

        printOut_0_to_24_Time(tidenArray);

        PrisTiden prisTidenObject[] = new PrisTiden[inputPrice.length];

        boolean isEnabledMeny = true;

        while (isEnabledMeny) {
            boolean isEnabledSecondMeny = true;

            System.out.println();


            mainMeny();

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 1) {
                    System.out.println("Mata in priset under dygnets timmar: ");
                    inmatning(sc, inputPrice, tidenArray, prisTidenObject);

                } else if (choice == 2) {                                        // Andra meny öppnar när man trycker 2
                    secondMenyMinMaxAverage(choice, prisTidenObject, isEnabledSecondMeny,sc);

                } else if (choice == 3) {
                    sorteringPrisTidenConnect_PrintOut(prisTidenObject);

                } else if (choice == 4) {
                    System.out.println("Bästa laddningstid (4 h) : ");
                    bestChargingTime(prisTidenObject);

                } else if (choice == 5) {
                    System.out.println("5. Visualisering :");
                    tableVG(inputPrice);
                }
            } else {

                String choice = sc.nextLine();                             // Avslutar program genom att trycka e eller E

                if (choice.equals("e") || choice.equals("E")) {
                    System.out.println("\nexit.");
                    isEnabledMeny = false;

                } else if (choice != "e" || choice != "E") {                                 // user choice NOT e or E
                    System.out.println("\nDu valde fel, prova igen");
                }
            }
        }
    }
    public static void printOut_0_to_24_Time(String [] tidenArray) {                                                           // Method för skriva ut array time i tabel i hela row 7

        for (int i = 0; i < tidenArray.length; i++) {
            if (i <= 8) {
                {
                    tidenArray[i] ="0" + i + " - " + "0" + (i + 1);
                }
            }
            else if (i == 9) {
                tidenArray[i] = "0" + i + " - " + (i + 1);

            } else {
                tidenArray[i] = i + " - " + (i + 1);
            }
        }
    }
    private static void mainMeny() {
        System.out.println("Elpriser");
        System.out.println("========");
        System.out.println("1. Inmatning");
        System.out.println("2. Min,Max och Medel");
        System.out.println("3. Sortera");
        System.out.println("4. Bästa Laddningstid (4h)");
        System.out.println("5. Visualisering (VG uppgift)");
        System.out.println("e. avsluta");
    }
    private static void menyOfMinMaxMedel() {
        System.out.println("Välj:");
        System.out.println("1. Medel av elpris:");
        System.out.println("2. Min av elpris");
        System.out.println("3. Max av elpris");
        System.out.println("4. Exit");
    }
    private static void inmatning(Scanner sc, int[] inputPrice, String[] tidenArray, PrisTiden[] prisTidenObject) {
        for (int i = 0; i < inputPrice.length; i++) {  // set i metoden                                             // Skriver ut tiden och ta inputPrice för el pris från user
            System.out.println("Skriva in pris av el här : " + tidenArray[i]);
            getPrice_CoverBug(sc, inputPrice, i);
        }
        priceTidenConnect(tidenArray, inputPrice, prisTidenObject);
    }
    private static void getPrice_CoverBug(Scanner sc, int[] inputPrice, int i) {
        try {
            inputPrice[i] = sc.nextInt();
        }catch (Exception e){
            System.out.println("Skriv in en tal.");
        }
    }
    private static void secondMenyMinMaxAverage(int choice, PrisTiden[] prisTidenObject, boolean isEnebledSecondMeny, Scanner sc) {

        int test = choice;
        menyOfMinMaxMedel();
        while (isEnebledSecondMeny) {

            int choiceThroghuMinMax = sc.nextInt();
            menyOfMinMaxMedel();
            isEnebledSecondMeny = false;

            double sumTwo = 0;

            if (choiceThroghuMinMax == 1) {
                printOutAverage(prisTidenObject, sumTwo);
                isEnebledSecondMeny = true;

            } else if (choiceThroghuMinMax == 2) {
                printOutLowestPrice(prisTidenObject);
                isEnebledSecondMeny = true;

            } else if (choiceThroghuMinMax == 3) {
                printOutMaxPrice(prisTidenObject);
                isEnebledSecondMeny = true;

            } else {
                if (choiceThroghuMinMax == 4) { // going to main Meny
                    System.out.println("Du valde EXIT tillbaka till main-meny ");
                    isEnebledSecondMeny = false;
                } else {
                    System.out.println("Du valt fel, prova igen");
                }
            }
        }
    }
    private static void printOutAverage(PrisTiden[] prisTidenObject, double sumTwo) {
        double average = getAverage(prisTidenObject, sumTwo);
        System.out.println("\nMedelpriset är " + average);
    }
    private static void printOutLowestPrice(PrisTiden[] prisTidenObject) {
        PrisTiden[] prisTidensArrayCopy = Arrays.copyOf(prisTidenObject, prisTidenObject.length);

        sorteringPrisTidenConnect(prisTidensArrayCopy);

        for (int i = 0; i < 1; i++) {
            System.out.println("\nDet billigaste priset är kl: " + prisTidensArrayCopy[0].getTime() + " och priset är: " + prisTidensArrayCopy[0].getPris() + " öre");
        }
    }
    private static void printOutMaxPrice(PrisTiden[] prisTidenObject) {
        PrisTiden[] prisTidensArrayCopy = Arrays.copyOf(prisTidenObject, prisTidenObject.length);

        sorteringPrisTidenConnect(prisTidensArrayCopy);

        for (int i = 0; i < 1; i++) {
            System.out.println("\nDet billigaste priset är kl " + prisTidensArrayCopy[prisTidensArrayCopy.length - 1].getTime() + " och priset är: " + prisTidensArrayCopy[prisTidensArrayCopy.length - 1].getPris() + " öre");
        }
    }
    private static void priceTidenConnect(String[] tidenArray, int[] input, PrisTiden[] prisTidenObject) {
        for (int j = 0; j < input.length; j++) {
            prisTidenObject[j] = new PrisTiden();
            prisTidenObject[j].setTime( tidenArray[j]);
            prisTidenObject[j].setPris(input[j]);
        }
    }
    public static PrisTiden[] sorteringPrisTidenConnect (PrisTiden[] prisTidenArray){


        boolean sortingBubble = true;

        while (sortingBubble) {
            sortingBubble = false;
            for (int i = 0; i < prisTidenArray.length - 1; i++) {
                if (prisTidenArray[i].getPris() > prisTidenArray[i + 1].getPris()) {
                    PrisTiden temp = prisTidenArray[i + 1];
                    prisTidenArray[i + 1] = prisTidenArray[i];
                    prisTidenArray[i] = temp;
                    sortingBubble = true;

                }
            }
        }
        return prisTidenArray;
    }
    private static double getAverage(PrisTiden[] prisTidenObject, double sumTwo) {
        for (int i = 0; i < prisTidenObject.length; i++) {
            sumTwo += prisTidenObject[i].getPris();
        }
        return sumTwo / 24;
    }
    public static void sorteringPrisTidenConnect_PrintOut(PrisTiden[] prisTidensObject) {
        PrisTiden[] prisTidenArrayCopy = Arrays.copyOf(prisTidensObject,prisTidensObject.length);

        sorteringPrisTidenConnect(prisTidenArrayCopy);


        for (int i = 0; i < prisTidenArrayCopy.length; i++) {
            System.out.println(prisTidenArrayCopy[i].getTime() + " " + prisTidenArrayCopy[i].getPris() + " öre");
        }
    }
    private static void bestChargingTime(PrisTiden[] prisTidenObject) {


        PrisTiden[] timerCopy =Arrays.copyOf(prisTidenObject,prisTidenObject.length);


        int fourHourPris = 0;
        int bestPris = Integer.MAX_VALUE;
        double genomsnitBestaPris = 0;
        double prisForBestaLaddning = 0;
        String testBestPris = "";

        for (int i = 0; i < timerCopy.length; i++) {
            if (i == timerCopy.length - 3) {
                break;
            }
            fourHourPris = getFourHourPris(timerCopy, i);
            if (fourHourPris < bestPris) {
                bestPris = fourHourPris;
                testBestPris = increasing4hThroughPrisTiden(timerCopy, i);

                prisForBestaLaddning = getFourHourPris(timerCopy, i);
            }
            genomsnitBestaPris = prisForBestaLaddning / 4;

        }


        System.out.println(testBestPris );
        System.out.println(genomsnitBestaPris + " - är genomsnittligt pris för bästa laddning");

    }
    private static int getFourHourPris(PrisTiden[] timerCopy, int i) {
        return timerCopy[i].getPris() + timerCopy[i + 1].getPris() + timerCopy[i + 2].getPris() + timerCopy[i + 3].getPris();
    }
    private static String increasing4hThroughPrisTiden(PrisTiden[] timerClone, int i) {
        return timerClone[i].getTime() + " " + timerClone[i].getPris() + " öre  \n" + timerClone[i + 1].getTime() + " " + timerClone[i + 1].getPris() + " öre   \n" + timerClone[i + 2].getTime() + " " + timerClone[i + 2].getPris() + " öre   \n" + timerClone[i + 3].getTime() + " " + timerClone[i + 3].getPris() + " öre";
    }
    public static void tableVG(int[] input) {

        int[] inputClone = Arrays.copyOf(input,input.length);

        bubbleSorteringMinAndMax(inputClone);

        final int different = inputClone[inputClone.length-1]/ 10;
        //((num + 99) / 100 ) * 100 round the number to higher number
        final int maxProcent = ((inputClone[input.length - 1] + different - 1) / different) * different;


        int spacer = Integer.toString(maxProcent).length();

        for (int procent = maxProcent; procent >= 0; ) {

            int nextValue = procent - different;

            xPlacer(procent + "  ");
            xPlacer(" ".repeat(spacer - Integer.toString(procent).length()) + "|");

            int placeHour = 0;

            for (int hour = 0; hour < input.length; hour++) {
                if (input[hour] >= procent && input[hour] > nextValue) {

                    xPlacer(" ".repeat((hour - placeHour) * 2) + "x");

                    placeHour = hour;
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
            procent = nextValue;

        }

        xPlacer(" ".repeat(spacer + 3));

        printOutTiden(inputClone.length);

    }
    private static void xPlacer(String hour) {
        System.out.print(hour);
    }
    public static void printOutTiden(int tiden) {                                                           // Method för skriva ut array time i tabel i hela row 7

        for (int i = 0; i < tiden; i++) {
            if (i < 10) {
                xPlacer("0" + i);
            } else {
                System.out.print(i);
            }
            xPlacer(" ");
        }
    }
    public static int[] bubbleSorteringMinAndMax(int[] input) {                            // Bubble sortering algoritam
        boolean sortingBubble = true;

        while (sortingBubble) {
            sortingBubble = false;
            for (int i = 0; i < input.length - 1; i++) {
                if (input[i] > input[i + 1]) {
                    int temp = input[i + 1];
                    input[i + 1] = input[i];
                    input[i] = temp;
                    sortingBubble = true;
                }
            }
        }
        return input;
    }

}








/*
När alternativ e väljs ska programmet avslutas. Både e och E bör vara giltiga som val för att avsluta.
Vid val av något av de övriga alternativen ska dessa köras och när körningen är klar ska menyn åter
skrivas ut på skärmen så att ett nytt val kan göras


. Inmatning
Senaste året har elpriserna blivit högre och varierar mycket. Det här programmet ska kunna hjälpa
 till med att analysera elpriser för ett dygn. När man väljer alternativet inmatning från menyn ska
programmet fråga efter priserna under dygnets timmar. Inmatningen av värden ska ske med hela
ören. Tex kan priser vara 50 102 eller 680 öre per kW/h. Priset sätts per intervall mellan två hela
timmar. Dygnets första pris är då mellan 00-01, andra intervallet är mellan 01-02 osv.

3. Min Max Medel
När alternativ 2 väljs på menyn så ska programmet skriva ut lägsta priset, högsta priset samt vilka
timmar som detta infaller under dygnet. Dygnets medelpris ska också räknas fram och presenteras på
skärmen.


3. Sortering
Skriv ut timmarna och priset för dessa sorterade efter billigast till dyrast pris. Ex:
00-01 23 öre
01-02 26 öre
05-06 30 öre
02-03 40 öre
...


4. Bästa laddningstid (4 h)
Om man har en elbil som man vill ladda så vill man kanske göra det när priset är som billigast på
dygnet. Då batteriet behöver värmas/kylas så blir det minst förluster om man genomför laddningen
sammanhängande under ett antal timmar. Låt programmet hitta de 4 billigaste timmarna som ligger i
följd och skriva ut vid vilket klockslag man ska börja ladda för att få lägst totalpris samt vilket
medelpris det blir under dessa 4 timmar.

5. Visualisering (VG uppgift)
Ett normalt konsol fönster har 80 teckens bredd och här vill vi utnyttja det för att visualisera priserna
under dygnet. Lägg till ett 5:e alternativ för detta i din meny eller utöka alternativ 2 till att visa alla
priser utöver min, max och medel. Exempel på hur visualiseringen kan se ut med 75 teckens bredd.
Beroende på vilket prisintervall som gäller kan skalan behöva justeras dynamiskt.
530| x x x
 | x x x x x x
 | x x x x x x x x x x x x x x
 | x x x x x x x x x x x x x x x
 | x x x x x x x x x x x x x x x x x x x x x x x
 40| x x x x x x x x x x x x x x x x x x x x x x x x
 |-----------------------------------------------------------------------
 |00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23

 */
