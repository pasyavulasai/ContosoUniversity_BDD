//package com.test.kafka;
//
//import kafka.common.FailedToSendMessageException;
//import kafka.javaapi.producer.Producer;
//import kafka.producer.KeyedMessage;
//import kafka.producer.ProducerConfig;
//import org.apache.commons.io.FileUtils;
//import org.joda.time.DateTime;
//import org.joda.time.DateTimeZone;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Properties;
//
///**
// * Created by c0250492 on 07/11/2017.
// */
//public class SendToKafka {
//
//    private static Producer<Integer, String> producer;
//    private final static Properties properties = new Properties();
//    public static String topic;
//
//    public static void main(String[] args) throws IOException, ParseException {
//
//        kafkaConnection(args[0]);
//
//        JSONObject customDetails = new JSONObject();
//        customDetails.put("projectName", args[2]);
//        customDetails.put("environment", args[3]);
//        customDetails.put("platform", args[4]);
//        customDetails.put("release", args[5]);
//        customDetails.put("sprint", args[6]);
//        customDetails.put("workarea", args[7]);
//        customDetails.put("program", args[8]);
//        customDetails.put("executiondate", new DateTime(DateTimeZone.getDefault()).toString());
//
//        topic = args[1];
//        System.out.println("Kafkaserver:"+ args[0]);
//        System.out.println("Topic details:"+ args[1]);
//
//        customDetails.toString();
//        addCustomTagsAndSendToKafka(customDetails);
//
//    }
//
//    public static void kafkaConnection(String kafkaServer) {
//        properties.put("metadata.broker.list", kafkaServer);
//        properties.put("serializer.class", "kafka.serializer.StringEncoder");
//        properties.put("request.required.acks", "1");
//        properties.put("producer.type", "sync");
//        producer = new Producer<>(new ProducerConfig(properties));
//    }
//
//    public static void addCustomTagsAndSendToKafka(JSONObject customDetails) throws ParseException, IOException {
//
//        JSONParser fileParser = new JSONParser();
//        JSONArray additionalCustomDetails = new JSONArray();
//        JSONArray finalData =  new JSONArray();
//
//        try {
//            String userhome = System.getProperty("user.dir", "./");
//            System.out.println(userhome);
//            String files = userhome+"/Automation_Framework_Web/target/cucumber-report.json";
//
//            JSONArray jsonArray = (JSONArray) fileParser.parse(new FileReader(files));
//
//            additionalCustomDetails.add((Object) customDetails);
//
//            for (Object o : jsonArray) {
//                JSONObject jsonObject = (JSONObject) o;
//                jsonObject.put("projectcustomtags", additionalCustomDetails);
//
//                finalData.add(jsonObject);
//                System.out.println(finalData.toString());
//
//                KeyedMessage<Integer, String> data = new KeyedMessage<>(topic, finalData.toString());
//                System.out.println("Size of the message: "+FileUtils.byteCountToDisplaySize(finalData.toString().getBytes("UTF-8").length));
//                producer.send(data);
//
//            }
//
//        }catch(FailedToSendMessageException fts){
//            System.out.println("Failed to send message to Kafka");
//            fts.printStackTrace();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            producer.close();
//        }
//    }
//
//}