import java.util.Scanner;

public class Conversor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApiClient api = new ApiClient();
        JsonAnalizador analizador = new JsonAnalizador();

        // Obtenemos las tasas con base USD por defecto
        String respuesta = api.obtenerTasas("USD");
        if (respuesta == null) {
            System.out.println("No se pudo obtener la respuesta de la API.");
            return;
        }

        analizador.analizarRespuesta(respuesta);

        int opcion;
        do {
            System.out.println("\n======================================");
            System.out.println(" Sea bienvenido/a al Conversor de Moneda ");
            System.out.println("======================================");
            System.out.println("1) USD → ARS (Peso argentino)");
            System.out.println("2) USD → BOB (Boliviano)");
            System.out.println("3) USD → BRL (Real brasileño)");
            System.out.println("4) USD → CLP (Peso chileno)");
            System.out.println("5) USD → COP (Peso colombiano)");
            System.out.println("6) Salir");
            System.out.print("Elija una opción válida: ");
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 5) {
                System.out.print("Ingrese el monto en USD: ");
                double monto = scanner.nextDouble();
                String monedaDestino = "";

                switch (opcion) {
                    case 1: monedaDestino = "ARS"; break;
                    case 2: monedaDestino = "BOB"; break;
                    case 3: monedaDestino = "BRL"; break;
                    case 4: monedaDestino = "CLP"; break;
                    case 5: monedaDestino = "COP"; break;
                }

                try {
                    double convertido = analizador.convertir(monedaDestino, monto);
                    System.out.println("\nResultado: " + monto + " USD = " + convertido + " " + monedaDestino);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (opcion != 6) {
                System.out.println("⚠️ Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 6);

        System.out.println("Gracias por usar el Conversor de Moneda. ¡Hasta pronto!");
        scanner.close();
    }
}



