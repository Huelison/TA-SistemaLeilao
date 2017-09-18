/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.ejb.BeanListaLeilao;
import br.edu.ifsul.modelo.Leilao;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Huelison
 */

@ManagedBean(name = "controleLeilao")
@ViewScoped
public class ControleLeilao implements Serializable{
    
    private Leilao objeto;
    private Boolean editando;
    private Boolean Ativos;
    private Double valorMinimo;
    @EJB
    private BeanListaLeilao beanListaUsuario;

    public ControleLeilao() {
        objeto = new Leilao();
        editando = false;
        Ativos = true;
        objeto.setId(1);
        objeto.setNome("Joao das Nevis");
        objeto.setTelefone("54 999956091");
        objeto.setDescricao("Celular nokia 5000");
        objeto.setValorMinimo(150.00);
        objeto.setValorVendaAutomatica(500.00);
        objeto.setDataValidade(Calendar.getInstance());
        objeto.setMaiorLance(450.00);
        objeto.setNomeLance("Daiane do Fogo");
        objeto.setTelefoneLance("54 99956091");
    }
   
    public String novoLeilao(){
        objeto = new Leilao();
        editando = true;
        System.out.println(editando);
        return "index.xhtml/?faces-redirect=true";
    }

    public String salvarLeilao(){
        objeto.setMaiorLance(0.0);
        beanListaUsuario.adicionarLeilaoAtivo(objeto);
        editando = false;
        return "index.xhtml/?faces-redirect=true";
    }
    
    public String cancelarLeilao(){
        editando = false;
        return "index.xhtml/?faces-redirect=true";
    }
    
    public void realizarLance(Leilao obj){
        if(obj.getMaiorLance()>0){
            valorMinimo = obj.getMaiorLance();
        }else{
            valorMinimo = obj.getValorMinimo();
        }
        objeto = obj;
    }
    
    public String salvarLance(){
        if(objeto.getValorVendaAutomatica()<=objeto.getMaiorLance()){
            beanListaUsuario.removerLeilaoAtivo(objeto);
            beanListaUsuario.adicionarLeilaoConcluido(objeto);
        }
        return "index.xhtml/?faces-redirect=true";
    }
    
    public List<Leilao> getListaLeilao(){
        if(Ativos){ 
            return beanListaUsuario.getLeiloesAtivos();
        }else{
            return beanListaUsuario.getLeiloesConcluidos();
        }
    }
    
    public String alterarLista(Boolean ativos){
        Ativos = ativos;
        return Listar();
    }
    
    public String Listar(){
        editando = false;
        return "index.xhtml/?faces-redirect=true";
    }
    
    public Leilao getObjeto() {
        return objeto;
    }

    public void setObjeto(Leilao objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public BeanListaLeilao getBeanListaUsuario() {
        return beanListaUsuario;
    }

    public void setBeanListaUsuario(BeanListaLeilao beanListaUsuario) {
        this.beanListaUsuario = beanListaUsuario;
    }

    public Boolean getAtivos() {
        return Ativos;
    }

    public void setAtivos(Boolean Ativos) {
        this.Ativos = Ativos;
    }

    public Double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }
    
}

