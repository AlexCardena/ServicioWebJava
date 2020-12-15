/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Alex
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ConsultaJava")
    public String[] ConsultaJava(@WebParam(name = "ci_alumno") String ci_alumno) {
        //TODO write your implementation code here:
        String []resultado=new String[100];
        Connection con = null;
        Statement stm = null;
            try {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SistemaEducativo","postgres","123456");
                stm = con.createStatement();
                String sql = "select  alumno.nombre, alumno.ap_paterno, alumno.ap_materno, materia.nombre, cursa.nota from materia, alumno, cursa where '"+ci_alumno+"'=cursa.ci and '"+ci_alumno+"'=alumno.ci and materia.id_materia=cursa.id_materia";
                ResultSet rs = stm.executeQuery(sql);
                int sw=0, i=3;
                while(rs.next())
                {
                    String NombreAlummo = rs.getString(1);
                    String PaternoAlummo = rs.getString(2);
                    String MaternoAlummo = rs.getString(3);
                    String NombreMateria = rs.getString(4);
                    int Nota = rs.getInt(5);
                    if(sw==0)
                    {
                        resultado[0]=NombreAlummo;
                        resultado[1]=PaternoAlummo;
                        resultado[2]=MaternoAlummo;
                        sw=1;
                    }
                    resultado[i]=NombreMateria;
                    i++;
                    resultado[i]=Nota+"";
                    i++;
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        return resultado;
    }

    
}
