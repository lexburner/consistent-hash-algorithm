package moe.cnkirito.consistenthash.strategy;

import moe.cnkirito.consistenthash.AbstractHashStrategy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dailj
 * @date 2022/11/25 17:47
 */
public class HashStratetyFactory {
    private static String type = "fnv";
    private static ConcurrentHashMap<String, AbstractHashStrategy> hashStrategyMap = new ConcurrentHashMap();

    static {
        hashStrategyMap.put("crc", new CrcHashStrategy());
        hashStrategyMap.put("fnv", new FnvHashStrategy());
        hashStrategyMap.put("jdk", new JdkHashCodeStrategy());
        hashStrategyMap.put("ket", new KetamaHashStrategy());
        hashStrategyMap.put("mur", new MurmurHashStrategy());
    }

    public static AbstractHashStrategy getHashStrategy() {
        return hashStrategyMap.get(type);
    }

}
