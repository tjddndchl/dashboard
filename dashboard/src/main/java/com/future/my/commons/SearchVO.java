package com.future.my.commons;

public class SearchVO {
	private String keyword;
	private String searchOption;
	public SearchVO(String keyword, String searchOption) {
		super();
		this.keyword = keyword;
		this.searchOption = searchOption;
	}
	
	public SearchVO() {
		
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSearchOption() {
		return searchOption;
	}
	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
	
	
	@Override
	public String toString() {
		return "SearchVO [keyword=" + keyword + ", searchOption=" + searchOption + "]";
	}
	
	
}
