package com.enginecore.bigcam.mng.service;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 15-4-17.
 */
public interface SuggestionService {
    void suggest(Integer userId, String userEmail, String userSuggestion);

    List<Map<String, Object>> list(Integer start, Integer limit);
}
