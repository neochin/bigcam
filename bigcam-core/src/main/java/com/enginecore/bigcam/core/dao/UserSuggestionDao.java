package com.enginecore.bigcam.core.dao;

import com.enginecore.bigcam.dto.beans.UserSuggestion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 15-4-17.
 */
@Repository
public interface UserSuggestionDao {
    void suggest(UserSuggestion userSuggestion);

    List<Map<String, Object>> list();
}
