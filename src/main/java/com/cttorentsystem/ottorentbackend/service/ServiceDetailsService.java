package com.cttorentsystem.ottorentbackend.service;

import com.cttorentsystem.ottorentbackend.dtos.ServiceDetailsDto;

public interface ServiceDetailsService {

    ServiceDetailsDto createServiceDetails(ServiceDetailsDto serviceDetailsDto);

    public void triggerNextServiceDate();

}
