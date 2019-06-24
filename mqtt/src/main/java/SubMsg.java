/*
 * Project Name: ids
 * File Name: SubMsg.java
 * Class Name: SubMsg
 *
 * Copyright 2018 Hengtian Software Inc
 *
 * Licensed under the Hengtiansoft
 *
 * http://www.hengtiansoft.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: TODO
 *
 * @author shuaimeng
 * @since 29.03.2019
 */
public class SubMsg {
    private static int qos = 2;
    private static String broker = "tcp://localhost:1883";
    private static String userName = "admin";
    private static String passWord = "password";


    private static MqttClient connect(String clientId) throws MqttException{
        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions connOpts = new MqttConnectOptions();
//    	 String[] uris = {"tcp://10.100.124.206:1883","tcp://10.100.124.206:1883"};
        connOpts.setCleanSession(false);
        connOpts.setUserName(userName);
        connOpts.setPassword(passWord.toCharArray());
        connOpts.setConnectionTimeout(10);
        connOpts.setKeepAliveInterval(20);
//         connOpts.setServerURIs(uris);
//         connOpts.setWill(topic, "close".getBytes(), 2, true);
        MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {


                String msg = new String(mqttMessage.getPayload());

                System.out.println( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " " + msg);



            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
        mqttClient.connect(connOpts);
        return mqttClient;
    }

    public static void sub(MqttClient mqttClient, String topic) throws MqttException {
        int[] Qos  = {qos};
        String[] topics = {topic};
        mqttClient.subscribe(topics, Qos);
    }


    private static void runsub(String clientId, String topic) throws MqttException{
        MqttClient mqttClient = connect(clientId);
        if(mqttClient != null){
            sub(mqttClient,topic);
        }
    }
    public static void main(String[] args) throws MqttException{

        runsub("test_client1", "meng/+");
    }


}
