package model;

public class Note {
	
	private int id;
	private String title;
	private String content;
	private String timeEdited;
	private boolean isTrashed = false;
	
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
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getTimeEdited() {
		return timeEdited;
	}

	public void setTimeEdited(String timeEdited) {
		this.timeEdited = timeEdited;
	}

	public boolean isTrashed() {
		return isTrashed;
	}

	public void setTrashed(boolean isTrashed) {
		this.isTrashed = isTrashed;
	}
	
	
}
