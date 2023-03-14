package net.javaguides.springmvc.service;

import net.javaguides.springmvc.dao.ResultDTO;

public interface MessageService {

    public ResultDTO getResponseMessage (String messageCode);
}
