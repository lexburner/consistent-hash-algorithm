package moe.cnkirito.consistenthash.load.balancer;

import moe.cnkirito.consistenthash.AbstractHashStrategy;
import moe.cnkirito.consistenthash.bo.Server;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

import static org.mockito.Mockito.*;

/**
 * @author dailj
 * @date 2022/11/26 14:51
 */
// 配合@Mock注解使用
@RunWith(PowerMockRunner.class)
public class ConsistentHashRingTest {
    @Mock
    ConsistentHashRing instance;
    @Mock
    AbstractHashStrategy hashStrategy;
    ConsistentHashRing consistentHashRing;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    /// 模拟私有方法调用，不会被单元测试覆盖率统计
    // @PrepareForTest({ConsistentHashRing.class})
    public void testGetInstance() {
        when(hashStrategy.getHashCode(anyString())).thenReturn(0);
        /// 模拟私有方法调用
        /**
         * @Spy
         * private ConsistentHashRing consistentHashRing = Mockito.spy(ConsistentHashRing.class);
         *
         * PowerMockito.doReturn(new TreeMap<Integer, Server>(new HashMap<Integer, Server>() {{
         *     put(Integer.valueOf(0), new Server("url"));}}))
         *     .when(consistentHashRing, "buildConsistentHashRing", Arrays.<Server>asList(new Server("url")));
         */

        ConsistentHashRing result = ConsistentHashRing.getInstance(Arrays.<Server>asList(new Server("url")));
        Assert.assertSame(ConsistentHashRing.getInstance(Arrays.<Server>asList(new Server("url"))), result);
        /// 验证方法被调用过一次
        /**
         * Mockito.verify(hashStrategy).getHashCode("url");
         * Mockito.verifyNoMoreInteractions(hashStrategy);
         */
    }

    @Test
    public void testBuildConsistentHashRing() throws Exception {
        // when(hashStrategy.getHashCode(anyString())).thenReturn(0);

        // 调用无权限的构造方法
        consistentHashRing = (ConsistentHashRing)Whitebox.invokeConstructor(ConsistentHashRing.class);
        // 调用无权限的方法
        TreeMap<Integer, Server> result = (TreeMap<Integer, Server>)Whitebox.invokeMethod(consistentHashRing,
            "buildConsistentHashRing", Arrays.<Server>asList(new Server("url")));
        Assert.assertEquals(10, result.entrySet().size());
    }

    @Test
    public void testLocate() {
        Server result = ConsistentHashRing.getInstance(Arrays.<Server>asList(new Server("url")))
                .locate(Integer.valueOf(0));
        Assert.assertNotNull(result);
    }
}
