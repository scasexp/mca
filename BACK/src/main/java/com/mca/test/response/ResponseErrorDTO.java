package com.mca.test.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponseErrorDTO {


    public ResponseErrorDTO(String status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
    }

    private String status;

    private String mensaje;

}