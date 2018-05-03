/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcdscreen;

/**
 *
 * @author Oscar VM
 */
public class Utils {

    static String join(String[] strings) {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            sb.append(string);
        }
        return sb.toString();
    }

    static String[] arrangeHorizontally(String[][] data) {
        if (data.length <= 0) {
            throw new AssertionError();
        }

        String[] result = new String[data.length];

        for (int row = 0; row < data.length; row++) {
            result[row] = "";
            for (String item : data[row]) {
                result[row] += item;
            }
        }
        return result;
    }

}
