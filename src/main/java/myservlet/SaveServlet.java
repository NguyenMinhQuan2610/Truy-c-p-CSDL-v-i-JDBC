/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package myservlet;

import jakarta.jms.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


/**
 *
 * @author mygam
 */
public class SaveServlet extends HttpServlet {

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
            //Lấy giá trị dữ liệu từ client
            String uname=request.getParameter("uname");
            String upass=request.getParameter("upass");
            String email=request.getParameter("email");
            String country=request.getParameter("country");
            //Xử lý yêu câu
            java.sql.Connection conn=null;
            PreparedStatement ps=null;
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn= DriverManager.getConnection("jdbc:sqlserver://May04:1433;databaseName=demodb","sa","123");
                ps=conn.prepareStatement("insert into users(name,password,email,country) values(?,?,?,?)");
                    ps.setString(1, uname);
                    ps.setString(2, upass);
                    ps.setString(3, email);
                    ps.setString(4, country);
                    
                    int kq=ps.executeUpdate();
                    if(kq>0){
                        out.println("<h2>Thêm user thành công</h2>");
                    }else{
                        out.println("<h2>Thêm user thất bại</h2>");
                    }
                }catch(Exception e){
                    System.out.println("Loi: "+e.toString());
                    out.println("<h2>Thêm user thất bại</h2>");
                }
                request.getRequestDispatcher("index.html").include(request, response);
                //Nạp driver
                
                
            //Phản hồi kịch bản
            
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