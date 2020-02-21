package com.springboot.cloud.auth.client.config;

import feign.Feign;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@AutoConfigureBefore(FeignAutoConfiguration.class)// FeignOkHttpConfig 在 FeignAutoConfiguration 之前加载
@Configuration
@ConditionalOnClass(Feign.class)// Feign 实例化成功 FeignOkHttpConfig才会实例化（当给定的类名在类路径上存在，则实例化当前Bean）
/****
 *     需要修改成OKHTTP的客户端，需要在配置文件增加
 *     feign.httpclient.enabled=false
	   feign.okhttp.enabled=true
       OkHttp是一个高效的HTTP客户端，它有以下默认特性：
		 支持HTTP/2，允许所有同一个主机地址的请求共享同一个socket连接
		 连接池减少请求延时
		 透明的GZIP压缩减少响应数据的大小
		 缓存响应内容，避免一些完全重复的请求

 */
public class FeignOkHttpConfig {

	private int feignOkHttpReadTimeout = 60;
	private int feignConnectTimeout = 60;
	private int feignWriteTimeout = 120;

	@Bean
	public okhttp3.OkHttpClient okHttpClient() {
		return new okhttp3.OkHttpClient.Builder()
				.readTimeout(feignOkHttpReadTimeout, TimeUnit.SECONDS)
				.connectTimeout(feignConnectTimeout, TimeUnit.SECONDS)
				.writeTimeout(feignWriteTimeout, TimeUnit.SECONDS)
//				.connectionPool(new ConnectionPool(int maxIdleConnections, long keepAliveDuration, TimeUnit timeUnit))   //自定义链接池
//				.addInterceptor(XXXXXXXInterceptor) 	//自定义拦截器
				.build();
	}
}
