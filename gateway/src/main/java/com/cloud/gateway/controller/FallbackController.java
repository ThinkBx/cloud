package com.cloud.gateway.controller;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 降级、熔断
 * @author fjj
 * @date 2020/5/23 23:40
 */
@RestController
public class FallbackController {

    @GetMapping("/defaultFallback")
    public Map<String, String> fallbackMethod() {
        Map<String, String> map = Maps.newHashMap();
        map.put("code", "500");
        map.put("message", "连接超时，请稍后重试");
        map.put("data", null);
        return map;
    }


}
