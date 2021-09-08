package at.technikumwien.rotter.esb.Tests;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CamelTest extends CamelTestSupport {

    private final byte[] msg_10 = new byte[10];
    private final byte[] msg_100 = new byte[100];
    private final byte[] msg_1000 = new byte[1000];
    private final byte[] msg_10000 = new byte[10000];

    private final String str_10 = Arrays.toString(msg_10).replaceAll("[ ,\\[\\]]","");
    private final String str_100 = Arrays.toString(msg_100).replaceAll("[ ,\\[\\]]","");
    private final String str_1000 = Arrays.toString(msg_1000).replaceAll("[ ,\\[\\]]","");
    private final String str_10000 = Arrays.toString(msg_10000).replaceAll("[ ,\\[\\]]","");

    private static final Map<String, Long> results = new TreeMap<>();

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start").to("mock:result");
            }
        };
    }

    /* ----------- Multiple Message Sending Test - 10 Byte Message Size - 100 runs for averaging ----------- */
    @Test
    public void Test_10Byte_100Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<100;j++)
                template.sendBody("direct:start", str_10 );

            getMockEndpoint("mock:result").expectedMessageCount(100);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("01: 10Byte_100Msg",avg);
    }

    @Test
    public void Test_10Byte_500Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<500;j++)
                template.sendBody("direct:start", str_10 );

            getMockEndpoint("mock:result").expectedMessageCount(500);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("02: 10Byte_500Msg",avg);
    }

    @Test
    public void Test_10Byte_1000Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<1000;j++)
                template.sendBody("direct:start", str_10 );

            getMockEndpoint("mock:result").expectedMessageCount(1000);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("03: 10Byte_1000Msg",avg);
    }

    @Test
    public void Test_10Byte_1500Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<1500;j++)
                template.sendBody("direct:start", str_10 );

            getMockEndpoint("mock:result").expectedMessageCount(1500);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("04: 10Byte_1500Msg",avg);
    }

    @Test
    public void Test_10Byte_2000Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<2000;j++)
                template.sendBody("direct:start", str_10 );

            getMockEndpoint("mock:result").expectedMessageCount(2000);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("05: 10Byte_2000Msg",avg);
    }

    /* ----------- Multiple Message Sending Test - 100 Byte Message Size - 100 runs for averaging ----------- */
    @Test
    public void Test_100Byte_100Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<100;j++)
                template.sendBody("direct:start", str_100 );

            getMockEndpoint("mock:result").expectedMessageCount(100);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("06: 100Byte_100Msg",avg);
    }

    @Test
    public void Test_100Byte_500Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<500;j++)
                template.sendBody("direct:start", str_100 );

            getMockEndpoint("mock:result").expectedMessageCount(500);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("07: 100Byte_500Msg",avg);
    }

    @Test
    public void Test_100Byte_1000Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<1000;j++)
                template.sendBody("direct:start", str_100 );

            getMockEndpoint("mock:result").expectedMessageCount(1000);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("08: 100Byte_1000Msg",avg);
    }

    @Test
    public void Test_100Byte_1500Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<1500;j++)
                template.sendBody("direct:start", str_100 );

            getMockEndpoint("mock:result").expectedMessageCount(1500);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("09: 100Byte_1500Msg",avg);
    }

    @Test
    public void Test_100Byte_2000Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<2000;j++)
                template.sendBody("direct:start", str_100 );

            getMockEndpoint("mock:result").expectedMessageCount(2000);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("10: 100Byte_2000Msg",avg);
    }

    /* ----------- Multiple Message Sending Test - 1000 Byte Message Size - 100 runs for averaging ----------- */
    @Test
    public void Test_1000Byte_100Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<100;j++)
                template.sendBody("direct:start", str_1000 );

            getMockEndpoint("mock:result").expectedMessageCount(100);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("11: 1000Byte_100Msg",avg);
    }

    @Test
    public void Test_1000Byte_500Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<500;j++)
                template.sendBody("direct:start", str_1000 );

            getMockEndpoint("mock:result").expectedMessageCount(500);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("12: 1000Byte_500Msg",avg);
    }

    @Test
    public void Test_1000Byte_1000Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<1000;j++)
                template.sendBody("direct:start", str_1000 );

            getMockEndpoint("mock:result").expectedMessageCount(1000);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("13: 1000Byte_1000Msg",avg);
    }

    @Test
    public void Test_1000Byte_1500Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<1500;j++)
                template.sendBody("direct:start", str_1000 );

            getMockEndpoint("mock:result").expectedMessageCount(1500);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("14: 1000Byte_1500Msg",avg);
    }

    @Test
    public void Test_1000Byte_2000Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<2000;j++)
                template.sendBody("direct:start", str_1000 );

            getMockEndpoint("mock:result").expectedMessageCount(2000);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("15: 1000Byte_2000Msg",avg);
    }

    /* ----------- Multiple Message Sending Test - 10000 Byte Message Size - 100 runs for averaging ----------- */
    @Test
    public void Test_10000Byte_100Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<100;j++)
                template.sendBody("direct:start", str_10000 );

            getMockEndpoint("mock:result").expectedMessageCount(100);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("16: 10000Byte_100Msg",avg);
    }

    @Test
    public void Test_10000Byte_500Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<500;j++)
                template.sendBody("direct:start", str_10000 );

            getMockEndpoint("mock:result").expectedMessageCount(500);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("17: 10000Byte_500Msg",avg);
    }

    @Test
    public void Test_10000Byte_1000Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<1000;j++)
                template.sendBody("direct:start", str_10000 );

            getMockEndpoint("mock:result").expectedMessageCount(1000);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("18: 10000Byte_1000Msg",avg);
    }

    @Test
    public void Test_10000Byte_1500Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<1500;j++)
                template.sendBody("direct:start", str_10000 );

            getMockEndpoint("mock:result").expectedMessageCount(1500);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("19: 10000Byte_1500Msg",avg);
    }

    @Test
    public void Test_10000Byte_2000Messages_Avg100Runs() throws InterruptedException {
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<100;i++){
            getMockEndpoint("mock:result").reset();
            long start = System.currentTimeMillis();
            for(int j = 0; j<2000;j++)
                template.sendBody("direct:start", str_10000 );

            getMockEndpoint("mock:result").expectedMessageCount(2000);
            getMockEndpoint("mock:result").assertIsSatisfied();
            times.add(System.currentTimeMillis()-start);
            //System.out.println("Number of messages received: " + getMockEndpoint("mock:result").getReceivedCounter());
        }
        long avg = (long) times.stream().mapToLong(l -> l).average().getAsDouble();
        results.put("20: 10000Byte_2000Msg",avg);
    }

    @AfterAll
    static void getResults() {
        results.forEach((str,l) -> System.out.println(str + " - " + l + " ms"));
    }

}
