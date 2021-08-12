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
	private List<CalVO> list; // 제네릭 List를 받는 CalVO
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
	
	// 전체 조회일 경우 호출
	private void doSelectAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
					
			JSONObject obj = new JSONObject();	
			JSONArray jArray =  new JSONArray(); // json 배열

			// 비즈니스 로직 호출
			list = cs.calList();	
		
			for(int i=0; i<list.size(); i++) {	
				JSONObject arrObj = new JSONObject();	// 배열 내에 들어갈 json
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

	// 부분 조회일 경우 호출
	private void doSelect(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			// 파라미터 값 가져오기
			int no = Integer.parseInt(request.getParameter("no"));	
			//System.out.println("no : " + no);
			
			// 비즈니스 로직 호출
			vo = cs.calSelect(no);		
			//System.out.println("rs : " + vo.getResult());
			
			// 값 처리
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

	// 새로운 계산을 할 경우 호출
	public void doInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
					
			JSONObject obj = new JSONObject();
			
			// 파라미터 값을 가져온다
			int op1 = Integer.parseInt(request.getParameter("op1"));		
			String op = request.getParameter("op");
			int op2 = Integer.parseInt(request.getParameter("op2"));
			System.out.println("op1 : " + op1 + " / op : " + op + " / op2 : " + op2);
			
			// 값 처리
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
	
    
    // 계산을 수정할 경우 호출
    public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			
			// 파라미터 값 가져오기
			int no = Integer.parseInt(request.getParameter("no"));
	    	int op1 = Integer.parseInt(request.getParameter("op1"));
			String op = request.getParameter("op");
			int op2 = Integer.parseInt(request.getParameter("op2"));
				
			// 값 처리
			vo = new CalVO();
			vo.setNo(no);
			vo.setOp1(op1);
			vo.setOp(op);
			vo.setOp2(op2);
			
			// 비즈니스 로직 호출
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
    
    // 계산 내용을 지울 경우 호출
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   	
    	try {	
    		response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			// 파라미터 값 호출
			int no = Integer.parseInt(request.getParameter("no"));

			// 비즈니스 로직
			cs.calDelete(no);
			
			obj.put("no", no);

			out.print(obj);   				
			out.close();
		} catch(Exception e){
			System.out.println(e);
		}
	}
    
    // 전체 계산 목록을 지울 경우 호출
    public void doDeleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
    	try {	
    		response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			// 비즈니스 로직 호출
			cs.calDeleteAll();
			
			String str = "전체 계산이 삭제되었습니다.";
			
			obj.put("str", str);
			out.print(obj);   				
			out.close();
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
