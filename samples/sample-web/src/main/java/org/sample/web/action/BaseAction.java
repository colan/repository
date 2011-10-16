package org.sample.web.action;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.easyssh.framework.web.struts2.PageAction;

@Controller
@Scope("prototype")
@ParentPackage("admin-package")
public class BaseAction extends PageAction {

	private static final long serialVersionUID = 8504539616009491463L;

}
