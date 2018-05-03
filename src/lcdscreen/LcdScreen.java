package lcdscreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Oscar VM
 */
public class LcdScreen {

    /**
     * @param args the command line arguments
     */
    static final String FINISH_CHAR = "0,0";

    public static void main(String[] args) {
        List<String> listaComando = new ArrayList<>();
        String input;

        try {

            try (Scanner lector = new Scanner(System.in)) {

                do {
                    System.out.print("Entrada: ");
                    input = lector.next();
                    if (!input.equalsIgnoreCase(FINISH_CHAR)) {
                        listaComando.add(input);
                    }
                } while (!input.equalsIgnoreCase(FINISH_CHAR));
            }

            Iterator<String> iterator = listaComando.iterator();
            while (iterator.hasNext()) {
                try {
                    System.out.println(LcdPrinter.ToLCD(iterator.next()));
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

}
