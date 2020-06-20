package br.com.wesleyeduardo.livraria.bean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.wesleyeduardo.livraria.dao.DAO;
import br.com.wesleyeduardo.livraria.modelo.Autor;


@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	
	
	private Integer autorId;

	public Integer getAutorId() {
	    return autorId;
	}

	public void setAutorId(Integer autorId) {
	    this.autorId = autorId;
	}

	public void carregarAutorPelaId() {
	    this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}

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
	
	
	public void removeAutor(Autor autor) {
		
		System.out.println("Removento Autor...");
		
		new DAO<Autor>(Autor.class).remove(autor);
		
	}
	
	public void alteraAutor(Autor autor) {
		
		System.out.println("Alterando Autor...");
		
		this.autor = autor;
		
	}
	
	
	
}
