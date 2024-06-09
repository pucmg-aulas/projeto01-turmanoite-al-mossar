/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author Guilh
 */
public class Mesas extends AbstractDAO implements Serializable {
    
    private List<Mesa> mesas;
    
    private static Mesas instance;
    
    private final String LocalArquivo = "./src/main/java/data/Mesas.txt";

    private Mesas(){
        this.mesas = new ArrayList<>();
        carregaMesas();
     }
    
    public static Mesas getInstance(){
        if(instance == null){
            instance = new Mesas();
        }
      return instance;  
    }
    
    public void addMesa (Mesa mesa){
        this.mesas.add(mesa);
        gravar();
    }
    
    private void carregaMesas(){
        this.mesas = super.leitura(LocalArquivo);
    }
    
    private void gravar(){
        super.gravar(LocalArquivo, mesas);
    }

    public List<Mesa> getMesas() {
        return mesas;
    }
    
    public void excluirMessa (Mesa mesa){
        mesas.remove(mesa);
        gravar();
    }
    
    public Mesa buscarMesaPorId(int id){
        for (Mesa mesa : mesas){
            if(mesa.getId() == id){
                return mesa;
            }
        }
        return null;
    }
}
