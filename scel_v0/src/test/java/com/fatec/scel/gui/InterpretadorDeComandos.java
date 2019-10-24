package com.fatec.scel.gui;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;
/**
 * A planilha.xls do roteiro de teste esta configurada fisicamente na maquina
 * A quantidade de linhas da planilha esta fixada no metodo fluxo()
 * Uma melhoria seria utilizar o TestResult para capturar os resultados do teste
 * Refatorar a conexao com o DB
 * Ajuste no caso de teste
 * @author Hp
 *
 */
public class InterpretadorDeComandos {
	private static WebDriver driver;
	private static String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private static FormCadastrarLivro formCadastrarLivro;
	private static FormConsultarLivro formConsultarLivro;
	
	
	public InterpretadorDeComandos(){
		inicializa();
	}
	public void inicializa(){
		try {
			ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Planilha1");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//System.setProperty("webdriver.gecko.driver", Constant.Firefox");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", Constant.Chrome);
		driver = new ChromeDriver();
		
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public void fluxo() throws Exception {
		String acao;
		
		for (int i = 1; i < 8; i++) {
			// linha x coluna
			System.out.println("percorrendo a linha = " + i); 
			System.out.println("coluna acao = " + ExcelUtils.getCellData(i, 2)); 
			
			Thread.sleep(5000);
			//obtem da coluna C da planilha "roteiro_de_teste.xls" a ação a ser executada.
			acao = ExcelUtils.getCellData(i, 2);
			
			
			
			
			if (acao.equals("cadastrarLivro")){
				formCadastrarLivro = new FormCadastrarLivro(driver);
				System.out.println("interpretador celula = " + ExcelUtils.getCellData(i, 4));
				formCadastrarLivro.cadastra(ExcelUtils.getCellData(i, 4), ExcelUtils.getCellData(i, 5),
						ExcelUtils.getCellData(i, 6), ExcelUtils.getCellData(i, 7));
				
				try {
					WebDriverWait wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
					assertEquals(ExcelUtils.getCellData(1, 7), driver.findElement(By.id("mensagem")).getText());
					driver.quit();
				} catch (Error e) {
					verificationErrors.append(e.toString());
				}
			}
			
			
			if (acao.equals("excluirLivro")){
				FormConsultarLivro formConsultarLivro = new FormConsultarLivro(driver);
				formConsultarLivro.excluir(ExcelUtils.getCellData(i, 4));
				
				
				try {
					WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
					assertEquals(ExcelUtils.getCellData(1, 7), driver.findElement(By.id("mensagem")).getText());
					driver.quit();
				} catch (Error e) {
					verificationErrors.append(e.toString());
				}
			}
			if (acao.equals("consultarLivro")){
				FormConsultarLivro formConsultarLivro = new FormConsultarLivro(driver);
				formConsultarLivro.consultar(ExcelUtils.getCellData(i, 4));
			}
		}
		driver.quit();
	}
	
}