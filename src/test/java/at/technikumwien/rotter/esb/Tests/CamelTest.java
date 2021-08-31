package at.technikumwien.rotter.esb.Tests;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CamelTest extends CamelTestSupport {

    private final byte[] msg_10 = new byte[10];
    private final byte[] msg_100 = new byte[100];
    private final byte[] msg_1000 = new byte[1000];

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start").to("mock:result");
            }
        };
    }

    @Test
    public void Test_10Byte_100Messages_Avg100Runs() {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            long start = System.currentTimeMillis();
            for(int j = 0; j<100;j++)
                template.sendBody("direct:start", msg_10 );
            getMockEndpoint("mock:result").expectedBodiesReceived(msg_10);
            times.add(System.currentTimeMillis()-start);
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("\n Average time over 100 iterations: " + avg + "ms \n");
    }

    @Test
    public void Test_10Byte_500Messages_Avg100Runs() {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            long start = System.currentTimeMillis();
            for(int j = 0; j<500;j++)
                template.sendBody("direct:start", msg_10 );
            getMockEndpoint("mock:result").expectedBodiesReceived(msg_10);
            times.add(System.currentTimeMillis()-start);
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("\n Average time over 100 iterations: " + avg + "ms \n");
    }

    @Test
    public void Test_10Byte_1000Messages_Avg100Runs() {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            long start = System.currentTimeMillis();
            for(int j = 0; j<1000;j++)
                template.sendBody("direct:start", msg_10 );
            getMockEndpoint("mock:result").expectedBodiesReceived(msg_10);
            times.add(System.currentTimeMillis()-start);
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("\n Average time over 100 iterations: " + avg + "ms \n");
    }

    @Test
    public void Test_100Byte_100Messages_Avg100Runs() {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            long start = System.currentTimeMillis();
            for(int j = 0; j<100;j++)
                template.sendBody("direct:start", msg_100 );
            getMockEndpoint("mock:result").expectedBodiesReceived(msg_100);
            times.add(System.currentTimeMillis()-start);
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("\n Average time over 100 iterations: " + avg + "ms \n");
    }

    @Test
    public void Test_100Byte_500Messages_Avg100Runs() {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            long start = System.currentTimeMillis();
            for(int j = 0; j<500;j++)
                template.sendBody("direct:start", msg_100 );
            getMockEndpoint("mock:result").expectedBodiesReceived(msg_100);
            times.add(System.currentTimeMillis()-start);
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("\n Average time over 100 iterations: " + avg + "ms \n");
    }

    @Test
    public void Test_100Byte_1000Messages_Avg100Runs() {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            long start = System.currentTimeMillis();
            for(int j = 0; j<1000;j++)
                template.sendBody("direct:start", msg_100 );
            getMockEndpoint("mock:result").expectedBodiesReceived(msg_100);
            times.add(System.currentTimeMillis()-start);
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("\n Average time over 100 iterations: " + avg + "ms \n");
    }

    @Test
    public void Test_1000Byte_100Messages_Avg100Runs() {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            long start = System.currentTimeMillis();
            for(int j = 0; j<100;j++)
                template.sendBody("direct:start", msg_1000 );
            getMockEndpoint("mock:result").expectedBodiesReceived(msg_1000);
            times.add(System.currentTimeMillis()-start);
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("\n Average time over 100 iterations: " + avg + "ms \n");
    }

    @Test
    public void Test_1000Byte_500Messages_Avg100Runs() {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            long start = System.currentTimeMillis();
            for(int j = 0; j<500;j++)
                template.sendBody("direct:start", msg_1000 );
            getMockEndpoint("mock:result").expectedBodiesReceived(msg_1000);
            times.add(System.currentTimeMillis()-start);
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("\n Average time over 100 iterations: " + avg + "ms \n");
    }

    @Test
    public void Test_1000Byte_1000Messages_Avg100Runs() {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            long start = System.currentTimeMillis();
            for(int j = 0; j<1000;j++)
                template.sendBody("direct:start", msg_1000 );
            getMockEndpoint("mock:result").expectedBodiesReceived(msg_1000);
            times.add(System.currentTimeMillis()-start);
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        System.out.println("\n Average time over 100 iterations: " + avg + "ms \n");
    }

//    @Test
//    public void Test1() throws InterruptedException {
//        MockEndpoint result = context.getEndpoint("mock:result",MockEndpoint.class);
//        result.expectedBodiesReceived(List.of(msg_1000,msg_10));
//
//        template.sendBody("direct:start", msg_1000 );
//
//        result.expectedMessageCount(3);
//        result.assertIsSatisfied();
//    }

}
