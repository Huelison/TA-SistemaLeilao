/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.ejb.BeanListaLeilao;
import br.edu.ifsul.modelo.Leilao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Huelison
 */
@Named(value = "controleLeilao")
@SessionScoped
public class ControleLeilao implements Serializable {

    private Leilao objeto;
    private Boolean editando;
    private Boolean Ativos;
    private Double valorMinimo;
    @EJB
    private BeanListaLeilao beanListaLeilao;

    public ControleLeilao() {
        objeto = new Leilao();
        editando = false;
        Ativos = true;
    }

    public String novoLeilao() {
        objeto = new Leilao();
        editando = true;
        return "index.xhtml/?faces-redirect=true";
    }

    public String salvarLeilao() {
        if (objeto.getValorMinimo() > objeto.getValorVendaAutomatica()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Util.getMensagemInternacionalizada("msg.ErroValorMinimo"), "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            objeto.setMaiorLance(0.0);
            beanListaLeilao.adicionarLeilaoAtivo(objeto);
            editando = false;
            FacesMessage msg = new FacesMessage(Util.getMensagemInternacionalizada("msg.SalvarProduto"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "index.xhtml/?faces-redirect=true";
    }

    public String cancelarLeilao() {
        editando = false;
        return "index.xhtml/?faces-redirect=true";
    }

    public String realizarLance(Leilao obj) {

        if (obj.getMaiorLance() > 0) {
            valorMinimo = obj.getMaiorLance();
        } else {
            valorMinimo = obj.getValorMinimo();
        }
        objeto = obj;
        return "lance.xhtml/?faces-redirect=true";
    }

    public String salvarLance() {
        System.out.println("Passou aqui");
        if (objeto.getMaiorLance() > getValorMinimo()) {
            if (objeto.getValorVendaAutomatica() <= objeto.getMaiorLance()) {
                beanListaLeilao.removerLeilaoAtivo(objeto);
                beanListaLeilao.adicionarLeilaoConcluido(objeto);
                FacesMessage msg = new FacesMessage(Util.getMensagemInternacionalizada("msg.VencerLance"));
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.getExternalContext().getFlash().setKeepMessages(true);
                contexto.addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(Util.getMensagemInternacionalizada("msg.SalvarLance"));
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.getExternalContext().getFlash().setKeepMessages(true);
                contexto.addMessage(null, msg);
            }
            return "index.xhtml?faces-redirect=true";
        } else {
            System.out.println(Util.getMensagemInternacionalizada("msg.ErroLanceMinimo"));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Util.getMensagemInternacionalizada("msg.ErroLanceMinimo"), "");
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.getExternalContext().getFlash().setKeepMessages(true);
            contexto.addMessage(null, msg);
            return "lance.xhtml?faces-redirect=true";
        }
    }

    public List<Leilao> getListaLeilao() {
        if (Ativos) {
            return beanListaLeilao.getLeiloesAtivos();
        } else {
            return beanListaLeilao.getLeiloesConcluidos();
        }
    }

    public String alterarLista(Boolean ativos) {
        Ativos = ativos;
        return Listar();
    }

    public String Listar() {
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

    public BeanListaLeilao getBeanListaLeilao() {
        return beanListaLeilao;
    }

    public void setBeanListaLeilao(BeanListaLeilao beanListaLeilao) {
        this.beanListaLeilao = beanListaLeilao;
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
