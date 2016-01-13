package com.nink.servico.sax.cliente;

public enum ClientesTagSax {

	TAG_CLASS{
		
		@Override
		public String getTag() {
			return "Clientes";
		}
	},
	
	CLIENTE {
		@Override
		public String getTag() {
			return "cliente";
		}
	},
	NOME {
		@Override
		public String getTag() {
			return "nome";
		}
	},
	IDADE {
		@Override
		public String getTag() {
			return "idade";
		}
	},
	PROFISSAO {
		@Override
		public String getTag() {
			return "profissao";
		}
	},
	SEXO {
		@Override
		public String getTag() {
			return "sexo";
		}
	};

	public abstract String getTag();
}
