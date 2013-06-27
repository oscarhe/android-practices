package com.spoobrain.stockquotes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class StockInfoActivity extends Activity {
	
	private static final String TAG = "STOCKQUOTE";
	
	TextView companyNameTextView;
	TextView yearLowTextView;
	TextView yearHighTextView;
	TextView daysLowTextView;
	TextView daysHighTextView;
	TextView lastTradePriceOnlyTextView;
	TextView changeTextView;
	TextView daysRangeTextView;
	
	static final String KEY_ITEM = "quote";
	static final String KEY_NAME = "Name";
	static final String KEY_YEAR_LOW = "YearLow";
	static final String KEY_YEAR_HIGH = "YearHigh";
	static final String KEY_DAYS_LOW = "DaysLow";
	static final String KEY_DAYS_HIGH = "DaysHigh";
	static final String KEY_LAST_TRADE_PRICE = "LastTradePriceOnly";
	static final String KEY_CHANGE = "Change";
	static final String KEY_DAYS_RANGE = "DaysRange";
	
	String name = "";
	String yearLow = "";
	String yearHigh = "";
	String daysLow = "";
	String daysHigh = "";
	String lastTradePriceOnly = "";
	String change = "";
	String daysRange = "";
	
	String yahooURLFirst = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22";
	String yahooURLSecond = "%22)&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
	
	String[][] xmlPullParserArray = {{"AverageDailyVolume", "0"}, 
			{"Change", "0"}, {"DaysLow", "0"}, 
			{"DaysHigh", "0"}, {"YearLow", "0"}, 
			{"YearHigh", "0"}, {"MarketCapitalization", "0"}, 
			{"LastTradePriceOnly", "0"}, {"DaysRange", "0"}, 
			{"Name", "0"}, {"Symbol", "0"},
			{"Volume", "0"}, {"StockExchange", "0"}};
	
	int parserArrayIncrement = 0;
	
	protected void onCreate(Bundle savedInstanceState) { 
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_info);
		
		Intent intent = getIntent();
		String stockSymbol = intent.getStringExtra(MainActivity.STOCK_SYMBOL);
		
		// Initialize TextViews
		companyNameTextView = (TextView) findViewById(R.id.companyNameTextView);
		yearLowTextView = (TextView) findViewById(R.id.yearLowTextView);
		yearHighTextView = (TextView) findViewById(R.id.yearHighTextView);
		daysLowTextView = (TextView) findViewById(R.id.daysLowTextView);
		daysHighTextView = (TextView) findViewById(R.id.daysHighTextView);
		lastTradePriceOnlyTextView = (TextView) findViewById(R.id.lastTradePriceOnlyTextView);
		changeTextView = (TextView) findViewById(R.id.changeTextView);
		daysRangeTextView = (TextView) findViewById(R.id.daysRangeTextView);
		
		Log.d(TAG, "Before URL Creation " + stockSymbol);
		
		final String yqlURL = yahooURLFirst + stockSymbol + yahooURLSecond;
		
		new MyAsyncTask().execute(yqlURL);

	}
	
	private class MyAsyncTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {
			/*
			try {
				
				URL url = new URL(args[0]);
				
				URLConnection connection;
				connection = url.openConnection();
				
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				
				int responseCode = httpConnection.getResponseCode();
				
				if (responseCode == HttpURLConnection.HTTP_OK) {
					
					InputStream in = httpConnection.getInputStream();
					
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					
					DocumentBuilder db = dbf.newDocumentBuilder();
					
					Document dom = db.parse(in);
					
					Element docEle = dom.getDocumentElement(); 
					
					NodeList nl = docEle.getElementsByTagName("quote");
					
					if (nl != null && nl.getLength() > 0) {
						
						for (int i = 0; i < nl.getLength(); i++) {
							
							StockInfo theStock = getStockInformation(docEle);
							
							 // Gets the values stored in the StockInfo object
				            daysLow = theStock.getDaysLow();
				            daysHigh = theStock.getDaysHigh();
				            yearLow = theStock.getYearLow();
				            yearHigh = theStock.getYearHigh();
				            name = theStock.getName();
				            lastTradePriceOnly = theStock.getLastTradePriceOnly();
				            change = theStock.getChange();
				            daysRange = theStock.getDaysRange();
							
						}
						
					}
					
				}
				
			} catch (MalformedURLException e) {
				Log.d(TAG, "MalformedURLException", e);
			} catch (IOException e) {
				Log.d(TAG, "IOException", e);
			} catch (ParserConfigurationException e) {
				Log.d(TAG, "Parser Configuration Exception", e);
			} catch (SAXException e) {
				Log.d(TAG, "SAX Exception", e);
			}
			finally { }
			*/
			
			try {
				
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser parser = factory.newPullParser();
				parser.setInput(new InputStreamReader(getUrlData(args[0])));
				
				beginDocument(parser, "query");
				
				int eventType = parser.getEventType();
				
				do {
					
					nextElement(parser);
					
					parser.next();
					
					eventType = parser.getEventType();
					
					if(eventType == XmlPullParser.TEXT) {
						
						String valueFromXML = parser.getText();
						
						xmlPullParserArray[parserArrayIncrement++][1] = valueFromXML;
						
					}
					
				} while (eventType != XmlPullParser.END_DOCUMENT); 
				
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally {}
			
			return null;
			
		}
		
		public InputStream getUrlData(String url) throws URISyntaxException, ClientProtocolException, IOException {
			
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet method = new HttpGet(new URI(url));
			
			HttpResponse res = client.execute(method);
			return res.getEntity().getContent();
			
		}
		
		public final void beginDocument(XmlPullParser parser, String firstElementName) throws XmlPullParserException, IOException {
			
			int type;
			
			while ((type = parser.next()) != parser.START_TAG && type != parser.END_DOCUMENT) {
				
				;
				
			}
			
			if (type != parser.START_TAG) {
				
				throw new XmlPullParserException("No Start Tag Found");
				
			}
			
			if (!parser.getName().equals(firstElementName)) {
				
				throw new XmlPullParserException("Unexpected Start Tag Found: " + parser.getName() + " Expected Start Tag: " + firstElementName);
				
			}
			
		}
		
		public final void nextElement(XmlPullParser parser) throws XmlPullParserException, IOException {
			
			int type;
			
			while ((type = parser.next()) != parser.START_TAG && type != parser.END_DOCUMENT) {
				;
			}
			
		}
		
		protected void onPostExecute(String result) {
			
			companyNameTextView.setText(xmlPullParserArray[9][1]);
			yearLowTextView.setText(xmlPullParserArray[4][1]);
			yearHighTextView.setText(xmlPullParserArray[5][1]);
			daysLowTextView.setText(xmlPullParserArray[2][1]);
			daysHighTextView.setText(xmlPullParserArray[3][1]);
			lastTradePriceOnlyTextView.setText(xmlPullParserArray[7][1]);
			changeTextView.setText(xmlPullParserArray[1][1]);
			daysRangeTextView.setText(xmlPullParserArray[8][1]);
			
		}
		
		/*private StockInfo getStockInformation(Element entry) {
			
			String stockName = getTextValue(entry, "Name");
			String stockYearLow = getTextValue(entry, "YearLow");
			String stockYearHigh = getTextValue(entry, "YearHigh");
			String stockDaysLow = getTextValue(entry, "DaysLow");
			String stockDaysHigh = getTextValue(entry, "DaysHigh");
			String stockLastTradePriceOnly = getTextValue(entry, "LastTradePriceOnly");
			String stockChange = getTextValue(entry, "Change");
			String stockDaysRange = getTextValue(entry, "DaysRange");
			
			StockInfo theStock = new StockInfo(stockDaysLow, stockDaysHigh, stockYearLow,
					stockYearHigh, stockName, stockLastTradePriceOnly,
					stockChange, stockDaysRange);
			
			return theStock;
			
		}
		
		private String getTextValue(Element entry, String tagName) {
			
			String tagValueToReturn = null;
			
			NodeList nl = entry.getElementsByTagName(tagName);
			
			if (nl != null && nl.getLength() > 0) {
				
				Element element = (Element) nl.item(0);
				
				tagValueToReturn = element.getFirstChild().getNodeValue();
				
			}
			
			return tagValueToReturn;
			
		}
		*/
		
	}
	
}