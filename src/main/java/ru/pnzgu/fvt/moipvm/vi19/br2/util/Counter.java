package ru.pnzgu.fvt.moipvm.vi19.br2.util;

import org.springframework.jdbc.core.JdbcTemplate;

public class Counter {
    private final JdbcTemplate jdbcTemplate;

    public Counter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Увеличивает счетчик посещений
     */
    public void increment() {
        int counter = count();
        if(counter == 0) {
            jdbcTemplate.execute("insert into counter values(" + (counter + 1) + ")");
        } else {
            jdbcTemplate.execute("update counter set count = " + (counter + 1));
        }
    }

    /**
     * Возвращает количество посещений
     * @return количество посещений
     */
    public int count() {
        return jdbcTemplate.query("select count from counter", resultSet -> {
            if (resultSet.next()) {
                return resultSet.getInt("count");
            } else {
                return 0;
            }
        });
    }

}
