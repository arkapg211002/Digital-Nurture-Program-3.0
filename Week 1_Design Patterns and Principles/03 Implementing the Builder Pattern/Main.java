public class Main {
    public static void main(String[] args) {

        System.out.println();
        // Creating a computer with default configuration
        Computer basicComputer = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM("8GB")
                .setStorage("1TB HDD")
                .build();

        // Creating a computer with all optional parts
        Computer gamingComputer = new Computer.Builder()
                .setCPU("AMD Ryzen 5")
                .setRAM("16GB")
                .setStorage("512GB SSD")
                .setGPU("NVIDIA GeForce RTX 3080")
                .setSSD(true)
                .build();

        // Printing the computers
        System.out.println("Basic Computer: " + basicComputer);
        System.out.println("Gaming Computer: " + gamingComputer);
    }
}
