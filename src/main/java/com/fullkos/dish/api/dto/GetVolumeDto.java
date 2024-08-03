package com.fullkos.dish.api.dto;

import com.fullkos.dish.db.dto.VolumeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetVolumeDto {

    List<VolumeDto> volumes;
    Long overall;
}
