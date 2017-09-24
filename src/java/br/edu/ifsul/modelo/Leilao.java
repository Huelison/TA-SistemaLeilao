/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author Huelison
 */
public class Leilao implements Serializable{
    
    private Integer id;
    private String nome;
    private String telefone;
    private String descricao;
    private Double valorMinimo;
    private Double valorVendaAutomatica;
    private Calendar dataValidade;
    private Double maiorLance;
    private String nomeLance;
    private String telefoneLance;

    public Leilao() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Leilao other = (Leilao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public Double getValorVendaAutomatica() {
        return valorVendaAutomatica;
    }

    public void setValorVendaAutomatica(Double valorVendaAutomatica) {
        this.valorVendaAutomatica = valorVendaAutomatica;
    }

    public Calendar getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Calendar dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Double getMaiorLance() {
        return maiorLance;
    }

    public void setMaiorLance(Double maiorLance) {
        this.maiorLance = maiorLance;
    }

    public String getNomeLance() {
        return nomeLance;
    }

    public void setNomeLance(String nomeLance) {
        this.nomeLance = nomeLance;
    }

    public String getTelefoneLance() {
        return telefoneLance;
    }

    public void setTelefoneLance(String telefoneLance) {
        this.telefoneLance = telefoneLance;
    }
}
