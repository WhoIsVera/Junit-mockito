package com.tutorialesvip.tutorialunittest.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.tutorialesvip.tutorialunittest.models.Country;
import com.tutorialesvip.tutorialunittest.models.CountryResponse;
import com.tutorialesvip.tutorialunittest.repositories.CountryRepository;
import com.tutorialesvip.tutorialunittest.util.DiferenciaEntreFechas;

class TestIndependency {
	
	//objetos que necesitamos que tiene los diferentes componentes
	@Autowired
	//aqui esta la respuesta
	CountryResponse countryResponse;
	@Autowired
	//aqui esta la bd y los paises
    Optional<Country> country;
	
	//El mockito para hacer la interseccion antes de que entre en la BDD
    CountryRepository countryRepositoryMock = Mockito.mock(CountryRepository.class);
    
    @Autowired
    DiferenciaEntreFechas diferenciaEntreFechas = new DiferenciaEntreFechas();
    
    @Autowired
    IndependencyController independencyController = new IndependencyController(countryRepositoryMock, diferenciaEntreFechas);
	
    
    //antes de cada prueba/test
	@BeforeEach
	void setUp() throws Exception {
		
		//el uso del objeto y el uso de set
		Country mockCountry = new Country();
		mockCountry.setIsoCode("DO");
		mockCountry.setCountryIdependenceDate("23/11/1986");
		mockCountry.setCountryId((long) 1);
		mockCountry.setCountryName("Republica Dominicana");
		mockCountry.setCountryCapital("Santo Domingo");
		
		Mockito.when(countryRepositoryMock.findCountryByIsoCode("DO")).thenReturn(mockCountry);
	}
	
	@Test
	final void testGetCountryDetailsValid() {
		
		ResponseEntity<CountryResponse> respuestaServicio;
		
		respuestaServicio = independencyController.getCountryDetails("DO");
		Assertions.assertEquals("Republica Dominicana", respuestaServicio.getBody().getCountryName());
		
	}
	
	@Test
	final void testGetCountryDetailsInvalid() {
		
		ResponseEntity<CountryResponse> respuestaServicio;
		
		respuestaServicio = independencyController.getCountryDetails("IT");
		Assertions.assertNull(null, respuestaServicio.getBody().getCountryName());
		
	}
	

}
