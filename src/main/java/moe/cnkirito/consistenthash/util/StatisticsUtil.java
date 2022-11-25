package moe.cnkirito.consistenthash.util;

/**
 * @author daofeng.xjf
 * @date 2019/2/16
 */
public class StatisticsUtil {

    /**
     * 计算方差 s^2=[(x1-x)^2 +...(xn-x)^2]/n
     *
     * @param x 参数列表
     * @return 方差
     */
    public static double variance(Long[] x) {
        int m = x.length;
        double sum = 0;
        for (int i = 0; i < m; i++) {
            sum += x[i];
        }
        // 求平均值
        double dAve = sum / m;
        double dVar = 0;
        // 求方差
        for (int i = 0; i < m; i++) {
            dVar += (x[i] - dAve) * (x[i] - dAve);
        }
        return dVar / m;
    }

    /**
     * 计算标准差 σ=sqrt(s^2)
     * @param x 参数列表
     * @return 标准差
     */
    public static double standardDeviation(Long[] x) {
        double varianceValue = variance(x);
        return Math.sqrt(varianceValue);
    }

}
