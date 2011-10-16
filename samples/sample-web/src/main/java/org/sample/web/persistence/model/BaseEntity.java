/**
 * 
 */
package org.sample.web.persistence.model;

import javax.persistence.MappedSuperclass;

import com.easyssh.framework.entity.mysql.MysqlBaseEntity;

/**
 * 模型基类
 */
@MappedSuperclass
public abstract class BaseEntity extends MysqlBaseEntity {

	private static final long serialVersionUID = 1L;
	
	
}
