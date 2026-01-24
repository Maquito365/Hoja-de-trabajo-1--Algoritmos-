import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.*;
public class test {

    /**
     * Prueba 1:
     * Verifica el avance de estaciones y el comportamiento de reinicio
     * al superar el límite de frecuencia, tanto en FM como en AM.
     */
    @Test
    public void testAvanzarEstacionWraparound() {
        RadioKM radio = new RadioKM();
        radio.encenderRadio();

        // Prueba en FM (87.9 – 107.9, incremento 0.2)
        // Se asume que la radio inicia en 87.9 FM
        assertEquals(87.9, radio.getEstacionActual(), 0.01, "Debe iniciar en 87.9 FM");

        // Avanzar una estación
        radio.avanzarEstacion();
        assertEquals(88.1, radio.getEstacionActual(), 0.01, "Debe avanzar a 88.1");

        // Avanzar repetidamente hasta alcanzar el límite superior de FM
        int pasos = 0;
        while (radio.getEstacionActual() < 107.9 && pasos < 200) {
            radio.avanzarEstacion();
            pasos++;
        }
        assertEquals(107.9, radio.getEstacionActual(), 0.01, "Debe llegar a 107.9");

        // Al superar el límite, debe reiniciarse a la frecuencia inicial
        radio.avanzarEstacion();
        assertEquals(87.9, radio.getEstacionActual(), 0.01, "Debe volver a 87.9 tras pasar el límite FM");

        // Prueba en AM (530 – 1610)
        radio.cambiarAM();
        radio.avanzarEstacion(); // 540

        pasos = 0;
        while (radio.getEstacionActual() < 1610 && pasos < 200) {
            radio.avanzarEstacion();
            pasos++;
        }
        assertEquals(1610, radio.getEstacionActual(), 0.01, "Debe llegar a 1610");

        // Al superar el límite AM, debe volver a 530
        radio.avanzarEstacion();
        assertEquals(530, radio.getEstacionActual(), 0.01, "Debe volver a 530 tras pasar el límite AM");
    }

    /**
     * Prueba 2:
     * Comprueba que las estaciones se puedan guardar y cargar correctamente
     * utilizando los botones de memoria.
     */
    @Test
    public void testGuardarCargarEstacion() {
        RadioKM radio = new RadioKM();
        radio.encenderRadio();

        // Avanzar en FM y guardar estación
        radio.avanzarEstacion(); // 88.1
        double expectedFM = 88.1;

        radio.guardarEstacion(1);

        // Cambiar estación para asegurar que se recupere desde memoria
        radio.avanzarEstacion(); // 88.3
        assertNotEquals(expectedFM, radio.getEstacionActual(), 0.01);

        // Cargar estación guardada
        radio.cargarEstacion(1);
        assertEquals(expectedFM, radio.getEstacionActual(), 0.01,
                "Debe recuperar 88.1 del botón 1");

        // Verificar guardado independiente en AM
        radio.cambiarAM();
        radio.avanzarEstacion(); // 540
        double expectedAM = 540;

        radio.guardarEstacion(1);
        radio.avanzarEstacion(); // 550
        radio.cargarEstacion(1);

        assertEquals(expectedAM, radio.getEstacionActual(), 0.01,
                "Debe recuperar 540 del botón 1 en AM");

        // Verificar que FM mantiene su valor original
        radio.cambiarFM();
        radio.cargarEstacion(1);
        assertEquals(expectedFM, radio.getEstacionActual(), 0.01,
                "El preset FM debe mantenerse independiente del AM");
    }

    /**
     * Prueba 3:
     * Verifica que la radio no responda a acciones
     * cuando se encuentra apagada.
     */
    @Test
    public void testRadioApagado() {
        RadioKM radio = new RadioKM();

        double initialStation = radio.getEstacionActual();

        // No debe avanzar si está apagada
        radio.avanzarEstacion();
        assertEquals(initialStation, radio.getEstacionActual(),
                "No debe avanzar si está apagado");

        // No debe cambiar de banda si está apagada
        radio.cambiarAM();
        assertTrue(radio.isFM(),
                "No debe cambiar de banda si está apagado");

        // Al encender, debe responder normalmente
        radio.encenderRadio();
        radio.avanzarEstacion();
        assertNotEquals(initialStation, radio.getEstacionActual(),
                "Debe avanzar si está encendido");
    }
}
