/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medilab.gerenciaprojeto.modelos;

/**
 *
 * @author medilab
 */
public class Vulnerabilidade {
    
    int codigo;
    String nome;
    String fl_criticidade;
    int vl_parametros;
    String nm_parametros_afetados;
    String nm_url_afetadas;
    String img;
    String descricao;
    String referencias;
    String remediacao;
    int id_projeto;
    
    public Vulnerabilidade(){
        
    }

    public Vulnerabilidade(int codigo, String nome, String fl_criticidade, int vl_parametros, String nm_parametros_afetados, String nm_url_afetadas, String img, String descricao, String referencias, String remediacao,int id_projeto) {
        this.codigo = codigo;
        this.nome = nome;
        this.fl_criticidade = fl_criticidade;
        this.vl_parametros = vl_parametros;
        this.nm_parametros_afetados = nm_parametros_afetados;
        this.nm_url_afetadas = nm_url_afetadas;
        this.img = img;
        this.descricao = descricao;
        this.referencias = referencias;
        this.remediacao = remediacao;
        this.id_projeto = id_projeto;
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

    public String getFl_criticidade() {
        return fl_criticidade;
    }

    public void setFl_criticidade(String fl_criticidade) {
        this.fl_criticidade = fl_criticidade;
    }

    public int getVl_parametros() {
        return vl_parametros;
    }

    public void setVl_parametros(int vl_parametros) {
        this.vl_parametros = vl_parametros;
    }

    public String getNm_parametros_afetados() {
        return nm_parametros_afetados;
    }

    public void setNm_parametros_afetados(String nm_parametros_afetados) {
        this.nm_parametros_afetados = nm_parametros_afetados;
    }

    public String getNm_url_afetadas() {
        return nm_url_afetadas;
    }

    public void setNm_url_afetadas(String nm_url_afetadas) {
        this.nm_url_afetadas = nm_url_afetadas;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public String getRemediacao() {
        return remediacao;
    }

    public void setRemediacao(String remediacao) {
        this.remediacao = remediacao;
    }

    public int getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(int id_projeto) {
        this.id_projeto = id_projeto;
    }
    
    
}
