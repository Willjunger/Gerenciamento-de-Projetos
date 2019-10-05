/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medilab.gerenciaprojeto.servicos;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.medilab.gerenciaprojeto.dao.ProjetosDao;
import com.medilab.gerenciaprojeto.dao.VulnerabilidadeDao;
import com.medilab.gerenciaprojeto.modelos.Vulnerabilidade;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author medilab
 */
@Path("/vulnerabilidade")
public class VulnerabilidadeResources {
    
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        org.json.JSONArray resposta = null;
        
        resposta = new VulnerabilidadeDao().selectFromDB();
        
        JSONObject jsonResposta = new JSONObject();
        jsonResposta.put("data",resposta);
        
        return Response.ok().entity(jsonResposta.toString()).build();
    }
    
    
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id){
        JSONObject resposta = null;
        
        resposta = new VulnerabilidadeDao().selectFromDbById(id);
        
        return Response.ok().entity(resposta.toString()).build();
    }
    
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)   
    public Response setNew(String json){
        String resposta = null;
        Gson gson = new Gson();
        Vulnerabilidade v = gson.fromJson(json, Vulnerabilidade.class);
        resposta =  new VulnerabilidadeDao().insertDB(v);
        
        if(resposta.isEmpty()){
            return Response.serverError().entity(resposta).build();
        }else{
          return Response.ok().entity(resposta).build();  
        }     
    }
    
    
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)   
    public Response SetAlter(String json){
        String resposta = null;
        Gson gson = new Gson();
        Vulnerabilidade v = gson.fromJson(json, Vulnerabilidade.class);
        resposta =  new VulnerabilidadeDao().alterFromDbById(v);
        
        if(resposta.isEmpty()){
            return Response.serverError().entity(resposta).build();
        }else{
          return Response.ok().entity(resposta).build();  
        }  
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delById(@PathParam("id") int id){
        String resposta = new  VulnerabilidadeDao().deleteFromDbById(id);
        
        return Response.ok().build();
    }
}
