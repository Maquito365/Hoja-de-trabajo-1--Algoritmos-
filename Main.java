import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        RadioKM miRadio = new RadioKM();
        int opcion = 0;
        boolean radioActiva = true;

        while(radioActiva){
        System.out.println("Menú de la Radio KM");
        System.out.println("1. Encender Radio");
        System.out.println("2. Cambiar a FM");
        System.out.println("3. Cambiar a AM");
        System.out.println("4. Avanzar Estación");
        System.out.println("5. Guardar Estación");
        System.out.println("6. Cargar Estación");
        System.out.println("7. Apagar Radio");
        System.out.println("Seleccione una opción: ");
        try {
            opcion = scanner.nextInt();

            // Validar opción válida
            if (opcion < 1 || opcion > 7) {
                System.out.println("Opción no válida. Ingrese un número entre 1 y 7.");
                continue;
            }

            //Se valida si la radio está encendida 
            if ((opcion != 1 && opcion != 7) && !miRadio.isEncendida()) {
                System.out.println("Debe encender la radio primero.");
                continue;
            }

            switch(opcion){
                case 1:
                    miRadio.encenderRadio();
                    System.out.println("Radio encendida.");
                    break;
                case 2:
                    miRadio.cambiarFM();
                    System.out.printf("\nCambiado a FM. Estación actual: %.1f%n", miRadio.getEstacionActual());
                    break;
                case 3: 
                    miRadio.cambiarAM();
                    System.out.printf("\nCambiado a AM. Estación actual: %.1f%n", miRadio.getEstacionActual());
                    break;
                case 4:
                    miRadio.avanzarEstacion();
                    System.out.printf("Estación actual: %.1f%n", miRadio.getEstacionActual());
                    break;
                case 5: 
                    System.out.println("Ingrese el número del botón (1-12) para guardar la estación:");
                    int botonGuardar = scanner.nextInt(); 
                    // Valida que el número del botón esté entre 1 y 12 y el usuario pueda guardar la estación
                    if (botonGuardar >= 1 && botonGuardar <= 12) {
                        miRadio.guardarEstacion(botonGuardar);
                        System.out.println("Estación guardada en el botón " + botonGuardar);
                    } else {
                        System.out.println("El número del botón debe estar entre 1 y 12.");
                    }
                    break;
                case 6: 
                    // Valida que el usuario pueda cargar la estación4
                    System.out.println("Ingrese el número de botón (1-12) de la estación que desea cargar:");
                    int botonCargar = scanner.nextInt();
                    if (botonCargar >= 1 && botonCargar <= 12) {
                        miRadio.cargarEstacion(botonCargar);
                        System.out.printf("Estación cargada: %.1f%n", miRadio.getEstacionActual()); //%.1f%n dice que es un float con un decimal
                    } else {
                        System.out.println("El número del botón debe estar entre 1 y 12.");
                    }
                    break;
                case 7:
                    miRadio.apagarRadio();
                    System.out.println("Radio apagada.");
                    radioActiva = false;
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("Ingrese un número válido.");
            scanner.nextLine(); 
        }
    }
    scanner.close();
    }
}