package br.com.canaaapi.estrutura;

public class Ordenacao {

	private String propriedade;
	private String direcao;

	public String getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}

	public String getDirecao() {
		return direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}

	@Override
	public String toString() {
		return "Ordenacao [propriedade=" + propriedade + ", direcao=" + direcao + "]";
	}
}