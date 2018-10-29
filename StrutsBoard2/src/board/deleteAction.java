package board;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class deleteAction extends ActionSupport {

  public static Reader reader;
  public static SqlMapClient sqlMapper;
  
  private boardVO paramClass;
  private boardVO resultClass;
  
  private cboardVO cClass = new cboardVO();
  private cboardVO cResult = new cboardVO();
  
  private int currentPage;
  private String fileUploadPath = "d:\\upload\\";
  
  private int no;
  
  public deleteAction() throws IOException{
	  reader = Resources.getResourceAsReader("sqlMapConfig.xml");
	  sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
	  reader.close();
  }

  public String execute() throws Exception{
	    paramClass = new boardVO();
	    resultClass = new boardVO();
	    
	    resultClass = (boardVO) sqlMapper.queryForObject("selectOne", getNo());
	    
	    File deleteFile = new File(fileUploadPath + resultClass.getFile_savname());
	    deleteFile.delete();
	    
	    paramClass.setNo(getNo());
	    
	    sqlMapper.update("deleteBoard", paramClass);
	    
	    return SUCCESS;
  }

  
  public String execute2() throws Exception{
	    cClass= new cboardVO();
	    cResult = new cboardVO();
	    
	    cClass.setNo(getNo());
        
	    sqlMapper.update("deleteComment", cClass);
	    
	    return SUCCESS;
  }

public static Reader getReader() {
	return reader;
}

public static void setReader(Reader reader) {
	deleteAction.reader = reader;
}

public static SqlMapClient getSqlMapper() {
	return sqlMapper;
}

public static void setSqlMapper(SqlMapClient sqlMapper) {
	deleteAction.sqlMapper = sqlMapper;
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

public String getFileUploadPath() {
	return fileUploadPath;
}

public void setFileUploadPath(String fileUploadPath) {
	this.fileUploadPath = fileUploadPath;
}

public int getNo() {
	return no;
}

public void setNo(int no) {
	this.no = no;
}
  
}