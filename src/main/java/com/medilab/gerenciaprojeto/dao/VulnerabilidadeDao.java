/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medilab.gerenciaprojeto.dao;

import com.google.gson.Gson;
import com.medilab.gerenciaprojeto.conexao.ConexaoBd;
import com.medilab.gerenciaprojeto.modelos.Vulnerabilidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author medilab
 */
public class VulnerabilidadeDao {

    private ConexaoBd bd = new ConexaoBd();

    public JSONArray selectFromDB() {
        //List<Vulnerabilidade> resposta = new ArrayList<Vulnerabilidade>();
        JSONArray jsonResposta = new JSONArray();
        try {
            String SQL = "SELECT * FROM Vulnerabilidade";
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vulnerabilidade a = new Vulnerabilidade(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME"),
                        rs.getString("FL_CRITICIDADE"),
                        rs.getInt("VL_PARAMETROS"),
                        rs.getString("NM_PARAMETROS_AFETADOS"),
                        rs.getString("NM_URL_AFETADAS"),
                        rs.getString("IMG"),
                        rs.getString("DESCRICAO"),
                        rs.getString("REFERENCIAS"),
                        rs.getString("REMEDIACAO"),
                        rs.getInt("ID_PROJETO")
                        
                );
                jsonResposta.put(new JSONObject(new Gson().toJson(a)));
            }

            return jsonResposta;
        } catch (Exception e) {
            return jsonResposta;
        }

    }

    public JSONObject selectFromDbById(int valor) {
        Vulnerabilidade resposta = null;
        JSONObject jsonResposta = null;

        try {
            String SQL = "SELECT * FROM Vulnerabilidade WHERE CODIGO = " + valor;
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resposta = new Vulnerabilidade(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME"),
                        rs.getString("FL_CRITICIDADE"),
                        rs.getInt("VL_PARAMETROS"),
                        rs.getString("NM_PARAMETROS_AFETADOS"),
                        rs.getString("NM_URL_AFETADAS"),
                        rs.getString("IMG"),
                        rs.getString("DESCRICAO"),
                        rs.getString("REFERENCIAS"),
                        rs.getString("REMEDIACAO"),
                        rs.getInt("ID_PROJETO")
                );
                jsonResposta = new JSONObject(new Gson().toJson(resposta));

            }

            return jsonResposta;
        } catch (Exception e) {
            return jsonResposta;
        }
    }

    public JSONArray selectFromDbByIdProjetos(int valor) {
        JSONArray a = new JSONArray();
        Vulnerabilidade resposta = null;

        try {
            String SQL
                    = "select v.* from vulnerabilidade v\n"
                    + "where\n"
                    + "v.id_projeto = " + valor ;
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resposta = new Vulnerabilidade(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME"),
                        rs.getString("FL_CRITICIDADE"),
                        rs.getInt("VL_PARAMETROS"),
                        rs.getString("NM_PARAMETROS_AFETADOS"),
                        rs.getString("NM_URL_AFETADAS"),
                        rs.getString("IMG"),
                        rs.getString("DESCRICAO"),
                        rs.getString("REFERENCIAS"),
                        rs.getString("REMEDIACAO"),
                        rs.getInt("ID_PROJETO")
                );

                Gson g = new Gson();
                JSONObject j = new JSONObject(g.toJson(resposta));
                a.put(j);
            }

            return a;
        } catch (Exception e) {
            return a;
        }
    }

    public String insertDB(Vulnerabilidade v) {
        String resposta = null;
        try {
            String SQL = "INSERT INTO  VULNERABILIDADE(CODIGO,NOME,FL_CRITICIDADE,VL_PARAMETROS,NM_PARAMETROS_AFETADOS,NM_URL_AFETADAS,IMG,DESCRICAO,REFERENCIAS,REMEDIACAO,ID_PROJETO) "
                    + "VALUES((SELECT GEN_ID( GEN_VULNERABILIDADE_ID, 1 ) FROM RDB$DATABASE),?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            stmt.setString(1, v.getNome().toUpperCase());
            stmt.setString(2, v.getFl_criticidade());
            stmt.setInt(3, v.getVl_parametros());
            stmt.setString(4, v.getNm_parametros_afetados());
            stmt.setString(5, v.getNm_url_afetadas());
            stmt.setString(6, v.getImg());
            stmt.setString(7, v.getDescricao());
            stmt.setString(8, v.getReferencias());
            stmt.setString(9, v.getRemediacao());
            stmt.setInt(10, v.getId_projeto());
            
            stmt.execute();

            return "FOI";
        } catch (Exception e) {
            return resposta;
        }
    }

    public String alterFromDbById(Vulnerabilidade v) {
        String resposta = null;

        try {
            String SQL = "UPDATE VULNERABILIDADE SET"
                    + " nome = ? ,"
                    + " fl_criticidade = ?,"
                    + "vl_parametros = ? ,"
                    + "nm_parametros_afetados = ? ,"
                    + "nm_url_afetadas = ? ,"
                    + "img = ? ,"
                    + "descricao = ? ,"
                    + "referencias = ? ,"
                    + "remediacao = ? "
                    + "WHERE CODIGO = ?";
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            stmt.setString(1, v.getNome().toUpperCase());
            stmt.setString(2, v.getFl_criticidade());
            stmt.setInt(3, v.getVl_parametros());
            stmt.setString(4, v.getNm_parametros_afetados());
            stmt.setString(5, v.getNm_url_afetadas());
            stmt.setString(6, v.getImg());
            stmt.setString(7, v.getDescricao());
            stmt.setString(8, v.getReferencias());
            stmt.setString(9, v.getRemediacao());
            stmt.setInt(10, v.getCodigo());

            stmt.execute();
            return "foi";
        } catch (Exception e) {
            return resposta;
        }
    }

    public String deleteFromDbById(int codigo) {
        String resposta = null;
        try {
            String SQL = "DELETE FROM VULNERABILIDADE WHERE CODIGO = " + codigo;
            PreparedStatement stmt = bd.connect().prepareStatement(SQL);
            stmt.execute();

            return "foi";
        } catch (Exception e) {
            return resposta;
        }
    }

}
