package br.com.avaliacao.acceptance;

import br.com.avaliacao.e2e.pages.Browser;
import br.com.avaliacao.e2e.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class avaliacaoSteps{

	private Browser browser;
	private HomePage homePage;
	
	@After
	public void tearDown() {
		browser.clean();
	}
	
	@Given("que estou na home")
	public void que_estou_na_home() {
		browser = new Browser();
		homePage = browser.getHomePage();
		homePage.acessarSite();
	}

	@When("eu clicar no menu Guide")
	public void eu_clicar_no_menu_guide() {
		homePage.clicarMenuGuide();
	}

	@When("clicar no menu fotos")
	public void clicar_no_menu_fotos() {
		homePage.clicarBtnFotos("/albums/1/photos");
	}

	@Then("deverá validar o id {int} com o titulo {string} , url {string} e thumbnailUrl {string}")
	public void deverá_validar_o_id_com_o_titulo_url_e_thumbnail_url(Integer id, String titulo, String url,String thumbUrl) {
		homePage.obterConteudoPagina();
		homePage.validarJsonComId(id, titulo, url, thumbUrl);
	}

	@Then("salvar em um arquivo json")
	public void salvar_em_um_arquivo_json() {
		homePage.salvarComoJson();
	}

}
