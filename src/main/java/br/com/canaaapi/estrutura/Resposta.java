package br.com.canaaapi.estrutura;


public class Resposta {

	private boolean sucesso;

	private Object retorno;

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}





	public Object getRetorno() {
		return retorno;
	}

	public void setRetorno(Object retorno) {
		this.retorno = retorno;
	}

	public Resposta alterarRetorno(Object retorno) {
		this.setRetorno(retorno);
		return this;
	}

	public static Resposta get(boolean sucesso) {
		Resposta resposta = new Resposta();
		resposta.setSucesso(sucesso);
		return resposta;
	}



	public static Resposta get(boolean sucesso, Object retorno) {
		Resposta resposta = Resposta.get(sucesso);
		resposta.setRetorno(retorno);
		return resposta;
	}



	@Override
	public String toString() {
		return "Resposta [sucesso=" + sucesso +  ", retorno=" + retorno + "]";
	}
}