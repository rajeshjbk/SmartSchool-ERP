package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.NoticesDTO;
import com.raj.schoolerp.exception.NoticesException;
import com.raj.schoolerp.model.Audience;
import com.raj.schoolerp.model.Classes;
import com.raj.schoolerp.model.Notices;
import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.repository.ClassesRepository;
import com.raj.schoolerp.repository.NoticesRepository;
import com.raj.schoolerp.repository.UsersRepository;
import com.raj.schoolerp.service.NoticesService;

@Service
public class NoticesServiceImpl implements NoticesService {

	@Autowired
	private NoticesRepository noticesRepo;

	@Autowired
	private ClassesRepository classesRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Override
	public Notices addNotice(NoticesDTO noticesDTO) throws NoticesException {

		Notices newNotice = new Notices();

		newNotice.setTitle(noticesDTO.getTitle());

		newNotice.setContent(noticesDTO.getContent());

		newNotice.setAudience(noticesDTO.getAudience());

		newNotice.setExpiryDate(noticesDTO.getExpiryDate());

		newNotice.setAttachment(noticesDTO.getAttachment());

		newNotice.setIsUrgent(noticesDTO.getIsUrgent());

		// Class Mapping
		if (noticesDTO.getClassId() != null) {

			Classes cls = classesRepo.findById(noticesDTO.getClassId())
					.orElseThrow(() -> new NoticesException("Class Not Found"));

			newNotice.setClasses(cls);
		}

		// CreatedBy Mapping
		Users user = usersRepo.findById(noticesDTO.getCreatedBy())
				.orElseThrow(() -> new NoticesException("User Not Found"));

		newNotice.setCreatedBy(user);

		return noticesRepo.save(newNotice);
	}

	@Override
	public Notices updateNotice(Long noticeId, NoticesDTO noticesDTO) throws NoticesException {

		Notices existNotice = noticesRepo.findById(noticeId)
				.orElseThrow(() -> new NoticesException("Notice Not Found"));

		existNotice.setTitle(noticesDTO.getTitle());

		existNotice.setContent(noticesDTO.getContent());

		existNotice.setAudience(noticesDTO.getAudience());

		existNotice.setExpiryDate(noticesDTO.getExpiryDate());

		existNotice.setAttachment(noticesDTO.getAttachment());

		existNotice.setIsUrgent(noticesDTO.getIsUrgent());

		// Class Mapping
		if (noticesDTO.getClassId() != null) {

			Classes cls = classesRepo.findById(noticesDTO.getClassId())
					.orElseThrow(() -> new NoticesException("Class Not Found"));

			existNotice.setClasses(cls);
		}

		// CreatedBy Mapping
		Users user = usersRepo.findById(noticesDTO.getCreatedBy())
				.orElseThrow(() -> new NoticesException("User Not Found"));

		existNotice.setCreatedBy(user);

		return noticesRepo.save(existNotice);
	}

	@Override
	public Notices getNoticeById(Long noticeId) throws NoticesException {

		return noticesRepo.findById(noticeId).orElseThrow(() -> new NoticesException("Wrong Notice Id"));
	}

	@Override
	public List<Notices> getAllNotices() throws NoticesException {

		return noticesRepo.findAll();
	}

	@Override
	public List<Notices> getNoticesByAudience(Audience audience) throws NoticesException {

		List<Notices> notices = noticesRepo.findNoticesByAudience(audience);

		if (notices.isEmpty()) {

			throw new NoticesException("No Notices Found");
		}

		return notices;
	}

	@Override
	public List<Notices> getActiveNotices() throws NoticesException {

		List<Notices> notices = noticesRepo.findActiveNotices();

		if (notices.isEmpty()) {

			throw new NoticesException("No Active Notices Found");
		}

		return notices;
	}

	@Override
	public List<Notices> getParentNotices() throws NoticesException {

		return noticesRepo.getParentNotices();
	}

	@Override
	public void deleteNotice(Long noticeId) throws NoticesException {

		Notices notice = noticesRepo.findById(noticeId)
				.orElseThrow(() -> new NoticesException("Notice not found with ID: " + noticeId));

		noticesRepo.delete(notice);
	}
}