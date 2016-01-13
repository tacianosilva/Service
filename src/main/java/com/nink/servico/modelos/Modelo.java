package com.nink.servico.modelos;

public abstract class Modelo {

	/**
	 * Quando o método getNameEntity não é gerado em uma classe do tipo Modelo,
	 * É gerado essa excessão
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getNameEntity() throws Exception {
		throw new Exception(
				"A classe que herda Modelo deve conter um método getNameEntity");
	}
}
