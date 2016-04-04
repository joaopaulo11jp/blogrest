/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.blogrest;

import br.edu.ifpb.blogrest.model.Post;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author joaopaulo
 */
@Path("blog")
public class BlogResource {

    @Context
    private UriInfo context;
    @PersistenceUnit(unitName = "BlogRestJAVADB")
    private EntityManager em;
    
    public BlogResource() {
        em = Persistence.createEntityManagerFactory("BlogRestJAVADB").createEntityManager();
    }

    @POST
    @Consumes("application/xml")
    public Response createPost(Post post){
        if(em.find(Post.class,post.getId()) == null){
           em.getTransaction().begin();
           em.persist(post);
           em.getTransaction().commit();
           return Response.status(Response.Status.CREATED).build();
        }else{
           return Response.status(Response.Status.CONFLICT).build(); 
        }
    }
    
    @PUT
    @Produces("application/json")
    @Path("{id}/{titulo}")
    public Response updatePost(@PathParam("id") Integer id,@PathParam("titulo") String titulo){
        Post post = em.find(Post.class,id);
        if(post != null){
            post.setTitulo(titulo);
            em.getTransaction().begin();
            em.merge(post);
            em.getTransaction().commit();
            return Response.ok(post, MediaType.APPLICATION_JSON).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @DELETE
    @Produces({MediaType.APPLICATION_XML})
    @Path("{id}")
    public Response removePost(@PathParam("id") Integer id){
        Post post = em.find(Post.class,id);        
        if(post != null){
            em.getTransaction().begin();
           em.remove(post);
           em.getTransaction().commit();
           return Response.ok(post, MediaType.APPLICATION_XML).build();
        }else{
           return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    public Response getPost(@QueryParam("id") Integer id){
        Post post = em.find(Post.class, id);
        String resposta = "";
        
        if(post != null){
           resposta = "<html><body>"+"<h1>Titulo:"+post.getTitulo()+"</h1>"+
                                     "<p>Id:"+post.getId()+"</p>"+
                                     "<p>Data:"+post.getData()+"</p>"+
                                     "<p>Autor:"+post.getAutor()+"</p>"+
                                     "<p>Conteudo:"+post.getConteudo()+"</p></body></html>";
           return Response.ok(resposta).build();
        }else{
           return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
