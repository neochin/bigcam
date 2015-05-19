package com.enginecore.bigcam.mng.service.impl;

import com.enginecore.bigcam.core.dao.UserSuggestionDao;
import com.enginecore.bigcam.dto.beans.UserSuggestion;
import com.enginecore.bigcam.mng.service.SuggestionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 15-4-17.
 */
@Service
public class SuggestionServiceImpl implements SuggestionService {
    private Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private UserSuggestionDao userSuggestionDao;

    @Override
    public void suggest(Integer userId, String userEmail, String userSuggestion) {
        UserSuggestion suggestion = new UserSuggestion();
        suggestion.setUserId(userId);
        suggestion.setEmail(userEmail);
        suggestion.setSuggestion(userSuggestion);
        userSuggestionDao.suggest(suggestion);
    }

    @Override
    public List<Map<String, Object>> list(Integer start, Integer limit) {
        return userSuggestionDao.list(start, limit);
    }
}
