package com.tutorialesvip.tutorialunittest.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TestFecha {
	
	@Autowired
	DiferenciaEntreFechas diferenciaEntreFecha;
	

	@Test
	void testCalculateYearsOfIndependency() {
		diferenciaEntreFecha = new DiferenciaEntreFechas();
			LocalDate today = LocalDate.now();
			String  fechaindependencia = "27/02/1894";
			
			Period resultado = diferenciaEntreFecha.calculateYearsOfIndependency(fechaindependencia);
			System.out.println(resultado.getDays());
			System.out.println(resultado.getMonths());
			System.out.println(resultado.getYears());
			
			
			Assertions.assertEquals(23, resultado.getDays());
			Assertions.assertEquals(1, resultado.getMonths());
			Assertions.assertEquals(130, resultado.getYears());
	}

}
