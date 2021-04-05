/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fastinjava.framework.codegen.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据源表
 *
 * @author lengleng
 * @date 2019-03-31 16:00:20
 */
@Data
public class GenDatasourceConfListDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * jdbcurl
	 */
	private String url;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 创建时间
	 */
	private String createDate;

	/**
	 * 更新
	 */
	private String updateDate;

	/**
	 * 删除标记
	 */
	private String delFlag;

}
