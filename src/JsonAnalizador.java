import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonAnalizador {

    private JsonObject conversiones;
    private String base;

    public void analizarRespuesta(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        base = jsonObject.get("base_code").getAsString();
        String resultado = jsonObject.get("result").getAsString();

        System.out.println("Resultado: " + resultado);
        System.out.println("Moneda base: " + base);

        conversiones = jsonObject.getAsJsonObject("conversion_rates");

        System.out.println("Tasas disponibles:");
        System.out.println("1 " + base + " = " + conversiones.get("ARS").getAsDouble() + " ARS");
        System.out.println("1 " + base + " = " + conversiones.get("COP").getAsDouble() + " COP");
        System.out.println("1 " + base + " = " + conversiones.get("BRL").getAsDouble() + " BRL");
        System.out.println("1 " + base + " = " + conversiones.get("CLP").getAsDouble() + " CLP");
        System.out.println("1 " + base + " = " + conversiones.get("BOB").getAsDouble() + " BOB");
    }

    public double convertir(String codigoMoneda, double monto) {
        if (conversiones != null && conversiones.has(codigoMoneda)) {
            double tasa = conversiones.get(codigoMoneda).getAsDouble();
            return monto * tasa;
        } else {
            throw new IllegalArgumentException("La moneda " + codigoMoneda + " no está disponible.");
        }
    }
}
