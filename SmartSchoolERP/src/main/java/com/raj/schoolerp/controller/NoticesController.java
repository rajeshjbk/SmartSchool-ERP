package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.schoolerp.DTO.NoticesDTO;
import com.raj.schoolerp.exception.NoticesException;
import com.raj.schoolerp.model.Audience;
import com.raj.schoolerp.model.Notices;
import com.raj.schoolerp.service.NoticesService;

@RestController
@RequestMapping("/schoolerp/notices")
@CrossOrigin(origins = "http://localhost:5173")
public class NoticesController {

	@Autowired
	private NoticesService noticesService;

	@PostMapping("/add")
	public Notices addNotice(@RequestBody NoticesDTO noticesDTO) throws NoticesException {

		return noticesService.addNotice(noticesDTO);
	}

	@PutMapping("/update/{noticeId}")
	public Notices updateNotice(@PathVariable Long noticeId, @RequestBody NoticesDTO noticesDTO)
			throws NoticesException {

		return noticesService.updateNotice(noticeId, noticesDTO);
	}

	@GetMapping("/{noticeId}")
	public Notices getNoticeById(@PathVariable Long noticeId) throws NoticesException {

		return noticesService.getNoticeById(noticeId);
	}

	@GetMapping("/all")
	public List<Notices> getAllNotices() throws NoticesException {

		return noticesService.getAllNotices();
	}

	@GetMapping("/audience/{audience}")
	public List<Notices> getNoticesByAudience(@PathVariable Audience audience) throws NoticesException {

		return noticesService.getNoticesByAudience(audience);
	}

	@GetMapping("/active")
	public List<Notices> getActiveNotices() throws NoticesException {

		return noticesService.getActiveNotices();
	}

	@GetMapping("/parent")
	public ResponseEntity<List<Notices>> getParentNoticesHandler() throws NoticesException {

		List<Notices> notices = noticesService.getParentNotices();

		return new ResponseEntity<>(notices, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{noticeId}")
	public ResponseEntity<String> deleteNotice(@PathVariable Long noticeId) throws NoticesException {

		noticesService.deleteNotice(noticeId);

		return new ResponseEntity<>("Notice deleted successfully", HttpStatus.OK);
	}
}