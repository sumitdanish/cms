package com.micromax.bugtracker.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.micromax.bugtracker.UserComment;

public class MessageQueue {

	private static volatile MessageQueue messageQueue;
	private static BlockingQueue<UserComment> userCommentBlockingQueue;
	private MessageQueue(){
		//messageQueue = new MessageQueue();
	}
	public static synchronized MessageQueue getMessageQueue() {
		if(messageQueue == null){
			synchronized (MessageQueue.class) {
				if(messageQueue  == null){
					messageQueue = new MessageQueue();
					userCommentBlockingQueue = new ArrayBlockingQueue<UserComment>(1000);
				}
			}
		}
		return messageQueue;
	}
	public static BlockingQueue<UserComment> getUserCommentBlockingQueue() {
		return userCommentBlockingQueue;
	}
	
	
	
	
	
	
}
