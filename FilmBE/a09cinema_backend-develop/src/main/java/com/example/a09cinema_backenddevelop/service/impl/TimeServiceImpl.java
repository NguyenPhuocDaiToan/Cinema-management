package com.example.a09cinema_backenddevelop.service.impl;
import com.example.a09cinema_backenddevelop.model.dto.TimeDto;
import com.example.a09cinema_backenddevelop.repository.TimeRepository;
import com.example.a09cinema_backenddevelop.service.TimeService;
import org.springframework.stereotype.Service;
import com.example.a09cinema_backenddevelop.model.dto.TimeDto;
import com.example.a09cinema_backenddevelop.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class TimeServiceImpl implements TimeService {
    @Autowired
    private TimeRepository timeRepository;
    @Override
    public List<TimeDto> findAllTimeShowBySeatDetail(String date_show, String film_id) {
        return timeRepository.findAllTimeShowBySeatDetail(date_show,film_id);
    }
}
