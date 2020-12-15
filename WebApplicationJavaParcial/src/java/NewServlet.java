/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moises
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Materias inscritas</h1>");
            out.println("<table>");
            
            String idAlumno="6645754";
            Connection con = null;
            Statement stm = null;
            try {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SistemaEducativo","postgres","123456");
                stm = con.createStatement();
                String sql = "select  alumno.nombre, alumno.ap_paterno, alumno.ap_materno, materia.nombre, cursa.nota from materia, alumno, cursa where '"+idAlumno+"'=cursa.ci and '"+idAlumno+"'=alumno.ci and materia.id_materia=cursa.id_materia";
                ResultSet rs = stm.executeQuery(sql);
                int sw=0;
                while(rs.next())
                {
                    String NombreAlummo = rs.getString(1);
                    String PaternoAlummo = rs.getString(2);
                    String MaternoAlummo = rs.getString(3);
                    String NombreMateria = rs.getString(4);
                    int Nota = rs.getInt(5);
                    if(sw==0)
                    {
                    out.println("<tr>");
                    out.println("<td>Nombre alumn@:  </td>");
                    out.println("<td>"+NombreAlummo+"</td>");
                    out.println("<td>"+PaternoAlummo+"</td>");
                    out.println("<td>"+MaternoAlummo+"</td>");
                    out.println("</tr>");
                    out.println("<tr><td>-----------------------</td></tr>");
                    out.println("<tr><td>MATERIA</td><td>NOTA</td></tr>");
                    sw=1;
                    }
                    out.println("<tr>");  
                    out.println("<td> "+NombreMateria+" </td>");
                    out.println("<td>"+Nota+"</td>");
                    out.println("</tr>");
                }
                /*
                String sql = "insert into docente values(11,'m1','s1');";
                stm.executeUpdate(sql);
                stm.close();
                con.commit();
                con.close();*/
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
