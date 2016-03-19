package br.com.square.mb;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TemaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String tema = "afternoon"; // Tema padrão.

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	// temas disponíveis dentro do JAR
	public List<String> getTemas() {
		return Arrays.asList("aristo", "afternoon", "black-tie", "blitzer", "bluesky",
				"bootstrap", "casablanca", "cupertino", "dark-hive", "dot-luv", "eggplant",
				"excite-bike", "flick", "glass-x", "hot-sneaks", "humanity",
				"le-frog", "midnight", "mint-choc", "overcast",
				"pepper-grinder", "redmond", "rocket", "sam", "smoothness",
				"south-street", "start", "sunny", "swanky-purse", "trontastic",
				"ui-darkness", "ui-lightness", "vader");
	}

}
