/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.ejb;

import br.edu.ifsul.modelo.Leilao;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Schedule;
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
    
    @Schedule(minute = "*/1",hour = "*/1", second = "2")
    public void removerLeiloesVencidos(){
        System.out.println("Vai Atualizar Lista ..... " +
            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(Calendar.getInstance().getTime()));
        for (Leilao LeilaoAtivo : LeiloesAtivos) {
               System.out.println("Passou no Laço");
           if( Calendar.getInstance().after(LeilaoAtivo.getDataValidade())){
               System.out.println("Passou no teste de validade");
               if(LeilaoAtivo.getMaiorLance()>0){
                   this.adicionarLeilaoConcluido(LeilaoAtivo);
               }
               this.removerLeilaoAtivo(LeilaoAtivo);
           } 
        }
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
