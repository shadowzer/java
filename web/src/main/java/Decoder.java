import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/decode")
public class Decoder extends HttpServlet {

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getParameter("text");

        byte[] ans = Base64.decodeBase64(s.getBytes());
        ByteArrayInputStream bIn = new ByteArrayInputStream(ans);
        XZCompressorInputStream xzIn = new XZCompressorInputStream(bIn);
        ans = new byte[ans.length];
        xzIn.read(ans);
        xzIn.close();

        s = new String(ans).trim();
        req.setAttribute("result", s);
        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }
*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getParameter("text");

        byte[] ans = Base64.decodeBase64(s.getBytes());
        ByteArrayInputStream bIn = new ByteArrayInputStream(ans);
        XZCompressorInputStream xzIn = new XZCompressorInputStream(bIn);
        ans = new byte[ans.length];
        xzIn.read(ans);
        xzIn.close();

        s = new String(ans).trim();
        req.setAttribute("result", s);
        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }
}
