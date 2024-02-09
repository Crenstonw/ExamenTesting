package com.salesianostriana.dam.testing.examen;

import com.salesianostriana.dam.testing.examen.model.DatoMeteorologico;
import com.salesianostriana.dam.testing.examen.model.DatoMeterologicoPK;
import com.salesianostriana.dam.testing.examen.repo.DatoMeteorologicoRepository;
import com.salesianostriana.dam.testing.examen.service.ServicioMeteorologico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ServiceTestTemplate {

    @InjectMocks
    ServicioMeteorologico servicio;

    @Mock
    DatoMeteorologicoRepository repository;

    @Test
    void mediaDiaSemana_whenResultIsEmpty() {
        DatoMeteorologico d1 = new DatoMeteorologico(
                new DatoMeterologicoPK("sevilla", LocalDate.now()),
                10);

        DatoMeteorologico d2 = new DatoMeteorologico(
                new DatoMeterologicoPK("sevilla", LocalDate.of(2024, 2, 8)),
                2);
        DatoMeteorologico d3 = new DatoMeteorologico(
                new DatoMeterologicoPK("sevilla", LocalDate.of(2024, 2, 7)),
                3);
        repository.saveAll(Set.of(d1, d2, d3));

        Mockito.when(repository.saveAll(Set.of(d1, d2, d3))).thenReturn(repository.buscarPorPoblacion("sevilla"));

        Map<String, Double> resultado = servicio.mediaDiaSemana("sevilla");

        Map<String, Double> expectedResult = new HashMap<>();

        System.out.println(resultado);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(resultado, expectedResult);
    }
}
