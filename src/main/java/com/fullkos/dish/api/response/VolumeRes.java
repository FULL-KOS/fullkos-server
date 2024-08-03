package com.fullkos.dish.api.response;

import com.fullkos.dish.api.dto.GetVolumeDto;
import com.fullkos.dish.db.dto.SampleDto;
import com.fullkos.dish.db.dto.VolumeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class VolumeRes extends BaseResponseBody {

     List<VolumeDto> volumes;
     Long overall;

    static public VolumeRes of(int statusCode, String message, GetVolumeDto dto) {

        VolumeRes volumeRes = VolumeRes.builder()
                .volumes(dto.getVolumes())
                .overall(dto.getOverall())
                .build();
        volumeRes.setStatusCode(statusCode);
        volumeRes.setMessage(message);

        return volumeRes;
    }
}
