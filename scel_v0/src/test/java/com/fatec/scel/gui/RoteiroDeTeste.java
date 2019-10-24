package com.fatec.scel.gui;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RoteiroDeTeste {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws Exception {
		InterpretadorDeComandos cmd = new InterpretadorDeComandos();
		cmd.fluxo();		
	}

}