package com.fatec.scel.gui;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormCadastrarLivro {
	private WebDriver driver;
	private String baseUrl;
	
	public FormCadastrarLivro (WebDriver driver){
		this.driver = driver;
		baseUrl = "http://localhost:8080/";
	}
	
	public void abre(){
		driver.get(baseUrl + "ControleDeEmprestimo/FormLivro.jsp");
	}
	public void cadastra(String i, String t, String a, String re){
		try{
			driver.get("http://localhost:8090/livros/cadastrar");
		    
		    driver.findElement(By.id("isbn")).click();
		    driver.findElement(By.id("isbn")).sendKeys("1112");
		    driver.findElement(By.id("autor")).sendKeys("Delamaro");
		    driver.findElement(By.id("titulo")).sendKeys("Teste de Software");
		    driver.findElement(By.cssSelector(".btn-primary:nth-child(1)")).click();
		//    assertThat(driver.findElement(By.cssSelector("strong")).getText(), is("Livro cadastrado com sucesso"));
		driver.findElement(By.name("txtISBN")).clear();
		driver.findElement(By.id("txtisbn")).sendKeys(i);
		driver.findElement(By.id("txttitulo")).clear();
		driver.findElement(By.id("txttitulo")).sendKeys(t);
		driver.findElement(By.id("txtautor")).clear();
		driver.findElement(By.id("txtautor")).sendKeys(a);
		
		driver.findElement(By.name("btnIncluir")).click();
		}catch(Exception e){
			System.out.println("erro = " + e.getMessage());
		}
		
	}
	public void excluir(String isbn){
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.get(baseUrl + "ControleDeEmprestimo/FormLivro.jsp");
		driver.findElement(By.id("txtisbn")).clear();
		driver.findElement(By.id("txtisbn")).sendKeys(isbn);
		driver.findElement(By.name("btnExcluir")).click();
	}
	public void consultar(String isbn){
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		
		driver.findElement(By.id("txtisbn")).clear();
		driver.findElement(By.id("txtisbn")).sendKeys(isbn);
		driver.findElement(By.name("btnConsultar")).click();
	}
}