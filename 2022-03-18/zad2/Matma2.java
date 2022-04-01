package zad2;

public class Matma2 {
    public static void main(String[] args) {
        Parabola one = new Parabola(1,-1,5./4.);
        System.out.println(FunDD.xminmax(one,0,1));
        System.out.println(FunDD.xminmax(new FunDD() {
            @Override
            public double fun(double x) {
                return Math.sqrt(Math.pow((x-0.75),2)+1);
            }
        }, 0, 2));
        System.out.println(FunDD.xminmax(x -> (x*x*(x-2)),0,2));
    }
}

@FunctionalInterface
interface FunDD {
    double fun(double x);
    static double xminmax(FunDD f, double a, double b) {
        double min = f.fun(a);
        double result = 0;
        for (double check = a; check <= b; check += 1e-5) {
            double result_fun = f.fun(check);
            if (result_fun < min){
                min = result_fun;
                result = check;
            }
        }
        return result;
    }
}

class Parabola implements FunDD {
    double a;
    double b;
    double c;

    public Parabola(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double fun(double x) {
        return a * x * x + b * x + c;
    }
}