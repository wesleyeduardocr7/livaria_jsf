package br.com.wesleyeduardo.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.wesleyeduardo.livraria.dao.DAO;
import br.com.wesleyeduardo.livraria.modelo.Autor;
import br.com.wesleyeduardo.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		
		 System.out.println("Gravando Autor " + this.autor.getNome());

		    
		    if (this.autor.getId() == null) {
		        new DAO<Autor>(Autor.class).adiciona(this.autor);        
		    } else {
		    	new DAO<Autor>(Autor.class).atualiza(this.autor);
		    }

		    this.autor = new Autor();
		
	}
	
	
	public List<Autor> getAutores(){
		
		return new DAO<Autor>(Autor.class).listaTodos();		
	}
	
}
