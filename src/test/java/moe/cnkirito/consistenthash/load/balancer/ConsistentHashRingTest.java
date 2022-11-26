package moe.cnkirito.consistenthash.load.balancer;

import moe.cnkirito.consistenthash.AbstractHashStrategy;
import moe.cnkirito.consistenthash.bo.Server;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

import static org.mockito.Mockito.*;

/**
 * @author dailj
 * @date 2022/11/26 14:51
 */
public class ConsistentHashRingTest {
    @Mock
    AbstractHashStrategy hashStrategy;
    ConsistentHashRing consistentHashRing;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetInstance() throws Exception {
        /// when(hashStrategy.getHashCode(anyString())).thenReturn(0);

        ConsistentHashRing result = ConsistentHashRing.getInstance(Arrays.<Server>asList(new Server("url")));
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void testLocate() throws Exception {
        /// when(hashStrategy.getHashCode(anyString())).thenReturn(0);

        consistentHashRing = ConsistentHashRing.getInstance(Arrays.<Server>asList(new Server("url")));
        Server result = consistentHashRing.locate(Integer.valueOf(0));
        Assert.assertNotNull(result.getUrl());
    }

    @Test
    public void testBuildConsistentHashRing() throws Exception {
        /// when(hashStrategy.getHashCode(anyString())).thenReturn(0);

        consistentHashRing = new ConsistentHashRing();
        TreeMap<Integer, Server> result = consistentHashRing.buildConsistentHashRing(
            Arrays.<Server>asList(new Server("url")));
        Assert.assertEquals(10, result.entrySet().size());
    }
}
