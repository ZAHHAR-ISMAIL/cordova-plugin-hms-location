/*
 * Copyright 2020-2025. Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huawei.hms.cordova.location.services;

import com.huawei.hms.cordova.location.basef.CordovaBaseModule;
import com.huawei.hms.cordova.location.basef.CordovaMethod;
import com.huawei.hms.cordova.location.basef.HMSLog;
import com.huawei.hms.cordova.location.basef.handler.CorPack;
import com.huawei.hms.cordova.location.basef.handler.Promise;
import com.huawei.hms.cordova.location.utils.json.ObjectToJSON;
import com.huawei.hms.support.api.entity.location.coordinate.LonLat;

import org.json.JSONArray;
import org.json.JSONException;

public class CoordinateConversionService extends CordovaBaseModule {

    @CordovaMethod
    @HMSLog
    public void convertCoord(final CorPack corPack, JSONArray args, final Promise cb) throws JSONException {
        double latitude = args.getInt(0);
        double longitude = args.getInt(1);

        double[] convertLonLat = com.huawei.hms.cordova.location.utils.GPSUtil.gps84ToGcj02(latitude, longitude);

        cb.success(ObjectToJSON.convertLonLatToJSON(convertLonLat));
    }
}
