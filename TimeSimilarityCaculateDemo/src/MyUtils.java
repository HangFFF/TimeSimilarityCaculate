/**
 * @author FengH
 * @date 2023/11/24 15:56
 */
import java.time.LocalDate;
public class MyUtils {
    //connection计算的是拓扑关系
    public static int connect_calculate_1(LocalDate date_a0, LocalDate date_b0) {
        if (date_a0.toEpochDay() < date_b0.toEpochDay()) {
            return 1;
        }
        //System.out.println("date_a0.toEpochDay()==" +date_a0.toEpochDay());
        //System.out.println("date_b0.toEpochDay()==" +date_b0.toEpochDay());
        if (date_a0.toEpochDay() == date_b0.toEpochDay()) return 2;
        if (date_a0.toEpochDay() > date_b0.toEpochDay()) return 3;
        return 0;
    }

    public static int connect_calculate_2(LocalDate date_a0, LocalDate date_b1, LocalDate date_b2) {
        if (date_a0.toEpochDay() < date_b1.toEpochDay()) {
            return 11;
        }
        if (date_a0.toEpochDay() == date_b1.toEpochDay()) return 12;
        if (date_a0.toEpochDay() > date_b1.toEpochDay() && date_a0.toEpochDay() < date_b2.toEpochDay()) return 13;
        if (date_a0.toEpochDay() == date_b2.toEpochDay()) return 14;
        if (date_a0.toEpochDay() > date_b2.toEpochDay()) return 15;

        return 0;
    }

    public static int connect_calculate_3(LocalDate date_a1, LocalDate date_a2, LocalDate date_b0) {
        if (date_b0.toEpochDay() < date_a1.toEpochDay()) {
            return 21;
        }
        if (date_b0.toEpochDay() == date_a1.toEpochDay()) return 22;
        if (date_b0.toEpochDay() > date_a1.toEpochDay() && date_b0.toEpochDay() < date_a2.toEpochDay()) return 23;
        if (date_b0.toEpochDay() == date_a2.toEpochDay()) return 24;
        if (date_b0.toEpochDay() > date_a2.toEpochDay()) return 25;

        return 0;
    }

    public static int connect_calculate_4(LocalDate date_a1, LocalDate date_a2, LocalDate date_b1, LocalDate date_b2) {
        long a1 = date_a1.toEpochDay();
        long a2 = date_a2.toEpochDay();
        long b1 = date_b1.toEpochDay();
        long b2 = date_b2.toEpochDay();

        //if (b1 > a2) return 31;//before
        //if (a1 > b2) return 32;
        if (b1 > 1+a2) return 31;//before
        if (a1 > 1+b2) return 32;
        //if (a1 == (b2 + 1)) return 33;//meets
        //if (b1 == (a2 + 1)) return 34;
        if (a1 == (b2 + 1)) return 34;
        if (b1 == (a2 + 1)) return 33;//meets
        if (a1 < b1 && a2 < b2 && b1 <= a2) return 35;//overlap
        if (b1 < a1 && b2 < a2 && a1 <= b2) return 36;
        if (a1 == b1 && a2 < b2) return 37;//starts
        if (a1 == b1 && a2 > b2) return 38;
        if (a2 == b2 && a1 > b1) return 39;//ends
        if (a2 == b2 && a1 < b1) return 40;
        if (a1 > b1 && a2 < b2) return 41;//during
        if (a1 < b1 && a2 > b2) return 42;//contain
        if (a1 == b1 && a2 == b2) return 43;//equal
        return 0;
    }

    //四种类别的相似度计算
    public static double similarity_calculate_1(LocalDate date_a0, LocalDate date_b0, double W1, double W2, double W3, double W4) {

        double s = 0.0d;
        //System.out.println("date_a.toEpochDay()==" +date_a0.toEpochDay());
        //System.out.println("date_b0.toEpochDay()==" +date_b0.toEpochDay());
        if (date_a0.toEpochDay() < date_b0.toEpochDay()) {
            int t = (int) (date_b0.toEpochDay() - date_a0.toEpochDay());
            s = W1*Math.atan(Math.tan(1.0d)/(double)t);
            return s;
        }
        if (date_a0.toEpochDay() == date_b0.toEpochDay()) return 1.0D;
        if (date_a0.toEpochDay() > date_b0.toEpochDay())
            return W1*Math.atan(Math.tan(1.0d)/(double)(date_a0.toEpochDay() - date_b0.toEpochDay()));
        return 0.0D;
    }

    public static double similarity_calculate_2(LocalDate date_a0, LocalDate date_b1, LocalDate date_b2, double W1, double W2, double W3, double W4) {
        //System.out.println("date_a0.toEpochDay()==" +date_a0.toEpochDay());
        //System.out.println("date_b0.toEpochDay()==" +date_b1.toEpochDay()+"  "+(((float)date_b2.toEpochDay()+(float)date_b1.toEpochDay())/2.0F-date_a0.toEpochDay()));
        double d;
        if (date_a0.toEpochDay() < date_b1.toEpochDay())//相离
        {
            d=(((double) date_b2.toEpochDay() + (double) date_b1.toEpochDay()) / 2.0d - (double) date_a0.toEpochDay());
            return W1*Math.atan(Math.tan(1.0d)/(double)d);
        }


        if (date_a0.toEpochDay() == date_b1.toEpochDay())
            return W1 + W2 + W3 / ((double) date_b2.toEpochDay() - (double) date_b1.toEpochDay() + 1.0d);//时间长度加一

        if (date_a0.toEpochDay() > date_b1.toEpochDay() && date_a0.toEpochDay() < date_b2.toEpochDay())
            return W1 + W2 + W3 / ((double) date_b2.toEpochDay() - (double) date_b1.toEpochDay() + 1.0d);//时间长度加一

        if (date_a0.toEpochDay() == date_b2.toEpochDay())
            return W1 + W2 + W3 / ((double) date_b2.toEpochDay() - (double) date_b1.toEpochDay() + 1.0d);//时间长度加一

        if (date_a0.toEpochDay() > date_b2.toEpochDay()){
            d=((double) date_a0.toEpochDay() - ((double) date_b2.toEpochDay() + (double) date_b1.toEpochDay()) / 2.0d);
            return W1*Math.atan(Math.tan(1.0d)/(double)d);
        }
        return 0.0d;
    }

    public static double similarity_calculate_3(LocalDate date_a1, LocalDate date_a2, LocalDate date_b0, double W1, double W2, double W3, double W4) {

        double d;
        if (date_b0.toEpochDay() < date_a1.toEpochDay()) {
            d=(((double) date_a2.toEpochDay() + (double) date_a1.toEpochDay()) / 2.0d - date_b0.toEpochDay());
            return W1*Math.atan(Math.tan(1.0d)/(double)d);
        }
        if (date_b0.toEpochDay() == date_a1.toEpochDay())
            return W1 + W2 + W3 / ((double) date_a2.toEpochDay() - (double) date_a1.toEpochDay() + 1.0d);

        if (date_b0.toEpochDay() > date_a1.toEpochDay() && date_b0.toEpochDay() < date_a2.toEpochDay())
            return W1 + W2 + W3 / ((double) date_a2.toEpochDay() - (double) date_a1.toEpochDay() + 1.0d);

        if (date_b0.toEpochDay() == date_a2.toEpochDay())
            return W1 + W2 + W3 / ((double) date_a2.toEpochDay() - (double) date_a1.toEpochDay() + 1.0d);

        if (date_b0.toEpochDay() > date_a2.toEpochDay()){
            d=(date_b0.toEpochDay() - ((double) date_a2.toEpochDay() + (double) date_a1.toEpochDay()) / 2.0d);
            return W1*Math.atan(Math.tan(1.0d)/(double)d);
        }
        return 0.0d;
    }

    public static double similarity_calculate_4(LocalDate date_a1, LocalDate date_a2, LocalDate date_b1, LocalDate date_b2, double W1, double W2, double W3, double W4) {
        long a1 = date_a1.toEpochDay();
        long a2 = date_a2.toEpochDay();
        long b1 = date_b1.toEpochDay();
        long b2 = date_b2.toEpochDay();
        double d;

        if (b1 > (a2 + 1)){
            d= (((double) b1 + (double) b2) / 2.0d - ((double) a1 + (double) a2) / 2.0d);//before
            return W1*Math.atan(Math.tan(1.0d)/(double)d);
        }

        if (a1 > (b2 + 1)){
            d=(((double) a1 + (double) a2) / 2.0d - ((double) b1 + (double) b2) / 2.0d);
            return W1*Math.atan(Math.tan(1.0d)/(double)d);
        }

        if (b1 == (a2 + 1)){
            //代码反了，添加一个负号
            d=-(((double) a1 + (double) a2) / 2.0D - ((double) b1 + (double) b2) / 2.0D);//meets
            return W1 + W2*Math.atan(Math.tan(1.0d)/(double)d);
        }
        if (a1 == (b2 + 1)){
            d=-(((double) b1 + (double) b2) / 2.0D - ((double) a1 + (double) a2) / 2.0D);
            return W1 + W2*Math.atan(Math.tan(1.0d)/(double)d);
        }

        if (a1 < b1 && (a2 < b2) && (b1 <= a2))
            return W1 + W2 + W3 + W4 * 2.0D * (double) (a2 - b1 + 1) / (double) (a2 + b2 - a1 - b1 + 2);//overlap
        if (b1 < a1 && b2 < a2 && a1 <= b2)
            return W1 + W2 + W3 + W4 * 2.0D * (double) (b2 - a1 + 1) / (double) (a2 + b2 - a1 - b1 + 2);

        if (a1 == b1 && a2 < b2)
            return W1 + W2 + W3 + W4 * 2.0D * (double) (a2 - a1 + 1) / (double) (a2 + b2 - a1 - b1 + 2);//starts
        if (a1 == b1 && a2 > b2)
            return W1 + W2 + W3 + W4 * 2.0D * (double) (b2 - b1 + 1) / (double) (a2 + b2 - a1 - b1 + 2);
        if (a2 == b2 && a1 > b1)
            return W1 + W2 + W3 + W4 * 2.0D * (double) (a2 - a1 + 1) / (double) (a2 + b2 - a1 - b1 + 2);//ends
        if (a2 == b2 && a1 < b1)
            return W1 + W2 + W3 + W4 * 2.0D * (double) (b2 - b1 + 1) / (double) (a2 + b2 - a1 - b1 + 2);
        if (a1 > b1 && a2 < b2)
            return W1 + W2 + W3 + W4 * 2.0D * (double) (a2 - a1 + 1) / (double) (a2 + b2 - a1 - b1 + 2);//during
        if (a1 < b1 && a2 > b2)
            return W1 + W2 + W3 + W4 * 2.0D * (double) (b2 - b1 + 1) / (double) (a2 + b2 - a1 - b1 + 2);//contain
        if (a1 == b1 && a2 == b2) return 1.0D;//equal
        return 0.0D;
    }

}
