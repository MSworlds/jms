/*
 * Project Name: ids
 * File Name: TestOutofMermoy.java
 * Class Name: TestOutofMermoy
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

import java.util.Map;
import java.util.Random;

/**
 * Description: TODO
 *
 * @author shuaimeng
 * @since 08.04.2019
 */
public class TestOutofMermoy {
    public static void main(String[] args) {
        while (true){
            Map map = System.getProperties();
            Random r = new Random();
            while (true) {
                map.put(r.nextInt(), "value");
            }
        }
    }
}
