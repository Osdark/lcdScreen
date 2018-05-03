package lcdscreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Oscar VM
 */
public class LcdPrinter {

    //Posiciones fijas
    private static final int NONE = 0;
    private static final int LEFT_UP = 1;
    private static final int UP = 2;
    private static final int RIGHT_UP = 3;
    private static final int MIDDLE = 4;
    private static final int LEFT_DOWN = 5;
    private static final int DOWN = 6;
    private static final int RIGHT_DOWN = 7;
    //Caracteres 
    private static final String SPACE_CHAR = " ";
    private static final String ROW_CHAR = "-";
    private static final String COLUMN_CHAR = "|";
    private static final int SPACE = 0;
    private static final int ROW = 1;
    private static final int COLUMN = 2;

    private static final Map<Integer, int[]> LCD_NUMBERS = new HashMap<Integer, int[]>() {
        {
            put(1, new int[]{RIGHT_UP, RIGHT_DOWN});
            put(2, new int[]{UP, MIDDLE, RIGHT_UP, LEFT_DOWN, DOWN});
            put(3, new int[]{UP, RIGHT_UP, MIDDLE, RIGHT_DOWN, DOWN});
            put(4, new int[]{LEFT_UP, RIGHT_UP, MIDDLE, RIGHT_DOWN});
            put(5, new int[]{UP, LEFT_UP, RIGHT_DOWN, MIDDLE, DOWN});
            put(6, new int[]{UP, LEFT_UP, LEFT_DOWN, RIGHT_DOWN, MIDDLE, DOWN});
            put(7, new int[]{UP, RIGHT_UP, MIDDLE, RIGHT_DOWN});
            put(8, new int[]{UP, LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN, MIDDLE, DOWN});
            put(9, new int[]{UP, LEFT_UP, RIGHT_UP, RIGHT_DOWN, MIDDLE, DOWN});
            put(0, new int[]{UP, LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN, DOWN});

        }
    };

    public static int[] splitInput(String numberToSplit) {
        if (numberToSplit.contains(",")) {
            String[] result = numberToSplit.split(",");
            int size = Integer.parseInt(result[0]);
            int number = Integer.parseInt(result[1]);
            if (size > 0 && size < 10) {
                return new int[]{size, number};
            } else {
                throw new IllegalArgumentException("El tamaño debe estar entre"
                        + " 0-10, (Actual tamaño:" + size + ")");
            }
        } else {
            throw new IllegalArgumentException("Cadena " + numberToSplit
                    + " no contiene caracter ,");
        }
    }

    static public String ToLCD(String num) throws Exception {

        if (!num.matches("[0-9]+,[0-9]+")) {
            throw new NumberFormatException();
        } else {
            int[] splittedNum = splitInput(num);
            int number = splittedNum[1];
            int size = splittedNum[0];

            ArrayList<int[][]> allSegments = getSegmentsForEachDigit(number, size);
            int[][] resultSegments = allSegments.get(0);

            for (int i = 1; i < allSegments.size(); i++) {
                resultSegments = append(resultSegments, allSegments.get(i));
            }
            String[] result = Utils.arrangeHorizontally(segmentsToString(resultSegments));
            return toTextLines(result);
        }
    }

    private static String[][] segmentsToString(int[][] resultInt) {

        String[][] result = new String[resultInt.length][resultInt[0].length];
        for (int row = 0; row < resultInt.length; row++) {
            for (int column = 0; column < resultInt[row].length; column++) {
                switch (resultInt[row][column]) {
                    case COLUMN:
                        result[row][column] = COLUMN_CHAR;
                        break;
                    case ROW:
                        result[row][column] = ROW_CHAR;
                        break;
                    case SPACE:
                        result[row][column] = SPACE_CHAR;
                        break;
                }
            }
        }
        return result;
    }

    private static String toTextLines(String[] result) {
        return Utils.join(result);
    }

    static public ArrayList<int[][]> getSegmentsForEachDigit(int number, int size) {
        String digits = Integer.toString(number);
        ArrayList<int[][]> result = new ArrayList<>(digits.length());
        for (int digitIndex = 0; digitIndex < digits.length(); digitIndex++) {
            result.add(digitIndex, buildMatrix(segmentsFor(digitAt(digits, digitIndex)), size));
        }
        return result;
    }

    private static int[][] buildMatrix(int[] segments, int size) {
        int rows = 2 * size + 3;
        int columns = size + 2;
        int[][] result = new int[rows][columns];
        int init;
        int end;
        for (int line : segments) {
            switch (line) {
                case NONE:
                    break;
                case LEFT_UP:
                    init = 0;
                    end = (rows - 1) / 2;
                    result = matrixAdd(createLineVertical(columns, rows, false, init, end), result);
                    break;
                case RIGHT_UP:
                    init = 0;
                    end = (rows - 1) / 2;
                    result = matrixAdd(createLineVertical(columns, rows, true, init, end), result);
                    break;
                case LEFT_DOWN:
                    init = ((rows - 1) / 2);
                    end = rows - 1;
                    result = matrixAdd(createLineVertical(columns, rows, false, init, end), result);
                    break;
                case RIGHT_DOWN:
                    init = ((rows - 1) / 2);
                    end = rows - 1;
                    result = matrixAdd(createLineVertical(columns, rows, true, init, end), result);
                    break;
                case UP:
                    init = 0;
                    result = matrixAdd(createLineHorizontal(columns, rows, init), result);
                    break;
                case MIDDLE:
                    init = ((rows - 1) / 2);
                    result = matrixAdd(createLineHorizontal(columns, rows, init), result);
                    break;
                case DOWN:
                    init = rows - 1;
                    result = matrixAdd(createLineHorizontal(columns, rows, init), result);
                    break;
            }
        }
        return result;
    }

    private static int[][] createLineHorizontal(int columns, int rows, int init) {
        int[][] resultNumber = new int[rows][columns];
        for (int j = 1; j < columns - 1; j++) {
            resultNumber[init][j] = 1;
        }
        return resultNumber;
    }

    private static int[][] createLineVertical(int columns, int rows, boolean b, int init, int end) {
        int[][] resultNumber = new int[rows][columns];
        int j = b ? columns - 1 : 0;
        for (int i = init + 1; i < end; i++) {
            resultNumber[i][j] = 2;
        }
        return resultNumber;
    }

    private static int[][] transposeMatrix(int[][] matrix) {
        int[][] temp = new int[matrix[0].length][matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                temp[column][row] = matrix[row][column];
            }
        }
        return temp;
    }

    static public int[][] append(int[][] firstMatrix, int[][] secondMatrix) {
        firstMatrix = transposeMatrix(firstMatrix);
        secondMatrix = transposeMatrix(secondMatrix);
        int append[][] = new int[firstMatrix.length + secondMatrix.length][];
        System.arraycopy(firstMatrix, 0, append, 0, firstMatrix.length);
        System.arraycopy(secondMatrix, 0, append, firstMatrix.length, secondMatrix.length);
        return transposeMatrix(append);
    }

    private static int[][] matrixAdd(int[][] firstMatrix, int[][] secondMatrix) {

        if ((firstMatrix.length < 0) || (firstMatrix[0].length < 0)) {
            return secondMatrix;
        }
        if ((secondMatrix.length < 0) || (secondMatrix[0].length < 0)) {
            return firstMatrix;
        }

        int[][] resultAdd = new int[firstMatrix.length][firstMatrix[0].length];

        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[i].length; j++) {
                resultAdd[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
            }
        }
        return resultAdd;
    }

    private static int digitAt(String digits, int i) {
        return Integer.parseInt(Character.toString(digits.charAt(i)));
    }

    private static int[] segmentsFor(int number) throws RuntimeException {
        int result[] = LCD_NUMBERS.get(number);
        if (null == result) {
            throw new RuntimeException("Digito no encontrado: " + number);
        }
        return result;
    }
}
