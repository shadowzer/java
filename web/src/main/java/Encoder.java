import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet("/encode")
public class Encoder extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getParameter("text");

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        XZCompressorOutputStream xzOut = new XZCompressorOutputStream(bout);
        xzOut.write(s.getBytes());
        xzOut.close();

        String result = Base64.encodeBase64String(bout.toByteArray());

        req.setAttribute("result", result);
        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }

    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getParameter("text");

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        XZCompressorOutputStream xzOut = new XZCompressorOutputStream(bout);
        xzOut.write(s.getBytes());
        xzOut.close();

        String result = Base64.encodeBase64String(bout.toByteArray());

        req.setAttribute("result", result);
        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }*/

}
