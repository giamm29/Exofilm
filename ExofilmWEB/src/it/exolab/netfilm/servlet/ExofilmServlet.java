package it.exolab.netfilm.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.exolab.netfilm.ejb.interfaces.CategoriaEjbInterface;
import it.exolab.netfilm.ejb.interfaces.FilmEjbInterface;
import it.exolab.netfilm.ejb.interfaces.RegistaEjbInterface;
import it.exolab.netfilm.jpa.models.Film;


@WebServlet("/ExofilmServlet")
public class ExofilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	CategoriaEjbInterface categoriaEjb;
	
	@EJB
	FilmEjbInterface filmEjb;
	
	@EJB
	RegistaEjbInterface registaEjb;
    
    public ExofilmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" -- Nella servlet - GET -- ");
		try {
			if(null != request.getParameter("toGetAll")) {
				toGetAll(request);
				request.getRequestDispatcher("getAll.jsp").include(request, response);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("error.jsp").include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" -- Nella servlet - POST -- ");
		try {
			if(null != request.getParameter("toInsert")) {
				toInsert(request);
				request.getRequestDispatcher("recap.jsp").include(request, response);
			} 
			else if(null != request.getParameter("toUpdate")) {
				toUpdate(request);
				request.getRequestDispatcher("recap.jsp").include(request, response);
			} 
			else if(null != request.getParameter("toDelete")) {
				toDelete(request);
				request.getRequestDispatcher("recap.jsp").include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("error.jsp").include(request, response);
		}
	}
	
	private void toInsert(HttpServletRequest request) {
		System.out.println(" -- Nel toInsert -- ");
		
		Film film = new Film();
		
		film.setTitolo(request.getParameter("titolo"));
		film.setIdRegista(Integer.valueOf(request.getParameter("idRegista")));

		film = filmEjb.insert(film);
		request.getSession().setAttribute("film", film);
		
	}
	
	private void toUpdate(HttpServletRequest request) {
		System.out.println(" -- Nel toUpdate -- ");
		
		Film film = new Film();
		
		film.setIdFilm(Integer.valueOf(request.getParameter("idFilm")));
		film.setTitolo(request.getParameter("titolo"));
		film.setIdRegista(Integer.valueOf(request.getParameter("idRegista")));
		
		film = filmEjb.update(film);
		request.getSession().setAttribute("film", film);
	}
	
	private void toGetAll(HttpServletRequest request) {
		System.out.println(" -- Nel toGetAll -- ");

		List<Film> listaFilm = filmEjb.findAll();
		request.getSession().setAttribute("listaFilm", listaFilm);
	}
	
	private void toDelete(HttpServletRequest request) {
		System.out.println(" -- Nel toDelete -- ");
		
		Film filmTrovato = filmEjb.findById(Integer.valueOf(request.getParameter("idFilm")));
		if(null != filmTrovato) {
			System.out.println(" -- film trovato -- ");
			filmEjb.delete(filmTrovato);
		} else {
			System.err.println(" -- film non trovato -- ");
		}
		// svuoto il film in sessione
		request.getSession().setAttribute("film", new Film());
	}

}
