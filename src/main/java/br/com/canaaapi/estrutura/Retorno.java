package br.com.canaaapi.estrutura;

import java.util.List;


public class Retorno<E> {

	private Long total = 0L;
	private List<E> dados;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<E> getDados() {
		return dados;
	}

	public void setDados(List<E> dados) {
		this.dados = dados;
	}

	@Override
	public String toString() {
		return "Retorno [total=" + total + ", dados=" + dados + "]";
	}
}