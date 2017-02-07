

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MyServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    // This messageID would be used to get the correct file eventually
    	String messageFromParams = request.getParameter("messageID");
    	long messageID  = 0;
    	if(messageFromParams!=null){
    		 messageID = Long.parseLong(request.getParameter("messageID"));
    	}
	   
	    ServletOutputStream out = response.getOutputStream();
	    ServletContext context = getServletConfig().getServletContext();
	    String mimetype = context.getMimeType("C:\\Users\\Soto\\Desktop\\new_audio1.amr");
	    String sizeFromHeader = request.getParameter("fileSize");
	    int generatedFileLength = 0;
	    if(sizeFromHeader!=null){
	    	System.out.println(sizeFromHeader);
	    	generatedFileLength = Integer.parseInt(sizeFromHeader);
	    }

	    response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
	    response.setContentLength(generatedFileLength);
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + "randomFile.txt" + "\"");

	    byte[] buffer = new byte[4096];

	   	int totalSize = 0;
	    while(totalSize <  generatedFileLength) {
	    	buffer = generateRandomContent();
	        out.write(buffer, 0, 4096);
	        totalSize += 4096;
	    }
	    out.flush();
	}
    
    public static byte[] generateRandomContent(){
		byte[] randomContent = new byte[4096];
		for(int i = 0; i < randomContent.length; i++){
			randomContent[i] = (byte)0x2c;
		}
		return randomContent;
	}

}
