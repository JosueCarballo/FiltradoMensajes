package service;

import model.Mensaje;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FiltroAspect {

    private final FiltroService filtroService;

    @Autowired
    public FiltroAspect(FiltroService filtroService) {
        this.filtroService = filtroService;
    }

    @Around("execution(* service.FiltroService.filtrar(..))")
    public Object filtrarMensaje(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Mensaje mensaje = (Mensaje) args[0];

        System.out.println("Aspecto: Interceptando mensaje para filtrado");

        Mensaje mensajeFiltrado = filtroService.procesarMensaje(mensaje);

        System.out.println("Aspecto: Se encontraron " + mensajeFiltrado.getContadorProhibidas() +
                " palabras prohibidas");

        return mensajeFiltrado;
    }
}