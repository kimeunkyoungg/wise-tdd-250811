import com.back.App;
import com.back.AppContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppContext.init();
        new App().run();
        //experiment1();
        //experiment2();
    }





    //입력 자동화 테스트
    public static void experiment1(){

        String input= """
                    등록
                    너 자신을 알라
                   """;
        Scanner sc = new Scanner(input);

        String cmd = sc.nextLine();
        String saying = sc.nextLine();


        System.out.println("입력한 명령어: " + cmd);

        System.out.println("입력한 명언: " + saying);
    }

    //출력 자동화 테스트
    public static void experiment2(){

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        System.setOut(printStream);
    }
}
