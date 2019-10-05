/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medilab.gerenciaprojeto.modelos;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author medilab
 */
public class Projetos {

    int codigo;
    String nome;
    Date dt_inicio;
    Date dt_conclusao;
    String tipo;
    CheckList checklist;

    public Projetos(int codigo, String nome, Date dt_Inicio, Date dt_Conlusao, String tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.dt_inicio = dt_Inicio;
        this.dt_conclusao = dt_Conlusao;
        this.tipo = tipo;
    }

    public Projetos(int codigo, String nome, Date dt_inicio, Date dt_conclusao, String tipo, CheckList checklist) {
        this.codigo = codigo;
        this.nome = nome;
        this.dt_inicio = dt_inicio;
        this.dt_conclusao = dt_conclusao;
        this.tipo = tipo;
        this.checklist = checklist;
 
    }

    
    
    public Projetos(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public Date getDt_Inicio() {
        return dt_inicio;
    }

    public void setDt_Inicio(Date dt_Inicio) {
        this.dt_inicio = dt_Inicio;
    }

    public Date getDt_Conclusao() {
        return dt_conclusao;
    }

    public void setDt_Conlusao(Date dt_conclusao) {
        this.dt_conclusao = dt_conclusao;
    }

    public java.sql.Date getDt_InicioSQL(String format) {
        try {
            SimpleDateFormat simple = new SimpleDateFormat(format);
            long timems = simple.parse(this.getDt_Inicio().toString()).getTime();
            java.sql.Date dateSQL = new java.sql.Date(timems);

            return dateSQL;
        } catch (ParseException ex) {
            return null;
        }

    }
    
    public java.sql.Date getDt_ConclusaoSQL(String format) {
        try {
            SimpleDateFormat simple = new SimpleDateFormat(format);
            long timems = simple.parse(this.getDt_Conclusao().toString()).getTime();
            java.sql.Date dateSQL = new java.sql.Date(timems);

            return dateSQL;
        } catch (ParseException ex) {
            return null;
        }

    }


}
