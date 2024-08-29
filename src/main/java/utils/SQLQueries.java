package utils;

public class SQLQueries {
	
	private SQLQueries() {}
	
	public static final String SELECT_BY_ID = "SELECT * FROM notes WHERE id=?";
	public static final String SELECT_BY_TITLE = "SELECT * FROM notes WHERE title=?";
	public static final String SELECT_ALL_ACTIVE_NOTES = "SELECT * FROM notes WHERE is_trashed=FALSE ORDER BY favorite DESC, time_edited DESC";
	public static final String SELECT_ALL_TRASHED_NOTES = "SELECT * FROM notes WHERE is_trashed=TRUE ORDER BY time_edited DESC";
	public static final String UPDATE_NOTE = "UPDATE notes SET title=?, content=?, time_edited=?, favorite=?, is_trashed=? WHERE id=?";
	public static final String INSERT_NOTE = "INSERT INTO notes (title, content) VALUES(?,?)";
	public static final String DELETE_NOTE = "DELETE FROM notes WHERE id=?";
	public static final String DELETE_ALL_FROM_TRASH = "DELETE FROM notes WHERE is_trashed=TRUE";
	
}
