package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;

@WebServlet(urlPatterns = "/usucontroller")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private List<Usuario> lista = new ArrayList<>();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Capturando o que vem do client
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");

		// Instanciando objeto
		Usuario usu = new Usuario(nome, email);

		// Inserir na lista
		lista.add(usu);

		resp.getWriter().println("{ nome:" + usu.getNome() + "  , email: " + usu.getEmail() + "  } ");

	}
	
	
	
	/*
	 * Deletar por nome e email.
	 * 
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		
		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getNome().equals(nome)|| lista.get(i).getEmail().equals(email) ){
				lista.remove(i);
				break;
			}
			
		}
	}
	*/
	
	// Deletar por índice.
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int op = Integer.parseInt(req.getParameter("op"));
		
		lista.remove(op);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Indice a ser alterado
		int op = Integer.parseInt(req.getParameter("op"));

		
		// Dados a serem alterados
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		
		//Acessando objeto e alterando dados
		Usuario usu = lista.get(op);
		usu.setNome(nome);
		usu.setEmail(email);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json = "[";
		for (int i = 0; i < lista.size(); i++) {

			json += "{ nome:" + lista.get(i).getNome() + "  , email: " + lista.get(i).getEmail() + "  }";
			if (i < lista.size()-1)
				json += ",";
		}
		json += "]";

		resp.getWriter().print(json);

	}

}
