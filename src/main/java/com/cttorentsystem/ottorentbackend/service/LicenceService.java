package com.cttorentsystem.ottorentbackend.service;

import com.cttorentsystem.ottorentbackend.dtos.LicenceDto;

public interface LicenceService {

    LicenceDto createLicence(LicenceDto licenceDto);

    void triggerLicenceExpiryDateDate();
}
