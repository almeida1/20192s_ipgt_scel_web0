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
		driver.get(baseUrl + "livros/cadastrar");
	}
	public void cadastra(String isbn, String autor, String titulo, String re){
		try{
			abre();
		    
		    driver.findElement(By.id("isbn")).click();
		    driver.findElement(By.id("isbn")).sendKeys(isbn);
		    driver.findElement(By.id("autor")).sendKeys(autor);
		    driver.findElement(By.id("titulo")).sendKeys(titulo);
		    driver.findElement(By.cssSelector(".btn-primary:nth-child(1)")).click();
		//    assertThat(driver.findElement(By.cssSelector("strong")).getText(), is("Livro cadastrado com sucesso"));
		
		
		driver.findElement(By.name("btnIncluir")).click();
		}catch(Exception e){
			System.out.println("erro = " + e.getMessage());
		}
		
	}
	public void excluir(String isbn){
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.get(baseUrl + "ControleDeEmprestimo/FormLivro.jsp");
		driver.findElement(By.id("isbn")).clear();
		driver.findElement(By.id("isbn")).sendKeys(isbn);
		driver.findElement(By.name("btnExcluir")).click();
	}
	public void consultar(String isbn){
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		
		driver.findElement(By.id("isbn")).clear();
		driver.findElement(By.id("isbn")).sendKeys(isbn);
		driver.findElement(By.name("btnConsultar")).click();
	}
}