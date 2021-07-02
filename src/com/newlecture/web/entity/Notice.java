/**
 * 
 */
package com.newlecture.web.entity;

/**
 * @author jiho-kim
 *
 * 신규개발
 */
public class Notice {
	
	
	
	private int id;
	private String title;
	
	
	
	
	/**
	 * 
	 */
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	public String getTitle() {
		return title;
	}






	public void setTitle(String title) {
		this.title = title;
	}






	/**
	 * @param id
	 * @param title
	 */
	
	
	
	
	public Notice(int id, String title) {
		this.id = id;
		this.title = title;
	}






	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + "]";
	}
	
	
	
	
	
	
	
	
}
