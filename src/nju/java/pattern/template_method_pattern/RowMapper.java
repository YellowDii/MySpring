package nju.java.pattern.template_method_pattern;

import java.sql.ResultSet;

public interface RowMapper<T> {
    T mapRow(ResultSet rs,int rowNum) throws Exception;
}
