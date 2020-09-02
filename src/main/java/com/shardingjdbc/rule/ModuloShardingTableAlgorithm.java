package com.shardingjdbc.rule;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;

import java.util.Collection;

/**
 * @Author: yyl
 * @Date: 2018/11/1 20:55
 */
public class ModuloShardingTableAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(final Collection<String> tableNames, final PreciseShardingValue<Long> shardingValue) {
        for (String each : tableNames) {
            if (each.endsWith(shardingValue.getValue() % 2 + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
    public static void main(String[] args){
        int s = 0;
        int m = 0;
        DefaultKeyGenerator dkg = new DefaultKeyGenerator();
    }

}
