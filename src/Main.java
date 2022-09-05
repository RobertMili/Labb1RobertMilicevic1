/*
printa kryss med char in denna diagram
 */

import java.util.*;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] input = new int[24];                                                                                       // array for input
        String[] timer = {"00 - 01", "01 - 02", "02 - 03", "03 - 04", "04 - 05", "05 - 06", "06 - 07", "07 - 08",       // array for tiden
                "08 - 09", "09 - 10", "10 - 11", "11 - 12", "12 - 13", "13 - 14", "14 - 15",
                "15 - 16", "16 - 17", "17 - 18", "18 - 19", "19 - 20", "20 - 21", "21 - 22", "22 - 23", "23 - 00"};
        String[] tiden = {"00", "01", "02", "03", "04", "05", "06", "07",                                               // array for tiden att skriva ut i diagram
                "08", "09", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        // System.out.println(Arrays.toString(new String[]{timer[1]}));

        Map<Integer,String> mapOfList = new HashMap<>();                                                                // HashMap för att addera Integer och String så kan senare sortera och printa ut

        boolean goingThroughtProgram = true;                                                                            // Den gå i while loop tills bli true så ska avsluta program

        while (goingThroughtProgram) {                                                                                   //Program ska repetara till man trycker e eller E för att gå ut
            boolean goingThroughtProgramTwo = true;

            System.out.println();
            System.out.println("Elpriser");
            System.out.println("========");
            System.out.println("1. Inmatning");
            System.out.println("2. Min,Max och Medel");
            System.out.println("3. Sortera");
            System.out.println("4. Bästa Laddningstid (4h)");
            System.out.println("5. Visualisering (VG uppgift)");
            System.out.println("e. avsluta");

            if (sc.hasNextInt()) {                                                                                      // Input för välja meny
                int choice = sc.nextInt();



                if (choice == 1) {
                    choice1(sc, input, timer, mapOfList);

                } else if (choice == 2) {                                                                               // Andra meny öppnar när man trycker 2

                    while (goingThroughtProgramTwo) {
                        goingThroughtProgramTwo = true;

                        System.out.println("Välja:");
                        System.out.println("1. Medel av elpris:");
                        System.out.println("2. Min av elpris");
                        System.out.println("3. Max av elpris");
                        System.out.println("4. Exit");

                        int choiceThrouMinMax = sc.nextInt();
                        int sumTwo = 0;

                        goingThroughtProgramTwo = menyAverageMinMax(input, goingThroughtProgramTwo, choiceThrouMinMax, sumTwo);
                    }

                } else if (choice == 3) {                                                                               // Sortering genom Arrays.sort, man kan sortera också med bubble sortering method som jag hade skrivit
                    System.out.println("Sorterad");

                    sortingHashMap((HashMap<Integer, String>) mapOfList);



                } else if (choice == 4) {
                    System.out.println("Bästa laddningstid (4 h) : ");                                                  // Skriver bästa tid för laddning och den är första 4 tiden på natten

                    bestCharchingTime(input, timer);
                } else if (choice == 5) {
                    System.out.println("5. Visualisering (VG uppgift)");
                    tabelVG(input, tiden);

                }
            } else {

                String choice = sc.nextLine();                                                                           // Avslutar program genom att trycka e eller E

                if (choice.equals("e") || choice.equals("E")) {

                    System.out.println("\nexit.");
                    goingThroughtProgram = false;
                } else if (choice != "e" || choice != "E") {
                    System.out.println("\nDu hade väljat fel, prova igen");
                }
            }
        }
    }

    private static void bestCharchingTime(int[] input, String[] timer) {
        for (int i = 0; i < 4; i++) {
            System.out.println(timer[i]+"   " + input[i] + " öre");
        }
    }

    private static boolean menyAverageMinMax(int[] input, boolean goingThroughtProgramTwo, int choiceThrouMinMax, int sumTwo) {
        if (choiceThrouMinMax == 1) {                                                                   // Räknar genomsnit
            for (int i = 0; i < input.length; i++) {
                sumTwo += input[i];
            }
            int average = sumTwo / 2;
            System.out.println("Medel av elpris är " + average);
        } else if (choiceThrouMinMax == 2) {                                                            // Räknar MIN tal genom att sortera med bubble sort algoritam så tar första talet
            bubbleSortering(input);         // calling bubble sortering
            int minNumber = input[0];
            System.out.println("Den här minmum pris elpirs är: " + minNumber + " öre");

        } else if (choiceThrouMinMax == 3) {
            bubbleSortering(input);
            int maxNumber = input[23];
            System.out.println("Den här är max pris på elpris är : " + maxNumber + " öre");             // Räknar MAX tal genom att sortera med bubble sort algoritam och tar sista tal och skriver ut
        } else {
            if (choiceThrouMinMax == 4) {
                System.out.println("Du valde EXIT till main meny ");
                goingThroughtProgramTwo = false;
            } else {
                System.out.println("Du har väljat fel, prova igen");
            }
        }
        return goingThroughtProgramTwo;
    }

    private static void choice1(Scanner sc, int[] input, String[] timer, Map<Integer, String> mapOfList) {
        System.out.println("Matta in pris under dygnets timmar: ");


        for (int i = 0; i < input.length; i++) {                                                            // Skriver ut tiden och ta input för el pris från user
            System.out.println("Matta in pris unde: " + timer[i]);
            input[i] = sc.nextInt();

            mapOfList.put(input[i], timer[i]);

        }
    }


    public static void sortingHashMap(HashMap<Integer,String>mapOfList) {                                               // Method för soreting Hash map med TreeMap

        TreeMap<Integer, String> tm = new TreeMap<Integer, String>(mapOfList);
        Iterator itr = tm.keySet().iterator();
        while (itr.hasNext()) {
            int key = (int) itr.next();

            System.out.println(mapOfList.get(key) + "  " + key + " öre");

        }
    }

    public static int[] bubbleSortering(int[] input) {                                                                   // Bubble sortering algoritam
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


    public static void tabelVG(int[] input, String[] timer) {                                                           // Skriver ut tabel

        int[][] rowColumns = new int[8][51];

        for (int i = 0; i < rowColumns.length; i++) {
            for (int j = 0; j < rowColumns[i].length; j++) {



                if (j == 1)
                    System.out.print("|");
                else if (i == 7 && j == 0)
                    System.out.print("   ");
                else if (i == 6 && j == 0)
                    System.out.print("0  ");
                else if (i == 5 && j == 0)
                    System.out.print("10 ");
                else if (i == 4 && j == 0)
                    System.out.print("20 ");
                else if (i == 3 && j == 0)
                    System.out.print("30 ");
                else if (i == 2 && j == 0)
                    System.out.print("40 ");
                else if (i == 1 && j == 0)
                    System.out.print("50 ");
                else if (i == 0 && j == 0)
                    System.out.print("60+");



                else if (input[0] > 0 && input[0] <= 10 && j == 2 && i == 6)                                               // input [0] printing out in tabel
                    System.out.print("x ");
                else if (input[0] > 10 && input[0] <= 20 && j == 2 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[0] > 20 && input[0] <= 30 && j == 2 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[0] > 30 && input[0] <= 40 && j == 2 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[0] > 40 && input[0] <= 50 && j == 2 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[0] > 50 && input[0] <= 60 && j == 2 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[0] > 60 && j == 2 && i <= 6)
                    System.out.print("x ");

                else if (input[1] > 0 && input[1] <= 10 && j == 4 && i == 6)                                               // input [1] printing out in tabel
                    System.out.print("x ");
                else if (input[1] > 10 && input[1] <= 20 && j == 4 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[1] > 20 && input[1] <= 30 && j == 4 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[1] > 30 && input[1] <= 40 && j == 4 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[1] > 40 && input[1] <= 50 && j == 4 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[1] > 50 && input[1] <= 60 && j == 4 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[1] > 60  && j == 4 && i <= 6)
                    System.out.print("x ");

                else if (input[2] > 0 && input[2] <= 10 && j == 6 && i == 6)                                               // input [2] printing out in tabel
                    System.out.print("x ");
                else if (input[2] > 10 && input[2] <= 20 && j == 6 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[2] > 20 && input[2] <= 30 && j == 6 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[2] > 30 && input[2] <= 40 && j == 6 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[2] > 40 && input[2] <= 50 && j == 6 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[2] > 50 && input[2] <= 60 && j == 6 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[2] > 60  && j == 6 && i <= 6)
                    System.out.print("x ");
//
                else if (input[3] > 0 && input[3] <= 10 && j == 8 && i == 6)                                               // input [3] printing out in tabel
                    System.out.print("x ");
                else if (input[3] > 10 && input[3] <= 20 && j == 8 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[3] > 20 && input[3] <= 30 && j == 8 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[3] > 30 && input[3] <= 40 && j == 8 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[3] > 40 && input[3] <= 50 && j == 8 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[3] > 50 && input[3] <= 60 && j == 8 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[3] > 60  && j == 8 && i <= 6)
                    System.out.print("x ");
//
                else if (input[4] > 0 && input[4] <= 10 && j == 10 && i == 6)                                               // input [4] printing out in tabel
                    System.out.print("x ");
                else if (input[4] > 10 && input[4] <= 20 && j == 10 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[4] > 20 && input[4] <= 30 && j == 10 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[4] > 30 && input[4] <= 40 && j == 10 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[4] > 40 && input[4] <= 50 && j == 10 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[4] > 50 && input[4] <= 60 && j == 10 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[4] > 60  && j == 10 && i <= 6)
                    System.out.print("x ");

                else if (input[5] > 0 && input[5] <= 10 && j == 12 && i == 6)                                               // input [5] printing out in tabel
                    System.out.print("x ");
                else if (input[5] > 10 && input[5] <= 20 && j == 12 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[5] > 20 && input[5] <= 30 && j == 12 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[5] > 30 && input[5] <= 40 && j == 12 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[5] > 40 && input[5] <= 50 && j == 12 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[5] > 50 && input[5] <= 60 && j == 12 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[5] > 60  && j == 12 && i <= 6)
                    System.out.print("x ");

                else if (input[6] > 0 && input[6] <= 10 && j == 14 && i == 6)                                               // input [6] printing out in tabel
                    System.out.print("x ");
                else if (input[6] > 10 && input[6] <= 20 && j == 14 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[6] > 20 && input[6] <= 30 && j == 14 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[6] > 30 && input[6] <= 40 && j == 14 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[6] > 40 && input[6] <= 50 && j == 14 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[6] > 50 && input[6] <= 60 && j == 14 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[6] > 60  && j == 14 && i <= 6)
                    System.out.print("x ");

                else if (input[7] > 0 && input[7] <= 10 && j == 16 && i == 6)                                               // input [7] printing out in tabel
                    System.out.print("x ");
                else if (input[7] > 10 && input[7] <= 20 && j == 16 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[7] > 20 && input[7] <= 30 && j == 16 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[7] > 30 && input[7] <= 40 && j == 16 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[7] > 40 && input[7] <= 50 && j == 16 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[7] > 50 && input[7] <= 60 && j == 16 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[7] > 60  && j == 16 && i <= 6)
                    System.out.print("x ");

                else if (input[8] > 0 && input[8] <= 10 && j == 18 && i == 6)                                               // input [8] printing out in tabel
                    System.out.print("x ");
                else if (input[8] > 10 && input[8] <= 20 && j == 18 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[8] > 20 && input[8] <= 30 && j == 18 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[8] > 30 && input[8] <= 40 && j == 18 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[8] > 40 && input[8] <= 50 && j == 18 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[8] > 50 && input[8] <= 60 && j == 18 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[8] > 60  && j == 18 && i <= 6)
                    System.out.print("x ");

                else if (input[9] > 0 && input[9] <= 10 && j == 20 && i == 6)                                               // input [9] printing out in tabel
                    System.out.print("x ");
                else if (input[9] > 10 && input[9] <= 20 && j == 20 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[9] > 20 && input[9] <= 30 && j == 20 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[9] > 30 && input[9] <= 40 && j == 20 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[9] > 40 && input[9] <= 50 && j == 20 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[9] > 50 && input[9] <= 60 && j == 20 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[9] > 60  && j == 20 && i <= 6)
                    System.out.print("x ");

                else if (input[10] > 0 && input[10] <= 10 && j == 22 && i == 6)                                               // input [10] printing out in tabel
                    System.out.print("x ");
                else if (input[10] > 10 && input[10] <= 20 && j == 22 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[10] > 20 && input[10] <= 30 && j == 22 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[10] > 30 && input[10] <= 40 && j == 22 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[10] > 40 && input[10] <= 50 && j == 22 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[10] > 50 && input[10] <= 60 && j == 22 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[10] > 60  && j == 22 && i <= 6)
                    System.out.print("x ");

                else if (input[11] > 0 && input[11] <= 10 && j == 24 && i == 6)                                               // input [11] printing out in tabel
                    System.out.print("x ");
                else if (input[11] > 10 && input[11] <= 20 && j == 24 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[11] > 20 && input[11] <= 30 && j == 24 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[11] > 30 && input[11] <= 40 && j == 24 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[11] > 40 && input[11] <= 50 && j == 24 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[11] > 50 && input[11] <= 60 && j == 24 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[11] > 60  && j == 24 && i <= 6)
                    System.out.print("x ");

                else if (input[12] > 0 && input[12] <= 10 && j == 26 && i == 6)                                               // input [12] printing out in tabel
                    System.out.print("x ");
                else if (input[12] > 10 && input[12] <= 20 && j == 26 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[12] > 20 && input[12] <= 30 && j == 26 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[12] > 30 && input[12] <= 40 && j == 26 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[12] > 40 && input[12] <= 50 && j == 26 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[12] > 50 && input[12] <= 60 && j == 26 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[12] > 60  && j == 26 && i <= 6)
                    System.out.print("x ");

                else if (input[13] > 0 && input[13] <= 10 && j == 28 && i == 6)                                               // input [13] printing out in tabel
                    System.out.print("x ");
                else if (input[13] > 10 && input[13] <= 20 && j == 28 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[13] > 20 && input[13] <= 30 && j == 28 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[13] > 30 && input[13] <= 40 && j == 28 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[13] > 40 && input[13] <= 50 && j == 28 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[13] > 50 && input[13] <= 60 && j == 28 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[13] > 60  && j == 28 && i <= 6)
                    System.out.print("x ");

                else if (input[14] > 0 && input[14] <= 10 && j == 30 && i == 6)                                               // input [14] printing out in tabel
                    System.out.print("x ");
                else if (input[14] > 10 && input[14] <= 20 && j == 30 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[14] > 20 && input[14] <= 30 && j == 30 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[14] > 30 && input[14] <= 40 && j == 30 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[14] > 40 && input[14] <= 50 && j == 30 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[14] > 50 && input[14] <= 60 && j == 30 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[14] > 60  && j == 30 && i <= 6)
                    System.out.print("x ");

                else if (input[15] > 0 && input[15] <= 10 && j == 32 && i == 6)                                               // input [15] printing out in tabel
                    System.out.print("x ");
                else if (input[15] > 10 && input[15] <= 20 && j == 32 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[15] > 20 && input[15] <= 30 && j == 32 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[15] > 30 && input[15] <= 40 && j == 32 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[15] > 40 && input[15] <= 50 && j == 32 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[15] > 50 && input[15] <= 60 && j == 32 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[15] > 60  && j == 32 && i <= 6)
                    System.out.print("x ");

                else if (input[16] > 0 && input[16] <= 10 && j == 34 && i == 6)                                               // input [16] printing out in tabel
                    System.out.print("x ");
                else if (input[16] > 10 && input[16] <= 20 && j == 34 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[16] > 20 && input[16] <= 30 && j == 34 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[16] > 30 && input[16] <= 40 && j == 34 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[16] > 40 && input[16] <= 50 && j == 34 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[16] > 50 && input[16] <= 60 && j == 34 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[16] > 60  && j == 34 && i <= 6)
                    System.out.print("x ");


                else if (input[17] > 0 && input[17] <= 10 && j == 36 && i == 6)                                               // input [18] printing out in tabel
                    System.out.print("x ");
                else if (input[17] > 10 && input[17] <= 20 && j == 36 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[17] > 20 && input[17] <= 30 && j == 36 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[17] > 30 && input[17] <= 40 && j == 36 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[17] > 40 && input[17] <= 50 && j == 36 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[17] > 50 && input[17] <= 60 && j == 36 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[17] > 60  && j == 38 && i <= 6)
                    System.out.print("x ");

                else if (input[18] > 0 && input[18] <= 10 && j == 38 && i == 6)                                               // input [18] printing out in tabel
                    System.out.print("x ");
                else if (input[18] > 10 && input[18] <= 20 && j == 38 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[18] > 20 && input[18] <= 30 && j == 38 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[18] > 30 && input[18] <= 40 && j == 38 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[18] > 40 && input[18] <= 50 && j == 38 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[18] > 50 && input[18] <= 60 && j == 38 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[18] > 60  && j == 40 && i <= 6)
                    System.out.print("x ");

                else if (input[19] > 0 && input[19] <= 10 && j == 40 && i == 6)                                               // input [19] printing out in tabel
                    System.out.print("x ");
                else if (input[19] > 10 && input[19] <= 20 && j == 40 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[19] > 20 && input[19] <= 30 && j == 40 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[19] > 30 && input[19] <= 40 && j == 40 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[19] > 40 && input[19] <= 50 && j == 40 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[19] > 50 && input[19] <= 60 && j == 40 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[19] > 60  && j == 42 && i <= 6)
                    System.out.print("x ");

                else if (input[20] > 0 && input[20] <= 10 && j == 42 && i == 6)                                               // input [20] printing out in tabel
                    System.out.print("x ");
                else if (input[20] > 10 && input[20] <= 20 && j == 42 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[20] > 20 && input[20] <= 30 && j == 42 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[20] > 30 && input[20] <= 40 && j == 42 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[20] > 40 && input[20] <= 50 && j == 42 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[20] > 50 && input[20] <= 60 && j == 42 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[20] > 60  && j == 44 && i <= 6)
                    System.out.print("x ");

                else if (input[21] > 0 && input[21] <= 10 && j == 44 && i == 6)                                               // input [21] printing out in tabel
                    System.out.print("x ");
                else if (input[21] > 10 && input[21] <= 20 && j == 44 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[21] > 20 && input[21] <= 30 && j == 44 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[21] > 30 && input[21] <= 40 && j == 44 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[21] > 40 && input[21] <= 50 && j == 44 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[21] > 50 && input[21] <= 60 && j == 44 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[21] > 60  && j == 46 && i <= 6)
                    System.out.print("x ");

                else if (input[22] > 0 && input[22] <= 10 && j == 46 && i == 6)                                               // input [22] printing out in tabel
                    System.out.print("x ");
                else if (input[22] > 10 && input[22] <= 20 && j == 46 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[22] > 20 && input[22] <= 30 && j == 46 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[22] > 30 && input[22] <= 40 && j == 46 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[22] > 40 && input[22] <= 50 && j == 46 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[22] > 50 && input[22] <= 60 && j == 46 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[22] > 60  && j == 48 && i <= 6)
                    System.out.print("x ");

                else if (input[23] > 0 && input[23] <= 10 && j == 48 && i == 6)                                               // input [23] printing out in tabel
                    System.out.print("x ");
                else if (input[23] > 10 && input[23] <= 20 && j == 48 && i <= 6 && i >= 5)
                    System.out.print("x ");
                else if (input[23] > 20 && input[23] <= 30 && j == 48 && i <= 6 && i >= 4)
                    System.out.print("x ");
                else if (input[23] > 30 && input[23] <= 40 && j == 48 && i <= 6 && i >= 3)
                    System.out.print("x ");
                else if (input[23] > 40 && input[23] <= 50 && j == 48 && i <= 6 && i >= 2)
                    System.out.print("x ");
                else if (input[23] > 50 && input[23] <= 60 && j == 48 && i <= 6 && i >= 1)
                    System.out.print("x ");
                else if (input[23] > 60  && j == 50 && i <= 6)
                    System.out.print("x ");


                else if (j == 3 || j == 5 || j == 7 || j == 9 || j == 11 || j == 13 || j == 15 || j == 17 ||        // Making in row free space
                        j == 19 || j == 21 || j == 23 || j == 25 || j ==27 || j == 29 || j == 31 || j == 33 || j ==35 ||
                        j == 37 || j == 39 || j == 41 || j == 43 || j == 45 || j == 47 || j == 49 || j == 50 || i == 7 && j == 50) {
                    System.out.print(" ");
                } else if (i >= 7) {
                    printOutArray(timer);
                    break;
                } else System.out.print("  ");
            }
            System.out.println("");
        }
    }



    public static String[] printOutArray (String[]tiden){                                                           // Method för skriva ut array time i tabel i hela row 7

        for (int i = 0; i < 24; i++) {
            System.out.print(tiden[i] + " ");
        }
        return tiden;
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
