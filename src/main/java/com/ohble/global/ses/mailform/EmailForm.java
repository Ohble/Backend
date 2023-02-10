package com.ohble.global.ses.mailform;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmailForm {

    public String setAdminAuthFormSubject() {
        return "Ohble - 관리자 계정 인증 요청입니다.";
    }


    public String buildAdminAuthForm(String payload) {
        return "<center>\n" +
                "\t<div class=container>\n" +
                "\t\t안녕하세요. Ohble API 서버에서 관리자 인증 메일 발송합니다.\n" +
                "\t\t<p>\n" +
                "\t\t<br>\n" +
                "\t\t" + payload + "\n" +
                "                <p>\n" +
                "<br>" +
                "                <b>회원가입 화면</b>에서 위 <b>인증번호를 입력</b>하시면 정상적으로 서비스 이용이 가능합니다." + "\n" +
                "<br>" +
                "\t\t인증이 정상적으로 수행되지 않을 시, kitten.diger@gmail.com 으로 문의 부탁드립니다. \n" +
                "\t\t<p>\n" +
                "\t\t감사합니다.\n" +
                "\t</div>\n" +
                "</center>";
    }
}
