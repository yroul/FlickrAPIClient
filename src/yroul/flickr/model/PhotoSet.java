package yroul.flickr.model;

import java.util.List;

public class PhotoSet {

	private int page;
	@Override
	public String toString() {
		return "PhotoSet [page=" + page + ", pages=" + pages + ", perpage="
				+ perpage + ", total=" + total + ", photo=" + photo.toString() + "]";
	}
	private double pages;
	private int perpage;
	private String total;
	private List<Photo> photo;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public double getPages() {
		return pages;
	}
	public void setPages(double pages) {
		this.pages = pages;
	}
	public int getPerpage() {
		return perpage;
	}
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<Photo> getPhoto() {
		return photo;
	}
	public void setPhoto(List<Photo> photo) {
		this.photo = photo;
	}

}
