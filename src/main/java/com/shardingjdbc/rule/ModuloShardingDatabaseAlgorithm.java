package com.shardingjdbc.rule;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * @Author: yyl
 * @Date: 2018/11/1 19:51
 */
public class ModuloShardingDatabaseAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(final Collection<String> databaseNames, final PreciseShardingValue<Long> shardingValue) {
        for (String each : databaseNames) {
            if (each.endsWith(shardingValue.getValue() % 2 + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
