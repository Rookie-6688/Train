package util;

public class Pager {
	
	//1.页面相关的数据
	
	private String requestPage; //请求页
	private int currentPage; //当前页
	private int firstPage; //首页
	private int previosPage; //前一页
	private int nextPage; //下一页
	private int lastPage; //尾页
	private int pageSize = 10; //每页显示条数	
	
	//2.后台有关的数据 + 数据库有关
	private int totalCount; //总记录数
	private int totalPage; //总页数
	private int startRecord; //开始记录数
	private int recordCount; //显示条数 = pageSize 
	
	public String getRequestPage() {
		return requestPage;
	}
	public void setRequestPage(String requestPage) {
		this.requestPage = requestPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getPreviosPage() {
		return previosPage;
	}
	public void setPreviosPage(int previosPage) {
		this.previosPage = previosPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartRecord() {
		return startRecord;
	}
	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * 针对页面pager对象进行初始化工作
	 * @param requestPage
	 * @param pageSize
	 * @param totalCount
	 */
	public void init(String requestPage, int pageSize, int totalCount) {
		this.requestPage = requestPage;		
		this.pageSize = pageSize;
		this.totalCount = totalCount;

		this.currentPage = Integer.valueOf(this.requestPage); //当前页=请求页
		this.previosPage = this.currentPage - 1;
		this.nextPage = this.currentPage + 1;
		this.firstPage = 1;
		this.totalPage = this.totalCount%this.pageSize !=0 ? this.totalCount/this.pageSize +1 : this.totalCount/this.pageSize;
		this.lastPage = this.totalPage;
		
		this.startRecord = (this.currentPage-1)*this.pageSize;
		this.recordCount = this.pageSize;
	}
}
