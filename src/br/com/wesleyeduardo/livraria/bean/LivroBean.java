package br.com.wesleyeduardo.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.wesleyeduardo.livraria.dao.DAO;
import br.com.wesleyeduardo.livraria.modelo.Autor;
import br.com.wesleyeduardo.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	private Integer autorId;

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Escrito por " + autor.getNome());

	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros() {
		
		return new DAO<Livro>(Livro.class).listaTodos();
		
	}


    public void gravar() {
        System.out.println("Gravando livro " + this.livro.getTitulo());

        if (livro.getAutores().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("autor",  new FacesMessage("Livro deve ter pelo menos um Autor"));
            return;
        } else {
            new DAO<Livro>(Livro.class).adiciona(this.livro);
            this.livro = new Livro();
        }
    }
    
    public void remover(Livro livro) {
        System.out.println("Removendo livro " + livro.getTitulo());
        new DAO<Livro>(Livro.class).remove(livro);            
    }
	
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {

	    String valor = value.toString();
	    if (!valor.startsWith("1")) {
	        throw new ValidatorException(new FacesMessage("Deveria come�ar com 1"));
	    }
	}
	
	
	  public String formAutor() {
	        System.out.println("Chamanda o formulario do Autor");
	        return "autor?faces-redirect=true";
	    }

}
