package board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class viewAction extends ActionSupport {

	
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private boardVO paramClass = new boardVO();
	private boardVO resultClass = new boardVO();
	private List<cboardVO> commentlist = new ArrayList<cboardVO>();
    
	private cboardVO cClass = new cboardVO();
	private cboardVO cResult = new cboardVO();
	
	private int currentPage;
	
	private int no;
	private int originno;
	
	private String password;
	
	private String fileUploadPath = "D:\\upload\\";
	
	private InputStream inputStream;
	private String contentDisposition;
	private long contentLength;

	public viewAction() throws IOException{
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}
	
	public String execute() throws Exception{
		paramClass.setNo(getNo());
		sqlMapper.update("updateReadHit", paramClass);
		
		resultClass = (boardVO) sqlMapper.queryForObject("selectOne", getNo());
	    
		commentlist = sqlMapper.queryForList("commentSelectAll", getNo());
		
		return SUCCESS;
	}

    public String download() throws Exception{
    	resultClass = (boardVO) sqlMapper.queryForObject("selectOne", getNo());
    	File fileInfo = new File(fileUploadPath + resultClass.getFile_savname());
    	
    	System.out.print(resultClass.getFile_savname());
    	
    	setContentLength(fileInfo.length());
    	setContentDisposition("attahment; filename=" + URLEncoder.encode(resultClass.getFile_orgname(), "utf-8"));
    	setInputStream(new FileInputStream(fileUploadPath + resultClass.getFile_savname()));
    	
 /*
       File fileInfo = new File(fileUploadPath + fileDownloadName);
       seContentLength(fileInfo.length());
       setContentDisposition("attachment; filename="+URLEncoder.encode(fileDownloadName, "utf-8"));
       setInputStream(new FileInputStream(fileUploadPath + fileDownloadName));
 */      
       return SUCCESS;
    }
    
    public String checkForm() throws Exception{
    	return SUCCESS;
    }
    
    public String checkAction() throws Exception{
    	paramClass.setNo(getNo());
    	paramClass.setPassword(getPassword());
    	
    	resultClass = (boardVO) sqlMapper.queryForObject("selectPassword", paramClass);
    	
    	if(resultClass == null)
    		return ERROR;
    	
    	return SUCCESS;
    }
    
    public String checkAction2() throws Exception{
    	cClass.setNo(getNo());
    	cClass.setPassword(getPassword());
    	cClass.setOriginno(getOriginno());
    	cResult = (cboardVO) sqlMapper.queryForObject("selectPassword2", cClass);
    	
    	if(cResult == null)
    		return ERROR;
    	return SUCCESS;
    }

	public static Reader getReader() {
		return reader;
	}

	public static void setReader(Reader reader) {
		viewAction.reader = reader;
	}

	public static SqlMapClient getSqlMapper() {
		return sqlMapper;
	}

	public static void setSqlMapper(SqlMapClient sqlMapper) {
		viewAction.sqlMapper = sqlMapper;
	}

	public boardVO getParamClass() {
		return paramClass;
	}

	public void setParamClass(boardVO paramClass) {
		this.paramClass = paramClass;
	}

	public boardVO getResultClass() {
		return resultClass;
	}

	public void setResultClass(boardVO resultClass) {
		this.resultClass = resultClass;
	}

	public List<cboardVO> getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(List<cboardVO> commentlist) {
		this.commentlist = commentlist;
	}

	public cboardVO getcClass() {
		return cClass;
	}

	public void setcClass(cboardVO cClass) {
		this.cClass = cClass;
	}

	public cboardVO getcResult() {
		return cResult;
	}

	public void setcResult(cboardVO cResult) {
		this.cResult = cResult;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getOriginno() {
		return originno;
	}

	public void setOriginno(int originno) {
		this.originno = originno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
}    