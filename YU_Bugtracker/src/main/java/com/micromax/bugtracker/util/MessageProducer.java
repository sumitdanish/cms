package com.micromax.bugtracker.util;

import java.util.concurrent.BlockingQueue;

import com.micromax.bugtracker.UserComment;

public class MessageProducer implements Runnable{

	BlockingQueue<UserComment> queue;
	UserComment userComment;
	public MessageProducer(BlockingQueue<UserComment> queue,UserComment userComment){
		this.queue = queue;
		this.userComment = userComment;
	}
	public void run() {
		try{
			int sleepTime = Integer.parseInt(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.THREAD_SLEEP)));
			//Thread.sleep(sleepTime);
			System.out.println("We are producer the message");
			queue.put(userComment);
			System.out.println("P Que : "+queue.size());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}