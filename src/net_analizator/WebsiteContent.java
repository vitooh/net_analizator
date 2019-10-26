package net_analizator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class WebsiteContent {
	
	String address;
	
	String protocol = "https://";
	
	String source;
	
	public WebsiteContent(String address) {
		this.address = address;
		getWebsiteContent(this.protocol+this.address);
	}

	public WebsiteContent(String address, String protocol) {
		this.address = address;
		this.protocol = protocol;
		getWebsiteContent(this.protocol+this.address);
	}

	private void getWebsiteContent(String address) {
		URL url;
		try {
			url = new URL(address);
			URLConnection connection = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				builder.append(inputLine);
				builder.append(System.getProperty("line.separator"));
			}
			br.close();
			source = builder.toString();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		
	}
	
	public List<String> getLinks(){
		List<String> result = new ArrayList<String>();
		int currentPosition = 0;
		while (currentPosition >= 0) {
			int hrefIndex = this.source.indexOf("href", currentPosition);
			if (hrefIndex != -1) {
				int startIndex = checkNextSign('=','"', this.source.substring(hrefIndex+4));
				if (startIndex > -1) {
					int realStart = startIndex+hrefIndex+5;
					int endIndex = findEnd(this.source, realStart)+ realStart;
					result.add(this.source.substring(realStart, endIndex));
				}
				
				currentPosition = hrefIndex + 1;
			}
			else {
				currentPosition = hrefIndex;
			}
			
		}
		
				
		return clearLinks(result);
}

	private List<String> clearLinks(List<String> allLinks) {
		List<String> result = new ArrayList<String>();
		
		for (String link : allLinks) {
			if (link.startsWith("http")) result.add(link);
		}
		
		return result;
		
		
	}

	private int findEnd(String string, int realStart) {
		return string.substring(realStart).indexOf('"');
	}

	private int checkNextSign(char c, char d, String string) {
		if (checkNextSign(c, string)) {
			int firstIndex = string.indexOf(String.valueOf(c));
			if (checkNextSign(d, string.substring(firstIndex+1))) {
				return string.substring(firstIndex).indexOf(String.valueOf(d));
			}
		}
		return -1;
	}

	private boolean checkNextSign(char c, String string) {
		int index = 0;
		while(Character.isWhitespace(string.charAt(index))) index++;
		if (string.charAt(index)==c) return true; 
		return false;
	}

}
