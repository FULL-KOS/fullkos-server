package com.fullkos.dish.db.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

public interface VolumeDtoInterface {
    Long getCompanyId();
    String getCompanyName();
    Long getTotal();
}
