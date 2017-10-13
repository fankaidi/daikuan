package co.kensure.mem;

public class PageInfo {

	/**
	 * 页数
	 */
	private int pageNo;
	/**
	 * 每页个数
	 */
	private int pageSize;

	/**
	 * 偏移数
	 */
	private int pageOffset;
	
	public PageInfo(int pageNo,int pageSize){
		setPageNo(pageNo);
		setPageSize(pageSize);
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageOffset() {
		if (pageNo != 0) {
			pageOffset = (pageNo - 1) * pageSize;
		}
		return pageOffset;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}
	
	
	

}
