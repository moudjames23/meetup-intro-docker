package com.moudjames23.springcollob.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class MyHttpResponse {

    public static ResponseEntity<Object> response(HttpStatus status, String message, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", status.value());
        map.put("message", message);

        if (data != null)
        {
            if (status == HttpStatus.BAD_REQUEST)
                map.put("errors", data);
            else
                map.put("data", data);
        }

        return new ResponseEntity<>(map, status);
    }



}
