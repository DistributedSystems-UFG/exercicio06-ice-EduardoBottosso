import com.zeroc.Ice.*;

public class Client {
    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {

            ObjectPrx base1 = communicator.stringToProxy(
                "SimplePrinter1:tcp -h 172.31.83.218 -p 11000"
            );
            ObjectPrx base2 = communicator.stringToProxy(
                "SimplePrinter2:tcp -h 172.31.83.218 -p 11000"
            );

            Demo.PrinterPrx printer1 = Demo.PrinterPrx.checkedCast(base1);
            Demo.PrinterPrx printer2 = Demo.PrinterPrx.checkedCast(base2);

            if (printer1 == null || printer2 == null) {
                throw new Error("Invalid proxy");
            }

            System.out.println("Chamando métodos do objeto servidor 1...");
            printer1.printString("Hello from Goiania to object 1!");
            printer1.printUpperText("Hello from Goiania to object 1!");

            System.out.println("Chamando métodos do objeto servidor 2...");
            printer2.printString("Hello from Goiania to object 2!");
            printer2.printUpperText("Hello from Goiania to object 2!");

        } catch (LocalException e) {
            e.printStackTrace();
        }
    }
}
