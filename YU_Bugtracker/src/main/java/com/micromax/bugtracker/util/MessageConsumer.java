package com.micromax.bugtracker.util;

import java.util.concurrent.BlockingQueue;

import com.micromax.bugtracker.UserComment;

public class MessageConsumer implements Runnable{
	BlockingQueue<UserComment> queue;
	public MessageConsumer(BlockingQueue<UserComment> queue) {
		this.queue = queue;
	}
	public void run() {
		try{
			System.out.println("Queue size : "+queue.size());
			for(UserComment comment : queue){
				if(comment != null){
					Thread.sleep(Integer.parseInt(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.THREAD_SLEEP))));
					System.out.println("We are consuling the messages"+comment.getIssue().getTitle());
					
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}