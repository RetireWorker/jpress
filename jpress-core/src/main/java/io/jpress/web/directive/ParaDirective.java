/**
 * Copyright (c) 2016-2019, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.web.directive;

import com.jfinal.core.Controller;
import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import io.jboot.utils.StrUtils;
import io.jboot.web.JbootControllerContext;
import io.jboot.web.directive.annotation.JFinalDirective;
import io.jboot.web.directive.base.JbootDirectiveBase;
import io.jpress.service.RoleService;

import javax.inject.Inject;
import java.io.IOException;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Package io.jpress.core.directives
 */
@JFinalDirective("para")
public class ParaDirective extends JbootDirectiveBase {

    @Inject
    private RoleService roleService;

    @Override
    public void onRender(Env env, Scope scope, Writer writer) {

        Controller controller = JbootControllerContext.get();


        String key = getPara(0, scope);
        String defaultValue = getPara(1, scope);

        if (StrUtils.isBlank(key)) {
            throw new IllegalArgumentException("#para(...) argument must not be empty");
        }

        String value = controller.getPara(key);
        if (StrUtils.isBlank(value)) {
            value = StrUtils.isNotBlank(defaultValue) ? defaultValue : "";
        }


        try {
            writer.write(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

