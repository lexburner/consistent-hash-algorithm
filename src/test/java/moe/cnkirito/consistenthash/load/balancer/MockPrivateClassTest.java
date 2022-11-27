package moe.cnkirito.consistenthash.load.balancer;

import moe.cnkirito.consistenthash.LoadBalanceable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author dailj
 * @date 2022/11/28 22:09
 */
@RunWith(PowerMockRunner.class)
public class MockPrivateClassTest {

    @Spy
    private MockPrivateClass mockPrivateClass = Mockito.spy(MockPrivateClass.class);

    @Test
    @PrepareForTest({MockPrivateClass.class})
    public void testMockPrivateFunc() throws Exception {
        // mockPrivateClass = Mockito.mock(MockPrivateClass.class);
        PowerMockito.when(mockPrivateClass, "privateFunc").thenReturn("test");
        String realResult = mockPrivateClass.mockPrivateFunc();
        Assert.assertEquals("test", realResult);
        PowerMockito.verifyPrivate(mockPrivateClass, Mockito.times(1)).invoke("privateFunc");
    }
}
