import model.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import service.FiltroService;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"model", "service"})
public class FiltradoApplication implements CommandLineRunner {

    private final FiltroService filtroService;

    @Autowired
    public FiltradoApplication(FiltroService filtroService) {
        this.filtroService = filtroService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FiltradoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("=== SISTEMA DE FILTRADO DE MENSAJES ===");
        System.out.println("Palabras prohibidas: " +
                Arrays.toString(filtroService.getPalabrasProhibidas()));
        System.out.println("Los mensajes con más de 3 palabras prohibidas serán bloqueados.");

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.print("\nEscribe tu mensaje (o 'salir' para terminar): ");
            String input = scanner.nextLine();

            if ("salir".equalsIgnoreCase(input)) {
                continuar = false;
            } else {
                Mensaje mensaje = new Mensaje(input);
                Mensaje mensajeFiltrado = filtroService.filtrar(mensaje);

                System.out.println("\nMensaje filtrado: " + mensajeFiltrado.getTexto());
                System.out.println("Palabras prohibidas encontradas: " +
                        mensajeFiltrado.getContadorProhibidas());
            }
        }

        System.out.println("¡Hasta pronto!");
        scanner.close();
    }
}