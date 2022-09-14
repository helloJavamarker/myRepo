package com.test.mark.zhang.test.other.project.asset.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by mark
 * @Classname AddressRegUtil
 * @Description TODO
 * @Date 2022/8/4 11:48 上午
 */
public class AddressRegUtil {

    /**
     * ocr处理结果矫正
     *
     * @param address ocr识别地址
     * @return OcrModel
     */
    public static List<Object> ocrConvertData(String address) {

//        List<AddressModel> result = Lists.newArrayList();
        List<Object> result = new ArrayList<>();

        if (StringUtils.isBlank(address)) {
            return result;
        }

        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)" +
                "(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)" +
                "(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)" +
                "?(?<village>.*)";

        Matcher m = Pattern.compile(regex).matcher(address);

        while (m.find()) {
//            AddressModel addressModel = AddressModel.builder().build();
//            String province = m.group("province");
//            addressModel.setProvinceText(province == null ? "" : province.trim());
//
//            String  city = m.group("city");
//            addressModel.setCityText(city == null ? "" : city.trim());
//
//            String district = m.group("county");
//            addressModel.setDistrictText(district == null ? "" : district.trim());
//
//            String street = m.group("town");
//            addressModel.setStreetText(street == null ? "" : street.trim());
//
//            result.add(addressModel);
        }
        return result;
    }



    public static String addressCutting(String address) {
        //序列化
        if (address.startsWith("北京市") || address.startsWith("天津市") || address.startsWith("上海市") || address.startsWith("重庆市")) {
            address = address.substring(0, 3) + "市辖区" + address.substring(3);
        }
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)" +
                "(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)" +
                "(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)" +
                "?(?<town>[^区]+区|.+镇)" +
                "?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null, city = null, county = null, town = null, village = null;
        while(m.find()){
            province = m.group("province");

//            if ("北京市".equals(province) || "天津市".equals(province) || "上海市".equals(province) || "重庆市".equals(province)) {
            if (StringUtils.containsAny("上海", province) || StringUtils.containsAny("北京", province) || StringUtils.containsAny("天津", province) || StringUtils.containsAny("重庆", province)) {
                city = province;

                county = m.group("city");
                if (county.split("区").length > 1) {
                    town = county.substring(county.indexOf("区") + 1);
                    county = county.substring(0, county.indexOf("区") + 1);
                    if (town.contains("区")) {
                        town = town.substring(county.indexOf("区") + 1);
                    }
                } else {
                    county = m.group("county");
                    if (county.split("区").length > 1) {
                        town = county.substring(county.indexOf("区") + 1);
                        county = county.substring(0, county.indexOf("区") + 1);
                    }
                }
            } else {
                city = m.group("city");

                county = m.group("county");
                if (county != null && !"".equals(county)) {
                    if (county.split("市").length > 1 && county.indexOf("市") < 5) {
                        town = county;
                        county = county.substring(0, county.indexOf("市") + 1);
                        town = town.substring(county.indexOf("市") + 1);
                    }
                    if (county.split("旗").length > 1) {
                        town = county;
                        county = county.substring(0, county.indexOf("旗") + 1);
                        town = town.substring(county.indexOf("旗") + 1);
                    }
                    if (county.split("海域").length > 1) {
                        town = county;
                        county = county.substring(0, county.indexOf("海域") + 2);
                        town = town.substring(county.indexOf("海域") + 2);
                    }
                    if (county.split("区").length > 1) {
                        town = county;
                        county = county.substring(0, county.indexOf("区") + 1);
                        town = town.substring(county.indexOf("区") + 1);
                    }
                }

            }

            if (province != null && !"".equals(province)) {
                province = province + "-";
            }

            if (city != null && !"".equals(city)) {
                city = city + "-";
            }

            if (county != null && !"".equals(county)) {
                county = county + "-";
            }

            town+=m.group("town");
            if ((county == null || "".equals(county)) && town != null && !"".equals(town)) {
                town = town + "-";
            }
            village=m.group("village");

        }

        String newMachineAdress = province + city + county + town + village;
        if (!"".equals(newMachineAdress)) {
            newMachineAdress = newMachineAdress.replaceAll("null", "");
        }

        if ("".equals(newMachineAdress)) {
            newMachineAdress = address;
        }

        return newMachineAdress;
    }

    public static void main(String[] args) {
        System.out.println(addressCutting("北 京市朝阳区幸福小区101"));
        System.out.println(addressCutting("上海 市黄浦区鑫浩壹都市 A座10楼"));
        System.out.println(addressCutting(" 重庆市黄浦区鑫浩壹都市 A座10楼"));
        System.out.println(addressCutting("杭州市拱墅区西夏市小区"));

        System.out.println(addressCutting("广东省/深圳市/罗湖区/幸福小市XX区"));
        System.out.println(addressCutting("广东省深圳市 罗湖区-幸福小市XX区"));
        System.out.println(addressCutting("深圳市罗湖区幸福小区XX区"));

        System.out.println(addressCutting("湖南省长沙 市某市幸福小区"));
        System.out.println(addressCutting("河南省周口市项城市县政府大院1栋103"));

        System.out.println(addressCutting("广东省深圳市南山区桃源街道桃源社区高发西路XX号深圳市XX公司X层A区"));

        System.out.println(addressCutting("广xx东省深xx圳市南山区北环路第五工业区XX楼三楼A区"));

        System.out.println(addressCutting("天津市和平区和平区南市盒华安大街交口XX有限公司"));

        System.out.println(addressCutting("天津市和平区南市盒华安大街交口XX有限公司"));

        System.out.println(addressCutting("湖北省武汉市洪山区XX街道"));

        System.out.println(addressCutting("湖北省恩施土家族苗族自治州恩施市XX区"));

        System.out.println(addressCutting("内蒙古自治区兴安盟科尔沁右翼前旗XX区"));

        System.out.println(addressCutting("西藏自治区日喀则地区日喀则市XX区"));

        System.out.println(addressCutting("海南省省直辖县级行政单位中沙群岛的岛礁及其海域XX区"));


        System.out.println(StringUtils.containsAny("上海", " 上 海 市"));
    }
}
