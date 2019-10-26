package net_analizator;

import static org.junit.Assert.*;

import org.junit.Test;

public class AllTests {

	@Test
	public void testWebsiteContent() {
		WebsiteContent www = new WebsiteContent("www.snooker.pl");
		assertEquals("www.snooker.pl", www.address);
		assertEquals("<!doctype html>", www.source.substring(0, 15).toLowerCase());
		for (String link : www.getLinks()) {
			System.out.println(link);
		}
	}
	
	@Test
	public void testLottoResponse() {
		LottoResponse response = new LottoResponse("app.lotto.pl/wyniki/?type=dl");
		System.out.println(response.response.source);
		
	}

}
