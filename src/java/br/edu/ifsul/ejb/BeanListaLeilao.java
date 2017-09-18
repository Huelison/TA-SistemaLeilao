/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.ejb;

import br.edu.ifsul.modelo.Leilao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Huelison
 */
@Singleton
@Startup
public class BeanListaLeilao implements Serializable{
    private Integer contador;
    private List<Leilao> LeiloesAtivos;
    private List<Leilao> LeiloesConcluidos;

    public BeanListaLeilao() {
        contador = 0;
        LeiloesAtivos =  new ArrayList<>();
        LeiloesConcluidos =  new ArrayList<>();
    }

    public void adicionarLeilaoAtivo( Leilao leilao ){
        leilao.setId(++contador);
        LeiloesAtivos.add(leilao);
    }
    
    public void removerLeilaoAtivo(Leilao leilao){
        this.LeiloesAtivos.remove(leilao);
    }
    
    public void adicionarLeilaoConcluido( Leilao leilao ){
        LeiloesConcluidos.add(leilao);
    }
    
    public void removerLeilaoConcluido(Leilao leilao){
        this.LeiloesConcluidos.remove(leilao);
    }
    
    public List<Leilao> getLeiloesAtivos() {
        return LeiloesAtivos;
    }

    public void setLeiloesAtivos(List<Leilao> LeiloesAtivos) {
        this.LeiloesAtivos = LeiloesAtivos;
    }

    public List<Leilao> getLeiloesConcluidos() {
        return LeiloesConcluidos;
    }

    public void setLeiloesConcluidos(List<Leilao> LeiloesConcluidos) {
        this.LeiloesConcluidos = LeiloesConcluidos;
    }
}
