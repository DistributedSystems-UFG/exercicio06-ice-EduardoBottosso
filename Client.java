import com.zeroc.Ice.*;

public class Client {
    public static void main(String[] args) {
        // 1. Initialize the Ice communicator within a try-with-resources block
        try (Communicator communicator = Util.initialize(args)) {

            // 2. Create a proxy to the remote 'Printer' object
            // Replace '10.0.0.5' with the actual IP of your ICE server
            ObjectPrx base = communicator.stringToProxy("SimplePrinter1:tcp -h 54.234.163.189 -p 11000");

            // 3. Downcast the proxy to the Printer interface
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);

            if (printer == null) {
                throw new Error("Invalid proxy");
            }

            // 4. Call the remote method
            printer.printString("Hello from Goiania!");
            printer.printUpperText("Hello from Goiania!");

        } catch (LocalException e) {
            e.printStackTrace();
        }
    }
}
