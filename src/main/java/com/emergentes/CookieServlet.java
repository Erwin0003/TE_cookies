package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ErwinEM
 */
@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensaje = null;
        boolean nuevaVista = true;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ((c.getName().equals("visitante")) && c.getValue().equals("SI")) {
                    nuevaVista = false;
                    break;
                }
            }
        }

        if (nuevaVista) {
            Cookie ck = new Cookie("visitante", "SI");
            ck.setMaxAge(10);
            ck.setComment("control de nuevos visitantes");
            response.addCookie(ck);
            mensaje = "Gracias por visitar nuestra pagina";
        } else {
            mensaje = "Estamos agradecidos por tenerlo NUEVAMENTE";
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + mensaje + "</h1>");
        

        out.println("<a href='index.jsp'>Ir a Inicio</a>");
    }
}
