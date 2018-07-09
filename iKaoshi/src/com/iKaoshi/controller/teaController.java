 package com.iKaoshi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iKaoshi.bean.Question1;
import com.iKaoshi.bean.Shijuanzhuguan;
import com.iKaoshi.bean.Student;
import com.iKaoshi.bean.Stutestinfo;
import com.iKaoshi.bean.TeaTestInfo;
import com.iKaoshi.bean.Teacjfenxi;
import com.iKaoshi.bean.Teaconsult;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.bean.kaoshinum;
import com.iKaoshi.bean.tea_cha_chengji;
import com.iKaoshi.service.studentService;
import com.iKaoshi.service.suiji;
import com.iKaoshi.service.teacherService;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Controller
public class teaController {
	//璺宠浆鍒版暀瀹ょ櫥褰曠晫闈�
	//create by lcq 2018骞�6鏈�19鏃�09:02:21
	@RequestMapping("/tea_login_s")
	public ModelAndView tea_login_s(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("tea_login","error",str);
	}
	
	@RequestMapping("/riqi")
	public ModelAndView riqi(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("riqi","error",str);
	}
	@RequestMapping("/hellow")
	public ModelAndView hellow(HttpServletRequest request)
	{
		String str="123";
		int[] a=new int[5];
		 a= suiji.getRandomFromArray(10,5);
		 System.out.println(a.toString());
        return new ModelAndView("hellow","error",str);
	}
	
	
	@RequestMapping("/tea_info")
	public ModelAndView stu_info(HttpServletRequest request)
	{
		String str="";
        return new ModelAndView("tea_info","error",str);
	}
	@RequestMapping("/tea_login")
	public ModelAndView stu_login(HttpServletRequest request)
	{
		String idd = request.getParameter("username");
		int id=Integer.parseInt(idd);
		request.getSession().setAttribute("sessiontea_id",id);     //鐢⊿ession淇濆瓨鐢ㄦ埛鍚�  
		
        String password = request.getParameter("password");
        System.out.println(id);System.out.println(password);
        String str="123";
        if(teacherService.login(id, password))
        {
        	
        	return new ModelAndView("tea_home","error",str);
        }
        return new ModelAndView("default","error",str);
	}
	@RequestMapping("/tea_home")
	public ModelAndView stu_home(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("tea_home","error",str);
	}
	
	//璺宠浆鍒伴搴撶鐞嗙晫闈�
	//create by lcq 2018骞�6鏈�15鏃�19:14:55
	@RequestMapping("/tea_tikuguanli")
	public ModelAndView tea_tikuguanli(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("tea_tikuguanli","error",str);
	}
	
	//璺宠浆鍒版坊鍔犻搴撶晫闈�
	//create by lcq 2018骞�6鏈�15鏃�20:03:13
	@RequestMapping("/tea_addtiku")
	public ModelAndView tea_addtiku(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("tea_addtiku","error",str);
	}
	//鍦ㄦ坊鍔犻搴撶晫闈腑鐐瑰嚮娣诲姞鎸夐挳
	//create by lcq 2018骞�6鏈�15鏃�20:13:19
	@RequestMapping("/tea_addtiku_f")
	public ModelAndView tea_addtiku_f(HttpServletRequest request)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		System.out.println(tea_id);
		String tiku_name = request.getParameter("tiku_name");
		System.out.println(tiku_name);
		String str="123";
		Tikuxinxi t=new Tikuxinxi();
		t.setTea_Id(tea_id);t.setTiku_name(tiku_name);
		teacherService.addTikuxinxi(t);
	    return new ModelAndView("tea_addtiku","error",str);
	}
	//璺宠浆鍒版煡鐪嬮搴撶晫闈�
	//create by lcq 2018骞�6鏈�16鏃�08:37:47
	@RequestMapping("/tea_chakantiku")
	public ModelAndView tea_chakantiku(HttpServletRequest request)
	{
		String str="123";
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<Tikuxinxi> t=null;
		t=teacherService.quary(tea_id);
		
        return new ModelAndView("tea_chakantiku","tikuxinxi",t);
	}
	
	 //鍏蜂綋棰樺簱绠＄悊 瀵规寚瀹氱殑棰樺簱杩涜绠＄悊
	// create by lcq 2018骞�6鏈�16鏃�19:40:09
	@RequestMapping("/tea_dangetikuguanli")
	public ModelAndView tea_dangetikuguanli(HttpServletRequest request,Model model)
	{
		
		String tiku_ID = request.getParameter("tiku_ID");
		//String tiku_name = request.getParameter("tiku_name");
		//model.addAttribute("tiku_name", tiku_name);
		int tiku_Id=Integer.parseInt(tiku_ID);
		List<Question1> q=null;
		q=teacherService.quaryQuestionbytikuID(tiku_Id);
		System.out.println(q.size());
		model.addAttribute("question", q);
        return new ModelAndView("tea_dangetikuguanli","tiku_ID",tiku_ID);
	}
	//棰樼洰淇℃伅淇敼鐣岄潰
	//create by lcq 2018骞�6鏈�17鏃�09:53:08
	@RequestMapping("/tea_changequestion")
	public ModelAndView tea_changequestion(HttpServletRequest request,Model model)
	{
		
		String question_Idd = request.getParameter("question_Id");
		String tiku_Idd = request.getParameter("tiku_ID");
		//String tiku_name = request.getParameter("tiku_name");
		//model.addAttribute("tiku_name", tiku_name);
		int question_Id=Integer.parseInt(question_Idd);
		int tiku_Id=Integer.parseInt(tiku_Idd);
		List<Question1> q=null;
		q=teacherService.quaryQuestionbyquestionID(question_Id, tiku_Id);
		System.out.println(q.size());
		if(q.size()!=0) 
		{
			System.out.println(q.get(0).getQuestion_type());
			Question1 nq=new Question1();
			nq=q.get(0);
			model.addAttribute("question", nq);
		}
		else
		{
			Question1 nq=new Question1();
			model.addAttribute("question",nq );
		}
        return new ModelAndView("tea_changequestion","tiku_ID",tiku_Id);
	}
	//鍏蜂綋淇敼棰樼洰淇℃伅鐨勪唬鐮�  閫夋嫨
	//create by lcq 2018骞�6鏈�17鏃�10:38:17
	@RequestMapping("/tea_changequestion_f1")
	public ModelAndView tea_changequestion_f1(HttpServletRequest request,Model model)
	{
		
		
		String tiku_Idd = request.getParameter("tiku_ID");
		String question_Idd= request.getParameter("question_Id");
		int question_Id=Integer.parseInt(question_Idd);
		int tiku_Id=Integer.parseInt(tiku_Idd);
        String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String choice_A=request.getParameter("choice_A");System.out.println(choice_A);
		String choice_B=request.getParameter("choice_B");System.out.println(choice_B);
		String choice_C=request.getParameter("choice_C");System.out.println(choice_C);
		String choice_D=request.getParameter("choice_D");System.out.println(choice_D);
		String answerr=request.getParameter("answer");
		int answer=0;
		System.out.println(answerr);
		if(answerr.equals("A")) {
			answer=1;
		}else if(answerr.equals("B")) {
			answer=2;
		}else if(answerr.equals("C")) {
				answer=3;
		}else if(answerr.equals("D")) {
				answer=4;
		}
		System.out.println(answer);
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		List<Question1> q=null;
		q=teacherService.quaryQuestionbyquestionID(question_Id, tiku_Id);
		System.out.println(q.size());
		Question1 nq=new Question1();
		if(q.size()!=0) 
		{
			System.out.println(q.get(0).getQuestion_type());
			nq=q.get(0);
		}
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(!choice_A.isEmpty()) nq.setChoice_A(choice_A);
		if(!choice_B.isEmpty()) nq.setChoice_B(choice_B);
		if(!choice_C.isEmpty()) nq.setChoice_C(choice_C);
		if(!choice_D.isEmpty()) nq.setChoice_D(choice_D);
		if(answer!=0) nq.setAnswer(answer);
		if(lable!=0) nq.setLable(lable);
		System.out.println(nq.toString());
		teacherService.updatebyone(nq);
		model.addAttribute("question", nq);
        return new ModelAndView("tea_changequestion","tiku_ID",tiku_Idd);
	}
	//淇敼鍒ゆ柇棰�
	//create by lcq 2018骞�6鏈�17鏃�14:44:15
	@RequestMapping("/tea_changequestion_f2")
	public ModelAndView tea_changequestion_f2(HttpServletRequest request,Model model)
	{
		
		
		String tiku_Idd = request.getParameter("tiku_ID");
		String question_Idd= request.getParameter("question_Id");
		int question_Id=Integer.parseInt(question_Idd);
		int tiku_Id=Integer.parseInt(tiku_Idd);
        String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String answerr=request.getParameter("answer");
		int answer=0;
		if(answerr.equals("姝ｇ‘")) {
			answer=1;
		}else if(answerr.equals("閿欒")) {
			answer=2;
		}
		System.out.println(answer);
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		List<Question1> q=null;
		q=teacherService.quaryQuestionbyquestionID(question_Id, tiku_Id);
		System.out.println(q.size());
		Question1 nq=new Question1();
		if(q.size()!=0) 
		{
			System.out.println(q.get(0).getQuestion_type());
			nq=q.get(0);
		}
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(answer!=0) nq.setAnswer(answer);
		if(lable!=0) nq.setLable(lable);
		System.out.println(nq.toString());
		teacherService.updatebyone(nq);
		model.addAttribute("question", nq);
		
        return new ModelAndView("tea_changequestion","tiku_ID",tiku_Idd);
	}
	//淇敼澶ч
	//create by lcq 2018骞�6鏈�17鏃�14:46:12
	@RequestMapping("/tea_changequestion_f3")
	public ModelAndView tea_changequestion_f3(HttpServletRequest request,Model model)
	{
		
		
		String tiku_Idd = request.getParameter("tiku_ID");
		String question_Idd= request.getParameter("question_Id");
		int question_Id=Integer.parseInt(question_Idd);
		int tiku_Id=Integer.parseInt(tiku_Idd);
        String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		List<Question1> q=null;
		q=teacherService.quaryQuestionbyquestionID(question_Id, tiku_Id);
		System.out.println(q.size());
		Question1 nq=new Question1();
		if(q.size()!=0) 
		{
			System.out.println(q.get(0).getQuestion_type());
			nq=q.get(0);
		}
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(lable!=0) nq.setLable(lable);
		System.out.println(nq.toString());
		teacherService.updatebyone(nq);
		model.addAttribute("question", nq);
        return new ModelAndView("tea_changequestion","tiku_ID",tiku_Idd);
	}
	//娣诲姞閫夋嫨鐣岄潰
	//create by lcq 2018骞�6鏈�17鏃�15:53:33
	@RequestMapping("/tea_addxuanze")
	public ModelAndView tea_addxuanze(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int count=teacherService.getMaxquestion(tiku_Id);
		model.addAttribute("question_Id", count+1);
        return new ModelAndView("tea_addxuanze","tiku_ID",tiku_Id);
	}
	//娣诲姞鍒ゆ柇鐣岄潰
	//create by lcq 2018骞�6鏈�17鏃�15:53:33
	@RequestMapping("/tea_addpanduan")
	public ModelAndView tea_addpanduan(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int count=teacherService.getMaxquestion(tiku_Id);
		model.addAttribute("question_Id", count+1);
        return new ModelAndView("tea_addpanduan","tiku_ID",tiku_Id);
	}
	//娣诲姞澶ч鐣岄潰
	//create by lcq 2018骞�6鏈�17鏃�15:53:33
	@RequestMapping("/tea_adddati")
	public ModelAndView tea_adddati(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int count=teacherService.getMaxquestion(tiku_Id);
		model.addAttribute("question_Id", count+1);
        return new ModelAndView("tea_adddati","tiku_ID",tiku_Id);
	}
	//娣诲姞閫夋嫨鍔熻兘
	//create by lcq 2018骞�6鏈�17鏃�16:24:58
	@RequestMapping("/tea_addxuanze_f")
	public ModelAndView tea_addxuanze_f(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int question_Id=teacherService.getMaxquestion(tiku_Id)+1;
		String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String choice_A=request.getParameter("choice_A");System.out.println(choice_A);
		String choice_B=request.getParameter("choice_B");System.out.println(choice_B);
		String choice_C=request.getParameter("choice_C");System.out.println(choice_C);
		String choice_D=request.getParameter("choice_D");System.out.println(choice_D);
		String answerr=request.getParameter("answer");
		int answer=0;
		System.out.println(answerr);
		if(answerr.equals("A")) {
			answer=1;
		}else if(answerr.equals("B")) {
			answer=2;
		}else if(answerr.equals("C")) {
				answer=3;
		}else if(answerr.equals("D")) {
				answer=4;
		}
		System.out.println(answer);
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		Question1 nq=new Question1();
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(!choice_A.isEmpty()) nq.setChoice_A(choice_A);
		if(!choice_B.isEmpty()) nq.setChoice_B(choice_B);
		if(!choice_C.isEmpty()) nq.setChoice_C(choice_C);
		if(!choice_D.isEmpty()) nq.setChoice_D(choice_D);
		if(answer!=0) nq.setAnswer(answer);
		if(lable!=0) nq.setLable(lable);
		nq.setQuestion_type(1);
		System.out.println(nq.toString());
		teacherService.addquestion(nq);
        return new ModelAndView("tea_addxuanze","tiku_ID",tiku_Id);
	}
	//娣诲姞鍒ゆ柇鍔熻兘
	//create by lcq 2018骞�6鏈�17鏃�16:25:03
	@RequestMapping("/tea_addpanduan_f")
	public ModelAndView tea_addpanduan_f(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int question_Id=teacherService.getMaxquestion(tiku_Id)+1;
		String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String answerr=request.getParameter("answer");
		int answer=0;
		System.out.println(answerr);
		if(answerr.equals("姝ｇ‘")) {
			answer=1;
		}else if(answerr.equals("閿欒")) {
			answer=2;
		}
		System.out.println(answer);
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		Question1 nq=new Question1();
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(answer!=0) nq.setAnswer(answer);
		if(lable!=0) nq.setLable(lable);
		nq.setQuestion_type(2);
		System.out.println(nq.toString());
		teacherService.addquestion(nq);
        return new ModelAndView("tea_addpanduan","tiku_ID",tiku_Id);
	}
	//娣诲姞澶ч鍔熻兘
	//create by lcq 2018骞�6鏈�17鏃�16:25:07
	@RequestMapping("/tea_adddati_f")
	public ModelAndView tea_adddati_f(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int question_Id=teacherService.getMaxquestion(tiku_Id)+1;
		String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		Question1 nq=new Question1();
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(lable!=0) nq.setLable(lable);
		nq.setQuestion_type(3);
		System.out.println(nq.toString());
		teacherService.addquestion(nq);
        return new ModelAndView("tea_adddati","tiku_ID",tiku_Id);
	}
	//鏍规嵁棰樺簱鍙� 棰樺彿鍒犻櫎鏌愪竴璇曢
	//create by lcq 2018骞�6鏈�17鏃�19:16:43
	@RequestMapping("/tea_delquestion")
	public ModelAndView tea_delquestion_f(HttpServletRequest request,Model model)
	{
		String tiku_ID = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_ID);
		String question_Idd= request.getParameter("question_Id");
		int question_Id=Integer.parseInt(question_Idd);
		teacherService.delquestion(question_Id, tiku_Id);

		//鏄剧ず鐩稿叧鐨勪唬鐮�
		List<Question1> q=null;
		q=teacherService.quaryQuestionbytikuID(tiku_Id);
		System.out.println(q.size());
		model.addAttribute("question", q);
    	return new ModelAndView("tea_dangetikuguanli","tiku_ID",tiku_Id);
	}
	//璺宠浆鍒板鍏ラ搴撶晫闈�
	//create by lcq 2018骞�6鏈�17鏃�19:24:00
	@RequestMapping("/tea_daorutiku")
	public ModelAndView tea_daorutiku(HttpServletRequest request,Model model)
	{
		String tiku_ID = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_ID);
		return new ModelAndView("tea_daorutiku","tiku_ID",tiku_Id);
	}
	@RequestMapping(value = "/tea_daorutiku_f", method = RequestMethod.POST)
    public ModelAndView batchimport(@RequestParam(value="filename") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response) throws IOException{
        
		String tiku_ID = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_ID);
        //鍒ゆ柇鏂囦欢鏄惁涓虹┖
        if(file==null) return null;
        //鑾峰彇鏂囦欢鍚�
        String name=file.getOriginalFilename();
        //杩涗竴姝ュ垽鏂枃浠舵槸鍚︿负绌猴紙鍗冲垽鏂叾澶у皬鏄惁涓�0鎴栧叾鍚嶇О鏄惁涓簄ull锛�
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0) return null;
        System.out.println(name);
        //鎵归噺瀵煎叆銆傚弬鏁帮細鏂囦欢鍚嶏紝鏂囦欢銆�
        //涔熷彲浠ョ敤request鑾峰彇涓婁紶鏂囦欢  
        //MultipartFile  fileFile = request.getFile("file"); //杩欓噷鏄〉闈㈢殑name灞炴��   
        //杞崲鎴愯緭鍏ユ祦  
       try { InputStream is = file.getInputStream();  
        //寰楀埌excel  
        Workbook workbook = Workbook.getWorkbook(is);  
        //寰楀埌sheet  
        Sheet sheet = workbook.getSheet(0);  
        //寰楀埌鍒楁暟  
        int colsNum = sheet.getColumns();  
        //寰楀埌琛屾暟  
        int rowsNum = sheet.getRows();  
        //鍗曞厓鏍�  
        Cell cell;  
        Map<Integer, String> map = new HashMap<Integer, String>();  
        for (int i = 1; i < rowsNum; i++) {//鎴戠殑excel绗竴琛屾槸鏍囬,鎵�浠� i浠�1寮�濮�   
        		
        		Question1 q=new Question1();
                
                cell = sheet.getCell(0, i);//get question_conten  
                String str=cell.getContents();
                q.setQuestion_content(str);
                
                cell = sheet.getCell(1, i);//get_type
                str=cell.getContents();
                int question_type=0;
                if(str.equals("1")) {
                	question_type=1;
                }else if(str.equals("2")) {
                	question_type=2;
                }
                else if(str.equals("3")) {
                	question_type=3;
                }
                q.setQuestion_type(question_type);
                
                cell = sheet.getCell(2, i);//get choice_A
                str=cell.getContents();
                q.setChoice_A(str);
                
                cell = sheet.getCell(3, i);//get choice_B
                str=cell.getContents();
                q.setChoice_B(str);
                
                cell = sheet.getCell(4, i);//get choice_C
                str=cell.getContents();
                q.setChoice_C(str);
                
                cell = sheet.getCell(5, i);//get choice_D
                str=cell.getContents();
                q.setChoice_D(str);
                
                cell = sheet.getCell(6, i);//get answer
                str=cell.getContents();
                System.out.println("answer"+str);
                if(str.equals("1")) {
                	q.setAnswer(1);
                }else if(str.equals("2")) {
                	q.setAnswer(2);
                }else if(str.equals("3")) {
                	q.setAnswer(3);
                }else if(str.equals("4")) {
                	q.setAnswer(4);
                }
                
                cell = sheet.getCell(7, i);//get question_level
                str=cell.getContents();
                int lable=0;
                if(str.equals("1")) {
                	lable=1;
                }else if(str.equals("2")) {
                	lable=2;
                }
                else if(str.equals("3")) {
                	lable=3;
                }
                q.setLable(lable);
                q.setTiku_Id(tiku_Id);
                q.setQuestion_Id(teacherService.getMaxquestion(tiku_Id)+1);
                System.out.println(q.toString());
                
                teacherService.addquestion(q);
        }  
        //鍋氫綘闇�瑕佺殑鎿嶄綔  
        System.out.println(map);  
    } catch (IOException e) {  
        e.printStackTrace();  
    } catch (BiffException e) {  
        e.printStackTrace();  
    }  

       return new ModelAndView("tea_daorutiku","tiku_ID",tiku_Id);
    }
	//璺宠浆鍒拌�冭瘯绠＄悊鐣岄潰
	//create by lcq 2018骞�6鏈�17鏃�23:41:38
	@RequestMapping("/tea_kaoshiguanli")
	public ModelAndView tea_kaoshiguanli(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");

		return new ModelAndView("tea_kaoshiguanli","tea_id",tea_id);
	}
	//璺宠浆鍒版煡鐪嬭�冭瘯鐣岄潰
	//create by lcq 2018骞�6鏈�17鏃�23:52:38
	@RequestMapping("/tea_chakankaoshi")
	public ModelAndView tea_chakankaoshi(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<TeaTestInfo> t=null;
		t=teacherService.quaryTestinfobyteaid(tea_id);
		model.addAttribute("teatestinfo",t );
		return new ModelAndView("tea_chakankaoshi","tea_id",tea_id);
	}
	//璺宠浆鍒版坊鍔犺�冭瘯鐣岄潰
	//create by lcq 2018骞�6鏈�17鏃�23:54:09
	@RequestMapping("/tea_addkaoshi")
	public ModelAndView tea_addkaoshi(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<Tikuxinxi> tikuxinxi=null;
		System.out.println("tea_id:"+tea_id);
		tikuxinxi=teacherService.quary(tea_id);
		model.addAttribute("tikuxinxi",tikuxinxi);
		return new ModelAndView("tea_addkaoshi","tea_id",tea_id);
	}
	//鑾峰彇鏁欏笀娣诲姞鑰冭瘯鐨勭浉鍏充俊鎭�
	//create by lcq 2018骞�6鏈�18鏃�10:11:52
	@RequestMapping("/tea_addkaoshi_f")
	public ModelAndView tea_addkaoshi_f(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_name = request.getParameter("test_name");
		String tiku_IDname = request.getParameter("tiku_IDname");String tiku_Idd=tiku_IDname.substring(0, tiku_IDname.indexOf(':'));int tiku_id=tiku_Idd.isEmpty()?0:Integer.parseInt(tiku_Idd);
		String begin_timee = request.getParameter("begin_Time");//Timestamp begin_time= new Timestamp(System.currentTimeMillis());begin_time=Timestamp.valueOf("begin_timee");
		String end_timee = request.getParameter("end_Time");//Timestamp end_time= new Timestamp(System.currentTimeMillis());end_time.valueOf("end_timee");
		TeaTestInfo t=new TeaTestInfo();
		if((!begin_timee.isEmpty())&&(!end_timee.isEmpty()))
		{	
			Date d1 = null;
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begin_timee);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Timestamp begin_time= new Timestamp(d1.getTime());
			
			Date d2 = null;
			try {
				d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end_timee);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("here");
			Timestamp end_time= new Timestamp(d2.getTime());
			t.setBegin_time(begin_time);t.setEnd_time(end_time);
			if(begin_time.compareTo(end_time)<0)
			{
				t.setBegin_time(begin_time);t.setEnd_time(end_time);
			}else {
				String error="璇疯緭鍏ユ纭棩鏈�";	
				List<Tikuxinxi> tikuxinxi=null;
				System.out.println("tea_id:"+tea_id);
				tikuxinxi=teacherService.quary(tea_id);
				model.addAttribute("tikuxinxi",tikuxinxi);
				model.addAttribute("error", error);
				return new ModelAndView("tea_addkaoshi","tea_id",tea_id);
			}

		}else {
			String error="璇疯緭鍏ユ纭棩鏈�";	
			List<Tikuxinxi> tikuxinxi=null;
			System.out.println("tea_id:"+tea_id);
			tikuxinxi=teacherService.quary(tea_id);
			model.addAttribute("tikuxinxi",tikuxinxi);
			model.addAttribute("error", error);
			return new ModelAndView("tea_addkaoshi","tea_id",tea_id);
		}
		String time_longg = request.getParameter("time_long");int time_long=time_longg.isEmpty()?0:Integer.parseInt(time_longg);
		String dx_scoree = request.getParameter("dx_score");int dx_score=dx_scoree.isEmpty()?0:Integer.parseInt(dx_scoree);
		String dx_easyy = request.getParameter("dx_easy");int dx_easy=dx_easyy.isEmpty()?0:Integer.parseInt(dx_easyy);
		String dx_mediumm = request.getParameter("dx_medium");int dx_medium=dx_mediumm.isEmpty()?0:Integer.parseInt(dx_mediumm);
		String dx_hardd = request.getParameter("dx_hard");int dx_hard=dx_hardd.isEmpty()?0:Integer.parseInt(dx_hardd);
		String pd_scoree = request.getParameter("pd_score");int pd_score=pd_scoree.isEmpty()?0:Integer.parseInt(pd_scoree);
		String pd_easyy = request.getParameter("pd_easy");int pd_easy=pd_easyy.isEmpty()?0:Integer.parseInt(pd_easyy);
		String pd_mediumm = request.getParameter("pd_medium");int pd_medium=pd_mediumm.isEmpty()?0:Integer.parseInt(pd_mediumm);
		String pd_hardd = request.getParameter("pd_hard");int pd_hard=pd_hardd.isEmpty()?0:Integer.parseInt(pd_hardd);
		String dt_scoree = request.getParameter("dt_score");int dt_score=dt_scoree.isEmpty()?0:Integer.parseInt(dt_scoree);
		String dt_easyy = request.getParameter("dt_easy");int dt_easy=dt_easyy.isEmpty()?0:Integer.parseInt(dt_easyy);
		String dt_mediumm = request.getParameter("dt_medium");int dt_medium=dt_mediumm.isEmpty()?0:Integer.parseInt(dt_mediumm);
		String dt_hardd = request.getParameter("dt_hard");int dt_hard=dt_hardd.isEmpty()?0:Integer.parseInt(dt_hardd);
		//搴旇鍏堝垽鏂槸鍚︽弧瓒虫坊鍔犵殑鏉′欢
		int test_id=teacherService.getMaxtestid()+1;
		
		t.setTea_id(tea_id);t.setTest_id(test_id);t.setTest_name(test_name);t.setTiku_id(tiku_id);
		t.setTime_long(time_long);
		t.setDx_easy(dx_easy);t.setDx_medium(dx_medium);t.setDx_hard(dx_hard);t.setDx_score(dx_score);
		t.setPd_easy(pd_easy);t.setPd_medium(pd_medium);t.setPd_hard(pd_hard);t.setPd_score(pd_score);
		t.setDt_easy(dt_easy);t.setDt_medium(dt_medium);t.setDt_hard(dt_hard);t.setDt_score(dt_score);
		System.out.println(t.toString());
		int score=pd_score*(pd_easy+pd_medium+pd_hard)+dx_score*(dx_easy+dx_medium+dx_hard)+dt_score*(dt_easy+dt_medium+dt_hard);
		String error="璇风‘璁よ瘯鍗风殑鎬诲垎鏄惁涓�100鍒�";
		if(teacherService.judgetikuNum(tiku_id, t))
		{
			if(score==100)
			{
				teacherService.addteatestinfo(t);
				System.out.println(t.toString());
				error="娣诲姞鎴愬姛锛屽彲缁х画娣诲姞";
				model.addAttribute("teatestinfo", t);
				model.addAttribute("error", error);
				model.addAttribute("test_id", test_id);
				List<Tikuxinxi> tikuxinxi=null;
				System.out.println("tea_id:"+tea_id);
				tikuxinxi=teacherService.quary(tea_id);
				model.addAttribute("tikuxinxi",tikuxinxi);
				return new ModelAndView("tea_dangekaoshiguanli","tea_id",tea_id);
			}else {
				error="璇风‘璁よ瘯鍗风殑鎬诲垎鏄惁涓�100鍒�";		
			}

		}else {
			error="璇风‘璁よ瘯棰樼洰涓暟婊¤冻瑕佹眰";		
		}
		List<Tikuxinxi> tikuxinxi=null;
		System.out.println("tea_id:"+tea_id);
		tikuxinxi=teacherService.quary(tea_id);
		model.addAttribute("tikuxinxi",tikuxinxi);
		model.addAttribute("error", error);
		return new ModelAndView("tea_addkaoshi","tea_id",tea_id);
		
		
		/*
		System.out.println("test_name=" + test_name+
				" tiku_IDname=" + tiku_IDname+
				" begin_time=" + begin_time+
				" end_time=" + end_time+
				" time_long=" + time_long+
				" dx_score=" + dx_score+
				" dx_easy=" + dx_easy+
				" dx_medium=" + dx_medium+
				" dx_hard=" + dx_hard+
				" pd_score=" + pd_score+
				" pd_easy=" + pd_easy+
				" pd_medium=" + pd_medium+
				" pd_hard=" + pd_hard+
				" dt_score=" + dt_score+
				" dt_easy=" + dt_easy+
				" dt_medium=" + dt_medium+
				" dt_hard="+dt_hard);
				*/
		//return new ModelAndView("tea_addkaoshi","tea_id",tea_id);
	}
	//璺宠浆鍒板崟涓�冭瘯淇℃伅淇敼鐣岄潰
	//create by lcq 2018骞�6鏈�18鏃�19:13:28
	@RequestMapping("/tea_dangekaoshiguanli")
	public ModelAndView tea_dangekaoshiguanli(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		List<TeaTestInfo> t=null;
		System.out.println(test_id);
		t=teacherService.quaryTestinfobytestid(test_id);
		TeaTestInfo nt=new TeaTestInfo();
		System.out.println("size="+t.size());
		if(t.size()!=0)
		{
			System.out.println(t.get(0));
			nt=t.get(0);
		}
		System.out.println(nt.toString());
		model.addAttribute("teatestinfo", nt);
		
		//鏄剧ず寮�濮嬫椂闂�
		Timestamp ts = nt.getBegin_time();   
        String tsStr = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");   
        try {   
            //鏂规硶涓�   
            tsStr = sdf.format(ts);   
            System.out.println(tsStr);   
            //鏂规硶浜�   
            tsStr = ts.toString();   
            System.out.println(tsStr);   
        } catch (Exception e) {   
            e.printStackTrace();   
        }  
		String temp = tsStr;
		System.out.println(temp);
		String temp1=temp.substring(0,10);
		temp=temp.substring(0,temp.length()-5);
		String temp2=temp.substring(temp.length()-5);
		System.out.println(temp1);
		System.out.println(temp2);
		String begin_timee=temp1+"T"+temp2;
		System.out.println(begin_timee);
		model.addAttribute("begin_timee", begin_timee);
		
		//鏄剧ず缁撴潫鏃堕棿
		ts = nt.getEnd_time();   
        tsStr = "";   
        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");   
        try {   
            //鏂规硶涓�   
            tsStr = sdf.format(ts);   
            System.out.println(tsStr);   
            //鏂规硶浜�   
            tsStr = ts.toString();   
            System.out.println(tsStr);   
        } catch (Exception e) {   
            e.printStackTrace();   
        }  
		temp = tsStr;
		System.out.println(temp);
		temp1=temp.substring(0,10);
		temp=temp.substring(0,temp.length()-5);
		temp2=temp.substring(temp.length()-5);
		System.out.println(temp1);
		System.out.println(temp2);
		String end_timee=temp1+"T"+temp2;
		System.out.println(end_timee);
		model.addAttribute("end_timee", end_timee);
		
		
		String error="";
		model.addAttribute("error", error);
		model.addAttribute("test_id", test_id);
		List<Tikuxinxi> tikuxinxi=null;
		System.out.println("tea_id:"+tea_id);
		tikuxinxi=teacherService.quary(tea_id);
		model.addAttribute("tikuxinxi",tikuxinxi);
		return new ModelAndView("tea_dangekaoshiguanli","tea_id",tea_id);
	}
	//淇敼鍏蜂綋鐨勬煇涓�涓瘯棰樼殑鍔ㄤ綔
	//create by lcq 2018骞�6鏈�18鏃�21:09:00 tea_dangekaoshiguanli_f
	@RequestMapping("/tea_dangekaoshiguanli_f")
	public ModelAndView tea_dangekaoshiguanli_f(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		List<TeaTestInfo> t=null;
		System.out.println(test_id);
		t=teacherService.quaryTestinfobytestid(test_id);
		TeaTestInfo nt=new TeaTestInfo();
		System.out.println("size="+t.size());
		if(t.size()!=0)
		{
			System.out.println(t.get(0));
			nt=t.get(0);
		}
		System.out.println(nt.toString());
		
		String test_name = request.getParameter("test_name");
		String tiku_IDname = request.getParameter("tiku_IDname");String tiku_Idd=tiku_IDname.substring(0, tiku_IDname.indexOf(':'));int tiku_id=tiku_Idd.isEmpty()?0:Integer.parseInt(tiku_Idd);
		//鑾峰彇寮�濮嬫棩鏈�
		String begin_timee = request.getParameter("begin_Time");
		/*String temp = request.getParameter("begin_time");//Timestamp begin_time= new Timestamp(System.currentTimeMillis());begin_time=Timestamp.valueOf("begin_timee");
		model.addAttribute("begin_timee", temp);
		System.out.println(temp);
		String temp1=temp.substring(0,10);
		String temp2=temp.substring(temp.length()-5);
		System.out.println(temp1);
		System.out.println(temp2);
		String begin_timee=temp1+" "+temp2+":00";
		
		System.out.println("2"+begin_timee);
		*/
		Date d1 = null;
		if(!begin_timee.isEmpty()) {
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begin_timee);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Timestamp begin_time= new Timestamp(d1.getTime());
			nt.setBegin_time(begin_time);
		}
		System.out.println(nt.getBegin_time());
		
		
		//鑾峰彇缁撴潫鏃ユ湡
		String end_timee = request.getParameter("end_Time");
		/*temp = request.getParameter("end_time");//Timestamp end_time= new Timestamp(System.currentTimeMillis());end_time.valueOf("end_timee");
		model.addAttribute("end_timee", temp);
		temp1=temp.substring(0,10);
		temp2=temp.substring(temp.length()-5);
		System.out.println(temp1);
		System.out.println(temp2);
		String end_timee=temp1+" "+temp2+":00";
		*/
		Date d2 = null;
		if(!end_timee.isEmpty()) {
			try {
				d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end_timee);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Timestamp end_time= new Timestamp(d2.getTime());
			nt.setEnd_time(end_time);
		}
		
		
		String time_longg = request.getParameter("time_long");int time_long=time_longg.isEmpty()?0:Integer.parseInt(time_longg);
		String dx_scoree = request.getParameter("dx_score");int dx_score=dx_scoree.isEmpty()?0:Integer.parseInt(dx_scoree);
		String dx_easyy = request.getParameter("dx_easy");int dx_easy=dx_easyy.isEmpty()?0:Integer.parseInt(dx_easyy);
		String dx_mediumm = request.getParameter("dx_medium");int dx_medium=dx_mediumm.isEmpty()?0:Integer.parseInt(dx_mediumm);
		String dx_hardd = request.getParameter("dx_hard");int dx_hard=dx_hardd.isEmpty()?0:Integer.parseInt(dx_hardd);
		String pd_scoree = request.getParameter("pd_score");int pd_score=pd_scoree.isEmpty()?0:Integer.parseInt(pd_scoree);
		String pd_easyy = request.getParameter("pd_easy");int pd_easy=pd_easyy.isEmpty()?0:Integer.parseInt(pd_easyy);
		String pd_mediumm = request.getParameter("pd_medium");int pd_medium=pd_mediumm.isEmpty()?0:Integer.parseInt(pd_mediumm);
		String pd_hardd = request.getParameter("pd_hard");int pd_hard=pd_hardd.isEmpty()?0:Integer.parseInt(pd_hardd);
		String dt_scoree = request.getParameter("dt_score");int dt_score=dt_scoree.isEmpty()?0:Integer.parseInt(dt_scoree);
		String dt_easyy = request.getParameter("dt_easy");int dt_easy=dt_easyy.isEmpty()?0:Integer.parseInt(dt_easyy);
		String dt_mediumm = request.getParameter("dt_medium");int dt_medium=dt_mediumm.isEmpty()?0:Integer.parseInt(dt_mediumm);
		String dt_hardd = request.getParameter("dt_hard");int dt_hard=dt_hardd.isEmpty()?0:Integer.parseInt(dt_hardd);
		System.out.println("dx_hard="+dx_hard);
		if(!test_name.isEmpty()) nt.setTest_name(test_name);
		if(tiku_id!=nt.getTiku_id()) nt.setTiku_id(tiku_id);
		//if(!begin_timee.isEmpty()) nt.setBegin_time(begin_time);
		//if(!end_timee.isEmpty()) nt.setEnd_time(end_time);
		if(!time_longg.isEmpty()) nt.setTime_long(time_long);
		if(!dx_scoree.isEmpty()) nt.setDx_score(dx_score);
		if(!dx_easyy.isEmpty()) nt.setDx_easy(dx_easy);
		if(!dx_mediumm.isEmpty()) nt.setDx_medium(dx_medium);
		if(!dx_hardd.isEmpty()) nt.setDx_hard(dx_hard);
		if(!pd_scoree.isEmpty()) nt.setPd_score(pd_score);
		if(!pd_easyy.isEmpty()) nt.setPd_easy(pd_easy);
		if(!pd_mediumm.isEmpty()) nt.setPd_medium(pd_medium);
		if(!pd_hardd.isEmpty()) nt.setPd_hard(pd_hard);		
		if(!dt_scoree.isEmpty()) nt.setDt_score(dt_score);
		if(!dt_easyy.isEmpty()) nt.setDt_easy(dt_easy);
		if(!dt_mediumm.isEmpty()) nt.setDt_medium(dt_medium);
		if(!dt_hardd.isEmpty()) nt.setDt_hard(dt_hard);		
		System.out.println(nt);
		
		int score=nt.getPd_score()*(nt.getPd_easy()+nt.getPd_medium()+nt.getPd_hard())+
				nt.getDx_score()*(nt.getDx_easy()+nt.getDx_medium()+nt.getDx_hard())+nt.getDt_score()*(nt.getDt_easy()+nt.getDt_medium()+nt.getDt_hard());
		String error="璇风‘璁よ瘯鍗风殑鎬诲垎鏄惁涓�100鍒�";
		if(teacherService.judgetikuNum(tiku_id, nt))
		{
			if(score==100)
			{
				teacherService.updateteatestinfobyone(nt);
				error="淇敼鎴愬姛";
				model.addAttribute("teatestinfo", nt);
				model.addAttribute("error", error);
				model.addAttribute("test_id", test_id);
				List<Tikuxinxi> tikuxinxi=null;
				System.out.println("tea_id:"+tea_id);
				tikuxinxi=teacherService.quary(tea_id);
				model.addAttribute("tikuxinxi",tikuxinxi);
				return new ModelAndView("tea_dangekaoshiguanli","tea_id",tea_id);
			}else {
				error="璇风‘璁よ瘯鍗风殑鎬诲垎鏄惁涓�100鍒�";		
			}

		}else {
			error="璇风‘璁よ瘯棰樼洰涓暟婊¤冻瑕佹眰";		
		}

		model.addAttribute("error", error);
		//teacherService.updateteatestinfobyone(nt);
		t=null;
		t=teacherService.quaryTestinfobytestid(test_id);
		
		TeaTestInfo tt=new TeaTestInfo();
		System.out.println("size="+t.size());
		if(t.size()!=0)
		{
			System.out.println(t.get(0));
			tt=t.get(0);
		}
		model.addAttribute("teatestinfo", tt);
		
		System.out.println("tt:"+tt.toString());
		model.addAttribute("test_id", test_id);
		List<Tikuxinxi> tikuxinxi=null;
		System.out.println("tea_id:"+tea_id);
		tikuxinxi=teacherService.quary(tea_id);
		model.addAttribute("tikuxinxi",tikuxinxi);
		return new ModelAndView("tea_dangekaoshiguanli","tea_id",tea_id);
	}
	//鐢ㄦ潵娴嬭瘯
	
	@RequestMapping("/ttt")
	public String ttt(HttpServletRequest request)
	{
		teacherService.getkaoshi(10, 2);
		return "hellow";
	}
	//鑰佸笀杩涜鎵规敼 棣栧厛杩涘叆鏌ョ湅鑰冭瘯淇℃伅鐨勭晫闈�
	//create by lcq 2018骞�6鏈�19鏃�10:42:49
	@RequestMapping("/tea_pigai_kaoshi")
	public String tea_pigai_kaoshi(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		Timestamp today = new Timestamp(System.currentTimeMillis()); 
		List<TeaTestInfo> overt=null;
		overt=teacherService.quaryOverbyteaiddate(tea_id, today);
		List<TeaTestInfo> novert=null;
		novert=teacherService.quaryNOverbyteaiddate(tea_id, today);
		model.addAttribute("overt", overt);
		model.addAttribute("novert", novert);
		return "tea_pigai_kaoshi";
	}
	//鑰佸笀鏍规嵁鏌愪釜鑰冭瘯杩涜鎵规敼 姝ょ晫闈㈢敤鏉ユ樉绀哄鐢熼棶棰� 绛旀绛変俊鎭� 鍚庨潰鏄搷浣�
	//create by lcq 2018骞�6鏈�19鏃�14:48:25
	@RequestMapping("/tea_pigai_stugn")
	public String tea_pigai_stugn(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		Timestamp today = new Timestamp(System.currentTimeMillis()); 
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		System.out.println(test_id);
		List<Shijuanzhuguan> shijuanzhuguan =null;
		shijuanzhuguan=teacherService.quaryBytestid(test_id);
		for(int i=0;i<shijuanzhuguan.size();i++)
		{
			System.out.println(shijuanzhuguan.get(i).getScore());
		}
		model.addAttribute("shijuanzhuguan", shijuanzhuguan);
		return "tea_pigai_stugn";
	}
	//杩涘叆鍒板叿浣撶殑鍒ゅ嵎椤甸潰
	//create by lcq 2018骞�6鏈�19鏃�16:53:43
	@RequestMapping("/tea_pigai_juti")
	public String tea_pigai_juti(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		Timestamp today = new Timestamp(System.currentTimeMillis()); 
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		String question_idd = request.getParameter("question_id");
		int question_id=question_idd.isEmpty()?0:Integer.parseInt(question_idd);
		String stu_idd = request.getParameter("stu_id");
		int stu_id=test_idd.isEmpty()?0:Integer.parseInt(stu_idd);
		String tiku_idd = request.getParameter("tiku_id");
		int tiku_id=test_idd.isEmpty()?0:Integer.parseInt(tiku_idd);
		Shijuanzhuguan ns=new Shijuanzhuguan();
		ns=teacherService.getBytestidstuidquestionidtikuid(test_id, stu_id, question_id, tiku_id);
		model.addAttribute("sjzg", ns);
		
		return "tea_pigai_juti";
	}
	//鍏蜂綋鎵规敼鐨勫姩浣�
	//create by lcq 2018骞�6鏈�19鏃�21:04:21
	@RequestMapping("/tea_pigai_juti_f")
	public String tea_pigai_juti_f(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		Timestamp today = new Timestamp(System.currentTimeMillis()); 
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		String question_idd = request.getParameter("question_id");
		int question_id=question_idd.isEmpty()?0:Integer.parseInt(question_idd);
		String stu_idd = request.getParameter("stu_id");
		int stu_id=test_idd.isEmpty()?0:Integer.parseInt(stu_idd);
		String tiku_idd = request.getParameter("tiku_id");
		int tiku_id=test_idd.isEmpty()?0:Integer.parseInt(tiku_idd);
		String scoree = request.getParameter("score");
		int score=scoree.isEmpty()?0:Integer.parseInt(scoree);
		System.out.println("score="+score);
		
		Shijuanzhuguan ns=new Shijuanzhuguan();
		ns=teacherService.getBytestidstuidquestionidtikuid(test_id, stu_id, question_id, tiku_id);
		ns.setScore(score);
		ns.setZgstate(1);
		teacherService.updatebysjzg(ns);
		if(teacherService.getBytestidstuidtikuid_wpg(test_id, stu_id, tiku_id)==false)
		{
			teacherService.updateStutestinfo(stu_id, test_id, tiku_id);
		}
		
		//model.addAttribute("sjzg", ns);
		ns=new Shijuanzhuguan();
		ns=teacherService.getBytestidstuidquestionidtikuid(test_id, stu_id, question_id, tiku_id);
		ns=teacherService.getBytestidstuidquestionidtikuid_n(test_id, stu_id, question_id, tiku_id, ns);
		
		model.addAttribute("sjzg", ns);
		
		return "tea_pigai_juti";
	}
	//鍓嶄竴涓壒鏀逛俊鎭�
	//create by lcq 2018骞�6鏈�19鏃�23:33:39
	@RequestMapping("/tea_pigai_juti_fp")
	public String tea_pigai_juti_fp(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		Timestamp today = new Timestamp(System.currentTimeMillis()); 
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		String question_idd = request.getParameter("question_id");
		int question_id=question_idd.isEmpty()?0:Integer.parseInt(question_idd);
		String stu_idd = request.getParameter("stu_id");
		int stu_id=test_idd.isEmpty()?0:Integer.parseInt(stu_idd);
		String tiku_idd = request.getParameter("tiku_id");
		int tiku_id=test_idd.isEmpty()?0:Integer.parseInt(tiku_idd);
		
		Shijuanzhuguan ns=new Shijuanzhuguan();
		ns=teacherService.getBytestidstuidquestionidtikuid(test_id, stu_id, question_id, tiku_id);
		ns=teacherService.getBytestidstuidquestionidtikuid_p(test_id, stu_id, question_id, tiku_id, ns);
		
		model.addAttribute("sjzg", ns);
		
		return "tea_pigai_juti";
	}
	//鍚庝竴涓壒鏀逛俊鎭�
	//create by lcq 2018骞�6鏈�19鏃�21:04:21
	@RequestMapping("/tea_pigai_juti_fn")
	public String tea_pigai_juti_fn(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		Timestamp today = new Timestamp(System.currentTimeMillis()); 
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		String question_idd = request.getParameter("question_id");
		int question_id=question_idd.isEmpty()?0:Integer.parseInt(question_idd);
		String stu_idd = request.getParameter("stu_id");
		int stu_id=test_idd.isEmpty()?0:Integer.parseInt(stu_idd);
		String tiku_idd = request.getParameter("tiku_id");
		int tiku_id=test_idd.isEmpty()?0:Integer.parseInt(tiku_idd);
		
		Shijuanzhuguan ns=new Shijuanzhuguan();
		ns=teacherService.getBytestidstuidquestionidtikuid(test_id, stu_id, question_id, tiku_id);
		ns=teacherService.getBytestidstuidquestionidtikuid_n(test_id, stu_id, question_id, tiku_id, ns);
		
		model.addAttribute("sjzg", ns);
		
		return "tea_pigai_juti";
	}
	//鏌ョ湅鎴愮哗閭ｉ噷鐨勮�冭瘯淇℃伅
	//create by lcq2018骞�6鏈�20鏃�08:20:55
	@RequestMapping("/tea_chengji_kaoshilist")
	public String tea_chengji_kaoshilist(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		Timestamp today = new Timestamp(System.currentTimeMillis()); 
		List<TeaTestInfo> overt=null;
		overt=teacherService.quaryOverbyteaiddate(tea_id, today);
		List<TeaTestInfo> novert=null;
		novert=teacherService.quaryNOverbyteaiddate(tea_id, today);
		model.addAttribute("overt", overt);
		model.addAttribute("novert", novert);
		return "tea_chengji_kaoshilist";
	}
	//鏌ョ湅鏌愰棬璇惧鐢熷叿浣撶殑鎴愮哗
	//create by lcq 2018骞�6鏈�20鏃�15:17:31
	@RequestMapping("/tea_chengji_testid")
	public String tea_chengji_testid(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		Timestamp today = new Timestamp(System.currentTimeMillis()); 
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		List<tea_cha_chengji> tcj=null;
		tcj=teacherService.queryBytestchachengjibytestid(test_id);
		System.out.println("here");
		for(int i=0;i<tcj.size();i++)
		{
			System.out.println(tcj.get(i).getScore());
		}
		model.addAttribute("tcj", tcj);
		model.addAttribute("test_id", test_id);
		return "tea_chengji_testid";
	}
	//鏌ョ湅鏌愰棬璇剧殑鎴愮哗鍒嗘瀽
	//create by lcq 2018骞�6鏈�20鏃�15:18:02
	@RequestMapping("/tea_chengji_fenxi")
	public String tea_chengji_fenxi(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		Timestamp today = new Timestamp(System.currentTimeMillis()); 
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		Teacjfenxi cjfx=new Teacjfenxi();
		cjfx=teacherService.getteacjfenxi(test_id);
		model.addAttribute("cjfx", cjfx);
		model.addAttribute("test_id", test_id);
		return "tea_chengji_fenxi";
	}
	
	//鏁欏笀淇敼涓汉淇℃伅
	//create by lcq 2018骞�6鏈�20鏃�19:32:09
	@RequestMapping("/tea_update_password")
	public ModelAndView tea_update_password(HttpServletRequest request,Model model)
	{
		HttpSession session=request.getSession();
		int tea_Id=(int)session.getAttribute("sessiontea_id");
		String old_password=request.getParameter("old_password");
		String new_password1=request.getParameter("new_password1");
		String new_password2=request.getParameter("new_password2");
		System.out.println(tea_Id);System.out.println(old_password);
		if(!teacherService.login(tea_Id, old_password)){
			String error="鍘熷瘑鐮佽緭鍏ラ敊璇紝璇烽噸鏂拌緭鍏ワ紒";
			System.out.println("here");
			return new ModelAndView("tea_info","error",error);
		}
		if(!new_password1.equals(new_password2)){
			String error="涓ゆ鏂板瘑鐮佷笉鍚岋紝璇烽噸鏂拌緭鍏�";
			return new ModelAndView("tea_info","error",error);
		}
		//灏嗘柊瀵嗙爜鍐欏埌鏁版嵁搴�
		teacherService.update_tea_password(tea_Id, new_password1);
		
		//鏇存柊鎴愬姛
		return new ModelAndView("tea_update_password_success");
	}
	
	//鏁欏笀瀵逛簬鏌愪釜鑰冭瘯缂栧彿瀵瑰簲鐨勮�冭瘯 鏌ョ湅鍙傚姞鑰冭瘯鐨勫鐢熷悕鍗�
	//create by lcq 2018骞�6鏈�20鏃�20:22:12
	@RequestMapping("/tea_jtkaoshixuesheng")
	public ModelAndView tea_jtkaoshixuesheng(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		List<Student> sl=null;
		sl=teacherService.queryAllbytestid(test_id);
		model.addAttribute("sl", sl);
		return new ModelAndView("tea_jtkaoshixuesheng","test_id",test_id);
	}
	
	//鏁欏笀瀵逛簬鏌愪釜鑰冭瘯缂栧彿 璺宠浆鍒板搴旂殑瀵煎叆瀛︾敓淇℃伅鐣岄潰
	//create by lcq 2018骞�6鏈�20鏃�20:36:12
	@RequestMapping("/tea_jtkaoshixueshengdaoru")
	public ModelAndView tea_jtkaoshixueshengdaoru(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);

		return new ModelAndView("tea_jtkaoshixueshengdaoru","test_id",test_id);
	}
	
	//瀵煎叆瀛︾敓鐨勫姩浣�
	@RequestMapping(value = "/tea_jtkaoshixueshengdaoru_f", method = RequestMethod.POST)
    public ModelAndView tea_jtkaoshixueshengdaoru_f(@RequestParam(value="filename") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
        
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
        //鍒ゆ柇鏂囦欢鏄惁涓虹┖
        if(file==null) return null;
        //鑾峰彇鏂囦欢鍚�
        String name=file.getOriginalFilename();
        //杩涗竴姝ュ垽鏂枃浠舵槸鍚︿负绌猴紙鍗冲垽鏂叾澶у皬鏄惁涓�0鎴栧叾鍚嶇О鏄惁涓簄ull锛�
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0) return null;
        System.out.println(name);
        //鎵归噺瀵煎叆銆傚弬鏁帮細鏂囦欢鍚嶏紝鏂囦欢銆�
        //涔熷彲浠ョ敤request鑾峰彇涓婁紶鏂囦欢  
        //MultipartFile  fileFile = request.getFile("file"); //杩欓噷鏄〉闈㈢殑name灞炴��   
        //杞崲鎴愯緭鍏ユ祦  
       try { InputStream is = file.getInputStream();  
        //寰楀埌excel  
        Workbook workbook = Workbook.getWorkbook(is);  
        //寰楀埌sheet  
        Sheet sheet = workbook.getSheet(0);  
        //寰楀埌鍒楁暟  
        int colsNum = sheet.getColumns();  
        //寰楀埌琛屾暟  
        int rowsNum = sheet.getRows();  
        //鍗曞厓鏍�  
        Cell cell;  
        Map<Integer, String> map = new HashMap<Integer, String>();  
        for (int i = 1; i < rowsNum; i++) {//鎴戠殑excel绗竴琛屾槸鏍囬,鎵�浠� i浠�1寮�濮�   
        		
        		Question1 q=new Question1();
                cell=sheet.getCell(0, i); //鑾峰彇绗竴琛� 绗�0鍒楃殑鍏冪礌
                String str=cell.getContents();
                int stu_id=str.isEmpty()?0:Integer.parseInt(str);
                
                cell = sheet.getCell(1, i);//get question_conten  
                str=cell.getContents();
                String stu_name=str;
                if(stu_id!=0 && (!stu_name.isEmpty()))
                {
                	System.out.println("id="+stu_id+" name="+stu_name);
                	Stutestinfo sti=new Stutestinfo();
                	sti.setStu_Id(stu_id);sti.setTest_Id(test_id);
                	
                	Student stu=new Student();
                	stu.setId(stu_id);stu.setPassword(""+stu_id);stu.setStu_name(stu_name);

                	if(!teacherService.quarySti(test_id, stu_id))
                	{
                		teacherService.addStutestinfo(sti);
                	}
                	if(!teacherService.quaryStu(stu_id))
                	{
                		teacherService.addStudent(stu);
                	}
                	
                }
                
        }  
        //鍋氫綘闇�瑕佺殑鎿嶄綔  
    } catch (IOException e) {  
        e.printStackTrace();  
    } catch (BiffException e) {  
        e.printStackTrace();  
    }  
       String error="鎴愬姛";
       model.addAttribute("error", error);
       return new ModelAndView("tea_jtkaoshixueshengdaoru","test_id",test_id);
    }
	
	//璺宠浆鍒扮瓟鐤戝垪琛�
	//crate by lcq 2018骞�6鏈�21鏃�19:09:06
	@RequestMapping("/tea_consult_list")
	public ModelAndView tea_consult_list(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<Teaconsult> consult=null;
		consult=teacherService.quaryAll(tea_id);
		System.out.print("consult num:"+consult.size());
		model.addAttribute("consult", consult);
		return new ModelAndView("tea_consult_list","tea_id",tea_id);
	}
	//璺宠浆鍒板皻鏈瓟鐤戝垪琛�
	//crate by lcq 2018骞�6鏈�21鏃�19:09:06
	@RequestMapping("/tea_consult_wait")
	public ModelAndView tea_consult_wait(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<Teaconsult> consult=null;
		consult=teacherService.quaryWait(tea_id);
		model.addAttribute("consult", consult);
		return new ModelAndView("tea_consult_wait","tea_id",tea_id);
	}
	//璺宠浆鍒板凡缁忕瓟鐤戝垪琛�
	//crate by lcq 2018骞�6鏈�21鏃�19:09:06
	@RequestMapping("/tea_consult_over")
	public ModelAndView tea_consult_over(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<Teaconsult> consult=null;
		consult=teacherService.quaryOver(tea_id);
		model.addAttribute("consult", consult);
		return new ModelAndView("tea_consult_over","tea_id",tea_id);
	}
	//璺宠浆鍒板洖澶嶇晫闈�
	//create by lcq 2018骞�6鏈�22鏃�00:31:30
	@RequestMapping("/tea_consult_dg")
	public ModelAndView tea_consult_dg(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		String stu_idd = request.getParameter("stu_id");
		int stu_id=test_idd.isEmpty()?0:Integer.parseInt(stu_idd);
		System.out.println("stu_id"+stu_id+" test_id="+test_id);
		model.addAttribute("test_id", test_id);
		model.addAttribute("stu_id", stu_id);
		return new ModelAndView("tea_consult_dg","tea_id",tea_id);
	}
	//璺宠浆鍒板洖澶嶇晫闈�
	//create by lcq 2018骞�6鏈�22鏃�00:46:35
	@RequestMapping("/tea_consult_f")
	public ModelAndView tea_consult_f(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		String stu_idd = request.getParameter("stu_id");
		int stu_id=test_idd.isEmpty()?0:Integer.parseInt(stu_idd);
		System.out.println("stu_id"+stu_id+" test_id="+test_id);
		String answer = request.getParameter("answer");
		System.out.println(answer);
		teacherService.updateConsult(stu_id, test_id, answer);
		List<Teaconsult> consult=null;
		consult=teacherService.quaryAll(tea_id);
		System.out.print("consult num:"+consult.size());
		model.addAttribute("consult", consult);
		return new ModelAndView("tea_consult_list","tea_id",tea_id);
	}
	//鑾峰彇鐩稿簲棰樺簱瀵瑰簲鐨勮�冭瘯鏁版嵁闄愬埗
	//create by lcq 2018骞�6鏈�21鏃�20:56:19
	@RequestMapping("/tea_kaoshinum")
	public ModelAndView tea_kaoshinum(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<Tikuxinxi> tikuxinxi=null;
		tikuxinxi=teacherService.quary(tea_id);
		List<kaoshinum> kaoshi=new ArrayList<kaoshinum>();;
		for(int i=0;i<tikuxinxi.size();i++)
		{
			Tikuxinxi t=tikuxinxi.get(i);
			kaoshinum k=teacherService.gettikuNum(t.getTiku_ID(), t);
			kaoshi.add(k);
		}
		model.addAttribute("kaoshi", kaoshi);
		return new ModelAndView("tea_kaoshinum","tea_id",tea_id);
	}
	
	//鎶藉彇璇曞嵎鐨勫姩浣�
	//create by lcq 2018骞�6鏈�24鏃�20:27:24
	@RequestMapping("/tea_get_shijuan_f")
	public ModelAndView tea_get_shijuan_f(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<Tikuxinxi> tikuxinxi=null;
		tikuxinxi=teacherService.quary(tea_id);
		List<kaoshinum> kaoshi=new ArrayList<kaoshinum>();;
		for(int i=0;i<tikuxinxi.size();i++)
		{
			Tikuxinxi t=tikuxinxi.get(i);
			kaoshinum k=teacherService.gettikuNum(t.getTiku_ID(), t);
			kaoshi.add(k);
		}
		model.addAttribute("kaoshi", kaoshi);
		
		//鎶藉彇璇曞嵎鐨勫姩浣�
		teacherService.tea_get_shijuan_f(12);
		return new ModelAndView("hellow","tea_id",tea_id);
	}
	//璺宠浆鍒版暀甯堣緭鍏ヨ瘯鍗风浉鍏冲弬鏁扮殑鐣岄潰 骞舵樉绀虹浉鍏崇殑
	//create by lcq 2018骞�6鏈�21鏃�20:56:19
	@RequestMapping("/tea_add_kaoshi_s1")
	public ModelAndView tea_add_kaoshi_s1(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<Tikuxinxi> tikuxinxi=null;
		System.out.println("tea_id:"+tea_id);
		tikuxinxi=teacherService.quary(tea_id);
		model.addAttribute("tikuxinxi",tikuxinxi);
		return new ModelAndView("tea_add_kaoshi_s1","tea_id",tea_id);
	}
	//鑾峰彇鑰冭瘯淇℃伅鐨勭浉鍏冲弬鏁� 骞惰烦杞埌tea_add_kaoshi_s2椤甸潰 
	//create by lcq 2018骞�6鏈�24鏃�21:19:43
	@RequestMapping("/tea_add_kaoshi_s2")
	public ModelAndView tea_add_kaoshi_s1_f(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_name = request.getParameter("test_name");
		String tiku_IDname = request.getParameter("tiku_IDname");String tiku_Idd=tiku_IDname.substring(0, tiku_IDname.indexOf(':'));int tiku_id=tiku_Idd.isEmpty()?0:Integer.parseInt(tiku_Idd);
		String begin_timee = request.getParameter("begin_Time");//Timestamp begin_time= new Timestamp(System.currentTimeMillis());begin_time=Timestamp.valueOf("begin_timee");
		String end_timee = request.getParameter("end_Time");//Timestamp end_time= new Timestamp(System.currentTimeMillis());end_time.valueOf("end_timee");
		TeaTestInfo t=new TeaTestInfo();
		if((!begin_timee.isEmpty())&&(!end_timee.isEmpty()))
		{	
			Date d1 = null;
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begin_timee);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Timestamp begin_time= new Timestamp(d1.getTime());
			
			Date d2 = null;
			try {
				d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end_timee);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("here");
			Timestamp end_time= new Timestamp(d2.getTime());
			t.setBegin_time(begin_time);t.setEnd_time(end_time);
			if(begin_time.compareTo(end_time)<0)
			{
				t.setBegin_time(begin_time);t.setEnd_time(end_time);
			}else {
				String error="璇疯緭鍏ユ纭棩鏈�";	
				List<Tikuxinxi> tikuxinxi=null;
				System.out.println("tea_id:"+tea_id);
				tikuxinxi=teacherService.quary(tea_id);
				model.addAttribute("tikuxinxi",tikuxinxi);
				model.addAttribute("error", error);
				return new ModelAndView("tea_add_kaoshi_s1","tea_id",tea_id);
			}

		}else {
			String error="璇疯緭鍏ユ纭棩鏈�";	
			List<Tikuxinxi> tikuxinxi=null;
			System.out.println("tea_id:"+tea_id);
			tikuxinxi=teacherService.quary(tea_id);
			model.addAttribute("tikuxinxi",tikuxinxi);
			model.addAttribute("error", error);
			return new ModelAndView("tea_add_kaoshi_s1","tea_id",tea_id);
		}
		String time_longg = request.getParameter("time_long");int time_long=time_longg.isEmpty()?0:Integer.parseInt(time_longg);
		System.out.println(test_name);model.addAttribute("test_name", test_name);
		System.out.println(tiku_IDname);model.addAttribute("tiku_IDname", tiku_IDname);
		System.out.println(begin_timee);model.addAttribute("begin_timee", begin_timee);
		System.out.println(end_timee);model.addAttribute("end_timee", end_timee);
		System.out.println(time_longg);model.addAttribute("time_long", time_long);
		
		
		Tikuxinxi t1=new Tikuxinxi();
		t1.setTea_Id(tea_id);
		t1.setTiku_ID(tiku_id);
		kaoshinum k=teacherService.gettikuNum(t1.getTiku_ID(), t1);
		model.addAttribute("kaoshinum",k);
		return new ModelAndView("tea_add_kaoshi_s2","tea_id",tea_id);
	}
	//娣诲姞鑰冭瘯绗簩姝ユ墽琛岀殑鍐呭
	//create by lcq 2018骞�6鏈�25鏃�14:17:33
	@RequestMapping("/tea_add_kaoshi_s2_f")
	public ModelAndView tea_add_kaoshi_s2_f(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		
		String test_name = request.getParameter("test_name");
		System.out.print(test_name);
		String tiku_IDname = request.getParameter("tiku_IDname");
		System.out.print(tiku_IDname);
		String tiku_Idd=tiku_IDname.substring(0, tiku_IDname.indexOf(':'));int tiku_id=tiku_Idd.isEmpty()?0:Integer.parseInt(tiku_Idd);
		System.out.println("hhh");
		String begin_timee = request.getParameter("begin_Time");//Timestamp begin_time= new Timestamp(System.currentTimeMillis());begin_time=Timestamp.valueOf("begin_timee");
		String end_timee = request.getParameter("end_Time");//Timestamp end_time= new Timestamp(System.currentTimeMillis());end_time.valueOf("end_timee");
		TeaTestInfo t=new TeaTestInfo();
		System.out.println("hhh");
		if((!begin_timee.isEmpty())&&(!end_timee.isEmpty()))
		{	
			Date d1 = null;
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begin_timee);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Timestamp begin_time= new Timestamp(d1.getTime());
			
			Date d2 = null;
			try {
				d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end_timee);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("here");
			Timestamp end_time= new Timestamp(d2.getTime());
			t.setBegin_time(begin_time);t.setEnd_time(end_time);
			if(begin_time.compareTo(end_time)<0)
			{
				t.setBegin_time(begin_time);t.setEnd_time(end_time);
			}else {
				String error="璇疯緭鍏ユ纭棩鏈�";	
				List<Tikuxinxi> tikuxinxi=null;
				System.out.println("tea_id:"+tea_id);
				tikuxinxi=teacherService.quary(tea_id);
				model.addAttribute("tikuxinxi",tikuxinxi);
				model.addAttribute("error", error);
				return new ModelAndView("tea_addkaoshi","tea_id",tea_id);
			}

		}else {
			String error="璇疯緭鍏ユ纭棩鏈�";	
			List<Tikuxinxi> tikuxinxi=null;
			System.out.println("tea_id:"+tea_id);
			tikuxinxi=teacherService.quary(tea_id);
			model.addAttribute("tikuxinxi",tikuxinxi);
			model.addAttribute("error", error);
			return new ModelAndView("tea_addkaoshi","tea_id",tea_id);
		}
		String time_longg = request.getParameter("time_long");int time_long=time_longg.isEmpty()?0:Integer.parseInt(time_longg);
		String dx_scoree = request.getParameter("dx_score");int dx_score=dx_scoree.isEmpty()?0:Integer.parseInt(dx_scoree);
		String dx_easyy = request.getParameter("dx_easy");int dx_easy=dx_easyy.isEmpty()?0:Integer.parseInt(dx_easyy);
		String dx_mediumm = request.getParameter("dx_medium");int dx_medium=dx_mediumm.isEmpty()?0:Integer.parseInt(dx_mediumm);
		String dx_hardd = request.getParameter("dx_hard");int dx_hard=dx_hardd.isEmpty()?0:Integer.parseInt(dx_hardd);
		String pd_scoree = request.getParameter("pd_score");int pd_score=pd_scoree.isEmpty()?0:Integer.parseInt(pd_scoree);
		String pd_easyy = request.getParameter("pd_easy");int pd_easy=pd_easyy.isEmpty()?0:Integer.parseInt(pd_easyy);
		String pd_mediumm = request.getParameter("pd_medium");int pd_medium=pd_mediumm.isEmpty()?0:Integer.parseInt(pd_mediumm);
		String pd_hardd = request.getParameter("pd_hard");int pd_hard=pd_hardd.isEmpty()?0:Integer.parseInt(pd_hardd);
		String dt_scoree = request.getParameter("dt_score");int dt_score=dt_scoree.isEmpty()?0:Integer.parseInt(dt_scoree);
		String dt_easyy = request.getParameter("dt_easy");int dt_easy=dt_easyy.isEmpty()?0:Integer.parseInt(dt_easyy);
		String dt_mediumm = request.getParameter("dt_medium");int dt_medium=dt_mediumm.isEmpty()?0:Integer.parseInt(dt_mediumm);
		String dt_hardd = request.getParameter("dt_hard");int dt_hard=dt_hardd.isEmpty()?0:Integer.parseInt(dt_hardd);
		//搴旇鍏堝垽鏂槸鍚︽弧瓒虫坊鍔犵殑鏉′欢
		int test_id=teacherService.getMaxtestid()+1;
		
		t.setTea_id(tea_id);t.setTest_id(test_id);t.setTest_name(test_name);t.setTiku_id(tiku_id);
		t.setTime_long(time_long);
		t.setDx_easy(dx_easy);t.setDx_medium(dx_medium);t.setDx_hard(dx_hard);t.setDx_score(dx_score);
		t.setPd_easy(pd_easy);t.setPd_medium(pd_medium);t.setPd_hard(pd_hard);t.setPd_score(pd_score);
		t.setDt_easy(dt_easy);t.setDt_medium(dt_medium);t.setDt_hard(dt_hard);t.setDt_score(dt_score);
		System.out.println(t.toString());
		int score=pd_score*(pd_easy+pd_medium+pd_hard)+dx_score*(dx_easy+dx_medium+dx_hard)+dt_score*(dt_easy+dt_medium+dt_hard);
		System.out.println("score="+score);
		String error="璇风‘璁よ瘯鍗风殑鎬诲垎鏄惁涓�100鍒�";
		if(teacherService.judgetikuNum(tiku_id, t))
		{
			if(score==100)
			{
				teacherService.addteatestinfo(t);
				teacherService.tea_get_shijuan_f(t.getTest_id());
				System.out.println(t.toString());
				error="娣诲姞鎴愬姛锛屽彲缁х画娣诲姞";
				model.addAttribute("teatestinfo", t);
				model.addAttribute("error", error);
				model.addAttribute("test_id", test_id);
				List<Tikuxinxi> tikuxinxi=null;
				System.out.println("tea_id:"+tea_id);
				tikuxinxi=teacherService.quary(tea_id);
				model.addAttribute("tikuxinxi",tikuxinxi);
				List<Question1> q=null;
				//q=teacherService.quaryBytest_id(12);
				q=teacherService.quaryBytest_id(t.getTest_id());
				System.out.println(q.size());
				model.addAttribute("question", q);
				
				return new ModelAndView("tea_add_kaoshi_s3","tea_id",tea_id);
			}else {
				error="璇风‘璁よ瘯鍗风殑鎬诲垎鏄惁涓�100鍒�";		
			}

		}else {
			System.out.println(t.toString());
		}
		List<Tikuxinxi> tikuxinxi=null;
		System.out.println("tea_id:"+tea_id);
		tikuxinxi=teacherService.quary(tea_id);
		model.addAttribute("tikuxinxi",tikuxinxi);
		model.addAttribute("error", error);
		//return new ModelAndView("tea_addkaoshi","tea_id",tea_id);
		System.out.println(test_name);model.addAttribute("test_name", test_name);
		System.out.println(tiku_IDname);model.addAttribute("tiku_IDname", tiku_IDname);
		System.out.println(begin_timee);model.addAttribute("begin_timee", begin_timee);
		System.out.println(end_timee);model.addAttribute("end_timee", end_timee);
		System.out.println(time_longg);model.addAttribute("time_long", time_long);
		System.out.println("dsvdsav");
		
		Tikuxinxi t1=new Tikuxinxi();
		t1.setTea_Id(tea_id);
		t1.setTiku_ID(tiku_id);
		kaoshinum k=teacherService.gettikuNum(t1.getTiku_ID(), t1);
		model.addAttribute("kaoshinum",k);
		return new ModelAndView("tea_add_kaoshi_s2","tea_id",tea_id);
	}
	//鍦ㄦ柊鐗堟坊鍔犺�冭瘯鐨勭涓夋 閲嶆柊鐢熸垚璇曞嵎
	//create by lcq 2018-6-26 16:38:352
	@RequestMapping("/tea_add_kaoshi_s3_r")
	public ModelAndView tea_add_kaoshi_s3_r(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_idd = request.getParameter("test_id");int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		
		teacherService.delateShijuanbytestid(test_id);
		teacherService.tea_get_shijuan_f(test_id);
		//boolean a=teacherService.delateShijuanbytestid(12);
		//System.out.println("del "+a);
		//teacherService.tea_get_shijuan_f(12);
		String error="娣诲姞鎴愬姛";
		model.addAttribute("error", error);
		model.addAttribute("test_id", test_id);
		List<Tikuxinxi> tikuxinxi=null;
		System.out.println("tea_id:"+tea_id);
		tikuxinxi=teacherService.quary(tea_id);
		model.addAttribute("tikuxinxi",tikuxinxi);
		List<Question1> q=null;
		q=teacherService.quaryBytest_id(test_id);
		System.out.println(q.size());
		model.addAttribute("question", q);
		return new ModelAndView("tea_add_kaoshi_s3","tea_id",tea_id);
			
	}
	//鍦ㄦ柊鐗堟坊鍔犺�冭瘯鐨勭鍥涙 鐢熸垚鎴愬姛
	//create by lcq 2018-6-26 16:38:352
	@RequestMapping("/tea_add_kaoshi_s3_y")
	public ModelAndView tea_add_kaoshi_s3_y(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_idd = request.getParameter("test_id");int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		model.addAttribute("test_id", test_id);
		return new ModelAndView("tea_add_kaoshi_s4","tea_id",tea_id);
			
	}
	
}
