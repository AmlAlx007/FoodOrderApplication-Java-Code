package com.fagito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.model.Notification;
import com.fagito.repository.NotificationRepository;
import com.fagito.view.UserNotiView;

@Service
public class NotificationService {

	
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	public Notification userNotification(UserNotiView userNotiView)
	{
		Notification notificationObject=new Notification();
		notificationObject=notificationRepository.findNotiByOrderId(userNotiView.getOid(),userNotiView.getuserId());
		return notificationObject;
	}
	
	
}
