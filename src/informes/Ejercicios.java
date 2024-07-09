package informes;

public class Ejercicios {

    // CLASE PARA DESARROLLAR FUNCIONALIDADES ESPECÍFICAS

    public static void main(String[] args) {

        System.out.println("\n***********************************");
        System.out.println("REDONDEAR CON CLASE MATH()");
        System.out.println(roundIntValue1(124.4f));
        System.out.println(roundIntValue1(124.5f));
        System.out.println("***********************************");
        System.out.println(roundFloatValue2(243.4f));

    }

    private static int roundIntValue1(float num) {
        return Math.round(num);
    }

    private static int roundFloatValue2(float num) {
        // redondeamos el decimal y volvemos el puntero
        float decimalValue = roundFloatValue2Helper1(num * 10) / 10;
        // redondeamos el último número
        return (int) roundFloatValue2Helper1(decimalValue);
    }

    private static float roundFloatValue2Helper1(float num) {
        String sNum = Float.toString(num); // 2434.0 => a String
        sNum = sNum.substring(0, sNum.indexOf('.')); // "2434.0" => "2434"
        int finalNum = Integer.parseInt(sNum.substring(sNum.length()-1)); // "2434" => 4
        if(finalNum == 0) {
            return num;
        }
        return (finalNum >= 5) ? num + ((finalNum - 10) * -1) : num - finalNum;
    }



}
