package com.team.message.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.message.DAO.MessageDAO;
import com.team.message.VO.MessageVO;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	MessageDAO messageDao;
	
	//쪽지 전체 목록 
	@Override
	public List<MessageVO>listAll()throws Exception{
		return messageDao.listAll();
	}
	
	//쪽지 작성
	@Override
	public void create(MessageVO mvo)throws Exception{
		
		String MESSAGE_SUBJECT = mvo.getMESSAGE_SUBJECT();
		String MESSAGE_CONTENT = mvo.getMESSAGE_CONTENT();
		String MESSAGE_RECEIVER = mvo.getMESSAGE_RECEIVER();
		String MESSAGE_SENDER = mvo.getMESSAGE_SENDER();
	
		//태그문자 처리
		MESSAGE_SUBJECT = MESSAGE_SUBJECT.replace("<", "&lt;");
		MESSAGE_SUBJECT = MESSAGE_SUBJECT.replace(">", "&gt;");
		MESSAGE_SENDER = MESSAGE_SENDER.replace("<", "&lt;");
		MESSAGE_SENDER = MESSAGE_SENDER.replace(">", "&gt;");
		MESSAGE_RECEIVER = MESSAGE_RECEIVER.replace("<", "&lt;");
		MESSAGE_RECEIVER = MESSAGE_RECEIVER.replace(">", "&gt;");
		
		//공백문자 처리
		MESSAGE_SUBJECT = MESSAGE_SUBJECT.replace(" ", "&nbsp;&nbsp;");
		MESSAGE_SENDER = MESSAGE_SENDER.replace(" ", "&nbsp;&nbsp;");
		MESSAGE_RECEIVER = MESSAGE_RECEIVER.replace(" ", "&nbsp;&nbsp;");
		
		//줄바꿈 문자 처리
		MESSAGE_CONTENT = MESSAGE_CONTENT.replace("\n", "<br>");
		
		mvo.setMESSAGE_SUBJECT(MESSAGE_SUBJECT);
		mvo.setMESSAGE_CONTENT(MESSAGE_CONTENT);
		mvo.setMESSAGE_RECEIVER(MESSAGE_RECEIVER);
		mvo.setMESSAGE_SENDER(MESSAGE_SENDER);
		
		messageDao.create(mvo);
	}
	
	//쪽지 상세 내용
	@Override
	public MessageVO read(MessageVO mvo)throws Exception{
		MessageVO resultVO = messageDao.read(mvo);
		return resultVO;
	}
	
	//쪽지 삭제
	@Override
	public void delete(int MESSSAGE_NO)throws Exception{
		messageDao.delete(MESSSAGE_NO);
	}
}