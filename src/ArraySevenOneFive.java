public class ArraySevenOneFive {
    public static void main(String[] args) {
        int length = Integer.parseInt(args[0]);

        String[] array;
        if (args.length == 0) {
            array = new String[10];

        } else {
            array = new String[length];
        }
        //ariyotolulademi@gmail.com
        //  array = args;
        System.out.printf("%8s      %8s%n", "Index", "Value");

        for (int counter = 0; counter < array.length; counter++)
            System.out.printf("%8d      %8s%n", counter, array[counter]);
    }
}
