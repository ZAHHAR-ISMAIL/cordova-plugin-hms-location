/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2025. All rights reserved.
 */

package com.huawei.hms.cordova.location.utils;

import java.util.Locale;

import java.math.BigDecimal;

public class GPSUtil {
    public static final double PI = 3.1415926535897932384626d;

    public static final double X_PI = div(mul(3.14159265358979324, 3000.0), 180.0);

    public static final double AA = 6378245.0d;

    public static final double EE = 0.00669342162296594323d;

    public static double transformLat(double abscissa, double ordinate) {
        double ret =
            -100.0 + mul(2.0, abscissa) + mul(3.0, ordinate) + mul(0.2, ordinate, ordinate) + mul(0.1, abscissa, ordinate) + mul(0.2, Math.sqrt(Math.abs(abscissa)));
        ret += div(mul(add(mul(20.0, Math.sin(mul(6.0, abscissa, PI))), mul(20.0, Math.sin(mul(2.0, abscissa, PI)))), 2.0), 3.0);
        ret += div(mul(add(mul(20.0, Math.sin(mul(ordinate, PI))), mul(40.0, Math.sin(mul(div(ordinate, 3.0), PI)))), 2.0), 3.0);
        ret +=
            div(mul(add(mul(160.0, Math.sin(mul(div(ordinate, 12.0), PI))), mul(320.0, Math.sin(div(mul(ordinate, PI), 30.0)))), 2.0),
                3.0);
        return ret;
    }

    public static double add(double parameter, double secParameter) {
        BigDecimal object = new BigDecimal(Double.toString(parameter));
        BigDecimal objectTwo = new BigDecimal(Double.toString(secParameter));
        return object.add(objectTwo).doubleValue();
    }

    public static double sub(double parameter, double secParameter) {
        BigDecimal object = new BigDecimal(Double.toString(parameter));
        BigDecimal objectTwo = new BigDecimal(Double.toString(secParameter));
        return object.subtract(objectTwo).doubleValue();
    }

    public static double mul(double parameter, double secParameter) {
        BigDecimal object = new BigDecimal(Double.toString(parameter));
        BigDecimal objectTwo = new BigDecimal(Double.toString(secParameter));
        return object.multiply(objectTwo).doubleValue();
    }

    public static double mul(double parameter, double secParameter, double thirdParameter) {
        BigDecimal object = new BigDecimal(Double.toString(parameter));
        BigDecimal objectTwo = new BigDecimal(Double.toString(secParameter));
        BigDecimal objectThree = new BigDecimal(Double.toString(thirdParameter));
        return object.multiply(objectTwo).multiply(objectThree).doubleValue();
    }

    public static double div(double parameter, double secParameter) {
        BigDecimal object = new BigDecimal(Double.toString(parameter));
        BigDecimal objectTwo = new BigDecimal(Double.toString(secParameter));
        return object.divide(objectTwo, 20, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double transformLon(double abscissa, double ordinate) {
        double ret = 300.0 + abscissa + mul(2.0, ordinate) + mul(0.1, abscissa, abscissa) + mul(0.1, abscissa, ordinate) + mul(0.1, Math.sqrt(Math.abs(abscissa)));
        ret += div(mul(add(mul(20.0, Math.sin(mul(6.0, abscissa, PI))), mul(20.0, Math.sin(mul(2.0, abscissa, PI)))), 2.0), 3.0);
        ret += div(mul(add(mul(20.0, Math.sin(mul(abscissa, PI))), mul(40.0, Math.sin(mul(div(abscissa, 3.0), PI)))), 2.0), 3.0);
        ret +=
            div(mul(add(mul(150.0, Math.sin(mul(div(abscissa, 12.0), PI))), mul(300.0, Math.sin(mul(div(abscissa, 30.0), PI)))), 2.0),
                3.0);
        return ret;
    }

    public static double[] transform(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new double[] {lat, lon};
        }
        double dLat = transformLat(sub(lon, 105.0), sub(lat, 35.0));
        double dLon = transformLon(sub(lon, 105.0), sub(lat, 35.0));
        double radLat = mul(div(lat, 180.0), PI);
        double magic = Math.sin(radLat);
        magic = 1 - EE * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        double magicProduct = (magic * sqrtMagic) * PI;
        double magicQuotient = 0.0d;
        if (magicProduct != 0) {
            magicQuotient = (AA * (1 - EE)) / magicProduct;
        }
        if (magicQuotient != 0) {
            dLat = (dLat * 180.0) / magicQuotient;
        }
        double sqrtProduct = sqrtMagic * Math.cos(radLat) * PI;
        double sqrtQuotient = 0.0d;
        if (sqrtProduct != 0) {
            sqrtQuotient = AA / sqrtProduct;
        }
        if (sqrtQuotient != 0) {
            dLon = (dLon * 180.0) / sqrtQuotient;
        }
        double mgLat = add(lat, dLat);
        double mgLon = add(lon, dLon);
        return new double[] {mgLat, mgLon};
    }

    public static boolean outOfChina(double latitude, double longitude) {
        if (latitude < 0.8293 || latitude > 55.8271) {
            return true;
        }
        if (longitude < 72.004 || longitude > 137.8347) {
            return true;
        }
        return false;
    }

    public static double[] gps84ToGcj02(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new double[] {lat, lon};
        }
        double dLat = transformLat(sub(lon, 105.0), sub(lat, 35.0));
        double dLon = transformLon(sub(lon, 105.0), sub(lat, 35.0));
        double radLat = mul(div(lat, 180.0), PI);
        double magic = Math.sin(radLat);
        magic = 1 - EE * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        if ((magic * sqrtMagic) * PI != 0) {
            double temp = (AA * (1 - EE)) / (magic * sqrtMagic) * PI;
            if (temp != 0) {
                dLat = (dLat * 180.0) / temp;
            }
        }
        if (sqrtMagic * Math.cos(radLat) * PI != 0) {
            double temp = AA / sqrtMagic * Math.cos(radLat) * PI;
            if (temp != 0) {
                dLon = (dLon * 180.0) /temp;
            }
        }
        double mgLat = add(lat, dLat);
        double mgLon = add(lon, dLon);
        return new double[] {mgLat, mgLon};
    }

    public static double[] gcj02ToGps84(double lat, double lon) {
        double[] gps = transform(lat, lon);
        double lontitude = lon * 2 - gps[1];
        double latitude = lat * 2 - gps[0];
        return new double[] {latitude, lontitude};
    }

    public static double[] gcj02ToBd09(double lat, double lon) {
        double lontitude = lon;
        double latitude = lat;
        double num = add(Math.sqrt(add(mul(lontitude, lontitude), mul(latitude, latitude))), mul(0.00002, Math.sin(latitude * X_PI)));
        double theta = add(Math.atan2(latitude, lontitude), mul(0.000003, Math.cos(mul(lontitude, X_PI))));
        double temLon = add(mul(num, Math.cos(theta)), 0.0065);
        double tempLat = add(mul(num, Math.sin(theta)), 0.006);
        return new double[] {tempLat, temLon};
    }

    public static double[] bd09ToGcj02(double lat, double lon) {
        double lontitude = sub(lon, 0.0065);
        double latitude = sub(lat, 0.006);
        double num = sub(Math.sqrt(add(mul(lontitude, lontitude), mul(latitude, latitude))), mul(0.00002, Math.sin(latitude * X_PI)));
        double theta = sub(Math.atan2(latitude, lontitude), mul(0.000003, Math.cos(lontitude * X_PI)));
        double tempLon = mul(num, Math.cos(theta));
        double tempLat = mul(num, Math.sin(theta));
        return new double[] {tempLat, tempLon};
    }

    public static double[] gps84ToBd09(double lat, double lon) {
        double[] gcj02 = gps84ToGcj02(lat, lon);
        return gcj02ToBd09(gcj02[0], gcj02[1]);
    }

    public static double[] bd09ToGps84(double lat, double lon) {
        double[] gcj02 = bd09ToGcj02(lat, lon);
        double[] gps84 = gcj02ToGps84(gcj02[0], gcj02[1]);
        gps84[0] = retain6(gps84[0]);
        gps84[1] = retain6(gps84[1]);
        return gps84;
    }

    private static double retain6(double num) {
        String result = String.format(Locale.ENGLISH, "%.6f", num);
        return Double.valueOf(result);
    }
}
