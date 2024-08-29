package model;

import java.time.LocalDateTime;

public class Note {
	
	private int id;
	private String title;
	private String content;
	private LocalDateTime timeEdited;
	private boolean isFavorite;
	private boolean isTrashed;
	
	public Note(int id, String title, String content, LocalDateTime timeEdited, boolean isFavorite, boolean isTrashed) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.timeEdited = timeEdited;
		this.isTrashed = isTrashed;
		this.isFavorite = isFavorite;
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
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimeEdited() {
		return timeEdited;
	}

	public void setTimeEdited(LocalDateTime timeEdited) {
		this.timeEdited = timeEdited;
	}
	
	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
		
	public boolean isTrashed() {
		return isTrashed;
	}

	public void setTrashed(boolean isTrashed) {
		this.isTrashed = isTrashed;
	}

	
	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", content=" + content + ", timeEdited=" + timeEdited
				+ ", isTrashed=" + isTrashed + ", isFavorite=" + isFavorite + "]";
	}
}
