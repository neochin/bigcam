package com.enginecore.bigcam.core.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yyam on 14-11-8.
 */
@Repository
public interface SignatureDao {
    Integer updateSignature(@Param("signatureText") String signatureText, @Param("userId") Integer userId);
}
