package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.NoticesDTO;
import com.raj.schoolerp.exception.NoticesException;
import com.raj.schoolerp.model.Audience;
import com.raj.schoolerp.model.Notices;
import com.raj.schoolerp.repository.NoticesRepository;
import com.raj.schoolerp.service.NoticesService;

@Service
public class NoticesServiceImpl implements NoticesService {

	@Autowired
	private NoticesRepository noticesRepo;

	@Override
	public Notices addNotice(NoticesDTO noticesDTO) throws NoticesException {

		Notices newNotice = new Notices();

		BeanUtils.copyProperties(noticesDTO, newNotice);

		return noticesRepo.save(newNotice);
	}

	@Override
	public Notices updateNotice(Long noticeId, NoticesDTO noticesDTO) throws NoticesException {

		Notices existNotice = noticesRepo.findById(noticeId)
				.orElseThrow(() -> new NoticesException("Notice Not Found"));

		BeanUtils.copyProperties(noticesDTO, existNotice);

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
}