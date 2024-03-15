package br.com.avaliacao.e2e.pages;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.gson.JsonObject;

import io.cucumber.messages.internal.com.google.gson.Gson;
import io.cucumber.messages.internal.com.google.gson.GsonBuilder;

public class HomePage {

	private WebDriver driver;

	private static String conteudoPagina;

	private static String URL_HOME_PAGE = "https://jsonplaceholder.typicode.com/";

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage acessarSite() {
		driver.get(URL_HOME_PAGE);
		return new HomePage(driver);
	}

	public HomePage clicarMenuGuide() {

		WebElement btnGuide = driver.findElement(By.linkText("Guide"));
		btnGuide.click();

		return new HomePage(driver);
	}

	public HomePage clicarBtnFotos(String texto) {
		WebElement btnFoto = driver.findElement(By.partialLinkText(texto));
		btnFoto.click();

		return new HomePage(driver);
	}

	public HomePage obterConteudoPagina() {
		WebElement preElement = driver.findElement(By.tagName("pre"));
		conteudoPagina = preElement.getText();

		return new HomePage(driver);
	}

	public HomePage salvarComoJson() {
		try (FileWriter fileWriter = new FileWriter("src/test/resources/json/avaliacao.json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("conteudo", conteudoPagina);

			gson.toJson(jsonObject, fileWriter);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new HomePage(driver);
	}

	public void validarJsonComId(Integer id, String titulo, String url, String thumbUrl) {
		try {
			JSONArray jsonArray = new JSONArray(conteudoPagina);

			JSONObject objetoComId = encontrarObjetoComId(jsonArray, id);

			if (objetoComId == null) {
				throw new RuntimeException("Não foi possível encontrar o objeto com id = " + id + ".");
			}

			String tituloEsperado = titulo;
			String urlEsperada = url;
			String thumbnailUrlEsperada = thumbUrl;

			String tituloDoObjeto = objetoComId.getString("title");
			String urlDoObjeto = objetoComId.getString("url");
			String thumbnailUrlDoObjeto = objetoComId.getString("thumbnailUrl");

			Assertions.assertEquals(tituloEsperado, tituloDoObjeto,"O campo title do objeto com id = " + id + " não corresponde ao esperado.");
			Assertions.assertEquals(urlEsperada, urlDoObjeto,"O campo url do objeto com id = " + id + " não corresponde ao esperado.");
			Assertions.assertEquals(thumbnailUrlEsperada, thumbnailUrlDoObjeto,"O campo thumbnailUrl do objeto com id = " + id + " não corresponde ao esperado.");

		} catch (JSONException e) {
			System.err.println("Erro ao processar o JSON: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private JSONObject encontrarObjetoComId(JSONArray jsonArray, int id) {
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject objeto = jsonArray.getJSONObject(i);
				if (objeto.getInt("id") == id) {
					return objeto;
				}
			}
		} catch (JSONException e) {
			System.err.println("Erro ao obter JSONObject do JSONArray: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
