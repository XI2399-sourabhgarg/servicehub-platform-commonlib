
package com.unobank.servicehub.platform.commonlib.dto.error.hps;


import lombok.Data;

import java.net.http.HttpResponse;

@Data
public class HpsErrorResponse {

    private HttpResponse.ResponseInfo responseInfo;

}
