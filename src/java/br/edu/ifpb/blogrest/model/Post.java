/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.blogrest.model;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.TransactionManagement;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joaopaulo
 */
@Entity
@XmlRootElement
public class Post implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer id;
    private String autor;
    private String titulo;
    private Date data;
    private String conteudo;
    
        public Post(Integer id, String autor, String titulo, Date data, String conteudo){
            this.id = id;
            this.autor = autor;
            this.titulo = titulo;
            this.data = data;
            this.conteudo = conteudo;            
        }
        
        public Post(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
        
}
