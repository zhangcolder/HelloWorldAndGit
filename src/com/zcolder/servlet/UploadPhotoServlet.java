package com.zcolder.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadPhotoServlet
 */
@WebServlet("/UploadPhoto")
public class UploadPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileName = "";
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            //�����ϴ��ļ��Ĵ�СΪ4MB
            factory.setSizeThreshold(4*1024 * 1024);
            @SuppressWarnings("rawtypes")
			List items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
  
            @SuppressWarnings("rawtypes")
			Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
  
                    // ����ʱ�������ͷ���ļ�
                    fileName = System.currentTimeMillis() + ".jpg";
                     
                    //ͨ��getRealPath��ȡ�ϴ��ļ��У������Ŀ��e:/project/j2ee/web,��ô�ͻ��Զ���ȡ�� e:/project/j2ee/web/images
                    String photoFolder =request.getServletContext().getRealPath("images");
                     
                    File f = new File(photoFolder, fileName);
                    f.getParentFile().mkdirs();
  
                    // ͨ��item.getInputStream()��ȡ������ϴ����ļ���������
                    InputStream is = item.getInputStream();
  
                    // �����ļ�
                    FileOutputStream fos = new FileOutputStream(f);
                    byte b[] = new byte[1024 * 1024];
                    int length = 0;
                    while (-1 != (length = is.read(b))) {
                        fos.write(b, 0, length);
                    }
                    fos.close();
  
                } else {
                    System.out.println(item.getFieldName());
                    String value = item.getString();
                    //���servlet��û�ж� ������Ӧ�ı�������Ϊutf-8
                    value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                    System.out.println(value);
                }
            }
             
            String html = "<img width='1200' height='750' src='images/%s' />";
            response.setContentType("text/html");
            PrintWriter pw= response.getWriter();
             
            pw.format(html, fileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
