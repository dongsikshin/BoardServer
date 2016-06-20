package parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XmlParseTest {
	URL url;
	HttpURLConnection con;
	StringBuffer sb;
	InputStream is;
	
	
	public XmlParseTest() {
		try {
			url = new URL("http://192.168.1.113:9090/xml/list.jsp");
			con=(HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			
			int code=con.getResponseCode();
			if(code == HttpURLConnection.HTTP_OK){
				System.out.println("��û ����");
				sb = new StringBuffer();
				
				BufferedReader buffr = new BufferedReader(new InputStreamReader(is=con.getInputStream(), "utf-8"));
				
				/*				
				String data=null;
				while(true){
					data=buffr.readLine();
					if(data==null)break;
					//System.out.println(data);
					sb.append(data); //��Ʈ�� ���ۿ� data ����!!! 
				}
				*/				
				//System.out.println(sb.toString());
				
				/*json �ƴ� xml�� �Ľ��غ���!!(���̵� ��)
				 *�ڹپ��� �Ľ��� �ϴ� ����� ũ�� 2������ �ִ�
				 *1.DOM ���- ������ ��� ������ �ؼ��� �� , �ش� tag���� ��ü�� �����ϹǷ� 
				 *                   pc�� ��� ������ ������, ����Ʈ���� ��� �޸� �����ϴ�..
				 *                   
				 *2.SAX ��� - ����ΰ� ������ �о���̸鼭, �±� �� ������ �߰��Ҷ����� �̺�Ʈ��
				 *                   �߻���Ű�� ����̸�, DOM ���� ������ ������ �۰��߽� ���� �е���!!
				 *                   
				 * SAXParser �� �ʿ�..
				 * */
				parse();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	//SAX ����� �Ľ� ����!!
	public void parse(){
		SAXParserFactory factory=SAXParserFactory.newInstance();
		try {
			SAXParser saxParser=factory.newSAXParser();
			saxParser.parse(is, new MyHandler());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new XmlParseTest();
	}

}
