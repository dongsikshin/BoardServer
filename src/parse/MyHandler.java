/*
 * DOM ����� ������ ��� �о� �±׸��� 1:1�� ��ü�� �����ϹǷ�, ���̴�,
 * ������ SAX ����� 1:1 �� ��ü�� �������� �����Ƿ� �ξ� ������ 
 * �״�� �����ڴ� SAX ��Ŀ��� �����ϴ� �ڵ鷯 ��ü�� �̿��Ͽ� �±׸� �߰��Ҷ����� 
 * �˸´� ó���� ���� �ռ� �ؾ� �Ѵ�...
 * */
package parse;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import board.model.Board;

public class MyHandler extends DefaultHandler{
	ArrayList<Board> list;
	Board board;
	
	//���� ����ΰ� �� ������ ���������� ���� �Ǵ�
	boolean board_id; 
	boolean title; 
	boolean writer; 
	boolean content; 
	boolean regdate; 
	boolean hit; 
	
	public void startDocument() throws SAXException {
		 list= new ArrayList<Board>();
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.print("<"+qName+">");
		
		if(qName.equalsIgnoreCase("board")){
			board = new Board();
		}else if(qName.equalsIgnoreCase("board_id")){
			board_id=true; //���� �� ����޾�!!!
		}else if(qName.equalsIgnoreCase("title")){
			title=true;
		}else if(qName.equalsIgnoreCase("writer")){
			writer=true;
		}else if(qName.equalsIgnoreCase("content")){
			content=true;
		}else if(qName.equalsIgnoreCase("regdate")){
			regdate=true;
		}else if(qName.equalsIgnoreCase("hit")){
			hit=true;
		}
	}
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data=new String(ch, start, length);
		
		if(board_id){
			board.setBoard_id( Integer.parseInt(data));	
			board_id=false; //�ٽ� ��� ���Ѱɷ� ��������!!
		}else if(title){
			board.setTitle(data);
			title=false;
		}else if(writer){
			board.setWriter(data);
			writer=false;
		}else if(content){
			board.setContent(data);
			content=false;
		}else if(regdate){
			board.setRegdate(data);
			regdate=false;
		}else if(hit){
			board.setHit(Integer.parseInt(data));
			hit=false;
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//�ϳ��� dto �� list�� ����!!
		if(qName.equalsIgnoreCase("board")){
			list.add(board);
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("list�� ����� �Խù��� �� ������ "+list.size());
	}
}









