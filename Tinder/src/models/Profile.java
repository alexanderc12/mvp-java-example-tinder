package models;

public class Profile {

	private long id;
	private String name;
	private String imgPath;
	private Status status;
	
	public Profile(long id, String name, String imgPath) {
		this.id = id;
		this.name = name;
		this.imgPath = imgPath;
		this.status = Status.NEW;
	}
	
	public Profile(long id, String name, String imgPath, Status status) {
		this.id = id;
		this.name = name;
		this.imgPath = imgPath;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImgPath() {
		return imgPath;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", name=" + name + ", imgPath=" + imgPath + ", status=" + status + "]";
	}
}