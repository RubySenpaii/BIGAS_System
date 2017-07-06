/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.mobile;

import dao.WeeklyPlantingReportDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author RubySenpaii
 */
@MultipartConfig
public class MobileDamageReportImage extends HttpServlet {

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
        System.out.println("receiving damageReportImages");
        String filepath = "C:\\\\Users\\\\RubySenpaii\\\\Documents\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\images\\\\damageReport";
        ArrayList<String> imagePath = new ArrayList<>();
        Iterable<Part> parts = request.getParts();
        OutputStream out = null;
        InputStream fileContent = null;
        for (Part part : parts) {
            try {
                out = new FileOutputStream(new File(filepath + File.separator + part.getSubmittedFileName()));
                fileContent = part.getInputStream();
                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = fileContent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                imagePath.add("images\\damageReport" + part.getSubmittedFileName());
            } catch (FileNotFoundException x) {
                System.out.println("file not found");
                Logger.getLogger(MobileDamageReportImage.class.getName()).log(Level.SEVERE, null, x);
            } finally {
                if (out != null) {
                    out.close();
                }
                if (fileContent != null) {
                    fileContent.close();
                }
            }
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
