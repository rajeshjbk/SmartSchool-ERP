package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.NoticesDTO;
import com.raj.schoolerp.exception.NoticesException;
import com.raj.schoolerp.model.Audience;
import com.raj.schoolerp.model.Notices;

public interface NoticesService {

	// Add Notice
	Notices addNotice(NoticesDTO noticesDTO) throws NoticesException;

	// Update Notice
	Notices updateNotice(Long noticeId, NoticesDTO noticesDTO) throws NoticesException;

	// Get Notice By Id
	Notices getNoticeById(Long noticeId) throws NoticesException;

	// Get All Notices
	List<Notices> getAllNotices() throws NoticesException;

	// Get Notices By Audience
	List<Notices> getNoticesByAudience(Audience audience) throws NoticesException;

	// Get Active Notices
	List<Notices> getActiveNotices() throws NoticesException;
}