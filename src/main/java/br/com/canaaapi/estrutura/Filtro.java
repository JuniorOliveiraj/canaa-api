package br.com.canaaapi.estrutura;




public class Filtro {

	private String operador;
	private String propriedade;
	private Object valor;

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Filtro [operador=" + operador + ", propriedade=" + propriedade + ", valor=" + valor + "]";
	}
}