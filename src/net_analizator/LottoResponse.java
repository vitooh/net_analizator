package net_analizator;

public class LottoResponse {
	
	WebsiteContent response;
	
	LottoResponse (String request) {
		response = new WebsiteContent(request);
	}

}
