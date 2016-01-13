/**
 * 
 */
package modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author jefferson
 *
 * Essa classe é uma tabela do sistema. Necessário para gerar log em run-time para usuário final. 
 */
@Entity(name = "StatusArquivos")
public class StatusArquivos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank
	@NotEmpty
	private String horarioUltimaAlteracao;

	@NotBlank
	@NotEmpty
	private String nome;

	@NotBlank
	@NotEmpty
	private String tipo;

	private boolean valido;

	private boolean depositadoBancoDeDados;

	private boolean pertenceAUmArquivoCompactado;

	private String nomeDoArquivoCompactado;

	protected Long getCodigo() {
		return codigo;
	}

	protected void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getHorario() {
		return horarioUltimaAlteracao;
	}

	public void setHorario(String horario) {
		this.horarioUltimaAlteracao = horario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public boolean isDepositadoBancoDeDados() {
		return depositadoBancoDeDados;
	}

	public void setDepositadoBancoDeDados(boolean depositadoBancoDeDados) {
		this.depositadoBancoDeDados = depositadoBancoDeDados;
	}

	public boolean isPertenceAumArquivoCompactado() {
		return pertenceAUmArquivoCompactado;
	}

	public void setPertenceAumArquivoCompactado(
			boolean pertenceAumArquivoCompactado) {
		this.pertenceAUmArquivoCompactado = pertenceAumArquivoCompactado;
	}

	public String getNomeDoArquivoCompactado() {
		return nomeDoArquivoCompactado;
	}

	public void setNomeDoArquivoCompactado(String nomeDoArquivoCompactado) {
		this.nomeDoArquivoCompactado = nomeDoArquivoCompactado;
	}

	@Override
	public String toString() {

		StringBuilder st = new StringBuilder();

		st.append("Status arquivo <> \n").append(" Horario da última alteração : ")
				.append(getHorario()).append("\n").append(" Nome do arquivo: ")
				.append(getNome()).append("\n").append(" Tipo do arquivo: ")
				.append(getTipo()).append("\n").append(" Arquivo válido: ")
				.append(String.valueOf(isValido())).append("\n")
				.append(" Arquivo inserido no banco de dados: ")
				.append(String.valueOf(isDepositadoBancoDeDados()))
				.append("\n")
				.append(" Arquivo estava contido em um arquivo Compactado: ")
				.append(String.valueOf(isPertenceAumArquivoCompactado()))
				.append("\n").append(" Nome do arquivo compactado: ")
				.append(getNomeDoArquivoCompactado()).append("\n");

		return st.toString();
	}

}
