package com.richard.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="用户对象User",description="用户对象User")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
     * 主键ID
     */
    @Id
    @ApiModelProperty(value="库表主键id",name="id",example="1")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名",name="username",example="richard")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码",name="password",example="123456")
    private String password;

    /**
     * 性别
     */
    @ApiModelProperty(value="性别",name="sex",example="1")
    private Integer sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄",name="age",example="19")
    private Integer age;

    /**
     * 生日
     */
    @ApiModelProperty(value="生日",name="birth",example="2000-01-01")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birth;

    /**
     * 注册时间
     */
    @ApiModelProperty(value="注册时间",name="registtime",example="2018-09-08 13:44:31")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date registtime;

    /**
     * 归属公司id
     */
    @ApiModelProperty(value="归属公司id",name="companyid",example="sdfghjiuytrew26yvcx")
    private String companyid;

    /**
     * 所属门店id
     */
    @ApiModelProperty(value="归属门店id",name="storeid",example="sdfghjiuytrew26yvcx")
    private String storeid;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取生日
     *
     * @return birth - 生日
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * 设置生日
     *
     * @param birth 生日
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * 获取注册时间
     *
     * @return registtime - 注册时间
     */
    public Date getRegisttime() {
        return registtime;
    }

    /**
     * 设置注册时间
     *
     * @param registtime 注册时间
     */
    public void setRegisttime(Date registtime) {
        this.registtime = registtime;
    }

    /**
     * 获取归属公司id
     *
     * @return companyid - 归属公司id
     */
    public String getCompanyid() {
        return companyid;
    }

    /**
     * 设置归属公司id
     *
     * @param companyid 归属公司id
     */
    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    /**
     * 获取所属门店id
     *
     * @return storeid - 所属门店id
     */
    public String getStoreid() {
        return storeid;
    }

    /**
     * 设置所属门店id
     *
     * @param storeid 所属门店id
     */
    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }
}