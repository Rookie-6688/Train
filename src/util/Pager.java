package util;

public class Pager {
	
	//1.ҳ����ص�����
	
	private String requestPage; //����ҳ
	private int currentPage; //��ǰҳ
	private int firstPage; //��ҳ
	private int previosPage; //ǰһҳ
	private int nextPage; //��һҳ
	private int lastPage; //βҳ
	private int pageSize = 10; //ÿҳ��ʾ����	
	
	//2.��̨�йص����� + ���ݿ��й�
	private int totalCount; //�ܼ�¼��
	private int totalPage; //��ҳ��
	private int startRecord; //��ʼ��¼��
	private int recordCount; //��ʾ���� = pageSize 
	
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
	 * ���ҳ��pager������г�ʼ������
	 * @param requestPage
	 * @param pageSize
	 * @param totalCount
	 */
	public void init(String requestPage, int pageSize, int totalCount) {
		this.requestPage = requestPage;		
		this.pageSize = pageSize;
		this.totalCount = totalCount;

		this.currentPage = Integer.valueOf(this.requestPage); //��ǰҳ=����ҳ
		this.previosPage = this.currentPage - 1;
		this.nextPage = this.currentPage + 1;
		this.firstPage = 1;
		this.totalPage = this.totalCount%this.pageSize !=0 ? this.totalCount/this.pageSize +1 : this.totalCount/this.pageSize;
		this.lastPage = this.totalPage;
		
		this.startRecord = (this.currentPage-1)*this.pageSize;
		this.recordCount = this.pageSize;
	}
}
