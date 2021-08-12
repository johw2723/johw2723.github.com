package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.CalDAO;
import model.CalService;
import model.CalVO;

/**
 * Servlet implementation class CalServlet
 */

@WebServlet("/caldb.do")
public class CalServlet extends HttpServlet {
	private CalService cs;
	private List<CalVO> list; // ���׸� List�� �޴� CalVO
	private CalVO vo;
	private static final long serialVersionUID = 1L;


    public CalServlet() {
    	super(); 
    	cs = new CalService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		System.out.println("Servlet :: command : " + command);
		
		if(request.getParameter("command").equals("insert")) {
			doInsert(request, response);
		} else if(request.getParameter("command").equals("select")){
			doSelect(request, response);
		} else if(request.getParameter("command").equals("selectAll")){
			doSelectAll(request, response);
		} else if(request.getParameter("command").equals("update")){
			doUpdate(request, response);
		} else if(request.getParameter("command").equals("delete")){
			doDelete(request, response);
		} else if(request.getParameter("command").equals("deleteAll")){
			doDeleteAll(request, response);
		} 
	}
	
	// ��ü ��ȸ�� ��� ȣ��
	private void doSelectAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
					
			JSONObject obj = new JSONObject();	
			JSONArray jArray =  new JSONArray(); // json �迭

			// ����Ͻ� ���� ȣ��
			list = cs.calList();	
		
			for(int i=0; i<list.size(); i++) {	
				JSONObject arrObj = new JSONObject();	// �迭 ���� �� json
				arrObj.put("getNo", list.get(i).getNo());
				arrObj.put("getOp1", list.get(i).getOp1());
				arrObj.put("getOp", list.get(i).getOp());
				arrObj.put("getOp2", list.get(i).getOp2());
				arrObj.put("getResult", list.get(i).getResult());
				jArray.add(arrObj);				
				
			}			

			obj.put("item", jArray);
			System.out.println(obj.toString());

			out.print(obj);   				
			out.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// �κ� ��ȸ�� ��� ȣ��
	private void doSelect(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			// �Ķ���� �� ��������
			int no = Integer.parseInt(request.getParameter("no"));	
			//System.out.println("no : " + no);
			
			// ����Ͻ� ���� ȣ��
			vo = cs.calSelect(no);		
			//System.out.println("rs : " + vo.getResult());
			
			// �� ó��
			int op1 = vo.getOp1();
			String op = vo.getOp();
			int op2 = vo.getOp2();
			int result = vo.getResult();
			
			obj.put("op1", op1);
			obj.put("op", op);
			obj.put("op2", op2);
			obj.put("result", result);

			out.print(obj);   				
			out.close();		
		}catch(Exception e){
			System.out.println(e);
		}
		
	}

	// ���ο� ����� �� ��� ȣ��
	public void doInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
					
			JSONObject obj = new JSONObject();
			
			// �Ķ���� ���� �����´�
			int op1 = Integer.parseInt(request.getParameter("op1"));		
			String op = request.getParameter("op");
			int op2 = Integer.parseInt(request.getParameter("op2"));
			System.out.println("op1 : " + op1 + " / op : " + op + " / op2 : " + op2);
			
			// �� ó��
			vo = new CalVO();
			vo.setOp1(op1);
			vo.setOp(op);
			vo.setOp2(op2);
			
			System.out.println("op1 : " + vo.getOp1() + " / op : " + vo.getOp() + " / op2 : " + vo.getOp2());
			
			cs.calInsert(vo);
			
			int result = vo.getResult();
			System.out.println("result : " + result);
					
			obj.put("op1", op1);
			obj.put("op", op);
			obj.put("op2", op2);
			obj.put("result", result);
			
			out.print(obj);   				
			out.close();		
		}catch(Exception e){
			System.out.println(e);
		}
	}  	
	
    
    // ����� ������ ��� ȣ��
    public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			
			// �Ķ���� �� ��������
			int no = Integer.parseInt(request.getParameter("no"));
	    	int op1 = Integer.parseInt(request.getParameter("op1"));
			String op = request.getParameter("op");
			int op2 = Integer.parseInt(request.getParameter("op2"));
				
			// �� ó��
			vo = new CalVO();
			vo.setNo(no);
			vo.setOp1(op1);
			vo.setOp(op);
			vo.setOp2(op2);
			
			// ����Ͻ� ���� ȣ��
			cs.calUpdate(vo);			
			
			int result = vo.getResult();
			
			obj.put("no", no);
			obj.put("op1", op1);
			obj.put("op", op);
			obj.put("op2", op2);
			obj.put("result", result);

			out.print(obj);   				
			out.close();		
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
    
    // ��� ������ ���� ��� ȣ��
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   	
    	try {	
    		response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			// �Ķ���� �� ȣ��
			int no = Integer.parseInt(request.getParameter("no"));

			// ����Ͻ� ����
			cs.calDelete(no);
			
			obj.put("no", no);

			out.print(obj);   				
			out.close();
		} catch(Exception e){
			System.out.println(e);
		}
	}
    
    // ��ü ��� ����� ���� ��� ȣ��
    public void doDeleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
    	try {	
    		response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			// ����Ͻ� ���� ȣ��
			cs.calDeleteAll();
			
			String str = "��ü ����� �����Ǿ����ϴ�.";
			
			obj.put("str", str);
			out.print(obj);   				
			out.close();
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
