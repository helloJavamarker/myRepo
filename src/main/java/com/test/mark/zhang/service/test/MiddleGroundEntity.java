package com.test.mark.zhang.service.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by mark
 * @Classname MiddleGroundEntity
 * @Description TODO
 * @Date 2021/11/9 2:48 下午
 */
@Data
@AllArgsConstructor
public class MiddleGroundEntity {

    private int id;
    private String type;

    static {
        System.out.println("static before");
    }
    static {
        System.out.println("static after");
        //TestService service = new TestService();
//        service.sout();
    }
    public static TestService entityBefore = new TestService();
    public static MiddleGroundEntity entity = new MiddleGroundEntity();

    public static void main(String[] args) {


    }

    public MiddleGroundEntity() {
        System.out.println("const....");
    }



    private static List<MiddleGroundEntity> sortData(String sortField) {
        return generateData().stream().sorted(Comparator.comparing(
                middleGroundEntity -> {
                    if (sortField.equals(middleGroundEntity.getType())) {
                        return "";
                    } else {
                        return middleGroundEntity.getType();
                    }
                },
                Comparator.nullsLast(String::compareTo)))
                .collect(Collectors.toList());

    }

    private static List<MiddleGroundEntity> generateData() {
        List<MiddleGroundEntity> list = new ArrayList<>();
        MiddleGroundEntity data1 = new MiddleGroundEntity(1, "data");
        MiddleGroundEntity data2 = new MiddleGroundEntity(2, "tech");
        MiddleGroundEntity data3 = new MiddleGroundEntity(3, "tech");
        MiddleGroundEntity data4 = new MiddleGroundEntity(4, null);
        MiddleGroundEntity data5 = new MiddleGroundEntity(5, "data");
        MiddleGroundEntity data6 = new MiddleGroundEntity(6, null);
        MiddleGroundEntity data7 = new MiddleGroundEntity(7, "tech");
        MiddleGroundEntity data8 = new MiddleGroundEntity(8, "data");
        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        list.add(data5);
        list.add(data6);
        list.add(data7);
        list.add(data8);
        return list;
    }

}
