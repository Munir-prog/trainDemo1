package com.mprog;

import com.mprog.traindemo1.service.TicketService;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class Test {


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generatePassengerNo());
        }
    }

    private static String generatePassengerNo() {
        var random = new Random();
        var i = random.nextInt(3);
        var builder = new StringBuilder();
        if (i == 0){
            appendThreeNumbers(random, builder);
            appendThreeNumbers(random, builder);
            return builder.toString();
        } else if (i == 1){
            appendThreeChars(random, builder);
            appendThreeChars(random, builder);
        } else {
            var i1 = random.nextInt(2);
            if (i1 == 0){
                appendThreeChars(random, builder);
                appendThreeNumbers(random, builder);
            }
            if (i1 == 1){
                appendThreeNumbers(random, builder);
                appendThreeChars(random, builder);
            }
        }
        return builder.toString();
    }

    private static void appendThreeChars(Random random, StringBuilder builder) {
        for (int i = 0; i < 3; i++) {
            builder.append((char) random.nextInt(65, 91));
        }
    }

    private static void appendThreeNumbers(Random random, StringBuilder builder) {
        for (int j = 0; j < 3; j++) {
            builder.append(random.nextInt(10));
        }
    }
}
