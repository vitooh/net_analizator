package net_analizator;

public class TagContent {
	
	String address;
	
	String tag;
	
	long position;
	
	String content;
	
	TagContent(String tag, String content, long position, String Address) {
		this.tag = tag;
		this.content = content;
		this.position = position;
	}
	
	TagContent(String tag, String content, long position, WebsiteContent www) {
		this(tag, content, position, www.address);
	}


}
