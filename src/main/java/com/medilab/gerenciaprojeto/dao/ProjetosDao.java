/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medilab.gerenciaprojeto.dao;

import com.google.gson.Gson;
import com.medilab.gerenciaprojeto.conexao.ConexaoBd;
import com.medilab.gerenciaprojeto.modelos.CheckList;
import com.medilab.gerenciaprojeto.modelos.Projetos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author medilab
 */
public class ProjetosDao {
    
    private ConexaoBd bd = new ConexaoBd();
    
    public JSONArray selectFromDb() {
        // List<Projetos> resposta = new ArrayList<Projetos>();
        JSONArray jsonResposta = new JSONArray();
        try {
            String SQL = "SELECT * FROM PROJETOS";
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Projetos a = new Projetos(rs.getInt("CODIGO"), rs.getString("NOME"), rs.getDate("DT_INICIO"),
                        rs.getDate("DT_CONCLUSAO"), rs.getString("TIPO"));
                Gson g = new Gson();
                JSONObject json = new JSONObject(g.toJson(a));
                jsonResposta.put(json);
            }
            return jsonResposta;
        } catch (Exception e) {
            return jsonResposta;
        }
    }
    
    public JSONObject selectFromDbById(int codigo) {
        Projetos resposta = null;
        JSONObject jsonResposta = null;
        try {
            String SQL = "SELECT * FROM PROJETOS WHERE CODIGO = " + codigo;
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                resposta = new Projetos(rs.getInt("CODIGO"), rs.getString("NOME"), rs.getDate("DT_INICIO"),
                        rs.getDate("DT_CONCLUSAO"), rs.getString("TIPO"));
                jsonResposta = new JSONObject(new Gson().toJson(resposta));
            }
            
            return jsonResposta;
        } catch (Exception e) {
            return jsonResposta;
        }
    }
    
    public JSONObject selectChecklistFromDbById(int codigo) {
        CheckList resposta = null;
        JSONObject jsonResposta = null;
        try {
            String SQL = "SELECT * FROM PROJETOS  WHERE CODIGO = " + codigo;
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                resposta = new CheckList(rs.getInt("CODIGO"), rs.getString("FL_CAPTCHA"),
                        rs.getString("FL_HTTP"), rs.getString("FL_HTTPS"), rs.getString("FL_SEGURANCA"),
                        rs.getString("FL_HTML"), rs.getString("FL_JAVA"), rs.getString("FL_REST"),
                        rs.getString("FL_TOMCAT"), rs.getString("FL_VUE"), rs.getString("FL_SERVLET"),
                        rs.getString("FL_PYTHON"));
                jsonResposta = new JSONObject(new Gson().toJson(resposta));
            }
            
            return jsonResposta;
        } catch (Exception e) {
            return jsonResposta;
        }
    }
    
    public String insertBd(Projetos p) {
        String resposta = null;
        try {
            String SQL = "INSERT INTO PROJETOS(CODIGO,NOME,DT_INICIO,DT_CONCLUSAO,TIPO) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getNome().toUpperCase());
            stmt.setDate(3, p.getDt_Inicio());
            stmt.setDate(4, p.getDt_Conclusao());
            stmt.setString(5, p.getTipo());
            
            stmt.execute();
            
            return "FOI";
        } catch (Exception e) {
            return resposta;
        }
    }
    
    public String alterFromDbById(Projetos p) {
        String resposta = null;
        try {
            String SQL = "UPDATE PROJETOS SET " + "NOME = ? , " + "DT_INICIO = ?," + "DT_CONCLUSAO = ? , "
                    + "TIPO = ? " + "WHERE CODIGO = ?";
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            stmt.setString(1, p.getNome().toUpperCase());
            stmt.setDate(2, p.getDt_Inicio());
            stmt.setDate(3, p.getDt_Conclusao());
            stmt.setString(4, p.getTipo());
            stmt.setInt(5, p.getCodigo());
            
            stmt.execute();
            return resposta;
        } catch (Exception e) {
            return resposta;
        }
    }
    
    public String alterCheckListFromDbById(CheckList p) {
        String resposta = null;
        try {
            String SQL = "UPDATE PROJETOS SET "
                    + "FL_CAPTCHA = ? ," + "FL_HTTPS = ? ," + "FL_SEGURANCA = ? ," + "FL_HTML = ? ,"
                    + "FL_JAVA = ? ," + "FL_REST = ? ," + "FL_TOMCAT = ? ," + "FL_VUE = ? ," + "FL_SERVLET = ? ,"
                    + "FL_PYTHON = ? ," + "FL_HTTP = ? " + "WHERE CODIGO = ?";
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            stmt.setString(1, p.getFl_captcha());
            stmt.setString(2, p.getFl_https());
            stmt.setString(3, p.getFl_seguranca());
            stmt.setString(4, p.getFl_html());
            stmt.setString(5, p.getFl_java());
            stmt.setString(6, p.getFl_rest());
            stmt.setString(7, p.getFl_tomcat());
            stmt.setString(8, p.getFl_vue());
            stmt.setString(9, p.getFl_servlet());
            stmt.setString(10, p.getFl_python());
            stmt.setString(11, p.getFl_http());
            stmt.setInt(12, p.getCodigo());
            
            stmt.execute();
            return "foi";
        } catch (Exception e) {
            return resposta;
        }
    }
    
    public String deleteFromDbById(int codigo) {
        String resposta = null;
        try {
            String SQL = "DELETE FROM PROJETOS WHERE CODIGO = " + codigo;
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            stmt.execute();
            
            return "foi";
        } catch (Exception e) {
            return resposta;
        }
    }
    
}
