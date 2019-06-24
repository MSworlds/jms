/*
 * Project Name: ids
 * File Name: Data.java
 * Class Name: Data
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

import java.util.Calendar;

/**
 * Description: TODO
 *
 * @author shuaimeng
 * @since 29.03.2019
 */
public class Data {


    public static void main(String[] args) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -4);
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.MONTH) + 1);


        System.out.println(String.format("%s%s%s%s%s", "1",2,"5",4,5));


    }
}
