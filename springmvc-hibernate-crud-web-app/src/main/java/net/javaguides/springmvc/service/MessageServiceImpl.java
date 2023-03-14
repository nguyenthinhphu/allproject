package net.javaguides.springmvc.service;

import org.springframework.stereotype.Service;

import net.javaguides.springmvc.common.CommonConstant;
import net.javaguides.springmvc.dao.ResultDTO;
import net.javaguides.springmvc.utils.MessageUtil;

@Service
public class MessageServiceImpl implements MessageService{

    @Override
    public ResultDTO getResponseMessage(String messageCode) {
        ResultDTO dto = new ResultDTO();
        dto.setMessageCode(messageCode);
        dto.setMessageJp(MessageUtil.getMessageContent(messageCode, CommonConstant.LANGUAGE_JP));
        return dto;
    }
}
