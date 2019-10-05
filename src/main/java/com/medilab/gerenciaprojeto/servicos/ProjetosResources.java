/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medilab.gerenciaprojeto.servicos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.medilab.gerenciaprojeto.dao.ProjetosDao;
import com.medilab.gerenciaprojeto.dao.VulnerabilidadeDao;
import com.medilab.gerenciaprojeto.modelos.CheckList;
import com.medilab.gerenciaprojeto.modelos.Projetos;
import com.medilab.gerenciaprojeto.modelos.Vulnerabilidade;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author medilab
 */
@Path("/projetos")
public class ProjetosResources {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        JSONArray resposta = new ProjetosDao().selectFromDb();
        //  Padrão de resposta ao front-end
        JSONObject jsonResposta = new JSONObject();
        jsonResposta.put("data", resposta);
        return Response.status(200).entity(jsonResposta.toString()).build();
    }

    @GET
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getbyId(@PathParam("codigo") int codigo) {
        //vulnerabilidade dao e informações do projeto
        JSONObject resposta = new ProjetosDao().selectFromDbById(codigo);
        JSONObject resposta2 = new ProjetosDao().selectChecklistFromDbById(codigo);     
        JSONArray resp = new VulnerabilidadeDao().selectFromDbByIdProjetos(codigo);
        
        JSONObject json = new JSONObject();
        json.put("projeto", resposta);
        json.put("checklist", resposta2);
        json.put("vulnerabilidades", resp);

        
        return Response.status(200).entity(json.toString()).build();

    }

    @GET
    @Path("/{codigo}/vulnerabilidades")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVulnerabilidadesFromProjetos(@PathParam("codigo") int codigo) {
        JSONArray resp = new VulnerabilidadeDao().selectFromDbByIdProjetos(codigo);

        return Response.status(200).entity(resp.toString()).build();

    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setNew(String novo) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        Projetos p = gson.fromJson(novo, Projetos.class);
        String resposta;
        
        if (p.getCodigo() != 0 && !p.getNome().equals("")) {
            resposta = new ProjetosDao().insertBd(p);
        } else {
            resposta = null;
        }

        if (resposta.isEmpty()) {
            return Response.serverError().entity("Ja existe esse codigo na tabela").build();
        } else {
            return Response.ok().entity(p).build();
        }
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterOne(String novo) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        Projetos p = gson.fromJson(novo, Projetos.class);
        String resposta = new ProjetosDao().alterFromDbById(p);

        return Response.status(200).entity(resposta).build();
    }
    
    
     @PUT
    @Path("/checklist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterCheckList(String novo) {
        Gson gson = new Gson();
        CheckList p = gson.fromJson(novo, CheckList.class);
        String resposta = new ProjetosDao().alterCheckListFromDbById(p);

        if(!resposta.isEmpty()){
            return Response.status(200).build();
        }else{
            return Response.status(500).build();
        }
        
    }
    
   
    
    @PUT
    @Path("/vulnerabilidade")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterChecklist(String novo) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        Projetos p = gson.fromJson(novo, Projetos.class);
        String resposta = new ProjetosDao().alterFromDbById(p);

        return Response.status(200).entity(resposta).build();
    }

    @DELETE
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropbyId(@PathParam("codigo") int codigo) {
        String resposta = new ProjetosDao().deleteFromDbById(codigo);

        return Response.status(200).entity(resposta).build();

    }

}
