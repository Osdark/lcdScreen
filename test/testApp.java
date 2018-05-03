/**
 *
 * @author Oscar VM
 */
import lcdscreen.LcdPrinter;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class testApp {
    @Test(expected = NumberFormatException.class)
    public void toLCDNoNumber() throws Exception {
        LcdPrinter.ToLCD(",");
    }

    @Test(expected = RuntimeException.class)
    public void toLCDNoValidDigit() throws Exception {
        LcdPrinter.ToLCD("2,3+6");
    }


    @Test()
    public void toLCDSizeTwonumberOne() throws Exception {
        int[][] multi = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        };
        int[][] resultMatriz = LcdPrinter.getSegmentsForEachDigit(1, 2).get(0);
        assertArrayEquals(resultMatriz, multi);
    }


    @Test()
    public void toLCDSizeTwonumberTwo() throws Exception {
        int[][] multi = new int[][]{
                {0, 1, 1, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 1, 1, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 1, 1, 0}
        };
        int[][] resultMatriz = LcdPrinter.getSegmentsForEachDigit(2, 2).get(0);
        assertArrayEquals(multi, resultMatriz);
    }

    @Test()
    public void toLCDSizeTwonumberThree() throws Exception {
        int[][] multi = new int[][]{
                {0, 1, 1, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 1, 1, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 1, 1, 0}
        };
        int[][] resultMatriz = LcdPrinter.getSegmentsForEachDigit(3, 2).get(0);
        assertArrayEquals(multi, resultMatriz);
    }

    @Test()
    public void toLCDSizeTwonumberFour() throws Exception {
        int[][] multi = new int[][]{
                {0, 0, 0, 0},
                {2, 0, 0, 2},
                {2, 0, 0, 2},
                {0, 1, 1, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        };
        int[][] resultMatriz = LcdPrinter.getSegmentsForEachDigit(4, 2).get(0);
        assertArrayEquals(multi, resultMatriz);
    }

    @Test()
    public void toLCDSizeTwonumberFive() throws Exception {
        int[][] multi = new int[][]{
                {0, 1, 1, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 1, 1, 0}
        };
        int[][] resultMatriz = LcdPrinter.getSegmentsForEachDigit(5, 2).get(0);
        assertArrayEquals(multi, resultMatriz);
    }

    @Test()
    public void toLCDSizeTwonumberSix() throws Exception {
        int[][] multi = new int[][]{
                {0, 1, 1, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 1, 1, 0},
                {2, 0, 0, 2},
                {2, 0, 0, 2},
                {0, 1, 1, 0}
        };
        int[][] resultMatriz = LcdPrinter.getSegmentsForEachDigit(6, 2).get(0);
        assertArrayEquals(multi, resultMatriz);
    }

    @Test()
    public void toLCDSizeTwoNumberSeven() throws Exception {
        int[][] multi = new int[][]{
                {0, 1, 1, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 1, 1, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        };
        int[][] resultMatriz = LcdPrinter.getSegmentsForEachDigit(7, 2).get(0);
        assertArrayEquals(multi, resultMatriz);
    }

    @Test()
    public void toLCDSizeTwoNumberEight() throws Exception {
        int[][] multi = new int[][]{
                {0, 1, 1, 0},
                {2, 0, 0, 2},
                {2, 0, 0, 2},
                {0, 1, 1, 0},
                {2, 0, 0, 2},
                {2, 0, 0, 2},
                {0, 1, 1, 0}
        };
        int[][] resultMatriz = LcdPrinter.getSegmentsForEachDigit(8, 2).get(0);
        assertArrayEquals(multi, resultMatriz);
    }

    @Test()
    public void toLCDSizeTwoNumberNine() throws Exception {
        int[][] multi = new int[][]{
                {0, 1, 1, 0},
                {2, 0, 0, 2},
                {2, 0, 0, 2},
                {0, 1, 1, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 1, 1, 0}
        };
        int[][] resultMatriz = LcdPrinter.getSegmentsForEachDigit(9, 2).get(0);
        assertArrayEquals(multi, resultMatriz);
    }

    @Test()
    public void toLCDSizeTwoNumberZero() throws Exception {
        int[][] multi = new int[][]{
                {0, 1, 1, 0},
                {2, 0, 0, 2},
                {2, 0, 0, 2},
                {0, 0, 0, 0},
                {2, 0, 0, 2},
                {2, 0, 0, 2},
                {0, 1, 1, 0}
        };
        int[][] resultMatriz = LcdPrinter.getSegmentsForEachDigit(0, 2).get(0);
        assertArrayEquals(multi, resultMatriz);
    }

    @Test()
    public void toLCDNumber102() throws Exception {
        String resultMatriz = LcdPrinter.ToLCD("3,10756");
        System.out.println(resultMatriz);
        Assert.assertTrue(resultMatriz instanceof String);
    }

    @Test()
    public void appendAandB() throws Exception {
        int[][] multi = new int[][]{
                {0, 2, 2, 0},
                {0, 2, 2, 0}
        };
        int[][] multi2 = new int[][]{
                {0, 2, 2, 0},
                {0, 2, 2, 0}
        };
        int[][] append = new int[][]{
                {0, 2, 2, 0, 0, 2, 2, 0},
                {0, 2, 2, 0, 0, 2, 2, 0}
        };
        int[][] resultMatriz = LcdPrinter.append(multi, multi2);
        assertArrayEquals(append, resultMatriz);
    }
}
