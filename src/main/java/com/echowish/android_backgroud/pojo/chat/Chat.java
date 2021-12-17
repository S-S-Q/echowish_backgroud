package com.echowish.android_backgroud.pojo.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    public Integer myId;
    public Integer otherId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date time;
    public String content;

}
