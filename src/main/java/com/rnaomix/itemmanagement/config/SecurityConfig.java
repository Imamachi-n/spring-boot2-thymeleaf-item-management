package com.rnaomix.itemmanagement.config;

import com.rnaomix.itemmanagement.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Thymeleafとともに使うことで、formの中にCSRFのトークンを自動で埋め込む
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するファイルを設定
        // 静的リソース（images, css, javascript）に対するアクセスを許可
        web.ignoring().antMatchers(
                "/img/**",
                "/css/**",
                "/js/**",
                "/webjars/**",
                "/font/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // 認可の設定
        http.authorizeRequests()
                .antMatchers("/login/**").permitAll()  // loginは全ユーザーアクセス許可
                .anyRequest().authenticated()  // それ以外へのアクセスは認証が必須
                .and()
        // ログイン設定
            .formLogin()
                .loginProcessingUrl("/login")    // 認証処理のパス
                .loginPage("/login/index")    // ログインフォーム表示用のパス
//                .failureHandler(new SimpleUrlAuthenticationFailureHandler())    // 認証失敗時
                .failureUrl("/login/index?error=true") // 認証失敗時のパス
                .defaultSuccessUrl("/home") // 認証成功時の遷移先
                .usernameParameter("username")  // ユーザ名のリクエストパラメータ
                .passwordParameter("password")    // パスワードのリクエストパラメータ
                .and()
        // ログアウト設定
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))   // ログアウト処理のパス
                .logoutSuccessUrl("/login/index");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 認証するユーザーを設定する
        auth.userDetailsService(new UserDetailsServiceImpl())
                // 入力値をBCryptでハッシュ化した値でパスワード認証を行う
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}