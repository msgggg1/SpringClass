package org.doit.ik.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

// 공지사항 관련
@Controller
@Log4j
@RequestMapping("/customer/*")
public class CustomerController {

	@Autowired
	private NoticeDao noticeDao;

	@GetMapping("/download.htm")
	public void download(
			@RequestParam("dir") String p  , 
			@RequestParam("file") String f ,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {

		String fname =  f;        
		response.setHeader("Content-Disposition","attachment;filename="+ new String(fname.getBytes(), "ISO8859_1"));
		String fullPath = request.getServletContext().getRealPath(   p + "/" + fname);

		FileInputStream fin = new FileInputStream(fullPath);
		ServletOutputStream sout = response.getOutputStream(); // 응답 스트림
		byte[] buf = new byte[1024];
		int size = 0;
		while((size = fin.read(buf, 0, 1024)) != -1) {
			sout.write(buf, 0, size); 
		}
		fin.close();
		sout.close();

	}

	@GetMapping("/noticeDel.htm")
	public String noticeDel(
			@RequestParam("seq") String seq 
			,@RequestParam("filesrc") String filesrc 
			,HttpServletRequest request
			,RedirectAttributes rttr
			, Model model) throws ClassNotFoundException, SQLException {
		// 2. 첨부파일 확인 후 파일 삭제 코딩
		String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		File delFilesrc = new File(uploadRealPath, filesrc);
		if (delFilesrc.exists() && delFilesrc.isFile()) delFilesrc.delete();

		// 1. DB 삭제
		int rowCount = this.noticeDao.delete(seq);
		rttr.addFlashAttribute("result", rowCount);

		return "redirect:notice.htm";

		//rttr.addAttribute("seq",seq);
		//return "redirect:noticeDetail.htm"
	}

	/*
	@PostMapping("/noticeDel.htm")
	public String noticeDel(@RequestParam("seq") String seq ,
						Model model) throws ClassNotFoundException, SQLException {

		int rowCount = this.noticeDao.delete(seq);
		model.addAttribute("result", rowCount);

		return "redirect:notice.htm";
	}
	 */

	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(
			NoticeVO notice,
			@RequestParam("o_filesrc") String ofilesrc,
			HttpServletRequest request,
			RedirectAttributes rttr) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {

		// 1. 
		CommonsMultipartFile multipartFile = notice.getFile();  
		String uploadRealPath = null;
		if ( !multipartFile.isEmpty() ) { // 수정 - 새로운 첨부파일 선택
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("> uploadRealPath : " + uploadRealPath);
			// ㄱ 이전 첨부된 파일은 삭제
			File delFilesrc = new File(uploadRealPath, ofilesrc);
			if (delFilesrc.exists() && delFilesrc.isFile()) {
				delFilesrc.delete();
			}
			// ㄴ 새로 첨부된 파일 저장
			 String  originalFilename = multipartFile.getOriginalFilename();
	         String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
	         File dest = new File(uploadRealPath, filesystemName );
	         multipartFile.transferTo(dest); // 실제 파일 저장
	         notice.setFilesrc(filesystemName);
	         
		} else { // 새로운 첨부파일 선택하지 않은 경우
			notice.setFilesrc(ofilesrc);
		}
	
		/*
		if (filesrc == null || filesrc == "") {
			CommonsMultipartFile multipartFile = notice.getFile();      
			if ( !multipartFile.isEmpty() ) {
				String  originalFilename = multipartFile.getOriginalFilename();
				String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
				File dest = new File(uploadRealPath, filesystemName );
				try {
					multipartFile.transferTo(dest);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}    // a-1.txt 저장
				notice.setFilesrc(filesystemName);
			} // if 
		} else {
			// 2. 첨부파일 확인 후 파일 삭제 코딩
			File prevFilesrc = new File(uploadRealPath, filesrc);
			if (!(prevFilesrc.exists() && prevFilesrc.isFile())) {
				File delFilesrc = new File(uploadRealPath, filesrc);
				delFilesrc.delete();

				CommonsMultipartFile multipartFile = notice.getFile();      
				if ( !multipartFile.isEmpty() ) {
					String  originalFilename = multipartFile.getOriginalFilename();
					String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
					File dest = new File(uploadRealPath, filesystemName );
					try {
						multipartFile.transferTo(dest);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}    // a-1.txt 저장
					notice.setFilesrc(filesystemName);
				} // if
				
			} 
			
		}
		*/ 
		
		//2.
		int rowCount = this.noticeDao.update(notice);
		rttr.addFlashAttribute("result", rowCount);
		rttr.addAttribute("seq", notice.getSeq()); // 파라미터로 가지고 가려면 flash x

		return "redirect:noticeDetail.htm";
	}

	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(@RequestParam("seq") String seq ,
			Model model) throws ClassNotFoundException, SQLException {

		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);

		return "noticeEdit.jsp";
	}

	@GetMapping("/noticeReg.htm")
	public String noticeReg() {

		return "noticeReg.jsp";
	}

	// <form action="" method="post">
	// [3]                                                  a.txt
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
		int index = 1;      
		while( true ) {         
			File f = new File(uploadRealPath, originalFilename);         
			if( !f.exists() ) return originalFilename;   
			// a
			String fileName = originalFilename.substring(0, originalFilename.length() - 4 );
			// .txt
			String ext =  originalFilename.substring(originalFilename.length() - 4 );
			//                        a-2.txt  
			originalFilename = fileName+"-"+(index)+ext;
			index++;
		} // while 
	}
	// [3]
	@PostMapping( value =  "/noticeReg.htm" )
	public String noticeReg( NoticeVO notice , HttpServletRequest request ) 
			throws ClassNotFoundException, SQLException, IllegalStateException, IOException {   // 커맨드객체    

		CommonsMultipartFile multipartFile = notice.getFile();      
		String uploadRealPath = null; // 실제 배포했을때의 경로 변수

		if ( !multipartFile.isEmpty() ) {
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("> uploadRealPath : " + uploadRealPath);
			String  originalFilename = multipartFile.getOriginalFilename();
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
			File dest = new File(uploadRealPath, filesystemName );
			multipartFile.transferTo(dest);    // a-1.txt 저장
			notice.setFilesrc(filesystemName);
		} // if 
		notice.setWriter("MSg"); 
		int rowCount = this.noticeDao.insert(notice);
		if (rowCount == 1) { 
			return "redirect:notice.htm";
		} else { 
			return "noticeReg.htm?error";
		} // if    
	}

	/*
	//[2]
	@PostMapping("/noticeReg.htm")
	public String noticeRegDB(NoticeVO notice, // 커맨드 객체 p356	request 파라미터를 저장할 객체
								RedirectAttributes rttr
								) throws ClassNotFoundException, SQLException { 

		log.info("> CmrUploadController.multiupload() 호출됨 + POST");
		log.info("-".repeat(30));

		//2.<div><input type="file" name="attach" multiple="multiple" ></div>
		List<CommonsMultipartFile> fileList = notice.getFile();

		for (CommonsMultipartFile file : fileList) {

			if (!file.isEmpty()) { // 업로드된 파일 있는지 
				log.info("-".repeat(30));
				String originalFileName = file.getOriginalFilename();
				notice.setFilesrc(originalFileName);
				log.info("2. originalFileName :" + originalFileName);
				long fileSize = file.getSize();
				log.info("3. Size :" + fileSize);

				// 업로드된 파일 저장
				String parent = "C:\\upload";
				File dest = new File(parent, originalFileName);
				try {
					file.transferTo(dest);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					//e.printStackTrace();
					log.info(e.toString());
				}
			}// if


			int rowcount = noticeDao.insert(notice);
			rttr.addFlashAttribute("result", rowcount);

		}


		log.info(" end.  ");	

		return "redirect:notice.htm";
	}
	 */

	/* 
	// [1]
	@PostMapping("/noticeReg.htm")
	public String noticeRegDB(@RequestParam("title") String title, 
							@RequestParam("content") String content,
							RedirectAttributes rttr
							) throws ClassNotFoundException, SQLException { 

		NoticeVO notice = new NoticeVO();
		notice.setTitle(title);
		notice.setContent(content);

		int rowcount = noticeDao.insert(notice);
		rttr.addFlashAttribute("result", rowcount); // 일회성
		if (rowcount == 1) {
			rttr.addFlashAttribute("success", "success");
		} else if(rowcount == 0) {
			rttr.addFlashAttribute("error", "error");
		}

		// 포워딩 - return "notice.jsp";
		// 리다이렉트 - redirect: 접두사
		return "redirect:notice.htm";
	}
	 */

	// [3]
	@GetMapping("/notice.htm") 
	public String notices(
			@RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value="field", defaultValue = "title") String field,
			@RequestParam(value="query", defaultValue = "") String query,			
			Model model) throws Exception {



		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);

		model.addAttribute("list", list);
		model.addAttribute("message", "Hello World!");

		return "notice.jsp";
	}

	// [2]
	/*
	@GetMapping("/notice.htm") 
	public String notices(
			@RequestParam(value="page", required = false) String ppage,
			@RequestParam(value="field", required = false) String pfield,
			@RequestParam(value="query", required = false) String pquery,			
			Model model) throws Exception {

		int page = 1;
		String field = "title";
		String query = "";

		// ?  or ?page=
		if( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage);
		if( pfield != null && !pfield.equals("") ) field = pfield;
		if( pquery != null && !pquery.equals("") ) query = pquery;

		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);

		model.addAttribute("list", list);
		model.addAttribute("message", "Hello World!");

		return "notice.jsp";
	}
	 */

	// 컨트롤러 안의 메서드 -> 컨트롤러 메서드 p.356
	/* [1]
	// 공지사항 목록 요청 URL 
	// http://localhost/customer/notice.htm?page=2&field=검색조건&query=검색어
	// @RequestMapping(value =".notice.htm", method = RequestMethod.GET )
	@GetMapping("/notice.htm") // 위와 같은 코딩
	public ModelAndView notices(
			HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		// 리턴자료형 : ModelAndView  p282
		ModelAndView mav = new ModelAndView();

		String ppage = request.getParameter("page");
		String pfield = request.getParameter("field");
		String pquery = request.getParameter("query");

		int page = 1;
		String field = "title";
		String query = "";

		// ?  or ?page=
		if( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage);
		if( pfield != null && !pfield.equals("") ) field = pfield;
		if( pquery != null && !pquery.equals("") ) query = pquery;

		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);

		mav.addObject("list", list);
		mav.addObject("message", "Hello World!");

		mav.setViewName("notice.jsp");

		return mav;
	}
	 */

	// [2]
	@GetMapping("/noticeDetail.htm")
	public String noticeDetail(@RequestParam("seq") String seq, Model model) throws Exception {
		NoticeVO  notice  = this.noticeDao.getNotice(seq);      

		model.addAttribute("notice", notice);

		return "noticeDetail.jsp";
	}

	// 공지사항 목록 요청 URL 
	/*[1]
	// http://localhost/customer/noticeDetail.htm?seq=1
	@GetMapping("/noticeDetail.htm")
	public ModelAndView noticeDetail(
			HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("noticeDetail.jsp");      
		String seq = request.getParameter("seq");       
		NoticeVO  notice  = this.noticeDao.getNotice(seq);      
		mav.addObject("notice", notice);          
		return mav;
	}
	 */

} // class
