/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medilab.gerenciaprojeto.modelos;

/**
 *
 * @author william.vieira
 */
public class CheckList {

    int codigo;
    String fl_captcha;
    String fl_http;
    String fl_https;
    String fl_seguranca;
    String fl_html;
    String fl_java;
    String fl_rest;
    String fl_tomcat;
    String fl_vue;
    String fl_servlet;
    String fl_python;

    public CheckList(){
        
    }

    public CheckList(int codigo, String fl_captcha, String fl_http, String fl_https, String fl_seguranca, String fl_html, String fl_java, String fl_rest, String fl_tomcat, String fl_vue, String fl_servlet, String fl_python) {
        this.codigo = codigo;
        this.fl_captcha = fl_captcha;
        this.fl_http = fl_http;
        this.fl_https = fl_https;
        this.fl_seguranca = fl_seguranca;
        this.fl_html = fl_html;
        this.fl_java = fl_java;
        this.fl_rest = fl_rest;
        this.fl_tomcat = fl_tomcat;
        this.fl_vue = fl_vue;
        this.fl_servlet = fl_servlet;
        this.fl_python = fl_python;
    }
    
    public CheckList(String fl_captcha, String fl_http, String fl_https, String fl_seguranca, String fl_html, String fl_java, String fl_rest, String fl_tomcat, String fl_vue, String fl_servlet, String fl_python) {
        this.fl_captcha = fl_captcha;
        this.fl_http = fl_http;
        this.fl_https = fl_https;
        this.fl_seguranca = fl_seguranca;
        this.fl_html = fl_html;
        this.fl_java = fl_java;
        this.fl_rest = fl_rest;
        this.fl_tomcat = fl_tomcat;
        this.fl_vue = fl_vue;
        this.fl_servlet = fl_servlet;
        this.fl_python = fl_python;
    }
    
    
    public String getFl_captcha() {
        return fl_captcha;
    }

    public void setFl_captcha(String fl_captcha) {
        this.fl_captcha = fl_captcha;
    }

    public String getFl_http() {
        return fl_http;
    }

    public void setFl_http(String fl_http) {
        this.fl_http = fl_http;
    }

    public String getFl_https() {
        return fl_https;
    }

    public void setFl_https(String fl_https) {
        this.fl_https = fl_https;
    }

    public String getFl_seguranca() {
        return fl_seguranca;
    }

    public void setFl_seguranca(String fl_seguranca) {
        this.fl_seguranca = fl_seguranca;
    }

    public String getFl_html() {
        return fl_html;
    }

    public void setFl_html(String fl_html) {
        this.fl_html = fl_html;
    }

    public String getFl_java() {
        return fl_java;
    }

    public void setFl_java(String fl_java) {
        this.fl_java = fl_java;
    }

    public String getFl_rest() {
        return fl_rest;
    }

    public void setFl_rest(String fl_rest) {
        this.fl_rest = fl_rest;
    }

    public String getFl_tomcat() {
        return fl_tomcat;
    }

    public void setFl_tomcat(String fl_tomcat) {
        this.fl_tomcat = fl_tomcat;
    }

    public String getFl_vue() {
        return fl_vue;
    }

    public void setFl_vue(String fl_vue) {
        this.fl_vue = fl_vue;
    }

    public String getFl_servlet() {
        return fl_servlet;
    }

    public void setFl_servlet(String fl_servlet) {
        this.fl_servlet = fl_servlet;
    }

    public String getFl_python() {
        return fl_python;
    }

    public void setFl_python(String fl_python) {
        this.fl_python = fl_python;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    

}
