package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.MemberService;
import dao.MemberVO;


@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService ms;


    public MemberServlet() {
    	ms = new MemberService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GET 방식 요청일 경우 호출
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//POST 방식 요청일 경우 호출
		doProcess(request, response);
	}


	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String command = request.getParameter("command");
		
		if(command.equals("insertMember")){
    		insertMember(request, response);	
		} else if(command.equals("idCheck")) {
			IdCheck(request,response);
		} else if(command.equals("updateMember")) {
			updateMember(request, response);
		} else if(command.equals("MemberInfo")) {
			MemberInfo(request, response);
		} else if(command.equals("MemberList")) {
			MemberList(request, response);
		} else if(command.equals("deleteMember")) {
			deleteMember(request, response);
		}
		
	}

	private void deleteMember(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			// 파라미터 값 받기
			String memId = request.getParameter("memId");
					
			boolean result = ms.idCheck(memId);
			System.out.println("idCheck : " + result);
			
			if(result) {
				ms.deleteMember(memId);
				obj.put("msg", "회원 정보가 삭제되었습니다.");
			} else {
				obj.put("msg", "회원 정보가 없습니다.");
			}
							
			System.out.println(obj.toString());	

			out.print(obj);   				
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}


	private void MemberList(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			JSONArray jArray =  new JSONArray(); // json 배열
			
			ArrayList<MemberVO> list = ms.MemberList();
				
			for(int i=0; i<list.size(); i++) {
				JSONObject arrObj = new JSONObject();	// 배열 내에 들어갈 json
				arrObj.put("memId", list.get(i).getMemId());
				arrObj.put("pw", list.get(i).getPw());
				arrObj.put("email", list.get(i).getEmail());
				arrObj.put("phone", list.get(i).getPhone());
				arrObj.put("address", list.get(i).getAddress());
				arrObj.put("payment", list.get(i).getPayment());
				arrObj.put("date", list.get(i).getDate());
				jArray.add(arrObj);	
			}
			
			obj.put("item", jArray);
			System.out.println(obj.toString());	

			out.print(obj);   				
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	private void MemberInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			// 파라미터 값 받기
			String memId = request.getParameter("memId");
					
			MemberVO vo = ms.MemberInfo(memId);
			//System.out.println(vo.toString());				
			
			System.out.println(vo.getMemId());
			if(vo.getMemId()==null) {
				obj.put("msg", "회원 정보가 없습니다.");
			} else {
				obj.put("memId", vo.getMemId());
				obj.put("pw", vo.getPw());
				obj.put("email", vo.getEmail());
				obj.put("phone", vo.getPhone());
				obj.put("address", vo.getAddress());
				obj.put("payment", vo.getPayment());
				obj.put("date", vo.getDate());
			}
				
			System.out.println(obj.toString());	

			out.print(obj);   				
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	private void updateMember(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			// 파라미터 값 받기
			String memId = request.getParameter("memId");
			String pw = request.getParameter("pw");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String payment = request.getParameter("payment");
			
			// 파라미터 값 VO 저장
			MemberVO vo = new MemberVO();
			vo.setMemId(memId);
			vo.setPw(pw);
			vo.setEmail(email);
			vo.setPhone(phone);
			vo.setAddress(address);
			vo.setPayment(payment);
					
			ms.updateMember(vo);
			
			obj.put("memId", memId);
			obj.put("pw", pw);
			obj.put("email", email);
			obj.put("phone", phone);
			if(vo.getPayment()=="") {
				obj.put("address", "미입력");
			} else {
				obj.put("address", address);
			}
			
			if(vo.getPayment()=="") {
				obj.put("payment", "미입력");
			} else {
				obj.put("payment", payment);
			}

			out.print(obj);   				
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}


	private void IdCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			String memId = request.getParameter("memId");
			
			boolean result = ms.idCheck(memId);
			
			obj.put("result", result);
			System.out.println(obj.toString());					
			
			out.print(obj);   				
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}	
			
	}


	private void insertMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비즈니스 로직 호출
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			
			// 파라미터 값 받기
			String memId = request.getParameter("memId");
			String pw = request.getParameter("pw");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String payment = request.getParameter("payment");
			
			// 파라미터 값 VO 저장
			MemberVO vo = new MemberVO();
			vo.setMemId(memId);
			vo.setPw(pw);
			vo.setEmail(email);
			vo.setPhone(phone);
			vo.setAddress(address);
			vo.setPayment(payment);
					
			ms.insertMember(vo);
			
			
			
			obj.put("memId", memId);
			obj.put("pw", pw);
			obj.put("email", email);
			obj.put("phone", phone);
			if(vo.getPayment()=="") {
				obj.put("address", "미입력");
			} else {
				obj.put("address", address);
			}
			
			if(vo.getPayment()=="") {
				obj.put("payment", "미입력");
			} else {
				obj.put("payment", payment);
			}
			
			
			out.print(obj);   				
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
