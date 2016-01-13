package com.nink.servico.modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "Clientes")
public class Cliente extends Modelo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String NAME_ENTITY = "Clientes";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank
    @NotEmpty
    private String nome;

    @NotNull
    private Integer idade;

    @NotBlank
    @NotEmpty
    private String profissao;

    @NotBlank
    @NotEmpty
    private String sexo;

    public static String getNameEntity() {
        return NAME_ENTITY;
    }

    protected Long getCodigo() {
        return codigo;
    }

    protected void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {

        StringBuilder st = new StringBuilder();

        // Cliente = {nome:"$",idade:"$",profissão:"$",sexo:"$" }
        st.append("Cliente = {").append("nome: \"").append(this.getNome())
                .append("\",").append("idade: \"").append(this.getIdade().toString()).append("\",")
                .append("profissão: \"").append(this.getProfissao()).append("\",")
                .append("sexo: \"").append(this.getSexo()).append("\"}");

        return st.toString();
    }

}
